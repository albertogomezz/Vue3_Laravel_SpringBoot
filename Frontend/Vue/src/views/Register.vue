<template>
    <loginRegisterFormVue :isLogin="false" @send="register" />
</template>

<script>
import loginRegisterFormVue from '../components/loginRegisterForm.vue';
import Constant from '../Constant';
import { useStore } from 'vuex';

export default {
    components: { loginRegisterFormVue },
    setup() {
        const store = useStore();

        const register = (data) => {
            // console.log(data);
            store.dispatch(`user/${Constant.REGISTER_USER}`, data);
        }

        return { register };
    }
}
</script>

<style lang="scss">

</style>