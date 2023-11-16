<template>
  <Header />
  <RouterView />
  <br>
  <br><br>
  <br>
  <br>
  <FooterVue />
</template>

<script setup>
import { RouterView } from 'vue-router'
import FooterVue from './components/TheFooter.vue';
import Header from './components/TheHeader.vue';
import { useStore } from 'vuex';
import Constant from './Constant';
const store = useStore();

</script>