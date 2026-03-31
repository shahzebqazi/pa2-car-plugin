<script lang="ts">
  interface Props {
    label: string
    note?: string
  }
  let { label, note = '' }: Props = $props()
</script>

<div class="frame-label" role="status">
  <span class="badge">{label}</span>
  {#if note}
    <span class="note">{note}</span>
  {/if}
</div>

<style>
  .frame-label {
    display: flex;
    flex-wrap: wrap;
    align-items: baseline;
    gap: 0.5rem 0.75rem;
    padding: 0.5rem 0.75rem;
    background: rgba(0, 0, 0, 0.35);
    border-bottom: 1px solid var(--mock-chrome-border, #333);
  }
  .badge {
    font-size: 0.7rem;
    font-weight: 700;
    letter-spacing: 0.06em;
    text-transform: uppercase;
    color: var(--pa2-primary, #70cccc);
  }
  .note {
    font-size: 0.75rem;
    color: var(--pa2-on-surface-variant, #cbced1);
    max-width: 42rem;
    line-height: 1.35;
  }
</style>
