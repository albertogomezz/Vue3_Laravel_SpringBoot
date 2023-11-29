<template>
    <!-- <h1>{{ pistas }}</h1> -->
     <body class="">
       <div class="container" v-for="pista in pistas">
            <div class="card float-right">
                <div class="row">
                    <div class="col-sm-5">
                    <img class="d-block w-100 img-card" :src="pista.image">
                    </div>
                    <div class="col-sm-7">
                    <div class="card-block">
                        <h4 class="card-title">{{ pista.pista_id }}</h4>
                        <p>{{ pista.description }}</p>
                        <!-- <h4>Que deporte te gustaria practicar?</h4> -->
                        <div class="row tarjetas">
                            <div v-for="deporte in pista.sports" :key="deporte.id" class="col-md-5">
                                <div class="mini-card">
                                    <div class="mini-card-body">
                                    <h3 class="mini-card-title">{{ deporte.sport_name }}</h3>
                                    <p class="mini-card-price">{{ deporte.price }} €/h</p>
                                    <a href="#" class="btn btn-primary btn-sm float-right"> Reservar ahora</a>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <br>
                    </div>
                    </div>
                </div>
            </div> 
        </div>
    </body>
</template>

<script>
import { useRoute, useRouter } from 'vue-router'
export default {

    props: {
        pistas: Object,
    },
    setup(props){

        // console.log(props.pistas);
        const router = useRouter();
        

        const details = (id) => {
            // console.log(id);
            router.push({ name: "detailsPista", params: { id } })
        }
    return { details }
    }
}
</script>
<style lang="scss">
body {
  background-color:  #eee;
}
.title {
 
    margin-bottom: 50px;
    text-transform: uppercase;
}

.card-block {
    font-size: 1em;
    position: relative;
    margin: 0;
    padding: 1em;
    padding-bottom: 0;
    border: none;
    border-top: 1px solid rgba(34, 36, 38, .1);
    box-shadow: none;
     
}
.card {
    font-size: 1em;
    overflow: hidden;
    padding: 5;
    border: none;
    border-radius: .28571429rem;
    box-shadow: 0 1px 3px 0 #d4d4d5, 0 0 0 1px #d4d4d5;
    margin-top:20px;
}

.mini-card{
    font-size: 0.7em;
    text-align: center;
    border-radius: .28571429rem;
    box-shadow: 0 1px 3px 0 #d4d4d5, 0 0 0 1px #d4d4d5;
    margin-top:20px;
}

.mini-card .mini-card-body{
    padding: 0.5em;
    padding-bottom: 0.5em;
}

.btn {
    margin-top: auto;
}

.mini-card-price{
    font-size: 14px;
}

a{
    text-decoration: none;
    color: black;
    padding-bottom: 0;
}

.img-card{
    height: 300px;
    width: 200px;
}
.tarjetas{
    justify-content: center;
}
</style>