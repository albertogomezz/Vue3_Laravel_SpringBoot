import Vuex from "vuex";

import { pistaAdmin } from './modules/admin/pistaAdmin.js';
import { pistaClient } from './modules/client/pistaClient.js';

import { sportClient } from "./modules/client/sportClient.js";
import { sportAdmin } from './modules/admin/sportAdmin.js';
import { reservationAdmin } from './modules/admin/reservationAdmin.js';

import { user } from './modules/client/user.js';


export default Vuex.createStore({

    modules: {
        pistaAdmin: pistaAdmin,
        sportAdmin: sportAdmin,
        reservationAdmin: reservationAdmin,
        pistaClient: pistaClient,
        sportClient: sportClient,
        user: user
    }
});