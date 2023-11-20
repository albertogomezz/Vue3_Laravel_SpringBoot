import Api from "../Api";
import secrets from "../../secrets";

export default {

    GetPistas() {
        return Api(secrets.URL_SPRING).get('pistas');
    },
    GetOnePista(id) {
        return Api(secrets.URL_SPRING).get('pistas/' + id);
    },
}