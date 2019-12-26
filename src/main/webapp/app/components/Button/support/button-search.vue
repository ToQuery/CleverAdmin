<script>
import ButtonBase from './button-base.vue'
export default {
  // name: 'XButtonSearch',
  extends: ButtonBase,
  props: {
    text: {
      type: String,
      default: '查询'
    },
    type: {
      type: String,
      default: 'primary'
    },
    icon: {
      type: String,
      default: 'el-icon-search'
    },
    xClass: {
      type: String,
      default: 'filter-item'
    }
  }
}
</script>
