<template>
  <div class="components-container">
    <aside>
      {{ $t('components.tinymceTips') }}
      <a target="_blank" class="link-type" href="https://panjiachen.github.io/vue-element-admin-site/component/rich-editor.html"> {{ $t('components.documentation') }}</a>
    </aside>
    <div>
      <vue-ueditor-wrap v-model="content" :config="ueditorConfig" />
    </div>
    <div class="editor-content" v-html="content" />
  </div>
</template>

<script>
import VueUeditorWrap from 'vue-ueditor-wrap'
export default {
  name: 'UeditorDemo',
  components: { VueUeditorWrap },
  data() {
    return {
      content: `<h1 style="text-align: center;">Welcome to the ueditor demo!</h1>`,
      ueditorConfig: {
        // 编辑器不自动被内容撑高
        autoHeightEnabled: false,
        // 初始容器高度
        initialFrameHeight: 240,
        // 初始容器宽度
        initialFrameWidth: '100%',
        // 上传文件接口（这个地址是我为了方便各位体验文件上传功能搭建的临时接口，请勿在生产环境使用！！！）
        serverUrl: '/app/ueditor',
        // UEditor 资源文件的存放路径，如果你使用的是 vue-cli 生成的项目，通常不需要设置该选项，vue-ueditor-wrap 会自动处理常见的情况，如果需要特殊配置，参考下方的常见问题2
        UEDITOR_HOME_URL: '/static/UEditor/'
      }
    }
  }
}
</script>

<style scoped>
.editor-content{
  margin-top: 20px;
}
</style>

