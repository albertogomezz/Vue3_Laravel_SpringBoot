import Api from "../Api";
import secrets from "../../secrets";

export default {

    GetSports() {
        return Api(secrets.URL_LARAVEL).get('sports');
    },
    GetOneSport(id) {
        return Api(secrets.URL_LARAVEL).get('sports/' + id);
    },
    DeleteSport(id) {
        return Api(secrets.URL_LARAVEL).delete(`sports/${id}`)
    },
    UpdateOneSport(sport) {
        return Api(secrets.URL_LARAVEL).put(`sports/${sport.id}`, sport)
    },
    CreateOneSport(sport) {
        return Api(secrets.URL_LARAVEL).post(`sports`, sport)
    },
}