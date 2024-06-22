import { defineConfig } from 'vite';
import vue from '@vitejs/plugin-vue';

import AutoImport from 'unplugin-auto-import/vite'
import Components from 'unplugin-vue-components/vite'
import { ElementPlusResolver } from 'unplugin-vue-components/resolvers'

// https://vitejs.dev/config/
export default defineConfig({
  server: {
    host: '0.0.0.0',
    proxy: {
      '/server': {
        target: 'http://localhost:8099', // 后端API服务器地址
        changeOrigin: true, // 是否改变源地址
        rewrite: (path) => path.replace(/^\/api/, '') // 重写路径
      }
    }
  },
  plugins: [
    vue(),
    AutoImport({
      resolvers: [ElementPlusResolver()],
    }),
    Components({
      resolvers: [ElementPlusResolver()],
    })
  ],
  compilerOptions: {
    // ...
    types: ['element-plus/global'],
  },
});
