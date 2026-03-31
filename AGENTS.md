# Agent instructions — Android Auto (Power Ampache 2)

This document defines how specialized agents should work in this workspace.

**Sandbox vs upstream:** The **root of this directory** (`android-auto`) is a **sandbox**: the sample Gradle app, agent notes, and prototypes live here. The **canonical Power Ampache / integration codebase** is **not** this tree alone—it is expected as a **Git clone in a subdirectory** of this folder (e.g. `Power-Ampache-2/`). **Reference upstream:** [github.com/icefields/Power-Ampache-2](https://github.com/icefields/Power-Ampache-2); theme module submodule: [github.com/icefields/PowerAmpache2Theme](https://github.com/icefields/PowerAmpache2Theme). Work you intend to **merge upstream** happens in that clone (branches, PRs); the sandbox is for spikes, mockups, and the Car App Library sample unless you explicitly copy or integrate.

The sandbox Android app uses **Gradle + Kotlin** and the **Android for Cars App Library** (see `README.md`). **Production Power Ampache 2** uses **Media3** (`MediaSession` + `MediaSessionService`); Android Auto UX for PA2 follows **Google’s media-for-cars** (host-rendered) model, **not** the sandbox’s templated `androidx.car.app` sample unless a task says otherwise.

---

## Shared context for all agents

- **Sandbox app (this repo root)**: Kotlin under `app/src/main/`, car UI under `app/src/main/java/.../car/`, tests under `app/src/test/` and `app/src/androidTest/`.
- **Upstream clone (subdirectory)**: After `git clone <maintainer-repo-url> <dir>`, do feature work and open PRs from **that** repository’s working tree. Keep the sandbox and the clone mentally separate unless a task says to wire them together.
- **Build and verify**: `./gradlew assembleDebug`, `./gradlew testDebugUnitTest`, `./gradlew connectedDebugAndroidTest` (device/emulator for instrumented tests).
- **Android Auto testing**: Desktop Head Unit (DHU), category rules, and host validation are described in `README.md`.
- **Safety and compliance**: In-car UI must respect driver distraction guidelines (e.g. glanceability, limited interaction depth, no unsafe patterns). Research and mockups should align with platform constraints, not fight them.
- **Mockup app (`mockup/`):** Vite + Svelte, **hash routes**: `#/` **home** (DHU reference image from `public/dhu/` when present, nav to other sections), `#/design` (**Android Auto** implementation focus: embedded `docs/ux-research/01-platform-constraint-sheet.md` + `docs/design-system/00-design-system-index.md`; **phone** theme tokens in a collapsible reference), `#/research` (expandable sections with inlined markdown for `00-executive-summary`, `07-research-synthesis`, `02-task-analysis-and-flows`, `08-mockup-handoff-package`), `#/mockup` and `#/mockup/<frame>`. Mockup frames cover **Phone — PA2 theme** and **Auto — host media** only; the **Car App Library** sample is the Kotlin app under `app/.../car/` (DHU), not duplicate browser wireframes. **`public/dhu/`** PNGs remain optional assets for the home hero and comparison with a real head unit.
- **Public sandbox mirror (optional):** This workspace may be published as **[github.com/shahzebqazi/pa2-car-plugin](https://github.com/shahzebqazi/pa2-car-plugin)** with the mockup built to **GitHub Pages** (see root `README.md`, `.github/workflows/deploy-github-pages.yml`). That mirror is for review and docs; **upstream PA2 PRs** still target the maintainer clone (`Power-Ampache-2/`), not this repo alone.

### Phone companion — player design direction

Applies to **Power Ampache 2 phone / full-screen player** and the **`mockup/`** **Phone — PA2 theme** now-playing frame (`mockup/src/lib/PhoneNowPlaying.svelte`). **Not** a description of Android Auto’s **host-rendered** media UI unless a task explicitly scopes a custom in-car surface.

- **Blurred zoom-fill background:** Drive the player backdrop from the current track’s **album artwork** — **scale to cover** the viewport, **blur** (heavy enough to read as texture, not sharp detail), and add a **dark or scrim overlay** so title, artist, and controls keep contrast. When art is missing, fall back to theme **surface / gradient** from [`docs/design-system/01-brand-and-language.md`](docs/design-system/01-brand-and-language.md).
- **Smooth motion:** Use **short, smooth** transitions for artwork and metadata changes, play/pause affordances, and sheet / control visibility. **Respect system reduced-motion** (and equivalent accessibility settings); provide a non-animated or minimal path when reduced motion is on.
- **Gestures:** Specify **touch gestures** for the phone player where product intent allows — e.g. **drag / horizontal scrub** on the seek region, **swipe** for next/previous or auxiliary actions, coordinated with hit targets in [`docs/design-system/02-layout-and-navigation.md`](docs/design-system/02-layout-and-navigation.md). Call out that **Android Auto** remains **host-controlled** for the media path; gestures there follow OEM/host behaviour, not custom PA2 chrome.
- **Scrub visualizer:** Along the **playback scrubber** (seek bar + playhead), a **waveform- or wave-generated** visual treatment (organic wave geometry, not only a flat progress tint) can provide a **visual anchor** for scrubbing and optional tie-in to position or audio character per engineering. In **`mockup/`**, the **reference** illustration for that pattern lives on the **Auto — host media** now-playing frame (`AutoNowPlaying.svelte`) as an exploratory, host-adjacent mock (real projected UI is OEM-dependent). The **Phone — PA2 theme** now-playing frame (`PhoneNowPlaying.svelte`) uses a **standard seek bar** plus phone gestures. Validate **motion and density** against [`docs/ux-research/05-design-guardrails-checklist.md`](docs/ux-research/05-design-guardrails-checklist.md) if a variant is ever considered outside parked / phone-only contexts.

**Handoff (phone player):** Mockup developer implements and refines the above in Svelte; researcher notes distraction / a11y implications; development swarm implements in **upstream** Compose (`Power-Ampache-2/`) against tests and theme module.

### Car (Android Auto) — now-playing redesign

Use this when improving **what drivers experience on the head unit** for PA2 over the **Media3 / host-rendered media** path (not the sandbox **Car App Library** templates unless the task explicitly scopes templated UI).

- **What the app controls:** **MediaSession** behaviour, **MediaMetadata** (title, artist, album, display fields), **artwork** (uris, sizing per platform guidance), **PlaybackState** (position, actions, errors), and **queue** semantics where the host exposes them. Prefer one **glanceable primary line** plus a short secondary; align with [`docs/ux-research/02-task-analysis-and-flows.md`](docs/ux-research/02-task-analysis-and-flows.md), [`docs/ux-research/07-research-synthesis.md`](docs/ux-research/07-research-synthesis.md) §6 (now playing row), and [`docs/ux-research/05-design-guardrails-checklist.md`](docs/ux-research/05-design-guardrails-checklist.md). Official integration references: [Create audio media apps](https://developer.android.com/training/cars/media), [Errors in car media apps](https://developer.android.com/training/cars/media/errors).
- **What the host controls:** **Typography, colors, control layout, and scrubber appearance** on standard Android Auto media UI are **OEM / host** decisions. Do not treat `mockup/src/lib/AutoNowPlaying.svelte` (`#/mockup/auto-np`) as a pixel spec for production—use it to **communicate information hierarchy and optional embellishments** (e.g. wave-style scrub exploration) to engineers and reviewers; validate on **DHU** and **real devices** where possible.
- **Constraints reminder:** [`docs/ux-research/01-platform-constraint-sheet.md`](docs/ux-research/01-platform-constraint-sheet.md) — deep custom layouts are not the lever on the media-browser path; focus on **data quality, shallow cognitive load, and stable session state**.
- **Where code ships:** Session and metadata work belongs in **`Power-Ampache-2/`** (Media3), not in the sandbox `app/.../car/` sample unless deliberately prototyping templates.

**Handoff (car now-playing):** Refine **`AutoNowPlaying.svelte`** + `FrameLabel` notes for clarity; researcher flags guardrail / distraction issues; development swarm updates **upstream Media3** metadata, artwork, and playback reporting, then verifies **DHU and at least one device/OEM** snapshot.

### Mockup site — format and information design

Agents **reviewing or editing** the **`mockup/`** Vite + Svelte app should keep **layout and hierarchy** easy to scan for stakeholders:

- **Global:** Each route has a clear **H1** and a short **lead** before long copy or embeds. **Nav** (`SiteNav.svelte`) matches page intent (**Auto design**, **UX research**, **Mockups**). Hash routes in [`mockup/src/lib/hash-routes.ts`](mockup/src/lib/hash-routes.ts) stay in sync with the UI.
- **Mockup route (`#/mockup`):** Explanatory text and **P0–P3** chips sit **beside** the preview on wide viewports; **one capped preview width** so phone and car frames feel comparable; tall phone mockups **scroll inside** the preview card (`App.svelte`). Avoid accidental full-viewport overflow.
- **Research route:** Expandable cards show **title, blurb, and source path** before inlined markdown; prose styling in **`mockup/src/lib/md-prose.css`** must keep **tables, `pre`/code, and links** readable at mobile widths.
- **Design route:** **Android Auto implementation** content is primary; **phone theme** swatches stay in the **collapsible** reference section so car vs phone is obvious on first scan.
- **Frame chrome:** `FrameLabel` uses **`surface="phone"`** (PA2 primary on the badge) vs **`surface="auto"`** (neutral badge/background) so **Auto — host media** previews are not mistaken for PA2-teal head-unit branding. This strip is **reviewer UI** only.
- **Reduced motion:** Gate decorative **CSS transitions and animations** with **`prefers-reduced-motion`** where practical (home nav, phone/auto player mockups), per [`docs/ux-research/06-accessibility-matrix.md`](docs/ux-research/06-accessibility-matrix.md).
- **Quality bar:** After substantive UI changes, run **`npm run build`** and **`npm run check`** under `mockup/`. For **GitHub Pages**, set **`VITE_BASE_PATH`** to the repo subpath so assets resolve (`mockup/vite.config.ts`). Browser mockups **illustrate** guardrails; they do **not** replace Play / DHU / device verification ([`docs/ux-research/05-design-guardrails-checklist.md`](docs/ux-research/05-design-guardrails-checklist.md)).

---

## 1. UX / UI researcher

**Role**: Compile evidence-backed research on **driving music app** interfaces, **accessibility** in automotive and mobile contexts, and **safety / distraction** guidance.

**Research plan**: See [`docs/android-auto-ux-research-plan.md`](docs/android-auto-ux-research-plan.md) for phased goals, workstreams (driver UX, safety, accessibility), methods, deliverables, and risks.

**Responsibilities**

- Survey patterns for in-car and projected music UIs: information hierarchy (now playing, queue, browse), touch vs. rotary/voice assumptions, and Android Auto / Automotive OS constraints where relevant.
- Summarize **accessibility**: large targets, contrast, motion reduction, screen reader / TalkBack considerations for companion phone UI vs. projected car UI limits.
- Collect **safety and regulatory / guideline** references: OEM HMI guidelines, academic or industry reports on cognitive load while driving, and official Android for Cars documentation on templates and restricted interactions.

**Outputs**

- Structured briefs (markdown): executive summary, findings with **citations** (title, publisher, date, URL), and **actionable design implications** for this project (bullets tied to car screens and phone app where applicable).
- Optional: comparison tables (e.g. browse depth, primary actions per screen, typography scale).
- Flag gaps or contradictions in sources instead of smoothing them over.

**Constraints**

- Prefer primary sources and recent guidelines; label speculation clearly.
- Do not recommend UI patterns that violate Android Auto template limits or obvious distraction risks.

---

## 2. UI mockup developer (Svelte + Cursor MCP + skills)

**Role**: Build **disposable or prototype** UI mockups in **Svelte** (and TypeScript where appropriate) to explore layout, flow, and visual hierarchy before or alongside Kotlin/Car App implementation.

**Car now-playing + mockup site:** For **host now-playing** redesign goals and **mockup website** layout/copy standards, follow **Shared context** → **Car (Android Auto) — now-playing redesign** and **Mockup site — format and information design**.

**Handoff package (start here):** The UX research phase and design system are **ready for mockup work**. Read in order:

1. [`docs/ux-research/08-mockup-handoff-package.md`](docs/ux-research/08-mockup-handoff-package.md) — prioritized scenarios (P0–P3), labelling rules, engineering checklist.
2. [`docs/ux-research/01-platform-constraint-sheet.md`](docs/ux-research/01-platform-constraint-sheet.md) — **PA2 vs sandbox**: production Auto UI is **host-rendered media** (Media3 path), not the sandbox **Car App Library** sample; note the **browse implementation caveat** before assuming a full library tree on the head unit.
3. [`docs/design-system/00-design-system-index.md`](docs/design-system/00-design-system-index.md) and [`docs/design-system/01-brand-and-language.md`](docs/design-system/01-brand-and-language.md) — **phone** surfaces use **PowerAmpache2Theme** (Nunito, M3 tokens); **Android Auto** chrome does **not** inherit PA2 hex colours for the media-browser path.
4. [`docs/ux-research/05-design-guardrails-checklist.md`](docs/ux-research/05-design-guardrails-checklist.md) — embed in prototypes where relevant.

Full index: [`docs/ux-research/README.md`](docs/ux-research/README.md).

**Responsibilities**

- Implement mockups using project-agreed tooling: **Svelte** for components and interactivity; **Cursor MCP** (e.g. browser, Figma) and **agent skills** as specified in the session (e.g. Svelte MCP, `svelte-typescript-frontend`, `ui-mockup-html` only when static HTML is explicitly preferred).
- **Label every frame** per `08-mockup-handoff-package.md`: **Phone — PA2 theme** vs **Auto — host media (PA2)** in the browser mockup. The **Car App Library** flow is the **Kotlin** sample under `app/.../car/` (exercise in **DHU**); do not reintroduce redundant Svelte clones of that flow unless the handoff package is explicitly revised.
- Reflect researcher output: typography, spacing, safe touch targets, and minimal steps for common tasks (play/pause, skip, simple browse). Use **design-system** spacing/type tokens for **phone** mockups; for **Auto**, show **generic** list/now-playing patterns (metadata density, short titles), not PA2-branded chrome.
- For **phone now-playing**, follow **Shared context → Phone companion — player design direction** (blurred art backdrop, smooth motion with reduced-motion awareness, gestures, standard seek bar in the mockup); keep `PhoneNowPlaying.svelte` as the living reference. **Wave-style scrub** exploration: `AutoNowPlaying.svelte`.
- When mockups target the **sandbox** `androidx.car.app` flow, stay aligned with **templates and list limits**; when they target **PA2 on Auto**, align with **media browser** constraints from Google’s docs, not custom templates.

**Outputs**

- Runnable Svelte pages or components under a dedicated folder (e.g. `mockup/` or `design-prototype/` at repo root—create if the task owner agrees).
- Short `README.md` in that folder: purpose, map to car vs phone screens, links to `08-mockup-handoff-package.md` and `docs/design-system/00-design-system-index.md`.

**Constraints**

- Match existing skill and MCP workflows in Cursor (validate Svelte code with official Svelte tooling when available).
- Do not replace production car UI in Kotlin unless explicitly asked; mockups inform implementation, they are not the shipping Android Auto surface by themselves.

---

## 3. Development and testing swarm

**Role**: **Implement and maintain code** according to **specifications and unit tests** defined by the project owner; treat tests as the contract unless a spec explicitly overrides them.

**Responsibilities**

- Read the **spec** (issue, doc, or comment thread) and the **tests** (JUnit under `app/src/test/`, Android tests under `app/src/androidTest/`) before writing production code.
- Write the **minimal Kotlin (and resources)** needed to make defined tests pass and satisfy the spec; extend tests only when required for missing edge cases agreed with the owner.
- Run **`./gradlew testDebugUnitTest`** (and instrumented tests when the task touches UI or integration) and fix failures before handing off.
- When working against the **cloned upstream repo** (subdirectory), follow its build, modules, and contribution rules; consume shared logic there via the project’s chosen integration (composite build, Git submodule, Maven artifact, etc.) and avoid duplicating integration logic in the sandbox app unless the spec says otherwise.

**Outputs**

- Focused commits or patches: production code + tests as specified.
- Brief note of what was implemented, which tests were added or updated, and any Android Auto / DHU verification performed.

**Constraints**

- Do not weaken or delete tests to green the build without explicit owner approval.
- Respect `HostValidator` and security notes in `README.md` for release-bound changes.
- Prefer small, reviewable changes; large refactors only when the spec demands them.

---

## Coordination

| Handoff | From | To |
|--------|------|-----|
| Research brief | UX/UI researcher | Mockup developer + development swarm |
| Mockup / flow | Mockup developer | Development swarm (Kotlin car UI / phone UI) |
| API / integration / PR target | Cloned repo in workspace subdirectory (e.g. `Power-Ampache-2/`) | Development swarm |

When tasks conflict (e.g. mockup suggests a pattern the car library cannot support), the **development swarm** should document the constraint and the **researcher** or **mockup** agent should revise recommendations.

---

## Updating this file

When the maintainer’s repository is cloned here, update **Shared context** with:

- **Remote URL** and **local directory name** (the clone path under this sandbox). If using submodules, note theme repo path (e.g. `Power-Ampache-2/PowerAmpache2Theme/`).
- How the sandbox sample app relates to the clone (independent, or linked build)—if at all.

Keep all three agent types aligned on which tree is PR-bound vs sandbox-only.

**Note:** `Power-Ampache-2/` may be gitignored in this sandbox; clones for research/theme extraction are still valid local paths for agents.

---

## Learned User Preferences

- When executing work from an attached plan, treat the plan file as read-only unless the user explicitly asks to revise that document (e.g. do not edit `docs/android-auto-ux-research-plan.md` during implementation passes).
- If todos for a plan already exist in the session, advance those items in order instead of creating a parallel duplicate list.
- Ground phone and companion UI mockups in **PowerAmpache2Theme** and related authoritative theme sources (theme module or sibling repos the maintainer references), not one-off invented palettes.

## Learned Workspace Facts

- The `mockup/` Vite + Svelte app is for browser review only; **Desktop Head Unit** shows Android Auto from an installed app on a device or emulator, not from `npm run dev` URLs. Production builds for Pages set `VITE_BASE_PATH` so asset URLs work under a repo subpath (`mockup/vite.config.ts`).
- Android Gradle tasks need a valid Android SDK via `sdk.dir` in `local.properties` (see `local.properties.example`); without it, `assembleDebug` and similar commands fail with SDK location errors.
