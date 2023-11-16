import Api from "../Api";
import secrets from "../../secrets";

export default {

    GetPistas() {
        return Api(secrets.URL_LARAVEL).get('pistas');
    },//GetMesas
    // GetOneMesa(id) {
    //     return Api(secrets.URL_LARAVEL).get('mesa/' + id);
    // },//GetOneLinkMesa
    // DeleteMesa(id) {
    //     return Api(secrets.URL_LARAVEL).delete(`mesa/${id}`)
    // },
    // UpdateOneMesa(mesa) {
    //     return Api(secrets.URL_LARAVEL).put(`mesa/${mesa.id}`, mesa)
    // },
    // CreateOneMesa(mesa) {
    //     return Api(secrets.URL_LARAVEL).post(`mesa`, mesa)
    // },
}