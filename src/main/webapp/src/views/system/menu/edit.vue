<template>
  <el-dialog :title="dialogTitleMap[dialogStatus]" :visible.sync="dialogFormVisible">
    <el-form ref="contentForm" :rules="rules" :model="content" label-position="left" label-width="90px" style="width: 400px; margin-left:50px;">
      <el-form-item v-show="false" :label="$t('system.menu.parentId')">
        <el-input v-model="content.parentId" />
      </el-form-item>
      <el-form-item :label="$t('system.menu.parentName')">
        <el-input v-model="content.parentName" :disabled="true" />
      </el-form-item>
      <el-form-item :label="$t('system.menu.name')" prop="name">
        <el-input v-model="content.name" />
      </el-form-item>
      <el-form-item :label="$t('system.menu.code')" prop="code">
        <el-input v-model="content.code" />
      </el-form-item>
      <el-form-item :label="$t('system.menu.sortNum')" prop="sortNum">
        <el-input-number v-model="content.sortNum" :min="0" :max="999" />
      </el-form-item>
    </el-form>
    <div slot="footer" class="dialog-footer">
      <el-button @click="dialogFormVisible = false">{{ $t('table.cancel') }}</el-button>
      <el-button type="primary" @click="saveOrUpdateData()">{{ $t('table.confirm') }}</el-button>
    </div>
  </el-dialog>
</template>

<script>
import Vue from 'vue'
import menuApi from '@/api/system/menu'

export default {
  name: 'SysMenuEdit',
  filters: {},
  data() {
    return {
      dialogFormVisible: false,
      dialogStatus: '',
      dialogTitleMap: { update: '创建', create: '保存' },
      content: {
        name: '',
        code: '',
        parentId: '',
        level: '',
        leaf: '',
        sortNum: 1,
        id: undefined
      },
      rules: {
        name: [{ required: true, message: 'name is required', trigger: 'change' }],
        code: [{ required: true, message: 'code is required', trigger: 'change' }]
      }
    }
  },
  created() {
  },
  methods: {
    resetContent() {
      this.content = {
        name: '',
        code: '',
        parentId: '',
        level: '',
        parentPath: '',
        leaf: '',
        sortNum: 1,
        id: undefined
      }
    },
    handleShowEdit(parentId = 1, id = undefined) {
      this.resetContent()
      this.dialogStatus = id === undefined || id === '' || id === null ? 'create' : 'update'
      this.dialogFormVisible = true
      this.$nextTick(() => {
        this.$refs['contentForm'].clearValidate()
      })
      if (this.dialogStatus === 'update') {
        menuApi.get(id).then((reponses) => {
          this.content = reponses.content
          this.handleParent(reponses.content.parentId)
        })
      } else {
        this.handleParent(parentId)
      }
    },
    handleParent(parentId = 1) {
      const _this = this
      menuApi.get(parentId).then((reponses) => {
        Vue.set(_this.content, 'parentId', reponses.content.id)
        Vue.set(_this.content, 'parentName', reponses.content.name)
      })
    },
    handleDataSuccess() {
      this.dialogFormVisible = false
      this.$emit('success')
    },
    saveOrUpdateData() {
      this.$refs['contentForm'].validate((valid) => {
        if (valid) {
          console.info(this.content)
          menuApi.saveOrUpdate(this.content).then((reponses) => {
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
