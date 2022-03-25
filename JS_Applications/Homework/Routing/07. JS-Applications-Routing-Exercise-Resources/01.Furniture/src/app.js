import { getUserData, clearData } from './api/sessionHandler.js'
import { page, render, html } from './lib.js'

import { homePage } from './views/catalog.js'
import { createPage } from './views/create.js'
import { detailsPage } from './views/details.js'
import { editPage } from './views/edit.js'
import { loginPage } from './views/login.js'
import { myPage } from './views/my-furniture.js'
import { registerPage } from './views/register.js'

page(decorateContext);
page('/', homePage);
page('/create', createPage);
page('/details/:id', (ctx) => detailsPage(ctx));
page('/edit/:id', (ctx) => editPage(ctx));
page('/login', loginPage);
page('/register', registerPage);
page('/my-furniture', myPage);

function decorateContext(ctx, next) {
    ctx.render = (content) => render(content, document.getElementById('root'));
    ctx.updateNav = () => updateNav();

    next();
}

page.start();
updateNav(); 


function updateNav() {
    const user = getUserData();

    const navTemplate = () => html`
    <a id="catalogLink" href="/" class="active">Dashboard</a>
    ${user 
    ? html`
        <div id="user">
            <a id="createLink" href="/create">Create Furniture</a>
            <a id="profileLink" href="/my-furniture">My Publications</a>
            <a @click=${logout} id="logoutBtn" href="javascript:void(0)">Logout</a>
        </div>`
    : html`
        <div id="guest">
            <a id="loginLink" href="/login">Login</a>
            <a id="registerLink" href="/register">Register</a>
        </div>`}`;

    render(navTemplate(), document.querySelector('nav'))
};

function logout(){
    clearData();
    updateNav();
    page.redirect('/');
}
