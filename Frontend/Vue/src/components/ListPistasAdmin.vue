<template>
    <body>
        <div class="">
                <div class="crear">
                    <button class="btn btn-success" @click="createPista()">
                        Add Pista <span class="fa fa-plus"/></button>
                </div>
            </div>
    <div class="table-container">
        <table class="table table-striped table table-hover">
                <thead>
                    <tr class="table-active">
                        <th class="table-active" scope="col">Image</th>
                        <th class="table-active" scope="col">ID</th>
                        <th class="table-active" scope="col">Description</th>
                        <th class="table-active" scope="col">Type</th>
                        <th class="table-active" scope="col"></th>
                        <th class="table-active" scope="col"></th>
                        <th class="table-active" scope="col"></th>
                    </tr>
                </thead>
                <tbody class="text-center">
                    <tr v-for="pista in pistas">
                        <td> <img class="foto" :src=pista.image ></td>
                        <td>{{ pista.pista_id }}</td>
                        <td>{{ pista.description }}</td>
                        <td>{{ pista.type }}</td>
                        <td>
                            <button class="btn btn-warning" @click="updatePista(pista.id)">Update <font-awesome-icon icon="pen-to-square" /></button>
                        </td>
                        <td>
                            <button class="btn btn-danger" @click="deletePista(pista.id)">Delete <font-awesome-icon icon="trash" /></button>
                        </td>
                        <td>
                            <button class="btn btn-info" @click="details(pista.id)">Details <font-awesome-icon icon="circle-info" /></button>
                        </td>
                    </tr>
                </tbody>
            </table>
        </div>
    </body>
</template>

<script>
import { useStore } from 'vuex';
import { useRouter } from 'vue-router';
import Constant from '../Constant';
import { createToaster } from "@meforma/vue-toaster";

export default {

    props: {
        pistas: Object,
    },
    setup() {

        const toaster = createToaster({ "position": "top-right", "duration": 2000 });
        const store = useStore();
        const router = useRouter();

        const createPista = () => {
            router.push({ name: "createPista" })
        }

        const deletePista = (id) => {
            store.dispatch(`pistaAdmin/${Constant.DELETE_ONE_PISTA}`, { id })
            toaster.success('Pista Deleted Successfully');
            router.push({ name: "listPistas" })
        }
        const updatePista = (id) => {
            // console.log(id);
            router.push({ name: "updatePista", params: { id } })
        }
        const details = (id) => {
            // console.log(id);
            router.push({ name: "detailsPista", params: { id } })
        }
        return { 
            deletePista
            , updatePista
            , createPista 
            , details
        }
    }
}
</script>
<style scoped>
.foto{
    width: 110px;
    height: 110px;
    border-radius: 50%;
    object-fit: cover;

}
.text-center{
    text-align: center;
    vertical-align: middle;
}
div.table-container table.table{
    border-radius: 10px !important;
    margin: 0 auto;
    width: 86%;
    clear: both;
    border-collapse: collapse;
     table-layout: fixed; 
    word-wrap: break-word;
    margin: 40px;
    /*box-shadow: 0px 0px 10px 0px #000000; */
}
    .table-container {
    display: flex;
    justify-content: center;
    align-items: center;
    height: 100%;
}
.arriba{
    margin: 10px;
    align-items: centerz;
}
.table-active{
    background-color: rgb(47, 132, 207);
    color: #ffffff;
    text-align: center;
}

.crear{
    padding-top: 20px;
    padding-bottom: 0;
    padding-left: 100px;  
}

</style>