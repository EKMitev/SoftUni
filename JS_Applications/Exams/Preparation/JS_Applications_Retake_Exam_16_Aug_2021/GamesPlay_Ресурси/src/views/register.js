import { register } from '../api/api.js';
import { html } from '../lib.js';

const template = (onSubmit) => html`
<!-- Register Page ( Only for Guest users ) -->
<section id="register-page" class="content auth">
    <form @submit=${onSubmit} id="register">
        <div class="container">
            <div class="brand-logo"></div>
            <h1>Register</h1>

            <label for="email">Email:</label>
            <input type="email" id="email" name="email" placeholder="maria@email.com">

            <label for="pass">Password:</label>
            <input type="password" name="password" id="register-password">

            <label for="con-pass">Confirm Password:</label>
            <input type="password" name="confirm-password" id="confirm-password">

            <input class="btn submit" type="submit" value="Register">

            <p class="field">
                <span>If you already have profile click <a href="/login">here</a></span>
            </p>
        </div>
    </form>
</section>`;

export function registerPage(ctx) {
    ctx.render(template(onSubmit));


    async function onSubmit(event) {
        event.preventDefault();

        const formData = new FormData(document.querySelector('form'));
        const email = formData.get('email').trim();
        const password = formData.get('password').trim();
        const repass = formData.get('confirm-password').trim();

        if (email == '' || password == '') {
            return alert('All fields are required!');
        }

        if (password != repass) {
            return alert('Passwords don\'t match!');
        }

        await register(email, password);

        ctx.updateNavbar();
        ctx.page.redirect('/');
    }
}