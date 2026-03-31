<script lang="ts">
  import FrameLabel from './FrameLabel.svelte'
</script>

<div class="auto-shell">
  <FrameLabel
    label="Auto — host media (PA2)"
    note="P2 error wireframe: one short sentence + recovery (G8). Actual copy/layout from host after MediaSession error reporting."
  />
  <div class="auto-screen">
    <div class="panel">
      <p class="message">Can’t connect. Check your network and try again.</p>
      <button type="button" class="retry">Retry</button>
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
    display: flex;
    align-items: center;
    justify-content: center;
    padding: clamp(16px, 3vw, 32px);
    background: linear-gradient(180deg, #2a2a2a 0%, #1e1e1e 100%);
    box-sizing: border-box;
  }
  .panel {
    max-width: min(420px, 90%);
    padding: clamp(16px, 2.5vw, 24px);
    border-radius: 12px;
    background: #2e2e2e;
    border: 1px solid #555;
  }
  .message {
    margin: 0 0 16px;
    font-family: system-ui, -apple-system, BlinkMacSystemFont, 'Segoe UI', sans-serif;
    font-size: clamp(0.9rem, 2vw, 1.05rem);
    line-height: 1.4;
    color: #eee;
  }
  .retry {
    width: 100%;
    min-height: 48px;
    padding: 12px 16px;
    border: none;
    border-radius: 8px;
    font-family: system-ui, sans-serif;
    font-weight: 600;
    font-size: 0.95rem;
    background: #e0e0e0;
    color: #111;
  }
  .retry:focus-visible {
    outline: 2px solid #fff;
    outline-offset: 2px;
  }
</style>
