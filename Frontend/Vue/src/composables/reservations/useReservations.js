import { ref } from 'vue';
import ReservationService from "../../services/client/ReservationServiceClient";

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