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
        <router-link v-for="loginItem in item_login" :key="loginItem.label" :to="loginItem.router">
          <a v-ripple style="margin-right: 50px;">
            <span>{{ loginItem.label }}</span>
            <i class="bi bi-person-circle"></i>
          </a>
        </router-link>
        <router-link v-if="user.auth && user.token" to="/profile" style="margin-right: 50px;">
          <span>{{ state.profile.username }}</span>
          <img :src="state.profile.photo" alt="Profile Photo" style="width: 30px; height: 30px; border-radius: 50%; margin-left: 10px;" />
        </router-link>
      </template>
    </Menubar>
  </div>
</template>

<script setup>
import { ref, onMounted, reactive, computed } from "vue";
import { useStore } from 'vuex';


let user = reactive({
  auth: null,
  token: null,
  isAdmin: null,
  token_admin: null
});

const store = useStore();
const state = reactive({
    profile: computed(() => store.getters['user/GetProfile']),
});

onMounted(() => {
  //user info
  user.auth = window.localStorage.getItem('isAuth');
  user.token = window.localStorage.getItem('token');
  //admin info
  user.isAdmin = window.localStorage.getItem('isAdmin');
  user.token_admin = window.localStorage.getItem('token_admin');
});

//NO REGISTRADO

let items = computed(() => {
  if (!user.auth && !user.token) {
    console.log('no registrado');
    return [
      {
        label: 'Home',
        router: '/home',
      },
      {
        label: 'Pistas',
        router: '/pistas',
      }
    ];
  } else if (user.auth === 'true' && user.token) {
    if(user.isAdmin === 'true'  && user.token_admin){
      console.log('admin');
      return [
        {
          label: 'Pistas Admin',
          router: '/admin/pistas',
        },
        {
          label: 'Sports Admin',
          router: '/admin/sports',
        }
      ];
    } else {
      console.log('client');
      return [
        {
          label: 'Home',
          router: '/home',
        },
        {
          label: 'Pistas',
          router: '/pistas',
        }
      ];
    }
  }
});

let item_login = computed(() => {
  if (!user.auth && !user.token) {
    return [
      {
        label: 'Login',
        router: '/login',
      },
    ];
  } else if (user.auth === 'true' && user.token) {
    return [
      {
        label: 'Logout',
        router: '/login',
      },
    ];
  }
});

</script>

<style scoped>
a {
  text-decoration: none;
  color: black;
  padding-bottom: 0;
}
</style>