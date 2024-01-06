<template>
    <div class="row">
        <div class="col-lg-8 offset-lg-3">
            <div class="table-responsive">
                <DataTable :data="reservations" :columns="columns" :options="{responsive: true, autoWidth: false, dom: 'Bfrtip' }" class="table table-striped table-bordered display" >
                    <thead>
                    <tr>
                        <th>ID</th>
                        <th>User ID</th>
                        <th>Pista ID</th>
                        <th>Date</th>
                        <th>State</th>
                        <th>Change state</th>
                        <th>Modify</th>
                    </tr>
                </thead>
                <!-- <tbody class="text-center">
                    <tr v-for="reservation in reservations">
                        <td>{{ reservation.id }}</td>
                        <td>{{ reservation.user_id }}</td>
                        <td>{{ reservation.pista_id }}</td>
                        <td>{{ reservation.date }}</td>
                        <td>{{ reservation.state }}</td>
                        <td>
                            <button class="pulse" @click="ConfirmReservation(reservation.id)">Confirm Reservation</button>
                            <br>
                            <button class="pulse" @click="CancelReservation(reservation.id)">Cancel Reservation</button>
                        </td>
                        <td>
                            <button class="pulse" @click="updatePista(reservation.id)">Update Reservation</button>
                            <br>
                            <button class="pulse" @click="deletePista(reservation.id)">Delete Reservation</button>
                        </td>
                    </tr>
                </tbody> -->
                </DataTable>
            </div>
            
        </div>
    
    </div>
</template>

<script>
import { useStore } from 'vuex';
import { useRouter } from 'vue-router';
import Constant from '../Constant';
import { createToaster } from "@meforma/vue-toaster";
import DataTable from 'datatables.net-vue3';
import DataTableLib from 'datatables.net-bs5';
import ButtonsHtml5 from 'datatables.net-buttons/js/buttons.html5';
import Buttons from 'datatables.net-buttons-bs5';
import JsZip from 'jszip';

window.JsZip = JsZip;
DataTable.use(DataTableLib)
DataTable.use(Buttons)
// DataTable.use(ButtonsHtml5)


export default {
    components: { DataTable },

    data(){
        return {
            columns: [

                { title: "ID", data: "id" },
                { title: "User ID", data: "user_id" },
                { title: "Pista ID", data: "pista_id" },
                { title: "Date", data: "date" },
                { title: "State", data: "state" },
                { 
                title: "Change state", 
                render: function(data, type, row, meta) {
                    return `<Buttons onclick="ConfirmReservation('${row.id}')">Change state</Buttons> 
                    <br>
                    <Buttons onclick="ConfirmReservation('${row.id}')">Change state</Buttons>`;
                }
            },
            { 
                title: "Modify", 
                render: function(data, type, row, meta) {
                    return `<button onclick="updateReservation('${row.id}')">Update</button>
                    <br>
                    <button onclick="deleteReservation('${row.id}')">Delete</button>`;
                }
            },
            ],
        }
    },
    props: {
        reservations: Object,
    },
    setup() {

        const toaster = createToaster({ "position": "top-right", "duration": 2000 });
        const store = useStore();
        const router = useRouter();


        const deleteReservation = (id) => {
            // store.dispatch(`pistaAdmin/${Constant.DELETE_ONE_PISTA}`, { id })
            // toaster.success('Pista Deleted Successfully');
            // router.push({ name: "listPistas" })
        }
        const updateReservation = (id) => {
            // console.log(id);
            // router.push({ name: "updatePista", params: { id } })
        }
        const ConfirmReservation = (id) => {
            // store.dispatch(`pistaAdmin/${Constant.CONFIRM_RESERVATION}`, { id })
            // toaster.success('Reservation Confirmed Successfully');
            // router.push({ name: "listPistas" })
        }
        const CancelReservation = (id) => {
            // store.dispatch(`pistaAdmin/${Constant.CANCEL_RESERVATION}`, { id })
            // toaster.success('Reservation Cancelled Successfully');
            // router.push({ name: "listPistas" })
        }
        return { 
            deleteReservation
            , updateReservation
            , ConfirmReservation
            , CancelReservation
        }
    }
}
</script>
<style scoped>
@import 'datatables.net-dt';
</style>