<template>
    <Carousel v-bind="settings" :breakpoints="breakpoints" :wrap-around="true">
        <slide v-for="item in sports" :key="item.id">
            <carousel_itemVue class="carousel__item" :item="item" @emitAction="emitAction" />
        </slide>
        <template #addons>
            <navigation />
            <pagination />
        </template>
    </Carousel>
</template>


<script>
import { Carousel, Slide, Pagination, Navigation } from 'vue3-carousel'
import carousel_itemVue from './carousel_item.vue';
import { getCurrentInstance } from 'vue';
export default {
    components: { carousel_itemVue, Carousel, Slide, Pagination, Navigation },
    props: {
        sports: Object
    },
    emits: {
        emitAction: Object
    },

    setup(props) {
        const { emit } = getCurrentInstance();
        const emitAction = (item) => {
            emit('emitAction', item);
            // console.log(item);
        }

        return { emitAction }
    },
    data: () => ({
    // carousel settings
    settings: {
      itemsToShow: 3,
      snapAlign: 'center'
    },
    // breakpoints are mobile first
    // any settings not specified will fallback to the carousel settings
    breakpoints: {

        300: {
        itemsToShow: 0.9,
        snapAlign: 'start',
      },
      // 700px and up
      700: {
        itemsToShow: 1,
        snapAlign: 'start',
      },
      // 1024 and up
      1024: {
        itemsToShow: 3,
        snapAlign: 'start',
      },
    },
  }),
}


</script>

<style lang="scss">
@import 'vue3-carousel/dist/carousel.css';

// .carousel__item {
//     min-height: 200px;
//     width: 100%;
//     background-color: none;
//     font-size: 20px;
//     border-radius: 8px;
//     display: flex;
//     justify-content: center;
//     align-items: center;
// }
</style>