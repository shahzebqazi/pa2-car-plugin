# Agent instructions — Android Auto (Power Ampache 2)

How specialized agents work in this workspace. **Deep sections** live under [`docs/agents/`](docs/agents/README.md) so this file stays maintainable.

---

## Maintaining this document (compression and sharding)

**Triggers — do one of the following when:**

- **`AGENTS.md` exceeds ~350–400 lines**, or
- The same guidance is **copy-pasted** in multiple places, or
- A topic (**DHU**, **roles**, **PRD**) needs **heavy edits** without bloating the root file.

**Allowed strategies (pick one per change set; update links when done):**

1. **Shard** — Move a topic to **`docs/agents/<nn>-<topic>.md`**, keep a **short summary + link** here, and add the file to [`docs/agents/README.md`](docs/agents/README.md).
2. **Compress** — Replace a long subsection with **bullet constraints** and a **“see shard”** link; do not delete unique requirements.
3. **Optional one-pager** — If coordinators want a **minimal** entry, add **`docs/agents/AGENTS-compact.md`** (or similar) as a **checklist-only** view; **`AGENTS.md` remains normative** unless humans demote it.

**Rules:** After sharding, **do not** leave contradictory copies — one normative home per topic. **PR backlog + workspace snapshot** normatively live in [`docs/agents/01-prd-and-backlog.md`](docs/agents/01-prd-and-backlog.md).

---

## Tech-lead reset (read before coding)

