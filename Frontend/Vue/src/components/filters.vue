<template>
    <div class="filtros">
      <select v-model="state.filters.sports" class="form-select form-select-lg mb-3 each" aria-label="Color">
        <option selected value="" disabled hidden>Select Sport</option>
      <option v-for="sport in state.all_sports" :key="sport.sport_name" :value="sport.sport_name">
        {{ sport.sport_name }}
      </option>
    </select>


    <select v-model="state.filters.order" class="form-select form-select-lg mb-3 each" aria-label="Que pasa">
      <option selected hidden value="2" >Ordenar Precio</option>
      <option value="1">Precio Mas Alto</option>
      <option value="0">Precio Mas Bajo</option>
    </select>

    <select v-model="state.filters.reservation" class="form-select form-select-lg mb-3 each" aria-label="Price">
      <option selected hidden value="2" disabled default >Disponibilidad</option>
      <option value="0">Si</option>
      <option value="1">No</option>
    </select>

    <Button label="Filter" type="submit" severity="success" @click="sendFilters()" raised />
    <br>
    <Button label="Delete Filter" type="submit" severity="danger" class="red-bttn" @click="deleteFilters()" raised />
  </div>
</template>

<script>
import { reactive,getCurrentInstance, computed } from 'vue';
import Constant from '../Constant';
import { useStore } from 'vuex';

export default {

  props: {
        filters: Object
    },
    emits: {
        filters: Object,
        deleteFilters: Object
    },
  setup(props) {
    const store = useStore();
    const { emit } = getCurrentInstance();


    store.dispatch(`sportClient/${Constant.GET_SPORTS}`);

    const state = reactive({
      all_sports: computed(() => store.getters['sportClient/getSports']),
      filters: { ...props.filters }
    });


     function sendFilters(){
      console.log(state.filters);
      emit('filters', state.filters);
    }
    const deleteFilters = () => {
            state.filters.sports = [];
            state.filters.order = 2;
            // state.filters.sport_name = "";
            state.filters.reservation = 2;
            state.filters.page = 1;
            state.filters.limit = 9;
            console.log(state.filters);
            emit('deleteFilters', state.filters);
        }//sendFilters

    // const resetFilters = {
    //     capacity: () => { state.filters.capacity = 0; sendFilters(); },
    //     order: () => { state.filters.order = 0; sendFilters(); }
    // };

    return { state, sendFilters, deleteFilters };
  },
};
</script>

<style>
.filtros {
  display: flex;
  justify-content: center;
}


.filtros select{
    margin-right: 10px;
    margin-left: 10px;
    width: 300px;
    height: 50px;
    border-radius: 10px;
    border: 1px solid #ced4da;
    background-color: #fff;
    color: #495057;
    font-size: 1rem;
    font-weight: 400;
    line-height: 1.5;
    padding: .375rem .75rem;
    transition: border-color .15s ease-in-out,box-shadow .15s ease-in-out;
}
.filtros Button {
  height: 50px;
  border-radius: 10px;
  width: 100px;
}

.red-bttn{
  margin-left: 10px;
}
</style>