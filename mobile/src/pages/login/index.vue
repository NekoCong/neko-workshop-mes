<template>
  <view class="login-page">
    <view class="login-header">
      <view class="logo">MES</view>
      <view class="title">灵喵工坊MES</view>
      <view class="subtitle">生产报工移动端</view>
    </view>

    <view class="login-form">
      <view class="form-item">
        <text class="label">账号</text>
        <input
          class="input"
          v-model="username"
          placeholder="请输入账号"
          placeholder-class="placeholder"
        />
      </view>
      <view class="form-item">
        <text class="label">密码</text>
        <input
          class="input"
          v-model="password"
          type="password"
          placeholder="请输入密码"
          placeholder-class="placeholder"
        />
      </view>

      <button class="login-btn" :loading="loading" :disabled="loading" @click="handleLogin">
        登 录
      </button>

      <view class="tips">
        <text class="text-muted">测试账号：worker01（工人）/ manager01（管理）</text>
      </view>
    </view>
  </view>
</template>

<script setup lang="ts">
import { ref } from 'vue'
import { useUserStore } from '@/store/user'

const userStore = useUserStore()
const username = ref('worker01')
const password = ref('111111')
const loading = ref(false)

async function handleLogin() {
  if (!username.value.trim()) {
    uni.showToast({ title: '请输入账号', icon: 'none' })
    return
  }
  if (!password.value) {
    uni.showToast({ title: '请输入密码', icon: 'none' })
    return
  }

  loading.value = true
  try {
    await userStore.login(username.value.trim(), password.value)
    await userStore.loadPermission()

    uni.showToast({ title: '登录成功', icon: 'success' })

    // 按角色跳转
    if (userStore.isManager) {
      uni.reLaunch({ url: '/pages/manager/home' })
    } else {
      uni.reLaunch({ url: '/pages/worker/task-list' })
    }
  } catch (e: any) {
    // 错误提示已在 request 封装里处理
    console.error('登录失败', e)
  } finally {
    loading.value = false
  }
}
</script>

<style lang="scss" scoped>
.login-page {
  min-height: 100vh;
  background: linear-gradient(180deg, #2d8cf0 0%, #5cadff 40%, #f5f6f8 40.1%);
  padding: 0 60rpx;
}

.login-header {
  padding-top: 160rpx;
  padding-bottom: 80rpx;
  text-align: center;
  color: #fff;

  .logo {
    width: 140rpx;
    height: 140rpx;
    line-height: 140rpx;
    margin: 0 auto 30rpx;
    background: rgba(255, 255, 255, 0.2);
    border-radius: 32rpx;
    font-size: 56rpx;
    font-weight: bold;
    letter-spacing: 2rpx;
  }

  .title {
    font-size: 44rpx;
    font-weight: 600;
  }

  .subtitle {
    margin-top: 12rpx;
    font-size: 26rpx;
    opacity: 0.85;
  }
}

.login-form {
  background: #fff;
  border-radius: 24rpx;
  padding: 60rpx 40rpx;
  box-shadow: 0 8rpx 30rpx rgba(45, 140, 240, 0.15);

  .form-item {
    display: flex;
    align-items: center;
    height: 100rpx;
    border-bottom: 1rpx solid #eee;

    .label {
      width: 120rpx;
      font-size: 30rpx;
      color: #303133;
    }

    .input {
      flex: 1;
      font-size: 30rpx;
      color: #303133;
    }

    .placeholder {
      color: #c0c4cc;
    }
  }

  .login-btn {
    margin-top: 60rpx;
    height: 96rpx;
    line-height: 96rpx;
    background: #2d8cf0;
    color: #fff;
    font-size: 34rpx;
    border-radius: 48rpx;
    border: none;

    &::after {
      border: none;
    }

    &[disabled] {
      opacity: 0.6;
      color: #fff;
    }
  }

  .tips {
    margin-top: 30rpx;
    text-align: center;
    font-size: 24rpx;
  }
}
</style>
