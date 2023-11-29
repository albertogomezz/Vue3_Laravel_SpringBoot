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
// .carusel_item {
//     text-align: center; /* Centra el contenido */
// }

// .carusel_item img {
//     width: 380px; /* Ancho de la imagen */
//     height:320px; /* Alto de la imagen */
//     object-fit: cover; /* Ajusta la imagen al tamaño del contenedor */
//     border-radius: 8px; /* Borde redondeado */
// }

// .carusel_item h2 {
//     margin-top: 10px; /* Espaciado superior entre la imagen y el título */
// }
.carusel_item {
    position: relative;
    display: inline-block; /* Permite que el contenedor se ajuste al tamaño del contenido */
}

.carusel_item img {
    width: 380px; /* Ancho de la imagen */
    height:320px; /* Alto de la imagen */
    object-fit: cover; /* Ajusta la imagen al tamaño del contenedor */
    border-radius: 8px; /* Borde redondeado */
}

.carusel_item h2 {
    position: absolute;
    top: 80%;
    left: 50%;
    transform: translate(-50%, -50%);
    z-index: 1; /* Asegura que el h2 esté sobre la imagen */
    color: white; /* Puedes ajustar el color del texto según tus necesidades */
    background-color: rgba(0, 0, 0, 0.78);
    padding: 10px 20px;
    border-radius: 10px;
    text-transform: uppercase;
}



</style>