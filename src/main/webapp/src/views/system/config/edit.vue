<template>
  <el-dialog :title="dialogTitleMap[dialogStatus]" :visible.sync="dialogFormVisible">
    <el-form
      ref="contentForm"
      :rules="rules"
      :model="content"
      label-position="left"
      label-width="90px"
      style="width: 400px; margin-left:50px;"
    >
      <el-form-item :label="$t('system.config.configGroup')" prop="configGroup">
        <el-input v-model="content.configGroup" />
      </el-form-item>
      <el-form-item :label="$t('system.config.configName')" prop="configName">
        <el-input v-model="content.configName" />
      </el-form-item>
      <el-form-item :label="$t('system.config.configValue')" prop="configValue">
        <el-input v-model="content.configValue" />
      </el-form-item>
      <el-form-item :label="$t('system.config.sortNum')" prop="sortNum">
        <el-input-number v-model="content.sortNum" :min="0" :max="999" :label="$t('system.config.sortNum')" />
      </el-form-item>
    </el-form>
    <div slot="footer" class="dialog-footer">
      <el-button @click="dialogFormVisible = false">
        {{ $t('table.cancel') }}
      </el-button>
      <el-button type="primary" @click="saveOrUpdateData()">
        {{ $t('table.confirm') }}
      </el-button>
    </div>
  </el-dialog>
</template>

<script>
import sysConfigApi from '@/api/system/config'

export default {
  name: 'SystemConfigEdit',
  filters: {
  },
  data() {
    return {
      dialogFormVisible: false,
      dialogStatus: '',
      dialogTitleMap: {
        update: '创建',
        create: '保存'
      },
      content: {
        id: undefined,
        sortNum: 0,
        configValue: '',
        configName: '',
        configGroup: ''
      },
      rules: {
        sortNum: [{ required: true, message: 'sortNum is required', trigger: 'change' }],
        configValue: [{ required: true, message: 'configValue is required', trigger: 'change' }],
        configGroup: [{ required: true, message: 'configGroup is required', trigger: 'change' }],
        configName: [{ required: true, message: 'configName is required', trigger: 'change' }]
      },
      roleLoading: false,
      enabledOptions: [{
        label: '启用',
        value: true
      }, {
        label: '禁用',
        value: false
      }],
      sysRoleOptions: []
    }
  },
  created() {
  },
  methods: {
    resetContent() {
      this.content = {
        id: undefined,
        sortNum: 0,
        configValue: '',
        configName: '',
        configGroup: ''
      }
    },
    handleShowEdit(id = undefined) {
      this.resetContent()
      const isCreate = id === undefined || id === null || id === ''
      this.dialogStatus = isCreate ? 'create' : 'update'
      this.dialogFormVisible = true
      this.$nextTick(() => {
        this.$refs['contentForm'].clearValidate()
      })
      if (!isCreate) {
        sysConfigApi.get(id).then((reponses) => {
          this.content = reponses.content
        })
      }
    },
    handleDataSuccess(reponses) {
      this.dialogFormVisible = false
      this.$emit('success')
    },
    saveOrUpdateData() {
      this.$refs['contentForm'].validate((valid) => {
        if (valid) {
          sysConfigApi.saveOrUpdate(this.content).then((reponses) => {
            this.$notify({ title: '成功', message: '操作成功', type: 'success', duration: 2000 })
            this.handleDataSuccess(reponses)
          }).catch(error => {
            this.dialogFormVisible = true
            console.info(error)
          })
        }
      })
    }
  }
}
</script>
