<template>
  <div class="ui container">
    <h1 class="centered header">Login Form</h1>

    <div class="ui grid center aligned">
      <div class="ten wide column left aligned">
        <div class="ui message negative" v-if="error">
          Wrong Credentials, Please try again
        </div>
        <form class="ui form" :class="{loading: loading}" @submit.prevent="login">
          <div class="field">
            <label for="email">Your Email : </label>
            <input id="email" v-model="email" type="email" name="email" placeholder="Your Email">
          </div>
          <div class="field">
            <label for="password">Your Password : </label>
            <input id="password" v-model="password" type="password" name="password" placeholder="Your Password">
          </div>
          <button class="ui button green" type="submit">Login</button>
        </form>
      </div>
    </div>
  </div>
</template>
<script>
  export default {
    data() {
      return {
        email: '',
        password: '',
        loading: false,
        error: false
      }
    },
    methods: {
      login() {
        this.loading = true
        const authenticationRequest = {
          email: this.email,
          password: this.password
        }
        this.$http.post('/api/auth/login', authenticationRequest)
          .then(res => {
            this.$store.commit('setUser', res.body.user)
            this.$localStorage.set('_token', res.body.token)
            this.loading = false
            this.error = false
            setTimeout(() => {
              this.$router.push({path: '/dashboard'})
            }, 2000)
          })
          .catch(e => {
            this.loading = false
            this.error = true
            console.log('error ', e)
          })
      }
    }
  }
</script>
<style lang="scss" scoped>
  .ui.container {
    .centered {
      text-align: center;
    }
    .header {
      font-family: 'Montserrat', sans-serif;
      font-weight: 300;
      padding: 2rem;
      font-size: 2.5em;
    }
  }
</style>
