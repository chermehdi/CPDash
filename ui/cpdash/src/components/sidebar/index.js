import Sidebar from './SideBar.vue'

const SideBarLinks = {
  showSidebar: false,
  sidebarLinks: [
    {
      name: 'My Sheets',
      icon: 'ti-panel',
      path: '/dashboard/sheets',
      id: 0
    },
    {
      name: 'Profile',
      icon: 'ti-user',
      path: '/dashboard/profile',
      id: 1
    },
    {
      name: 'Operations',
      icon: 'ti-view-list-alt',
      path: '/dashboard/settings',
      id: 2
    }
  ],
  displaySidebar(value) {
    this.showSidebar = value
  }
}

const SidebarPlugin = {
  install(Vue) {
    Vue.mixin({
      data() {
        return {
          sidebarStore: SideBarLinks
        }
      }
    })
    Object.defineProperty(Vue.prototype, '$sidebar', {
      get() {
        return this.$root.sidebarStore
      }
    })
    Vue.component('side-bar', Sidebar)
  }
}
export default SidebarPlugin
