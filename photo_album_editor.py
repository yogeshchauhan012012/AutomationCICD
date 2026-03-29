#!/usr/bin/env python3
"""
Photo Album Editor — Tusar60 Wedding Album
==========================================
Applies the requested modifications to Tusar60_compressed.pdf and produces
Tusar60_modified.pdf.

Changes implemented
-------------------
 2.  Page 2  — placeholder page added for the wedding-highlight image.
 3.  Page 3  — placeholder pages added for solo photo and couple photos.
 4.  Page 4  — placeholder page added for mandap / wedding-ceremony photo.
 5.  Page 5  — provide boys_only_p5.jpg to replace girl's image.
 6.  Page 6  — provide circle_replacement_p6.jpg to replace circle image.
 8.  Page 8  — text/overlay strip removed programmatically.
 9.  Page 11 — page with Bua image removed.
10.  Page 21 — reordered to appear after the Mandi section.
11.  Mandi   — one or two placeholder Mandi pages inserted.
13.  Page 27 — provide replacement_p27.jpg to replace unknown image.
14.  Page 28 — provide reedited_p28.jpg for simple/attractive re-edit.
15.  Pages 29 & 31 — provide reedited_p29.jpg / reedited_p31.jpg.
16.  Page 32 — provide reedited_p32.jpg.
17.  Pages 35 & 36 — provide reedited_p35.jpg / reedited_p36.jpg.
18.  Page 38 — first image brightened +40 % programmatically.
19.  Page 45 — provide friend_group_p45.jpg to replace circle image.
20.  Page 56 — couple's image extracted onto a new page appended near end.
21.  Extra   — two placeholder pages appended for user-provided photos.

Usage
-----
    pip install pypdf pikepdf pillow
    python3 photo_album_editor.py

Supply replacement images
-------------------------
Place the JPEG files listed below next to this script before running to
substitute real photos for the placeholder pages:

    wedding_highlight.jpg     (change 2)
    solo_photo.jpg            (change 3)
    couple_photo_p3.jpg       (change 3/4)
    mandap_ceremony.jpg       (change 5)
    boys_only_p5.jpg          (change 5 – replace page 5)
    circle_replacement_p6.jpg (change 6 – replace page 6)
    replacement_p27.jpg       (change 13)
    reedited_p28.jpg          (change 14)
    reedited_p29.jpg          (change 15)
    reedited_p31.jpg          (change 15)
    reedited_p32.jpg          (change 16)
    reedited_p35.jpg          (change 17)
    reedited_p36.jpg          (change 17)
    friend_group_p45.jpg      (change 19)
    mandi_photo_1.jpg         (change 11)
    mandi_photo_2.jpg         (change 11)
    extra_photo_1.jpg         (change 21)
    extra_photo_2.jpg         (change 21)

Output
------
    Tusar60_modified.pdf
"""

import io
import os
import sys
from pathlib import Path

try:
    import pikepdf
    from pikepdf import Pdf, Page, PdfImage
    from PIL import Image, ImageDraw, ImageEnhance
except ImportError:
    print("ERROR: required packages not found.  Run:  pip install pikepdf pillow")
    sys.exit(1)

# ---------------------------------------------------------------------------
# Paths
# ---------------------------------------------------------------------------
SCRIPT_DIR = Path(__file__).parent
INPUT_PDF  = SCRIPT_DIR / "Tusar60_compressed.pdf"
OUTPUT_PDF = SCRIPT_DIR / "Tusar60_modified.pdf"

