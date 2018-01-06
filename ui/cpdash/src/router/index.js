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
import store from '@/store'

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
const unprotectedRoutes = ['/login', '/register']

const protectedRegex = new RegExp(protectedRoutes.join('|'), "i")
const unprotectedRegex = new RegExp(unprotectedRoutes.join('|'), "i")
const logoutRoute = new RegExp("/logout")

router.beforeEach((to, from, next) => {
  if (logoutRoute.test(to.fullPath)) {
    store.commit('logout')
    next({path: '/'})
    return
  }
  if (protectedRegex.test(to.fullPath)) {
    if (isUserAuthenticated()) {
      next();
    } else {
      next({path: '/register'})
    }
  } else if (unprotectedRegex.test(to.fullPath)) {
    if (isUserAuthenticated()) {
      next({path: '/dashboard'})
    } else {
      next()
    }
  } else {
    next()
  }
})
export default router
