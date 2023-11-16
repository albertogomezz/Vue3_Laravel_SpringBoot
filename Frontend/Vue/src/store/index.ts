import { createStore } from "vuex";

import home from "./home.module";

export default createStore({
  modules: {
    home,
    // @ts-ignore
  }
});