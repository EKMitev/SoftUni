import { logout } from "../api/data.js";
import { getUserData } from "../api/sessionHandler.js"
import { page } from "../lib.js";

const nav = document.querySelector('nav');
const guestDiv = document.getElementById('guest')
const userDiv = document.getElementById('user')

export function updateNavbar(){
    if (getUserData()){
        nav.removeChild(guestDiv);
        nav.appendChild(userDiv);
    } else {
        nav.removeChild(userDiv);
        nav.appendChild(guestDiv);
    }
}

const logoutBtn = document.getElementById('logoutBtn');
logoutBtn.addEventListener('click', () => {
    logout();
    updateNavbar();
    page.redirect('/');
})