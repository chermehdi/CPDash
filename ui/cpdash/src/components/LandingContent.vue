<template>
  <div class="ui container">
    <section class="first-section">
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
    <section class="about-us" id="about-us">
      <h2 class="header centered">Who Are We ?</h2>
      <div class="ui container">
        <div class="ui grid">
          <div class="column six wide">
            <div class="img-container">
              <img src="../assets/images/azuz.jpg" class="ui huge avatar image" alt="Azuz Erraoui">
            </div>
            <div class="description">
              Competitive Programmer, And Software Engineer
            </div>
          </div>
          <div class="column four wide"></div>
          <div class="column six wide">
            <div class="img-container">
              <img src="../assets/images/mehdi.jpg" class="ui avatar huge image" alt="Mehdi Maick">
            </div>
            <div class="description">
              Competitive Programmer, And Java Enthusiast
            </div>
          </div>
        </div>
      </div>
    </section>
  </div>
</template>
<script>
  export default {
    mounted() {
      this.startAnimations()
    },
    methods: {
      startAnimations() {
        $('.ui.huge.button')
          .transition('set looping')
          .transition('bounce', '2000ms');
      }
    }
  }
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
    .about-us {
      padding: 10rem 5rem;
    }
  }

  .header {
    font-family: 'Montserrat', sans-serif;
    font-weight: 300;
    color: #F5F5F5;
    padding: 1rem;
    text-align: center;
    font-size: 2.8em;
  }

  .img-container {
    padding: 1rem;
    img.image {
      box-shadow: 0 3px 3px rgba(0, 0, 0, 0.12), 0 3px 2px rgba(0, 0, 0, 0.24);
      border: 2px solid #BA68C8;
    }
  }

  .description {
    text-align: center;
    padding: 2rem;
    font-size: 1.2em;
    color: white;
  }

  .about-us {
    box-shadow: 0 14px 28px rgba(0, 0, 0, 0.25), 0 10px 10px rgba(0, 0, 0, 0.22);
    background: linear-gradient(to bottom, #AB47BC, #7B1FA2);
  }

  .ui.huge.button {
    margin-top: 1em;
  }
</style>
