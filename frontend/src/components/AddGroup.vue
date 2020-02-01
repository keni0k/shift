<template>
    <div>
        <form>
            <div class="row">
                <div class="form-group col-md-4">
                    <label for="user_name">Ваше имя</label>
                    <input type="text" class="form-control" id="user_name" v-model="user_name">
                </div>
                <div class="form-group col-md-4">
                    <label for="group_name">Название группы</label>
                    <input type="text" class="form-control" id="group_name" v-model="group_name">
                </div>
                <div class="form-group col-md-2">
                    <label for="count_people" style="font-size: 0.9rem">Количество людей</label>
                    <input type="number" class="form-control" id="count_people" v-model.number="count_people">
                </div>
                <div class="col-md-2">
                    <button class="btn btn-primary invite-btn" @click="add_group()" @keyup.esc="showModal = false"
                            type="button">Создать
                    </button>
                </div>
            </div>
        </form>

        <div id="exampleModal">
            <transition @enter="startTransitionModal" @after-enter="endTransitionModal"
                        @before-leave="endTransitionModal" @after-leave="startTransitionModal">
                <div class="modal fade" v-if="showModal" ref="modal" @click.self="showModal = false">
                    <div class="modal-dialog modal-lg" role="document">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h5 class="modal-title" id="exampleModalLabel">Модерация группы</h5>
                                <button class="close" type="button" @click="showModal = false"><span aria-hidden="true">×</span>
                                </button>
                            </div>
                            <div class="modal-body">
                                <div>Ссылка: <a v-bind:href="link_to_subscribe"> {{ link_to_subscribe.substr(8) }} </a>
                                </div>
                                <div id="circularG" >
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
        name: 'add_group',
        data: function () {
            return {
                showModal: false,
                user_name: "",
                group_name: "",
                count_people: 0,
                link_to_subscribe: ""
            }
        },
        methods: {
            add_group: function () {
                const self = this;
                axios.post('https://secretsanta2020.herokuapp.com/api/v001/groups/without_user_id', null, {
                    params:
                        {'user_name': this.user_name, 'name': this.group_name, 'count_people': this.count_people}
                })
                    .then(response => {
                        self.showModal = true;
                        self.link_to_subscribe = response.data

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
<style scoped>
    form .form-group {
        text-align: left;
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
    #exampleModal {
        color: black;
    }
    #circularG {
        position: relative;
        width: 58px;
        height: 58px;
        margin: auto;
    }
    .circularG {
        position: absolute;
        background-color: rgb(0, 0, 0);
        width: 14px;
        height: 14px;
        border-radius: 9px;
        -o-border-radius: 9px;
        -ms-border-radius: 9px;
        -webkit-border-radius: 9px;
        -moz-border-radius: 9px;
        animation-name: bounce_circularG;
        -o-animation-name: bounce_circularG;
        -ms-animation-name: bounce_circularG;
        -webkit-animation-name: bounce_circularG;
        -moz-animation-name: bounce_circularG;
        animation-duration: 1.1s;
        -o-animation-duration: 1.1s;
        -ms-animation-duration: 1.1s;
        -webkit-animation-duration: 1.1s;
        -moz-animation-duration: 1.1s;
        animation-iteration-count: infinite;
        -o-animation-iteration-count: infinite;
        -ms-animation-iteration-count: infinite;
        -webkit-animation-iteration-count: infinite;
        -moz-animation-iteration-count: infinite;
        animation-direction: normal;
        -o-animation-direction: normal;
        -ms-animation-direction: normal;
        -webkit-animation-direction: normal;
        -moz-animation-direction: normal;
    }
    #circularG_1 {
        left: 0;
        top: 23px;
        animation-delay: 0.41s;
        -o-animation-delay: 0.41s;
        -ms-animation-delay: 0.41s;
        -webkit-animation-delay: 0.41s;
        -moz-animation-delay: 0.41s;
    }
    #circularG_2 {
        left: 6px;
        top: 6px;
        animation-delay: 0.55s;
        -o-animation-delay: 0.55s;
        -ms-animation-delay: 0.55s;
        -webkit-animation-delay: 0.55s;
        -moz-animation-delay: 0.55s;
    }
    #circularG_3 {
        top: 0;
        left: 23px;
        animation-delay: 0.69s;
        -o-animation-delay: 0.69s;
        -ms-animation-delay: 0.69s;
        -webkit-animation-delay: 0.69s;
        -moz-animation-delay: 0.69s;
    }
    #circularG_4 {
        right: 6px;
        top: 6px;
        animation-delay: 0.83s;
        -o-animation-delay: 0.83s;
        -ms-animation-delay: 0.83s;
        -webkit-animation-delay: 0.83s;
        -moz-animation-delay: 0.83s;
    }
    #circularG_5 {
        right: 0;
        top: 23px;
        animation-delay: 0.97s;
        -o-animation-delay: 0.97s;
        -ms-animation-delay: 0.97s;
        -webkit-animation-delay: 0.97s;
        -moz-animation-delay: 0.97s;
    }
    #circularG_6 {
        right: 6px;
        bottom: 6px;
        animation-delay: 1.1s;
        -o-animation-delay: 1.1s;
        -ms-animation-delay: 1.1s;
        -webkit-animation-delay: 1.1s;
        -moz-animation-delay: 1.1s;
    }
    #circularG_7 {
        left: 23px;
        bottom: 0;
        animation-delay: 1.24s;
        -o-animation-delay: 1.24s;
        -ms-animation-delay: 1.24s;
        -webkit-animation-delay: 1.24s;
        -moz-animation-delay: 1.24s;
    }
    #circularG_8 {
        left: 6px;
        bottom: 6px;
        animation-delay: 1.38s;
        -o-animation-delay: 1.38s;
        -ms-animation-delay: 1.38s;
        -webkit-animation-delay: 1.38s;
        -moz-animation-delay: 1.38s;
    }
    @keyframes bounce_circularG {
        0% {
            transform: scale(1);
        }
        100% {
            transform: scale(.3);
        }
    }
    @-o-keyframes bounce_circularG {
        0% {
            -o-transform: scale(1);
        }
        100% {
            -o-transform: scale(.3);
        }
    }
    @-ms-keyframes bounce_circularG {
        0% {
            -ms-transform: scale(1);
        }
        100% {
            -ms-transform: scale(.3);
        }
    }
    @-webkit-keyframes bounce_circularG {
        0% {
            -webkit-transform: scale(1);
        }
        100% {
            -webkit-transform: scale(.3);
        }
    }
    @-moz-keyframes bounce_circularG {
        0% {
            -moz-transform: scale(1);
        }
        100% {
            -moz-transform: scale(.3);
        }
    }
</style>