import { logout } from "../api/data.js";
import { getUserData } from "../api/sessionHandler.js"
import { page } from "../lib.js";

const nav = document.querySelector('nav');
const guestEl = document.querySelectorAll('.guest')
const userEl = document.querySelectorAll('.user')

export function updateNavbar(){
    const user = getUserData();
    if (user){
        [...guestEl].map(el => nav.querySelector('ul').removeChild(el));
        [...userEl].map(el => nav.querySelector('ul').appendChild(el));
       
    } else {
        [...userEl].map(el => nav.querySelector('ul').removeChild(el));
        [...guestEl].map(el => nav.querySelector('ul').appendChild(el));
    }
}

const logoutBtn = document.getElementById('logoutBtn');
logoutBtn.addEventListener('click', () => {
    logout();
    updateNavbar();
    page.redirect('/');
})