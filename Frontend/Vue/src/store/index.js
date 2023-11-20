import Vuex from "vuex";

import { pistaAdmin } from './modules/admin/pistaAdmin.js';
import { pistaClient } from './modules/client/pistaClient.js';

export default Vuex.createStore({

    modules: {
        pistaAdmin: pistaAdmin,
        pistaClient: pistaClient,
    }
});