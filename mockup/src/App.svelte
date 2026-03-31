<script lang="ts">
  import PhoneNowPlaying from './lib/PhoneNowPlaying.svelte'
  import PhoneBrowseRoot from './lib/PhoneBrowseRoot.svelte'
  import PhoneQueue from './lib/PhoneQueue.svelte'
  import PhoneErrorState from './lib/PhoneErrorState.svelte'
  import AutoNowPlaying from './lib/AutoNowPlaying.svelte'
  import AutoBrowseRoot from './lib/AutoBrowseRoot.svelte'
  import AutoErrorState from './lib/AutoErrorState.svelte'
  import HomePage from './lib/HomePage.svelte'
  import DesignSystemPage from './lib/DesignSystemPage.svelte'
  import ResearchPage from './lib/ResearchPage.svelte'
  import SiteNav from './lib/SiteNav.svelte'
  import { parseHash, type AppRoute } from './lib/hash-routes'
  import type { MockupFrameId } from './lib/screen-ids'

  const frames: { id: MockupFrameId; label: string }[] = [
    { id: 'phone-np', label: 'P0 · Phone · Now playing' },
    { id: 'auto-np', label: 'P0 · Car screen · Now playing' },
    { id: 'phone-browse', label: 'P1 · Phone · Library' },
    { id: 'auto-browse', label: 'P1 · Car screen · Library' },
    { id: 'phone-error', label: 'P2 · Phone · Can’t connect' },
    { id: 'auto-error', label: 'P2 · Car screen · Can’t connect' },
    { id: 'phone-queue', label: 'P3 · Phone · Up next' },
  ]

  let route = $state<AppRoute>(parseHash(typeof window !== 'undefined' ? window.location.hash : ''))

  $effect(() => {
    if (typeof window === 'undefined') return
    const sync = () => {
      route = parseHash(window.location.hash)
    }
    sync()
    window.addEventListener('hashchange', sync)
    return () => window.removeEventListener('hashchange', sync)
  })

  const carDisplayActive = $derived(
    route.name === 'mockup' &&
      (route.frame === 'auto-np' || route.frame === 'auto-browse' || route.frame === 'auto-error'),
  )

  function setMockupFrame(id: MockupFrameId) {
    window.location.hash = `#/mockup/${id}`
  }
</script>

{#if route.name === 'home'}
  <HomePage />
{:else if route.name === 'design'}
  <DesignSystemPage {route} />
{:else if route.name === 'research'}
  <ResearchPage {route} />
{:else if route.name === 'mockup'}
  <div class="app-layout" class:layout-car={carDisplayActive}>
    <aside class="nav" aria-label="Mockup list">
      <div class="nav-top">
        <SiteNav {route} />
      </div>
      <h1 class="app-title">Screen mockups</h1>
      <p class="app-lead">
        <strong>Phone</strong> uses the Power Ampache 2 look (Nunito and theme colors from the design docs).
        <strong>Car</strong> is a rough stand-in for Android Auto: the system draws most of the chrome, so these grays
        are intentional, not a bug. Priorities P0–P3 match
        <code>docs/ux-research/08-mockup-handoff-package.md</code>.
      </p>
      <nav class="chips" aria-label="Mockup frames">
        {#each frames as f}
          <button
            type="button"
            class="chip"
            class:active={route.name === 'mockup' && route.frame === f.id}
            onclick={() => setMockupFrame(f.id)}
          >
            {f.label}
          </button>
        {/each}
      </nav>
      <p class="dhu-note">
        The <strong>Desktop Head Unit</strong> runs the real Kotlin sample app from this repo (not this website).
        The hero image on the home page is an optional screenshot from that setup; see <code>mockup/README.md</code>
        if you want to refresh it.
      </p>
    </aside>

    <main class="stage" class:stage-car={carDisplayActive}>
      {#if route.frame === 'phone-np'}
        <PhoneNowPlaying />
      {:else if route.frame === 'phone-browse'}
        <PhoneBrowseRoot />
      {:else if route.frame === 'phone-queue'}
        <PhoneQueue />
      {:else if route.frame === 'phone-error'}
        <PhoneErrorState />
      {:else if route.frame === 'auto-np'}
        <AutoNowPlaying />
      {:else if route.frame === 'auto-browse'}
        <AutoBrowseRoot />
      {:else if route.frame === 'auto-error'}
        <AutoErrorState />
      {/if}
    </main>
  </div>
{/if}

<style>
  .app-layout {
    display: grid;
    grid-template-columns: minmax(260px, 320px) 1fr;
    min-height: 100svh;
    gap: 0;
  }
  .app-layout.layout-car {
    grid-template-columns: minmax(220px, 280px) 1fr;
  }
  @media (max-width: 800px) {
    .app-layout {
      grid-template-columns: 1fr;
    }
    .app-layout.layout-car {
      grid-template-columns: 1fr;
    }
  }
  .nav {
    padding: 16px 18px 24px;
    border-right: 1px solid var(--mock-chrome-border);
    background: #121413;
  }
  .nav-top {
    margin-bottom: 16px;
    padding-bottom: 14px;
    border-bottom: 1px solid var(--mock-chrome-border);
  }
  @media (max-width: 800px) {
    .nav {
      border-right: none;
      border-bottom: 1px solid var(--mock-chrome-border);
    }
  }
  .app-title {
    margin: 0 0 8px;
    font-size: 1.1rem;
    font-weight: 700;
    color: var(--pa2-on-surface);
    line-height: 1.3;
  }
  .app-lead {
    margin: 0 0 16px;
    font-size: 0.82rem;
    line-height: 1.45;
    color: var(--pa2-on-surface-variant);
  }
  .app-lead strong {
    color: var(--pa2-primary);
    font-weight: 600;
  }
  .app-lead code {
    font-size: 0.72rem;
    word-break: break-all;
    color: var(--pa2-on-surface-variant);
  }
  .chips {
    display: flex;
    flex-direction: column;
    gap: 8px;
  }
  .chip {
    text-align: left;
    padding: 10px 12px;
    border-radius: 10px;
    border: 1px solid var(--mock-chrome-border);
    background: var(--pa2-surface-container);
    color: var(--pa2-on-surface);
    font-size: 0.78rem;
    font-weight: 600;
    line-height: 1.35;
  }
  .chip.active {
    border-color: var(--pa2-primary);
    background: rgba(112, 204, 204, 0.15);
    color: var(--pa2-primary);
  }
  .chip:focus-visible {
    outline: 2px solid var(--pa2-tertiary);
    outline-offset: 2px;
  }
  .dhu-note {
    margin: 20px 0 0;
    font-size: 0.75rem;
    line-height: 1.45;
    color: var(--pa2-on-surface-variant);
  }
  .dhu-note code {
    font-size: 0.68rem;
    color: var(--pa2-on-surface-variant);
  }
  .dhu-note strong {
    color: var(--pa2-on-surface);
  }
  .stage {
    padding: 24px 20px 40px;
    display: flex;
    align-items: flex-start;
    justify-content: center;
    overflow: auto;
  }
  .stage.stage-car {
    justify-content: center;
    padding-inline: 12px;
  }
</style>
