// 依赖预构建配置
// 说明：Vite 4 只对列在 include 里的依赖做预构建。
// 若某个依赖在运行时才第一次被用到，Vite 会重新预构建并强制整页 reload
// （控制台表现为：[vite] new dependencies optimized: xxx / optimized dependencies changed. reloading），
// 用户侧看到的就是页面突然闪一下、回到首屏加载动画。
// element-plus 的组件样式尤其容易踩到这个问题：页面是懒加载的，点开新菜单才会 import 某个组件，
// 所以这里把 element-plus 全部组件的 style/css 一次性列出（共 105 个），避免运行时触发 reload。
const elementPlusStyleCss = [
  'affix', 'alert', 'aside', 'autocomplete', 'avatar', 'backtop', 'badge', 'base',
  'breadcrumb', 'breadcrumb-item', 'button', 'button-group', 'calendar', 'card',
  'carousel', 'carousel-item', 'cascader', 'cascader-panel', 'checkbox',
  'checkbox-button', 'checkbox-group', 'check-tag', 'col', 'collapse',
  'collapse-item', 'collapse-transition', 'color-picker', 'config-provider',
  'container', 'countdown', 'date-picker', 'descriptions', 'descriptions-item',
  'dialog', 'divider', 'drawer', 'dropdown', 'dropdown-item', 'dropdown-menu',
  'empty', 'footer', 'form', 'form-item', 'header', 'icon', 'image',
  'image-viewer', 'infinite-scroll', 'input', 'input-number', 'link', 'loading',
  'main', 'menu', 'menu-item', 'menu-item-group', 'message', 'message-box',
  'notification', 'option', 'option-group', 'overlay', 'page-header',
  'pagination', 'popconfirm', 'popover', 'popper', 'progress', 'radio',
  'radio-button', 'radio-group', 'rate', 'result', 'row', 'scrollbar', 'select',
  'select-v2', 'skeleton', 'skeleton-item', 'slider', 'space', 'statistic',
  'step', 'steps', 'sub-menu', 'switch', 'table', 'table-column', 'table-v2',
  'tab-pane', 'tabs', 'tag', 'teleport', 'text', 'timeline', 'timeline-item',
  'time-picker', 'time-select', 'tooltip', 'transfer', 'tree', 'tree-select',
  'tree-v2', 'upload', 'virtual-list'
].map((name) => `element-plus/es/components/${name}/style/css`)

const include = [
  'qs',
  'url',
  'vue',
  'sass',
  'mitt',
  'axios',
  'pinia',
  'dayjs',
  'qrcode',
  'windicss',
  'vue-router',
  'vue-types',
  'vue-i18n',
  'xe-utils',
  'crypto-js',
  'cropperjs',
  'lodash-es',
  'nprogress',
  'animate.css',
  'vxe-table',
  'vxe-table/es/style',
  'vxe-table/lib/locale/lang/zh-CN',
  'vxe-table/lib/locale/lang/en-US',
  'web-storage-cache',
  '@iconify/iconify',
  '@vueuse/core',
  '@zxcvbn-ts/core',
  'echarts/core',
  'echarts/charts',
  'echarts/components',
  'echarts/renderers',
  'echarts-wordcloud',
  '@wangeditor/editor',
  '@wangeditor/editor-for-vue',
  'element-plus',
  'element-plus/es',
  'element-plus/es/locale/lang/zh-cn',
  'element-plus/es/locale/lang/en',
  // 全部 element-plus 组件样式，一次性预构建，避免懒加载页面首次打开时触发 Vite 整页 reload
  ...elementPlusStyleCss
]

const exclude = ['@iconify/json']

export { include, exclude }