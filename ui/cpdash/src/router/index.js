import Vue from 'vue'
import Router from 'vue-router'
import Landing from '@/pages/Landing'
import Login from '@/components/Login'
import Registration from '@/components/Registration'
import LandingContent from '@/components/LandingContent'
import isUserAuthenticated from '@/state/auth'

Vue.use(Router)

const router = new Router({
  routes: [
    {
      path: '/',
      component: Landing,
      children: [
        {
          path: '',
          component: LandingContent
        },
        {
          path: 'register',
          component: Registration
        },
        {
          path: 'login',
          component: Login
        }
      ]
    }
  ]
})

const protectedRoutes = ['/login', '/dashboard']

router.beforeEach((to, from, next) => {
  if (protectedRoutes.find(a => a.indexOf(to.fullPath) >= 0)) {
    console.log('hel')
    if (isUserAuthenticated()) {
      next()
    } else {
      next({path: '/register'})
    }
  } else {
    next()
  }
})
export default router
