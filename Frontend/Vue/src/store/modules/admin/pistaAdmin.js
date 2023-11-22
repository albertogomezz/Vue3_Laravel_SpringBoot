import Constant from '../../../Constant';
import PistaServiceAdmin from "../../../services/admin/PistaServiceAdmin"

export const pistaAdmin = {
    namespaced: true,
    mutations: {
        [Constant.GET_PISTAS]: (state, payload) => {
            if (payload) {
                state.pistas = payload;
            }
        },
        [Constant.DELETE_ONE_PISTA]: (state, payload) => {
            if (payload) {
                state.pistas = state.pistas.filter(itemPista => itemPista.id === payload); 
            }
        },
        [Constant.GET_ONE_PISTA]: (state, payload) => {
            if (payload) {
                // console.log(payload);
                state.OnePista = payload;
            }
        },
        [Constant.UPDATE_ONE_PISTA]: (state, payload) => {
            // console.log(payload);
            if (payload) {
                let index = (state.pistas || []).findIndex((item) => item.id == payload.id);
                if (index !== -1) {
                    state.pistas[index] = payload;
                }
            }
        },
        [Constant.CREATE_ONE_PISTA]: (state, payload) => {
            if (payload) {
                state.pistas.push({ ...payload });
            }
        },
    },//mutations
    actions: {
        [Constant.GET_PISTAS]: async (store) => {
            try {
                const response = await PistaServiceAdmin.GetPistas();
                
                //for per a cambiar el tipus de dada de is_reserved
                for (let i = 0; i < response.data.data.length; i++) {
                    response.data.data[i].is_reserved = Boolean(response.data.data[i].is_reserved); 
                }

                store.commit(Constant.GET_PISTAS, response.data.data);
            } catch (error) {
                console.error(error)
            }
        },
        [Constant.DELETE_ONE_PISTA]: async (store, payload) => {
            try {
                // console.log(payload.id);
                await PistaServiceAdmin.DeleteMesa(payload.id);
                store.commit(Constant.DELETE_ONE_PISTA, payload)
            } catch (error) {
                console.error(error)
            }
        },
        [Constant.GET_ONE_PISTA]: async (store, payload) => {

            const response = await PistaServiceAdmin.GetOnePista(payload);
            // console.log(response.data.data);
            store.commit(Constant.GET_ONE_PISTA, response.data.data);
            
        },
        [Constant.UPDATE_ONE_PISTA]: async (store, payload) => {
            try {
                if (typeof payload.is_reserved == 'number') {
                    payload.is_reserved = Boolean(!payload.is_reserved);
                }
                const response = await PistaServiceAdmin.UpdateOnePista(payload);
                if (response.status == 200) {
                    store.commit(Constant.UPDATE_ONE_PISTA, payload);
                }
            } catch (error) {
                console.error(error)
            }
        },
        [Constant.CREATE_ONE_PISTA]: async (store, payload) => {
            try {
                const response = await PistaServiceAdmin.CreateOnePista(payload);
                if (response.status == 201) {
                    store.commit(Constant.CREATE_ONE_PISTA, response.data.data);
                }
            } catch (error) {
                console.error(error)
            }
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