- **Portfolio vs plugin:** Umbrella repo (**`mockup/`**, **`docs/`**, **`android-auto-agents/`**) vs **nested plugin fork** — remotes, what ships to **icefields**, agent rules (no **`data`/`domain`** without explicit scope; GitHub comments require human yes). See [`docs/agents/08-portfolio-and-pr-policy.md`](docs/agents/08-portfolio-and-pr-policy.md).
- **`PowerAmpache2PluginTemplate/`** — **Your fork** (**`origin`**); **`upstream`** remote URL = **icefields/PowerAmpache2PluginTemplate** (sync **branches** such as **`plugin/AndroidAuto`** or **`main`** — not a path inside the repo). **`PowerAmpache2PluginTemplateOld/`** — quarantined prior experiment; **do not** treat as source of truth.
- **Mockups were wrong** when they framed the **plugin** as the **full phone app**. Rebuild **`mockup/`** (after umbrella/fork merge per **08**) so **handheld / player / library** scenarios depict **[Power-Ampache-2](https://github.com/icefields/Power-Ampache-2)**; use **plugin** framing for **Android Auto / Media3** and thin plugin-specific surfaces only. **PA2-Theme:** consume read-only; do not commit into a fresh theme tree. Details: [`docs/agents/03-mockups-and-design.md`](docs/agents/03-mockups-and-design.md).
- **Boundaries:** **Default plugin template `app/` only** unless the task **explicitly** includes **`data`/`domain`**. Prefer **`domain/.../model/mocks/`** and **test fakes** for presentation work until integration is specified.
- **Branch `plugin/AndroidAuto`:** On **your fork** (**`origin`**). Sync maintainer **`main`** via **`git fetch upstream && git merge upstream/main`** while checked out on **`plugin/AndroidAuto`** (see [`docs/agents/01-prd-and-backlog.md`](docs/agents/01-prd-and-backlog.md)).

---

## Document map

| Need | Read |
|------|------|
| PRD table, snapshot, reset context | [`docs/agents/01-prd-and-backlog.md`](docs/agents/01-prd-and-backlog.md) |
| DHU, USB, car validation | [`docs/agents/02-dhu-and-car-testing.md`](docs/agents/02-dhu-and-car-testing.md) |
| Mockup rules (PA2 phone vs plugin) | [`docs/agents/03-mockups-and-design.md`](docs/agents/03-mockups-and-design.md) |
| Roles (expanded) | [`docs/agents/04-roles-detailed.md`](docs/agents/04-roles-detailed.md) |
| Integrity, tests, proof | [`docs/agents/05-integrity-and-tests.md`](docs/agents/05-integrity-and-tests.md) |
| Template Gradle / module map | [`docs/agents/06-plugin-template-hotspots.md`](docs/agents/06-plugin-template-hotspots.md) |
| Plugin Auto UI handoff (SDK + DHU) | [`docs/agents/07-handoff-plugin-ui-dhu.md`](docs/agents/07-handoff-plugin-ui-dhu.md) |
| Portfolio / fork PR policy (umbrella vs icefields) | [`docs/agents/08-portfolio-and-pr-policy.md`](docs/agents/08-portfolio-and-pr-policy.md) |
| UX research — executive summary, platform constraints, mockup handoff | [`docs/ux-research/00-executive-summary.md`](docs/ux-research/00-executive-summary.md), [`docs/ux-research/01-platform-constraint-sheet.md`](docs/ux-research/01-platform-constraint-sheet.md), [`docs/ux-research/08-mockup-handoff-package.md`](docs/ux-research/08-mockup-handoff-package.md) |
| Workflows, roles, MCP stubs | [`android-auto-agents/README.md`](android-auto-agents/README.md) |

Cursor routing: [`.cursor/rules/android-auto-agents.mdc`](.cursor/rules/android-auto-agents.mdc).

---

## Agent swarm (short)

Use a **swarm** model: one **coordinator**, shared backlog in **`docs/agents/01-prd-and-backlog.md`**, **`android-auto-agents/`** for workflows.

| Role | Responsibility |
|------|----------------|
| **Coordinator** | Boundaries, merge targets, done criteria (tests + **DHU** for car work). |
| **UX researcher** | Evidence, guardrails — [`docs/android-auto-ux-research-plan.md`](docs/android-auto-ux-research-plan.md). |
| **Mockup developer** | **`mockup/`** — **PA2 = phone**, plugin = Auto slice ([`03-mockups-and-design.md`](docs/agents/03-mockups-and-design.md)). |
| **Development swarm** | Kotlin / tests — **mock use cases + fakes** until wired; **`app/`** default ([`04-roles-detailed.md`](docs/agents/04-roles-detailed.md)). |

### Parallel tracks

| Track | Scope | Avoid |
|-------|--------|--------|
| **Presentation** | **`mockup/`**, **`docs/`** | Changing template Kotlin without coordination. |
| **Code** | Template **`app/`**, tests | Rewriting **`mockup/`** unless fixing factual errors. |

**Swarm rules:** Single backlog source; no silent cross-repo assumptions (**upstream-only** when **Power-Ampache-2** not cloned); mockup → TDD → DHU for mockup-driven UI; cite **`gradle-plugin-template.sh`** (nested plugin) and **`npm run check`** when claiming green; see [`05-integrity-and-tests.md`](docs/agents/05-integrity-and-tests.md). **Issues/comments:** no new GitHub comments without the human’s prior yes ([`08`](docs/agents/08-portfolio-and-pr-policy.md)).

### Presentation shipping loop (standing until presentation-layer ship)

**Priority:** Start or continue **mockup** work in **`mockup/`** first (IA and screens) per [`03-mockups-and-design.md`](docs/agents/03-mockups-and-design.md) — **Power Ampache 2 = phone**, **plugin = Android Auto / Media3** slice, not a second full phone shell.

**Perpetual cadence:** Keep a **repeatable loop** until presentation-layer quality is shippable: **mockup → plugin template `app/` + tests + pure catalog helpers + Auto mock polish + small doc updates → human DHU pass**. Iterate; do not wait for a single “big bang” integration.

**DHU — human in the loop:** After meaningful **Android Auto / Media3** changes (browse, now playing chrome, errors, session wiring that affects the host), agents **must periodically prompt the maintainer to inspect in DHU** (USB Desktop Head Unit — [`02-dhu-and-car-testing.md`](docs/agents/02-dhu-and-car-testing.md), repo root **`./android-auto-agents/scripts/dhu-start.sh`** / **`… auto`**). Automated tests alone are not enough for car UX. File feedback against the existing **DHU bug-catalog** rules in this file (structured pass; no invented bugs).

**In scope for this loop**

| Area | Work |
|------|------|
| **Mockup** | Rebuild/align Svelte mockup with PA2 vs plugin framing; **`npm run check`**. |
| **Plugin `app/`** | Layout fixes; **mock/demo paths** (e.g. `useMock`, debug flags); **stubbed local playback UX** (clearly non-production); a11y/strings/theme polish. |
| **Tests** | `app/src/test` / `androidTest` with **fakes**; JVM tests for **`MediaLibraryCatalog`** and other pure logic in **`app/`**. |
| **Auto (mock-backed)** | **`PluginMediaLibraryService`**, **`MediaLibraryCatalog`**, **`MediaItemFactory`** polish **without** live **`MusicFetcher`** / IPC. |
| **Docs** | Targeted updates to **`docs/agents/`** or root **README** when behaviour or entry points change. |

**Deferred (do not block this loop)**

- Maintainer **dev PA2 APK** and **host-app** code that feeds **`PA2DataFetchService`**.
- Real **`MusicFetcher`** / **`data`/`domain`** / IPC implementation — **upstream maintainer** scope unless a task **explicitly** includes it.

**Boundary:** This loop **authorizes** ongoing **`PowerAmpache2PluginTemplate/app/`** edits that stay **presentation-only**. The parallel **Presentation** track above still means: do not rewrite **`mockup/`** for fun — only to fix IA or factual errors; coordinate if another agent owns the same screens.

---

## New agent onboarding (checklist)

| Step | Action |
|------|--------|
| **1. Scope** | Target **`PowerAmpache2PluginTemplate/app`** (primary), **`mockup/`**, **`docs/`**, or **upstream-only**. Ignore **`PowerAmpache2PluginTemplateOld/`** unless the human points you there for archaeology. |
| **2. Read** | This file + **Document map** shards for your task + [`.cursor/rules/android-auto-agents.mdc`](.cursor/rules/android-auto-agents.mdc). |
| **3. Gradle** | **Plugin Auto:** set **`PA2_PLUGIN_GRADLE_ROOT`** to nested **`PowerAmpache2PluginTemplate/`** and use **`./android-auto-agents/scripts/gradle-plugin-template.sh`** (see **`android-auto-agents/README.md`**). This umbrella repo has **no** root `./gradlew`. |
| **4. Verify** | Paste command results; **DHU** from repo root: **`./android-auto-agents/scripts/dhu-start.sh`**. |
| **5. Boundaries** | **`app/`** only by default; **no `data`/`domain`** without explicit task. |

---

## Handoff — next session

| Read first | Why |
|------------|-----|
| [`docs/agents/01-prd-and-backlog.md`](docs/agents/01-prd-and-backlog.md) | Backlog + snapshot. |
| [`docs/agents/06-plugin-template-hotspots.md`](docs/agents/06-plugin-template-hotspots.md) | Where code lives after last pull. |
| [`docs/agents/07-handoff-plugin-ui-dhu.md`](docs/agents/07-handoff-plugin-ui-dhu.md) | Plugin Android Auto / Media3 + DHU (not PA2 full-app phone scope unless assigned). |
| [`android-auto-agents/contracts/verification-checklist.md`](android-auto-agents/contracts/verification-checklist.md) | Done bar. |

**Tests:** `PA2_PLUGIN_GRADLE_ROOT=... ./android-auto-agents/scripts/gradle-plugin-template.sh :app:testDebugUnitTest` and `:app:lintDebug` (nested plugin). Mockup: `cd mockup && npm run check`.

**Optional:** `.cursor/hooks/state/continual-learning-index.json` — do not commit secrets.

---

## Shared context (minimal)

- **Root Android module:** **Removed** from this umbrella repo — use **`mockup/`** + nested **plugin** + maintainer **PA2** APK for integration and DHU.
- **Plugin template:** Separate APK for **plugin + (when implemented) Auto**; clone **`PowerAmpache2PluginTemplate/`**, not **Old**.
- **DHU:** USB script at repo root — [`02-dhu-and-car-testing.md`](docs/agents/02-dhu-and-car-testing.md).
- **Auto plugin (research baseline for agents):** The **host** renders browse and now playing; the plugin supplies **Media3** session, **browsable `MediaItem` tree**, metadata, artwork, errors, and voice hooks — not OEM pixel layout. **Phone** product is **[Power-Ampache-2](https://github.com/icefields/Power-Ampache-2)**; mockups must not treat the plugin as a full phone shell. **P0→P3** order and guardrails: [`08-mockup-handoff-package.md`](docs/ux-research/08-mockup-handoff-package.md). **Platform constraints:** [`01-platform-constraint-sheet.md`](docs/ux-research/01-platform-constraint-sheet.md).
- **Media3 + DHU skip and playlist advance:** Legacy skip/next in Android Auto / DHU come from **intersect**(session `Player.Commands` from `onConnect`, **`player.getAvailableCommands()`**). A single-`MediaItem` Exo pipeline often omits seek-to-next/previous; augment with a **`ForwardingPlayer`** (or equivalent) tied to **app queue** state so controls appear when a next/previous track exists, and handle **`STATE_ENDED`** in the playback engine to **advance the queue** for autoplay (otherwise playback stops between tracks).
- **Foreground `MediaLibraryService`:** If the system starts the service with **`startForegroundService()`**, call **`startForeground()`** immediately after **`super.onCreate()`**. Doing heavy work first (**`MediaLibrarySession`**, Hilt, **ExoPlayer** init) can exceed the FGS start window and cause **`ForegroundServiceDidNotStartInTimeException`**, breaking **MediaBrowser** / DHU or car host connection.
- **Learned preferences / facts** — fold into shards when useful; avoid duplicating long lists here (see **Maintaining this document**).

### DHU UI and bug cataloging (agents)

The **Desktop Head Unit** can connect and show host UI while **integration bugs** remain (metadata, browse tree, queue, errors, timing). **Do not assume** the absence of issues just because projection works.

When the human is testing in **DHU**, has mentioned a **DHU session**, or work touches **Android Auto / Media3** behavior:

1. **Prompt them to catalog bugs** before you refactor or “fix” speculatively — ask for a structured pass (even brief): what went wrong, which surface (browse / now playing / voice / errors), repro or frequency, and **severity** (blocker vs polish).
2. Offer to **capture** that list into [`docs/agents/01-prd-and-backlog.md`](docs/agents/01-prd-and-backlog.md) or a linked issue list **only after** they have dictated or confirmed items — do not invent bug reports.
3. Prefer **one thread** of fixes per triaged item after the catalog exists, unless they ask for a hotfix.

---

## Updating documentation

When nested clones or behaviour change: update **`docs/agents/01-prd-and-backlog.md`**, **`06-plugin-template-hotspots.md`**, and **root `README.md`** so README does not contradict manifests. Align PR targets (**PowerAmpache2PluginTemplate** vs **Power-Ampache-2**) with the active task.

---

## Rebuild prompt (for you — mockups + UI)

Use this with the team or an agent when restarting UI work:

1. **Pull template fork** — Work from **`PowerAmpache2PluginTemplate/`** on **`plugin/AndroidAuto`**; merge **`upstream/main`** when syncing **icefields**.
2. **Redraw information architecture** — **Phone / library / now playing** = **Power Ampache 2**; **Plugin** mocks = **Auto / Media3** + explicit plugin entry, not a second full phone shell.
3. **Implement behind boundaries** — **`app/`** only; drive lists and VM tests with **`domain/.../model/mocks/`** and fakes until product wires real **`MusicFetcher`** / API.
4. **TDD** — Tests in **`app/src/test`** / **`androidTest`** before expanding production UI.
5. **DHU** — After car-touched code ships on a branch that registers media, validate per [`02-dhu-and-car-testing.md`](docs/agents/02-dhu-and-car-testing.md).
6. **Standing loop** — Until presentation-layer ship, follow **§ Presentation shipping loop** (mockup first, periodic maintainer DHU inspection; defer dev APK / real **`MusicFetcher`** / IPC).
