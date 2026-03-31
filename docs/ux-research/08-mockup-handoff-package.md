# Mockup and engineering handoff package

**For:** UI mockup developer (Svelte or other), Kotlin / Media3 engineers.  
**Prerequisites:** Read [01-platform-constraint-sheet.md](01-platform-constraint-sheet.md) before drawing **any** Android Auto screen (note the **browse implementation caveat**).

**Upstream verification:** Scenarios **P1–P2** assume a **browsable `MediaItem` tree** on the head unit as described in Google’s [media for cars](https://developer.android.com/training/cars/media) docs. Confirm in **`Power-Ampache-2`** that `MediaLibraryService` / `MediaLibrarySession` (or equivalent) is implemented before mockups are treated as **shipping** Auto UI.

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

## 3. Labelling rules for mockups

| Label | When to use |
|-------|-------------|
| **Phone — PA2 theme** | Uses colors/type from [../design-system/01-brand-and-language.md](../design-system/01-brand-and-language.md) |
| **Auto — host media (PA2)** | Generic list/now-playing **pattern**; no custom PA2 chrome |
| **Sandbox — Car App Library** | Only for `android-auto` sample; **not** PA2 shipping |

## 4. Deliverables expected from mockups

- Wireframes or hi-fi for **P0–P1** at minimum.
- **Mermaid** or numbered flows matching [02-task-analysis-and-flows.md](02-task-analysis-and-flows.md).
- Explicit **deviation log** if a screen cannot be built under **media browser** constraints.

## 5. Engineering checklist (Kotlin / Media3)

- [ ] `MediaItem` hierarchy respects guardrail **depth** goals.
- [ ] `MediaSession` callbacks complete for **transport** controls.
- [ ] **Artwork** size and aspect per platform guidance.
- [ ] **Error** reporting hooked per [cars/media/errors](https://developer.android.com/training/cars/media/errors).
- [ ] DHU smoke test on **browse + play + voice** (where available).

## 6. Open questions to resolve with maintainer

See [07-research-synthesis.md](07-research-synthesis.md) §7; track decisions here:

| Question | Decision | Date |
|----------|----------|------|
| Offline badge on Auto | TBD | — |
| Multi-account in car | TBD | — |
| Lyrics policy | TBD | — |
