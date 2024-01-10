import Api from "../Api";
import secrets from "../../secrets";

export default {

    // ListReservationUser() {
    //     return Api(secrets.URL_SPRING).get(`listReservation`);
    // },//ListReservationUser
    // ListReservationsOfMesa(mesa_id) {
    //     return Api(secrets.URL_SPRING).get(`listReservation/${mesa_id}`);
    // },//ListReservationsOfMesa
    CreateReservation(data) {
        return Api(secrets.URL_SPRING).post(`reservation/${data.pista_id}`, {"date":data.date});
    },//CreateReservation
    FindReservationsUserByState(state) {
        return Api(secrets.URL_SPRING).get(`reservations/${state}`);
    },//CreateReservation
    UpdateReservation(data, id) {
        return Api(secrets.URL_SPRING).put(`reservation/${id}`, {"date":data});
    },//UpdateReservation
    DeleteReservation(id) {
        return Api(secrets.URL_SPRING).delete(`reservation/${id}`);
    },//DeleteReservation
    CancelReservation(id) {
        return Api(secrets.URL_SPRING).put(`reservation_cancel/${id}`);
    },//DeleteReservation
}//export