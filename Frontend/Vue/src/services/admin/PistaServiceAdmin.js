import Api from "../Api";
import secrets from "../../secrets";

export default {

    GetPistas() {
        return Api(secrets.URL_LARAVEL).get('pistas');
    },
    GetOnePista(id) {
        return Api(secrets.URL_LARAVEL).get('pistas/' + id);
    },
    DeleteMesa(id) {
        return Api(secrets.URL_LARAVEL).delete(`pistas/${id}`)
    },
    UpdateOnePista(pista) {
        return Api(secrets.URL_LARAVEL).put(`pistas/${pista.id}`, pista)
    },
    CreateOnePista(pista) {
        return Api(secrets.URL_LARAVEL).post(`pistas`, pista)
    },
}