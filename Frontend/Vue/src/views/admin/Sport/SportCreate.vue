<template>
    <!-- <h1>esto es el template de SPORTS CREATE</h1> -->
    <formSport @data="create_emit" />
</template>

<script>
import { reactive, computed } from 'vue';
import Constant from '../../../Constant';
import { useRouter } from 'vue-router'
import { useStore } from 'vuex'
import formSport from '../../../components/form_sport_dashboard.vue'

export default {
    components: { formSport },
    setup() {

        const router = useRouter();
        const store = useStore();

        const create_emit = (sport) => {
            console.log(sport);
            store.dispatch(`sportAdmin/${Constant.CREATE_ONE_SPORT}`, sport);
            router.push({ name: "listSports" })
        }

        return { create_emit }
    }
}
</script>