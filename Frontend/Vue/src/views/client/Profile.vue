<template>
  <div>
    <header>
      <div class="container">
        <div class="profile">
          <div class="profile-image">
            <img :src="state.profile.photo" alt="" />
          </div>

          <div class="profile-user-settings">
            <h1 class="profile-user-name">
              Welcome back <span class="profile-user-name2">{{ state.profile.username }}</span>!
            </h1>
            <br />
            <div class="profile-email">{{ state.profile.email }}</div>
            <br />
          </div>

          <div class="profile-stats">
            <ul>
              <li @click="GetReservationsByEstado(1)" id="accepted">
                <span class="profile-stat-count"></span> Accepted
              </li>
              <li @click="GetReservationsByEstado(0)" id="pending">
                <span class="profile-stat-count"></span> Pending
              </li>
              <li @click="GetReservationsByEstado(2)" id="canceled">
                <span class="profile-stat-count"></span> Canceled
              </li>
            </ul>
          </div>
        </div>
        <div class="reservation-cards">
          <div class="alinear-texto" v-if="state.reservations.length === 0">
            No reservations to display.
          </div>
          <div v-else>
            <div v-for="reservation in state.reservations" :key="reservation.id" class="card">
              <div v-if="reservation.state === 2">
                <div class="card-info">
                  <h2>Pista : P{{ reservation.pista_id }}</h2>
                  <p>Date : {{ reservation.date }}</p>
                  <div class="buttons">
                    <button class="delete-button" @click="DeleteReservation(reservation.id)">Delete  <font-awesome-icon icon="trash"/></button>
                  </div>
                </div>
              </div>
              <div v-else-if="reservation.state === 0">
                <div class="card-info">
                  <h2>Pista : P{{ reservation.pista_id }}</h2>
                  <p>Date : {{ reservation.date }}</p>
                  <div class="buttons">
                    <button class="edit-button" @click="openDialog(reservation), reservation_id = reservation.id, visible = true">Edit Date <font-awesome-icon icon="pen-to-square" /></button>
                    <button class="delete-button" @click="CancelReservations(reservation.id)">Cancelar <font-awesome-icon icon="trash"/></button>
                  </div>
                </div>
              </div>
              <div v-else>
                <div class="card-info">
                  <h2>Pista : P{{ reservation.pista_id }}</h2>
                  <p>Date : {{ reservation.date }}</p>
                  <div class="buttons">
                    <button class="delete-button" @click="AnularReservations(reservation.id)">Anular</button>
                  </div>
                </div>
              </div>
            </div>
            <Dialog v-model:visible="visible" modal header="Update Reservation">
              <form @submit.prevent="updateReservation(reservation_id)">
                  <div class="form-group">
                    <label for="reservationDate">Date</label>
                    <input type="date" id="reservationDate" v-model="reservation.date" class="form-control">
                  </div>
                  <br>
                <button type="submit" @click="visible = false" class="edit-button">Update</button>
              </form>
            </Dialog>
          </div>
        </div>
      </div>
    </header>
  </div>
</template>

<script>
import { computed, reactive, onMounted, ref} from "vue";
import { useStore } from 'vuex';
import { createToaster } from "@meforma/vue-toaster";
import { GetReservationsUserByState, UseDeleteReservations, UseCancelReservations, UseAnularReservations, UseUpdateReservation } from '../../composables/reservations/useReservations';

const toaster = createToaster({ "position": "top-right", "duration": 1500 });

