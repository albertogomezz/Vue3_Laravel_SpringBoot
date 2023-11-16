import { createStore } from "vuex";

import home from "./home.module";
import article from "./article.module";

export default createStore({
  modules: {
    home,
    // @ts-ignore
    article,
  }
});