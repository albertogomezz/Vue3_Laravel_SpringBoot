<template>
        <Sport_item  :data="data" @emitAction="emitAction" />
        <InfiniteLoading @infinite="scroll" :distance="1" />
</template>

<script>
import InfiniteLoading from "v3-infinite-loading";
import Sport_item from "./sport_item.vue";
import { getCurrentInstance } from 'vue';
export default {
    components: { InfiniteLoading, Sport_item },
    props: {
        data: Object
    },
    emits: {
        page: Number,
        emitAction: Object
    },
    setup(props) {
        const { emit } = getCurrentInstance();
        const emitAction = (sport_name) => {
            // console.log(sport_name);
            emit('emitAction', sport_name);
        }
        
        let page = 0;
        const scroll = ($state) => {
            page++
            if (page) {
                emit('page', page);
            }
        }

        return { scroll, emitAction }
    }
}
</script>

<style lang="scss">
@import 'v3-infinite-loading/lib/style.css';

</style>