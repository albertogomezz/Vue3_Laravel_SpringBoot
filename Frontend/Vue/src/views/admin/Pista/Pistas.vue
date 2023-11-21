<template>
  <ListPistas :pistas="state.pistas" />
</template>

<script>
import { reactive, computed } from 'vue';
import { useStore } from 'vuex';
import Constant from '../../../Constant';
import ListPistas from '../../../components/ListPistasAdmin.vue';

export default {
  components:{ListPistas},
  setup() {

      const store = useStore();
      const ruta = `pistaAdmin/${Constant.GET_PISTAS}`;
      
      store.dispatch(ruta);
      
      const state = reactive({
          pistas: computed(() => store.getters['pistaAdmin/getPistas']),
      });


      return { state };
  }
}
</script>