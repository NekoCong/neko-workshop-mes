// 后端统一响应结构
export interface CommonResult<T = any> {
  code: number
  data: T
  msg: string
}

// 分页结果
export interface PageResult<T> {
  list: T[]
  total: number
}

// 登录响应
export interface LoginResp {
  userId: number
  accessToken: string
  refreshToken: string
  expiresTime: string
}

// 权限信息
export interface PermissionInfo {
  user: {
    id: number
    nickname: string
    avatar: string
  }
  roles: string[]
  permissions: string[]
}

// 生产任务（pro_task）
export interface Task {
  id?: number
  taskCode?: string
  taskName?: string
  workorderId?: number
  workorderCode?: string
  workorderName?: string
  itemId?: number
  itemCode?: string
  itemName?: string
  specification?: string
  unitOfMeasure?: string
  quantity?: number | string
  quantityFeedback?: number | string
  residueQuantity?: number | string
  processId?: number
  processCode?: string
  processName?: string
  workstationId?: number
  workstationCode?: string
  workstationName?: string
  machineryCode?: string
  machineryName?: string
  teamCode?: string
  teamName?: string
  schedule?: number | string
  scheduleType?: string
  status?: string
  startTime?: string
  endTime?: string
  requestDate?: string
  barcodeFormat?: string
}

// 报工记录（pro_feedback）
export interface Feedback {
  id?: number
  workstationId?: number
  workstationCode?: string
  workstationName?: string
  workorderId?: number
  workorderCode?: string
  workorderName?: string
  taskId?: number
  taskCode?: string
  itemId?: number
  itemCode?: string
  itemName?: string
  unitOfMeasure?: string
  quantity?: number | string
  userName?: string
  feedbackTime?: string
  status?: string
  processId?: number
  processCode?: string
  processName?: string
  equipmentCode?: string
  finishedTime?: string
  pauseTime?: string
  orderQuantity?: number | string
  pauseReason?: string
  workOrderQuentity?: number | string
  reportingProgress?: number | string
  assignUsername?: string
}

// 报工请求（ProfeedBackVo）
export interface FeedbackReq {
  taskCode?: string
  quantity?: number | string
  equipmentCode?: Array<Record<string, string>>
  workHour?: string
  ipAddress?: string
  userName?: string
  status?: string
  pauseReason?: string
}

// 报工状态枚举（与后端 PRODUCE_STATE 对应）
export const FEEDBACK_STATUS = {
  NoSTARTED: 'NoSTARTED',
  STARTED: 'STARTED',
  PAUSED: 'PAUSED',
  RESUMED: 'RESUMED',
  FINISHED: 'FINISHED'
} as const

export const FEEDBACK_STATUS_TEXT: Record<string, string> = {
  NoSTARTED: '未开工',
  STARTED: '进行中',
  PAUSED: '已暂停',
  RESUMED: '已恢复',
  FINISHED: '已完成'
}
