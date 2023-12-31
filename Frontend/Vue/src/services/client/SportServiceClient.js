import Api from "../Api";
import secrets from "../../secrets";

export default {

    GetSports() {
        return Api(secrets.URL_SPRING).get('sports');
    },
    GetOneSport(id) {
        return Api(secrets.URL_SPRING).get('sports/' + id);
    },
    GetSportInfinite(page, limit) {
        return Api(secrets.URL_SPRING).get('sportInfinite', { params: { page: page, limit: limit } });
    },
}