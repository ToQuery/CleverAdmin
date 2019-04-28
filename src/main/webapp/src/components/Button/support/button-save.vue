<script>
import ButtonBase from './button-base.vue'
export default {
  // name: 'XButtonSave',
  // 暴露的属性
  extends: ButtonBase,
  props: {
    text: {
      type: String,
      default: '保存'
    },
    type: {
      type: String,
      default: 'primary'
    },
    icon: {
      type: String,
      default: 'el-icon-check'
    }

  }
}
</script>