export default {
    data() {
      return {
        selectedReservation: null,
        visible: false,
        date: null,
        reservation_id: null,
      };
    },
    
    setup() {
      let estado = 1;
      let reservations = ref('');

      const store = useStore();

      function openDialog(reservation) {
            this.visible = true;
            this.reservation = { ...reservation };
      }

      const state = reactive({
          reservations: GetReservationsUserByState(estado),
          profile: computed(() => store.getters['user/GetProfile']),
      });

      const GetReservationsByEstado = async (estat) => {
        state.reservations = await GetReservationsUserByState(estat);
      };

      onMounted(async () => {
        state.reservations = await GetReservationsUserByState(estado);
      });

      const DeleteReservation = async (id) => {
        await UseDeleteReservations(id);
        toaster.success("Eliminada correctamente");
        estado = 2;
        state.reservations = await GetReservationsUserByState(estado);
      };

      const CancelReservations = async (id) => {
        await UseCancelReservations(id);
        toaster.success("Cancelada correctamente");
        estado = 0;
        state.reservations = await GetReservationsUserByState(estado);
      };

      const AnularReservations = async (id) => {
        await UseAnularReservations(id);
        toaster.success("Anulada correctamente");
        estado = 1;
        state.reservations = await GetReservationsUserByState(estado);
      };

      const updateReservation = async (id) => {
        try {
          var date = document.getElementById("reservationDate").value;
          await UseUpdateReservation(date, id);
          estado = 0;
          state.reservations = await GetReservationsUserByState(estado);
        } catch (error) {
          console.error("Error updating reservation:", error);
        }
      };

      return {
        state,
        GetReservationsByEstado,
        reservations,
        estado,
        DeleteReservation,
        CancelReservations,
        AnularReservations,
        updateReservation,
        openDialog,
      };
  },
}

</script>

<style scoped>
@import url("https://fonts.googleapis.com/css?family=Open+Sans:400,700&display=swap");

.selected {
  font-weight: bold;
}

.reservation-cards {
  display: flex;
  flex-wrap: wrap;
  justify-content: space-around;
}

.card {
  flex: 0 0 calc(33.333% - 20px);
  /* Ajusta el espacio y el tamaño según tus necesidades */
  background-color: #fff;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
  margin: 10px;
}

.alinear-texto {
  text-align: center;
}

.card img {
  width: 100px;
  height: 100px;
  object-fit: cover;
  border-radius: 4px 0 0 4px;
}

.card-info {
  padding: 10px;
  width: 825px;
  height: 150px;
}

.card-info h2 {
  margin: 0;
}

.card-info p {
  margin: 8px 0;
}

.buttons {
  display: flex;
}

.edit-button,
.delete-button {
  padding: 8px;
  margin-right: 8px;
  cursor: pointer;
  border: none;
  border-radius: 4px;
}

.edit-button {
  background-color: #3498db;
  color: #fff;
}

.delete-button {
  background-color: #e74c3c;
  color: #fff;
}

:root {
  font-size: 8px;
}

*,
*::before,
*::after {
  box-sizing: border-box;
}

body {
  font-family: "Open Sans", Arial, sans-serif;
  min-height: 100vh;
  background-color: #fafafa;
  color: #262626;
  padding-bottom: 3rem;
}

img {
  display: block;
}


.profile-email{
    font-size: 1.2rem;
    font-weight: 300;
    color: #949494;
    margin-left: 25px;
    padding: 0;
}
.container {
  max-width: 93.5rem;
  margin: 0 auto;
  padding: 0 2rem;
}

.btn {
  display: inline-block;
  font: inherit;
  background: none;
  border: none;
  color: inherit;
  padding: 0;
  cursor: pointer;
}

.btn:focus {
  outline: 0.5rem auto #4d90fe;
}

.visually-hidden {
  position: absolute !important;
  height: 1px;
  width: 1px;
  overflow: hidden;
  clip: rect(1px, 1px, 1px, 1px);
}

/* Profile Section */

.profile {
  padding: 5rem 0;
}

.profile::after {
  content: "";
  display: block;
  clear: both;
}

.profile-image {
  float: left;
  width: calc(33.333% - 1rem);
  display: flex;
  justify-content: center;
  align-items: center;
}

.profile-image img {
  border-radius: 50%;
  width: 172px;
  height: 172px;
}

.profile-user-settings,
.profile-stats,
.profile-bio {
  float: left;
  width: calc(66.666% - 2rem);
}

