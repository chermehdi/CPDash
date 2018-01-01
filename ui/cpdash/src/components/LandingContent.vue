<template>
  <div class="ui container">
    <section>
      <div class="grid">
        <div class="row first">
          <div class="column">
            <h1 class="ui header">CPDash</h1> <!-- Add logo here -->
            <h2>Track You Training, Manage Your WorkSheets, be a Better Programmer </h2>
            <h3>Don't Have an Account Create one Now</h3>
            <router-link to="/register" class="ui huge button green">Create an Account</router-link>
          </div>
        </div>
      </div>
    </section>
  </div>
</template>
<script>
  export default {}
</script>
<style lang="scss" scoped>
  .ui.container {
    h1, h2, h3, h4, h5 {
      font-family: 'Montserrat', sans-serif;
    }
    section {
      margin-top: 10rem;
      .row.first {
        text-align: center;
        color: #F5F5F5;
      }
      h1 {
        color: white;
      }
    }
  }
</style>
