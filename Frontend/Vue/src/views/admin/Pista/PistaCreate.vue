<template>
    <formPista @data="create_emit" />
</template>

<script>
import { reactive, computed } from 'vue';
import { createToaster } from "@meforma/vue-toaster";
import Constant from '../../../Constant';
import { useRouter } from 'vue-router'
import { useStore } from 'vuex'
import formPista from '../../../components/form_pista_dashboard.vue'

export default {
    components: { formPista },
    setup() {

        const toaster = createToaster({ "position": "top-right", "duration": 2000 });
        const router = useRouter();
        const store = useStore();

        const create_emit = (pista) => {
            // console.log(pista);
            store.dispatch(`pistaAdmin/${Constant.CREATE_ONE_PISTA}`, pista);
            toaster.success('Pista Created Succesfully');
            router.push({ name: "listPistas" })
        }

        return { create_emit }
    }
}
</script>