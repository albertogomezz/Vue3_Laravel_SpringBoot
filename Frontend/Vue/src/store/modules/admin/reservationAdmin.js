import Constant from '../../../Constant';
import ReservationServiceAdmin from '../../../services/admin/ReservationServiceAdmin.js';
import { createToaster } from "@meforma/vue-toaster";
import router from '../../../router/index.js'
const toaster = createToaster({ "position": "top-right", "duration": 2300 });

export const reservationAdmin = {
    namespaced: true,
    actions: {
        [Constant.GET_RESERVATIONS]: async (store) => {
            try {
                const response = await ReservationServiceAdmin.GetAll();
                if (response.status === 200) {
                    store.commit(Constant.GET_RESERVATIONS, response.data.data);
                    // console.log(response.data.data);
                }
            } catch (error) {
                console.error(error);
            }
        },
        [Constant.DELETE_RESERVATION]: async (store, payload) => {
            try {
                const response = await ReservationServiceAdmin.DeleteReservation(payload);
                if (response.status === 200) {
                    store.commit(Constant.DELETE_RESERVATION, payload);
                }
            } catch (error) {
                console.error(error);

            }
        },
        [Constant.GET_ONE_RESERVATION]: async (store, payload) => {

            try {
                const data = store.state.reservations ?? [];
                const index = data.findIndex(item => item.id == payload);
                if (index === -1) {
                    const response = await ReservationServiceAdmin.GetOne(payload);
                    if (response.status === 200) {
                        store.commit(Constant.GET_ONE_RESERVATION, response.data.data);
                    }
                } else {
                    store.commit(Constant.GET_ONE_RESERVATION, store.state.reservations[index]);
                }
            } catch (error) {
                console.error(error);
            }
        },
        [Constant.UPDATE_RESERVATION]: async (store, payload) => {
            try {
                const response = await ReservationServiceAdmin.UpdateReservation(payload);
                if (response.status == 200) {
                    store.commit(Constant.UPDATE_RESERVATION, payload);
                }
            } catch (error) {
                const status = error.response.status ?? 0;
                if (status === 404) {
                    toaster.error('Need to make a change');
                }
                if (status === 304) {
                    toaster.error('The table is already reserved that day and in that type');
                }
            }
        },
    },//mutations
    mutations: {
        [Constant.GET_RESERVATIONS]: (state, payload) => {
            if (payload) {
                state.reservations = payload;
                // console.log(state.reservations);
            }
        },
        [Constant.DELETE_RESERVATION]: (state, payload) => {
            if (payload) {
                state.reservations = state.reservations.filter(item => item.id !== payload);
            }
        },
        [Constant.GET_ONE_RESERVATION]: (state, payload) => {
            if (payload) {
                state.reservation = payload;
            }
        },
        [Constant.UPDATE_RESERVATION]: (state, payload) => {
            if (payload) {
                const data = state.reservations ?? []
                const index = data.findIndex(item => item.id === payload.id);
                if (index !== -1) {
                    payload.accepted = Number(payload.accepted);
                    state.reservations[index] = payload;
                }
                router.push({ name: 'reservationsList' });
            }
        },
    },//actions
    getters: {
        GetReservations(state) {
            return state.reservations
        },
        GetOneReservation(state) {
            return state.reservation
        }
    }//getters
}//export