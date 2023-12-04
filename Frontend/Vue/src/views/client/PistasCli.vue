<template>
    <!-- <div  v-if="state.pistas.length  >= 1"> -->
        <ListPistas :pistas="state.pistas" />
        <br><br>    
        <paginate class="paginate" v-model="state.page"
            :page-count="state.totalPages" 
            :page-range="3" 
            :margin-pages="2"
            :click-handler="clickCallback" 
            :container-class="'pagination'"
            :page-class="'page-item'">
        </paginate>
    <!-- </div> -->
    <!-- <h1 v-else>😔😔LO SIENTO, NO HAY PISTAS DISPONIBLES PARA ESTE DEPORTE😔😔 </h1> -->
</template>

<script>
    import { ref, onMounted, reactive } from 'vue';
    import { useRouter, useRoute } from 'vue-router';
    import ListPistas from '../../components/ListPistasClient.vue';
    import { usePistaFilters } from '../../../src/composables/pistas/usePistas';
    import { usePistaPaginate } from '../../../src/composables/pistas/usePistas';
    import Paginate from 'vuejs-paginate-next';

    
    export default {
        components: { ListPistas, Paginate },
        setup() {
            const route = useRoute();
            const router = useRouter();
            const pistas = ref([]);
            const page = ref([]);
            const totalPages = ref([]);
        
            let filters_URL = {
                sports: [],
                page: 1,
                limit: 3,
            };
            
            ///////////////////////////////////////////////////////////////

            try {
                if (route.params.filters !== '') {
                filters_URL = JSON.parse(atob(route.params.filters));
                }
            } catch (error) {}

            ///////////////////////////////////////////////////////////////

            const state = reactive({
                pistas: usePistaFilters(filters_URL),
                page: filters_URL.page,
                totalPages: usePistaPaginate(filters_URL)
            });

            ///////////////////////////////////////////////////////////////

            const ApplyFilters = (filters) => {
                const filters_64 = btoa(JSON.stringify(filters));
                router.push({ name: "pistas_cli_filters", params: { filters: filters_64 } });
                state.pistas = usePistaFilters(filters);
                state.totalPages = usePistaPaginate(filters);
            }

            ///////////////////////////////////////////////////////////////

            const clickCallback = (pageNum) => {
                try {
                    if (route.params.filters !== '') {
                        filters_URL = JSON.parse(atob(route.params.filters));
                    }
                } catch (error) {
                }
                filters_URL.page = pageNum;
                state.page = pageNum;
                ApplyFilters(filters_URL)
            }

            ///////////////////////////////////////////////////////////////

            console.log(state.pistas.length);

            onMounted(async () => {
                state.pistas = await usePistaFilters(filters_URL),
                state.page = filters_URL.page,
                state.totalPages = usePistaPaginate(filters_URL)
            });

            return { state, clickCallback, filters_URL, ApplyFilters};
        },
    };
</script>
<style scoped>
    .paginate {
        display: flex;
        justify-content: center;
        align-items: center;
    }
</style>