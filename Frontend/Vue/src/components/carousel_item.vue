<template :key="item.sport_name">
    <div class="carusel_item" @click="emitAction()">
        <img :src="item.image" />
        <h2>{{ item.sport_name }}</h2>
    </div>
   
</template>

<script>
import { getCurrentInstance } from 'vue';
export default {
    props: {
        item: Object
    },
    emits: {
        emitAction: Object
    },
    
    setup(props) {
        // console.log(props.item);
        const { emit } = getCurrentInstance();
        const emitAction = () => {
                emit('emitAction', props.item.sport_name);
        }

        return { emitAction }
    }
}
</script>

<style lang="scss">

.carusel_item {
    position: relative;
    display: inline-block; 
}

.carusel_item img {
    width: 380px; 
    height:320px; 
    object-fit: cover; 
    border-radius: 8px; 
}

.carusel_item h2 {
    position: absolute;
    top: 80%;
    left: 50%;
    transform: translate(-50%, -50%);
    z-index: 1; 
    color: white; 
    background-color: rgba(0, 0, 0, 0.78);
    padding: 10px 20px;
    border-radius: 10px;
    text-transform: uppercase;
}
</style>