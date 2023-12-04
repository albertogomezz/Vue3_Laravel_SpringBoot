import Api from "../Api";
import secrets from "../../secrets";

export default {
   
    FormatFilters(params) {
        let params_ = [];
        Object.entries(params).forEach(item => {
            if (item[0] === 'sports' && item[1].length > 0) {
                const sports = item[1].map(item => `sports=${item}`).join('&');
                params_.push(sports)
            } else if (item[1] != null) {
                params_.push(`${item[0]}=${item[1]}`);
            }
        });
        // console.log(params_.join('&'));
        return params_.join('&')
    },

    GetPistas(params) {
        return Api(secrets.URL_SPRING).get(`pistas?${this.FormatFilters(params)}`);
    },
    GetOnePista(id) {
        return Api(secrets.URL_SPRING).get('pistas/' + id);
    },
    GetPistasPaginate(params) {
        return Api(secrets.URL_SPRING).get(`pistasPaginate?${this.FormatFilters(params)}`);
    },//GetMesasPaginate
}