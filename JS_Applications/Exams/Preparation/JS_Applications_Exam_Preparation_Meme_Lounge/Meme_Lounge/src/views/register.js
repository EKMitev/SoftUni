import { register } from '../api/data.js';
import { html } from '../lib.js';
import { notify } from '../notify.js';

const registerTemplate = (onSubmit) => html`
<!-- Register Page ( Only for guest users ) -->
<section id="register">
    <form @submit=${onSubmit} id="register-form">
        <div class="container">
            <h1>Register</h1>
            <label for="username">Username</label>
            <input id="username" type="text" placeholder="Enter Username" name="username">
            <label for="email">Email</label>
            <input id="email" type="text" placeholder="Enter Email" name="email">
            <label for="password">Password</label>
            <input id="password" type="password" placeholder="Enter Password" name="password">
            <label for="repeatPass">Repeat Password</label>
            <input id="repeatPass" type="password" placeholder="Repeat Password" name="repeatPass">
            <div class="gender">
                <input type="radio" name="gender" id="female" value="female">
                <label for="female">Female</label>
                <input type="radio" name="gender" id="male" value="male" checked>
                <label for="male">Male</label>
            </div>
            <input type="submit" class="registerbtn button" value="Register">
            <div class="container signin">
                <p>Already have an account?<a href="/login">Sign in</a>.</p>
            </div>
        </div>
    </form>
</section>`;

export function registerPage(ctx) {
    ctx.render(registerTemplate(onSubmit));

    async function onSubmit(event) {
        event.preventDefault();
        const formData = new FormData(document.querySelector('form'));

        const missing = [...formData.entries()]
            .some(x => x[1] == '');

        if (missing) {
            return notify('All fields are required!');
        }

        const user = {};

        [...formData.entries()].forEach(x => user[x[0]] = x[1]);

        if(user.password != formData.get('repeatPass')) {
            return notify('Passwords don\'t match!');
        }

        await register(user.username, user.email, user.password, user.gender);

        ctx.updateNavbar();
        ctx.page.redirect('/catalog');
    }
}