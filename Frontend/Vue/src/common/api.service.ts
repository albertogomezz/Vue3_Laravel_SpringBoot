// import JwtService from "@/common/jwt.service";
import axios from "axios";
import { API_URL } from "./config";

export const axiosClient = axios.create({
  baseURL: API_URL
});

const ApiService = {
  // setHeader() {
  //   axiosClient.defaults.headers[
  //     "Authorization"
  //   ] = `Token ${JwtService.getToken()}`;
  // },

  query(resource, params) {
    return axiosClient.get(resource, params).catch((error) => {
      throw new Error(`[RWV] ApiService ${error}`);
    });
  },

  get(resource, slug = "") {
    return axiosClient.get(`${resource}/${slug}`).catch((error) => {
      throw new Error(`[RWV] ApiService ${error}`);
    });
  },

  post(resource, params?: any) {
    return axiosClient.post(`${resource}`, params);
  },

  update(resource, slug, params) {
    return axiosClient.put(`${resource}/${slug}`, params);
  },

  put(resource, params) {
    return axiosClient.put(`${resource}`, params);
  },

  delete(resource) {
    return axiosClient.delete(resource).catch((error) => {
      throw new Error(`[RWV] ApiService ${error}`);
    });
  }
};

export default ApiService;

// export const TagsService = {
//   get() {
//     return ApiService.get("tags");
//   }
// };

export const PistasServices = {
  // query(type, params) {
  //   return ApiService.query("pistas" + (type === "feed" ? "/feed" : ""), {
  //     params: params
  //   });
  // },
  get(slug) {
    return ApiService.get("pistas", slug);
  },
  create(params) {
    return ApiService.post("pistas", { article: params });
  },
  update(slug, params) {
    return ApiService.update("pistas", slug, { article: params });
  },
  destroy(slug) {
    return ApiService.delete(`pistas/${slug}`);
  }
};

export const CommentsService = {
  get(slug) {
    if (typeof slug !== "string") {
      throw new Error(
        "[RWV] CommentsService.get() article slug required to fetch comments"
      );
    }
    return ApiService.get("articles", `${slug}/comments`);
  },

  post(slug, payload) {
    return ApiService.post(`articles/${slug}/comments`, {
      comment: { body: payload }
    });
  },

  destroy(slug, commentId) {
    return ApiService.delete(`articles/${slug}/comments/${commentId}`);
  }
};

export const FavoriteService = {
  add(slug) {
    return ApiService.post(`articles/${slug}/favorite`);
  },
  remove(slug) {
    return ApiService.delete(`articles/${slug}/favorite`);
  }
};
