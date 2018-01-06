import store from '@/store'

export default function isUserAuthenticated() {
  return store.state.isLoggedIn
}
