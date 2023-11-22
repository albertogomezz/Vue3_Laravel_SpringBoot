import Constant from '../../../Constant';
import PistaServiceClient from "../../../services/client/PistaServiceClient"

export const pistaClient = {
    namespaced: true,
    mutations: {
        [Constant.GET_PISTAS]: (state, payload) => {
            if (payload) {
                state.pistas = payload;
            }
        },
        [Constant.GET_ONE_PISTA]: (state, payload) => {
            if (payload) {
                state.OnePista = payload;
            }
        },
    },//mutations   
    actions: {
        [Constant.GET_PISTAS]: async (store) => {
            try {
                const response = await PistaServiceClient.GetPistas();
                // console.log(response.data);
                store.commit(Constant.GET_PISTAS, response.data);
            } catch (error) {
                console.error(error)
            }
        },
        [Constant.GET_ONE_PISTA]: async (store, payload) => {
            // console.log(payload);
            const response = await PistaServiceClient.GetOnePista(payload);
            store.commit(Constant.GET_ONE_PISTA, response.data);
        },
    },//actions
    getters: {
        getPistas(state) {
            return state.pistas;
        },
        getOnePista(state) {
            return state.OnePista;
        },
    }//getters
}//export