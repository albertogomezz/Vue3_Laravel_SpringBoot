<template>
    <ListSportClient :sports="state.sports" />
    <!-- <h1>{{state.sports}}</h1> -->
</template>

<script>
import { reactive, computed } from 'vue';
import { useStore } from 'vuex';
import Constant from '../../Constant';
import ListSportClient from '../../components/ListSportClient.vue';

export default {
    components:{ListSportClient},
    setup() {

        const store = useStore();
        const ruta = `sportClient/${Constant.GET_SPORTS}`;
        
        // console.log(ruta);
        store.dispatch(`sportClient/${Constant.GET_SPORTS}`);
        
        const state = reactive({
            sports: computed(() => store.getters['sportClient/getSports']),
        });

        return { state };
    }
}
</script>