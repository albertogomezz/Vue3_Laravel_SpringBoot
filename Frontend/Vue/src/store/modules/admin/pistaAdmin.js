import Constant from '../../../Constant';
import PistaServiceAdmin from "../../../services/admin/PistaServiceAdmin"

export const pistaAdmin = {
    namespaced: true,
    // state: { pista: {} },
    mutations: {
        [Constant.GET_PISTAS]: (state, payload) => {
            if (payload) {
                // console.log(payload);
                state.pistas = payload;
                // console.log(state);
            }
        },
        // [Constant.DELETE_ONE_PISTA]: (state, payload) => {
        //     if (payload) {
        //         state.pista = state.pista.filter((itemPista) => itemPista.id !== payload)
        //     }
        // },
        // [Constant.GET_ONE_PISTA]: (state, payload) => {
        //     if (payload) {
        //         state.OneMesa = payload;
        //     }
        // },
        // [Constant.UPDATE_ONE_PISTA]: (state, payload) => {
        //     if (payload) {
        //         payload.is_active = Number(payload.is_active)
        //         let index = (state.pista || []).findIndex((item) => item.id == payload.id);
        //         if (index !== -1) {
        //             state.pista[index] = payload;
        //         }
        //     }
        // },
        // [Constant.CREATE_ONE_PISTA]: (state, payload) => {
        //     if (payload) {
        //         state.pista.push({ ...payload });
        //     }
        // },
    },//mutations
    actions: {
        [Constant.GET_PISTAS]: async (store) => {
            try {
                const response = await PistaServiceAdmin.GetPistas();
                // console.log(response.data.data);
                store.commit(Constant.GET_PISTAS, response.data.data);
            } catch (error) {
                console.error(error)
            }
        },
        // [Constant.DELETE_ONE_PISTA]: async (store, payload) => {
        //     try {
        //         await MesaServiceDashboard.DeleteMesa(payload);
        //         store.commit(Constant.DELETE_ONE_PISTA, payload)
        //     } catch (error) {
        //         console.error(error)
        //     }
        // },
        // [Constant.GET_ONE_PISTA]: async (store, payload) => {
        //     try {
        //         if (store.state.pista != undefined) {
        //             const index = store.state.pista.findIndex(object => {
        //                 return object.id === parseInt(payload);
        //             });
        //             store.commit(Constant.GET_ONE_PISTA, store.state.pista[index]);
        //         } else {
        //             const response = await MesaServiceDashboard.GetOneMesa(payload);
        //             store.commit(Constant.INITIALIZE_ONE_STATE_MESA, response.data.data);
        //         }
        //     } catch (error) {
        //         console.error(error)
        //     }
        // },
        // [Constant.UPDATE_ONE_PISTA]: async (store, payload) => {
        //     try {
        //         if (typeof payload.is_active == 'number') {
        //             payload.is_active = Boolean(!payload.is_active);
        //         }
        //         const response = await MesaServiceDashboard.UpdateOneMesa(payload);
        //         if (response.status == 200) {
        //             store.commit(Constant.UPDATE_ONE_PISTA, payload);
        //         }
        //     } catch (error) {
        //         console.error(error)
        //     }
        // },
        // [Constant.CREATE_ONE_PISTA]: async (store, payload) => {
        //     try {
        //         payload.is_active = true;
        //         const response = await MesaServiceDashboard.CreateOneMesa(payload);
        //         if (response.status == 201) {
        //             store.commit(Constant.CREATE_ONE_PISTA, response.data.data);
        //         }
        //     } catch (error) {
        //         console.error(error)
        //     }
        // },
    },//actions
    getters: {
        getPistas(state) {
            // console.log(state.pistas);
            return state.pistas;
        },
        // getOnePista(state) {
        //     return state.OnePista;
        // },
    }//getters
}//export