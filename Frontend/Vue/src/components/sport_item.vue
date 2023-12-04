<template>
    <div class="container mt-100">
      <div class="aliniar">
        <div v-for="sport in data" class="col-md-4 col-sm-6 category">
          <div class="card mb-30">
              <div class="inner">
                <div class="main-img"><img class="img-list rounded-top" :src="sport.image" alt="Category"></div>
              </div>
            <div class="card-body text-center">
               <h4 class="sport_item_title">{{ sport.sport_name }}</h4>
              <button class="btn btn-outline-primary btn-sm" data-abc="true" @click="emitAction(sport.sport_name)">
                View Pistas
              </button>
            </div>
          </div>
        </div>
      </div>
    </div>
</template>
<script>
import { getCurrentInstance } from 'vue';
export default {
    props: {
        data: Object
    },
    emits: {
        emitAction: Object
    },

    setup(props) {
        // console.log(props.item);
        const { emit } = getCurrentInstance();
        const emitAction = (sport_name) => {
            // console.log(sport_name);
                emit('emitAction', sport_name);
        }

        return { emitAction }
    }
}

</script>

<style>

.img-list {
    width: 100%; 
    height:240px; 
    object-fit: cover; 
    /* border-radius: 8px;  */
}
 .sport_item_title {
    position: absolute;
    top: 60%;
    left: 50%;
    transform: translate(-50%, -50%);
    z-index: 1; 
    color: white; 
    background-color: rgba(0, 0, 0, 0.78);
    padding: 10px 20px;
    border-radius: 10px;
    text-transform: uppercase;
} 

body {
    background-color: #eee
}

.mt-100{
    margin-top: 100px;
}

.card {
    border-radius: 7px !important;
    border-color: #e1e7ec;
    margin: 20px;
}

.mb-30 {
    margin-bottom: 30px !important;
}

.card-img-tiles {
    display: block;
    border-bottom: 1px solid #e1e7ec;
}

a {
    color: black;
    text-decoration: none !important;
}

.card-img-tiles>.inner {
    display: table;
    width: 100%;
}

.card-img-tiles .main-img, .card-img-tiles .thumblist {
    display: table-cell;
    width: 65%;
    padding: 15px;
    vertical-align: middle;
}

.card-img-tiles .main-img>img:last-child, .card-img-tiles .thumblist>img:last-child {
    margin-bottom: 0;
}

.card-img-tiles .main-img>img, .card-img-tiles .thumblist>img {
    display: block;
    width: 100%;
    margin-bottom: 6px;
}
.thumblist {
    width: 35%;
    border-left: 1px solid #e1e7ec !important;
    display: table-cell;
    width: 65%;
    padding: 15px;
    vertical-align: middle;
}

.card-img-tiles .thumblist>img {
    display: block;
    width: 100%;
    margin-bottom: 6px;
}
.btn-group-sm>.btn, .btn-sm {
    padding: .45rem .5rem !important;
    font-size: .875rem;
    line-height: 1.5;
    border-radius: .2rem;
}

.aliniar{
    display: flex;
    justify-content: center;
    align-items: center;
    flex-wrap: wrap;
    text-align: center;
}

</style>