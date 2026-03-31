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
  <div class="mockup-page">
    <header class="mockup-top">
      <SiteNav {route} />
    </header>

    <div class="mockup-body">
      <aside class="mockup-meta" aria-label="Mockup list">
        <h1 class="app-title">Screen mockups</h1>
        <p class="app-lead">
          <strong>Phone</strong> frames use PA2 theme (Nunito and colors from <code>docs/design-system/</code>).
          <strong>Car</strong> frames approximate <strong>host-rendered</strong> Android Auto: the head unit owns layout
          and styling; the app supplies the media tree and session. Gray chrome here is deliberate. Scenario order
          P0–P3 follows <code>docs/ux-research/08-mockup-handoff-package.md</code>.
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

      <main class="mockup-stage" aria-label="Selected mockup">
        <div class="mockup-viewport">
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
        </div>
      </main>
    </div>
  </div>
{/if}

<style>
  .mockup-page {
    min-height: 100svh;
    max-width: 1040px;
    margin: 0 auto;
    padding: 16px 18px 32px;
    box-sizing: border-box;
  }

  .mockup-top {
    margin-bottom: 18px;
    padding-bottom: 14px;
    border-bottom: 1px solid var(--mock-chrome-border);
  }

  .mockup-body {
    display: grid;
    gap: 24px 32px;
    align-items: start;
  }

  @media (max-width: 879px) {
    .mockup-stage {
      order: 1;
    }
    .mockup-meta {
      order: 2;
    }
  }

  @media (min-width: 880px) {
    .mockup-body {
      grid-template-columns: minmax(240px, 300px) 1fr;
    }
  }

  .mockup-meta {
    padding: 0;
    background: transparent;
  }

  @media (max-width: 879px) {
    .mockup-meta {
      padding-bottom: 8px;
      border-bottom: 1px solid var(--mock-chrome-border);
    }
  }

  .mockup-stage {
    display: flex;
    align-items: center;
    justify-content: center;
    min-height: min(360px, 50svh);
  }

  @media (min-width: 880px) {
    .mockup-stage {
      min-height: min(640px, calc(100svh - 120px));
      position: sticky;
      top: 16px;
    }
  }

  /* One preview width for phone and car; scroll if the phone frame is taller than the cap. */
  .mockup-viewport {
    --mockup-slot-w: min(100%, 380px);
    width: var(--mockup-slot-w);
    max-width: var(--mockup-slot-w);
    max-height: min(72svh, 520px);
    overflow: auto;
    margin-inline: auto;
    padding: 12px;
    border-radius: 16px;
    border: 1px solid var(--mock-chrome-border);
    background: var(--mock-chrome-bg);
    box-sizing: border-box;
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: flex-start;
    --car-display-max-w: 100%;
  }

  .mockup-viewport :global(.phone-shell),
  .mockup-viewport :global(.auto-shell) {
    width: 100%;
    max-width: 100% !important;
    margin-inline: auto;
    flex-shrink: 0;
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
</style>
