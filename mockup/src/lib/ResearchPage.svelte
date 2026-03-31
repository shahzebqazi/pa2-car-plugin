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
        'Short read on what matters in the car: play, pause, skip, and getting back to what you were listening to. Phone UI follows the PA2 theme; Android Auto draws its own chrome around your media session.',
      docPath: 'docs/ux-research/00-executive-summary.md',
    },
    {
      title: 'Platform & synthesis',
      blurb:
        'How this sandbox sample differs from real Power Ampache 2, and why the car should stay shallow (recents, playlists first) while heavier browsing stays on the phone or voice.',
      docPath: 'docs/ux-research/07-research-synthesis.md',
    },
    {
      title: 'Tasks & patterns',
      blurb:
        'Walkthrough of common tasks and which screens they hit, so build and test time goes to behaviour drivers actually use.',
      docPath: 'docs/ux-research/02-task-analysis-and-flows.md',
    },
    {
      title: 'Safety, accessibility, mockups',
      blurb:
        'Play policy, distraction constraints, and how a11y differs between phone and head unit. The mockup checklist (P0–P3) lives here.',
      docPath: 'docs/ux-research/08-mockup-handoff-package.md',
    },
  ]
</script>

<div class="page">
  <header class="page-header">
    <SiteNav {route} />
    <h1 class="page-title">UX research</h1>
    <p class="page-lead">
      Pointers into the markdown in this repo. Paths are from the <strong>project root</strong> (<code>android-auto/</code>).
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
