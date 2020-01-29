<template>
  <form>
    <div class="row">
      <div class="form-group col-md-5">
        <label for="user_name">Ваше имя</label>
        <input type="text" class="form-control" id="user_name" v-model="user_name">
      </div>
      <div class="form-group col-md-4">
        <label for="group_name">Название группы</label>
        <input type="text" class="form-control" id="group_name" v-model="group_name">
      </div>
      <div class="form-group col-md-3">
        <label for="count_people">Количество человек</label>
        <input type="number" class="form-control" id="count_people" v-model.number="count_people">
      </div>
    </div>
    <div class="row col-sm-12">
      <button class="btn btn-primary" v-on:click="add_group()" type="button">Создать</button>
    </div>
  </form>
</template>

<script>
import Vue from 'vue'
import axios from 'axios'
Vue.prototype.$axios = axios
export default {
  name: 'add_group',
  data: function() {
    return {
      user_name: "",
      group_name: "",
      count_people: 0
    }
  },
  methods: {
    add_group: function() {
      axios.post('https://secretsanta2020.herokuapp.com/api/v001/groups/without_user_id', null, { params: 
          {'user_name': this.user_name, 'name': this.group_name, 'count_people': this.count_people}
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