import { html, render } from "../lib.js";
import { login } from '../api/data.js'

const loginTemplate = (loginUser) => html`
<div class="row space-top">
    <div class="col-md-12">
        <h1>Login User</h1>
        <p>Please fill all fields.</p>
    </div>
</div>
<form @submit=${loginUser}>
    <div class="row space-top">
        <div class="col-md-4">
            <div class="form-group">
                <label class="form-control-label" for="email">Email</label>
                <input class="form-control" id="email" type="text" name="email">
            </div>
            <div class="form-group">
                <label class="form-control-label" for="password">Password</label>
                <input class="form-control" id="password" type="password" name="password">
            </div>
            <input type="submit" class="btn btn-primary" value="Login" />
        </div>
    </div>
</form>`

export function loginPage(ctx) {
    ctx.render(loginTemplate(loginUser))

    async function loginUser(e) {
        e.preventDefault();

        const formData = new FormData(document.querySelector('form'));

        const email = formData.get('email').trim();
        const password = formData.get('password').trim();

        try {
            const res = await login(email, password)
        } catch (err) {
            return;
        }
        ctx.updateNav();
        ctx.page.redirect('/');
    }
}
