<template>
  <div class="app-container">
    <div class="filter-container">
      <el-input v-model="data.filter.filter_usernameLike" clearable :placeholder="$t('system.user.username')" style="width: 200px;" class="filter-item" @keyup.enter.native="handleFilter" />
      <el-input v-model="data.filter.filter_nicknameLike" clearable :placeholder="$t('system.user.nickname')" style="width: 200px;" class="filter-item" @keyup.enter.native="handleFilter" />
      <el-button v-waves class="filter-item" type="primary" icon="el-icon-search" @click="handleFilter">
        {{ $t('table.search') }}
      </el-button>
      <el-button class="filter-item" style="margin-left: 10px;" type="primary" icon="el-icon-edit" @click="handleShowEdit(undefined)">
        {{ $t('table.add') }}
      </el-button>
    </div>
    <el-table
      :key="tableKey"
      v-loading="listLoading"
      :data="data.content"
      border
      fit
      highlight-current-row
      style="width: 100%;"
    >
      <el-table-column :label="$t('system.user.username')" prop="username" align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.username }}</span>
        </template>
      </el-table-column>
      <el-table-column :label="$t('system.user.nickname')" align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.nickname }}</span>
        </template>
      </el-table-column>
      <el-table-column :label="$t('system.user.lastPasswordResetDate')" width="200px" align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.lastPasswordResetDate | parseTime('{y}-{m}-{d} {h}:{i}') }}</span>
        </template>
      </el-table-column>
      <el-table-column :label="$t('system.user.enabled')" align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.enabled }}</span>
        </template>
      </el-table-column>
      <el-table-column :label="$t('system.user.role')" align="center">
        <template slot-scope="scope">
          <el-tag v-for="role in scope.row.authorities" :key="role.id" style="margin-right: 10px">{{ role.name }}</el-tag>
        </template>
      </el-table-column>
      <el-table-column :label="$t('table.actions')" align="center" class-name="small-padding fixed-width">
        <template slot-scope="{row}">
          <el-button type="primary" size="mini" @click="handleShowEdit(row.id)">
            {{ $t('table.edit') }}
          </el-button>
          <el-button size="mini" type="danger" @click="handleDelete(row.id)">
            {{ $t('table.delete') }}
          </el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination v-show="data.page.totalElements > 0" :total="data.page.totalElements" :page.sync="data.page.pageNumber" :limit.sync="data.page.pageSize" @pagination="queryContent" />

    <edit-vue ref="editVue" @success="handleEditSuccess" />
  </div>
</template>

<script>
import systemUserApi from '@/api/system/user'
import editVue from './edit'
import waves from '@/directive/waves' // waves directive
import Pagination from '@/components/Pagination' // secondary package based on el-pagination

export default {
  name: 'SystemUser',
  components: { editVue, Pagination },
  directives: { waves },
  filters: {
  },
  data() {
    return {
      tableKey: 0,
      data: {
        filter: {
          filter_nicknameLike: '',
          filter_usernameLike: ''
        },
        page: {
          pageSize: 10,
          pageNumber: 1,
          totalElements: 0,
          totalPages: 0
        },
        content: null
      },
      listLoading: true,
      downloadLoading: false
    }
  },
  created() {
    this.queryContent()
  },
  methods: {
    queryContent() {
      this.listLoading = true
      systemUserApi.query(this.data.filter, this.data.page).then(response => {
        this.data.content = response.content
        this.data.page = response.page
        this.listLoading = false
      }).catch(e => {
        this.listLoading = false
        this.$notify({ title: '错误', message: e.message || '错误', type: 'error', duration: 2000 })
        console.error(e)
      })
    },
    handleShowEdit(id) {
      this.$refs['editVue'].handleShowEdit(id)
    },
    handleEditSuccess() {
      this.queryContent()
    },
    handleFilter() {
      this.data.page.pageNumber = 1
      this.queryContent()
    },
    handleDelete(id) {
      systemUserApi.deleteById(id).then(response => {
        this.$notify({ title: '成功', message: response.message || '删除成功', type: 'success', duration: 2000 })
        this.queryContent()
      }).catch(e => {
        this.$notify({ title: '错误', message: e.message || '错误', type: 'error', duration: 2000 })
      })
    }
  }
}
</script>
