<script>
import ButtonBase from './button-base.vue'
export default {
  // name: 'XButtonDetail',
  // 暴露的属性
  extends: ButtonBase,
  props: {
    text: {
      type: String,
      default: '详情'
    },
    type: {
      type: String,
      default: ''
    },
    icon: {
      type: String,
      default: 'el-icon-document'
    },
    size: {
      type: String,
      default: 'medium'
    }
  }
}
</script>
