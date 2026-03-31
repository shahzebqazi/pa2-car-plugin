<script lang="ts">
  import FrameLabel from './FrameLabel.svelte'

  const items = [
    { title: 'Favorites', sub: '12 songs' },
    { title: 'Playlists', sub: '' },
    { title: 'Albums', sub: '' },
    { title: 'Artists', sub: '' },
    { title: 'Songs', sub: '' },
  ] as const
</script>

<div class="cal-shell">
  <FrameLabel
    label="Sandbox — Car App Library"
    note="Svelte wireframe approximating androidx.car.app list/browse templates (sample Kotlin app under app/.../car/). Not shipping PA2 Auto. DHU PNGs in public/dhu/ kept for side-by-side reference with a real Desktop Head Unit capture."
  />
  <div class="cal-screen">
    <header class="cal-top">
      <button type="button" class="cal-back" aria-label="Back">‹</button>
      <span class="cal-title">Sample Car App</span>
      <span class="cal-spacer"></span>
    </header>
    <div class="cal-body">
      <p class="cal-section">Library</p>
      <ul class="cal-list" role="list">
        {#each items as row}
          <li>
            <button type="button" class="cal-row">
              <span class="cal-thumb" aria-hidden="true"></span>
              <span class="cal-text">
                <span class="cal-row-title">{row.title}</span>
                {#if row.sub}
                  <span class="cal-row-sub">{row.sub}</span>
                {/if}
              </span>
              <span class="cal-chev" aria-hidden="true">›</span>
            </button>
          </li>
        {/each}
      </ul>
    </div>
    <div class="cal-pill" aria-hidden="true">
      <span class="cal-pill-inner"></span>
    </div>
  </div>
</div>

<style>
  .cal-shell {
    width: 100%;
    max-width: var(--car-display-max-w, 1120px);
    margin: 0 auto;
    border-radius: 12px;
    overflow: hidden;
    border: 1px solid #3d3d3d;
    background: #0d0d0d;
    box-shadow: 0 16px 40px rgba(0, 0, 0, 0.55);
  }

  .cal-screen {
    position: relative;
    width: 100%;
    aspect-ratio: var(--car-aspect-ratio, 16 / 9);
    display: flex;
    flex-direction: column;
    background: linear-gradient(180deg, #1c1c1c 0%, #141414 100%);
    box-sizing: border-box;
    min-height: 0;
  }

  .cal-top {
    flex-shrink: 0;
    display: grid;
    grid-template-columns: 48px 1fr 48px;
    align-items: center;
    padding: 10px 8px 8px;
    border-bottom: 1px solid #2a2a2a;
  }

  .cal-back {
    border: none;
    background: transparent;
    color: #8ab4f8;
    font-size: 1.75rem;
    line-height: 1;
    border-radius: 8px;
    min-width: 44px;
    min-height: 44px;
    font-family: system-ui, sans-serif;
  }

  .cal-back:focus-visible {
    outline: 2px solid #fff;
    outline-offset: 2px;
  }

  .cal-title {
    text-align: center;
    font-family: system-ui, -apple-system, BlinkMacSystemFont, 'Segoe UI', sans-serif;
    font-size: clamp(0.95rem, 2vw, 1.1rem);
    font-weight: 600;
    color: #e8e8e8;
    white-space: nowrap;
    overflow: hidden;
    text-overflow: ellipsis;
  }

  .cal-spacer {
    width: 48px;
  }

  .cal-body {
    flex: 1;
    min-height: 0;
    display: flex;
    flex-direction: column;
    padding: 6px 12px 40px;
    overflow: hidden;
  }

  .cal-section {
    margin: 0 0 6px;
    padding: 4px 8px;
    font-family: system-ui, sans-serif;
    font-size: 0.65rem;
    font-weight: 600;
    letter-spacing: 0.06em;
    text-transform: uppercase;
    color: #888;
  }

  .cal-list {
    list-style: none;
    margin: 0;
    padding: 0;
    overflow-y: auto;
    flex: 1;
  }

  .cal-row {
    width: 100%;
    display: flex;
    align-items: center;
    gap: 12px;
    padding: 10px 12px;
    margin-bottom: 4px;
    border: none;
    border-radius: 10px;
    background: #262626;
    color: #fff;
    text-align: left;
    min-height: 56px;
    font-family: system-ui, sans-serif;
  }

  .cal-row:focus-visible {
    outline: 2px solid #fff;
    outline-offset: -2px;
  }

  .cal-thumb {
    width: 44px;
    height: 44px;
    border-radius: 8px;
    background: linear-gradient(135deg, #404040, #2a2a2a);
    flex-shrink: 0;
    border: 1px solid #444;
  }

  .cal-text {
    flex: 1;
    min-width: 0;
    display: flex;
    flex-direction: column;
    gap: 2px;
  }

  .cal-row-title {
    font-size: clamp(0.9rem, 1.7vw, 1.02rem);
    font-weight: 500;
    white-space: nowrap;
    overflow: hidden;
    text-overflow: ellipsis;
  }

  .cal-row-sub {
    font-size: 0.75rem;
    color: #999;
  }

  .cal-chev {
    color: #666;
    font-size: 1.2rem;
    flex-shrink: 0;
  }

  .cal-pill {
    position: absolute;
    bottom: 10px;
    left: 50%;
    transform: translateX(-50%);
    width: 120px;
    height: 5px;
    border-radius: 3px;
    background: #333;
    opacity: 0.85;
  }

  .cal-pill-inner {
    display: block;
    width: 40%;
    height: 100%;
    border-radius: 3px;
    background: #666;
    margin: 0 auto;
  }
</style>
