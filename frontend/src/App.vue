<template>
  <div id="app">
    <img src="https://www.mysecretsanta.me/assets/i/snowflake.png" class="app-img">
    <header class="header">
        <h1>Welcome to Secret Santa!</h1>
    </header>

    <div class="accordion">
        <div v-bind:class="{ active: isActive }" class="accordion-item"
        v-on:click="isActive = true">
            <router-link to="/subscribe" class="heading">
                <div class="title">Присоединиться к группе</div>
            </router-link>
            <div class="content" v-bind:class="{ initial: isActive }">
                <router-view v-if="isActive"/>
            </div>
        </div>

        <div v-bind:class="{ active: !isActive }" class="accordion-item"
        v-on:click="isActive = false">
            <router-link to="/add" class="heading">
                <div class="title">Создать группу</div>
            </router-link>
            <div class="content" v-bind:class="{ initial: !isActive }">
                <router-view v-if="!isActive"/>
            </div>
        </div>
    </div>
  <footer><p>by: <a href="https://vk.com/keni0k" target="_blank">@keni0k</a></p></footer>
  </div>
</template>

<script>
export default {
  name: 'app',
  data: function() {
    return {
      isActive: this.$route.name === 'subscribe'
    };
  }
}
</script>
<style>
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: ghostwhite;
  background-repeat: round;
  background-image: url(https://www.mysecretsanta.me/assets/i/slider/4.jpg);
}
img.app-img{
    margin-top: 60px;
}
button.btn.invite-btn {
  margin-top: 36px;
  display: block;
  border: 2px solid #FFF;
  color: #FFF !important;
  padding-right: 20px;
  padding-left: 20px;
  text-decoration: none;
  background-color: transparent;
}
.accordion-item .content.initial {
  display: initial;
}
</style>
<style lang="scss">
  @import url(https://fonts.googleapis.com/css?family=Raleway:300);

  $ultralight: #fefefe;
  $primary: rgba(150, 140, 170, 1);
  $primary-light: mix(#ffffff, $primary, 25%);
  $primary-dark: mix(#000000, $primary, 25%);

  *,
  *:before,
  *:after {
      box-sizing: border-box;    
  }

  html {
      padding-top: 50px;
      font-family: 'Raleway', Helvetica, arial, sans-serif;
      background-color: #eeeeee;
  }

  .header {
      text-align: center;

      a {
        text-decoration: none;
        color: $primary;
      }
  }

  .accordion {
      width: 100%;
      max-width: 75rem;
      margin: 0 auto;
      padding: 2rem;
  }

  .accordion-item {
      position: relative;

      &.active {
          .heading {
              color: $primary-light;
          }

          .icon {
              background: $ultralight;

              &:before {
                  background: $primary-light;
              }

              &:after {
                  width: 0;
              }
          }
      }
      
      .heading {
          display: block;
          text-transform: uppercase;
          text-decoration: none;
          color: #ddd6ea;
          font-weight: 700;
          font-size: 1rem;
          position: relative;
          padding: 1.5rem 1.5rem 1.5rem 4rem;
          transition: 0.3s ease-in-out;

          @media (min-width: 40rem) {
              font-size: 1.2rem;
          }

          &:hover {
              color: $primary-light;
              
              .icon {
                  &:before,
                  &:after {
                      background: $primary-light;
                  }
              }
          }
      }

      .content {
          display: none;
          
          p {
              margin-top: 0;
          }
          
          @media (min-width: 40rem) {
              // padding-left: 4rem;
              line-height: 1.75;
          }
      }
  }
</style>