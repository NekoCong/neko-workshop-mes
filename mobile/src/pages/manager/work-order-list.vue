<template>
  <view class="order-page">
    <view class="order-list">
      <view v-for="item in list" :key="item.id" class="order-card">
        <view class="order-head">
          <text class="order-name">{{ item.workorderName || item.productName }}</text>
          <text class="status-tag">{{ item.status }}</text>
        </view>
        <view class="meta-row">
          <text class="lbl">工单号</text>
          <text class="val">{{ item.workorderCode }}</text>
        </view>
        <view class="meta-row">
          <text class="lbl">产品</text>
          <text class="val">{{ item.productName }}</text>
        </view>
        <view class="meta-row">
          <text class="lbl">数量</text>
          <text class="val">{{ item.quantity }} {{ item.unitOfMeasure }}</text>
        </view>
        <view class="meta-row">
          <text class="lbl">进度</text>
          <text class="val">{{ item.produceProgress || 0 }}%</text>
        </view>
        <!-- 操作区：未排产的工单可一键排产 -->
        <view class="order-actions">
          <button
            class="action-btn"
            :disabled="item.status !== 'UNSCHEDULED' && item.status !== 'SCHEDUL' && !item.status"
            @click="handleSchedule(item)"
          >排 产</button>
          <text class="action-state" v-if="item.status === 'COMPLETED'">已排产</text>
        </view>
      </view>

      <view v-if="list.length === 0 && !loading" class="empty">
        <text class="text-muted">暂无工单</text>
      </view>
    </view>
  </view>
</template>

<script setup lang="ts">
import { ref, onMounted } from 'vue'
import { onPullDownRefresh, onReachBottom } from '@dcloudio/uni-app'
import { http } from '@/utils/request'
import { orderAdditionApi } from '@/api/production'
import type { PageResult } from '@/types'

const loading = ref(false)
const list = ref<any[]>([])
const pageNo = ref(1)
const pageSize = 20
const total = ref(0)
const scheduling = ref(false)

// 一键排产：按订单整体生成全部工序任务
async function handleSchedule(item: any) {
  uni.showModal({
    title: '确认排产',
    content: `对工单「${item.workorderName || item.workorderCode}」进行排产？\n将按工艺路线自动生成全部工序任务。`,
    success: async (res) => {
      if (!res.confirm) return
      scheduling.value = true
      try {
        await orderAdditionApi(item.id)
        uni.showToast({ title: '排产成功', icon: 'success' })
        // 刷新列表
        pageNo.value = 1
        await loadList()
      } catch (e: any) {
        console.error('排产失败', e)
      } finally {
        scheduling.value = false
      }
    }
  })
}

async function loadList() {
  loading.value = true
  try {
    const res = await http.get<PageResult<any>>('/mes/pro/workorder/list', {
      pageNo: pageNo.value,
      pageSize
    })
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
.order-page {
  min-height: 100vh;
  padding: 24rpx;
}

.order-card {
  background: #fff;
  border-radius: 16rpx;
  padding: 28rpx;
  margin-bottom: 20rpx;
  box-shadow: 0 2rpx 12rpx rgba(0, 0, 0, 0.04);

  .order-head {
    display: flex;
    justify-content: space-between;
    align-items: center;
    margin-bottom: 20rpx;

    .order-name {
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

  .order-actions {
    display: flex;
    justify-content: flex-end;
    align-items: center;
    margin-top: 16rpx;
    padding-top: 16rpx;
    border-top: 1rpx solid #f5f5f5;

    .action-btn {
      height: 64rpx;
      line-height: 64rpx;
      font-size: 26rpx;
      background: #2d8cf0;
      color: #fff;
      border-radius: 32rpx;
      padding: 0 40rpx;
      border: none;
      margin: 0;

      &::after {
        border: none;
      }

      &[disabled] {
        background: #c0c4cc;
        color: #fff;
      }
    }

    .action-state {
      font-size: 26rpx;
      color: #67c23a;
    }
  }
}

.empty {
  text-align: center;
  padding: 120rpx 0;
}
</style>
