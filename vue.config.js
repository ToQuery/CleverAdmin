'use strict'

const path = require('path')
const utils = require('./webpack/utils.js')
// const HtmlWebpackPlugin = require('html-webpack-plugin')
const defaultSettings = require('./src/main/webapp/app/config/settings.js')
// const CopyWebpackPlugin = require('copy-webpack-plugin');
// const HtmlWebpackPlugin = require('html-webpack-plugin')

function resolve(dir) {
  return path.join(__dirname, dir)
}

const name = defaultSettings.title || 'Clever Web Index' // page title

// If your port is set to 80,
// use administrator privileges to execute the command line.
// For example, Mac: sudo npm run
// You can change the port by the following method:
// port = 9527 npm run dev OR npm run dev --port = 9527
const port = process.env.port || process.env.npm_config_port || 9527 // dev port

module.exports = {
  /**
   * You will need to set publicPath if you plan to deploy your site under a sub path,
   * for example GitHub Pages. If you plan to deploy your site to https://foo.github.io/bar/,
   * then publicPath should be set to "/bar/".
   * In most cases please use '/' !!!
   * Detail: https://cli.vuejs.org/config/#publicpath
   */
  publicPath: '/',
  // 相对于outputDir的静态资源(js、css、img、fonts)目录
  assetsDir: 'static',
  outputDir: utils.root('target/classes/static/'),
  // outputDir: 'target/classes/static/',
  lintOnSave: process.env.NODE_ENV === 'development',
  productionSourceMap: false,

  // parallel: require('os').cpus().length > 1, // 多核编译

  /*
  // 这里配置了 pages， 在 chainWebpack 必须删除 插件 preload prefetch ！！！
  // TODO: Remove this workaround once https://github.com/vuejs/vue-cli/issues/2463 is fixed
  pages: {
    index: {
      // page 的入口
      entry: './src/main/webapp/src/main.js',
      // 模板来源
      template: './src/main/webapp/index.html',
      // 在 dist/index.html 的输出
      filename: 'index.html',
      // 当使用 title 选项时，
      // template 中的 title 标签需要是 <title><%= htmlWebpackPlugin.options.title %></title>
      title: name,
      // 在这个页面中包含的块，默认情况下会包含
      // 提取出来的通用 chunk 和 vendor chunk。
      chunks: ['chunk-vendors', 'chunk-common', 'index']
    }
  },
  */

  devServer: {
    port: port,
    open: true,

    // https://webpack.js.org/configuration/dev-server/#devserverwritetodisk-
    // writeToDisk: true,
    // output: {
    //   path: path.resolve(__dirname, './target/classes/static/'),
    // },

    // hot: true,
    // contentBase: './target/classes/static/',
    overlay: {
      warnings: false,
      errors: true
    },
    watchOptions: {
      ignored: /node_modules/
    },
    // hotOnly: true, //热更新（webpack已实现了，这里false即可）
    // proxy: 'http://127.0.0.1:8080',
    proxy: {
      // change xxx-api/login => mock/login
      // detail: https://cli.vuejs.org/config/#devserver-proxy
      // 优先匹配后台服务
      [process.env.VUE_APP_BASE_API]: {
        target: process.env.NODE_ENV === 'dev-mock' ? `http://localhost:${port}/mock` : `http://localhost:8081/api`,
        changeOrigin: true,
        pathRewrite: {
          ['^' + process.env.VUE_APP_BASE_API]: ''
        }
      },
      '/app': {
        target: process.env.NODE_ENV === 'dev-mock' ? `http://localhost:${port}/mock` : `http://localhost:8081/api`,
        changeOrigin: true
      }
    },
    after: require('./src/main/webapp/mock/mock-server.js')
  },

  configureWebpack: {
    name: name,
    entry: {
      app: './src/main/webapp/app/main.js'
    },
    resolve: {
      alias: {
        '~': resolve('src/main/webapp/'),
        '@': resolve('src/main/webapp/app/')
      }
    },
    plugins: [

      /*
      // todo 需要增加自定义目录
      // 取消 pages 配置后，增加 HtmlWebpackPlugin 插件，自定义 index.html 路径
      new HtmlWebpackPlugin({
        hash: true,
        filename: './src/main/webapp/public/index.html' // relative to root of the application
      })
      */

      // new CopyWebpackPlugin([
      //   { from: './src/main/webapp/public/favicon.ico', to: 'favicon.ico' },
      //   { from: './src/main/webapp/public/manifest.webapp', to: 'manifest.webapp' },
      //   { from: './src/main/webapp/public/robots.txt', to: 'robots.txt' }
      // ]),
      // new webpack.HotModuleReplacementPlugin(),
      // new writeFilePlugin(),

      // new webpack.WatchIgnorePlugin([
      //   utils.root('src/main/webapp/tests')
      // ])
      // new WebpackNotifierPlugin({
      //     title: 'Clever Web',
      //     contentImage: path.join(__dirname, 'src/main/webapp/favicon.ico')
      // })

      // new CopyWebpackPlugin([
      //   { from: './node_modules/swagger-ui/dist/css', to: 'swagger-ui/dist/css' },
      //   { from: './node_modules/swagger-ui/dist/lib', to: 'swagger-ui/dist/lib' },
      //   { from: './node_modules/swagger-ui/dist/swagger-ui.min.js', to: 'swagger-ui/dist/swagger-ui.min.js' },
      //   { from: './src/main/webapp//swagger-ui/', to: 'swagger-ui' },
      //   { from: './src/main/webapp/content/', to: 'content' },
      //   { from: './src/main/webapp/favicon.ico', to: 'favicon.ico' },
      //   { from: './src/main/webapp/manifest.webapp', to: 'manifest.webapp' },
      //   // jhipster-needle-add-assets-to-webpack - JHipster will add/remove third-party resources in this array
      //   { from: './src/main/webapp/robots.txt', to: 'robots.txt' }
      // ]),
      // new HtmlWebpackPlugin({
      //   template: './src/main/webapp/index.html',
      //   chunksSortMode: 'dependency',
      //   inject: 'body'
      // })
    ]
  },

  chainWebpack: config => {
    // FIX:
    // 1.preload,prefetch 插件和 pages 配置冲突，如果配置pages,则需要删除这两个插件，否则在项目根目录下增加public目录 index.html文件 （vue-cli默认配置）。
    // 2.如果想自定义main.js位置（默认在 根目录/src/main.js 下），则需要在 configureWebpack.entry.app 配置main.js文件路径
    //
    // TODO: need test
    config.plugins.delete('preload')
    config.plugins.delete('prefetch')

    // TODO: Remove this workaround once https://github.com/vuejs/vue-cli/issues/2463 is fixed
    // Remove preload plugins for multi-page build to prevent infinite recursion
    // Object.keys(pagesObject).forEach(page => {
    //   config.plugins.delete(`preload-${page}`)
    //   config.plugins.delete(`prefetch-${page}`)
    // })

    // console.info(config)
    // 替换 public/index.html 文件位置
    config.plugin('html')
      .tap(args => {
        // console.info(args)
        // template: '/Users/toquery/Projects/ToQuery/clever-web/public/index.html' =>  template: './src/main/webapp/index.html'
        args[0].template = './src/main/webapp/index.html'
        return args
      })

    // 修复HMR
    config.resolve.symlinks(true)

    // set svg-sprite-loader
    config.module
      .rule('svg')
      .exclude.add(resolve('src/main/webapp/app/icons'))
      .end()
    config.module
      .rule('icons')
      .test(/\.svg$/)
      .include.add(resolve('src/main/webapp/app/icons'))
      .end()
      .use('svg-sprite-loader')
      .loader('svg-sprite-loader')
      .options({
        symbolId: 'icon-[name]'
      })
      .end()

    // set preserveWhitespace
    config.module
      .rule('vue')
      .use('vue-loader')
      .loader('vue-loader')
      .tap(options => {
        options.compilerOptions.preserveWhitespace = true
        return options
      })
      .end()

    config
    // https://webpack.js.org/configuration/devtool/#development
      .when(process.env.NODE_ENV === 'development',
        config => config.devtool('cheap-source-map')
      )

    config
      .when(process.env.NODE_ENV !== 'development',
        config => {
          config
            .plugin('ScriptExtHtmlWebpackPlugin')
            .after('html')
            .use('script-ext-html-webpack-plugin', [{
              // `runtime` must same as runtimeChunk name. default is `runtime`
              inline: /runtime\..*\.js$/
            }])
            .end()
          config
            .optimization.splitChunks({
              chunks: 'all',
              cacheGroups: {
                libs: {
                  name: 'chunk-libs',
                  test: /[\\/]node_modules[\\/]/,
                  priority: 10,
                  chunks: 'initial' // only package third parties that are initially dependent
                },
                elementUI: {
                  name: 'chunk-elementUI', // split elementUI into a single package
                  priority: 20, // the weight needs to be larger than libs and app or it will be packaged into libs or app
                  test: /[\\/]node_modules[\\/]_?element-ui(.*)/ // in order to adapt to cnpm
                },
                commons: {
                  name: 'chunk-commons',
                  test: resolve('src/main/webapp/app/components'), // can customize your rules
                  minChunks: 3, //  minimum common number
                  priority: 5,
                  reuseExistingChunk: true
                }
              }
            })
          config.optimization.runtimeChunk('single')
        }
      )
  }
}
