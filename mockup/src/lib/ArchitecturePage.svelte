<script lang="ts">
  import { onMount } from 'svelte'
  import mermaid from 'mermaid'
  import SiteNav from './SiteNav.svelte'
  import type { AppRoute } from './hash-routes'

  const route: AppRoute = { name: 'architecture' }

  /** System / deployment view — umbrella repo, nested plugin, car host. */
  const systemDiagram = `
flowchart TB
  subgraph umbrella["android-auto (umbrella repo)"]
    mockup["mockup/ — Svelte (this site)"]
    docs["docs/ + AGENTS.md"]
    agents["android-auto-agents/ — DHU, Gradle harness"]
  end

  subgraph nested["PowerAmpache2PluginTemplate (nested clone; PA2_PLUGIN_GRADLE_ROOT)"]
    app["app/ — UI + Media3"]
    domain["domain/ — models, mocks, MusicFetcher"]
    data["data/ — MusicFetcherImpl, PA2DataFetchService"]
    theme["PowerAmpache2Theme/ — submodule"]
  end

  subgraph device["Phone + USB"]
    gearhead["Android Auto (Gearhead)"]
    dhu["Desktop Head Unit (dev)"]
  end

  mockup -.->|"informs IA"| app
  app --> svc["PluginMediaLibraryService"]
  svc --> cat["MediaLibraryCatalog"]
  svc --> fact["MediaItemFactory"]
  cat --> domain
  data --> domain
  app --> theme
  gearhead -->|"MediaBrowser / Media3 session"| svc
  dhu --> gearhead
`

  /** UML-style class view — main plugin car stack (app module). */
  const classDiagram = `
classDiagram
  direction TB
  class MainActivity {
    +setContent Compose
  }
  class PluginHomeScreen {
    +browse MediaLibraryCatalog
  }
  class PluginMediaLibraryService {
    +MediaLibrarySession
    +ExoPlayer
    +onGetLibraryRoot()
    +onGetChildren()
  }
  class MediaLibraryCatalog {
    <<object>>
    +childrenFor(parentId)
    +browseLabels
  }
  class MediaItemFactory {
    <<object>>
    +fromCatalogEntry()
    +rootMediaItem()
  }
  class PA2DataFetchService {
    +Messenger IPC
  }

  MainActivity --> PluginHomeScreen : hosts
  PluginMediaLibraryService --> MediaLibraryCatalog : mock tree
  PluginMediaLibraryService --> MediaItemFactory : MediaItems
  MediaItemFactory ..> MediaLibraryCatalog : ids + mocks
  note for PluginMediaLibraryService "Host (Gearhead) binds here for Android Auto"
`

  let systemEl: HTMLDivElement | undefined
  let classDiagramEl: HTMLDivElement | undefined

  onMount(async () => {
    mermaid.initialize({
      startOnLoad: false,
      theme: 'dark',
      securityLevel: 'strict',
      fontFamily: 'ui-sans-serif, system-ui, sans-serif',
    })
    const id1 = 'mmd-sys-' + Math.random().toString(36).slice(2)
    const id2 = 'mmd-cls-' + Math.random().toString(36).slice(2)
    if (systemEl) {
      const { svg } = await mermaid.render(id1, systemDiagram)
      systemEl.innerHTML = svg
    }
    if (classDiagramEl) {
      const { svg } = await mermaid.render(id2, classDiagram)
      classDiagramEl.innerHTML = svg
    }
  })
</script>

<div class="arch-page">
  <header class="arch-top">
    <SiteNav {route} />
  </header>

  <main class="arch-main">
    <h1 class="arch-title">Architecture (current stack)</h1>
    <p class="arch-lead">
      Umbrella repo <strong>android-auto</strong> ships docs, agent scripts, and this mockup. The car-capable APK is built
      from a nested <strong>PowerAmpache2PluginTemplate</strong> clone (see <code>AGENTS.md</code>). The host (Android Auto)
      renders browse and now playing; the plugin supplies <strong>Media3</strong> session + browsable <code>MediaItem</code>
      tree from domain mocks.
    </p>

    <section class="arch-section" aria-labelledby="sys-h">
      <h2 id="sys-h">System context</h2>
      <p class="arch-caption">Repositories, Gradle modules, and how Gearhead / DHU attach to the media service.</p>
      <div class="mermaid-wrap" bind:this={systemEl} role="img" aria-label="System context diagram"></div>
    </section>

    <section class="arch-section" aria-labelledby="uml-h">
      <h2 id="uml-h">Plugin app — UML (simplified)</h2>
      <p class="arch-caption">
        Primary types in <code>app/</code> for phone shell + <code>PluginMediaLibraryService</code> for Android Auto. IPC
        via <code>PA2DataFetchService</code> when the host app feeds catalog data (product scope).
      </p>
      <div class="mermaid-wrap" bind:this={classDiagramEl} role="img" aria-label="UML class diagram"></div>
    </section>
  </main>
</div>

<style>
  .arch-page {
    min-height: 100svh;
    max-width: 960px;
    margin: 0 auto;
    padding: 16px 18px 48px;
    box-sizing: border-box;
  }

  .arch-top {
    margin-bottom: 20px;
    padding-bottom: 14px;
    border-bottom: 1px solid var(--mock-chrome-border);
  }

  .arch-title {
    margin: 0 0 12px;
    font-size: 1.35rem;
    font-weight: 700;
    color: var(--pa2-on-surface);
  }

  .arch-lead {
    margin: 0 0 28px;
    font-size: 0.88rem;
    line-height: 1.55;
    color: var(--pa2-on-surface-variant);
  }

  .arch-lead strong {
    color: var(--pa2-primary);
    font-weight: 600;
  }

  .arch-lead code {
    font-size: 0.78rem;
    color: var(--pa2-on-surface-variant);
  }

  .arch-section {
    margin-bottom: 36px;
  }

  .arch-section h2 {
    margin: 0 0 8px;
    font-size: 1.05rem;
    font-weight: 700;
    color: var(--pa2-on-surface);
  }

  .arch-caption {
    margin: 0 0 14px;
    font-size: 0.8rem;
    line-height: 1.45;
    color: var(--pa2-on-surface-variant);
  }

  .arch-caption code {
    font-size: 0.72rem;
  }

  .mermaid-wrap {
    overflow-x: auto;
    padding: 16px 12px;
    border-radius: 12px;
    border: 1px solid var(--mock-chrome-border);
    background: var(--pa2-surface-container);
  }

  .mermaid-wrap :global(svg) {
    max-width: 100%;
    height: auto;
  }
</style>
