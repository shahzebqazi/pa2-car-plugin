<script lang="ts">
  import FrameLabel from './FrameLabel.svelte'
  import { publicUrl } from './public-url'

  /** PA2 app drawable `placeholder_album` → SVG in `public/pa2/` (tint = onSurfaceVariant). */
  const albumArtSrc = publicUrl('pa2/placeholder-album.svg')
</script>

<div class="phone-shell">
  <FrameLabel
    label="Phone — PA2 theme"
    note="P0: Nunito + PA2 tokens; backdrop = current track art (cover, blur, scrim); standard seek bar (wave-style scrub exploration is on Auto — host media mockup); 48dp-class targets; reduced-motion tones down art hover only."
  />
  <div class="screen">
    <div class="bg-stack" aria-hidden="true">
      <img class="bg-cover" src={albumArtSrc} alt="" />
      <div class="bg-blur"></div>
      <div class="bg-scrim"></div>
    </div>

    <div class="content">
      <header class="top">
        <button type="button" class="icon-btn hit-48" aria-label="Back">←</button>
        <span class="title">Now playing</span>
        <button type="button" class="icon-btn hit-48" aria-label="Open queue">≡</button>
      </header>

      <div class="art-wrap">
        <img
          class="art"
          src={albumArtSrc}
          alt="Album artwork: Midnight City"
          width="280"
          height="280"
          decoding="async"
        />
      </div>

      <div class="meta">
        <h1 class="track">Midnight City</h1>
        <p class="artist">M83 · Hurry Up, We're Dreaming</p>
      </div>

      <div class="scrub-block">
        <div
          class="seek-surface hit-48"
          role="slider"
          aria-label="Playback position"
          aria-valuemin={0}
          aria-valuemax={243}
          aria-valuenow={84}
          aria-valuetext="1 minutes 24 seconds of 4 minutes 3 seconds"
          tabindex="0"
        >
          <div class="scrub" aria-hidden="true">
            <span class="time">1:24</span>
            <div class="bar">
              <div class="fill"></div>
              <div class="knob" style="left: 32%"></div>
            </div>
            <span class="time">4:03</span>
          </div>
        </div>
        <p class="scrub-hint">Drag horizontally to seek (phone gesture target)</p>
      </div>

      <div class="transport">
        <button type="button" class="ctrl secondary hit-52" aria-label="Previous track">⏮</button>
        <button type="button" class="ctrl play hit-72" aria-label="Pause">⏸</button>
        <button type="button" class="ctrl secondary hit-52" aria-label="Next track">⏭</button>
      </div>

      <p class="resume-hint">P0 resume: last playlist · ≤1 browse step from root on Auto (see Auto browse mockup)</p>
    </div>
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
    box-shadow: 0 24px 48px rgba(0, 0, 0, 0.45);
    font-family: 'Nunito', system-ui, sans-serif;
  }
  .screen {
    position: relative;
    min-height: 640px;
    overflow: hidden;
    background: var(--pa2-background);
  }
  .bg-stack {
    position: absolute;
    inset: 0;
    pointer-events: none;
  }
  .bg-cover {
    position: absolute;
    inset: -22%;
    width: 144%;
    height: 144%;
    left: -22%;
    top: -22%;
    object-fit: cover;
    transform: scale(1.08);
    transform-origin: center center;
  }
  .bg-blur {
    position: absolute;
    inset: 0;
    backdrop-filter: blur(32px);
    -webkit-backdrop-filter: blur(32px);
  }
  .bg-scrim {
    position: absolute;
    inset: 0;
    background: linear-gradient(
      180deg,
      rgba(11, 13, 12, 0.5) 0%,
      rgba(27, 31, 29, 0.78) 48%,
      rgba(27, 31, 29, 0.94) 100%
    );
  }
  .content {
    position: relative;
    z-index: 1;
    padding: 0 16px 28px;
    min-height: 640px;
  }
  @media (prefers-reduced-motion: no-preference) {
    .art {
      transition: transform 0.35s ease, box-shadow 0.35s ease;
    }
    .ctrl.play {
      transition: transform 0.2s ease;
    }
  }
  .top {
    display: grid;
    grid-template-columns: 48px 1fr 48px;
    align-items: center;
    padding: 10px 0 8px;
  }
  .title {
    text-align: center;
    font-weight: 600;
    font-size: 0.95rem;
    color: var(--pa2-on-surface);
  }
  .icon-btn {
    border: none;
    background: transparent;
    color: var(--pa2-primary);
    font-size: 1.25rem;
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
  .art-wrap {
    display: flex;
    justify-content: center;
    padding: 8px 0 20px;
  }
  .art {
    width: min(280px, 72vw);
    aspect-ratio: 1;
    height: auto;
    border-radius: 16px;
    object-fit: cover;
    border: 1px solid rgba(255, 255, 255, 0.12);
    box-shadow: 0 12px 40px rgba(0, 0, 0, 0.35);
    background: var(--pa2-surface-container-high);
  }
  @media (prefers-reduced-motion: no-preference) {
    .art:hover {
      transform: scale(1.02);
    }
  }
  .meta {
    text-align: center;
    padding: 0 8px 12px;
  }
  .track {
    margin: 0;
    /* ~18sp player title — docs/design-system/02-layout-and-navigation.md */
    font-size: 1.125rem;
    font-weight: 700;
    color: var(--pa2-on-surface);
    line-height: 1.25;
  }
  .artist {
    margin: 6px 0 0;
    font-size: 0.9rem;
    color: var(--pa2-on-surface-variant);
  }
  .scrub-block {
    padding: 0 4px 12px;
  }
  .seek-surface {
    display: flex;
    flex-direction: column;
    justify-content: center;
    gap: 4px;
    padding: 4px 6px;
    border-radius: 12px;
    border: 1px solid rgba(255, 255, 255, 0.06);
    background: rgba(0, 0, 0, 0.12);
    cursor: pointer;
  }
  .seek-surface:focus-visible {
    outline: 2px solid var(--pa2-tertiary);
    outline-offset: 2px;
  }
  .scrub {
    display: flex;
    align-items: center;
    gap: 10px;
  }
  .scrub-hint {
    margin: 8px 0 0;
    font-size: 0.65rem;
    letter-spacing: 0.02em;
    color: var(--pa2-on-surface-variant);
    opacity: 0.9;
    text-align: center;
  }
  .time {
    font-size: 0.75rem;
    font-variant-numeric: tabular-nums;
    color: var(--pa2-on-surface-variant);
    min-width: 2.25rem;
  }
  .bar {
    position: relative;
    flex: 1;
    height: 6px;
    border-radius: 3px;
    background: var(--pa2-surface-container);
    overflow: visible;
  }
  .fill {
    width: 32%;
    height: 100%;
    background: var(--pa2-primary);
    border-radius: 3px;
  }
  .knob {
    position: absolute;
    top: 50%;
    width: 14px;
    height: 14px;
    margin-left: -7px;
    margin-top: -7px;
    border-radius: 50%;
    background: var(--pa2-on-surface);
    border: 2px solid var(--pa2-primary);
    box-shadow: 0 2px 8px rgba(0, 0, 0, 0.35);
  }
  .transport {
    display: flex;
    justify-content: center;
    align-items: center;
    gap: 28px;
    padding: 8px 0 24px;
  }
  .ctrl {
    border: none;
    border-radius: 999px;
    display: grid;
    place-items: center;
  }
  .ctrl.secondary {
    background: rgba(36, 41, 39, 0.85);
    color: var(--pa2-on-surface);
    font-size: 1.25rem;
    border: 1px solid rgba(255, 255, 255, 0.08);
  }
  .hit-52 {
    width: 52px;
    height: 52px;
    min-width: 52px;
    min-height: 52px;
  }
  .ctrl.play {
    background: var(--pa2-primary);
    color: var(--pa2-on-primary);
    font-size: 1.75rem;
    font-weight: 700;
  }
  .hit-72 {
    width: 72px;
    height: 72px;
    min-width: 72px;
    min-height: 72px;
  }
  @media (prefers-reduced-motion: no-preference) {
    .ctrl.play:active {
      transform: scale(0.96);
    }
  }
  .ctrl:focus-visible {
    outline: 2px solid var(--pa2-tertiary);
    outline-offset: 3px;
  }
  .resume-hint {
    margin: 0;
    text-align: center;
    font-size: 0.78rem;
    color: var(--pa2-on-surface-variant);
    line-height: 1.4;
  }
</style>
