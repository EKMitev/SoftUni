import * as api from './api.js';

export const login = api.login;
export const register = api.register;
export const logout = api.logout;

export async function getMemes() {
    return api.get('/data/memes?sortBy=_createdOn%20desc');
}

export async function createMeme(meme) {
    return api.post('/data/memes', meme);
}

export function getMyMemes(id) {
    return api.get(`/data/memes?where=_ownerId%3D%22${id}%22&sortBy=_createdOn%20desc`);
}

export async function getById(id) {
    return api.get('/data/memes/' + id);
}

export async function editMeme(id, meme) {
    return api.put('/data/memes/' + id, meme);
}

export async function deleteMeme(id) {
    return api.remove('/data/memes/' + id);
}