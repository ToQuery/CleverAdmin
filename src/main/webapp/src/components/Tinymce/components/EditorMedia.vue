<template>
  <div class="upload-container">
    <el-button :style="{background:color,borderColor:color}" icon="el-icon-upload" size="mini" type="primary" @click=" dialogVisible=true">
      上传媒体
    </el-button>
    <el-dialog :visible.sync="dialogVisible">
      <el-upload
        drag
        multiple
        :headers="{ 'Authorization': token }"
        :file-list="fileList"
        :show-file-list="true"
        :on-remove="handleRemove"
        :on-error="handleUploadError"
        :on-success="handleSuccess"
        :before-upload="beforeUpload"
        class="editor-slide-upload"
        action="/app/files/upload"
      >
        <i class="el-icon-upload">&nbsp;</i>
        <div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
        <div slot="tip" class="el-upload__tip">支持批量插入，建议文件小于100MB</div>
      </el-upload>
      <el-button @click="dialogVisible = false">
        取消
      </el-button>
      <el-button type="primary" @click="handleSubmit">
        插入
      </el-button>
    </el-dialog>
  </div>
</template>

<script>
import { getMediaType } from '@/utils/media'
// import { getToken } from 'api/qiniu'
import { mapGetters } from 'vuex'
export default {
  name: 'EditorSlideUpload',
  props: {
    color: {
      type: String,
      default: '#1890ff'
    }
  },
  data() {
    return {
      dialogVisible: false,
      listObj: {},
      fileList: []
    }
  },
  computed: mapGetters(['token']),
  methods: {
    handleUploadError() {
      this.$notify({ title: '错误', message: '上传错误', type: 'error', duration: 2000 })
    },
    checkAllSuccess() {
      return Object.keys(this.listObj).every(item => this.listObj[item].hasSuccess)
    },
    handleSubmit() {
      const arr = Object.keys(this.listObj).map(v => this.listObj[v])
      if (!this.checkAllSuccess()) {
        this.$message('请检测确认所有文件都已上传成功!')
        return
      }
      this.$emit('success', arr)
      this.listObj = {}
      this.fileList = []
      this.dialogVisible = false
    },
    handleSuccess(response, file) {
      const uid = file.uid
      const objKeyArr = Object.keys(this.listObj)
      for (let i = 0, len = objKeyArr.length; i < len; i++) {
        if (this.listObj[objKeyArr[i]].uid === uid) {
          this.listObj[objKeyArr[i]].url = response.content.fullDownloadPath
          this.listObj[objKeyArr[i]].hasSuccess = true
          return
        }
      }
    },
    handleRemove(file) {
      const uid = file.uid
      const objKeyArr = Object.keys(this.listObj)
      for (let i = 0, len = objKeyArr.length; i < len; i++) {
        if (this.listObj[objKeyArr[i]].uid === uid) {
          delete this.listObj[objKeyArr[i]]
          return
        }
      }
    },
    beforeUpload(file) {
      const mediaType = this.handleMediaType(file.name)
      if (!mediaType) {
        this.$message.error('文件 ' + file.name + ' 上传错误，格式不支持！')
        return false
      }
      const _this = this
      const fileUid = file.uid
      this.listObj[fileUid] = {}
      return new Promise((resolve, reject) => {
        _this.listObj[fileUid] = { mediaType: mediaType, hasSuccess: false, uid: file.uid, width: this.width, height: this.height }
        resolve(true)
      })
    },
    handleMediaType(fileName) {
      const extension = fileName.substring(fileName.lastIndexOf('.') + 1)
      return getMediaType(extension)
    }
  }
}
</script>

<style lang="scss" scoped>
.editor-slide-upload {
  margin-bottom: 20px;
  /deep/ .el-upload--picture-card {
    width: 100%;
  }
}
</style>
