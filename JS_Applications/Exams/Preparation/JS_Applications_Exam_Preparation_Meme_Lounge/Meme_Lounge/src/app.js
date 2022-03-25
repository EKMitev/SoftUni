import { html, page, render } from './lib.js';
import { logout } from './api/data.js';
import { getUserData } from './api/util.js';
import { homePage } from './views/home.js'
import { loginPage } from './views/login.js';
import { registerPage } from './views/register.js';
import { catalogPage } from './views/catalog.js';
import { createPage } from './views/create.js';
import { myPage } from './views/myMemes.js';
import { detailsPage } from './views/details.js';
import { editPage } from './views/edit.js';

const root = document.querySelector('main');
const logoutBtn = document.querySelector('#logoutBtn');
logoutBtn.addEventListener('click', onLogout);

page(decorateContext);
page('/', homePage);
page('/login', loginPage);
page('/register', registerPage);
page('/catalog', catalogPage);
page('/create', createPage);
page('/details/:id', detailsPage);
page('/edit/:id', editPage);
page('/profile', myPage);

//on start
updateNavbar();
page.start();

function decorateContext(ctx, next) {
    ctx.render = (template) => render(template, root)
    ctx.updateNavbar = () => updateNavbar();
    next();
}


function updateNavbar() {
    const user = getUserData();

    if (user) {
        document.querySelector('.guest').style.display = 'none';
        document.querySelector('.user').style.display = 'block';
        document.querySelector('.user span').textContent = `Welcome, ${user.email}`;
    } else {
        document.querySelector('.guest').style.display = 'block';
        document.querySelector('.user').style.display = 'none';
    }
}

async function onLogout() {
    logout();
    updateNavbar();
    page.redirect('/');
}

