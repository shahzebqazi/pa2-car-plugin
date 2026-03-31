# Power Ampache 2 design system — index (phone + Android Auto)

This design system follows the structure in [99designs — What does a design system consist of?](https://99designs.com/blog/tips/design-systems/): **branding and design language**, **layout and navigation**, **components and patterns**, plus **implementation** notes.

## Goals

- Give designers and developers a **single map** from **brand tokens** to **surfaces**.
- Separate what applies to the **phone app** (Power Ampache 2 + **PowerAmpache2Theme**) from what applies to **Android Auto** (**host-rendered** media UI for PA2’s `MediaBrowserService` path).
- Stay aligned with UX research: [../ux-research/README.md](../ux-research/README.md).

## Audience

- Android / Compose engineers (PA2 + plugins)
- Mockup and marketing contributors
- Car integration engineers (browse tree, session, metadata — not pixel theme)

## Source of truth

| Layer | Location |
|-------|----------|
| **Code (theme module)** | `Power-Ampache-2/PowerAmpache2Theme/` (Git submodule in upstream repo) |
| **Code (app dimens + app colours)** | `Power-Ampache-2/app/src/main/res/values/` |
| **Inventory (this repo)** | [appendix-pa2-theme-inventory.md](appendix-pa2-theme-inventory.md) |

When the upstream theme changes, update the **appendix** and token tables in [01-brand-and-language.md](01-brand-and-language.md).

## Documents

| # | Document |
|---|----------|
| 01 | [01-brand-and-language.md](01-brand-and-language.md) — philosophy, tone, typography, color, assets |
| 02 | [02-layout-and-navigation.md](02-layout-and-navigation.md) — spacing, grids, IA, Auto vs phone |
| 03 | [03-components-and-patterns.md](03-components-and-patterns.md) — UI patterns and states |
| Apx | [appendix-pa2-theme-inventory.md](appendix-pa2-theme-inventory.md) — file map and semantic roles |

## Critical distinction: two surfaces

| Surface | Who controls visuals | PA2 theme? |
|---------|---------------------|------------|
| **Phone** | PA2 Compose + `PowerAmpache2Theme` | **Yes** — colors, Nunito, Material You |
| **Android Auto (media path)** | **Head unit / Android Auto host** | **No** — app supplies **content** (titles, art, tree) |

Do not promise PA2 **teal primary** or **Nunito** inside the **projected** media UI unless the product moves to a **custom template** path and platform allows it.

## Optional tools

- **Markdown in-repo** is the canonical **documentation** for this sandbox.
- **Figma** (or similar) may be added later; keep tokens synced from `PowerAmpache2Theme` and this folder.

## Related plugins

The upstream app queries **infoplugin**, **lyricsplugin**, **chromecastplugin** packages; their **branding** should remain **subordinate** to PA2 tokens unless a plugin ships a standalone surface. Theme extraction for this package focused on **`PowerAmpache2Theme`** (shared with those integrations when they depend on the same module).
