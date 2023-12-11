import Api from "../Api";
import secrets from "../../secrets";

export default {
   
    FormatFilters(params) {
        let params_ = [];
        Object.entries(params).forEach(item => {
                params_.push(`${item[0]}=${item[1]}`);
        });
        return params_.join('&')
    },

    GetPistas(params) {
        // return console.log(params);
        return Api(secrets.URL_SPRING).get(`pistas?${this.FormatFilters(params)}`);
    },
    GetOnePista(id) {
        return Api(secrets.URL_SPRING).get('pistas/' + id);
    },
}