<template>

<div  v-if="pistas.length  >= 1">
    <ListPistas :pistas="pistas" />
</div>
    <h1 v-else>😔😔LO SIENTO, NO HAY PISTAS DISPONIBLES PARA ESTE DEPORTE😔😔 </h1>
    
</template>

<script>
    import { ref, onMounted } from 'vue';
    import { useRoute } from 'vue-router';
    import ListPistas from '../../components/ListPistasClient.vue';
    import { usePistaFilters } from '../../../src/composables/pistas/usePistas';
    
    export default {
        components: { ListPistas },
        setup() {
            const route = useRoute();
            const pistas = ref([]);
        
            let filters_URL = {
                sports: [],
            };
        
            try {
                if (route.params.filters !== '') {
                filters_URL = JSON.parse(atob(route.params.filters));
                }
            } catch (error) {}
        
            onMounted(async () => {
                pistas.value = await usePistaFilters(filters_URL);
            });
        
            return { pistas };
        },
    };
</script>