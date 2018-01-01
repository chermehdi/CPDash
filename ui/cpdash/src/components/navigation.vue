<template>
  <div class="ui menu">
    <div class="header item">
      <router-link to="/">CPDash</router-link>
    </div>
    <div class="right menu">
      <div class="item">
        <router-link class="ui" :class="isInverted" to="/register">Register</router-link>
      </div>
      <div class="item">
        <router-link class="ui" :class="isInverted" to="/login">Login</router-link>
      </div>
    </div>
  </div>
</template>
<script>
  export default {
    computed: {
      isInverted() {
        return this.$route.fullPath === '/' ? 'inverted' : '';
      }
    }
  }
</script>

<style lang="scss">
  .ui.menu {
    font-family: 'Montserrat', sans-serif;
    border-radius: 0;
    background-color: transparent;
    .item {
      color: #F5F5F5;
      transition: all 0.3s cubic-bezier(.25, .8, .25, 1);
      &:hover {
        color: #E8EAF6;
      }
      a.inverted {
        color: white;
      }
    }
  }
</style>
