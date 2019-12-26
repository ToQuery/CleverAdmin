<template>
  <el-dialog :title="dialogTitleMap[dialogStatus]" :visible.sync="dialogFormVisible">
    <el-form ref="contentForm" :rules="rules" :model="content" label-position="left" label-width="110px" style="width: 400px; margin-left:50px;">
      <el-form-item :label="$t('system.log.moduleName')" prop="moduleName">
        <el-input v-model="content.moduleName" disabled />
      </el-form-item>
      <el-form-item :label="$t('system.log.bizName')" prop="bizName">
        <el-input v-model="content.bizName" disabled />
      </el-form-item>
      <el-form-item :label="$t('system.log.logType')" prop="logType">
        <el-input v-model="content.logType" disabled />
      </el-form-item>
      <el-form-item :label="$t('system.log.rawData')" prop="rawData">
        <el-input v-model="content.rawData" type="textarea" disabled />
      </el-form-item>
      <el-form-item :label="$t('system.log.targetData')" prop="targetData">
        <el-input v-model="content.targetData" type="textarea" disabled />
      </el-form-item>
      <el-form-item :label="$t('system.log.createDatetime')" prop="createDatetime">
        <el-input v-model="content.createDatetime" disabled />
      </el-form-item>
    </el-form>
    <div v-if="false" slot="footer" class="dialog-footer">
      <el-button @click="dialogFormVisible = false">{{ $t('table.cancel') }}</el-button>
      <el-button type="primary" @click="saveOrUpdateData()">{{ $t('table.confirm') }}</el-button>
    </div>
  </el-dialog>
</template>

<script>
import sysLogApi from '@/api/system/log'

export default {
  name: 'SysLogEdit',
  data() {
    return {
      dialogFormVisible: false,
      dialogStatus: '',
      dialogTitleMap: { update: '创建', create: '保存' },
      content: {
        moduleName: '',
        bizName: '',
        logType: '',
        rawData: '',
        targetData: '',
        createDatetime: new Date(),
        id: undefined
      },
      rules: {
        moduleName: [{ required: true, message: 'moduleName is required', trigger: 'change' }],
        bizName: [{ required: true, message: 'bizName is required', trigger: 'change' }],
        logType: [{ required: true, message: 'logType is required', trigger: 'change' }],
        rawData: [{ required: true, message: 'rawData is required', trigger: 'change' }],
        targetData: [{ required: true, message: 'targetData is required', trigger: 'change' }],
        createDatetime: [{ required: true, message: 'createDatetime is required', trigger: 'change' }]
      }
    }
  },
  created() {
  },
  methods: {
    resetContent() {
      this.content = {
        moduleName: '',
        bizName: '',
        logType: '',
        rawData: '',
        targetData: '',
        createDatetime: new Date(),
        id: undefined
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
        sysLogApi.get(id).then((reponses) => {
          this.content = reponses.content
        })
      }
    },
    handleDataSuccess() {
      this.dialogFormVisible = false
      this.$emit('success')
    },
    saveOrUpdateData() {
      this.$refs['contentForm'].validate((valid) => {
        if (valid) {
          sysLogApi.saveOrUpdate(this.content).then((reponses) => {
            this.$notify({ title: '成功', message: '创建成功', type: 'success', duration: 2000 })
            this.handleDataSuccess()
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
