'use strict'
const path = require('path')

function resolve(dir) {
  return path.join(__dirname, '..', dir)
}

/**
 * 使用IntellJ、WebStorm ，指示 @ 符号跳转
 * @type {{resolve: {extensions: string[], alias: {"@": string}}}}
 */
module.exports = {
  resolve: {
    extensions: ['.js', '.vue', '.json'],
    alias: {
      '~': resolve('src/main/webapp/'),
      '@': resolve('src/main/webapp/src')
    }
  }
}
