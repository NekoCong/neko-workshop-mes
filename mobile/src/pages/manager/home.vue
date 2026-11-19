<template>
  <view class="home-page">
    <!-- 欢迎 + 退出 -->
    <view class="header">
      <view>
        <text class="welcome">你好，{{ userStore.nickname || '管理员' }}</text>
        <text class="role">生产管理</text>
      </view>
      <view class="logout" @click="handleLogout">退出</view>
    </view>

    <!-- 统计卡片 -->
    <view class="stats">
      <view class="stat-card" @click="go('/pages/manager/work-order-list')">
        <text class="num">工单</text>
        <text class="label">生产工单</text>
      </view>
      <view class="stat-card" @click="go('/pages/manager/task-list')">
        <text class="num">任务</text>
        <text class="label">生产任务</text>
      </view>
      <view class="stat-card" @click="go('/pages/worker/task-list')">
        <text class="num">报工</text>
        <text class="label">生产报工</text>
      </view>
    </view>

    <!-- 功能入口 -->
    <view class="menu">
      <view class="menu-item" @click="go('/pages/manager/create-work-order')">
        <text class="menu-icon">➕</text>
        <text class="menu-label">新建生产工单</text>
        <text class="arrow">›</text>
      </view>
      <view class="menu-item" @click="go('/pages/manager/work-order-list')">
        <text class="menu-icon">📋</text>
        <text class="menu-label">生产工单（排产）</text>
        <text class="arrow">›</text>
      </view>
      <view class="menu-item" @click="go('/pages/manager/task-list')">
        <text class="menu-icon">📌</text>
        <text class="menu-label">生产任务</text>
        <text class="arrow">›</text>
      </view>
      <view class="menu-item" @click="go('/pages/worker/task-list')">
        <text class="menu-icon">✅</text>
        <text class="menu-label">生产报工</text>
        <text class="arrow">›</text>
      </view>
    </view>

    <view class="tips text-muted">
      移动端生产管理为简化版，完整功能请使用 PC 端
    </view>
  </view>
</template>

<script setup lang="ts">
import { useUserStore } from '@/store/user'

const userStore = useUserStore()

function go(url: string) {
  uni.navigateTo({ url })
}

function handleLogout() {
  uni.showModal({
    title: '提示',
    content: '确认退出登录？',
    success: (res) => {
      if (res.confirm) {
        userStore.logout()
        uni.reLaunch({ url: '/pages/login/index' })
      }
    }
  })
}
</script>

<style lang="scss" scoped>
.home-page {
  min-height: 100vh;
  padding: 24rpx;
}

.header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 20rpx 0 30rpx;

  .welcome {
    display: block;
    font-size: 38rpx;
    font-weight: 600;
    color: #303133;
  }

  .role {
    display: block;
    margin-top: 8rpx;
    font-size: 26rpx;
    color: #909399;
  }

  .logout {
    font-size: 28rpx;
    color: #f56c6c;
    padding: 12rpx 24rpx;
  }
}

.stats {
  display: flex;
  gap: 20rpx;
  margin-bottom: 24rpx;

  .stat-card {
    flex: 1;
    background: #fff;
    border-radius: 16rpx;
    padding: 32rpx 0;
    text-align: center;
    box-shadow: 0 2rpx 12rpx rgba(0, 0, 0, 0.04);

    .num {
      display: block;
      font-size: 40rpx;
      font-weight: 600;
      color: #2d8cf0;
    }

    .label {
      display: block;
      margin-top: 8rpx;
      font-size: 24rpx;
      color: #909399;
    }
  }
}

.menu {
  background: #fff;
  border-radius: 16rpx;
  overflow: hidden;
  box-shadow: 0 2rpx 12rpx rgba(0, 0, 0, 0.04);

  .menu-item {
    display: flex;
    align-items: center;
    padding: 32rpx;
    border-bottom: 1rpx solid #f5f5f5;

    &:last-child {
      border-bottom: none;
    }

    .menu-icon {
      font-size: 40rpx;
      margin-right: 24rpx;
    }

    .menu-label {
      flex: 1;
      font-size: 30rpx;
      color: #303133;
    }

    .arrow {
      font-size: 32rpx;
      color: #c0c4cc;
    }
  }
}

.tips {
  margin-top: 40rpx;
  text-align: center;
  font-size: 24rpx;
}
</style>
