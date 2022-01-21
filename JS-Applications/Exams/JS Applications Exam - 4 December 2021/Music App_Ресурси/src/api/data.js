import * as api from './api.js';
import { getUserData } from './sessionHandler.js';

export const login = api.login;
export const register = api.register;
export const logout = api.logout;

export async function getAll() {
    return api.get('/data/albums?sortBy=_createdOn%20desc&distinct=name')
}

export async function createAlbum(album) {
    return api.post('/data/albums', album);
}

export async function getById(id) {
    return api.get('/data/albums/' + id);
}

export async function del(id) {
    return api.del('/data/albums/' + id);
}

export async function editAlbum(id, album) {
    return api.put('/data/albums/' + id, album);
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
    return api.get(`/data/albums?where=name%20LIKE%20%22${query}%22`);
}