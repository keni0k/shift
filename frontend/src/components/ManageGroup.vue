<template>
  <form>
    <div class="row">
      <div class="form-group col-md-4">
        <label for="group_name">Название группы</label>
        <input type="text" class="form-control" id="group_name" v-model="group_name">
      </div>
      <div class="form-group col-md-3">
        <label for="count_people">Количество человек</label>
        <input type="number" class="form-control" id="count_people" v-model.number="count_people">
      </div>
      <div class="col-md-2">
        <button class="btn btn-primary" v-on:click="manage_group()" type="button">Изменить</button>
      </div>
    </div>
  </form>
</template>

<script>
import Vue from 'vue'
import axios from 'axios'
var VueCookie = require('vue-cookie')
Vue.prototype.$axios = axios
Vue.prototype.$cookie = VueCookie
export default {
  name: 'manage_group',
  data: function() {
    return {
      group_name: "",
      count_people: 0
    }
  },
  methods: {
    manage_group: function() {
      let self = this;
      axios.post('https://secretsanta2020.herokuapp.com/api/v001/groups/', { _method: 'PATCH' }, { params: 
          {'id': self.$cookie.get('id'), 'group_name': this.group_name, 'count_people': this.count_people}
        })
        .then(response => (alert(response.data)));
    }
  }
}
</script>
<!-- styling for the component -->
<style scoped>
#about {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
form .form-group {
  text-align: left;
}
</style>