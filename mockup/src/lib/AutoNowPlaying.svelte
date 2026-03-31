<script lang="ts">
  import FrameLabel from './FrameLabel.svelte'
</script>

<div class="auto-shell">
  <FrameLabel
    label="Auto — host media (PA2)"
    note="Rough idea of now playing in the car: gray chrome, not PA2 brand colors. The wave behind the scrubber is experimental; real Android Auto depends on the car maker."
  />
  <div class="auto-screen">
    <div class="np">
      <div class="np-art" role="img" aria-label="Album artwork"></div>
      <div class="np-maincol">
        <div class="np-center">
          <p class="np-title">Midnight City</p>
          <p class="np-sub">M83 · Hurry Up, We're Dreaming</p>
        </div>
        <div class="np-scrub-block">
          <div
            class="np-seek hit"
            role="slider"
            aria-label="Playback position"
            aria-valuemin={0}
            aria-valuemax={243}
            aria-valuenow={84}
            aria-valuetext="1 minutes 24 seconds of 4 minutes 3 seconds"
            tabindex="0"
          >
            <div class="np-wave-viz" aria-hidden="true">
              <svg class="np-wave-svg" viewBox="0 0 400 48" preserveAspectRatio="none">
                <path
                  class="np-wave-back"
                  d="M0,28 Q25,8 50,28 T100,28 T150,20 T200,32 T250,14 T300,30 T350,18 T400,26 L400,48 L0,48 Z"
                />
                <path
                  class="np-wave-front"
                  d="M0,30 Q30,12 60,30 T120,22 T180,34 T240,16 T300,28 T360,20 T400,24 L400,48 L0,48 Z"
                />
              </svg>
              <div class="np-wave-progress" style="width: 32%"></div>
            </div>
            <div class="np-progress" aria-hidden="true">
              <span class="np-time">1:24</span>
              <div class="np-bar">
                <div class="np-fill"></div>
                <div class="np-knob" style="left: 32%"></div>
              </div>
              <span class="np-time">4:03</span>
            </div>
          </div>
        </div>
      </div>
      <div class="np-controls">
        <button type="button" class="np-btn" aria-label="Previous">|◀</button>
        <button type="button" class="np-btn np-main" aria-label="Pause">❚❚</button>
        <button type="button" class="np-btn" aria-label="Next">▶|</button>
      </div>
    </div>
  </div>
</div>

