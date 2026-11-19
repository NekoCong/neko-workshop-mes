<template>
  <view class="report-page">
    <!-- 任务信息卡 -->
    <view class="info-card">
      <view class="info-title">{{ task.taskName || task.itemName || '生产任务' }}</view>
      <view class="info-rows">
        <view class="info-row">
          <text class="lbl">工单号</text>
          <text class="val">{{ task.workorderCode || '-' }}</text>
        </view>
        <view class="info-row">
          <text class="lbl">工序</text>
          <text class="val">{{ task.processName || task.processCode || '-' }}</text>
        </view>
        <view class="info-row">
          <text class="lbl">产品</text>
          <text class="val">{{ task.itemName || '-' }}</text>
        </view>
        <view class="info-row">
          <text class="lbl">排产数量</text>
          <text class="val">{{ task.quantity }} {{ task.unitOfMeasure || '' }}</text>
        </view>
        <view class="info-row">
          <text class="lbl">工作站</text>
          <text class="val">{{ task.workstationName || '-' }}</text>
        </view>
      </view>
    </view>

    <!-- 当前状态 -->
    <view class="status-banner" :class="'banner-' + currentStatus">
      <text class="status-text">当前状态：{{ statusText }}</text>
    </view>

    <!-- 报工数量输入（开工/报工时显示） -->
    <view class="qty-section" v-if="currentStatus === 'NoSTARTED' || currentStatus === 'FINISHED'">
      <text class="qty-label">报工数量</text>
      <input
        class="qty-input"
        type="number"
        v-model="quantity"
        placeholder="请输入报工数量"
      />
      <text class="qty-unit">{{ task.unitOfMeasure || '' }}</text>
    </view>

    <!-- 暂停原因（暂停时显示） -->
    <view class="pause-section" v-if="showPause">
      <text class="qty-label">暂停原因</text>
      <input
        class="pause-input"
        v-model="pauseReason"
        placeholder="请输入暂停原因"
      />
    </view>

    <!-- 操作按钮 -->
    <view class="actions">
      <!-- 未开工：开工 -->
      <button
        v-if="currentStatus === 'NoSTARTED'"
        class="btn btn-start"
        :loading="loading"
        @click="handleStart"
      >
        开 工
      </button>

      <!-- 进行中/已恢复：暂停 + 报工完成 -->
      <template v-if="currentStatus === 'STARTED' || currentStatus === 'RESUMED'">
        <button class="btn btn-pause" @click="openPause">暂 停</button>
        <button class="btn btn-finish" :loading="loading" @click="handleFinish">报工完成</button>
      </template>

      <!-- 已暂停：恢复 -->
      <button
        v-if="currentStatus === 'PAUSED'"
        class="btn btn-resume"
        :loading="loading"
        @click="handleResume"
      >
        恢 复
      </button>

      <!-- 已完成：提示 -->
      <view v-if="currentStatus === 'FINISHED'" class="finished-tip">
        <text>✓ 该任务已完成报工</text>
        <text class="text-muted">报工数量：{{ feedback?.quantity }}</text>
      </view>
    </view>
  </view>
</template>

<script setup lang="ts">
import { ref, computed } from 'vue'
import { onLoad } from '@dcloudio/uni-app'
import { getTaskDetailApi, getFeedbackListApi, insertFeedbackApi, updateFeedbackStatusApi } from '@/api'
import { useUserStore } from '@/store/user'
import type { Task, Feedback } from '@/types'

const userStore = useUserStore()
const task = ref<Task>({})
const feedback = ref<Feedback | null>(null)
const quantity = ref('')
const pauseReason = ref('')
const loading = ref(false)
const showPause = ref(false)

const taskCode = ref('')
const taskId = ref('')

const currentStatus = computed(() => {
  if (!feedback.value) return 'NoSTARTED'
  return feedback.value.status || 'NoSTARTED'
})

const statusText = computed(() => {
  const map: Record<string, string> = {
    NoSTARTED: '未开工',
    STARTED: '进行中',
    PAUSED: '已暂停',
    RESUMED: '已恢复',
    FINISHED: '已完成'
  }
  return map[currentStatus.value] || currentStatus.value
})

onLoad(async (options: any) => {
  taskCode.value = options?.taskCode || ''
  taskId.value = options?.taskId || ''
  await loadData()
})

async function loadData() {
  try {
    // 查任务详情
    if (taskId.value) {
      task.value = await getTaskDetailApi(taskId.value)
    }
    // 查该任务的报工记录
    const res = await getFeedbackListApi({ taskCode: taskCode.value, pageNo: 1, pageSize: 1 })
    const list = res.list || []
    feedback.value = list.length > 0 ? list[0] : null
    if (feedback.value) {
      quantity.value = String(feedback.value.quantity || '')
    }
  } catch (e) {
    console.error(e)
  }
}

function validateQuantity(): boolean {
  if (!quantity.value || Number(quantity.value) <= 0) {
    uni.showToast({ title: '请输入报工数量', icon: 'none' })
    return false
  }
  return true
}

