import { ref } from 'vue';
import PistaService from '../../services/client/PistaServiceClient';

const pistas = ref([]);

export const usePistaFilters = async (filters = {}) => {
  try {
    const res = await PistaService.GetPistas(filters);
    pistas.value = res.data;
  } catch (error) {
    console.error(error);
  }

  return pistas.value;
};

// export const useMesaPaginate = (filters = {}) => {

//     const totalPages = ref(0)
//     MesaService.GetMesasPaginate(filters)
//         .then(res => {
//             const limit = filters.limit ?? 9;
//             const total = res.data;
//             const pages = Math.ceil(total / limit);
//             totalPages.value = pages;
//         })
//         .catch(error => console.error(error))
//     return totalPages;
// };

// export const useMesaInfinite = (page = 1, limit = 3) => {
//     const mesas = ref([])
//     MesaService.GetMesasInfinite(page, limit)
//         .then(res => mesas.value = res.data)
//         .catch(error => console.error(error))
//     return mesas;
// };