.profile-user-settings {
  margin-top: 1.1rem;
}


.profile-user-name {
  display: inline-block;
  font-size: 2.4rem;
  font-weight: 300;
}
.profile-user-name2 {
  display: inline-block;
  font-size: 2.4rem;
  font-weight: 600;
}
.profile-edit-btn {
  font-size: 1.2rem;
  line-height: 1.8;
  border: 0.1rem solid #dbdbdb;
  border-radius: 0.3rem;
  padding: 0 2.4rem;
  margin-left: 2rem;
  margin-bottom: 30px;
}

.profile-settings-btn {
  font-size: 1.2rem;
  margin-left: 1rem;
}

.profile-stats {
  margin-top: 2.3rem;
}

.profile-stats li {
  display: inline-block;
  font-size: 1.2rem;
  line-height: 1.5;
  margin-right: 4rem;
  cursor: pointer;
}

.profile-stats li:last-of-type {
  margin-right: 0;
}

.profile-bio {
  font-size: 1.2rem;
  font-weight: 400;
  line-height: 1.5;
  margin-top: 2.3rem;
}

.profile-real-name,
.profile-stat-count,
.profile-edit-btn {
  font-weight: 600;
}

@media screen and (max-width: 40rem) {
  .profile {
    display: flex;
    flex-wrap: wrap;
    padding: 4rem 0;
  }

  .profile::after {
    display: none;
  }

  .profile-image,
  .profile-user-settings,
  .profile-bio,
  .profile-stats {
    float: none;
    width: auto;
  }

  .profile-image img {
    width: 7.7rem;
  }

  .profile-user-settings {
    flex-basis: calc(100% - 10.7rem);
    display: flex;
    flex-wrap: wrap;
    margin-top: 1rem;
  }

  .profile-user-name {
    font-size: 1.8rem;
  }

  .profile-edit-btn {
    order: 1;
    padding: 0;
    text-align: center;
    margin-top: 1rem;
  }

  .profile-edit-btn {
    margin-left: 0;
  }

  .profile-bio {
    font-size: 1.1rem;
    margin-top: 1.5rem;
  }

  .profile-edit-btn,
  .profile-bio,
  .profile-stats {
    flex-basis: 100%;
  }

  .profile-stats {
    order: 1;
    margin-top: 1.5rem;
  }

  .profile-stats ul {
    display: flex;
    text-align: center;
    /* padding: 1.2rem 0; */
    border-top: 0.1rem solid #dadada;
    border-bottom: 0.1rem solid #dadada;
    margin-left: 0;
  }

  .profile-stats li {
    font-size: 1.05rem;
    flex: 1;
    margin: 0;
    margin-left: 0;
  }

  .profile-stat-count {
    display: block;
  }
}

@supports (display: grid) {
  .profile {
    display: grid;
    grid-template-columns: 1fr 2fr;
    grid-template-rows: repeat(3, auto);
    grid-column-gap: 3rem;
    align-items: center;
  }

  .profile-image {
    grid-row: 1 / -1;
  }

  .gallery {
    display: grid;
    grid-template-columns: repeat(auto-fit, minmax(22rem, 1fr));
    grid-gap: 2rem;
  }

  .profile-image,
  .profile-user-settings,
  .profile-stats,
  .profile-bio,
  .gallery-item,
  .gallery {
    width: auto;
    margin: 0;
  }

  @media (max-width: 40rem) {
    .profile {
      grid-template-columns: auto 1fr;
      grid-row-gap: 1.5rem;
    }

    .profile-image {
      grid-row: 1 / 2;
    }

    .profile-user-settings {
      display: grid;
      grid-template-columns: auto 1fr;
      grid-gap: 1rem;
    }

    .profile-edit-btn,
    .profile-stats,
    .profile-bio {
      grid-column: 1 / -1;
    }

    .profile-user-settings,
    .profile-edit-btn,
    .profile-settings-btn,
    .profile-bio,
    .profile-stats {
      margin: 0;
    }
  }
}
</style>
