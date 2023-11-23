<template>
    <formSports :sport="stateOne.sport" :key="stateOne.sport?.id" @data="update_emit"/>
</template>

<script>
import Constant from '../../../Constant';
import { useRoute, useRouter } from 'vue-router'
import { reactive, computed } from 'vue'
import { useStore } from 'vuex'
import formSports from '../../../components/form_sport_dashboard.vue'

export default {
    components: { formSports },
    setup() {

        const router = useRouter();
        const route = useRoute()
        const store = useStore();
        const id = route.params.id;

        store.dispatch(`sportAdmin/${Constant.GET_ONE_SPORT}`, id)

        const stateOne = reactive({
            sport: computed(() => store.getters["sportAdmin/getOneSport"])
            // console.log(stateOne.pista);
        })

        const update_emit = (sport) =>{
            // console.log(pista);
            store.dispatch(`sportAdmin/${Constant.UPDATE_ONE_SPORT}`, sport);
            
            router.push({ name: "listSports" })
        }

        return { stateOne
            , update_emit 
        }

    }
}
</script>

<style lang="scss">

</style>