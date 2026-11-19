import { defineConfig } from 'vite'
import uni from '@dcloudio/vite-plugin-uni'

// https://vitejs.dev/config/
export default defineConfig({
  plugins: [uni()],
  server: {
    port: 5173,
    // 开发时把 /admin-api 代理到后端，避免跨域
    proxy: {
      '/admin-api': {
        target: 'http://127.0.0.1:18080',
        changeOrigin: true
      }
    }
  }
})
