import { createRouter, createWebHistory } from 'vue-router';

    const router = createRouter({
    history: createWebHistory(import.meta.env.BASE_URL),
    routes: [
        {
            path: "/",
            redirect: { name: "home" }
        },
        {
            path: "/home",
            name: "home",
            component: () => import('../views/Home.vue')
        },
        //ADMIN
        {
            path: "/admin/pistas",
            name: "listPistas",
            component: () => import('../views/Pista/Pistas.vue')
        },
        {
            path: "/admin/pistas/update/:id",
            name: "updatePista",
            component: () => import('../views/Pista/PistaUpdate.vue')
        },
        {
            path: "/admin/pistas/create",
            name: "createPista",
            component: () => import('../views/Pista/PistaCreate.vue')
        },
        {
            path: "/sports",
            name: "sports",
            component: () => import('../views/Sports.vue')
        },
    ]
    })

export default router