<script lang="ts">
  /** Phone mockups use PA2 primary on the badge; Auto mockups use neutral chrome so reviewers do not read teal as “car UI branding”. */
  interface Props {
    label: string
    note?: string
    surface?: 'phone' | 'auto'
  }
  let { label, note = '', surface = 'phone' }: Props = $props()
</script>

<div class="frame-label" class:frame-label--auto={surface === 'auto'} role="status">
  <span class="badge" class:badge--auto={surface === 'auto'}>{label}</span>
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

  .frame-label--auto {
    background: rgba(30, 30, 30, 0.85);
    border-bottom-color: #3a3a3a;
  }

  .badge {
    font-size: 0.7rem;
    font-weight: 700;
    letter-spacing: 0.06em;
    text-transform: uppercase;
    color: var(--pa2-primary, #70cccc);
  }

  .badge--auto {
    color: #b8bdc0;
  }

  .note {
    font-size: 0.75rem;
    color: var(--pa2-on-surface-variant, #cbced1);
    max-width: 42rem;
    line-height: 1.35;
  }

  .frame-label--auto .note {
    color: #c8cdd0;
  }
</style>
