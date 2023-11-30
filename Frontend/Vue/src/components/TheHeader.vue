<template>
  <div class="card">
      <Menubar :model="items">
          <template #start>
              <img src="https://upload.wikimedia.org/wikipedia/en/thumb/c/ce/Valenciacf.svg/1200px-Valenciacf.svg.png" width="35" height="40" viewBox="0 0 35 40" alt="">
          </template>
          <template #item="{ item, props, hasSubmenu, root }">
              <router-link v-if="item.router" v-slot="{ href, navigate }" :to="item.router" custom>
                <a v-ripple :href="href" v-bind="props.action" @click="navigate">
                    <span class="ml-2">{{ item.label }}</span>
                </a>
              </router-link>
          </template>
          <template #end>
              <div class="flex align-items-center gap-2">
                  <InputText placeholder="Search" type="text" class="w-8rem sm:w-auto" />
                  <!-- <Button icon="pi pi-search" class="p-button-rounded p-button-success p-mr-6"></Button> -->
              </div>
          </template>
      </Menubar>
  </div>
</template>
<script setup>
import { ref } from "vue";

const items = ref([
{
  label: 'Home',
  router: '/home'
},
{
  label: 'Pistas Admin',
  router: '/admin/pistas'
},
{
  label: 'Pistas Client',
  router: '/pistas'
},
{
  label: 'Sports Admin',
  router: '/admin/sports'
},
{
  label: 'Sports Client',
  router: '/sports'
}
]);
</script>
<style>
a{
  text-decoration: none;
  color: black;
  padding-bottom: 0;
}

</style>
