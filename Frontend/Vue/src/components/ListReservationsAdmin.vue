<template>
    <div class="table-container">
        <table class="table table-striped table table-hover">
                <thead>
                    <tr class="table-active">
                        <th class="table-active" scope="col">ID</th>
                        <th class="table-active" scope="col">User ID</th>
                        <th class="table-active" scope="col">Pista ID</th>
                        <th class="table-active" scope="col">Date</th>
                        <th class="table-active" scope="col">State</th>
                        <th class="table-active" scope="col">Change state</th>
                        <th class="table-active" scope="col">Modify</th>
                    </tr>
                </thead>
                <tbody class="text-center">
                    <tr v-for="reservation in reservations">
                        <td>{{ reservation.id }}</td>
                        <td>{{ reservation.user_id }}</td>
                        <td>{{ reservation.pista_id }}</td>
                        <td>{{ reservation.date }}</td>
                        <td>{{ GetState(reservation.state)}}</td>
                        <td v-if="reservation.state === 0">
                            <button class="btn btn-success arriba" @click="ConfirmReservation(reservation.id)"><font-awesome-icon icon="check" /></button>
                            <br>
                            <button class="btn btn-danger" @click="CancelReservation(reservation.id)"><font-awesome-icon icon="x" /></button>
                        </td>
                        <td v-else="reservation.state !== 0">
                        </td>
                        <td>
                            <button type="button" class="btn btn-primary arriba" @click="visible = true">Update <font-awesome-icon icon="pen-to-square" /></button>
                            <!-- <Button label="Update" class="btn btn-primary arriba"  /> -->
                            <br>                                            <!-- @click="updateReservation(reservation.id)" -->
                            <button class="btn btn-danger" @click="deleteReservation(reservation.id)">Delete <font-awesome-icon icon="trash" /></button>
                        </td>
                    </tr>
                </tbody>
            </table>
    </div>
    <Dialog v-model:visible="visible" modal header="Update Reservation">
    <form @submit.prevent="updateReservation">
        <div class="form-group">
            <label for="reservationDate">Date</label>
            <input type="date" id="reservationDate"  class="form-control">
        </div>
        <div class="form-group">
            <label for="reservationTime">Time</label>
            <input type="time" id="reservationTime" class="form-control">
        </div>
        <button type="submit" class="btn btn-primary">Update</button>
    </form>
    <h1>{{ stateOne.reservation }}</h1>
</Dialog>
</template>

<script>
import { useStore } from 'vuex';
import { useRouter } from 'vue-router';
import Constant from '../Constant';
import { reactive,ref } from "vue";
import { computed } from 'vue';
import { createToaster } from "@meforma/vue-toaster";
import DataTable from 'datatables.net-vue3';

export default {

    props: {
        reservations: Object,
    },
    data() {
        return {
            visible: false,
            date: null,
        }
    },
    setup() {

        const toaster = createToaster({ "position": "top-right", "duration": 2000 });
        const store = useStore();
        const router = useRouter();

        function GetState(reservation){
            if(reservation == 0){
                return "Pending";
            }else if(reservation == 1){
                return "Confirmed";
            }else if(reservation == 2){
                return "Canceled";
            }
        }

        //ACTIONS
        const deleteReservation = async (id) => {
            console.log(id);
            await store.dispatch(`reservationAdmin/${Constant.DELETE_RESERVATION}`, { id })
            toaster.success('Pista Deleted Successfully');
            router.push({ name: "listReservations" })
        }
        const updateReservation = async (id) => {
            console.log(id);
            await store.dispatch(`reservationAdmin/${Constant.GET_ONE_RESERVATION}`, id);
            const newreservation = store.state.reservationAdmin.reservation;
            router.push({ name: "listReservations" })
        }
        //STATE
        const ConfirmReservation =  async (id) => {
            console.log(id);
            await store.dispatch(`reservationAdmin/${Constant.UPDATE_RESERVATION}`, { id, state: 1 });
            toaster.success('Reservation Confirmed Successfully');
            router.push({ name: "listReservations" });
            window.location.reload();

        }
        const CancelReservation = async (id) => {
            console.log(id);
            await store.dispatch(`reservationAdmin/${Constant.UPDATE_RESERVATION}`, { id, state: 2 });
            toaster.success('Reservation Canceled Successfully');
            router.push({ name: "listReservations" });
            window.location.reload();
        }


    
        const stateOne = reactive({
            reservation: computed(() => store.getters["reservationAdmin/getOneReservation"])
        })

        const update_emit = (pista) =>{
            // console.log(pista);
            store.dispatch(`pistaAdmin/${Constant.UPDATE_ONE_PISTA}`, pista);
            toaster.success('Pista Updated Successfully');
            router.push({ name: "listPistas" })
        }

        return { 
            deleteReservation
            , updateReservation
            , ConfirmReservation 
            , CancelReservation
            , GetState
            , store
            , stateOne
            , update_emit
        }
    }
}
</script>
<style scoped>
div.table-container table.table{
    border-radius: 10px !important;
    margin: 0 auto;
    width: 86%;
    clear: both;
    border-collapse: collapse;
     table-layout: fixed; 
    word-wrap: break-word;
    margin: 40px;
    /*box-shadow: 0px 0px 10px 0px #000000; */
}
    .table-container {
    display: flex;
    justify-content: center;
    align-items: center;
    height: 100%;
}
.arriba{
    margin: 10px;
    align-items: centerz;
}
.table-active{
    background-color: rgb(47, 132, 207);
    color: #ffffff;
    text-align: center;
}
</style>