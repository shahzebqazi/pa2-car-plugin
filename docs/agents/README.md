# Agent instruction shards (`docs/agents/`)

Long-form content that used to live only in the repo-root **`AGENTS.md`** is split here so the root file stays scannable. **Read `AGENTS.md` first** for boundaries and maintenance rules; open these when your task needs depth.

| File | Contents |
|------|----------|
| [`01-prd-and-backlog.md`](01-prd-and-backlog.md) | PRD-style backlog table, workspace snapshot (update when behaviour changes). |
| [`02-dhu-and-car-testing.md`](02-dhu-and-car-testing.md) | Desktop Head Unit (USB), what to validate where, troubleshooting. |
| [`03-mockups-and-design.md`](03-mockups-and-design.md) | Mockup rules: **Power Ampache 2 = phone host**, plugin = Auto slice; car vs phone labelling. |
| [`04-roles-detailed.md`](04-roles-detailed.md) | UX researcher, mockup developer, development swarm (expanded). |
| [`05-integrity-and-tests.md`](05-integrity-and-tests.md) | Truth hierarchy, what counts as proof, coroutine tests, stubs. |
| [`06-plugin-template-hotspots.md`](06-plugin-template-hotspots.md) | Gradle roots, credentials, typical Kotlin entry points (refresh after upstream pulls). |
| [`07-handoff-plugin-ui-dhu.md`](07-handoff-plugin-ui-dhu.md) | Handoff for an agent with SDK + DHU: plugin Auto UI (Media3), specs, commands, boundaries. |
| [`08-portfolio-and-pr-policy.md`](08-portfolio-and-pr-policy.md) | Umbrella vs plugin fork, icefields PR scope, agent rules (no `data`/`domain` without scope), PA2-Theme read-only, salvage/cleanup notes. |

When you **add** a new shard, link it from this README and from **`AGENTS.md` → Document map**.
