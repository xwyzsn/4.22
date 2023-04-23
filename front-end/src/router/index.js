import { createRouter, createWebHistory } from 'vue-router'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      redirect: '/login'
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
      children: [
        { path: 'index', name: 'sysIndex', component: () => import('../views/sys/SysIndex.vue') },
        {
          path: 'settings', name: 'sysSettings', component: () => import('../views/sys/SysPerson.vue'),
          redirect: '/sys/settings/mypreservation',
          children: [
            {
              path: 'mypreservation',
              name: 'mypreservation',
              component: () => import('../views/sys/SysPreserve.vue')
            },
            {
              path: 'myrent',
              name: 'myrent',
              component: () => import('../views/sys/SysMyOrders.vue')
            },
            {
              path: 'myfee',
              name: 'myfee',
              component: () => import('../views/sys/SysMyFee.vue')
            }
          ]
        },
        {
          path: 'housedetail',
          name: 'housedetail',
          component: () => import('../views/sys/SysHouseDetail.vue')
        }
      ]
    },
    {
      path: '/landlord',
      name: 'landlord',
      component: () => import('../views/landlord/LandlordLayout.vue'),
      children: [
        { path: 'index', name: 'landlordIndex', component: () => import('../views/landlord/LandlordIndex.vue') },
      ]
    },
    {
      path: '/about',
      name: 'about',
      // route level code-splitting
      // this generates a separate chunk (About.[hash].js) for this route
      // which is lazy-loaded when the route is visited.
      component: () => import('../views/AboutView.vue')
    }
  ]
})

export default router
