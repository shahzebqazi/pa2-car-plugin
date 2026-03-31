<script lang="ts">
  import FrameLabel from './FrameLabel.svelte'
</script>

<div class="phone-shell">
  <FrameLabel
    label="Phone — PA2 theme"
    note="When the server is unreachable: one clear sentence and a single retry. Deeper fixes (accounts, settings) belong on the phone or when parked."
  />
  <div class="screen">
    <header class="top">
      <button type="button" class="icon-btn" aria-label="Back">←</button>
      <span class="title">Library</span>
      <span class="spacer"></span>
    </header>

    <div class="card">
      <p class="message">Can’t reach the server. Check your connection and try again.</p>
      <button type="button" class="retry">Retry</button>
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
    background: var(--pa2-surface);
    box-shadow: 0 24px 48px rgba(0, 0, 0, 0.45);
    font-family: 'Nunito', system-ui, sans-serif;
  }
  .screen {
    padding: 0 16px 32px;
    min-height: 420px;
    background: var(--pa2-surface);
  }
  .top {
    display: grid;
    grid-template-columns: 48px 1fr 48px;
    align-items: center;
    padding: 12px 0 20px;
  }
  .title {
    text-align: center;
    font-weight: 600;
    font-size: 0.95rem;
    color: var(--pa2-on-surface);
  }
  .spacer {
    width: 48px;
  }
  .icon-btn {
    border: none;
    background: transparent;
    color: var(--pa2-primary);
    font-size: 1.25rem;
    min-width: 48px;
    min-height: 48px;
    padding: 0;
    border-radius: 12px;
    display: grid;
    place-items: center;
  }
  .icon-btn:focus-visible {
    outline: 2px solid var(--pa2-primary);
    outline-offset: 2px;
  }
  .card {
    margin-top: 24px;
    padding: 20px;
    border-radius: 16px;
    background: var(--pa2-surface-container);
    border: 1px solid rgba(245, 147, 171, 0.35);
  }
  .message {
    margin: 0 0 16px;
    font-size: 0.95rem;
    line-height: 1.45;
    color: var(--pa2-on-surface);
  }
  .retry {
    width: 100%;
    min-height: 48px;
    padding: 12px 16px;
    border: none;
    border-radius: 12px;
    font-weight: 700;
    background: var(--pa2-primary);
    color: var(--pa2-on-primary);
  }
  .retry:focus-visible {
    outline: 2px solid var(--pa2-tertiary);
    outline-offset: 2px;
  }
</style>
