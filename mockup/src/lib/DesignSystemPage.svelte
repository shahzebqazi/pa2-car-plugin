<script lang="ts">
  import SiteNav from './SiteNav.svelte'
  import type { AppRoute } from './hash-routes'

  interface Props {
    route: AppRoute
  }

  let { route }: Props = $props()

  const swatches: { name: string; var: string }[] = [
    { name: 'Primary', var: '--pa2-primary' },
    { name: 'On primary', var: '--pa2-on-primary' },
    { name: 'Surface', var: '--pa2-surface' },
    { name: 'Background', var: '--pa2-background' },
    { name: 'Surface container', var: '--pa2-surface-container' },
    { name: 'On surface', var: '--pa2-on-surface' },
    { name: 'On surface variant', var: '--pa2-on-surface-variant' },
    { name: 'Tertiary', var: '--pa2-tertiary' },
    { name: 'Error', var: '--pa2-error' },
    { name: 'Outline', var: '--pa2-outline' },
  ]
</script>

<div class="page">
  <header class="page-header">
    <SiteNav {route} />
    <h1 class="page-title">Design system · phone (PowerAmpache2Theme)</h1>
    <p class="page-lead">
      Android Auto media UI does not use PA2 hex or Nunito — the head unit paints chrome. Phone
      surfaces use Material 3 roles below (see
      <code>docs/design-system/01-brand-and-language.md</code>).
    </p>
  </header>

  <main class="page-main">
    <section class="block" aria-labelledby="type-heading">
      <h2 id="type-heading" class="sr-only">Typography</h2>
      <div class="type-sample">
        <h3 class="type-label">Typography</h3>
        <p class="type-display">Nunito — display rhythm</p>
        <p class="type-body">
          Variable font from <code>public/fonts/</code> (OFL). Material 3 slots in the theme module
          map to Nunito for labels through display sizes.
        </p>
      </div>
    </section>

    <section class="block" aria-labelledby="swatch-heading">
      <h2 id="swatch-heading" class="h2">Color tokens (dark baseline)</h2>
      <ul class="swatches">
        {#each swatches as s}
          <li class="swatch">
            <span class="chip" style:background="var({s.var})"></span>
            <span class="swatch-name">{s.name}</span>
            <span class="swatch-var">{s.var}</span>
          </li>
        {/each}
      </ul>
      <p class="doc-hint">
        Index: <code>docs/design-system/00-design-system-index.md</code> · Inventory:
        <code>docs/design-system/appendix-pa2-theme-inventory.md</code>
      </p>
    </section>
  </main>
</div>

<style>
  .sr-only {
    position: absolute;
    width: 1px;
    height: 1px;
    padding: 0;
    margin: -1px;
    overflow: hidden;
    clip: rect(0, 0, 0, 0);
    white-space: nowrap;
    border: 0;
  }

  .page {
    min-height: 100svh;
    padding: 20px 20px 48px;
    max-width: 960px;
    margin: 0 auto;
  }

  .page-header {
    margin-bottom: 28px;
    padding-bottom: 20px;
    border-bottom: 1px solid var(--mock-chrome-border);
  }

  .page-title {
    margin: 16px 0 10px;
    font-size: clamp(1.25rem, 3vw, 1.5rem);
    font-weight: 800;
    color: var(--pa2-on-surface);
    line-height: 1.25;
  }

  .page-lead {
    margin: 0;
    font-size: 0.88rem;
    line-height: 1.5;
    color: var(--pa2-on-surface-variant);
  }

  .page-lead code {
    font-size: 0.78rem;
    color: var(--pa2-on-surface);
  }

  .page-main {
    display: flex;
    flex-direction: column;
    gap: 32px;
  }

  .block .h2 {
    margin: 0 0 12px;
    font-size: 1rem;
    font-weight: 800;
    color: var(--pa2-on-surface);
  }

  .type-sample {
    padding: 20px;
    border-radius: 12px;
    border: 1px solid var(--mock-chrome-border);
    background: var(--pa2-surface-container);
  }

  .type-label {
    margin: 0 0 8px;
    font-size: 0.75rem;
    font-weight: 700;
    text-transform: uppercase;
    letter-spacing: 0.04em;
    color: var(--pa2-primary);
  }

  .type-display {
    margin: 0 0 8px;
    font-size: 1.35rem;
    font-weight: 800;
    color: var(--pa2-on-surface);
  }

  .type-body {
    margin: 0;
    font-size: 0.85rem;
    line-height: 1.5;
    color: var(--pa2-on-surface-variant);
  }

  .type-body code {
    font-size: 0.78rem;
  }

  .swatches {
    list-style: none;
    margin: 0;
    padding: 0;
    display: grid;
    grid-template-columns: repeat(auto-fill, minmax(140px, 1fr));
    gap: 12px;
  }

  .swatch {
    display: flex;
    flex-direction: column;
    gap: 6px;
    padding: 10px;
    border-radius: 10px;
    border: 1px solid var(--mock-chrome-border);
    background: var(--mock-chrome-bg);
  }

  .swatch .chip {
    width: 100%;
    height: 36px;
    border-radius: 6px;
    border: 1px solid rgba(255, 255, 255, 0.12);
  }

  .swatch-name {
    font-size: 0.78rem;
    font-weight: 700;
    color: var(--pa2-on-surface);
  }

  .swatch-var {
    font-size: 0.65rem;
    color: var(--pa2-on-surface-variant);
    font-family: ui-monospace, monospace;
  }

  .doc-hint {
    margin: 20px 0 0;
    font-size: 0.78rem;
    line-height: 1.5;
    color: var(--pa2-on-surface-variant);
  }

  .doc-hint code {
    font-size: 0.72rem;
    color: var(--pa2-on-surface);
  }
</style>
