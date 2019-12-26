<script>
import ButtonBase from './button-base.vue'
export default {
  // name: 'XButtonEdit',
  // 暴露的属性
  extends: ButtonBase,
  props: {
    text: {
      type: String,
      default: '绑定API'
    },
    type: {
      type: String,
      default: 'primary'
    },
    icon: {
      type: String,
      default: 'el-icon-circle-plus-outline'
    },
    size: {
      type: String,
      default: 'mini'
    },
    xClass: {
      type: String,
      default: 'el-button-w90'
    }
  }
}
</script>
