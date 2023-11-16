<template>
  <Header />
  <RouterView />
  <Footer />
</template>

<script setup>
import { RouterView } from 'vue-router'
import Footer from './components/TheFooter.vue';
import Header from './components/TheHeader.vue';
import { useStore } from 'vuex';
import Constant from './Constant';
const store = useStore();

</script>