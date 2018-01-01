<template>
  <div class="ui container">
    <h1 class="centered">Login Form</h1>
    <div class="ui grid center aligned">
      <div class="ten wide column left aligned">
        <form class="ui form" @submit.prevent="login">
          <div class="field">
            <label for="email">Your Email : </label>
            <input id="email" v-model="email" type="email" name="email" placeholder="Your Email">
          </div>
          <div class="field">
            <label for="password">Your Password : </label>
            <input id="password" v-model="password" type="password" name="password" placeholder="Your Email">
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
        password: ''
      }
    },
    methods: {
      login() {
        console.log(this.$localStorage)
        console.log(this.$http)
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
