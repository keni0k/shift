<template>
  <div id="app">
    <img src="./assets/logo.png">
    <header class="header">
        <h1>Welcome to Secret Santa!</h1>
        <h4>By: <a href="https://vk.com/keni0k" target="_blank">@keni0k</a></h4>
    </header>

    <div class="accordion">
        <div v-bind:class="{ active: isActive }" class="accordion-item"
        v-on:click="isActive = true">
            <router-link to="/subscribe" class="heading">
                <div class="title">Присоединиться к группе</div>
            </router-link>
            <div class="content" v-bind:class="{ initial: isActive }">
                Trere is a form
            </div>
        </div>

        <div v-bind:class="{ active: !isActive }" class="accordion-item"
        v-on:click="isActive = false">
            <router-link to="/add" class="heading">
                <div class="title">Создать группу</div>
            </router-link>
            <div class="content" v-bind:class="{ initial: !isActive }">
                <p>
                    Trere is another form
                </p>
            </div>
        </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'app',
  data: function() {
    return {
      isActive: true
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
  color: #2c3e50;
  margin-top: 60px;
}
.accordion-item .content.initial {
  display: initial;
}
</style>
<style lang="scss">
  @import url(https://fonts.googleapis.com/css?family=Raleway:300);

  @function random-color($min: 0, $max: 255, $alpha: 1) {
      @if $min < 0 {
          $min: -1;
      } @else {
          $min: $min - 1;
      }
      @if $max > 255 {
          $max: 256;
      } @else {
          $max: $max + 1;
      }
      $red: random($max) + $min;
      $green: random($max) + $min;
      $blue: random($max) + $min;
      @return rgba($red, $green, $blue, $alpha);
  }

  $ultralight: #fefefe;
  $primary: random-color($min: 100, $max: 100);
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
          color: $primary-dark;
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