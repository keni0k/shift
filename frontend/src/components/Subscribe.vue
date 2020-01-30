<template>
  <form>
    <div class="row">
      <div class="form-group col-md-8">
        <label for="group_link">Пригласительная ссылка</label>
        <input type="text" class="form-control" id="group_link" v-model="group_link">
      </div>
      <div class="col-md-4">
        <button class="btn btn-primary" v-on:click="get_group()" 
                type="button">Найти</button>
      </div>
    </div>
    <div v-if="is_active">
      <div class="row">
        <div class="form-group col-md-4">
          <label for="user_name">Ваше имя</label>
          <input type="text" class="form-control" id="user_name" v-model="user_name">
        </div>
        <div class="form-group col-md-4">
          <label for="likes">Ваши предпочтения</label>
          <textarea class="form-control" id="likes" rows="3" v-model="user_likes"></textarea>
        </div>
        <div class="form-group col-md-4">
          <label for="dislikes">Что вам лучше не дарить</label>
          <textarea class="form-control" id="dislikes" rows="3" v-model="user_dislikes"></textarea>
        </div>
      </div>
      <div class="row col-sm-12">
        <button class="btn btn-primary" v-on:click="subscribe()" type="button">Создать</button>
      </div>
    </div>
  </form>
</template>

<script>
import Vue from 'vue'
import axios from 'axios'
Vue.prototype.$axios = axios
export default {
  name: 'subscription',
  data: function() {
    return {
      group_link: "",
      is_active: false,
      user_name: "",
      user_likes: "",
      user_dislikes: ""
    }
  },
  methods: {
    get_group: function() {
      var self = this;
      axios.get(this.group_link)
        .then(function (response) {
          self.is_active = response.data.value != null;
          if (!self.is_active)
            alert('Пригласительная ссылка недействительна'); 
        });
    },
    subscribe: function() {
      let config = {
        params: {
          'user_name': this.user_name, 
          'user_likes': this.user_likes, 
          'user_dislikes': this.user_dislikes
        }
      }
      axios.get(this.group_link, config)
        .then(function () {
          alert('Отлично! Ожидайте распределения..');
        })
        .catch(function (error) {
          alert(error);
        });
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
</style>