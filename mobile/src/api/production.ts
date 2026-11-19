// 生产管理 API 扩展：建工单、排产
import { http } from '@/utils/request'
import type { PageResult, Task } from '@/types'

// 产品列表（选产品下拉用）
export function getItemListApi(params: Record<string, any>) {
  return http.get<PageResult<any>>('/mes/md/item/list', params)
}

// 新建生产工单
export function createWorkOrderApi(data: Record<string, any>) {
  return http.post<number>('/mes/pro/workorder/save', data)
}

// 按订单排产（一次生成全部工序任务）
export function orderAdditionApi(workorderId: number | string) {
  return http.post<number>('/mes/pro/task/orderAddition', { workorderId })
}

// 工单详情
export function getWorkOrderDetailApi(id: number | string) {
  return http.get<any>(`/mes/pro/workorder/get/${id}`)
}

// 任务列表（复用）
export type { Task }