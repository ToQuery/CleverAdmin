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
      <el-form-item :label="$t('system.user.loginName')" prop="loginName">
        <el-input v-model="content.loginName" />
      </el-form-item>
      <el-form-item :label="$t('system.user.userName')" prop="userName">
        <el-input v-model="content.userName" />
      </el-form-item>
      <el-form-item :label="$t('system.user.enabled')" prop="enabled">
        <el-select v-model="content.enabled" class="filter-item" placeholder="Please select" :value="content.enabled">
          <el-option v-for="item in enabledOptions" :key="item" :label="item" :value="item" />
        </el-select>
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
import systemUserApi from '@/api/system/user'

export default {
  name: 'SystemUserEdit',
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
        loginName: '',
        userName: '',
        lastPasswordResetDate: new Date(),
        enabled: true
      },
      enabledOptions: [true, false],
      rules: {
        loginName: [{ required: true, message: 'type is required', trigger: 'change' }],
        userName: [{ type: 'date', required: true, message: 'timestamp is required', trigger: 'change' }],
        enabled: [{ type: 'date', required: true, message: 'enabled is required', trigger: 'change' }]
      }
    }
  },
  created() {
  },
  methods: {
    resetContent() {
      this.content = {
        id: undefined,
        loginName: '',
        userName: '',
        lastPasswordResetDate: new Date(),
        enabled: true
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
        systemUserApi.get(id).then((reponses) => {
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
          systemUserApi.saveOrUpdate(this.content).then((reponses) => {
            this.$notify({ title: '成功', message: '创建成功', type: 'success', duration: 2000 })
            this.handleDataSuccess()
          })
        }
      })
    }
  }
}
</script>
