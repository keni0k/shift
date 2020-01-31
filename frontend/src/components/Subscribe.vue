<template>
    <div>
        <form>
            <div class="row">
                <div class="form-group col-md-8 mx-auto subscribe">
                    <label for="group_link">Пригласительная ссылка</label>
                    <input type="text" class="form-control" id="group_link" v-model="group_link">
                    <button class="btn btn-primary invite-btn" v-on:click="get_group()"
                            type="button">Найти
                    </button>
                </div>
            </div>
            <div v-if="is_active">
                <div class="row">
                    <div class="form-group col-md-3">
                        <label for="user_name">Ваше имя</label>
                        <input type="text" class="form-control" id="user_name" v-model="user_name">
                    </div>
                    <div class="form-group col-md-3">
                        <label for="likes">Ваши предпочтения</label>
                        <textarea class="form-control" id="likes" rows="3" v-model="user_likes"></textarea>
                    </div>
                    <div class="form-group col-md-3">
                        <label for="dislikes">Что лучше не дарить</label>
                        <textarea class="form-control" id="dislikes" rows="3" v-model="user_dislikes"></textarea>
                    </div>
                    <div class="col-md-3">
                        <button class="btn btn-primary invite-btn" @click="subscribe()"
                                type="button" @keyup.esc="showModal = false">Создать</button>
                    </div>
                </div>
            </div>
        </form>

        <div id="modalSubscribe">
            <transition @enter="startTransitionModal" @after-enter="endTransitionModal"
                        @before-leave="endTransitionModal" @after-leave="startTransitionModal">
                <div class="modal fade" v-if="showModal" ref="modal" @click.self="showModal = false">
                    <div class="modal-dialog modal-lg" role="document">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h5 class="modal-title" id="exampleModalLabel">Заявка отправлена!</h5>
                                <button class="close" type="button" @click="showModal = false"><span aria-hidden="true">×</span>
                                </button>
                            </div>
                            <div class="modal-body">
                                <div v-if="user == null">Ожидайте распределение</div>
                                <div id="circularG">
                                    <div id="circularG_1" class="circularG"></div>
                                    <div id="circularG_2" class="circularG"></div>
                                    <div id="circularG_3" class="circularG"></div>
                                    <div id="circularG_4" class="circularG"></div>
                                    <div id="circularG_5" class="circularG"></div>
                                    <div id="circularG_6" class="circularG"></div>
                                    <div id="circularG_7" class="circularG"></div>
                                    <div id="circularG_8" class="circularG"></div>
                                </div>
                            </div>

                            <div class="modal-footer">
                                <button class="btn btn-secondary" @click="showModal = false">Выйти</button>
                            </div>
                        </div>
                    </div>
                </div>
            </transition>
            <div class="modal-backdrop fade d-none" ref="backdrop"></div>
        </div>
    </div>
</template>

<script>
    import Vue from 'vue'
    import axios from 'axios'

    Vue.prototype.$axios = axios;
    export default {
        name: 'subscription',
        data: function () {
            return {
                group_link: "",
                is_active: false,
                user_name: "",
                user_likes: "",
                user_dislikes: "",
                showModal: false,
                user: null
            }
        },
        methods: {
            get_group: function () {
                let self = this;
                axios.get(this.group_link)
                    .then(function (response) {
                        self.is_active = response.data.value != null;
                        if (!self.is_active)
                            alert('Пригласительная ссылка недействительна');
                    });
            },
            subscribe: function () {
                let self = this;
                let config = {
                    params: {
                        'user_name': this.user_name,
                        'user_likes': this.user_likes,
                        'user_dislikes': this.user_dislikes
                    }
                };
                axios.get(this.group_link, config)
                    .then(function () {
                        self.showModal = true;
                    })
                    .catch(function (error) {
                        alert(error);
                    });
            },
            startTransitionModal() {
                this.$refs.backdrop.classList.toggle("d-block");
                this.$refs.modal.classList.toggle("d-block");
            },
            endTransitionModal() {
                this.$refs.backdrop.classList.toggle("show");
                this.$refs.modal.classList.toggle("show");
            }
        }
    }
</script>
<!-- styling for the component -->
<style scoped>
    .subscribe label {
        display: block;
        margin-right: 130px;
        margin-bottom: .5rem;
    }
    .modal-mask {
        position: fixed;
        z-index: 9998;
        top: 0;
        left: 0;
        width: 100%;
        height: 100%;
        background-color: rgba(0, 0, 0, .5);
        display: table;
        transition: opacity .3s ease;
    }
    .modal-wrapper {
        display: table-cell;
        vertical-align: middle;
    }
    .subscribe button.btn.invite-btn {
        display: inline;
        margin: 0;
    }
    #modalSubscribe {
        color: black;
    }
    .subscribe input.form-control {
        display: inline;
        width: 60%;
        margin-right: 40px;
    }
</style>