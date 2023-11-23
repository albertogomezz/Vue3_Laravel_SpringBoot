import Constant from '../../../Constant';
import SportServiceClient from "../../../services/client/SportServiceClient"

export const sportClient = {
    namespaced: true,
    mutations: {
        [Constant.GET_SPORTS]: (state, payload) => {
            if (payload) {
                state.sports = payload;
            }
        },
        [Constant.GET_ONE_SPORT]: (state, payload) => {
            if (payload) {
                state.OneSport = payload;
            }
        },
    },//mutations   
    actions: {
        [Constant.GET_SPORTS]: async (store) => {
            try {
                const response = await SportServiceClient.GetSports();
                // console.log(response.data);
                store.commit(Constant.GET_SPORTS, response.data);
            } catch (error) {
                console.error(error)
            }
        },
        [Constant.GET_ONE_SPORT]: async (store, payload) => {
            const response = await SportServiceClient.GetOneSport(payload);
            store.commit(Constant.GET_ONE_SPORT, response.data.data);
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