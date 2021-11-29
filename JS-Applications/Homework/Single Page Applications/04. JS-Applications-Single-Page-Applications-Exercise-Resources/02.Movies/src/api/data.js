import * as api from './requestAPI.js';

export const login = api.login;
export const register = api.register;
export const logout = api.logout;

export async function getAllMovies() {
  return await api.getRequest("/data/movies")
}

export async function getById(id) {
  return await api.getRequest("/data/movies/" + id);
}

export async function addMovie(movie){
  return await api.postRequest('/data/movies', movie);
}

export async function removeMovie(id) {
  return await api.deleteRequest('/data/movies/' + id);
}

export async function editMovie(id, movie){
  return await api.putRequest('/data/movies/' + id, movie);
}