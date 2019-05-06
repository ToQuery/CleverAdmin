<template>
  <el-button v-waves class="filter-item" type="primary" icon="el-icon-search" @click="handleFilter">
    {{ $t('table.search') }}
  </el-button>
</template>
<script>
import { Button } from 'element-ui'
export default {
  name: 'CWButtonBase',
  extends: Button,
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
