# Android Auto workspace

This **directory is a sandbox**: it holds a small Gradle + Kotlin sample using the **Android for Cars App Library** (projected UI), plus docs and agent instructions. **Production Power Ampache work**—the repo your PRs target—should live in a **separate clone inside this folder**, e.g.:

```bash
git clone <maintainer-repository-url> Power-Ampache-2
```

Use the clone for upstream branches and PRs; use this root for experiments, mockups, and the sample `app/` unless you wire the two together on purpose.

## Prerequisites

1. **Android Studio** (recommended) or **Android SDK command-line tools**  
   Install from [developer.android.com/studio](https://developer.android.com/studio).

2. **JDK 17** (Temurin 17 works). Android Studio bundles a JRE; standalone builds need `JAVA_HOME` pointing at JDK 17.

3. **SDK path**  
   - With Android Studio: open this folder; Studio creates `local.properties` with `sdk.dir`.  
   - CLI only: copy `local.properties.example` to `local.properties` and set `sdk.dir` to your SDK (on macOS often `~/Library/Android/sdk`).  
   - **Homebrew command-line tools** (this machine): `brew install --cask android-commandlinetools` → SDK root is typically `/opt/homebrew/share/android-commandlinetools`; set `sdk.dir` to that path and add `cmdline-tools/latest/bin`, `platform-tools`, and `emulator` to your `PATH`.

4. **Environment (optional, for scripts/CI)**  
   - `ANDROID_HOME` or `ANDROID_SDK_ROOT` = same directory as `sdk.dir`.

## Build and test

```bash
./gradlew assembleDebug
./gradlew testDebugUnitTest
./gradlew connectedDebugAndroidTest   # requires a device or emulator
```

## Live mockup (GitHub Pages)

After each push to `main`, [`.github/workflows/deploy-github-pages.yml`](.github/workflows/deploy-github-pages.yml) builds `mockup/` and deploys the static site to **GitHub Pages** (project site):

**https://sqazi.sh/pa2-car-plugin/** (canonical; `https://shahzebqazi.github.io/pa2-car-plugin/` redirects here if your account uses this domain for Pages)

Use `cd mockup && npm run dev` locally (assets use base `/`). CI sets `VITE_BASE_PATH=/pa2-car-plugin` so production assets resolve under that path.

## Android Auto development

- **Desktop Head Unit (DHU)** — run your car UI on the desktop while the app runs on a device or emulator.  
  Install via **SDK Manager → SDK Tools → Android Auto Desktop Head Unit**, or CLI:  
  `sdkmanager "extras;google;auto"`  
  Binary: `<sdk>/extras/google/auto/desktop-head-unit` (keep `libusb-1.0.so` alongside it).  
  Docs: [Test Android apps for cars](https://developer.android.com/training/cars/testing) and [DHU user guide](https://developer.android.com/training/cars/testing/dhu).

- **Categories and validation** — this sample uses `androidx.car.app.category.POI`. Navigation and other categories have extra rules; see [Build Android Auto apps](https://developer.android.com/training/cars/apps).

- **Host validator** — `MainCarAppService` uses `HostValidator.ALLOW_ALL_HOSTS_VALIDATOR` for local development. Tighten this before release.

## Project layout

| Path | Purpose |
|------|--------|
| `app/src/main/.../car/` | Car App Library: `CarAppService`, `Screen`, templates |
| `app/src/test/` | JVM unit tests |
| `app/src/androidTest/` | On-device Espresso / integration tests |
| `AGENTS.md` | Agent roles and handoffs (sandbox vs clone) |
| `docs/android-auto-ux-research-plan.md` | Phased UX/UI research plan (driver UX, accessibility, safety) |
| *`Power-Ampache-2/`* (after clone) | Upstream app; **PRs and integration work** go here (name may differ) |

## Git

This tree is published as **[pa2-car-plugin](https://github.com/shahzebqazi/pa2-car-plugin)** on GitHub. The nested **Power Ampache 2** clone is listed in `.gitignore`; clone it locally for upstream work (see top of this file). The **upstream** repository is a **nested clone** in a subdirectory with its own `.git`. If you do not want the sandbox repo to track the clone, add that directory name to `.gitignore` (unless you use a submodule).

See `AGENTS.md` for coordination between sandbox and the cloned project.
