<template>
     <body class="">
       <div class="container" v-for="pista in pistas">
            <div class="card float-right">
                <div class="row">
                    <div class="col-sm-5">
                    <img class="d-block w-100 img-card" :src="pista.image">
                    </div>
                    <div class="col-sm-7">
                    <div class="card-block">
                        <h4 class="card-title">{{  pista.description }}</h4>
                        <p>{{ pista.pista_id }}</p>
                        <button class="btn btn-primary btn-sm pulse"  @click="details(pista.id)">Detalles</button>
                        <div class="row tarjetas">
                            <div v-for="deporte in pista.sports" :key="deporte.id" class="col-md-5">
                                <div class="mini-card">
                                    <div class="mini-card-body">
                                        <h3 class="mini-card-title">{{ deporte.sport_name }}</h3>
                                        <p class="mini-card-price">{{ deporte.price }} €/h</p>
                                        <div v-if="user.auth && user.token">
                                            <Button label="Reservar ahora" class="btn btn-primary btn-sm float-right" @click="visible = true" />
                                            <Dialog v-model:visible="visible" modal header="Selecciona tu día">
                                            <div>
                                                <Calendar v-model="date" inline showWeek @dateClick="handleDateClick" />
                                            </div>
                                            <div class="mt-2">
                                                <button class="btn btn-primary btn-sm float-right" @click="handleReservation">Reservar</button>
                                            </div>
                                            </Dialog>
                                        </div>
                                        <div v-else>
                                            <button class="btn btn-primary btn-sm float-right" @click="redirect_login()">Reservar ahora</button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <br>
                    </div>
                    </div>
                </div>
            </div> 
        </div>
    </body>
</template>

<script>
import { reactive,ref } from "vue";
import { useRouter } from 'vue-router'
import { createToaster } from "@meforma/vue-toaster";

export default {
    props: {
        pistas: Object,
    },
    data() {
        return {
            visible: false,
            date: null,
        }
    },
    setup(props){

        const visible = ref(false);
        const date = ref();
        const selectedDate = ref();

        const toaster = createToaster({ "position": "top-right", "duration": 1500 });
        const router = useRouter();
        
        const details = (id) => {
            router.push({ name: "detailsPista", params: { id } })
        }

        const redirect_login = () => {
            toaster.warning("Inicia sesión para poder reservar");
            setTimeout(() => {
                router.push({ name: "login" })
            }, 1500);
        }
        let user = reactive({
            auth: null,
            token: null,
        });
        user.auth = window.localStorage.getItem('isAuth');
        user.token = window.localStorage.getItem('token');

    return { details, user , redirect_login, visible, date, selectedDate }
    },
    methods: {
        handleReservation() {
            if (this.date) {
            // Formato de fecha deseado, puedes ajustarlo según tus necesidades
            const formattedDate = this.formatDate(this.date);

            // Ahora puedes usar la variable formattedDate según tus necesidades
            console.log("Fecha seleccionada:", formattedDate);
            }
        },
        formatDate(date) {
            // Puedes usar bibliotecas como moment.js o simplemente el objeto Date de JavaScript para formatear la fecha
            // Ejemplo usando el objeto Date:
            const options = { year: 'numeric', month: 'numeric', day: 'numeric' };
            return new Date(date).toLocaleDateString(undefined, options);
        }
    }
}
</script>
<style lang="scss">
body {
  background-color:  #eee;
}
.title {
 
    margin-bottom: 50px;
    text-transform: uppercase;
}

.card-block {
    font-size: 1em;
    position: relative;
    margin: 0;
    padding: 1em;
    padding-bottom: 0;
    border: none;
    border-top: 1px solid rgba(34, 36, 38, .1);
    box-shadow: none;
     
}
.card {
    font-size: 1em;
    overflow: hidden;
    padding: 5;
    border: none;
    border-radius: .28571429rem;
    box-shadow: 0 1px 3px 0 #d4d4d5, 0 0 0 1px #d4d4d5;
    margin-top:20px;
}

.mini-card{
    font-size: 0.7em;
    text-align: center;
    border-radius: .28571429rem;
    box-shadow: 0 1px 3px 0 #d4d4d5, 0 0 0 1px #d4d4d5;
    margin-top:20px;
}

.mini-card .mini-card-body{
    padding: 0.5em;
    padding-bottom: 0.5em;
}

.btn {
    margin-top: auto;
}

.mini-card-price{
    font-size: 14px;
}

a{
    text-decoration: none;
    color: black;
    padding-bottom: 0;
}

.img-card{
    height: 336px;
    width: 200px;
}
.tarjetas{
    justify-content: center;
}

</style>