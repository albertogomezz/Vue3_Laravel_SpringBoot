<template>
    <h1>SPORTS</h1>
    <Carousel  :sports="state.sports"  @emitAction="emitAction"/>
    <br>
    <br>
    <br>
    <br>
    <!-- <h1>{{ state.sportsInfinite }}</h1> -->
    <div>
        <!-- <Sport_item :item="state.sportsInfinite"  @emitAction="emitAction" /> -->
        <Scroll_sports :data="state.sportsInfinite" @emitAction="emitAction" @page="addInfinite" />

    </div>
</template>

<script>
import { reactive, computed } from 'vue';
import { useStore } from 'vuex';
import Constant from '../Constant';
import { useRouter } from 'vue-router';
import Scroll_sports  from '../components/scroll_sports.vue';
import Carousel from '../components/carousel.vue';
import { useSportsInfinite } from '../composables/pistas/usePistas';
import { getCurrentInstance } from 'vue';

export default {
    components: { Carousel, Scroll_sports },
    emits: {
        emitAction: Object
    },

    setup() {
        const router = useRouter();
        const store = useStore();
        
        const { emit } = getCurrentInstance();
        const emitAction = (item) => {
            // console.log(item);
            redirectToPistas(item);
        }


        store.dispatch(`sportClient/${Constant.GET_SPORTS}`);

        const state = reactive({
            sports: computed(() => store.getters['sportClient/getSports']),
            sportsInfinite: useSportsInfinite(1, 3),
        });

        const redirectToPistas = (item) => {
            const filters = {
                sports: [item],
                reservation: 2,
                order: 2,
                // sport_name: "",
                page: 1,
                limit: 3,
            };

            const filters_ = btoa(JSON.stringify(filters));
            // console.log( { filters: filters_ } );
            router.push({ name: "pistas_cli_filters", params: { filters: filters_ } });
        }

        const addInfinite = (page) => {
            state.sportsInfinite = useSportsInfinite(page, 3);
        }

        return { state, redirectToPistas, emitAction, addInfinite};
    }
}
</script>
<style scoped>
    h1 {
        text-align: center;
        margin-top: 20px;
    }
</style>
