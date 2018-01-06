<template>
  <nav class="navbar navbar-default">
    <div class="container-fluid">
      <div class="navbar-header">
        <button type="button" class="navbar-toggle" :class="{toggled: $sidebar.showSidebar}" @click="toggleSidebar">
          <span class="sr-only">Toggle navigation</span>
          <span class="icon-bar bar1"></span>
          <span class="icon-bar bar2"></span>
          <span class="icon-bar bar3"></span>
        </button>
        <a class="navbar-brand">{{routeName}}</a>
      </div>
      <div class="navbar-right-menu">
        <ul class="nav navbar-nav navbar-right">
          <drop-down title="2 Notifications" icon="ti-bell">
            <li><a href="#">Notification 1</a></li>
            <li><a href="#">Notification 2</a></li>
          </drop-down>
          <drop-down title="Settings" icon="ti-settings">
            <li>
              <router-link to="/logout"> Logout</router-link>
            </li>
          </drop-down>
        </ul>
      </div>
    </div>
  </nav>
</template>
<script>
  export default {
    data() {
      return {
        activeNotification: false
      }
    },
    methods: {
      capitalizeFirstLetter(string) {
        return string.charAt(0).toUpperCase() + string.slice(1)
      },
      toggleNotificationDropDown() {
        this.activeNotifications = !this.activeNotifications
      },
      closeNotificationDropDown() {
        this.activeNotifications = false
      },
      toggleSidebar() {
        console.log('display the side bar')
        this.$sidebar.displaySidebar(!this.$sidebar.showSidebar)
      },
      hideSidebar() {
        this.$sidebar.displaySidebar(false)
      }
    },
    computed: {
      routeName() {
        const {name} = this.$route
        return this.capitalizeFirstLetter(name)
      }
    }
  }
</script>
<style lang="scss" scoped>
  $primary-color: #AB47BC;
  li.dropdown {
    cursor: pointer;
  }

  .dropdown-menu > li > a:hover, .dropdown-menu > li > a:focus {
    background-color: $primary-color;
    color: white;
  }
</style>
