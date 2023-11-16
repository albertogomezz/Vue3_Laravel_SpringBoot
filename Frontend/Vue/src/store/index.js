import Vuex from "vuex";

import { pistaAdmin } from './modules/admin/pistaAdmin.js';
// import { category } from './modules/client/category';
// import { mesaDashboard } from './modules/dashboard/mesaDashboard';
// import { categoryDashboard } from './modules/dashboard/categoryDashboard';

export default Vuex.createStore({

    modules: {
        pistaAdmin: pistaAdmin,
        // category: category,
        // mesaDashboard: mesaDashboard,
        // categoryDashboard: categoryDashboard,
    }
});