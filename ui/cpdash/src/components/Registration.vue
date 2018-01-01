<template>
  <div class="ui container">
    <h2 class="centered header"> Registration Form</h2>
    <div class="center aligned ui grid">
      <div class="row">
        <div class="twelve wide column left aligned">
          <form class="ui form" :class="isSending" @submit.prevent="register">
            <div class="field required">
              <label for="fname">Your Username : </label>
              <input v-model="userName" id="fname" type="text" name="userName" placeholder="Username">
            </div>
            <div class="field required">
              <label for="email">Your Email : </label>
              <input id="email" v-model="email" type="email" name="email" placeholder="Your Email">
            </div>
            <div class="field required" :class="validPassword">
              <label for="password">Your Password : </label>
              <input id="password" v-model="password" type="password" name="password" placeholder="Your Email" required>
            </div>
            <div class="field required" :class="validPassword">
              <label for="confirmation">Your Password Confirmation : </label>
              <input id="confirmation" v-model="passwordConfirmation" type="password" name="password"
                     placeholder="Password Confirmation" required>
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
        userName: '',
        email: '',
        password: '',
        passwordConfirmation: '',
        valid: true,
        loading: false
      }
    },
    methods: {
      register() {
        if (!this.valid) {
          // TODO: display notification
          return
        }

      }
    },
    computed: {
      validPassword() {
        return {
          invalid: !this.valid
        }
      },
      isSending() {
        return {
          loading: this.loading
        }
      }
    },
    watch: {
      passwordConfirmation(newValue) {
        console.log(newValue, this.password)
        if (newValue !== this.password) {
          this.valid = false
        } else {
          this.valid = true
        }
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
    .field.invalid > input {
      border: 1px solid rgba(212, 25, 84, 0.52);
      transition: border 0.5s cubic-bezier(.25, .8, .25, 1);
    }
  }
</style>
