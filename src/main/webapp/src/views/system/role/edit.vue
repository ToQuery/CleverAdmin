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
      <el-form-item :label="$t('system.role.name')" prop="name">
        <el-input v-model="content.name" />
      </el-form-item>
      <el-form-item :label="$t('system.role.code')" prop="code">
        <el-input v-model="content.code" />
      </el-form-item>
      <el-form-item :label="$t('system.role.user')">
        <div v-if="content.users.length > 0">
          <el-tag v-for="user in content.users" :key="user.id" :label="user.loginName" :value="user.id">{{ user.loginName }}</el-tag>
        </div>
        <div v-else>暂无用户</div>
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
import sysRoleApi from '@/api/system/role'

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
        name: '',
        code: '',
        users: []
      },
      rules: {
        name: [{ required: true, message: 'loginName is required', trigger: 'change' }],
        code: [{ required: true, message: 'userName is required', trigger: 'change' }]
      }
    }
  },
  created() {
  },
  methods: {
    resetContent() {
      this.content = {
        id: undefined,
        name: '',
        code: '',
        users: []
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
        sysRoleApi.get(id).then((reponses) => {
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
          this.handleMany2Many()
          sysRoleApi.saveOrUpdate(this.content).then((reponses) => {
            this.$notify({ title: '成功', message: '创建成功', type: 'success', duration: 2000 })
            this.handleDataSuccess()
          }).catch(error => {
            console.info(error)
            this.dialogFormVisible = false
          })
        }
      })
    }
  }
}
</script>
