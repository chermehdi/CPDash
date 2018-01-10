<template>
  <div id="app">
    <router-view/>
  </div>
</template>

<script>
  export default {
    name: 'app',
    watch: {
      $route: async function (from, to) {
        //TODO: test for validity of the token here
        if (this.$store.state.isLoggedIn) {
          const response = await this.checkTokenValidity()
          if (!response) {
            this.$router.push({path: '/logout'})
          }
        }
      }
    },
    mounted() {
      this.checkState()
    },
    methods: {
      async checkState() {
        if (this.$store.state.isLoggedIn) {
          if (!this.$store.state.user.email) {
            // TODO: update the state
            console.log('should fetch user object')
            const token = this.$localStorage.get('_token')
            try {
              const response = await this.$http.get('/api/users/me', {headers: {'Authorization': `${token}`}})
              this.$store.commit('setUser', response.body)
            } catch (e) {
              this.$router.push({path: '/logout'})
            }
          }
        }
      },
      async checkTokenValidity() {
        const token = this.$localStorage.get('_token')
        try {
          const response = await this.$http.get('/api/users/validity', {headers: {'Authorization': `${token}`}})
          return response.status === 200
        } catch (e) {
          return false
        }
      }
    }
  }
</script>

<style lang="scss">
  #app {
    font-family: 'Montserrat', sans-serif;
    -webkit-font-smoothing: antialiased;
    -moz-osx-font-smoothing: grayscale;
    height: 100%;
  }

  $primary-color: #AB47BC;
  .navbar-default .navbar-nav > .active > a,
  .navbar-default .navbar-nav > .active > a:not(.btn):hover,
  .navbar-default .navbar-nav > .active > a:not(.btn):focus,
  .navbar-default .navbar-nav > li > a:not(.btn):hover,
  .navbar-default .navbar-nav > li > a:not(.btn):focus {
    &:hover {
      color: #AB47BC;
    }
    .navbar-default .navbar-nav > .dropdown > a:hover .caret {
      border-bottom-color: $primary-color;
      border-bottom-color: $primary-color;
    }
  }

</style>
