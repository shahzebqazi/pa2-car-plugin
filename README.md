# Android Auto workspace (Power Ampache 2 — full car stack)

**AI agent pack (roles, workflows, MCP stubs):** [`android-auto-agents/`](android-auto-agents/) — companion to [`AGENTS.md`](AGENTS.md) and [`docs/agents/`](docs/agents/README.md) (sharded PRD, DHU, mockup rules).

This repository supports **Power Ampache 2 on Android Auto** through **Media3**-style integration: **`MediaSession`** + **`MediaSessionService`**, media-browser manifest wiring, a browsable **`MediaItem`** tree (per [Google’s media-for-cars](https://developer.android.com/training/cars/media) contract), metadata, artwork, errors, and voice. **How much browse appears on the head unit** depends on what the **installed media APK** implements (**Power-Ampache-2**, **PowerAmpache2PluginTemplate** plugin, or another build) — see **`docs/ux-research/01-platform-constraint-sheet.md`** (code-scan caveat). UX research, the **design system**, **`mockup/`** (Svelte), and **`AGENTS.md`** describe the **host-rendered** car path and the companion **phone** experience.

**Gradle layout:** This umbrella repo is **docs + mockup + agents + scripts** — **no** root `./gradlew`. **Android builds** live in a nested clone of **`PowerAmpache2PluginTemplate`** (set **`PA2_PLUGIN_GRADLE_ROOT`**). **Primary phone product** for library / player UX is **[Power-Ampache-2](https://github.com/icefields/Power-Ampache-2)**; the **plugin** template (upstream **`main`**: **SongList** + mocks + **`MusicFetcher`** scaffold — **verify** `AndroidManifest.xml` after each pull for **Media3 / Auto**; **`PowerAmpache2PluginTemplateOld/`** is **not** canonical). **DHU** targets whichever installed APK registers **media-browser / session** services. Clone as needed:

```bash
git clone https://github.com/icefields/Power-Ampache-2.git Power-Ampache-2
# Plugin template: use YOUR fork (origin); add icefields as upstream for pulls
gh repo fork icefields/PowerAmpache2PluginTemplate --clone=true
# or: git clone git@github.com:<you>/PowerAmpache2PluginTemplate.git PowerAmpache2PluginTemplate
#      cd PowerAmpache2PluginTemplate && git remote add upstream git@github.com:icefields/PowerAmpache2PluginTemplate.git
cd PowerAmpache2PluginTemplate && git submodule update --init
```

This workspace uses branch **`plugin/AndroidAuto`** on the fork (merge **`upstream/main`** when syncing maintainer). See [`docs/agents/01-prd-and-backlog.md`](docs/agents/01-prd-and-backlog.md).

Use **`Power-Ampache-2`** for **full-app** Media3 + Auto behaviour and **DHU** when that tree declares car media. Use **`PowerAmpache2PluginTemplate`** for **plugin** work per maintainer scope (**default: plugin `app/` only**; do not expand **`data`/`domain`** without an explicit task — see **`AGENTS.md`**).

## Prerequisites

1. **Android Studio** (recommended) or **Android SDK command-line tools**  
   Install from [developer.android.com/studio](https://developer.android.com/studio).

2. **JDK 17** (Temurin 17 works). Android Studio bundles a JRE; standalone builds need `JAVA_HOME` pointing at JDK 17.

3. **SDK path** (for **nested `PowerAmpache2PluginTemplate/`** builds)  
   - With Android Studio: open the **plugin** project folder; Studio creates `local.properties` with `sdk.dir`.  
   - CLI only: copy `local.properties.example` into **`PowerAmpache2PluginTemplate/local.properties`** and set `sdk.dir` (on macOS often `~/Library/Android/sdk`).  
   - **Homebrew command-line tools** (this machine): `brew install --cask android-commandlinetools` → SDK root is typically `/opt/homebrew/share/android-commandlinetools`; set `sdk.dir` to that path and add `cmdline-tools/latest/bin`, `platform-tools`, and `emulator` to your `PATH`.

4. **Environment (optional, for android-auto-agents/scripts/CI)**  
   - `ANDROID_HOME` or `ANDROID_SDK_ROOT` = same directory as `sdk.dir`.  
   - `PA2_PLUGIN_GRADLE_ROOT` = absolute path to your nested PA2 **plugin** Gradle root (directory containing `./gradlew`) — required for `gradle-plugin-template.sh`, `adb-plugin-install-debug.sh`, and related harness scripts (see **`android-auto-agents/README.md`**).

## Build and test (nested plugin)

From the repo root, with **`PowerAmpache2PluginTemplate/`** present (or set **`PA2_PLUGIN_GRADLE_ROOT`** to its absolute path):

```bash
export PA2_PLUGIN_GRADLE_ROOT="$PWD/PowerAmpache2PluginTemplate"
./android-auto-agents/scripts/gradle-plugin-template.sh :app:assembleDebug
./android-auto-agents/scripts/gradle-plugin-template.sh :app:testDebugUnitTest
./android-auto-agents/scripts/gradle-plugin-template.sh :app:lintDebug
# ./android-auto-agents/scripts/gradle-plugin-template.sh :app:connectedDebugAndroidTest   # device/emulator
```

**Mockup (no Android):** `cd mockup && npm ci && npm run check && npm run build`

## Live mockup (GitHub Pages)

After each push to `main`, [`.github/workflows/deploy-github-pages.yml`](.github/workflows/deploy-github-pages.yml) builds `mockup/` and deploys the static site to **GitHub Pages** (project site):

**https://sqazi.sh/pa2-car-plugin/** (canonical; `https://shahzebqazi.github.io/pa2-car-plugin/` redirects here if your account uses this domain for Pages)

Use `cd mockup && npm run dev` locally (assets use base `/`). CI sets `VITE_BASE_PATH=/pa2-car-plugin` so production assets resolve under that path.

## Android Auto / DHU (full stack)

**Desktop Head Unit (DHU)** is the primary loop for **car UI** during development: install via **SDK Manager → SDK Tools → Android Auto Desktop Head Unit**, or `sdkmanager "extras;google;auto"`. Docs: [Test Android apps for cars](https://developer.android.com/training/cars/testing) and [DHU user guide](https://developer.android.com/training/cars/testing/dhu).

- **Launcher (USB only):** From the repo root, **`./android-auto-agents/scripts/dhu-start.sh`** kills stale DHU (`pkill` is safe if none is running), force-stops Android Auto on the device, then starts **USB accessory** DHU with `-c …/config/default.ini` and **always on top**. Use **`./android-auto-agents/scripts/dhu-start.sh auto`** for **USB retries** when the log shows transport errors, **SSL auth failure (-3)**, or DHU exits **0** before the phone reports a protocol handshake. **TCP / `adb forward`** is not implemented in this script — see [Run the DHU](https://developer.android.com/training/cars/testing/dhu) for that path. Requires `sdk.dir` in the **plugin** project’s `local.properties` (or `ANDROID_HOME`) when building the APK.
- **Car-capable APK:** Build and install an APK whose **manifest** registers **Android Auto / Media3** (**Power-Ampache-2** and/or **PowerAmpache2PluginTemplate** on your branch). This repo has **no** separate phone reference APK — phone UX is **[Power-Ampache-2](https://github.com/icefields/Power-Ampache-2)**; **mockup/** mirrors PA2 + Auto scenarios in the browser. Enable **Unknown sources** in Android Auto developer settings for sideloaded builds; see **`docs/agents/02-dhu-and-car-testing.md`**.
- **Shipping PA2 on Auto** is **host-rendered media** — session, browse tree, metadata, artwork, and errors must satisfy Google’s [media-for-cars](https://developer.android.com/training/cars/media) guidance; validate on **DHU** and real hardware. Compare outcomes to **`mockup/`** at **information hierarchy**, not pixels.

## Project layout

| Path | Purpose |
|------|--------|
| `mockup/` | Svelte browser mockups — **PA2 phone** vs **DHU / plugin Auto** slices (`AGENTS.md`, `mockup/README.md`) |
| `android-auto-agents/` | DHU/install scripts, workflows, verification contracts |
| `AGENTS.md` + `docs/agents/` | Agent roles, PRD backlog (shard), DHU, mockup boundaries |
| `docs/android-auto-ux-research-plan.md` | Phased UX/UI research plan |
| *Nested PA2 plugin clone* (optional; often gitignored) | Plugin Gradle project; set **`PA2_PLUGIN_GRADLE_ROOT`** then run **`./android-auto-agents/scripts/gradle-plugin-template.sh :app:testDebugUnitTest`**. This workspace often uses a fork under a conventional folder name — see **`AGENTS.md`** / **`docs/agents/`**. |
| *`Power-Ampache-2/`* (after clone) | Upstream full app; **PRs** here (name may differ) |

## Git

This tree may be published as **[pa2-car-plugin](https://github.com/shahzebqazi/pa2-car-plugin)** on GitHub (docs + mockup CI). **Power-Ampache-2** and **PowerAmpache2PluginTemplate** are usually **nested clones** with their own `.git`; common practice is to list those directories in `.gitignore` here unless you use submodules. See `AGENTS.md` for PR targets and verification expectations.
