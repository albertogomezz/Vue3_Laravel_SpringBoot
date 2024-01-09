<template>   
    <body>
        <div class="">
                <div class="crear">
                    <button class="btn btn-success" @click="createSport()">
                        Add Sport <span class="fa fa-plus"/></button>
                </div>
            </div>
    <div class="table-container">
        <table class="table table-striped table table-hover">
                <thead>
                    <tr class="table-active">
                        <th class="table-active" scope="col">Sport ID</th>
                        <th class="table-active" scope="col">Sport Name</th>
                        <th class="table-active" scope="col">Description</th>
                        <th class="table-active" scope="col">Price</th>
                        <th class="table-active" scope="col">Image</th>
                        <th class="table-active" scope="col"></th>
                    </tr>
                </thead>
                <tbody class="text-center">
                    <tr v-for="sport in sports">
                        <td>{{ sport.sport_id }}</td>
                        <td>{{ sport.sport_name }}</td>
                        <td>{{ sport.description }}</td>
                        <td>{{ sport.price }}</td>
                        <td>
                            <button class="btn btn-warning" @click="updateSport(sport.id)">Update <font-awesome-icon icon="pen-to-square" /></button>
                        </td>
                        <td>
                            <button class="btn btn-danger" @click="deleteSport(sport.id)">Delete <font-awesome-icon icon="trash" /></button>
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

export default {

    props: {
        sports: Object,
    },
    setup() {

        const store = useStore();
        const router = useRouter();

        const createSport = () => {
            router.push({ name: "createSport" })
        }

        const deleteSport = (id) => {
                // console.log('Delete sport with id: ' + id);
            store.dispatch(`sportAdmin/${Constant.DELETE_ONE_SPORT}`, { id })
            router.push({ name: "listSports" })
            const toast = useToast();
        }
        const updateSport = (id) => {
            // console.log(id);
            router.push({ name: "updateSport", params: { id } })
        }
        return { 
            deleteSport
            , updateSport
            , createSport 
        }
    }
}
</script>
<style scoped>
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