import Constant from '../../../Constant';
import SportServiceAdmin from "../../../services/admin/SportServiceAdmin"

export const sportAdmin = {
    namespaced: true,
    mutations: {
        [Constant.GET_SPORTS]: (state, payload) => {
            if (payload) {
                state.sports = payload;
            }
        },
        [Constant.DELETE_ONE_SPORT]: (state, payload) => {
            if (payload) {
                state.sports = state.sports.filter(itemSport => itemSport.id === payload); 
            }
        },
        [Constant.GET_ONE_SPORT]: (state, payload) => {
            if (payload) {
                // console.log(payload);
                state.OneSport = payload;
            }
        },
        [Constant.UPDATE_ONE_SPORT]: (state, payload) => {
            // console.log(payload);
            if (payload) {
                let index = (state.sports || []).findIndex((item) => item.id == payload.id);
                if (index !== -1) {
                    state.sports[index] = payload;
                }
            }
        },
        [Constant.CREATE_ONE_SPORT]: (state, payload) => {
            if (payload) {
                state.sports.push({ ...payload });
            }
        },
    },//mutations
    actions: {
        [Constant.GET_SPORTS]: async (store) => {
            try {
                const response = await SportServiceAdmin.GetSports();
                
                //for per a cambiar el tipus de dada de is_reserved
                for (let i = 0; i < response.data.data.length; i++) {
                    response.data.data[i].is_reserved = Boolean(response.data.data[i].is_reserved); 
                }

                store.commit(Constant.GET_SPORTS, response.data.data);
            } catch (error) {
                console.error(error)
            }
        },
        [Constant.DELETE_ONE_SPORT]: async (store, payload) => {
            try {
                // console.log(payload.id);
                await SportServiceAdmin.DeleteSport(payload.id);
                store.commit(Constant.DELETE_ONE_SPORT, payload)
            } catch (error) {
                console.error(error)
            }
        },
        [Constant.GET_ONE_SPORT]: async (store, payload) => {

            const response = await SportServiceAdmin.GetOneSport(payload);
            // console.log(response.data.data);
            store.commit(Constant.GET_ONE_SPORT, response.data.data);
            
        },
        [Constant.UPDATE_ONE_SPORT]: async (store, payload) => {
            try {
                if (typeof payload.is_reserved == 'number') {
                    payload.is_reserved = Boolean(!payload.is_reserved);
                }
                const response = await SportServiceAdmin.UpdateOneSport(payload);
                if (response.status == 200) {
                    store.commit(Constant.UPDATE_ONE_SPORT, payload);
                }
            } catch (error) {
                console.error(error)
            }
        },
        [Constant.CREATE_ONE_SPORT]: async (store, payload) => {
            try {
                const response = await SportServiceAdmin.CreateOneSport(payload);
                if (response.status == 201) {
                    store.commit(Constant.CREATE_ONE_SPORT, response.data.data);
                }
            } catch (error) {
                console.error(error)
            }
        },
    },//actions
    getters: {
        getSports(state) {
            return state.sports;
        },
        getOneSport(state) {
            return state.OneSport;
        },
    }//getters
}//export