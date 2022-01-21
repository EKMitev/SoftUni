import * as api from './api.js';
import { getUserData } from './sessionHandler.js';

export const login = api.login;
export const register = api.register;
export const logout = api.logout;

export async function getAll() {
    return api.get('/data/cars?sortBy=_createdOn%20desc')
}

export async function createCar(car) {
    return api.post('/data/cars', car);
}

export async function getById(id) {
    return api.get('/data/cars/' + id);
}

export async function del(id) {
    return api.del('/data/cars/' + id);
}

export async function editCar(id, car) {
    return api.put('/data/cars/' + id, car);
}

export async function getMyItems(){
    const user = getUserData();
    return api.get(`/data/cars?where=_ownerId%3D%22${user.id}%22&sortBy=_createdOn%20desc`);
}

//bonus comments
export async function getComments(gameId) {
    return api.get(`/data/comments?where=gameId%3D%22${gameId}%22`);
}

export async function postComment(comment) {
    return api.post('/data/comments', comment);
}

//bonus search
export async function getSearched(query) {
    return api.get(`/data/cars?where=year%3D${query}`);
}