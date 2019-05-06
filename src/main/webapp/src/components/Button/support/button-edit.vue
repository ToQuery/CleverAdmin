<script>
import ButtonBase from './button-base.vue'
export default {
  // name: 'XButtonEdit',
  // 暴露的属性
  extends: ButtonBase,
  props: {
    text: {
      type: String,
      default: '编辑'
    },
    type: {
      type: String,
      default: 'primary'
    },
    icon: {
      type: String,
      default: 'el-icon-edit'
    },
    size: {
      type: String,
      default: 'mini'
    }
  }
}
</script>
