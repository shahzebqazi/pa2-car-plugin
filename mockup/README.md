# Power Ampache 2 × Android Auto — UI mockup

**Svelte + Vite** browser preview for **P0–P3** flows in [`docs/ux-research/08-mockup-handoff-package.md`](../docs/ux-research/08-mockup-handoff-package.md). Same labelling ideas as root [`AGENTS.md`](../AGENTS.md).

## What this is

| Kind | Styling |
|------|---------|
| **Phone — PA2 theme** | Nunito + colors from [`docs/design-system/01-brand-and-language.md`](../docs/design-system/01-brand-and-language.md) |
| **Auto — host media (PA2)** | Plain list / now-playing layout (neutral chrome). Real Android Auto does **not** use PA2 colors or Nunito. |

There is **no** extra “sandbox template” row in the mockup app anymore: **Car App Library** behaviour is exercised in **Kotlin** under `app/src/main/.../car/` and, if you like, in the **Desktop Head Unit**. Optional **`public/dhu/*.png`** shots support the **home** page hero and side-by-side checks — not duplicate Svelte frames.

Phone now-playing uses **`public/pa2/placeholder-album.svg`** (from PA2 `placeholder_album` artwork) for backdrop + art — no remote images.

**Typography:** **`public/fonts/Nunito-Variable.ttf`** (OFL in `public/fonts/OFL.txt`); no Google Fonts CDN in `index.html`.

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
