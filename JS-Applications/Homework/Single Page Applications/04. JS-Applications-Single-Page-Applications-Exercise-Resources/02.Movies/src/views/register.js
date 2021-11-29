import { register } from "../api/data.js";

let ctx = null;

const section = document.getElementById('form-sign-up');
section.remove();

export async function showRegisterPage(ctxTarget){
    ctx = ctxTarget;
    ctx.showSection(section);
}


const form = section.querySelector('form');
form.addEventListener('submit', async (event) => {
    event.preventDefault();
    const formData = new FormData(form);
    const email = formData.get('email');
    const password = formData.get('password');
    const repass = formData.get('Repeat-Password');

    if (email == '' || password == '' || repass == '') {
        alert('All fields are required!')
        return;
    }

    await register(email, password);
    form.reset()
    ctx.updateNav();
    ctx.goTo('home');
})
