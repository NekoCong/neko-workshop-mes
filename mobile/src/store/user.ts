// 全局状态：用户信息
import { defineStore } from 'pinia'
import { loginApi, getPermissionInfoApi } from '@/api'
import { setToken, clearToken, setUser, clearUser, getUser, setTenantId } from '@/utils/request'

interface UserState {
  id: number
  nickname: string
  roles: string[]
  permissions: string[]
  token: string
}

export const useUserStore = defineStore('user', {
  state: (): UserState => ({
    id: 0,
    nickname: '',
    roles: [],
    permissions: [],
    token: ''
  }),

  getters: {
    isLoggedIn: (state) => !!state.token,
    // 是否生产工人（只有报工权限）
    isWorker: (state) => state.roles.includes('mes-worker') || state.roles.includes('生产工人'),
    // 是否生产管理
    isManager: (state) =>
      state.roles.includes('mes-product') || state.roles.includes('生产管理') || state.roles.includes('super_admin')
  },

  actions: {
    async login(username: string, password: string) {
      const data = await loginApi(username, password)
      this.token = data.accessToken
      setToken(data.accessToken)
      setTenantId('1')
      return data
    },

    async loadPermission() {
      const info = await getPermissionInfoApi()
      this.id = info.user.id
      this.nickname = info.user.nickname
      this.roles = info.roles || []
      this.permissions = info.permissions || []
      setUser({ id: info.user.id, nickname: info.user.nickname, roles: info.roles || [] })
      return info
    },

    logout() {
      this.token = ''
      this.id = 0
      this.nickname = ''
      this.roles = []
      this.permissions = []
      clearToken()
      clearUser()
    },

    // 从本地缓存恢复
    restore() {
      const cached = getUser()
      if (cached) {
        this.id = cached.id
        this.nickname = cached.nickname
        this.roles = cached.roles || []
      }
    }
  }
})
