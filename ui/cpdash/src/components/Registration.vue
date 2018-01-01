<template>
  <div class="ui container">
    <h2 class="centered"> Registration Form </h2>
    <div class="center aligned ui grid">
      <div class="row">
        <div class="twelve wide column left aligned">
          <form class="ui form" @submit.prevent="register">
            <div class="field">
              <label for="fname">Full Name: </label>
              <input v-model="fullName" id="fname" type="text" name="fullName" placeholder="Full Name">
            </div>
            <div class="field">
              <label for="email">Your Email : </label>
              <input id="email" v-model="email" type="email" name="email" placeholder="Your Email">
            </div>
            <div class="field">
              <label for="password">Your Password : </label>
              <input id="password" v-model="password" type="password" name="password" placeholder="Your Email">
            </div>
            <button class="ui button green" type="submit">Register</button>
            <router-link class="ui button blue" to="/login">already have an account ?</router-link>
          </form>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
  export default {
    data() {
      return {
        fullName: '',
        email: '',
        password: ''
      }
    },
    methods: {
      register() {
        console.log(this.fullName)
        console.log(this.email)
        console.log(this.password)
      }
    }
  }
</script>
<style lang="scss" scoped>
  .ui.container {
    .centered {
      text-align: center;
    }
  }
</style>
