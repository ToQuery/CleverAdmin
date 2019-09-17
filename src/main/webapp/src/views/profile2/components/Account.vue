<template>
  <el-form>
    <el-form-item label="昵称">
      <el-input v-model.trim="user.nickname" />
    </el-form-item>
    <el-form-item label="Email">
      <el-input v-model.trim="user.email" />
    </el-form-item>
    <el-form-item>
      <el-button type="primary" @click="submit">Update</el-button>
    </el-form-item>
  </el-form>
</template>

<script>
import sysUserApi from '@/api/system/user'
export default {
  data() {
    return {
      user: {}
    }
  },
  created() {
    this.getUser()
  },
  methods: {
    getUser() {
      this.$store.dispatch('user/getInfo').then(reponse => {
        this.user = reponse
      })
    },
    submit() {
      sysUserApi.saveOrUpdate(this.user, this.$route.query.rootPwd).then(reponse => {
        this.$message({
          message: '修改用户信息成功！',
          type: 'success',
          duration: 5 * 1000
        })
      })
    }
  }
}
</script>
