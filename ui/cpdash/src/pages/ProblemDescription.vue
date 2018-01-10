<template>
  <div class="problem">
    <h3>this is the problem description phase</h3>
    <div class="ui item">
      {{ currentProblem. name }}
    </div>
    <button class="ui button" @click="goBack">go back</button>
  </div>
</template>

<script>
  export default {
    computed: {
      currentProblem() {
        const id = this.$store.state.selectedProblem
        return this.$store.state.sheets[id]
      }
    },
    methods: {
      goBack() {
        this.$router.go(-1)
      }
    }
  }
</script>
<style lang="scss" scoped>

</style>
