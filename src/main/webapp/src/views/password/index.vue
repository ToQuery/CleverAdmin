<template>
  <div class="app-container">
    <el-card class="box-card">
      <div slot="header" class="clearfix">
        <span>{{ $t('navbar.password') }}</span>
      </div>
      <div>
        <el-form ref="ruleForm" :model="changePasswordForm" status-icon :rules="rules" label-width="100px">
          <el-form-item label="原密码" prop="sourcePassword">
            <el-input v-model.number="changePasswordForm.sourcePassword" type="password" clearable show-password />
          </el-form-item>
          <el-form-item label="新密码" prop="rawPassword">
            <el-input v-model="changePasswordForm.rawPassword" type="password" autocomplete="off" clearable show-password />
          </el-form-item>
          <el-form-item label="确认密码" prop="rawPasswordConfirm">
            <el-input v-model="changePasswordForm.rawPasswordConfirm" type="password" autocomplete="off" clearable show-password />
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="submitForm('ruleForm')">提交</el-button>
            <el-button @click="resetForm('ruleForm')">重置</el-button>
          </el-form-item>
        </el-form>
      </div>
    </el-card>
  </div>
</template>

<script>
// import { mapGetters } from 'vuex'
import { password } from '@/api/user'
export default {
  name: 'Password',
  components: { },
  data() {
    var validatePass = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请输入密码'))
      } else {
        if (this.changePasswordForm.rawPasswordConfirm !== '') {
          this.$refs.ruleForm.validateField('rawPasswordConfirm')
        }
        callback()
      }
    }
    var validatePass2 = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请再次输入密码'))
      } else if (value !== this.changePasswordForm.rawPassword) {
        callback(new Error('两次输入密码不一致!'))
      } else {
        callback()
      }
    }
    return {
      changePasswordForm: {
        rawPassword: '',
        rawPasswordConfirm: '',
        sourcePassword: ''
      },
      rules: {
        rawPassword: [
          { validator: validatePass, trigger: 'blur' }
        ],
        rawPasswordConfirm: [
          { validator: validatePass2, trigger: 'blur' }
        ],
        sourcePassword: [
          { required: true, message: 'sourcePassword is required', trigger: 'change' }
        ]
      }
    }
  },
  computed: {
  },
  created() {
  },
  methods: {
    resetFormData() {
      this.changePasswordForm = {
        rawPassword: '',
        rawPasswordConfirm: '',
        sourcePassword: ''
      }
    },
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          password(this.changePasswordForm).then(response => {
            this.$notify({ title: '成功', message: response.message || '操作成功', type: 'success', duration: 2000 })
            this.resetForm('ruleForm')
          }).catch(error => {
            this.$notify({ title: '错误', message: error.response.data.message || '修改失败', type: 'error', duration: 2000 })
          })
        }
      })
    },
    resetForm(formName) {
      this.$refs[formName].resetFields()
    }
  }
}
</script>