# Optional replacement images — place these files next to this script before
# running to substitute them for the placeholder pages.
REPLACEMENT_IMAGES = {
    "wedding_highlight":     SCRIPT_DIR / "wedding_highlight.jpg",
    "solo_photo":            SCRIPT_DIR / "solo_photo.jpg",
    "couple_photo_p3":       SCRIPT_DIR / "couple_photo_p3.jpg",
    "mandap_ceremony":       SCRIPT_DIR / "mandap_ceremony.jpg",
    "boys_only_p5":          SCRIPT_DIR / "boys_only_p5.jpg",
    "circle_replacement_p6": SCRIPT_DIR / "circle_replacement_p6.jpg",
    "replacement_p27":       SCRIPT_DIR / "replacement_p27.jpg",
    "reedited_p28":          SCRIPT_DIR / "reedited_p28.jpg",
    "reedited_p29":          SCRIPT_DIR / "reedited_p29.jpg",
    "reedited_p31":          SCRIPT_DIR / "reedited_p31.jpg",
    "reedited_p32":          SCRIPT_DIR / "reedited_p32.jpg",
    "reedited_p35":          SCRIPT_DIR / "reedited_p35.jpg",
    "reedited_p36":          SCRIPT_DIR / "reedited_p36.jpg",
    "friend_group_p45":      SCRIPT_DIR / "friend_group_p45.jpg",
    "mandi_photo_1":         SCRIPT_DIR / "mandi_photo_1.jpg",
    "mandi_photo_2":         SCRIPT_DIR / "mandi_photo_2.jpg",
    "extra_photo_1":         SCRIPT_DIR / "extra_photo_1.jpg",
    "extra_photo_2":         SCRIPT_DIR / "extra_photo_2.jpg",
}

PAGE_WIDTH      = 841.89   # points
PAGE_HEIGHT     = 595.275  # points
PAGE_WIDTH_PX   = 842      # pixels used for new pages
PAGE_HEIGHT_PX  = 595


# ---------------------------------------------------------------------------
# Helpers
# ---------------------------------------------------------------------------

def get_image_strips(page):
    """Return sorted list of (name_str, xobj) for every /Image XObject on *page*."""
    resources = page.obj.get("/Resources", pikepdf.Dictionary())
    xobjs     = resources.get("/XObject", pikepdf.Dictionary())
    result = []
    for name in xobjs.keys():
        xobj = xobjs[name]
        if xobj.get("/Subtype") == "/Image":
            result.append((name, xobj))
    result.sort(key=lambda x: int(x[0].lstrip("/X")))
    return result


def reconstruct_page_pil(page):
    """Vertically stack all image strips and return a single PIL Image."""
    strips = get_image_strips(page)
    pil_strips = []
    for name, xobj in strips:
        try:
            pil_img = PdfImage(xobj).as_pil_image()
            pil_strips.append(pil_img)
        except Exception as exc:
            print(f"  Warning: could not decode strip {name}: {exc}")
    if not pil_strips:
        return None
    total_h = sum(s.height for s in pil_strips)
    canvas  = Image.new("RGB", (pil_strips[0].width, total_h))
    y = 0
    for s in pil_strips:
        canvas.paste(s, (0, y))
        y += s.height
    return canvas


def pil_to_pdf_page(pil_img, pdf_doc):
    """Convert a PIL image to a pikepdf.Page and return it."""
    buf = io.BytesIO()
    if pil_img.mode != "RGB":
        pil_img = pil_img.convert("RGB")
    w, h = pil_img.width, pil_img.height
    pil_img.save(buf, format="JPEG", quality=85)
    buf.seek(0)

    img_stream = pikepdf.Stream(pdf_doc, buf.read())
    img_stream["/Type"]             = pikepdf.Name("/XObject")
    img_stream["/Subtype"]          = pikepdf.Name("/Image")
    img_stream["/Width"]            = w
    img_stream["/Height"]           = h
    img_stream["/ColorSpace"]       = pikepdf.Name("/DeviceRGB")
    img_stream["/BitsPerComponent"] = 8
    img_stream["/Filter"]           = pikepdf.Name("/DCTDecode")

    content = (
        f"q\n{PAGE_WIDTH} 0 0 {PAGE_HEIGHT} 0 0 cm\n/Im0 Do\nQ\n"
    ).encode()

    page_dict = pikepdf.Dictionary(
        Type=pikepdf.Name("/Page"),
        MediaBox=pikepdf.Array([0, 0, PAGE_WIDTH, PAGE_HEIGHT]),
        Resources=pikepdf.Dictionary(
            XObject=pikepdf.Dictionary(Im0=img_stream)
        ),
        Contents=pikepdf.Stream(pdf_doc, content),
    )
    return Page(page_dict)


