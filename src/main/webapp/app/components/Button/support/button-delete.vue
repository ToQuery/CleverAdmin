<script>
import ButtonBase from './button-base.vue'
export default {
  // name: 'XButtonDelete',
  // 暴露的属性
  extends: ButtonBase,
  props: {
    text: {
      type: String,
      default: '删除'
    },
    type: {
      type: String,
      default: 'default'
    },
    icon: {
      type: String,
      default: 'el-icon-delete'
    },
    size: {
      type: String,
      default: 'mini'
    }

  }
}
</script>
