'use strict';
const path = require('path');
const webpack = require('webpack');
const utils = require('./webpack/utils');
const CopyWebpackPlugin = require('copy-webpack-plugin');
const writeFilePlugin = require('write-file-webpack-plugin');
const WebpackNotifierPlugin = require('webpack-notifier');


function resolve(dir) {
    return path.join(__dirname, '..', dir);
}
function assetsPath(_path) {
    /*const assetsSubDirectory =
        process.env.NODE_ENV === 'production'
            ? config.build.assetsSubDirectory
            : config.dev.assetsSubDirectory;*/

    return path.posix.join(_path);
}

module.exports = {
    outputDir: 'target/www',
    pages: {
        index: {
            // page 的入口
            entry: 'src/main/webapp/app/main.js',
            // 模板来源
            template: 'src/main/webapp/index.html',
            // 在 dist/index.html 的输出
            filename: 'index.html',
            // 当使用 title 选项时，
            // template 中的 title 标签需要是 <title><%= htmlWebpackPlugin.options.title %></title>
            title: 'Clever Web Index',
            // 在这个页面中包含的块，默认情况下会包含
            // 提取出来的通用 chunk 和 vendor chunk。
            chunks: ['chunk-vendors', 'chunk-common', 'index']
        }
    },
    configureWebpack: {
        resolve: {
            alias: {
                "~": path.resolve(__dirname, 'src/main/webapp/app/'),
                "@": path.resolve(__dirname, 'src/main/webapp/app/'),
            }
        },
        plugins: [
            new CopyWebpackPlugin([
                { from: './src/main/webapp/favicon.ico', to: 'favicon.ico' },
                { from: './src/main/webapp/manifest.webapp', to: 'manifest.webapp' },
                { from: './src/main/webapp/robots.txt', to: 'robots.txt' }
            ]),
            // new webpack.HotModuleReplacementPlugin(),
            // new writeFilePlugin(),
            new webpack.WatchIgnorePlugin([
                utils.root('src/test'),
            ]),
            new WebpackNotifierPlugin({
                title: 'Clever Web',
                contentImage: path.join(__dirname, 'src/main/webapp/favicon.ico')
            })
        ]
    },

    chainWebpack: config => {
        // svg-sprite-loader
        config.module.rule('svg-sprite-loader')
            .include.add(resolve('src/icons')).end()
            .test(/\.svg$/)
            .use('svg-sprite-loader')
            .loader('svg-sprite-loader')
            .options({symbolId: 'icon-[name]'})
            .end();

        config.module.rule('icon-url-loader')
            .exclude.add(resolve('src/icons')).end()
            .test(/\.(png|jpe?g|gif|svg)(\?.*)?$/)
            .use('url-loader')
            .loader('url-loader')
            .options({limit: 10000,name: assetsPath('img/[name].[hash:7].[ext]')})
            .end();

        config.module.rule('media-url-loader')
            .test(/\.(mp4|webm|ogg|mp3|wav|flac|aac)(\?.*)?$/)
            .use('url-loader')
            .loader('url-loader')
            .options({limit: 10000,name: assetsPath('media/[name].[hash:7].[ext]')})
            .end();

        config.module.rule('fonts-url-loader')
            .test(/\.(woff2?|eot|ttf|otf)(\?.*)?$/)
            .use('url-loader')
            .loader('url-loader')
            .options({limit: 10000,name: assetsPath('fonts/[name].[hash:7].[ext]')})
            .end();
    }
};
