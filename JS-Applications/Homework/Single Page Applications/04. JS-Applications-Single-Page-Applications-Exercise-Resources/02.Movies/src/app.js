import { showHomePage } from "./views/home.js"
import { showRegisterPage } from "./views/register.js"
import { showLoginPage } from "./views/login.js"
import { showAddPage } from "./views/addMovie.js"
import { showDetailsPage } from "./views/details.js"
import { showEditPage } from "./views/edit.js"
import { showSection } from "./dom.js"
import { logout } from "./api/requestAPI.js"

const links = {
    'homeLink': 'home',
    'loginLink': 'login',
    'registerLink': 'register'
}

const views = {
    'home': showHomePage,
    'login': showLoginPage,
    'register': showRegisterPage,
    'add': showAddPage,
    'details': showDetailsPage,
    'edit': showEditPage
}

const nav = document.querySelector('nav');
nav.addEventListener('click', onNavigate);

const ctx = {
    goTo,
    showSection,
    updateNav
}

nav.querySelector('#logoutBtn').addEventListener('click', () => {
    logout()
    updateNav();
    goTo('home')
})

//start app in home view
goTo('home');
updateNav();

function onNavigate(event) {
    const name = links[event.target.id]
    if (name) {
        event.preventDefault();
        goTo(name);
    }
}

function goTo(name, ...params) {
    const view = views[name];
    if (typeof view == 'function') {
        view(ctx, ...params)
    }
}

function updateNav(){
    let name = undefined;
    if (sessionStorage.userData){
         name = JSON.parse(sessionStorage.userData).email
    }

    if (name) {
        nav.querySelector('#user').innerText = 'Welcome, ' + name;
        nav.querySelector('#loginLink').style.display = 'none';
        nav.querySelector('#registerLink').style.display = 'none';
        nav.querySelector('#logoutBtn').style.display = 'block';

    } else {
        nav.querySelector('#user').innerText = 'Welcome, email';
        nav.querySelector('#loginLink').style.display = 'block';
        nav.querySelector('#registerLink').style.display = 'block';
        nav.querySelector('#logoutBtn').style.display = 'none';
    }
}
