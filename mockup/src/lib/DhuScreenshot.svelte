<script lang="ts">
  import FrameLabel from './FrameLabel.svelte'

  interface Props {
    /** Resolved URL for a file under public/ (use publicUrl() so base path works on GH Pages). */
    imageSrc: string
    alt: string
    caption: string
  }
  let { imageSrc, alt, caption }: Props = $props()
</script>

<div class="dhu-shell">
  <FrameLabel
    label="Sandbox — Car App Library"
    note="DHU screenshot stand-in (public/dhu/). Kotlin androidx.car.app sample only — not PA2 shipping Auto (host Media3). Replace PNGs via DHU terminal screenshot; see mockup/README.md."
  />
  <figure class="figure">
    <div class="img-wrap">
      <img src={imageSrc} {alt} class="shot" loading="lazy" decoding="async" />
    </div>
    <figcaption class="cap">{caption}</figcaption>
  </figure>
</div>

<style>
  .dhu-shell {
    width: 100%;
    max-width: var(--car-display-max-w, 1120px);
    margin: 0 auto;
    border-radius: 12px;
    overflow: hidden;
    border: 1px solid var(--mock-chrome-border);
    background: #0a0a0a;
    box-shadow: 0 20px 50px rgba(0, 0, 0, 0.55);
  }
  .figure {
    margin: 0;
    padding: 0 12px 16px;
  }
  .img-wrap {
    border-radius: 8px;
    overflow: hidden;
    border: 1px solid #333;
    background: #000;
    aspect-ratio: var(--car-aspect-ratio, 16 / 9);
    display: flex;
    align-items: center;
    justify-content: center;
  }
  .shot {
    display: block;
    width: 100%;
    height: 100%;
    object-fit: contain;
  }
  .cap {
    margin: 10px 4px 0;
    font-size: 0.78rem;
    line-height: 1.4;
    color: var(--pa2-on-surface-variant);
  }
</style>
