import { login } from "../api/data.js";

let ctx = null; 

const section = document.getElementById('form-login');
section.remove();

const form = section.querySelector('form');
form.addEventListener('submit', async (event) => {
    event.preventDefault();
    const formData = new FormData(form);
    const email = formData.get('email');
    const password = formData.get('password');

    if (email == '' || password == '') {
        alert('All fields are required!')
        return;
    }

    await login(email, password);
    form.reset()
    ctx.updateNav();
    ctx.goTo('home');
})

export async function showLoginPage(ctxTarget){
    ctx = ctxTarget
    ctx.showSection(section);
}