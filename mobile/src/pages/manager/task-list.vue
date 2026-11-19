<template>
  <view class="task-page">
    <view class="task-list">
      <view v-for="item in list" :key="item.id" class="task-card">
        <view class="task-head">
          <text class="task-name">{{ item.taskName || item.itemName }}</text>
          <text class="status-tag">{{ item.status }}</text>
        </view>
        <view class="meta-row">
          <text class="lbl">任务号</text>
          <text class="val">{{ item.taskCode }}</text>
        </view>
        <view class="meta-row">
          <text class="lbl">工单号</text>
          <text class="val">{{ item.workorderCode }}</text>
        </view>
        <view class="meta-row">
          <text class="lbl">工序</text>
          <text class="val">{{ item.processName || item.processCode }}</text>
        </view>
        <view class="meta-row">
          <text class="lbl">数量</text>
          <text class="val">{{ item.quantity }} / 已报 {{ item.quantityFeedback || 0 }} {{ item.unitOfMeasure }}</text>
        </view>
        <view class="meta-row">
          <text class="lbl">进度</text>
          <text class="val">{{ item.schedule || 0 }}%</text>
        </view>
        <view class="meta-row">
          <text class="lbl">工作站</text>
          <text class="val">{{ item.workstationName || '-' }}</text>
        </view>
      </view>

      <view v-if="list.length === 0 && !loading" class="empty">
        <text class="text-muted">暂无任务</text>
      </view>
    </view>
  </view>
</template>

<script setup lang="ts">
import { ref, onMounted } from 'vue'
import { onPullDownRefresh, onReachBottom } from '@dcloudio/uni-app'
import { getTaskPageApi } from '@/api'
import type { Task } from '@/types'

const loading = ref(false)
const list = ref<Task[]>([])
const pageNo = ref(1)
const pageSize = 20
const total = ref(0)

async function loadList() {
  loading.value = true
  try {
    const res = await getTaskPageApi({ pageNo: pageNo.value, pageSize })
    const items = res.list || []
    if (pageNo.value === 1) {
      list.value = items
    } else {
      list.value = list.value.concat(items)
    }
    total.value = res.total
  } catch (e) {
    console.error(e)
  } finally {
    loading.value = false
  }
}

onMounted(() => loadList())

onPullDownRefresh(async () => {
  pageNo.value = 1
  await loadList()
  uni.stopPullDownRefresh()
})

onReachBottom(() => {
  if (list.value.length < total.value) {
    pageNo.value++
    loadList()
  }
})
</script>

<style lang="scss" scoped>
.task-page {
  min-height: 100vh;
  padding: 24rpx;
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
      background: #ecf5ff;
      color: #409eff;
    }
  }

  .meta-row {
    display: flex;
    margin-bottom: 12rpx;

    .lbl {
      width: 140rpx;
      font-size: 26rpx;
      color: #909399;
    }

    .val {
      flex: 1;
      font-size: 26rpx;
      color: #303133;
    }
  }
}

.empty {
  text-align: center;
  padding: 120rpx 0;
}
</style>
