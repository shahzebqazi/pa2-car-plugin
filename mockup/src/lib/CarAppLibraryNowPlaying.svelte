<script lang="ts">
  import FrameLabel from './FrameLabel.svelte'
</script>

<div class="cal-shell">
  <FrameLabel
    label="Sandbox — Car App Library"
    note="Svelte wireframe for template-style now playing (androidx.car.app sample). Wave-style scrub exploration lives on Auto — host media (PA2) mockup, not here. Compare with dhu-now-playing.png in public/dhu/ when validating against DHU."
  />
  <div class="cal-screen">
    <header class="cal-top">
      <button type="button" class="cal-back" aria-label="Back">‹</button>
      <span class="cal-title">Now playing</span>
      <button type="button" class="cal-more" aria-label="More">⋮</button>
    </header>
    <div class="cal-np">
      <div class="cal-art" role="img" aria-label="Album artwork"></div>
      <div class="cal-meta">
        <p class="cal-track">Midnight City</p>
        <p class="cal-artist">M83 · Hurry Up, We're Dreaming</p>
      </div>
      <div class="cal-progress" aria-hidden="true">
        <span class="cal-time">1:24</span>
        <div class="cal-bar">
          <div class="cal-fill"></div>
        </div>
        <span class="cal-time">4:03</span>
      </div>
      <div class="cal-controls">
        <button type="button" class="cal-btn cal-aux" aria-label="Shuffle">⇄</button>
        <button type="button" class="cal-btn" aria-label="Previous">|◀</button>
        <button type="button" class="cal-btn cal-play" aria-label="Pause">❚❚</button>
        <button type="button" class="cal-btn" aria-label="Next">▶|</button>
        <button type="button" class="cal-btn cal-aux" aria-label="Repeat">↻</button>
      </div>
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
    width: 100%;
    aspect-ratio: var(--car-aspect-ratio, 16 / 9);
    display: flex;
    flex-direction: column;
    background: linear-gradient(180deg, #1a1a1a 0%, #111 100%);
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

  .cal-back,
  .cal-more {
    border: none;
    background: transparent;
    color: #8ab4f8;
    font-size: 1.5rem;
    line-height: 1;
    border-radius: 8px;
    min-width: 44px;
    min-height: 44px;
    font-family: system-ui, sans-serif;
  }

  .cal-more {
    color: #aaa;
    font-size: 1.1rem;
  }

  .cal-back:focus-visible,
  .cal-more:focus-visible {
    outline: 2px solid #fff;
    outline-offset: 2px;
  }

  .cal-title {
    text-align: center;
    font-family: system-ui, sans-serif;
    font-size: clamp(0.9rem, 1.8vw, 1.05rem);
    font-weight: 600;
    color: #e8e8e8;
  }

  .cal-np {
    flex: 1;
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
    gap: clamp(12px, 2vw, 20px);
    padding: 16px 24px 28px;
    min-height: 0;
  }

  .cal-art {
    width: clamp(120px, 22vw, 200px);
    aspect-ratio: 1;
    border-radius: 12px;
    background: linear-gradient(145deg, #3d3d3d, #252525);
    border: 1px solid #444;
    flex-shrink: 0;
  }

  .cal-meta {
    text-align: center;
    max-width: 90%;
  }

  .cal-track {
    margin: 0;
    font-family: system-ui, sans-serif;
    font-size: clamp(1rem, 2.2vw, 1.35rem);
    font-weight: 600;
    color: #fff;
    white-space: nowrap;
    overflow: hidden;
    text-overflow: ellipsis;
  }

  .cal-artist {
    margin: 6px 0 0;
    font-family: system-ui, sans-serif;
    font-size: clamp(0.8rem, 1.5vw, 0.95rem);
    color: #aaa;
    white-space: nowrap;
    overflow: hidden;
    text-overflow: ellipsis;
  }

  .cal-progress {
    display: flex;
    align-items: center;
    gap: 12px;
    width: 100%;
    max-width: 420px;
    padding: 0 8px;
  }

  .cal-time {
    font-family: system-ui, sans-serif;
    font-size: 0.75rem;
    font-variant-numeric: tabular-nums;
    color: #888;
    flex-shrink: 0;
  }

  .cal-bar {
    flex: 1;
    height: 4px;
    border-radius: 2px;
    background: #3a3a3a;
    overflow: hidden;
  }

  .cal-fill {
    width: 32%;
    height: 100%;
    background: #8ab4f8;
    border-radius: 2px;
  }

  .cal-controls {
    display: flex;
    align-items: center;
    justify-content: center;
    gap: clamp(8px, 2vw, 20px);
    flex-wrap: wrap;
  }

  .cal-btn {
    border: none;
    border-radius: 50%;
    background: #2e2e2e;
    color: #ddd;
    font-size: 0.85rem;
    width: 44px;
    height: 44px;
    display: grid;
    place-items: center;
    font-family: system-ui, sans-serif;
  }

  .cal-btn.cal-aux {
    font-size: 0.95rem;
    color: #888;
  }

  .cal-btn.cal-play {
    width: 56px;
    height: 56px;
    font-size: 1.35rem;
    background: #e0e0e0;
    color: #111;
  }

  .cal-btn:focus-visible {
    outline: 2px solid #fff;
    outline-offset: 2px;
  }
</style>
