import { ref } from 'vue';
import ReservationService from "../../services/client/ReservationServiceClient";
import { createToaster } from "@meforma/vue-toaster";


const toaster = createToaster({ "position": "top-right", "duration": 1500 });
const reservations = ref([]);

export const GetReservationsUserByState = async (estado) => {
    try {
        const res = await ReservationService.FindReservationsUserByState(estado);
        reservations.value = res.data;
        // console.log(reservations.value);
    } catch (error) {
        console.error(error);
    }
    return reservations;
}

export const UseUpdateReservation = async (data, id) => {
    try {
        // console.log(data, id);
        const res = await ReservationService.UpdateReservation(data, id);
        toaster.success("Reservation updated successfully");
    } catch (error) {
        console.error(error);
        toaster.error("Failed to update reservation");
    }
}

export const UseDeleteReservations = async (id) => {
    try {
        const res = await ReservationService.DeleteReservation(id);
    } catch (error) {
        console.error(error);
    }
}

export const UseCancelReservations = async (id) => {
    try {
        const res = await ReservationService.CancelReservation(id);
    } catch (error) {
        console.error(error);
    }
}

export const UseAnularReservations = async (id) => {
    try {
        const res = await ReservationService.CancelReservation(id);
    } catch (error) {
        console.error(error);
    }
}