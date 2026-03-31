# Design guardrails checklist

Use before major UX reviews, browse-tree changes, or Android Auto release candidates. Tag each item **SRC** (linked source) or **ASM** (team assumption).

| # | Guardrail | SRC / ASM | Verification |
|---|-----------|-----------|--------------|
| G1 | Meet current **Google Play — Android app quality for cars** for **media** | SRC | Pre-submission checklist |
| G2 | Implement **distraction safeguards** per media training doc | SRC | Code + DHU behaviour |
| G3 | **Play/pause** reachable within **one** deliberate action from now-playing-equivalent | ASM | DHU / device |
| G4 | **Skip** next/previous within **one** action each from same surface | ASM | DHU / device |
| G5 | Avoid **keyboard-dependent** flows for primary playback tasks while driving | ASM (aligned with distraction-safeguards **intent**; confirm wording vs doc) | Voice path tested |
| G6 | Prefer **≤2 browse actions** from root to start playback for “happy path” scenarios | ASM | Task analysis [02](02-task-analysis-and-flows.md) |
| G7 | **Truncate or ellipsize** long titles on browse rows; no multi-line blocks in motion | ASM | UI review |
| G8 | **Offline / error** states use **one short sentence** + optional retry | ASM | Copy review |
| G9 | **Queue reorder / bulk edit** not required on Auto; phone is primary | ASM | Scope doc |
| G10 | **Settings / account / server URL** changes default to **parked** or **phone** | ASM | Flow audit |
| G11 | Artwork and icons follow **media app architecture** (sizes, session) | SRC | Platform doc |
| G12 | Do not add **in-motion video** or non-media surfaces | SRC | [Cars media](https://developer.android.com/training/cars/media) |

---

## Review record

| Date | Reviewer | Notes |
|------|----------|-------|
| 2026-03-30 | Research pass (initial) | Populate SRC links to exact quality-checklist sections in next edit. |

---

## Open questions for maintainer

- Minimum **browse depth** acceptable for classical/opera users (many tracks per work)?
- **Explicit content** flags in browse on Auto — host support?
- **Multi-account**: single “car profile” vs full switching?
