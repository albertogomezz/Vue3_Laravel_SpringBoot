<template>
    <section id="services" class="services section-bg">
      <div class="container-fluid">
        <div class="col-sm-12 text-center mb-4">
        </div>
        <div class="row row-sm">
          <div class="col-md-6 _boxzoom">
            <div class="_product-images">
                <div class="picZoomer">
                    <img class="my_img" :src="stateOne.pista.image" alt="">
                </div>
            </div>
          </div>
          <div class="col-md-6">
            <div class="_product-detail-content">
              <p class="_p-name"> {{stateOne.pista.pista_id}} </p>
              <div class="_p-price-box">
                <div v-if="stateOne.pista.sports.length === 2" class="sport-card" v-for="(sport, index) in stateOne.pista.sports" :key="index">
                  <div class="p-list">
                    <span> Precio </span>
                    <p> {{ sport.price }} </p>
                  </div>
                  <div class="_p-features">
                    <span> Description </span>
                    <p> {{ sport.description }} </p>
                  </div>
                </div>
                <div v-else-if="stateOne.pista.sports.length === 1" class="sport-card">
                  <div class="p-list">
                    <span> Precio </span>
                    <p> {{ stateOne.pista.sports[0].price }} </p>
                  </div>
                  <div class="_p-features">
                    <span> Description </span>
                    <p> {{ stateOne.pista.sports[0].description }} </p>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </section>
  </template>

<script>
import Constant from '../Constant';
import { useRoute } from 'vue-router'
import { reactive, computed } from 'vue'
import { useStore } from 'vuex'
export default {

    setup() {

        const route = useRoute()
        const store = useStore();
        const id = route.params.id;

        store.dispatch(`pistaClient/${Constant.GET_ONE_PISTA}`, id)

        const stateOne = reactive({
            pista: computed(() => store.getters["pistaClient/getOnePista"])
        })

        // stateOne.pista.is_reserved = Boolean(stateOne.pista.is_reserved);

        return { stateOne }
    },
    methods: {
        
        getSportNames() {

            const store = useStore();
            const stateOne = reactive({

                pista: computed(() => store.getters["pistaClient/getOnePista"])
            })
            return stateOne.pista.sports.map(sport => sport.sport_name).join(' | ')
        }
    }
}
</script>
<style scoped>
  .picZoomer {
    text-align: left;
    margin-right: 20px;
  }

  .my_img {
    max-width: 100%;
    display: block;
    margin: 0;
    border-top-right-radius: 10px;
    border-bottom-left-radius: 10px;
  }

  .sport-card {
    border: 1px solid #ccc;
    padding: 10px;
    margin-bottom: 10px;
    border-radius: 5px;
    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
    display: flex;
    justify-content: space-between;
  }

  .sport-card .p-list,
  .sport-card ._p-features {
    flex: 1;
  }

  .sport-card .p-list {
    margin-right: 10px;
  }

  .sport-card ._p-features {
    color: #666;
    display: flex;
    flex-direction: column;
  }

  .sport-card ._p-features span {
    font-weight: bold;
  }
</style>