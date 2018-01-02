import Vue from 'vue'
import Router from 'vue-router'
import Landing from '@/pages/Landing'
import Login from '@/components/Login'
import Registration from '@/components/Registration'
import LandingContent from '@/components/LandingContent'
import isUserAuthenticated from '@/state/auth'
import Dashboard from '@/pages/dashboard'
import Sheets from '@/pages/sheets'
import Profile from '@/pages/profile'

Vue.use(Router)

const router = new Router({
  routes: [
    {
      path: '/',
      name: 'landing',
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
    },
    {
      path: '/dashboard',
      name: 'dashboard',
      component: Dashboard,
      children: [
        {
          path: '',
          name: 'sheets',
          redirect: '/dashboard/sheets'
        },
        {
          path: 'sheets',
          name: 'sheets',
          component: Sheets
        },
        {
          path: 'profile',
          name: 'profile',
          component: Profile
        }
      ]
    }
  ],
  linkActiveClass: 'active'
})

const protectedRoutes = ['/dashboard/*']
const protectedRegex = new RegExp(protectedRoutes.join('|'), "i")

router.beforeEach((to, from, next) => {
  if (protectedRegex.test(to.fullPath)) {
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
