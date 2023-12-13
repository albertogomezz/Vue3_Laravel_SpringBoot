<template>
    <!-- <div  v-if="state.pistas.length  >= 1"> -->
        <div class="filters">
            <filters @filters="ApplyFilters" @deleteFilters="deleteAllFilters" :filters="filters_URL" />
        </div>
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
    import { usePistaFilters, usePistaPaginate } from '../../../src/composables/pistas/usePistas';
    import  filters from '../../components/filters.vue'
    import { useStore } from 'vuex';
    import { ref, onMounted, reactive } from 'vue';
    import { useRouter, useRoute } from 'vue-router';
    import ListPistas from '../../components/ListPistasClient.vue';
    import Paginate from 'vuejs-paginate-next';
    
    export default {
        components: { ListPistas, filters, Paginate },
        setup() {
            const route = useRoute();
            const router = useRouter();
            const pistas = ref([]);
            const store = useStore();
            const page = ref([]);
            const totalPages = ref([]);


            let filters_URL = {
                sports: [],
                reservation: 2,
                order: 2,
                // sport_name: "",
                page: 1,
                limit: 3,
            };
            
            ///////////////////////////////////////////////////////////////

            try {
                if (route.params.filters !== '') {
                filters_URL = JSON.parse(atob(route.params.filters));
                // console.log('hola');
                }
            } catch (error) {}


            const state = reactive({
                pistas: usePistaFilters(filters_URL),
                page: filters_URL.page,
                totalPages: usePistaPaginate(filters_URL)
            });


            onMounted(async () => {
                state.pistas = await usePistaFilters(filters_URL)
                state.page = filters_URL.page,
                state.totalPages = usePistaPaginate(filters_URL)
            });
        
        const ApplyFilters = (filters) => {
            // console.log(filters);
            const filters_64 = btoa(JSON.stringify(filters));
            router.push({ name: "pistas_cli_filters", params: { filters: filters_64 } });
            state.pistas = usePistaFilters(filters);
            state.totalPages = usePistaPaginate(filters);
            // state.totalPages = 4; 
        }

        const deleteAllFilters = (deleteFilters) => {
            router.push({ name: "pistas_cli" });
            state.pistas = usePistaFilters(deleteFilters);
            // console.log(state.pistas);
            filters_URL.page = 1;
            filters_URL.limit = 3;
            filters_URL = deleteFilters;
            state.totalPages = usePistaPaginate(deleteFilters);
        }

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
                // window.location.reload();
            }


            return { state, ApplyFilters ,deleteAllFilters, clickCallback, filters_URL };
        },
    };
</script>
<style scoped>
    .paginate {
        display: flex;
        justify-content: center;
        align-items: center;
    }
    .filters{
    margin-top: 20px;
    margin-bottom: 20px;
    justify-content: center;
    width: 100%;
}
</style>