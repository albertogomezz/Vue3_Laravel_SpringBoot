<template>
    <formPista :pista="stateOne.pista" :key="stateOne.pista?.id" @data="update_emit"/>
    <!-- <h1>{{stateOne.pista}}</h1> -->
</template>

<script>
import { createToaster } from "@meforma/vue-toaster";
import Constant from '../../../Constant';
import { useRoute, useRouter } from 'vue-router'
import { reactive, computed } from 'vue'
import { useStore } from 'vuex'
import formPista from '../../../components/form_pista_dashboard.vue'

export default {
    components: { formPista },
    setup() {

        const toaster = createToaster({ "position": "top-right", "duration": 2000 });
        const router = useRouter();
        const route = useRoute()
        const store = useStore();
        const id = route.params.id;

        store.dispatch(`pistaAdmin/${Constant.GET_ONE_PISTA}`, id)

        const stateOne = reactive({
            pista: computed(() => store.getters["pistaAdmin/getOnePista"])
            // console.log(stateOne.pista);
        })

        const update_emit = (pista) =>{
            // console.log(pista);
            store.dispatch(`pistaAdmin/${Constant.UPDATE_ONE_PISTA}`, pista);
            toaster.success('Pista Updated Successfully');
            router.push({ name: "listPistas" })
        }

        return { stateOne
            , update_emit 
        }

    }
}
</script>

<style lang="scss">

</style>