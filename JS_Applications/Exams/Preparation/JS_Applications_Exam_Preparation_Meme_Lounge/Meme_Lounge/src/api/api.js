import { clearData, getUserData, setUserData} from "./util.js";
import { notify } from '../notify.js'

const host = 'http://localhost:3030';


export async function get(url) {
    return request(url, createOptions());
}

export async function post(url, data) {
    return request(url, createOptions('post', data));
}

export async function put(url, data) {
    return request(url, createOptions('put', data));
}

export async function remove(url) {
    return request(url, createOptions('delete'));
}

export async function login(email, password) {
    const res = await post('/users/login', {email, password});

    const user = {
        username: res.username,
        email: res.email, 
        id: res._id,
        gender: res.gender,
        token: res.accessToken
    }

    setUserData(user);
}

export async function register(username, email, password, gender) {
    const res = await post('/users/register', {username, email, password, gender});

    const user = {
        username: res.username,
        email: res.email, 
        id: res._id,
        gender: res.gender,
        token: res.accessToken
    }
    
    setUserData(user);
}

export async function logout(){
    get('/users/logout');
    clearData();
}


async function request(url, options) {
    try {
        const res = await fetch(host + url, options);
        if (res.ok != true) {
            if (res.status == 403) {
                clearData()
            }
            const error = await res.json();
            throw new Error(error.message);
        }

        // if (res.status == 204) {
        //     return res;
        // }

        try {
            return res.json();
        } catch {
            return res;
        }

        // return res.json();
    } catch (err) {
        notify(err.message);
        //alert(err.message);
        throw err;
    }
}

function createOptions(method = 'get', body) {
    const options = {
        method,
        headers: {}
    }

    if (body != undefined) {
        options.headers['Content-Type'] = 'application/json';
        options.body = JSON.stringify(body);
    }

    const userData = getUserData();
    if (userData != null) {
        options.headers['X-Authorization'] = userData.token;
    }

    return options;
}