
export function getMediaType(fileExtension) {
  const fileGroup = new Map()
  fileGroup.set('jpg', 'image')
  fileGroup.set('jpeg', 'image')
  fileGroup.set('png', 'image')
  fileGroup.set('svg', 'image')
  fileGroup.set('gif', 'image')
  fileGroup.set('wav', 'audio')
  fileGroup.set('mp3', 'audio')
  fileGroup.set('mp4', 'video')
  fileGroup.set('mov', 'video')
  return fileGroup.get(fileExtension.toLowerCase())
}
