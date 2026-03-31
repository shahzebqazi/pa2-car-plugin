<script lang="ts">
  import FrameLabel from './FrameLabel.svelte'

  type Q = { title: string; artist: string; current?: boolean }
  const queue: Q[] = [
    { title: 'Midnight City', artist: 'M83', current: true },
    { title: 'Reunion', artist: 'M83' },
    { title: 'Wait', artist: 'M83' },
    { title: 'Steve McQueen', artist: 'M83' },
    { title: 'Raconte-moi une histoi…', artist: 'M83' },
  ]
</script>

<div class="phone-shell">
  <FrameLabel
    label="Phone — PA2 theme"
    note="P3 queue: read-mostly; reorder primarily on phone (G9). Handle height ~45dp pattern from theme docs."
  />
  <div class="screen">
    <div class="handle-bar" aria-hidden="true">
      <span class="handle"></span>
    </div>
    <header class="top">
      <button type="button" class="icon-btn hit-48" aria-label="Close queue">↓</button>
      <span class="title">Playing next</span>
      <span class="spacer"></span>
    </header>

    <ul class="list" role="list">
      {#each queue as row, i}
        <li>
          <button type="button" class="row" class:current={row.current} aria-current={row.current ? 'true' : undefined}>
            <span class="idx">{i + 1}</span>
            <span class="row-text">
              <span class="row-title">{row.title}</span>
              <span class="row-sub">{row.artist}</span>
            </span>
            {#if row.current}
              <span class="badge">Now</span>
            {/if}
          </button>
        </li>
      {/each}
    </ul>
    <p class="foot">Bulk edit and deep reorder stay on phone; Auto queue is read-oriented.</p>
  </div>
</div>

<style>
  .phone-shell {
    width: 100%;
    max-width: 390px;
    margin: 0 auto;
    border-radius: 28px;
    overflow: hidden;
    border: 1px solid var(--pa2-outline);
    background: var(--pa2-surface);
    box-shadow: 0 24px 48px rgba(0, 0, 0, 0.45);
    font-family: 'Nunito', system-ui, sans-serif;
  }
  .screen {
    padding: 0 0 20px;
    min-height: 520px;
    background: var(--pa2-surface);
  }
  .handle-bar {
    display: flex;
    justify-content: center;
    padding: 10px 0 6px;
  }
  .handle {
    width: 36px;
    height: 5px;
    border-radius: 999px;
    background: var(--pa2-on-surface-variant);
    opacity: 0.45;
  }
  .top {
    display: grid;
    grid-template-columns: 48px 1fr 48px;
    align-items: center;
    padding: 4px 0 12px;
  }
  .title {
    text-align: center;
    font-weight: 700;
    font-size: 1rem;
    color: var(--pa2-on-surface);
  }
  .spacer {
    width: 48px;
  }
  .icon-btn {
    border: none;
    background: transparent;
    color: var(--pa2-primary);
    font-size: 1.2rem;
    line-height: 1;
    border-radius: 12px;
    display: grid;
    place-items: center;
  }
  .hit-48 {
    min-width: 48px;
    min-height: 48px;
    padding: 0;
  }
  .icon-btn:focus-visible {
    outline: 2px solid var(--pa2-primary);
    outline-offset: 2px;
  }
  .list {
    list-style: none;
    margin: 0;
    padding: 0 12px;
  }
  .row {
    width: 100%;
    display: flex;
    align-items: center;
    gap: 12px;
    text-align: left;
    padding: 12px 12px;
    margin-bottom: 4px;
    border: none;
    border-radius: 14px;
    background: var(--pa2-surface-container);
    color: inherit;
    min-height: 56px;
  }
  .row.current {
    background: rgba(112, 204, 204, 0.1);
    border: 1px solid rgba(112, 204, 204, 0.28);
  }
  .row:focus-visible {
    outline: 2px solid var(--pa2-tertiary);
    outline-offset: 2px;
  }
  .idx {
    font-size: 0.8rem;
    font-variant-numeric: tabular-nums;
    color: var(--pa2-on-surface-variant);
    width: 1.25rem;
    flex-shrink: 0;
  }
  .row-text {
    display: flex;
    flex-direction: column;
    gap: 2px;
    min-width: 0;
    flex: 1;
  }
  .row-title {
    font-weight: 600;
    font-size: 1rem;
    color: var(--pa2-on-surface);
    white-space: nowrap;
    overflow: hidden;
    text-overflow: ellipsis;
  }
  .row-sub {
    font-size: 0.82rem;
    color: var(--pa2-on-surface-variant);
    white-space: nowrap;
    overflow: hidden;
    text-overflow: ellipsis;
  }
  .badge {
    flex-shrink: 0;
    font-size: 0.65rem;
    font-weight: 700;
    letter-spacing: 0.06em;
    text-transform: uppercase;
    color: var(--pa2-on-primary);
    background: var(--pa2-primary);
    padding: 4px 8px;
    border-radius: 8px;
  }
  .foot {
    margin: 16px 20px 0;
    font-size: 0.75rem;
    line-height: 1.4;
    color: var(--pa2-on-surface-variant);
  }
</style>
