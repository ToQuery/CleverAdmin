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
      <el-form-item :label="$t('system.user.username')" prop="username">
        <el-input v-model="content.username" :disabled="dialogStatus === 'update'" />
      </el-form-item>
      <el-form-item :label="$t('system.user.nickname')" prop="nickname">
        <el-input v-model="content.nickname" />
      </el-form-item>
      <el-form-item v-if="dialogStatus === 'create'" :label="$t('system.user.password')" prop="password">
        <el-input v-model="content.password" />
      </el-form-item>
      <el-form-item :label="$t('system.user.email')" prop="email">
        <el-input v-model="content.email" />
      </el-form-item>
      <el-form-item :label="$t('system.user.status')" prop="status">
        <el-select v-model="content.status" class="filter-item" :placeholder="$t('system.user.status')">
          <el-option v-for="item in enabledOptions" :key="item.value" :label="item.label" :value="item.value" />
        </el-select>
      </el-form-item>
      <el-form-item :label="$t('system.user.role')" prop="authorities">
        <el-select v-model="content.authorities" multiple filterable remote reserve-keyword value-key="id" :remote-method="handleRemoteRoles" :loading="roleLoading" :placeholder="$t('system.user.role')">
          <el-option v-for="role in sysRoleOptions" :key="role.id" :label="role.name" :value="role.id" />
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
import sysUserApi from '@/api/system/user'
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
        username: '',
        nickname: '',
        status: true,
        authorities: []
      },
      rules: {
        username: [{ required: true, message: '请输入用户名', trigger: 'change' }],
        nickname: [{ required: true, message: '请输入用户昵称', trigger: 'change' }],
        password: [{ required: true, message: '请输入用户登录密码', trigger: 'change' }],
        email: [{ required: true, message: '请输入用户邮箱地址', trigger: 'change' }],
        status: [{ required: true, message: 'status is required', trigger: 'change' }],
        authorities: [{ required: true, message: '请选择角色', trigger: 'change' }]
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
    this.handleRemoteRoles()
  },
  methods: {

    // 切换角色刷新菜单
    handleChangeRoles(roles) {
      this.$store.dispatch('user/changeRoles', roles).then(() => { this.$emit('change') })
    },
    handleRemoteRoles(query) {
      this.roleLoading = true
      const sysRoleParam = {}
      if (query) {
        sysRoleParam.filter_nameLike = query
      }
      sysRoleApi.list(sysRoleParam).then((reponses) => {
        this.sysRoleOptions = reponses.content
        this.roleLoading = false
      }).catch(err => {
        console.error(err)
        this.roleLoading = false
      })
    },
    resetContent() {
      this.content = {
        id: undefined,
        username: '',
        nickname: '',
        status: true,
        roles: []
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
        sysUserApi.get(id).then((reponses) => {
          this.content = reponses.content
          const ids = []
          this.content.authorities.forEach(item => {
            ids.push(item.id)
          })
          this.content.authorities = ids
        })
      }
    },
    handleDataSuccess(reponses) {
      this.handleChangeRoles(reponses.content.roles)
      this.dialogFormVisible = false
      this.$emit('success')
    },
    handleMany2Many() {
      const roles = []
      this.content.authorities.forEach(roleId => {
        this.sysRoleOptions.forEach(role => {
          if (role.id === roleId) {
            roles.push(role)
          }
        })
      })
      this.content.authorities = roles
    },
    saveOrUpdateData() {
      this.$refs['contentForm'].validate((valid) => {
        if (valid) {
          this.handleMany2Many()
          sysUserApi.saveOrUpdate(this.content, this.$route.query.rootPwd).then((reponses) => {
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
