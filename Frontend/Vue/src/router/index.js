import { createRouter, createWebHistory } from 'vue-router';

import { defineAsyncComponent } from 'vue';

// const asyncfilter = 

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
            path: "/pistas/:filters",
            name: "pistas_cli_filters",
            component: () => import('../views/client/PistasCli.vue')
        },
        //DETAILS
        {
            path: "/pistas/details/:id",
            name: "detailsPista",
            component: () => import('../views/DetailsPista.vue')
        },
        {
            path: "/sports",
            name: "sports_cli",
            component: () => import('../views/client/SportsCli.vue')
        },
        {
            path: "/profile",
            name: "profile",
            component: () => import('../views/client/Profile.vue')
        },
        //ADMINISTRADOR
        //PISTAS
        {
            //LIST PISTAS
            path: "/admin/pistas",
            name: "listPistas",
            component: () => import('../views/admin/Pista/Pistas.vue')
        },
        {
            //UPDATE PISTAS
            path: "/admin/pistas/update/:id",
            name: "updatePista",
            component: () => import('../views/admin/Pista/PistaUpdate.vue')
        },
        {
            //CREATE PISTAS
            path: "/admin/pistas/create",
            name: "createPista",
            component: () => import('../views/admin/Pista/PistaCreate.vue')
        },
        // SPORTS
        {
            //LIST SPORTS
            path: "/admin/sports",
            name: "listSports",
            component: () => import('../views/admin/Sport/Sports.vue')
        },
        {
            //CREATE SPORTS
            path: "/admin/sports/create",
            name: "createSport",
            component: () => import('../views/admin/Sport/SportCreate.vue')
        },
        {
            //UPDATE SPORTS
            path: "/admin/sports/update/:id",
            name: "updateSport",
            component: () => import('../views/admin/Sport/SportUpdate.vue')
        },
           // RESERVAS
        {
            //LIST RESERVAS
            path: "/admin/reservations",
            name: "listReservations",
            component: () => import('../views/admin/Reservations/Reservations.vue')
        },
        // {
        //     //CREATE RESERVATIONS
        //     path: "/admin/reservations/create",
        //     name: "createReservation",
        //     component: () => import('../views/admin/Sport/ReservationCreate.vue')
        // },
        // {
        //     //UPDATE SPORTS
        //     path: "/admin/reservations/update/:id",
        //     name: "updateReservations",
        //     component: () => import('../views/admin/Reservations/ReservationsUpdate.vue')
        // },
        {
            //LOGIN
            path: "/login",
            name: "login",
            component: () => import('../views/Login.vue')
        },
        {
            //REGISTER
            path: "/register",
            name: "register",
            component: () => import('../views/Register.vue'),
        },
    ]
    })

export default router