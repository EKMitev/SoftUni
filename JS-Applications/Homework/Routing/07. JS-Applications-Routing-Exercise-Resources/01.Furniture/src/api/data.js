import * as api from './api.js';
import { getUserData } from './sessionHandler.js';

export const login = api.login;
export const register = api.register;
export const logout = api.logout;

export async function getAllItems(){
    return api.get('/data/catalog');
}

export async function getById(id){
    return api.get('/data/catalog/' + id);
}

export async function createItem(item){
    return api.post('/data/catalog', item);
}

export async function editItem(id, item){
    return api.put('/data/catalog/' + id, item);
}

export async function deleteItem(id){
    return api.remove('/data/catalog/' + id);
}

export async function getMyItems(){
    const userId = getUserData().id;
    const url = (userId) => `/data/catalog?where=_ownerId%3D%22${userId}%22`
    return api.get(url(userId));
}