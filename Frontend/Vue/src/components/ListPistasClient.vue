<template>
    <div class="container">
      <div class="card float-right" v-for="pista in pistas" :key="pista.id">
        <div class="row">
          <div class="col-sm-5">
            <img class="d-block w-100 img-card" :src="pista.image" alt="Pista Image" />
          </div>
          <div class="col-sm-7">
            <div class="card-block">
              <h4 class="card-title">{{ pista.description }}</h4>
              <p>{{ pista.pista_id }}</p>
              <button class="btn btn-primary btn-sm pulse" @click="details(pista.id)">Detalles</button>
              <div class="row tarjetas">
                <div v-for="deporte in pista.sports" :key="deporte.id" class="col-md-5">
                  <div class="mini-card">
                    <div class="mini-card-body">
                      <h3 class="mini-card-title">{{ deporte.sport_name }}</h3>
                      <p class="mini-card-price">{{ deporte.price }} €/h</p>
                      <div v-if="user.auth && user.token">
                        <button class="btn btn-primary btn-sm float-right" @click="openModal(pista.id)">Reservar ahora</button>
                        <Dialog v-if="activeModal === pista.id" v-model:visible="visible" modal header="Selecciona tu día">
                          <div>
                            <Calendar v-model="date" inline @dateClick="handleDateClick" />
                          </div>
                          <div class="mt-2">
                            <button class="btn btn-primary btn-sm float-right" @click="handleReservation(pista.id)">Reservar</button>
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
              <br />
            </div>
          </div>
        </div>
      </div>
    </div>
  </template>

<script>
import { reactive,ref } from "vue";
import { useRouter } from 'vue-router'
import { createToaster } from "@meforma/vue-toaster";
import ReservationService from "../services/client/ReservationServiceClient";

const toaster = createToaster({ "position": "top-right", "duration": 1500 });

export default {
    props: {
        pistas: Object,
    },
    data() {
        return {
            visible: false,
            activeModal: null,
            date: null,
        }
    },
    setup(props){

        const visible = ref(false);
        const date = ref();
        const selectedDate = ref();

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
        openModal(pistaId) {
            this.visible = true;
            this.activeModal = pistaId;
        },
        handleReservation(pista_id) {
            if (this.date) {
                let info_reserva = {
                    "date": this.formatDate(this.date),
                    "pista_id": pista_id,
                }
                // console.log(info_reserva);
                ReservationService.CreateReservation(info_reserva)
                    .then(() => {
                        this.visible = false;
                        toaster.success(`Reserva solicitada para la fecha ${info_reserva.date}`);
                    })
                    .catch(() => {
                        this.visible = false;
                        toaster.warning(`No se ha podido realizar la reserva para la fecha ${info_reserva.date}`);
                    }
                );
            }
        },
        formatDate(date) {
            const year = date.getFullYear();
            const month = String(date.getMonth() + 1).padStart(2, '0');
            const day = String(date.getDate()).padStart(2, '0');

            return `${year}-${month}-${day}`;
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