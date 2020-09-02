<template>
  <div class="app-container">
    <el-card>
      <div slot="header" class="clearfix"><span>系统日志管理</span></div>
      <div>
        <div class="filter-container">
          <el-input v-model="data.query.filter_moduleNameLIKE" clearable :placeholder="$t('system.log.moduleName')" style="width: 200px;" class="filter-item" @keyup.enter.native="handleFilter" />
          <el-input v-model="data.query.filter_bizNameLIKE" clearable :placeholder="$t('system.log.bizName')" style="width: 200px;" class="filter-item" @keyup.enter.native="handleFilter" />
          <el-select v-model="data.query.filter_logType" clearable :placeholder="$t('system.log.logType')" style="width: 200px;" class="filter-item" @keyup.enter.native="handleFilter">
            <el-option v-for="item in logTypeOptions" :key="item.value" :label="item.label" :value="item.value" />
          </el-select>
          <el-date-picker
            v-model="createDateArray"
            clearable
            type="daterange"
            range-separator="至"
            :start-placeholder="$t('system.log.createDatetime')"
            :end-placeholder="$t('system.log.createDatetime')"
            style="width: 370px;"
            class="filter-item"
            :default-time="['00:00:00','23:59:59']"
            format="yyyy-MM-dd"
            value-format="yyyy-MM-dd HH:mm:ss"
            @keyup.enter.native="handleFilter"
            @change="handleChangeCreateDate"
          />
          <el-button v-waves class="filter-item" type="primary" icon="el-icon-search" @click="handleFilter">
            {{ $t('table.search') }}
          </el-button>
        </div>
        <el-table :key="tableKey" v-loading="listLoading" :data="data.content" border fit highlight-current-row style="width: 100%;">
          <el-table-column type="expand">
            <template slot-scope="props">
              <el-form label-position="left" inline class="demo-table-expand">
                <el-form-item :label="$t('system.log.logType')">
                  <span v-if="props.row.logType === 'CREA'">创建</span>
                  <span v-if="props.row.logType === 'MODF'">修改</span>
                  <span v-if="props.row.logType === 'DEL'">删除</span>
                  <span v-if="props.row.logType === 'SEARCH'">查看</span>
                </el-form-item>
                <el-form-item :label="$t('system.log.moduleName')">
                  <span>{{ props.row.moduleName }}</span>
                </el-form-item>
                <el-form-item :label="$t('system.log.bizName')">
                  <span>{{ props.row.bizName }}</span>
                </el-form-item>
                <el-form-item :label="$t('system.log.rawData')">
                  <span>{{ props.row.rawData }}</span>
                </el-form-item>
                <el-form-item :label="$t('system.log.targetData')">
                  <span>{{ props.row.targetData }}</span>
                </el-form-item>
                <el-form-item :label="$t('system.log.createDatetime')">
                  <span>{{ props.row.createDatetime }}</span>
                </el-form-item>
              </el-form>
            </template>
          </el-table-column>
          <el-table-column :label="$t('system.log.username')" prop="sysUser.username" align="center" />
          <el-table-column :label="$t('system.log.moduleName')" prop="moduleName" align="center" />
          <el-table-column :label="$t('system.log.bizName')" prop="bizName" align="center" />
          <el-table-column :label="$t('system.log.logType')" prop="logType" align="center">
            <template slot-scope="props">
              <span v-if="props.row.logType === 'CREA'">创建</span>
              <span v-if="props.row.logType === 'MODF'">修改</span>
              <span v-if="props.row.logType === 'DEL'">删除</span>
              <span v-if="props.row.logType === 'SEARCH'">查看</span>
            </template>
          </el-table-column>
          <el-table-column v-if="false" :label="$t('system.log.rawData')" prop="rawData" align="center" />
          <el-table-column v-if="false" :label="$t('system.log.targetData')" prop="targetData" align="center" />
          <el-table-column :label="$t('system.log.createDatetime')" prop="createDatetime" align="center" />
          <el-table-column v-if="false" :label="$t('table.actions')" align="center" class-name="small-padding fixed-width">
            <template slot-scope="{row}">
              <el-button type="primary" size="mini" @click="handleShowEdit(row.id)">{{ $t('table.edit') }}</el-button>
            </template>
          </el-table-column>
        </el-table>

        <pagination v-show="data.page.totalElements > 0" :total="data.page.totalElements" :page.sync="data.page.pageNum" :limit.sync="data.page.pageSize" @pagination="queryContent" />

        <edit-vue ref="editVue" @success="handleEditSuccess" />
      </div>
    </el-card>
  </div>
</template>

<script>
import sysLogApi from '@/api/system/log'
import editVue from './edit'
import Pagination from '@/components/Pagination' // secondary package based on el-pagination
import waves from '@/directive/waves' // waves directive

export default {
  name: 'SysLog',
  components: { editVue, Pagination },
  directives: { waves },
  data() {
    return {
      createDateArray: [],
      logTypeOptions: [
        { value: 'CREA', label: '创建' },
        { value: 'MODF', label: '修改' },
        { value: 'DEL', label: '删除' },
        { value: 'SEARCH', label: '查看' }
      ],
      tableKey: 0,
      data: {
        query: {
        },
        page: {
          pageSize: 10,
          pageNum: 1,
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
    handleChangeCreateDate(value) {
      this.createDateArray = value
    },
    queryContent() {
      this.listLoading = true
      sysLogApi.query(this.data.query, this.data.page).then(response => {
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
      if (this.createDateArray && this.createDateArray.length > 0) {
        this.data.query.filter_createDatetimeGT = this.createDateArray[0]
        this.data.query.filter_createDatetimeLT = this.createDateArray[1]
      } else {
        this.data.query.filter_createDatetimeGT = null
        this.data.query.filter_createDatetimeLT = null
      }
      console.info(this.data.query)
      this.data.page.pageNum = 1
      this.queryContent()
    },
    handleDelete(id) {
      sysLogApi.deleteById(id).then(response => {
        this.$notify({ title: '成功', message: response.message || '删除成功', type: 'success', duration: 2000 })
      }).catch(e => {
        this.$notify({ title: '错误', message: e.message || '错误', type: 'error', duration: 2000 })
      })
    }
  }
}
</script>

<style lang="scss" scoped>
  .demo-table-expand {
    font-size: 0;
  }
  .demo-table-expand label {
    width: 90px;
    color: #99a9bf;
  }
  .demo-table-expand .el-form-item {
    margin-right: 0;
    margin-bottom: 0;
    width: 50%;
  }
</style>
