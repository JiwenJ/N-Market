const { defineConfig } = require('@vue/cli-service')
const path = require('path')

module.exports = defineConfig({
  transpileDependencies: true,
  // publicPath: './',
  pluginOptions: {
    'style-resources-loader': {
      preProcessor: 'less',
      patterns: [
        path.join(__dirname, './src/assets/style/variables.less'),
        path.join(__dirname, './src/assets/style/mixin.less'),
        path.join(__dirname, './src/assets/style/base.css')
      ]
    }
  },
    devServer: {
    port:8080,

    proxy: {
        '/api':{
            target:'http://124.71.159.90:8081/',//这里后台地址;填写真实的后台接口
            ws: true,
            changeOrigin:true,//允许跨域
            pathRewrite:{
                '^/api':''
            }
        }
    }
  }
})
