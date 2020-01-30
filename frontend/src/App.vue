<template>
  <div id="app">
    <img src="./assets/logo.png">
    <header class="header">
        <h1>Welcome to Secret Santa!</h1>
    </header>

    <div class="accordion">
      <div v-bind:class="{ active: item == 'manage' }" class="accordion-item"
        v-on:click="item = 'manage'">
            <router-link to="/manage" class="heading">
                <div class="title">Управлять группой</div>
            </router-link>
            <div class="content" v-bind:class="{ initial: item == 'manage' }">
                <router-view v-if="item == 'manage'"/>
            </div>
        </div>

        <div v-bind:class="{ active: item == 'subscribe' }" class="accordion-item"
        v-on:click="item = 'subscribe'">
            <router-link to="/subscribe" class="heading">
                <div class="title">Присоединиться к группе</div>
            </router-link>
            <div class="content" v-bind:class="{ initial: item == 'subscribe' }">
                <router-view v-if="item == 'subscribe'"/>
            </div>
        </div>

        <div v-bind:class="{ active: item == 'add' }" class="accordion-item"
        v-on:click="item = 'add'">
            <router-link to="/add" class="heading">
                <div class="title">Создать группу</div>
            </router-link>
            <div class="content" v-bind:class="{ initial: item == 'add' }">
                <router-view v-if="item == 'add'"/>
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
      item: this.$route.name
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