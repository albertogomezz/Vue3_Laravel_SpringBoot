<template>
  <ListSportsAdmin :sports="state.sports"/>
</template>

<script>
import { reactive, computed } from 'vue';
import { useStore } from 'vuex';
import Constant from '../../../Constant';
import ListSportsAdmin from '../../../components/ListSportsAdmin.vue';

export default {
  components:{ListSportsAdmin},
  setup() {

      const store = useStore();
      const ruta = `sportAdmin/${Constant.GET_SPORTS}`;
      
      store.dispatch(ruta);
      
      const state = reactive({
          sports: computed(() => store.getters['sportAdmin/getSports']),
      });

      return { state };
  }
}
</script>