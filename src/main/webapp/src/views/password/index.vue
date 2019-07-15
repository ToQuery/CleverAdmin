<template>
  <div class="app-container">
    <el-row :gutter="20">
      <el-col :span="6" :xs="24">
        <el-card class="card-size">
          <div slot="header" class="clearfix">
            <span>个人信息</span>
          </div>
          <div class="user-profile">
            <div class="box-center">
              <pan-thumb :image="user.avatar" :height="'100px'" :width="'100px'" :hoverable="false">
                <div>Hello World</div>
              </pan-thumb>
            </div>
            <div class="box-center" style="margin-top: 20px">
              <el-tooltip effect="light" :content="'昵称:' + nickname" placement="top">
                <div class="user-name text-center"> {{ user.name }}</div>
              </el-tooltip>
              <div class="user-role text-center text-muted">{{ user.role | uppercaseFirst }}</div>
            </div>
          </div>
        </el-card>
      </el-col>
      <el-col :span="18" :xs="24">
        <el-card class="box-card card-size">
          <div slot="header" class="clearfix">
            <span>{{ $t('navbar.password') }}</span>
          </div>
          <div style="width: 40%;min-width:500px;margin-left: auto;margin-right: auto;">
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
                <el-button type="primary" @click="submitForm()">提交</el-button>
                <el-button @click="resetForm()">重置</el-button>
              </el-form-item>
            </el-form>
          </div>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import { mapGetters } from 'vuex'
import PanThumb from '@/components/PanThumb'
import { password } from '@/api/user'
export default {
  name: 'Password',
  components: { PanThumb },
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
      user: {},
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
    ...mapGetters(['name', 'nickname', 'avatar', 'roles'])
  },
  created() {
    this.getUser()
  },
  methods: {
    getUser() {
      this.user = {
        nickname: this.nickname,
        name: this.name,
        role: this.roles.join(' | '),
        email: 'admin@test.com',
        avatar: this.avatar
      }
    },
    resetFormData() {
      this.changePasswordForm = {
        rawPassword: '',
        rawPasswordConfirm: '',
        sourcePassword: ''
      }
    },
    submitForm() {
      this.$refs['ruleForm'].validate((valid) => {
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
    resetForm() {
      this.$refs['ruleForm'].resetFields()
    }
  }
}
</script>
<style lang="scss" scoped>
  .card-size {
    margin-bottom:20px;
    width: 100%;
    min-height: 400px;
  }
  .box-center {
    margin: 0 auto;
    display: table;
  }

  .text-muted {
    color: #777;
  }

  .user-profile {
    .user-name {
      font-weight: bold;
    }

    .box-center {
      padding-top: 10px;
    }

    .user-role {
      padding-top: 10px;
      font-weight: 400;
      font-size: 14px;
    }

    .box-social {
      padding-top: 30px;

      .el-table {
        border-top: 1px solid #dfe6ec;
      }
    }

    .user-follow {
      padding-top: 20px;
    }
  }

  .user-bio {
    margin-top: 20px;
    color: #606266;

    span {
      padding-left: 4px;
    }

    .user-bio-section {
      font-size: 14px;
      padding: 15px 0;

      .user-bio-section-header {
        border-bottom: 1px solid #dfe6ec;
        padding-bottom: 10px;
        margin-bottom: 10px;
        font-weight: bold;
      }
    }
  }
</style>
