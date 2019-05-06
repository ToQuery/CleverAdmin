import CWButtonBase from './support/button-base'
import CWButtonCancel from './support/button-cancel'
import CWButtonDelete from './support/button-delete'
import CWButtonDetail from './support/button-detail'
import CWButtonEdit from './support/button-edit'
import CWButtonNew from './support/button-new'
import CWButtonSave from './support/button-save'
import CWButtonSearch from './support/button-search'

const buttons = function(Vue) {
  Vue.component('cw-button-base', CWButtonBase)
  Vue.component('cw-button-cancel', CWButtonCancel)
  Vue.component('cw-button-delete', CWButtonDelete)
  Vue.component('cw-button-detail', CWButtonDetail)
  Vue.component('cw-button-edit', CWButtonEdit)
  Vue.component('cw-button-new', CWButtonNew)
  Vue.component('cw-button-save', CWButtonSave)
  Vue.component('cw-button-search', CWButtonSearch)
}

export default {
  buttons
}
