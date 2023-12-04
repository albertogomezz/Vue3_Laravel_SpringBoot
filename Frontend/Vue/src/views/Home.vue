<template>
    <h1>SPORTS</h1>
    <Carousel  :sports="state.sports"  @emitAction="emitAction"/>
</template>

<script>
import { reactive, computed } from 'vue';
import { useStore } from 'vuex';
import Constant from '../Constant';
import { useRouter } from 'vue-router';
import Carousel from '../components/carousel.vue';
import { getCurrentInstance } from 'vue';

export default {
    components: { Carousel },
    emits: {
        emitAction: Object
    },

    setup() {
        const router = useRouter();
        const store = useStore();
        
        const { emit } = getCurrentInstance();
        const emitAction = (item) => {
            redirectToPistas(item);
        }


        store.dispatch(`sportClient/${Constant.GET_SPORTS}`);

        const state = reactive({
            sports: computed(() => store.getters['sportClient/getSports']),
            // mesasInfinite: useMesaInfinite(1, 4),
        });

        const redirectToPistas = (item) => {
            const filters = {
                sports: [item],
                // capacity: 0,
                // order: 0,
                // name_mesa: "",
                page: 1,
                limit: 9,
            };
            // console.log(filters);
            const filters_ = btoa(JSON.stringify(filters));
            // console.log( { filters: filters_ } );
            router.push({ name: "pistas_cli_filters", params: { filters: filters_ } });
        }

        // const addInfinite = (page) => {
        //     state.mesasInfinite = useMesaInfinite(page, 4);
        // }

        return { state, redirectToPistas, emitAction };
    }
}
</script>
<style>
    h1 {
        text-align: center;
        margin-top: 20px;
    }
</style>
