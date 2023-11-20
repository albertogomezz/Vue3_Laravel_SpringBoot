<template>
  <!-- <ListSports :sports="state.sports" /> -->
  <h1>Esto es sports admin</h1>
</template>

<script>
import { reactive, computed } from 'vue';
import { useStore } from 'vuex';
import Constant from '../../../Constant';
// import ListSports from '../../../components/ListSportsAdmin.vue';

export default {
  // components:{ListSports},
  setup() {

      // const store = useStore();
      // const ruta = `pistaAdmin/${Constant.GET_PISTAS}`;
      
      // store.dispatch(ruta);
      
      // const state = reactive({
      //     pistas: computed(() => store.getters['pistaAdmin/getPistas']),
      // });

      // return { state };
  }
}
</script>