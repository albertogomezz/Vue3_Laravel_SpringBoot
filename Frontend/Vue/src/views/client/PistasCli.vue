<template>
    <ListPistas :pistas="state.pistas" />
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
        const ruta = "pistaClient/GetPistas";
        // console.log(ruta);
        store.dispatch(ruta);
        
        const state = reactive({
            pistas: computed(() => store.getters['pistaClient/getPistas']),
        });

        return { state };
    }
}
</script>