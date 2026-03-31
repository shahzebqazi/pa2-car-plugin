<script lang="ts">
  import FrameLabel from './FrameLabel.svelte'

  const items = [
    'Resume · Last playlist',
    'Recently played',
    'Playlists',
    'Albums',
    'Artists',
  ] as const
</script>

<div class="auto-shell">
  <FrameLabel
    label="Auto — host media (PA2)"
    note="Same library idea as the phone mockup, in plain car list form. Row text comes from the app; the shell owns the header and styling."
  />
  <div class="auto-screen">
    <aside class="dock" aria-label="App shortcuts (host chrome)">
      <span class="dock-dot active" title="Music"></span>
      <span class="dock-dot" title="Maps"></span>
      <span class="dock-dot" title="Phone"></span>
    </aside>
    <div class="browse">
      <p class="header">Library</p>
      <ul class="list" role="list">
        {#each items as label}
          <li>
            <button type="button" class="item">
              <span class="label">{label}</span>
              <span class="arrow" aria-hidden="true">›</span>
            </button>
          </li>
        {/each}
      </ul>
    </div>
  </div>
</div>

<style>
  .auto-shell {
    container-type: inline-size;
    width: 100%;
    max-width: var(--car-display-max-w, 1120px);
    margin: 0 auto;
    border-radius: 12px;
    overflow: hidden;
    border: 1px solid #444;
    background: #1a1a1a;
    box-shadow: 0 16px 40px rgba(0, 0, 0, 0.5);
  }
  .auto-screen {
    width: 100%;
    aspect-ratio: var(--car-aspect-ratio, 16 / 9);
    display: flex;
    flex-direction: row;
    align-items: stretch;
    background: #222;
    box-sizing: border-box;
    min-height: 0;
  }
  .dock {
    flex-shrink: 0;
    width: clamp(56px, 8vw, 72px);
    display: flex;
    flex-direction: column;
    align-items: center;
    gap: 14px;
    padding: 16px 8px;
    background: #1a1a1a;
    border-right: 1px solid #333;
  }
  .dock-dot {
    width: 40px;
    height: 40px;
    border-radius: 10px;
    background: #333;
    border: 1px solid #444;
  }
  .dock-dot.active {
    background: #4a6fa5;
    border-color: #6b8cc4;
  }
  .browse {
    flex: 1;
    min-width: 0;
    display: flex;
    flex-direction: column;
    padding: 8px 12px 12px;
    overflow: hidden;
  }
  .header {
    flex-shrink: 0;
    margin: 0;
    padding: 6px 8px 8px;
    font-family: system-ui, -apple-system, BlinkMacSystemFont, 'Segoe UI', sans-serif;
    font-size: 0.7rem;
    font-weight: 600;
    letter-spacing: 0.05em;
    text-transform: uppercase;
    color: #888;
  }
  .list {
    list-style: none;
    margin: 0;
    padding: 0 4px;
    overflow-y: auto;
    flex: 1;
    min-height: 0;
  }
  .item {
    width: 100%;
    display: flex;
    align-items: center;
    justify-content: space-between;
    gap: 12px;
    padding: 12px 14px;
    margin-bottom: 2px;
    border: none;
    border-radius: 8px;
    background: #2e2e2e;
    color: #fff;
    font-family: system-ui, -apple-system, BlinkMacSystemFont, 'Segoe UI', sans-serif;
    font-size: clamp(0.9rem, 1.8vw, 1.05rem);
    text-align: left;
    min-height: 48px;
  }
  .item:focus-visible {
    outline: 2px solid #fff;
    outline-offset: -2px;
  }
  .label {
    flex: 1;
    min-width: 0;
    white-space: nowrap;
    overflow: hidden;
    text-overflow: ellipsis;
  }
  .arrow {
    color: #888;
    font-size: 1.15rem;
    flex-shrink: 0;
  }
  @container (max-width: 520px) {
    .auto-screen {
      aspect-ratio: unset;
      min-height: 360px;
    }
  }
</style>
