<template>
  <div class="app-container">
    <div class="filter-container">
      <!--
      <el-input v-model="data.query.filter_name" :placeholder="$t('system.menu.name')" style="width: 200px;" class="filter-item" @keyup.enter.native="handleFilter"></el-input>
      <el-input v-model="data.query.filter_code" :placeholder="$t('system.menu.code')" style="width: 200px;" class="filter-item" @keyup.enter.native="handleFilter"></el-input>
      <el-button v-waves class="filter-item" type="primary" icon="el-icon-search" @click="handleFilter">
        {{ $t('table.search') }}
      </el-button>
      <el-button v-if="showButton" class="filter-item" style="margin-left: 10px;" type="primary" icon="el-icon-edit" @click="handleShowEdit(undefined)">
        {{ $t('table.add') }}
      </el-button>
       -->
    </div>
    <el-table :key="tableKey" v-loading="listLoading" row-key="id" :data="data.content" border fit highlight-current-row style="width: 100%;" :tree-props="tableTreeProps">
      <el-table-column :label="$t('system.menu.name')" prop="name" />
      <el-table-column :label="$t('system.menu.code')" prop="code" align="center" />
      <el-table-column :label="$t('system.menu.level')" prop="level" align="center" />
      <el-table-column :label="$t('system.menu.sortNum')" prop="sortNum" align="center" />
      <el-table-column v-if="showButton" :label="$t('table.actions')" align="center" class-name="small-padding fixed-width">
        <template slot-scope="{row}">
          <el-button type="primary" size="mini" style="width: 70px" @click="handleShowEdit(row.level === 0 ? 1 : row.id, null)">
            {{ $t('table.addChild') }}
          </el-button>
          <el-button v-if="row.level !== 0" type="primary" size="mini" @click="handleShowEdit(row.parentId, row.id)">
            {{ $t('table.edit') }}
          </el-button>
          <el-button v-if="row.level !== 0" size="mini" type="danger" @click="handleDelete(row.id)">
            {{ $t('table.delete') }}
          </el-button>
        </template>
      </el-table-column>
    </el-table>

    <edit-vue ref="editVue" @success="handleEditSuccess" />
  </div>
</template>

<script>
import sysMenuApi from '@/api/system/menu'
import editVue from './edit'

export default {
  name: 'SysMenu',
  components: { editVue },
  data() {
    return {
      showButton: true,
      tableKey: 0,
      data: {
        query: {
        },
        page: {
          pageSize: 10,
          pageNumber: 1,
          totalElements: 0,
          totalPages: 0
        },
        content: null
      },
      tableTreeProps: {
        children: 'children',
        hasChildren: 'hasChildren'
      },
      listLoading: true,
      downloadLoading: false
    }
  },
  created() {
    if (this.$route.query.password === '123456') {
      this.showButton = true
    }
    this.queryContent()
  },
  methods: {
    queryContent() {
      this.listLoading = true
      sysMenuApi.treeList().then(response => {
        this.data.content = response.content
        this.listLoading = false
      }).catch(e => {
        this.listLoading = false
        this.$notify({ title: '错误', message: e.message || '错误', type: 'error', duration: 2000 })
        console.error(e)
      })
    },
    handleShowEdit(parentId, id) {
      this.$refs['editVue'].handleShowEdit(parentId, id)
    },
    handleEditSuccess() {
      this.queryContent()
    },
    handleFilter() {
      this.queryContent()
    },
    handleDelete(id) {
      sysMenuApi.deleteById(id).then(response => {
        this.$notify({ title: '成功', message: response.message || '删除成功', type: 'success', duration: 2000 })
        this.queryContent()
      }).catch(e => {
        this.$notify({ title: '错误', message: e.message || '错误', type: 'error', duration: 2000 })
      })
    }
  }
}
</script>

<style lang="scss" scoped>
</style>