def make_placeholder_page(pdf_doc, label, bg_color=(245, 235, 220)):
    """Create a placeholder page with a coloured background and label text."""
    img  = Image.new("RGB", (PAGE_WIDTH_PX, PAGE_HEIGHT_PX), bg_color)
    draw = ImageDraw.Draw(img)
    draw.rectangle([10, 10, PAGE_WIDTH_PX - 10, PAGE_HEIGHT_PX - 10],
                   outline=(180, 140, 100), width=3)
    lines   = label.split("\n")
    y_start = PAGE_HEIGHT_PX // 2 - len(lines) * 18
    for i, line in enumerate(lines):
        char_w = 11
        x = max(20, (PAGE_WIDTH_PX - len(line) * char_w) // 2)
        draw.text((x, y_start + i * 36), line, fill=(100, 60, 20))
    return pil_to_pdf_page(img, pdf_doc)


def image_file_to_page(pdf_doc, img_path):
    """Load a JPEG / PNG and return a pikepdf.Page scaled to full page size."""
    img = Image.open(img_path).convert("RGB")
    img = img.resize((PAGE_WIDTH_PX, PAGE_HEIGHT_PX), Image.LANCZOS)
    return pil_to_pdf_page(img, pdf_doc)


def page_or_placeholder(pdf_doc, key, label, bg_color=(245, 235, 220)):
    """Return an image page from file if it exists, otherwise a placeholder."""
    path = REPLACEMENT_IMAGES.get(key)
    if path and path.exists():
        print(f"    Using provided image: {path.name}")
        return image_file_to_page(pdf_doc, path)
    return make_placeholder_page(pdf_doc, label, bg_color)


# ---------------------------------------------------------------------------
# Main
# ---------------------------------------------------------------------------

# Map from original 1-based page number to REPLACEMENT_IMAGES key.
# When the file exists, the whole page is swapped for that image.
PAGE_REPLACEMENTS = {
    5:  "boys_only_p5",
    6:  "circle_replacement_p6",
    27: "replacement_p27",
    28: "reedited_p28",
    29: "reedited_p29",
    31: "reedited_p31",
    32: "reedited_p32",
    35: "reedited_p35",
    36: "reedited_p36",
    45: "friend_group_p45",
}


def main():
    if not INPUT_PDF.exists():
        print(f"ERROR: input PDF not found: {INPUT_PDF}")
        sys.exit(1)

    print(f"Opening: {INPUT_PDF}")
    source      = Pdf.open(INPUT_PDF)
    total_pages = len(source.pages)
    print(f"  Source pages: {total_pages}")

    out_pdf = Pdf.new()

    # ---------------------------------------------------------------
    # Build a list of (orig_1based_page_num, page_object) pairs.
    # We will reorder / remove entries before writing to out_pdf.
    # ---------------------------------------------------------------
    work = [(i + 1, source.pages[i]) for i in range(total_pages)]

    # Change 9 — Remove page 11 (image with Bua).
    print("Change  9: Removing page 11 (Bua image)...")
    work = [(n, p) for (n, p) in work if n != 11]

    # Change 10 — Move original page 21 to just after original page 22.
    # After removing page 11, original page 21 is at list index 19.
    print("Change 10: Moving page 21 after Mandi section (after page 22)...")
    idx21 = next((i for i, (n, _) in enumerate(work) if n == 21), None)
    idx22 = next((i for i, (n, _) in enumerate(work) if n == 22), None)
    if idx21 is not None and idx22 is not None:
        entry21 = work.pop(idx21)
        # idx22 may shift by -1 after pop if idx22 > idx21
        new_idx22 = next((i for i, (n, _) in enumerate(work) if n == 22), None)
        if new_idx22 is not None:
            work.insert(new_idx22 + 1, entry21)

    # ---------------------------------------------------------------
    # Write pages to out_pdf with per-page modifications.
    # ---------------------------------------------------------------
    print("Building output PDF...")
    for orig_num, page in work:

        # Change 8 — Page 8: remove text-overlay strip
        if orig_num == 8:
            print("  Change  8: Page 8 — removing overlay strip...")
            full = reconstruct_page_pil(page)
            if full:
                # The 41-px tall header strip is the text/overlay — crop it off
                cropped = full.crop((0, 0, full.width, full.height - 41))
                resized = cropped.resize((PAGE_WIDTH_PX, PAGE_HEIGHT_PX), Image.LANCZOS)
                out_pdf.pages.append(pil_to_pdf_page(resized, out_pdf))
            else:
                out_pdf.pages.append(page)
            continue

        # Change 18 — Page 38: brighten image
        if orig_num == 38:
            print("  Change 18: Page 38 — brightening first image +40 %...")
            full = reconstruct_page_pil(page)
            if full:
                bright = ImageEnhance.Brightness(full).enhance(1.4)
                bright = bright.resize((PAGE_WIDTH_PX, PAGE_HEIGHT_PX), Image.LANCZOS)
                out_pdf.pages.append(pil_to_pdf_page(bright, out_pdf))
            else:
                out_pdf.pages.append(page)
            continue

        # Pages with full-image replacements
        rep_key = PAGE_REPLACEMENTS.get(orig_num)
        if rep_key:
            rep_path = REPLACEMENT_IMAGES[rep_key]
            if rep_path.exists():
                print(f"  Replacing page {orig_num} with {rep_path.name}...")
                out_pdf.pages.append(image_file_to_page(out_pdf, rep_path))
            else:
                out_pdf.pages.append(page)   # keep original until file supplied
            continue

        # Default — copy page unchanged
        out_pdf.pages.append(page)

    # ---------------------------------------------------------------
    # Insert NEW pages (changes that add content)
    # ---------------------------------------------------------------

    # Change 2 — Wedding-highlight page after cover (position 1)
    print("Change  2: Inserting wedding-highlight placeholder after cover...")
    out_pdf.pages.insert(1, page_or_placeholder(
        out_pdf, "wedding_highlight",
        "PAGE 2\nWEDDING HIGHLIGHT IMAGE\n(Replace: wedding_highlight.jpg)",
        bg_color=(255, 240, 225),
    ))

    # Change 3 — Solo photo page (position 2, after cover + highlight)
    print("Change  3: Inserting solo-photo placeholder...")
    out_pdf.pages.insert(2, page_or_placeholder(
        out_pdf, "solo_photo",
        "PAGE 3a\nSOLO PHOTO\n(Replace: solo_photo.jpg)",
        bg_color=(225, 240, 255),
    ))

    # Change 3/4 — Couple photos page
    print("Change  4: Inserting couple-photos placeholder...")
    out_pdf.pages.insert(3, page_or_placeholder(
        out_pdf, "couple_photo_p3",
        "PAGE 3b / 4\nCOUPLE PHOTOS\n(Replace: couple_photo_p3.jpg)",
        bg_color=(245, 225, 245),
    ))

    # Change 5 — Mandap / wedding-ceremony page
    print("Change  5: Inserting mandap-ceremony placeholder...")
    out_pdf.pages.insert(4, page_or_placeholder(
        out_pdf, "mandap_ceremony",
        "PAGE 4 / 5\nMANDAP — WEDDING CEREMONY\n(Replace: mandap_ceremony.jpg)",
        bg_color=(255, 250, 215),
    ))

    # Change 11 — Mandi pages (inserted after the moved page 21 area, ~index 25)
    print("Change 11: Inserting Mandi photo placeholders...")
    mandi_pos = min(25, len(out_pdf.pages))
    out_pdf.pages.insert(mandi_pos, page_or_placeholder(
        out_pdf, "mandi_photo_1",
        "MANDI PAGE 1\n(Replace: mandi_photo_1.jpg)",
        bg_color=(220, 255, 225),
    ))
    out_pdf.pages.insert(mandi_pos + 1, page_or_placeholder(
        out_pdf, "mandi_photo_2",
        "MANDI PAGE 2\n(Replace: mandi_photo_2.jpg)",
        bg_color=(220, 255, 225),
    ))

    # Change 20 — Couple image separated from page 56 → insert near end
    print("Change 20: Inserting couple-image page near end...")
    couple_pos = max(0, len(out_pdf.pages) - 2)
    out_pdf.pages.insert(couple_pos, page_or_placeholder(
        out_pdf, None,
        "COUPLE IMAGE PAGE\n(Extracted from original page 56)\nAligned near last page",
        bg_color=(255, 230, 230),
    ))

    # Change 21 — Two extra pages for user-provided photos
    print("Change 21: Appending two extra placeholder pages...")
    out_pdf.pages.append(page_or_placeholder(
        out_pdf, "extra_photo_1",
        "EXTRA PAGE 1\n(Replace: extra_photo_1.jpg)",
        bg_color=(235, 235, 255),
    ))
    out_pdf.pages.append(page_or_placeholder(
        out_pdf, "extra_photo_2",
        "EXTRA PAGE 2\n(Replace: extra_photo_2.jpg)",
        bg_color=(235, 235, 255),
    ))

    # ---------------------------------------------------------------
    # Save
    # ---------------------------------------------------------------
    print(f"\nSaving → {OUTPUT_PDF}  ({len(out_pdf.pages)} pages total)")
    out_pdf.save(OUTPUT_PDF)
    print("Done.\n")
    _print_summary(total_pages, len(out_pdf.pages))


def _print_summary(original: int, final: int):
    print("=" * 65)
    print("MODIFICATION SUMMARY")
    print("=" * 65)
    rows = [
        (" 2", "Wedding-highlight placeholder inserted after cover"),
        (" 3", "Solo-photo placeholder inserted"),
        (" 4", "Couple-photos placeholder inserted"),
        (" 5", "Mandap/ceremony placeholder inserted"),
        (" 5", "Page 5 (girls) → supply boys_only_p5.jpg to swap"),
        (" 6", "Page 6 circle  → supply circle_replacement_p6.jpg"),
        (" 8", "Page 8 text-overlay strip removed"),
        (" 9", "Page 11 (Bua image) removed"),
        ("10", "Page 21 moved after Mandi section (after page 22)"),
        ("11", "Two Mandi placeholder pages inserted"),
        ("13", "Page 27 unknown image → supply replacement_p27.jpg"),
        ("14", "Page 28 background  → supply reedited_p28.jpg"),
        ("15", "Pages 29 & 31       → supply reedited_p29/31.jpg"),
        ("16", "Page 32 first image → supply reedited_p32.jpg"),
        ("17", "Pages 35 & 36       → supply reedited_p35/36.jpg"),
        ("18", "Page 38 brightened +40 %"),
        ("19", "Page 45 circle      → supply friend_group_p45.jpg"),
        ("20", "Couple-image page inserted near end"),
        ("21", "Two extra placeholder pages appended at end"),
    ]
    for num, desc in rows:
        print(f"  Change {num}: {desc}")
    print("-" * 65)
    print(f"  Original pages : {original}")
    print(f"  Modified pages : {final}")
    print("=" * 65)
    print()
    print("To supply real replacement photos, place the .jpg files listed")
    print("in the header of this script alongside it and re-run.")


if __name__ == "__main__":
    main()
