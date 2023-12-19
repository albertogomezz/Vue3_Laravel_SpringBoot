<template>
    <!-- <h1>{{ stateOne.pista }}</h1> -->
    <body class="tableMesa">
        <div class="crud-table">
            <div class="clearfix">
            </div>
            <table class="table table-striped">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Description</th>
                        <th>Type</th>
                        <th>Reserved?</th>
                        <th>Category</th>
                    </tr>
                </thead>
                <tbody class="text-center">
                        <td>{{ stateOne.pista.pista_id }}</td>
                        <td>{{ stateOne.pista.description }}</td>
                        <td>{{ stateOne.pista.type }}</td>
                        <td>{{ stateOne.pista.is_reserved ? 'true' : 'false' }}</td>
                        <td>
                            {{ getSportNames() }}
                        </td>
                </tbody>
            </table>
        </div>
    </body>
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
<style lang="scss" scoped>
body.tableMesa {
    font-family: "Roboto", helvetica, arial, sans-serif;
    font-size: 16px;
    font-weight: 400;
    text-rendering: optimizeLegibility;
}

div.table-title {
    display: block;
    margin: auto;
    max-width: 600px;
    padding: 5px;
    width: 100%;
}

.table-title h3 {
    color: #ffffff;
    font-size: 30px;
    font-weight: 400;
    font-style: normal;
    font-family: "Roboto", helvetica, arial, sans-serif;
    text-shadow: -1px -1px 1px rgba(0, 0, 0, 0.1);
    text-transform: uppercase;
}


/* Table Styles **/

.table-fill {
    background: white;
    border-radius: 3px;
    border-collapse: collapse;
    height: 320px;
    margin: auto;
    max-width: 600px;
    padding: 5px;
    width: 100%;
    box-shadow: 0 5px 10px rgba(0, 0, 0, 0.1);
    animation: float 5s infinite;
}

body.tableMesa {
    th {
        color: #D5DDE5;
        ;
        background: #1b1e24;
        border-bottom: 4px solid #9ea7af;
        border-right: 1px solid #343a45;
        font-size: 23px;
        font-weight: 100;
        padding: 24px;
        text-align: left;
        text-shadow: 0 1px 1px rgba(0, 0, 0, 0.1);
        vertical-align: middle;
    }

    th:first-child {
        border-top-left-radius: 3px;
    }

    th:last-child {
        border-top-right-radius: 3px;
        border-right: none;
    }

    tr {
        border-top: 1px solid #C1C3D1;
        border-bottom: 1px solid #C1C3D1;
        color: #666B85;
        font-size: 16px;
        font-weight: normal;
        text-shadow: 0 1px 1px rgba(256, 256, 256, 0.1);
    }

    tr:hover td {
        background: #4E5066;
        color: #FFFFFF;
        border-top: 1px solid #22262e;
    }

    tr:first-child {
        border-top: none;
    }

    tr:last-child {
        border-bottom: none;
    }

    tr:nth-child(odd) td {
        background: #EBEBEB;
    }

    tr:nth-child(odd):hover td {
        background: #4E5066;
    }

    tr:last-child td:first-child {
        border-bottom-left-radius: 3px;
    }

    tr:last-child td:last-child {
        border-bottom-right-radius: 3px;
    }

    td {
        background: #FFFFFF;
        padding: 20px;
        text-align: left;
        vertical-align: middle;
        font-weight: 300;
        font-size: 18px;
        text-shadow: -1px -1px 1px rgba(0, 0, 0, 0.1);
        border-right: 1px solid #C1C3D1;
    }

    td:last-child {
        border-right: 0px;
    }

    th.text-left {
        text-align: left;
    }

    th.text-center {
        text-align: center;
    }

    th.text-right {
        text-align: right;
    }

    td.text-left {
        text-align: left;
    }

    td.text-center {
        text-align: center;
    }

    td.text-right {
        text-align: right;
    }

}

.pulse:hover,
.pulse:focus {
    animation: pulse 1s;
    box-shadow: 0 0 0 2em transparent;
}

@keyframes pulse {
    0% {
        box-shadow: 0 0 0 0 var(--hover);
    }
}

body.tableMesa {
    $colors: (pulse: #ef6eae,
    );

    @each $button,
    $color in $colors {
        .#{$button} {
            --color: #{$color};
            --hover: #{adjust-hue($color, 45deg)};
        }
    }

    button {
        color: var(--color);
        transition: 0.25s;

        &:hover,
        &:focus {
            border-color: var(--hover);
            color: #fff;
        }
    }

    button {
        background: none;
        border: 2px solid;
        font: inherit;
        line-height: 1;
        margin: 0.5em;
        padding: 1em 2em;
    }
}
</style>