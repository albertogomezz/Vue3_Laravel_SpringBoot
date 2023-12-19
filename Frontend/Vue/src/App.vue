<template>
  <Header />
  <RouterView />
  <br>
  <FooterVue />
</template>

<script setup>
import { RouterView } from 'vue-router'
import FooterVue from './components/TheFooter.vue';
import Header from './components/theheader.vue';
import { useStore } from 'vuex';
import Constant from './Constant';

const store = useStore();
if (localStorage.getItem("token")) {
  store.dispatch(`user/${Constant.GET_PROFILE}`);
}
</script>