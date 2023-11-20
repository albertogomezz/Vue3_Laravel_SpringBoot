<template>
    <!-- <ListPistas :pistas="state.pistas" /> -->
    <h1>Esto es sports client</h1>
</template>

<script>
import { reactive, computed } from 'vue';
import { useStore } from 'vuex';
import Constant from '../../Constant';
import ListPistas from '../../components/ListPistasClient.vue';

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