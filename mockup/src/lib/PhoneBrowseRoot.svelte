<script lang="ts">
  import FrameLabel from './FrameLabel.svelte'

  type Row = { title: string; sub: string; accent?: boolean }
  const rows: Row[] = [
    { title: 'Resume last session', sub: 'Smartlist · Demo server', accent: true },
    { title: 'Recently played', sub: '12 items' },
    { title: 'Playlists', sub: 'Favorites, smartlists' },
    { title: 'Albums', sub: 'Library' },
    { title: 'Artists', sub: 'Library' },
  ]
</script>

<div class="phone-shell">
  <FrameLabel
    surface="phone"
    label="Phone — PA2 theme"
    note="Library root: put resume and recents up top; keep paths to start playback short. Long titles should ellipsize, not wrap forever."
  />
  <div class="screen">
    <header class="top">
      <span class="brand">Power Ampache</span>
      <button type="button" class="text-btn" aria-label="Search">Search</button>
    </header>

    <h1 class="heading">Library</h1>

    <ul class="list" role="list">
      {#each rows as row}
        <li>
          <button type="button" class="row" class:accent={row.accent}>
            <span class="chev">›</span>
            <span class="row-text">
              <span class="row-title">{row.title}</span>
              <span class="row-sub">{row.sub}</span>
            </span>
          </button>
        </li>
      {/each}
    </ul>
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
    padding: 0 0 24px;
    min-height: 640px;
    background: var(--pa2-surface);
  }
  .top {
    display: flex;
    justify-content: space-between;
    align-items: center;
    padding: 16px 16px 8px;
  }
  .brand {
    font-weight: 700;
    font-size: 1.05rem;
    color: var(--pa2-primary);
  }
  .text-btn {
    border: none;
    background: transparent;
    color: var(--pa2-primary);
    font-weight: 600;
    font-size: 0.9rem;
    min-height: 48px;
    padding: 8px 12px;
    border-radius: 8px;
  }
  .text-btn:focus-visible {
    outline: 2px solid var(--pa2-primary);
    outline-offset: 2px;
  }
  .heading {
    margin: 0;
    padding: 8px 20px 12px;
    font-size: 1.35rem;
    font-weight: 700;
    color: var(--pa2-on-surface);
  }
  .list {
    list-style: none;
    margin: 0;
    padding: 0 12px;
  }
  .row {
    width: 100%;
    display: flex;
    align-items: flex-start;
    gap: 4px;
    text-align: left;
    padding: 12px 12px;
    margin-bottom: 4px;
    border: none;
    border-radius: 14px;
    background: var(--pa2-surface-container);
    color: inherit;
    min-height: 56px;
  }
  .row.accent {
    background: rgba(112, 204, 204, 0.12);
    border: 1px solid rgba(112, 204, 204, 0.35);
  }
  .row:focus-visible {
    outline: 2px solid var(--pa2-tertiary);
    outline-offset: 2px;
  }
  .chev {
    color: var(--pa2-on-surface-variant);
    font-size: 1.25rem;
    line-height: 1.2;
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
</style>
