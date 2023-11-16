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
        {
            path: "/pistas",
            name: "pistas",
            component: () => import('../views/Pistas.vue')
        },
        {
            path: "/sports",
            name: "sports",
            component: () => import('../views/Sports.vue')
        },
    ]
    })

export default router