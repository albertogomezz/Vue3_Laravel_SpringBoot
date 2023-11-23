import Vuex from "vuex";

import { pistaAdmin } from './modules/admin/pistaAdmin.js';
import { pistaClient } from './modules/client/pistaClient.js';
import { sportClient } from "./modules/client/sportClient.js";

import { sportAdmin } from './modules/admin/sportAdmin.js';


export default Vuex.createStore({

    modules: {
        pistaAdmin: pistaAdmin,
        sportAdmin: sportAdmin,
        pistaClient: pistaClient,
        sportClient: sportClient
    }
});