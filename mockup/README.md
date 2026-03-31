# Power Ampache 2 × Android Auto — UI mockup

Disposable **Svelte + Vite** browser prototype for **P0–P3** scenarios from [`docs/ux-research/08-mockup-handoff-package.md`](../docs/ux-research/08-mockup-handoff-package.md), aligned with root [`AGENTS.md`](../AGENTS.md) (frame labelling, phone player direction, Auto vs sandbox).

## What this is

| Frame label | Styling |
|-------------|---------|
| **Phone — PA2 theme** | Nunito + colors from [`docs/design-system/01-brand-and-language.md`](../docs/design-system/01-brand-and-language.md) |
| **Auto — host media (PA2)** | Generic list / now-playing pattern (system fonts, neutral chrome). Real Android Auto does **not** use PA2 hex or Nunito on the media path. |
| **Sandbox — Car App Library** | DHU screenshot stand-ins under `mockup/public/dhu/` (`dhu-browse.png`, `dhu-now-playing.png`). Replace with **real** PNGs from the DHU (see below). Not PA2 shipping Auto UI. |

Screens include **P3** phone queue (read-mostly) and **P2** generic Auto error wireframe. Phone now-playing uses **`public/pa2/placeholder-album.svg`** (converted from PA2 `app/.../drawable/placeholder_album.xml`, tint `onSurfaceVariant`) for zoom-fill backdrop + foreground art — no external image URLs.

**Typography:** Nunito is loaded from **`public/fonts/Nunito-Variable.ttf`** (OFL, `public/fonts/OFL.txt`); `index.html` does not use Google Fonts or other CDNs.

The runnable **Car App Library** surface is the Kotlin sample under `app/src/main/.../car/`; DHU entries in the mockup **embed** head-unit captures of that flow.

## Run the mockup (browser)

```bash
cd mockup
npm install   # first time
npm run dev
```

Open the URL Vite prints (usually `http://localhost:5173`). Use the left rail to switch screens.

## Desktop Head Unit (DHU) — sandbox Android app

The **DHU does not load this web UI**. It connects to **Android Auto on a device or emulator** and shows whatever the **installed car-capable app** projects.

To exercise the **this repo’s Car App Library sample** in DHU:

1. Install **Android Auto Desktop Head Unit** via Android Studio → **SDK Manager** → **SDK Tools** (see [Test Android apps for cars](https://developer.android.com/training/cars/testing)).
2. Create/start an **AVD** or plug in a **physical device** with developer options and USB debugging.
3. From the **sandbox repo root** (parent of `mockup/`):

   ```bash
   ./gradlew installDebug
   ```

4. Start the **Desktop Head Unit** from Android Studio (**Tools** → **Android Auto** → **Desktop Head Unit**) or run the `desktop-head-unit` binary from your SDK extras path.
5. On the device, enable Android Auto developer mode if prompted; connect DHU to the phone/emulator per Google’s testing doc.

**Power Ampache 2 production** Auto UI is **host-rendered media** (Media3), not this sample’s templates — confirm browse/session behaviour in a `Power-Ampache-2` clone before treating DHU output as shipping PA2.

### DHU screenshots for this mockup

1. Connect a device or emulator with **Android Auto** dev options and the sample app installed (`./gradlew installDebug` from repo root).
2. Forward the head-unit port (from [DHU testing](https://developer.android.com/training/cars/testing/dhu)):  
   `adb forward tcp:5277 tcp:5277`
3. Run the DHU binary (e.g. `$ANDROID_HOME/extras/google/auto/desktop-head-unit` on macOS/Linux).
4. In the **terminal where DHU is running**, save frames:  
   `screenshot /tmp/dhu-browse.png` — navigate UI, then `screenshot /tmp/dhu-now-playing.png` (or any paths you prefer).
5. Copy into the mockup:  
   `./tools/refresh-dhu-mockup-images.sh /tmp/dhu-browse.png /tmp/dhu-now-playing.png`

## Related docs

- [`docs/design-system/00-design-system-index.md`](../docs/design-system/00-design-system-index.md)
- [`docs/ux-research/05-design-guardrails-checklist.md`](../docs/ux-research/05-design-guardrails-checklist.md)
