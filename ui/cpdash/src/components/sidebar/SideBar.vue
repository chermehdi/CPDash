<template>

  <div :class="sidebarClasses" :data-background-color="backgroundColor" :data-active-color="activeColor">
    <div class="sidebar-wrapper" id="style-3">
      <div class="logo">
        <a href="#" class="simple-text">
          <span class="logo-img">
            <img src="static/img/car.png">
          </span>
          CPdash <!-- ADD Logo-->
        </a>
      </div>
      <slot>

      </slot>
      <ul :class="navClasses">
        <router-link v-for="(link,index) in sidebarLinks" :key="link.id" :to="link.path" tag="li" :ref="link.name">
          <a href="#">
            <i :class="link.icon"></i>
            <p>
              {{ link.name }}
            </p>
          </a>
        </router-link>
      </ul>
      <moving-arrow :move-y="arrowMovePx">
      </moving-arrow>
    </div>

  </div>
</template>

<script>
  import MovingArrow from './MovingArrow.vue'

  export default {
    components: {
      MovingArrow
    },
    props: {
      type: {
        type: String,
        default: 'sidebar',
        validator: (value) => {
          let acceptedValues = ['sidebar', 'navbar']
          return acceptedValues.indexOf(value) !== -1
        }
      },
      backgroundColor: {
        type: String,
        default: 'black',
        validator: (value) => {
          let acceptedValues = ['white', 'black', 'darkblue']
          return acceptedValues.indexOf(value) !== -1
        }
      },
      activeColor: {
        type: String,
        default: 'success',
        validator: (value) => {
          let acceptedValues = ['primary', 'info', 'success', 'warning', 'danger']
          return acceptedValues.indexOf(value) !== -1
        }
      },
      sidebarLinks: {
        type: Array,
        default: () => []
      }
    },
    computed: {
      sidebarClasses() {
        if (this.type === 'sidebar') {
          return 'sidebar'
        } else {
          return 'collapse navbar-collapse off-canvas-sidebar'
        }
      },
      navClasses() {
        if (this.type === 'sidebar') {
          return 'nav'
        } else {
          return 'nav navbar-nav'
        }
      },
      /**
       * Styles to animate the arrow near the current active sidebar link
       * @returns {{transform: string}}
       */
      arrowMovePx() {
        return this.linkHeight * this.activeLinkIndex
      }
    },
    data() {
      return {
        linkHeight: 60,
        activeLinkIndex: 0,
        windowWidth: 0,
        isWindows: false,
        hasAutoHeight: false
      }
    },
    methods: {
      findActiveLink() {
        this.sidebarLinks.find((element, index) => {
          let found = element.path === this.$route.path
          if (found) {
            this.activeLinkIndex = index
          }
          return found
        })
      }
    },
    mounted() {
      this.findActiveLink()
    },
    watch: {
      $route: function (newRoute, oldRoute) {
        this.findActiveLink()
      }
    }
  }
</script>
<style>
  .sidebar[data-background-color="black"] .nav li:not(.active) > a,
  .off-canvas-sidebar[data-background-color="black"] .nav li:not(.active) > a {
    color: #424242;
  }

  .sidebar[data-active-color="success"] .nav li.active > a,
  .off-canvas-sidebar[data-active-color="success"] .nav li.active > a {
  }

  .sidebar[data-background-color="black"] .logo .simple-text,
  .off-canvas-sidebar[data-background-color="black"] .logo .simple-text {
    color: #424242;
  }

  div#style-3 {
    background-color: #AB47BC;
    overflow: hidden;
  }

  #style-3 a {
    color: white;
  }

</style>
