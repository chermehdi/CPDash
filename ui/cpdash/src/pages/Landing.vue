<template>
  <div class="wrapper" :class="backgroundImage">
    <navigation></navigation>
    <div class="ui container">
      <transition name="fade" mode="out-in">
        <router-view/>
      </transition>
    </div>
  </div>
</template>

<script>
  import Navigation from '@/components/Navigation'
  import LandingContent from '@/components/LandingContent'

  export default {
    name: 'Landing',
    components: {
      Navigation,
      LandingContent
    },
    data() {
      return {
        st: {}
      }
    },
    computed: {
      backgroundImage() {
        return this.$route.fullPath === '/' ? 'bg-image' : ''
      }
    },
    methods: {},
    mounted() {
    }
  }
</script>

<style lang="scss" scoped>
  .wrapper {

    &.bg-image {
      background: url("../assets/images/wallpaper.jpg") center center / cover;
    }

    height: 100%;
    width: 100%;

    font-family: 'Montserrat', sans-serif;

    .fade-enter-active, .fade-leave-active {
      opacity: 1;
      transition: all 0.5s cubic-bezier(.25, .8, .25, 1);
    }

    .fade-enter, .fade-leave-active {
      opacity: 0
    }
  }


</style>
