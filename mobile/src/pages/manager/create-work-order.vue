<template>
  <view class="create-page">
    <!-- 工单信息 -->
    <view class="card">
      <view class="card-title">工单信息</view>
      <view class="form-item">
        <text class="label req">工单编号</text>
        <input class="input" v-model="form.workorderCode" placeholder="默认自动生成（MO+时间）" />
      </view>
      <view class="form-item">
        <text class="label req">工单名称</text>
        <input class="input" v-model="form.workorderName" placeholder="请输入工单名称" />
      </view>
      <view class="form-item">
        <text class="label req">产品</text>
        <picker :range="productList" range-key="itemName" @change="onProductChange">
          <view class="picker-value" :class="{ placeholder: !form.productCode }">
            {{ form.productName || '请选择产品' }}
          </view>
        </picker>
      </view>
      <view class="form-item">
        <text class="label req">数量</text>
        <input class="input" type="number" v-model="form.quantity" placeholder="请输入生产数量" />
      </view>
      <view class="form-item">
        <text class="label">单位</text>
        <input class="input" v-model="form.unitOfMeasure" placeholder="如：件 / 个 / kg" />
      </view>
      <view class="form-item">
        <text class="label">客户</text>
        <input class="input" v-model="form.clientName" placeholder="请输入客户名称（可选）" />
      </view>
      <view class="form-item">
        <text class="label">交付日期</text>
        <picker mode="date" @change="onDateChange">
          <view class="picker-value" :class="{ placeholder: !form.requestDate }">
            {{ form.requestDate || '请选择交付日期' }}
          </view>
        </picker>
      </view>
      <view class="form-item">
        <text class="label">备注</text>
        <input class="input" v-model="form.remark" placeholder="备注（可选）" />
      </view>
    </view>

    <!-- 提交 -->
    <button class="submit-btn" :loading="loading" :disabled="loading" @click="handleSubmit">
      创建工单
    </button>
  </view>
</template>

<script setup lang="ts">
import { ref, reactive, onMounted } from 'vue'
import { getItemListApi, createWorkOrderApi } from '@/api/production'

const loading = ref(false)
const productList = ref<any[]>([])
const productOptions = ref<any[]>([])

const form = reactive({
  workorderCode: '',
  workorderName: '',
  productId: undefined as number | undefined,
  productCode: '',
  productName: '',
  unitOfMeasure: '',
  quantity: '',
  clientName: '',
  requestDate: '',
  remark: ''
})

// 生成唯一工单编号：MO + 年月日时分秒（如 MO202611192205）
function genWorkOrderCode(): string {
  const d = new Date()
  const pad = (n: number) => String(n).padStart(2, '0')
  return `MO${d.getFullYear()}${pad(d.getMonth() + 1)}${pad(d.getDate())}${pad(d.getHours())}${pad(d.getMinutes())}${pad(d.getSeconds())}`
}

// 加载产品下拉
async function loadProducts() {
  try {
    const res = await getItemListApi({ pageNo: 1, pageSize: 100 })
    const list = res.list || []
    productOptions.value = list
    // 只取「产品」类型（itemOrProduct = 'PRODUCT' 或 'P'）
    productList.value = list.filter((it: any) => {
      const type = String(it.itemOrProduct || '').toUpperCase()
      return type === 'PRODUCT' || type === 'P' || type === ''
    })
    if (productList.value.length === 0) {
      productList.value = list
    }
  } catch (e) {
    console.error('加载产品失败', e)
  }
}

function onProductChange(e: any) {
  const idx = Number(e.detail.value)
  const item = productList.value[idx]
  if (item) {
    form.productId = item.id
    form.productCode = item.itemCode
    form.productName = item.itemName
    if (!form.unitOfMeasure) {
      form.unitOfMeasure = item.unitOfMeasure || ''
    }
  }
}

function onDateChange(e: any) {
  form.requestDate = e.detail.value
}

