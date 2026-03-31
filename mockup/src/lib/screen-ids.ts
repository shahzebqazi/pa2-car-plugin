/** Mockup frame ids (sidebar + stage). Home is a separate route, not a frame. */
export type MockupFrameId =
  | 'phone-np'
  | 'phone-browse'
  | 'phone-queue'
  | 'phone-error'
  | 'auto-np'
  | 'auto-browse'
  | 'auto-error'
  | 'dhu-browse'
  | 'dhu-np'

export const MOCKUP_FRAMES: readonly MockupFrameId[] = [
  'phone-np',
  'auto-np',
  'phone-browse',
  'auto-browse',
  'phone-error',
  'auto-error',
  'phone-queue',
  'dhu-browse',
  'dhu-np',
] as const

export function isMockupFrameId(s: string): s is MockupFrameId {
  return (MOCKUP_FRAMES as readonly string[]).includes(s)
}
