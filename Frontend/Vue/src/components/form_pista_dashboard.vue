<template>
    <div class="login-box">
        <h2>Form</h2>
        <form>
            <div class="user-box">
                <label>Pista ID</label>
                <input type="text" name="" required="" v-model="state.pista.pista_id" />
            </div>
            <div class="user-box">
                <label>Description</label>
                <input type="text" name="" required="" v-model="state.pista.description" />
            </div>
            <div class="user-box">
                <label>Image</label>
                <input type="url" name="" required="" v-model="state.pista.image" />
            </div>
            <div class="user-box">
                <label>Type</label>
                <input type="text" name="" required="" v-model="state.pista.type" />
            </div>
            <div class="user-box">
                <label>Reserved?</label>
                <input type="checkbox" name="" v-model="state.pista.is_reserved" />
            </div>
            <label><strong>Deportes:</strong></label>
            <br>
            <div v-for="sport in cat.sports" :key="sport.id">
                <input
                    type="checkbox"
                    :id="sport.id"
                    :value="sport.sport_name"
                    :checked="selectedSports && selectedSports.has(sport.sport_name)"
                    @change="handleSportSelection"
                />
                <label :for="sport.id">{{ sport.sport_name }}</label>
            </div>
            
            <br><br>
            <a id="form_buttons" @click="createSubmit()" v-if="!isUpdate">
                <span></span>
                <span></span>
                <span></span>
                <span></span>
                Create
            </a>
            <a id="form_buttons" @click="editSubmit()" v-if="isUpdate">
                <span></span>
                <span></span>
                <span></span>
                <span></span>
                Update
            </a>
            <br>
            <br>
            <a id="form_button_cancel" @click="cancel()">
                <span></span>
                <span></span>
                <span></span>
                <span></span>
                Cancel
            </a>
        </form>
    </div>
</template>

<script>
import { reactive, getCurrentInstance, computed } from 'vue'
import { useRouter } from 'vue-router';
import { useRoute } from 'vue-router';
import { useStore } from 'vuex'
import Constant from '../Constant';
import { ref } from 'vue';

export default {
    props: {
        pista: Object
    },
    emits: {
        data: Object
    },
    computed: {
        isUpdate() {
            const path = this.$route.path.split('/');
            return path[3] == 'update';
        },
    },
    // ...

setup(props) {
    const router = useRouter();
    const route = useRoute();
    const pista = props.pista;
    const { emit } = getCurrentInstance();
    const store = useStore();

    const state = reactive({
        pista: { ...pista }
    });

    state.pista.is_reserved = Boolean(state.pista.is_reserved);

    store.dispatch(`sportAdmin/${Constant.GET_SPORTS}`);

    const cat = reactive({
        sports: computed(() => store.getters['sportAdmin/getSports'])
    });

    const createSubmit = () => {
        // console.log(state.pista);
        emit('data', state.pista)
    };

    const editSubmit = () => {
        // console.log(state.pista);
        state.pista.sports = Array.from(selectedSports.value);
        emit('data', state.pista)
    };

    const cancel = () => {
        router.push({ name: "listPistas" });
    };

    const selectedSports = ref(new Set());

    if (state.pista.sports && state.pista.sports.length > 0) {
        selectedSports.value = new Set(state.pista.sports.map(sport => sport.sport_name));
    }

    const handleSportSelection = (event) => {
        const selectedSport = event.target.value;

        if (selectedSports.value.has(selectedSport)) {
            // Desseleccionar el deporte
            selectedSports.value.delete(selectedSport);
        } else {
            // Seleccionar el deporte
            selectedSports.value.add(selectedSport);
        }
        // Actualizar el array en state.pista.sports
        state.pista.sports = Array.from(selectedSports.value);
    };

    return { state, editSubmit, cancel, createSubmit, cat, handleSportSelection, selectedSports };
}

}
</script>

<style>
/* Estilos generales */
body {
    font-family: 'Arial', sans-serif;
    background-color: #f4f4f4;
    margin: 0;
    padding: 0;
}

/* Estilos del contenedor del formulario */
.login-box {
    width: 300px;
    margin: auto;
    background: #fff;
    padding: 20px;
    box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
    margin-top: 50px;
}

/* Estilos del formulario */
.login-box form {
    display: flex;
    flex-direction: column;
}

/* Estilos de los campos de entrada */
.user-box {
    margin-bottom: 15px;
}

.user-box label {
    display: block;
    margin-bottom: 5px;
    font-weight: bold;
}

.user-box input {
    width: 100%;
    padding: 8px;
    box-sizing: border-box;
    margin-bottom: 10px;
    border: 1px solid #ccc;
    border-radius: 4px;
}

/* Estilos de los checkbox */
.user-box input[type="checkbox"] {
    margin-right: 5px;
}

/* Estilos de los botones */
#form_buttons {
    display: inline-block;
    background: #4caf50;
    color: #fff;
    text-decoration: none;
    padding: 10px 20px;
    border-radius: 4px;
    cursor: pointer;
    transition: background 0.3s ease;
}

#form_button_cancel {
    display: inline-block;
    background: rgb(250, 4, 4);
    color: #fff;
    text-decoration: none;
    padding: 10px 20px;
    border-radius: 4px;
    cursor: pointer;
    transition: background 0.3s ease;
}

/* a:hover {
    background: #45a049;
} */

/* Espaciado entre los elementos */
br {
    margin-bottom: 10px;
}
</style>