<style>
  .auto-shell {
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
    padding: clamp(12px, 2.5vw, 24px) clamp(16px, 3vw, 36px);
    background: linear-gradient(180deg, #2a2a2a 0%, #1e1e1e 100%);
    display: flex;
    align-items: center;
    box-sizing: border-box;
  }
  .np {
    display: flex;
    flex-direction: row;
    align-items: center;
    justify-content: space-between;
    gap: clamp(16px, 4vw, 40px);
    width: 100%;
    min-height: 0;
  }
  .np-art {
    flex-shrink: 0;
    width: clamp(96px, 18vw, 200px);
    aspect-ratio: 1;
    max-height: 100%;
    border-radius: 10px;
    background: linear-gradient(145deg, #3a3a3a, #252525);
    border: 1px solid #555;
  }
  .np-maincol {
    flex: 1;
    min-width: 0;
    display: flex;
    flex-direction: column;
    gap: clamp(8px, 1.5vw, 14px);
    justify-content: center;
  }
  .np-center {
    text-align: left;
  }
  .np-title {
    margin: 0;
    font-family: system-ui, -apple-system, BlinkMacSystemFont, 'Segoe UI', sans-serif;
    font-size: clamp(1.05rem, 2.6vw, 1.55rem);
    font-weight: 600;
    color: #fff;
    white-space: nowrap;
    overflow: hidden;
    text-overflow: ellipsis;
  }
  .np-sub {
    margin: 4px 0 0;
    font-family: system-ui, -apple-system, BlinkMacSystemFont, 'Segoe UI', sans-serif;
    font-size: clamp(0.8rem, 1.9vw, 1rem);
    color: #bbb;
    white-space: nowrap;
    overflow: hidden;
    text-overflow: ellipsis;
  }
  .np-scrub-block {
    max-width: 100%;
  }
  .np-seek.hit {
    display: flex;
    flex-direction: column;
    gap: 6px;
    padding: 6px 8px;
    border-radius: 10px;
    border: 1px solid rgba(255, 255, 255, 0.08);
    background: rgba(0, 0, 0, 0.2);
    cursor: pointer;
  }
  .np-seek:focus-visible {
    outline: 2px solid #fff;
    outline-offset: 2px;
  }
  .np-wave-viz {
    position: relative;
    height: clamp(32px, 5vw, 40px);
    border-radius: 6px;
    overflow: hidden;
    background: rgba(0, 0, 0, 0.35);
    border: 1px solid rgba(255, 255, 255, 0.06);
  }
  .np-wave-svg {
    display: block;
    width: 100%;
    height: 100%;
  }
  .np-wave-back {
    fill: rgba(255, 255, 255, 0.08);
  }
  .np-wave-front {
    fill: rgba(255, 255, 255, 0.18);
  }
  .np-wave-progress {
    position: absolute;
    left: 0;
    top: 0;
    bottom: 0;
    background: linear-gradient(90deg, rgba(200, 200, 200, 0.2), transparent);
    pointer-events: none;
    border-right: 2px solid #c8c8c8;
    box-sizing: border-box;
  }
  @media (prefers-reduced-motion: no-preference) {
    .np-wave-svg {
      animation: np-wave-pan 14s ease-in-out infinite alternate;
    }
  }
  @media (prefers-reduced-motion: reduce) {
    .np-wave-svg {
      animation: none;
    }
  }
  @keyframes np-wave-pan {
    from {
      transform: translateX(-4%);
    }
    to {
      transform: translateX(4%);
    }
  }
  .np-progress {
    display: flex;
    align-items: center;
    gap: 10px;
    max-width: 100%;
  }
  .np-time {
    font-family: system-ui, sans-serif;
    font-size: clamp(0.7rem, 1.4vw, 0.85rem);
    font-variant-numeric: tabular-nums;
    color: #999;
    flex-shrink: 0;
  }
  .np-bar {
    position: relative;
    flex: 1;
    height: 5px;
    border-radius: 3px;
    background: #444;
    overflow: visible;
    min-width: 48px;
  }
  .np-fill {
    width: 32%;
    height: 100%;
    background: #c8c8c8;
    border-radius: 3px;
  }
  .np-knob {
    position: absolute;
    top: 50%;
    width: 12px;
    height: 12px;
    margin-left: -6px;
    margin-top: -6px;
    border-radius: 50%;
    background: #eee;
    border: 2px solid #c8c8c8;
    box-shadow: 0 1px 4px rgba(0, 0, 0, 0.4);
  }
  .np-controls {
    display: flex;
    flex-shrink: 0;
    gap: clamp(8px, 1.5vw, 16px);
    align-items: center;
  }
  .np-btn {
    min-width: 48px;
    min-height: 48px;
    padding: 0 14px;
    border: none;
    border-radius: 8px;
    background: #3a3a3a;
    color: #eee;
    font-size: 0.85rem;
    font-family: system-ui, sans-serif;
  }
  .np-btn.np-main {
    min-width: 64px;
    min-height: 56px;
    background: #e0e0e0;
    color: #111;
    font-weight: 700;
  }
  .np-btn:focus-visible {
    outline: 2px solid #fff;
    outline-offset: 2px;
  }
  @media (max-width: 520px) {
    .np {
      flex-wrap: wrap;
      justify-content: center;
    }
    .np-maincol {
      flex: 1 1 100%;
      order: 2;
      text-align: center;
    }
    .np-center {
      text-align: center;
    }
    .np-art {
      order: 1;
    }
    .np-controls {
      order: 3;
      width: 100%;
      justify-content: center;
    }
  }
</style>
