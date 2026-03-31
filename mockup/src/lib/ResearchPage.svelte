<script lang="ts">
  import SiteNav from './SiteNav.svelte'
  import type { AppRoute } from './hash-routes'

  interface Props {
    route: AppRoute
  }

  let { route }: Props = $props()

  const researchHighlights: {
    title: string
    blurb: string
    docPath: string
  }[] = [
    {
      title: 'Executive summary',
      blurb:
        'Evidence-oriented baseline for driver-safe music on Android Auto: PA2 uses Media3 MediaSessionService; play/pause, skip, and resume are the critical low-load tasks; phone UI uses PowerAmpache2Theme while Auto chrome is host-rendered.',
      docPath: 'docs/ux-research/00-executive-summary.md',
    },
    {
      title: 'Platform & synthesis',
      blurb:
        'Sandbox Car App Library prototypes must be labelled separately from shipping PA2. Research favors progressive disclosure: shallow car browse and recents/playlists first; deep Ampache trees primarily on phone, with voice for specific requests.',
      docPath: 'docs/ux-research/07-research-synthesis.md',
    },
    {
      title: 'Tasks & patterns',
      blurb:
        'Task analysis (T1–T3) and a pattern table map mainstream music-on-Auto behaviour to PA2 features so engineering and QA focus on what drivers actually do behind the wheel.',
      docPath: 'docs/ux-research/02-task-analysis-and-flows.md',
    },
    {
      title: 'Safety, a11y & handoff',
      blurb:
        'Google Play car quality and distraction safeguards are the binding gates; the accessibility matrix separates phone TalkBack from host-owned Auto semantics. Mockup priorities live in the handoff package.',
      docPath: 'docs/ux-research/08-mockup-handoff-package.md',
    },
  ]
</script>

<div class="page">
  <header class="page-header">
    <SiteNav {route} />
    <h1 class="page-title">UX research · docs showcase</h1>
    <p class="page-lead">
      Summaries from the Android Auto + PA2 research phase. Open files from the
      <strong>repository root</strong> in your editor (paths below are relative to
      <code>android-auto/</code>).
    </p>
  </header>

  <main class="page-main">
    <ul class="cards">
      {#each researchHighlights as h}
        <li class="card">
          <h2 class="card-title">{h.title}</h2>
          <p class="card-blurb">{h.blurb}</p>
          <p class="card-path"><code>{h.docPath}</code></p>
        </li>
      {/each}
    </ul>
    <p class="doc-hint">
      Full index: <code>docs/ux-research/README.md</code> · Plan:
      <code>docs/android-auto-ux-research-plan.md</code> ·
      <a
        class="ext"
        href="https://developer.android.com/training/cars/media"
        rel="noreferrer"
        target="_blank">Create audio media apps (Android for Cars)</a
      >
    </p>
  </main>
</div>

<style>
  .page {
    min-height: 100svh;
    padding: 20px 20px 48px;
    max-width: 720px;
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

  .page-lead strong {
    color: var(--pa2-on-surface);
  }

  .page-lead code {
    font-size: 0.78rem;
    color: var(--pa2-on-surface);
  }

  .cards {
    list-style: none;
    margin: 0;
    padding: 0;
    display: grid;
    gap: 14px;
  }

  .card {
    padding: 18px 20px;
    border-radius: 12px;
    border: 1px solid var(--mock-chrome-border);
    background: var(--pa2-surface-container);
  }

  .card-title {
    margin: 0 0 8px;
    font-size: 1rem;
    font-weight: 800;
    color: var(--pa2-primary);
  }

  .card-blurb {
    margin: 0 0 10px;
    font-size: 0.86rem;
    line-height: 1.5;
    color: var(--pa2-on-surface-variant);
  }

  .card-path {
    margin: 0;
    font-size: 0.72rem;
    color: var(--pa2-on-surface);
  }

  .card-path code {
    word-break: break-all;
  }

  .doc-hint {
    margin: 24px 0 0;
    font-size: 0.78rem;
    line-height: 1.5;
    color: var(--pa2-on-surface-variant);
  }

  .doc-hint code {
    font-size: 0.72rem;
    color: var(--pa2-on-surface);
  }

  .ext {
    color: var(--pa2-tertiary);
    text-decoration: underline;
    text-underline-offset: 2px;
  }

  .ext:focus-visible {
    outline: 2px solid var(--pa2-tertiary);
    outline-offset: 2px;
  }
</style>
