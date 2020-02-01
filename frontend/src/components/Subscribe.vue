<template>
    <div>
        <form>
            <div class="row" v-if="this.$route.params.id == null">
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
                                type="button" @keyup.esc="showModal = false">Присоединиться
                        </button>
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
                user: null,
                user_id: null
            }
        },
        methods: {
            get_group: function (url = this.group_link) {
                let self = this;
                axios.get(url)
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
                    .then(function (response) {
                        self.showModal = true;
                        self.user_id = parseInt(response.data)
                    })
                    .catch(function () {
                        alert('Пригласительная ссылка недействительна');
                    });
            },
            startTransitionModal() {
                this.$refs.backdrop.classList.toggle("d-block");
                this.$refs.modal.classList.toggle("d-block");
            },
            endTransitionModal() {
                this.$refs.backdrop.classList.toggle("show");
                this.$refs.modal.classList.toggle("show");
            },
            getTarget() {
                axios.get('https://secretsanta2020.herokuapp.com/api/v001/users/' + this.user_id)
                    .then((res) => {
                        this.currentData = res.data[0] //TODO
                    })
            },
            stopTimer() {
                if (this.interval) {
                    window.clearInterval(this.interval)
                }
            },
            startTimer() {
                this.stopTimer();
                this.interval = window.setInterval(() => {
                    this.getTarget()
                }, 1000)
            }
        },
        mounted() {
            if (this.$route.params.id != null)
                this.get_group('https://secretsanta2020.herokuapp.com/subscription/' + this.$route.params.id);
            this.startTimer()
        },
        beforeDestroy() {
            this.stopTimer()
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