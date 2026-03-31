# Android Auto: mockups and implementation checklist

**Who this is for:** Engineers shipping Media3 on Power Ampache 2, and anyone building **browser mockups** (for example the Svelte app in this repo).

**Before you draw or spec car screens:** Read [01-platform-constraint-sheet.md](01-platform-constraint-sheet.md), especially the **browse implementation caveat**. On Android Auto you do not theme the head unit like the phone app; you supply **content and session behaviour** (tree, metadata, artwork, errors, voice).

**Verify in upstream code:** Scenarios **P1–P2** assume a **browsable `MediaItem` tree** on the head unit, as described in Google’s [Create audio media apps](https://developer.android.com/training/cars/media) guide. Confirm in **Power-Ampache-2** that library browse (for example `MediaLibraryService` / `MediaLibrarySession` or equivalent callbacks) matches product intent before treating mockups as **final** car UI.

## Fidelity, labelling, and compliance limits

- **Illustration vs proof:** Mockups communicate **information hierarchy**, **copy length**, and **scenario coverage** (P0–P3). They do **not** satisfy [Android app quality for cars](https://developer.android.com/docs/quality-guidelines/car-app-quality) or [distraction safeguards](https://developer.android.com/training/cars/media/distraction-safeguards) by themselves — verification stays **Kotlin / Media3**, **DHU**, and **devices**.
- **Car frames:** Styled as **host-like** neutral chrome. In `mockup/`, **`FrameLabel`** uses **`surface="auto"`** on Auto frames so the **preview badge** is not PA2 teal (avoids implying branded head units).
- **Motion:** Phone and Auto player mockups respect **`prefers-reduced-motion`** for decorative animation where implemented; site chrome should follow the same pattern when adding transitions.
- **QA before handoff:** From `mockup/`, run **`npm run check`** and **`npm run build`** after substantive UI changes (`AGENTS.md`).

## 1. Prioritized scenarios (build in this order)

| Priority | Scenario | Success criteria |
|----------|----------|------------------|
| P0 | **Now playing** — play/pause, next/prev, scrub (if host allows) | One-glance understanding; matches session metadata |
| P0 | **Resume** last playback | ≤1 browse step from root |
| P1 | **Root browse** — playlists + recents | ≤2 steps to start playback for happy path |
| P1 | **Voice play** (“Play playlist X”) | Engine handles `MediaSession` voice paths |
| P2 | **Artist → album → track** | Works but **not** default driving path |
| P2 | **Error** — offline / server unreachable | Short copy; recovery without deep menu |
| P3 | **Queue** view | Read-mostly; minimal edit |

## 2. Guardrails to embed in prototypes

Copy from [05-design-guardrails-checklist.md](05-design-guardrails-checklist.md): especially **G3–G7**, **G10**.

## 3. Labels for mockup frames

Use these consistently so reviewers know which stack they are looking at.

| Label | When to use |
|-------|-------------|
| **Phone — PA2 theme** | Handheld UI using colors and type from [../design-system/01-brand-and-language.md](../design-system/01-brand-and-language.md) |
| **Auto — host media (PA2)** | **Pattern only** for host-rendered lists and now playing; not PA2-branded chrome |
| **Sandbox — Car App Library** | Kotlin sample under `app/` in this repo only; **not** PA2 production |

## 4. What mockups should include

- Wireframes or high-fidelity frames for **P0–P1** at minimum.
- **Mermaid** or numbered flows that match [02-task-analysis-and-flows.md](02-task-analysis-and-flows.md).
- A short **note where the design diverges** from media-browser constraints, if it does.

## 5. Engineering checklist (Kotlin / Media3)

- [ ] `MediaItem` hierarchy respects guardrail **depth** goals.
- [ ] `MediaSession` callbacks complete for **transport** controls.
- [ ] **Artwork** size and aspect per platform guidance.
- [ ] **Error** reporting hooked per [cars/media/errors](https://developer.android.com/training/cars/media/errors).
- [ ] DHU smoke test on **browse + play + voice** (where available).

## 6. Open questions (track with the maintainer)

See [07-research-synthesis.md](07-research-synthesis.md) (section on open questions). Track decisions here:

| Question | Decision | Date |
|----------|----------|------|
| Offline badge on Auto | TBD | — |
| Multi-account in car | TBD | — |
| Lyrics policy | TBD | — |
