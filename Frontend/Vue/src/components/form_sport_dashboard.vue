<template>
    <div class="login-box">
        <h2>Form</h2>
        <form>
            <div class="user-box">
                <label>Sport ID</label>
                <input type="text" name="" required="" v-model="state.sport.sport_id" />
            </div>
            <div class="user-box">
                <label>Sport Name</label>
                <input type="text" name="" required="" v-model="state.sport.sport_name" />
            </div>
            <div class="user-box">
                <label>Description</label>
                <input type="text" name="" required="" v-model="state.sport.description" />
            </div>
            <div class="user-box">
                <label>Price</label>
                <input type="text" name="" required="" v-model="state.sport.price" />
            </div>
            <div class="user-box">
                <label>Image(URL)</label>
                <input type="url" name="" v-model="state.sport.image" />
            </div>
            
            
            <br><br>
            <a @click="createSubmit()" v-if="!isUpdate">
                <span></span>
                <span></span>
                <span></span>
                <span></span>
                Create
            </a>
            <a @click="editSubmit()" v-if="isUpdate">
                <span></span>
                <span></span>
                <span></span>
                <span></span>
                Update
            </a>
            <a @click="cancel()">
                <span></span>
                <span></span>
                <span></span>
                <span></span>
                Cancel
            </a>
        </form>
    </div>
    <br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
    <br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
    <br><br><br><br><br><br><br><br><br><br><br>

</template>

<script>
import { reactive, getCurrentInstance, computed } from 'vue'
import { useRouter } from 'vue-router';
import { useStore } from 'vuex'
export default {
    props: {
        sport: Object
    },
    emits: {
        data: Object
    },
    computed: {
        isUpdate() {
            const path = this.$route.path.split('/');
            return path[3] == 'update';
        },
    },
    setup(props) {
        const router = useRouter();
        const sport = props.sport;
        // console.log(pista);
        const { emit } = getCurrentInstance();
        const store = useStore();

        const state = reactive({
            sport: { ...sport }
        });

        const createSubmit = () => {
            emit('data', state.sport)
        }

        const editSubmit = () => {
            emit('data', state.sport)
        }
        const cancel = () => {
            router.push({ name: "listSports" })
        }

        return { state, editSubmit, cancel, createSubmit }
    }
}
</script>

<style scoped lang="scss">
.login-box {
    position: absolute;
    top: 70%;
    left: 50%;
    width: 400px;
    padding: 40px;
    transform: translate(-50%, -50%);
    background: rgba(0, 0, 0, 0.5);
    box-sizing: border-box;
    box-shadow: 0 15px 25px rgba(0, 0, 0, 0.6);
    border-radius: 10px;

    h2 {
        margin: 0 0 30px;
        padding: 0;
        color: #fff;
        text-align: center;
    }

    .user-box {
        position: relative;

        input {
            width: 100%;
            padding: 10px 0;
            font-size: 16px;
            color: #fff;
            margin-bottom: 30px;
            border: none;
            border-bottom: 1px solid #fff;
            outline: none;
            background: transparent;
        }

        input {

            &:focus~label,
            &:valid~label {
                top: -20px;
                left: 0;
                color: #03e9f4;
                font-size: 12px;
            }
        }
    }

    form a {
        position: relative;
        display: inline-block;
        padding: 10px 20px;
        color: #03e9f4;
        font-size: 16px;
        text-decoration: none;
        text-transform: uppercase;
        overflow: hidden;
        transition: .5s;
        margin-top: 40px;
        letter-spacing: 4px;
    }

    a {
        &:hover {
            background: #03e9f4;
            color: #fff;
            border-radius: 5px;
            box-shadow: 0 0 5px #03e9f4, 0 0 25px #03e9f4, 0 0 50px #03e9f4, 0 0 100px #03e9f4;
        }

        span {
            position: absolute;
            display: block;

            &:nth-child(1) {
                top: 0;
                left: -100%;
                width: 100%;
                height: 2px;
                background: linear-gradient(90deg, transparent, #03e9f4);
                animation: btn-anim1 1s linear infinite;
            }

            &:nth-child(2) {
                top: -100%;
                right: 0;
                width: 2px;
                height: 100%;
                background: linear-gradient(180deg, transparent, #03e9f4);
                animation: btn-anim2 1s linear infinite;
                animation-delay: 0.25s;
            }

            &:nth-child(3) {
                bottom: 0;
                right: -100%;
                width: 100%;
                height: 2px;
                background: linear-gradient(270deg, transparent, #03e9f4);
                animation: btn-anim3 1s linear infinite;
                animation-delay: 0.5s;
            }

            &:nth-child(4) {
                bottom: -100%;
                left: 0;
                width: 2px;
                height: 100%;
                background: linear-gradient(360deg, transparent, #03e9f4);
                animation: btn-anim4 1s linear infinite;
                animation-delay: 0.75s;
            }
        }
    }
}

@keyframes btn-anim1 {
    0% {
        left: -100%;
    }

    50%,
    100% {
        left: 100%;
    }
}

@keyframes btn-anim2 {
    0% {
        top: -100%;
    }

    50%,
    100% {
        top: 100%;
    }
}

@keyframes btn-anim3 {
    0% {
        right: -100%;
    }

    50%,
    100% {
        right: 100%;
    }
}

@keyframes btn-anim4 {
    0% {
        bottom: -100%;
    }

    50%,
    100% {
        bottom: 100%;
    }
}

.login-box {
    select {
        // styles reset, including removing the default dropdown arrow
        appearance: none;
        -webkit-appearance: none;
        -moz-appearance: none;
        background-color: transparent;
        border: none;
        padding: 0 1em 0 0;
        margin: 0;
        width: 100%;
        font-family: inherit;
        font-size: inherit;
        cursor: inherit;
        line-height: inherit;

        // Stack above custom arrow
        z-index: 1;

        // Remove focus outline
        outline: none;
    }

    .select {
        display: grid;
        grid-template-areas: "select";
        align-items: center;
        position: relative;

        select,
        &::after {
            grid-area: select;
        }

        min-width: 15ch;
        max-width: 30ch;
        border: 1px solid var(--select-border);
        border-radius: 0.25em;
        padding: 0.25em 0.5em;
        font-size: 1.25rem;
        cursor: pointer;
        line-height: 1.1;

        // Optional styles
        // remove for transparency
        background: linear-gradient(to bottom, #ffffff 0%, #e5e5e5 100%);

        // Custom arrow
        &::after {
            content: "";
            justify-self: end;
            width: 0.8em;
            height: 0.5em;
            background-color: var(--select-arrow);
            clip-path: polygon(100% 0%, 0 0%, 50% 100%);
        }
    }
}
</style>