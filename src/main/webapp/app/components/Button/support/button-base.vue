<template>
  <CWButtonBase v-waves name="123" :class="clazz" :type="primary" :icon="icon">
    {{ text ? text : $t('table.search') }}
  </CWButtonBase>
</template>
<script>
import { Button } from 'element-ui'
export default {
  name: 'CWButtonBase',
  extends: Button,
  props: {
    text: {
      type: String,
      default: ''
    },
    clazz: {
      type: String,
      default: ''
    },
    type: {
      type: String,
      default: 'primary'
    },
    icon: {
      type: String,
      default: ''
    },
    size: {
      type: String,
      default: 'mini'
    }
  },
  data() {
    return {
    }
  },
  computed: {
  },
  methods: {
  }
}
</script>
