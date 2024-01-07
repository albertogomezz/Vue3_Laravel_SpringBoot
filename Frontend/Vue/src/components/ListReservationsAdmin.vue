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
                            <!-- <button type="button" class="btn btn-primary arriba" @click="updateReservation(reservation.id)" data-target="#exampleModalScrollable">Update <font-awesome-icon icon="pen-to-square" /></button> -->
                            <Button label="Show" icon="pi pi-external-link" @click="visible = true" />
                                <Dialog v-model:visible="visible" modal header="Header" :style="{ width: '50rem' }" :breakpoints="{ '1199px': '75vw', '575px': '90vw' }">
                                    <template #header>
                                        <div class="inline-flex align-items-center justify-content-center gap-2">
                                            <Avatar image="https://primefaces.org/cdn/primevue/images/avatar/amyelsner.png" shape="circle" />
                                            <span class="font-bold white-space-nowrap">Amy Elsner</span>
                                        </div>
                                    </template>
                                    <p class="m-0">
                                        Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.
                                        Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.
                                    </p>
                                    <template #footer>
                                        <Button label="Ok" icon="pi pi-check" @click="visible = false" autofocus />
                                    </template>
                                </Dialog>
                            <br>
                            <button class="btn btn-danger" @click="deleteReservation(reservation.id)">Delete <font-awesome-icon icon="trash" /></button>
                        </td>
                    </tr>
                </tbody>
            </table>
    </div>
</template>

<script>
import { useStore } from 'vuex';
import { useRouter } from 'vue-router';
import Constant from '../Constant';
import { reactive,ref } from "vue";
import { computed } from 'vue';
import { createToaster } from "@meforma/vue-toaster";
import DataTable from 'datatables.net-vue3';
import 'primevue/resources/themes/saga-blue/theme.css'; // theme
import 'primevue/resources/primevue.min.css'; // core css
import 'primeicons/primeicons.css'; // icons
import { Button } from 'primevue/button';
import { Dialog } from 'primevue/dialog';
export default {

    props: {
        reservations: Object,
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
            const reservation = store.state.reservationAdmin.reservation;
            console.log(reservation);
        }
        //STATE
        const ConfirmReservation = (id) => {
            console.log(id);
            router.push({ name: "confirmReservations", params: { id } })
        }
        const CancelReservation = (id) => {
            console.log(id);
            // router.push({ name: "detailsPista", params: { id } })
        }


    
        const stateOne = reactive({
            pista: computed(() => store.getters["pistaAdmin/getOnePista"])
            // console.log(stateOne.pista);
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