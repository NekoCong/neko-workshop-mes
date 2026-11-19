<template>
  <view class="task-page">
    <!-- 顶部统计 -->
    <view class="stats-bar">
      <view class="stat-item" @click="switchTab('todo')">
        <text class="stat-num">{{ counts.todo }}</text>
        <text class="stat-label">待报工</text>
      </view>
      <view class="stat-item" @click="switchTab('doing')">
        <text class="stat-num text-primary">{{ counts.doing }}</text>
        <text class="stat-label">进行中</text>
      </view>
      <view class="stat-item" @click="switchTab('done')">
        <text class="stat-num text-success">{{ counts.done }}</text>
        <text class="stat-label">已完成</text>
      </view>
    </view>

    <!-- 筛选 tab -->
    <view class="tab-bar">
      <view
        v-for="tab in tabs"
        :key="tab.value"
        class="tab-item"
        :class="{ active: activeTab === tab.value }"
        @click="switchTab(tab.value)"
      >
        {{ tab.label }}
      </view>
    </view>

    <!-- 任务列表 -->
    <view class="task-list">
      <view v-for="item in list" :key="item.id" class="task-card" @click="goReport(item)">
        <view class="task-head">
          <text class="task-name">{{ item.taskName || item.itemName }}</text>
          <text class="status-tag" :class="statusClass(item)">{{ statusText(item) }}</text>
        </view>
        <view class="task-meta">
          <view class="meta-row">
            <text class="meta-label">工单号</text>
            <text class="meta-value">{{ item.workorderCode }}</text>
          </view>
          <view class="meta-row">
            <text class="meta-label">工序</text>
            <text class="meta-value">{{ item.processName || item.processCode }}</text>
          </view>
          <view class="meta-row">
            <text class="meta-label">数量</text>
            <text class="meta-value">{{ item.quantity }} {{ item.unitOfMeasure }}</text>
          </view>
          <view class="meta-row">
            <text class="meta-label">工作站</text>
            <text class="meta-value">{{ item.workstationName || '-' }}</text>
          </view>
        </view>
        <view class="task-foot">
          <text class="text-muted">任务号：{{ item.taskCode }}</text>
          <text class="arrow">报工 ›</text>
        </view>
      </view>

      <view v-if="list.length === 0 && !loading" class="empty">
        <text class="text-muted">暂无任务</text>
      </view>
    </view>
  </view>
</template>

<script setup lang="ts">
import { ref, reactive, onMounted } from 'vue'
import { onPullDownRefresh } from '@dcloudio/uni-app'
import { getTaskPageApi } from '@/api'
import type { Task } from '@/types'

const loading = ref(false)
const list = ref<Task[]>([])
const activeTab = ref('todo')

const tabs = [
  { label: '全部', value: 'all' },
  { label: '待报工', value: 'todo' },
  { label: '进行中', value: 'doing' },
  { label: '已完成', value: 'done' }
]

const counts = reactive({ todo: 0, doing: 0, done: 0 })

function statusText(item: Task): string {
  const status = item.status || ''
  if (status === 'FINISHED' || status === 'COMPLETED') return '已完成'
  if (status === 'STARTED' || status === 'RESUMED') return '进行中'
  if (status === 'PAUSED') return '已暂停'
  return '待报工'
}

function statusClass(item: Task): string {
  const status = item.status || ''
  if (status === 'FINISHED' || status === 'COMPLETED') return 'status-done'
  if (status === 'PAUSED') return 'status-paused'
  return 'status-doing'
}

function switchTab(tab: string) {
  activeTab.value = tab
  loadList()
}

async function loadList() {
  loading.value = true
  try {
    const params: Record<string, any> = {
      pageNo: 1,
      pageSize: 100
    }
    // 按状态筛选（任务表的 status 字段）
    if (activeTab.value === 'todo') params.status = 'NORMARL'
    if (activeTab.value === 'done') params.status = 'COMPLETED'

    const res = await getTaskPageApi(params)
    let items = res.list || []
    list.value = items

    // 统计
    counts.todo = items.filter((t) => t.status === 'NORMARL').length
    counts.doing = items.filter((t) => ['STARTED', 'RESUMED', 'PAUSED'].includes(t.status || '')).length
    counts.done = items.filter((t) => ['FINISHED', 'COMPLETED'].includes(t.status || '')).length
  } catch (e) {
    console.error(e)
  } finally {
    loading.value = false
  }
}

function goReport(item: Task) {
  uni.navigateTo({
    url: `/pages/worker/report?taskCode=${encodeURIComponent(item.taskCode || '')}&taskId=${item.id}`
  })
}

onMounted(() => {
  loadList()
})

onPullDownRefresh(async () => {
  await loadList()
  uni.stopPullDownRefresh()
})
</script>

<style lang="scss" scoped>
.task-page {
  min-height: 100vh;
  padding-bottom: 40rpx;
}

.stats-bar {
  display: flex;
  background: #fff;
  padding: 30rpx 0;
  border-bottom: 1rpx solid #f0f0f0;

  .stat-item {
    flex: 1;
    text-align: center;

    .stat-num {
      display: block;
      font-size: 44rpx;
      font-weight: 600;
      color: #303133;
    }

    .stat-label {
      display: block;
      margin-top: 8rpx;
      font-size: 24rpx;
      color: #909399;
    }
  }
}

.tab-bar {
  display: flex;
  background: #fff;
  padding: 0 20rpx;

  .tab-item {
    padding: 24rpx 20rpx;
    margin-right: 20rpx;
    font-size: 28rpx;
    color: #606266;
    position: relative;

    &.active {
      color: #2d8cf0;
      font-weight: 600;

      &::after {
        content: '';
        position: absolute;
        left: 50%;
        bottom: 8rpx;
        transform: translateX(-50%);
        width: 40rpx;
        height: 6rpx;
        border-radius: 3rpx;
        background: #2d8cf0;
      }
    }
  }
}

.task-list {
  padding: 20rpx;
}

.task-card {
  background: #fff;
  border-radius: 16rpx;
  padding: 28rpx;
  margin-bottom: 20rpx;
  box-shadow: 0 2rpx 12rpx rgba(0, 0, 0, 0.04);

  .task-head {
    display: flex;
    justify-content: space-between;
    align-items: center;
    margin-bottom: 20rpx;

    .task-name {
      font-size: 32rpx;
      font-weight: 600;
      color: #303133;
    }

    .status-tag {
      font-size: 24rpx;
      padding: 6rpx 20rpx;
      border-radius: 20rpx;

      &.status-doing {
        background: #ecf5ff;
        color: #409eff;
      }
      &.status-done {
        background: #f0f9eb;
        color: #67c23a;
      }
      &.status-paused {
        background: #fdf6ec;
        color: #e6a23c;
      }
    }
  }

  .task-meta {
    .meta-row {
      display: flex;
      margin-bottom: 12rpx;

      .meta-label {
        width: 140rpx;
        font-size: 26rpx;
        color: #909399;
      }

      .meta-value {
        flex: 1;
        font-size: 26rpx;
        color: #303133;
      }
    }
  }

  .task-foot {
    display: flex;
    justify-content: space-between;
    align-items: center;
    padding-top: 20rpx;
    border-top: 1rpx solid #f5f5f5;

    .arrow {
      font-size: 28rpx;
      color: #2d8cf0;
    }
  }
}

.empty {
  text-align: center;
  padding: 120rpx 0;
}
</style>
