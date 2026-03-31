<script lang="ts">
  let heroFailed = $state(false)
</script>

<div class="home">
  <div class="hero-fill">
    {#if !heroFailed}
      <img
        src="/dhu/dhu-now-playing.png"
        alt="Desktop Head Unit: Android Auto now playing (sandbox sample)"
        class="hero-img"
        width="1920"
        height="1080"
        decoding="async"
        fetchpriority="high"
        onerror={() => (heroFailed = true)}
      />
    {:else}
      <div class="hero-fallback">
        <p>
          Add <code>public/dhu/dhu-now-playing.png</code> (DHU <code>screenshot</code>). Served as
          <code>/dhu/dhu-now-playing.png</code> while <code>npm run dev</code> is running.
        </p>
      </div>
    {/if}
    <div class="hero-vignette" aria-hidden="true"></div>
  </div>

  <footer class="home-dock">
    <p class="dock-title">Power Ampache 2 × Android Auto · browser mockup</p>
    <nav class="dock-nav" aria-label="Next pages">
      <a class="dock-link" href="#/design">Design system</a>
      <a class="dock-link" href="#/research">UX research</a>
      <a class="dock-link" href="#/mockup">Mockup frames</a>
    </nav>
  </footer>
</div>

<style>
  .home {
    position: relative;
    min-height: 100svh;
    width: 100%;
    background: var(--mock-chrome-bg);
    display: flex;
    flex-direction: column;
  }

  .hero-fill {
    position: relative;
    flex: 1;
    min-height: 0;
    display: flex;
  }

  .hero-img {
    width: 100%;
    height: 100%;
    min-height: 100%;
    object-fit: cover;
    object-position: center;
    display: block;
  }

  .hero-fallback {
    flex: 1;
    display: flex;
    align-items: center;
    justify-content: center;
    padding: 32px 24px;
    text-align: center;
    color: var(--pa2-on-surface-variant);
    font-size: 0.95rem;
    line-height: 1.55;
  }

  .hero-fallback code {
    font-size: 0.85rem;
    color: var(--pa2-on-surface);
  }

  .hero-vignette {
    pointer-events: none;
    position: absolute;
    inset: 0;
    background: linear-gradient(
      to top,
      rgba(11, 13, 12, 0.95) 0%,
      rgba(11, 13, 12, 0.25) 35%,
      transparent 55%
    );
  }

  .home-dock {
    position: relative;
    z-index: 1;
    margin-top: auto;
    padding: 20px 24px 28px;
    border-top: 1px solid rgba(255, 255, 255, 0.06);
    background: linear-gradient(to top, rgba(11, 13, 12, 0.98), rgba(11, 13, 12, 0.88));
    backdrop-filter: blur(8px);
  }

  .dock-title {
    margin: 0 0 12px;
    font-size: 0.78rem;
    font-weight: 700;
    letter-spacing: 0.04em;
    text-transform: uppercase;
    color: var(--pa2-primary);
  }

  .dock-nav {
    display: flex;
    flex-wrap: wrap;
    gap: 12px 20px;
  }

  .dock-link {
    font-size: 0.92rem;
    font-weight: 700;
    color: var(--pa2-on-surface);
    text-decoration: none;
    border-bottom: 2px solid var(--pa2-primary);
    padding-bottom: 2px;
  }

  .dock-link:hover {
    color: var(--pa2-primary);
  }

  .dock-link:focus-visible {
    outline: 2px solid var(--pa2-tertiary);
    outline-offset: 4px;
    border-radius: 4px;
  }
</style>
