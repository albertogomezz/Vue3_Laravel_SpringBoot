<template>
  <div>
    <ListReservationsAdmin :reservations="state.reservations"/>
  </div>
</template>

<script>

import { reactive, computed } from 'vue';
import { useStore } from 'vuex';
import Constant from '../../../Constant';
import ListReservationsAdmin from '../../../components/ListReservationsAdmin.vue';
export default {
  components:{ListReservationsAdmin},
    setup() {

        const store = useStore();
        const ruta = `reservationAdmin/${Constant.GET_RESERVATIONS}`;
        // console.log(ruta);
        store.dispatch(ruta);

        const state = reactive({
            reservations: computed(() => store.getters['reservationAdmin/GetReservations']),
        });

        return { state };
    }
}
</script>

<style scoped>
</style>
