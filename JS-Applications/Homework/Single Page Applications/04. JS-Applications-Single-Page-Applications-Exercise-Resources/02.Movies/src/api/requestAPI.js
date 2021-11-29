const host = 'http://localhost:3030';

async function request(url, options) {
    try {
        const response = await fetch(host + url, options);

        if (response.ok == false) {
            if (response.status == 403){
                sessionStorage.removeItem("userData");
            }
            const error = await response.json();
            throw new Error(error.message)
        }

        if (response.status == 204) {
            return response;
        }

        return response.json();

    } catch (err) {
        alert(err.message);
        throw new Error(err);
    }
}

function options(method = 'GET', data) {
    const options = {
        method,
        headers: {}
    }

    if (data != undefined) {
        options.headers['Content-Type'] = 'application/json';
        options.body = JSON.stringify(data);
    }

    const userData = JSON.parse(sessionStorage.getItem('userData'));

    if (userData !== null) {
        options.headers['X-Authorization'] = userData.token;
    }

    return options;
}

export async function getRequest(url) {
    return request(url);
}

export async function postRequest(url, data) {
    return request(url, options('post', data));
}

export async function putRequest(url, data) {
    return request(url, options('put', data));
}

export async function deleteRequest(url) {
    return request(url, options('delete'));
}

export async function login(email, password) {
    const result = await postRequest('/users/login', { email, password });

    const userData = {
        email: result.email,
        id: result._id,
        token: result.accessToken
    }

    sessionStorage.setItem('userData', JSON.stringify(userData));
}

export async function register(email, password) {
    const result = await postRequest('/users/register', { email, password });

    const userData = {
        email: result.email,
        id: result.id,
        token: result.accessToken
    }

    sessionStorage.setItem('userData', JSON.stringify(userData));
}

export async function logout() {
   
    sessionStorage.removeItem('userData');
}