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
        //CLIENT
        {
            path: "/pistas",
            name: "pistas_cli",
            component: () => import('../views/client/PistasCli.vue')
        },
        {
            path: "/sports",
            name: "sports_cli",
            component: () => import('../views/client/SportsCli.vue')
        },
        //ADMIN
        //PISTAS
        {
            path: "/admin/pistas",
            name: "listPistas",
            component: () => import('../views/admin/Pista/Pistas.vue')
        },
        {
            path: "/admin/pistas/update/:id",
            name: "updatePista",
            component: () => import('../views/admin/Pista/PistaUpdate.vue')
        },
        {
            path: "/admin/pistas/create",
            name: "createPista",
            component: () => import('../views/admin/Pista/PistaCreate.vue')
        },
        // SPORTS
        {
            path: "/admin/sports",
            name: "sports",
            component: () => import('../views/admin/Sport/Sports.vue')
        },
    ]
    })

export default router