<script>
import ButtonBase from './button-base.vue'
export default {
  // name: 'XButtonNew',
  // 暴露的属性
  extends: ButtonBase,
  props: {
    text: {
      type: String,
      default: '新建'
    },
    type: {
      type: String,
      default: 'danger'
    },
    icon: {
      type: String,
      default: 'el-icon-plus'
    },
    xClass: {
      type: String,
      default: 'filter-item'
    }
  }
}
</script>
