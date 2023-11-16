<template>
  <div class="card">
      <Menubar :model="items">
          <template #start>
              <img src="https://upload.wikimedia.org/wikipedia/en/thumb/c/ce/Valenciacf.svg/1200px-Valenciacf.svg.png" width="35" height="40" viewBox="0 0 35 40" alt="">
          </template>
          <template #item="{ item, props, hasSubmenu, root }">
              <router-link v-if="item.router" v-slot="{ href, navigate }" :to="item.router" custom>
                <a v-ripple :href="href" v-bind="props.action" @click="navigate">
                    <span :class="item.icon" />
                    <span class="ml-2">{{ item.label }}</span>
                </a>
              </router-link>
          </template>
          <template #end>
              <div class="flex align-items-center gap-2">
                  <InputText placeholder="Search" type="text" class="w-8rem sm:w-auto" />
                  <Avatar image="https://primefaces.org/cdn/primevue/images/avatar/amyelsner.png" shape="circle" />
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
  icon: 'pi pi-home',
  router: '/home'
},
{
  label: 'Pistas',
  icon: 'pi pi-envelope',
  router: '/pistas'
},
{
  label: 'Sports & Fitness',
  icon: ' pi pi-discord',
  router: '/sports'
},
{
  label: 'Instalations',
  items: [
      {
          label: 'Inside Instalations',
          icon: 'pi pi-bolt',
          router: '/sports'
          
      },
      {
          separator: true
      },
      {
          label: 'Outside Instalations',
          icon: 'pi pi-server',
          router: '/sports'
      }
  ]
},

]);
</script>