// 开工
async function handleStart() {
  if (!validateQuantity()) return
  loading.value = true
  try {
    await insertFeedbackApi({
      taskCode: taskCode.value,
      quantity: quantity.value,
      status: 'STARTED',
      userName: userStore.nickname || userStore.id ? '工人' : '',
      equipmentCode: []
    })
    uni.showToast({ title: '已开工', icon: 'success' })
    await loadData()
  } catch (e) {
    console.error(e)
  } finally {
    loading.value = false
  }
}

function openPause() {
  showPause.value = true
}

// 暂停
async function handlePause() {
  if (!pauseReason.value.trim()) {
    uni.showToast({ title: '请输入暂停原因', icon: 'none' })
    return
  }
  loading.value = true
  try {
    await updateFeedbackStatusApi({
      taskCode: taskCode.value,
      status: 'PAUSED',
      pauseReason: pauseReason.value,
      userName: userStore.nickname
    })
    showPause.value = false
    pauseReason.value = ''
    uni.showToast({ title: '已暂停', icon: 'success' })
    await loadData()
  } catch (e) {
    console.error(e)
  } finally {
    loading.value = false
  }
}

// 恢复
async function handleResume() {
  loading.value = true
  try {
    await updateFeedbackStatusApi({
      taskCode: taskCode.value,
      status: 'RESUMED',
      userName: userStore.nickname
    })
    uni.showToast({ title: '已恢复', icon: 'success' })
    await loadData()
  } catch (e) {
    console.error(e)
  } finally {
    loading.value = false
  }
}

// 报工完成
async function handleFinish() {
  if (!validateQuantity()) return
  loading.value = true
  try {
    await updateFeedbackStatusApi({
      taskCode: taskCode.value,
      quantity: quantity.value,
      status: 'FINISHED',
      userName: userStore.nickname,
      equipmentCode: []
    })
    uni.showToast({ title: '报工完成', icon: 'success' })
    await loadData()
  } catch (e) {
    console.error(e)
  } finally {
    loading.value = false
  }
}
</script>

<style lang="scss" scoped>
.report-page {
  min-height: 100vh;
  padding: 24rpx;
  padding-bottom: 60rpx;
}

.info-card {
  background: #fff;
  border-radius: 16rpx;
  padding: 32rpx;
  box-shadow: 0 2rpx 12rpx rgba(0, 0, 0, 0.04);

  .info-title {
    font-size: 36rpx;
    font-weight: 600;
    color: #303133;
    margin-bottom: 24rpx;
  }

  .info-row {
    display: flex;
    margin-bottom: 16rpx;

    .lbl {
      width: 160rpx;
      font-size: 28rpx;
      color: #909399;
    }

    .val {
      flex: 1;
      font-size: 28rpx;
      color: #303133;
    }
  }
}

.status-banner {
  margin-top: 24rpx;
  padding: 24rpx 32rpx;
  border-radius: 16rpx;
  text-align: center;

  .status-text {
    font-size: 30rpx;
    font-weight: 600;
  }

  &.banner-NoSTARTED {
    background: #f4f4f5;
    .status-text { color: #909399; }
  }
  &.banner-STARTED,
  &.banner-RESUMED {
    background: #ecf5ff;
    .status-text { color: #409eff; }
  }
  &.banner-PAUSED {
    background: #fdf6ec;
    .status-text { color: #e6a23c; }
  }
  &.banner-FINISHED {
    background: #f0f9eb;
    .status-text { color: #67c23a; }
  }
}

.qty-section {
  display: flex;
  align-items: center;
  margin-top: 24rpx;
  background: #fff;
  border-radius: 16rpx;
  padding: 28rpx 32rpx;

  .qty-label {
    font-size: 30rpx;
    color: #303133;
    margin-right: 24rpx;
  }

  .qty-input {
    flex: 1;
    font-size: 34rpx;
    font-weight: 600;
    color: #303133;
  }

  .qty-unit {
    font-size: 26rpx;
    color: #909399;
  }
}

.pause-section {
  display: flex;
  align-items: center;
  margin-top: 24rpx;
  background: #fff;
  border-radius: 16rpx;
  padding: 28rpx 32rpx;

  .qty-label {
    font-size: 30rpx;
    color: #303133;
    margin-right: 24rpx;
    width: 120rpx;
  }

  .pause-input {
    flex: 1;
    font-size: 28rpx;
  }
}

.actions {
  margin-top: 40rpx;

  .btn {
    height: 100rpx;
    line-height: 100rpx;
    border-radius: 50rpx;
    font-size: 34rpx;
    font-weight: 600;
    color: #fff;
    border: none;
    margin-bottom: 24rpx;

    &::after {
      border: none;
    }

    &[disabled] {
      opacity: 0.6;
      color: #fff;
    }
  }

  .btn-start {
    background: #2d8cf0;
  }
  .btn-pause {
    background: #e6a23c;
  }
  .btn-resume {
    background: #2d8cf0;
  }
  .btn-finish {
    background: #67c23a;
  }

  .finished-tip {
    text-align: center;
    padding: 40rpx 0;

    text {
      display: block;
      font-size: 32rpx;
      margin-bottom: 12rpx;
    }
  }
}
</style>
