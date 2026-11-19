// API 接口定义
import { http } from '@/utils/request'
import type { Feedback, FeedbackReq, LoginResp, PageResult, PermissionInfo, Task } from '@/types'

// ========== 认证 ==========

// 登录
export function loginApi(username: string, password: string) {
  return http.post<LoginResp>('/system/auth/login', {
    username,
    password,
    captchaVerification: ''
  })
}

// 登出
export function logoutApi() {
  return http.post<boolean>('/system/auth/logout')
}

// 获取权限信息
export function getPermissionInfoApi() {
  return http.get<PermissionInfo>('/system/auth/get-permission-info')
}

// ========== 生产任务 ==========

// 任务分页列表
export function getTaskPageApi(params: Record<string, any>) {
  return http.get<PageResult<Task>>('/mes/pro/task/list', params)
}

// 任务详情
export function getTaskDetailApi(id: number | string) {
  return http.get<Task>(`/mes/pro/task/get/${id}`)
}

// 按任务编号查任务
export function getTaskByCodeApi(taskCode: string) {
  return http.get<Task>('/mes/pro/task/findTaskByOrderCode', { taskCode })
}

// ========== 生产报工 ==========

// 报工列表（分页）
export function getFeedbackListApi(params: Record<string, any>) {
  return http.get<PageResult<Feedback>>('/mes/pro/feedback/list', params)
}

// 新增报工（报工2：按任务报工）
export function insertFeedbackApi(data: FeedbackReq) {
  return http.post<number>('/mes/pro/feedback/insert', data)
}

// 修改报工状态（开工/暂停/恢复/完成）
export function updateFeedbackStatusApi(data: FeedbackReq) {
  return http.post<number>('/mes/pro/feedback/updateStatus', data)
}

// 报工详情
export function getFeedbackDetailApi(id: number | string) {
  return http.get<Feedback>(`/mes/pro/feedback/find/${id}`)
}

// 删除报工
export function deleteFeedbackApi(id: number | string) {
  return http.delete<number>(`/mes/pro/feedback/delete/${id}`)
}

// ========== 统计 ==========

// 未开工数量
export function getUnStartedCountApi() {
  return http.get<number>('/mes/pro/feedback/unStartOrders')
}

// 已完成数量
export function getCompletedCountApi() {
  return http.get<number>('/mes/pro/feedback/completed')
}

// 已暂停数量
export function getPausedCountApi() {
  return http.get<number>('/mes/pro/feedback/paused')
}

// ========== 字典 ==========

// 暂停原因
export function getPauseReasonApi() {
  return http.get<any[]>('/system/dict-data/list-by-type', { dictType: 'reason_for_suspension' })
}
