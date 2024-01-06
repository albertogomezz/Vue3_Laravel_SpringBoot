import { createApp } from 'vue';
import App from './App.vue'
import PrimeVue from 'primevue/config';
import InputText from 'primevue/inputtext';
import Button from 'primevue/button';
import Toast from 'primevue/toast';
import TreeSelect from 'primevue/treeselect';
import Toaster from "@meforma/vue-toaster";
import Menubar from 'primevue/menubar';
import Paginate from "vuejs-paginate-next";
import '@fortawesome/fontawesome-free/css/all.min.css';
import 'primevue/resources/themes/lara-light-blue/theme.css'       
import 'primevue/resources/primevue.min.css'                
import  'primeicons/primeicons.css'
import 'bootstrap/dist/css/bootstrap.min.css';
import 'bootstrap';

//RESERVAS
import Dialog from 'primevue/dialog';
import Calendar from 'primevue/calendar';

import router from "./router";
import store from "./store";

const app = createApp(App)

app.use(PrimeVue)
.use(Dialog)
.use(router)
.use(store)
.use(Toaster)
.use(Paginate)
app.component('Toast', Toast);
app.component('InputText', InputText);
app.component('Menubar', Menubar);
app.component('Button', Button);
app.component('Dialog', Dialog);
app.component('Calendar', Calendar);
app.component('TreeSelect', TreeSelect); 
app.mount('#app')