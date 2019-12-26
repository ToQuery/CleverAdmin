<template>
  <div class="app-container">
    <el-row :gutter="20">
      <el-col :span="6" :xs="24">
        <user-card class="card-size" />
      </el-col>
      <el-col :span="18" :xs="24">
        <el-card class="card-size">
          <el-tabs v-model="activeTab">
            <el-tab-pane label="账号信息" name="account">
              <account />
            </el-tab-pane>
            <el-tab-pane label="修改密码" name="password">
              <password />
            </el-tab-pane>
          </el-tabs>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import UserCard from './components/UserCard'
import Account from './components/Account'
import Password from './components/Password'
export default {
  name: 'Profile2',
  components: { UserCard, Account, Password },
  data() {
    return {
      activeTab: 'account'
    }
  },
  computed: {
  },
  created() {
  },
  methods: {
  }
}
</script>
<style lang="scss" scoped>
  .card-size {
    margin-bottom:20px;
    width: 100%;
    min-height: 400px;
  }
</style>
