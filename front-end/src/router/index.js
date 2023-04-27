import { createRouter, createWebHistory } from 'vue-router'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'IndexLayout',
      component: () => import('../views/Index/IndexLayout.vue'),
      redirect: '/index',
      children: [
        { path: 'index', name: 'IndexLayoutView', component: () => import('../views/Index/IndexView.vue') }
      ]
    },
    {
      path: '/login',
      name: 'login',
      component: () => import('../views/LoginView.vue')
    },
    {
      path: '/sys',
      name: 'sys',
      component: () => import('../views/sys/SysLayout.vue'),
      meta: {
        breadcrumb: '租客后台'
      },
      children: [
        {
          path: 'mypreservation',
          name: 'mypreservation',
          component: () => import('../views/sys/SysPreserve.vue'),
          meta: {
            breadcrumb: '我的预约'
          },
        },
        {
          path: 'myrent',
          name: 'myrent',
          component: () => import('../views/sys/SysMyOrders.vue'),
          meta: { breadcrumb: '我的订单' }
        },
        {
          path: 'myfee',
          name: 'myfee',
          component: () => import('../views/sys/SysMyFee.vue'),
          meta: { breadcrumb: '我的费用' }
        },
        {
          path: 'issue',
          name: 'issue',
          component: () => import('../views/sys/SysReportIssue.vue'),
          meta: { breadcrumb: '问题反馈' }
        }
      ]
    },
    {
      path: '/housedetail',
      name: 'housedetail',
      component: () => import('../views/sys/SysHouseDetail.vue')
    },
    {
      path: '/landlord',
      name: 'landlord',
      component: () => import('../views/landlord/LandlordLayout.vue'),
      redirect: '/landlord/config',
      meta: {
        breadcrumb: '房东后台'
      },
      children: [
        { path: 'config', name: 'landlordConfig', component: () => import('../views/landlord/LandlordConfig.vue'), meta: { breadcrumb: '统计信息', path: '/landlord/config' } },
        { path: 'index', name: 'landlordIndex', component: () => import('../views/landlord/LandlordIndex.vue'), meta: { breadcrumb: '我的房屋', path: '/landlord/index' } },
        { path: 'reservation', name: 'landlordReservation', component: () => import('../views/landlord/LandlordReservation.vue'), meta: { breadcrumb: '看房预约', path: '/landlord/reservation' } },
        { path: 'order', name: 'landlordOrder', component: () => import('../views/landlord/LandlordOrder.vue'), meta: { breadcrumb: '订单管理', path: '/landlord/order' } },
        { path: 'feedback', name: 'landlordFeedback', component: () => import('../views/landlord/LandlordFeedback.vue'), meta: { breadcrumb: '反馈管理', path: '/landlord/feedback' } },
      ]
    },
    {
      path: '/admin',
      name: 'admin',
      component: () => import('../views/admin/AdminLayout.vue'),
      redirect: '/admin/index',
      meta: {
        breadcrumb: '管理员后台'
      },
      children: [
        { path: 'index', name: 'adminIndex', component: () => import('../views/admin/AdminIndex.vue'), meta: { breadcrumb: '首页', path: '/admin/index' } },
        { path: 'house', name: 'adminHouse', component: () => import('../views/admin/AdminHouse.vue'), meta: { breadcrumb: '房屋审核', path: '/admin/house' } }
      ]
    }
  ]
})

export default router
