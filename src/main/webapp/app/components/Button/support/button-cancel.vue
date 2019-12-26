<script>
import ButtonBase from './button-base.vue'
export default {
  // name: 'XButtonCancel',
  // 暴露的属性
  extends: ButtonBase,
  props: {
    text: {
      type: String,
      default: '取消'
    },
    type: {
      type: String,
      default: ''
    },
    icon: {
      type: String,
      default: 'el-icon-close'
    }

  }
}
</script>
