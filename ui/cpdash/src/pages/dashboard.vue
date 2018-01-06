<template>
  <div :class="{'nav-open': $sidebar.showSidebar}">
    <div class="wrapper">
      <side-bar type="sidebar" :sidebar-links="$sidebar.sidebarLinks">
      </side-bar>
      <div class="main-panel">
        <top-navbar></top-navbar>
        <dashboard-content @click.native="toggleSidebar">
        </dashboard-content>
      </div>
    </div>
    <side-bar type="navbar" :sidebar-links="$sidebar.sidebarLinks">
      <ul class="nav navbar-nav">
        <li>
          <a class="dropdown-toggle" data-toggle="dropdown">
            <i class="ti-panel"></i>
            <p>Stats</p>
          </a>
        </li>
        <drop-down title="5 Notifications" icon="ti-bell">
          <li><a>Notification 1</a></li>
          <li><a>Notification 2</a></li>
        </drop-down>

        <drop-down title="Settings" icon="ti-settings">
          <li><a>Notification 1</a></li>
          <li><a>Notification 2</a></li>
        </drop-down>
        <li class="divider"></li>
      </ul>
    </side-bar>
  </div>

</template>
<style lang="scss">

</style>
<script>
  import TopNavbar from '@/components/topnavbar'
  import DashboardContent from '@/components/DashboardContent'

  export default {
    components: {
      TopNavbar,
      DashboardContent
    },
    methods: {
      toggleSidebar() {
        if (this.$sidebar.showSidebar) {
          this.$sidebar.displaySidebar(false)
        }
      }
    }
  }

</script>
