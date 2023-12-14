<template>
  <div class="card">
    <Menubar :model="items">
      <template #start>
        <img 
          src="https://upload.wikimedia.org/wikipedia/en/thumb/c/ce/Valenciacf.svg/1200px-Valenciacf.svg.png"
          width="35"
          height="40"
          viewBox="0 0 35 40"
          alt=""
          style="margin-left: 35px"
        />
      </template>
      <template #item="{ item, props, hasSubmenu, root }">
        <router-link
          v-if="item.router"
          v-slot="{ href, navigate }"
          :to="item.router"
          custom
        >
          <a v-ripple :href="href" v-bind="props.action" @click="navigate">
            <span class="ml-2">{{ item.label }}</span>
          </a>
        </router-link>
      </template>
      <template #end>
        <router-link :to="item_login.find((item) => item.label === 'Login')?.router">
          <a v-ripple style="margin-right: 50px;">
            <span>{{ item_login.find((item) => item.label === 'Login')?.label }}</span>
            <i class="bi bi-person-circle"></i>
          </a>
        </router-link>
      </template>
    </Menubar>
  </div>
</template>

<script setup>
import { ref } from "vue";

const items = ref([
  {
    label: 'Home',
    router: '/home',
  },
  {
    label: 'Pistas Admin',
    router: '/admin/pistas',
  },
  {
    label: 'Pistas Client',
    router: '/pistas',
  },
  {
    label: 'Sports Admin',
    router: '/admin/sports',
  }
]);

const item_login = ref([
  {
    label: 'Login',
    router: '/login',
  },
]);

</script>

<style scoped>
a {
  text-decoration: none;
  color: black;
  padding-bottom: 0;
}
</style>