// 格式化日期为 yyyy-MM-dd
function formatDate(d: Date): string {
  const pad = (n: number) => String(n).padStart(2, '0')
  return `${d.getFullYear()}-${pad(d.getMonth() + 1)}-${pad(d.getDate())}`
}

// 格式化日期时间为 yyyy-MM-dd HH:mm:ss（后端日期字段格式）
function formatDateTime(d: Date): string {
  const pad = (n: number) => String(n).padStart(2, '0')
  return `${d.getFullYear()}-${pad(d.getMonth() + 1)}-${pad(d.getDate())} ${pad(d.getHours())}:${pad(d.getMinutes())}:${pad(d.getSeconds())}`
}

async function handleSubmit() {
  if (!form.workorderName.trim()) {
    uni.showToast({ title: '请输入工单名称', icon: 'none' })
    return
  }
  if (!form.productId) {
    uni.showToast({ title: '请选择产品', icon: 'none' })
    return
  }
  if (!form.quantity || Number(form.quantity) <= 0) {
    uni.showToast({ title: '请输入正确的数量', icon: 'none' })
    return
  }

  loading.value = true
  try {
    // 未填编号则自动生成（唯一）
    const code = form.workorderCode.trim() || genWorkOrderCode()
    // 交付日期必填（后端对空字符串日期会解析失败），未选则默认今天+7天
    const requestDate = form.requestDate || formatDate(new Date(Date.now() + 7 * 24 * 3600 * 1000))
    // 订单日期/投产日期：后端要求非空，默认取当天
    const today = formatDateTime(new Date())
    await createWorkOrderApi({
      workorderCode: code,
      workorderName: form.workorderName.trim(),
      orderSource: 'STORE',
      productId: form.productId,
      productCode: form.productCode,
      productName: form.productName,
      quantity: Number(form.quantity),
      unitOfMeasure: form.unitOfMeasure,
      clientName: form.clientName,
      orderDate: today,
      produceDate: today,
      requestDate,
      remark: form.remark,
      status: 'UNSCHEDULED',
      parentId: 0,
      ancestors: '0'
    })
    uni.showToast({ title: '创建成功', icon: 'success' })
    setTimeout(() => {
      uni.navigateBack()
    }, 800)
  } catch (e: any) {
    console.error('创建工单失败', e)
  } finally {
    loading.value = false
  }
}

onMounted(() => {
  // 默认生成一个工单编号
  form.workorderCode = genWorkOrderCode()
  // 默认交付日期：今天+7天
  form.requestDate = formatDate(new Date(Date.now() + 7 * 24 * 3600 * 1000))
  loadProducts()
})
</script>

<style lang="scss" scoped>
.create-page {
  min-height: 100vh;
  padding: 24rpx;
  padding-bottom: 120rpx;
}

.card {
  background: #fff;
  border-radius: 16rpx;
  padding: 32rpx;
  box-shadow: 0 2rpx 12rpx rgba(0, 0, 0, 0.04);

  .card-title {
    font-size: 32rpx;
    font-weight: 600;
    color: #303133;
    margin-bottom: 24rpx;
    padding-bottom: 20rpx;
    border-bottom: 1rpx solid #f5f5f5;
  }
}

.form-item {
  display: flex;
  align-items: center;
  padding: 24rpx 0;
  border-bottom: 1rpx solid #f5f5f5;

  &:last-child {
    border-bottom: none;
  }

  .label {
    width: 160rpx;
    font-size: 28rpx;
    color: #303133;

    &.req::before {
      content: '*';
      color: #f56c6c;
      margin-right: 4rpx;
    }
  }

  .input {
    flex: 1;
    font-size: 28rpx;
    color: #303133;
  }

  .picker-value {
    flex: 1;
    font-size: 28rpx;
    color: #303133;

    &.placeholder {
      color: #c0c4cc;
    }
  }
}

.submit-btn {
  margin-top: 40rpx;
  height: 96rpx;
  line-height: 96rpx;
  background: #2d8cf0;
  color: #fff;
  font-size: 34rpx;
  font-weight: 600;
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
</style>