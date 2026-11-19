// 请求封装：基于 uni.request，统一处理 token、tenant-id、错误提示

import type { CommonResult } from '@/types'

// 后端地址
// - H5：走 vite 代理，用相对路径 /admin-api
// - App 真机：没有代理，必须用电脑的局域网 IP（编译时通过 VITE_API_BASE 注入完整地址）
// 条件编译：#ifdef APP-PLUS 只在 App 平台生效
// #ifdef APP-PLUS
const BASE_URL = import.meta.env.VITE_API_BASE_APP || 'http://192.168.1.4:18080/admin-api'
// #endif
// #ifndef APP-PLUS
const BASE_URL = import.meta.env.VITE_API_BASE || '/admin-api'
// #endif

const TOKEN_KEY = 'mes_token'
const USER_KEY = 'mes_user'
const TENANT_KEY = 'mes_tenant_id'

export function getToken(): string {
  return uni.getStorageSync(TOKEN_KEY) || ''
}

export function setToken(token: string) {
  uni.setStorageSync(TOKEN_KEY, token)
}

export function clearToken() {
  uni.removeStorageSync(TOKEN_KEY)
}

export function getUser(): { id: number; nickname: string; roles: string[] } | null {
  return uni.getStorageSync(USER_KEY) || null
}

export function setUser(user: any) {
  uni.setStorageSync(USER_KEY, user)
}

export function clearUser() {
  uni.removeStorageSync(USER_KEY)
}

export function getTenantId(): string {
  return uni.getStorageSync(TENANT_KEY) || '1'
}

export function setTenantId(id: string) {
  uni.setStorageSync(TENANT_KEY, id)
}

interface RequestOptions {
  url: string
  method?: 'GET' | 'POST' | 'PUT' | 'DELETE'
  data?: any
  params?: Record<string, any>
  // 是否需要鉴权，默认 true
  auth?: boolean
  // 错误时是否自动弹 toast，默认 true
  showError?: boolean
}

/**
 * 统一请求
 * 后端返回结构 { code, data, msg }，code=0 表示成功
 */
export function request<T = any>(options: RequestOptions): Promise<T> {
  const {
    url,
    method = 'GET',
    data,
    params,
    auth = true,
    showError = true
  } = options

  return new Promise<T>((resolve, reject) => {
    // 拼接 query 参数
    let fullUrl = url.startsWith('http') ? url : `${BASE_URL}${url}`
    if (params && Object.keys(params).length > 0) {
      const query = Object.keys(params)
        .filter((k) => params[k] !== undefined && params[k] !== null && params[k] !== '')
        .map((k) => `${encodeURIComponent(k)}=${encodeURIComponent(params[k])}`)
        .join('&')
      if (query) {
        fullUrl += (fullUrl.includes('?') ? '&' : '?') + query
      }
    }

    const header: Record<string, string> = {
      'Content-Type': 'application/json'
    }

    if (auth) {
      const token = getToken()
      if (token) {
        header['Authorization'] = `Bearer ${token}`
      }
    }
    header['tenant-id'] = getTenantId()

    uni.request({
      url: fullUrl,
      method: method as any,
      data,
      header,
      success: (res: any) => {
        const body = res.data as CommonResult<T>
        if (body && body.code === 0) {
          resolve(body.data)
        } else {
          const msg = body?.msg || '请求失败'
          if (showError) {
            uni.showToast({ title: msg, icon: 'none' })
          }
          reject(new Error(msg))
        }
      },
      fail: (err) => {
        const msg = '网络请求失败，请检查连接'
        if (showError) {
          uni.showToast({ title: msg, icon: 'none' })
        }
        reject(err)
      }
    })
  })
}

export const http = {
  get: <T = any>(url: string, params?: Record<string, any>, opts?: Partial<RequestOptions>) =>
    request<T>({ url, method: 'GET', params, ...opts }),
  post: <T = any>(url: string, data?: any, opts?: Partial<RequestOptions>) =>
    request<T>({ url, method: 'POST', data, ...opts }),
  put: <T = any>(url: string, data?: any, opts?: Partial<RequestOptions>) =>
    request<T>({ url, method: 'PUT', data, ...opts }),
  delete: <T = any>(url: string, opts?: Partial<RequestOptions>) =>
    request<T>({ url, method: 'DELETE', ...opts })
}
