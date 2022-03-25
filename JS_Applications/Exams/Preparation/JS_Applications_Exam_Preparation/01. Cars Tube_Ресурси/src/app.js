import { page, render } from './lib.js';
import { catalogPage } from './views/catalog.js';
import { createPage } from './views/create.js';
import { detailsPage } from './views/details.js';
import { editPage } from './views/edit.js';
import { homePage } from './views/home.js';
import { loginPage } from './views/login.js';
import { myPage } from './views/myPage.js';
import { updateNavbar } from './views/navigationBar.js';
import { registerPage } from './views/register.js';
import { searchPage } from './views/search.js';

page(decorateContext);
page('/', homePage);
page('/catalog', catalogPage);
page('/myPage', myPage);
page('/create', createPage);
page('/details/:id', detailsPage);
page('/edit/:id', editPage);
page('/login', loginPage);
page('/register', registerPage);
page('/search', searchPage);

const root = document.getElementById('site-content');
function decorateContext(ctx, next){
    ctx.render = (template) => render(template, root);
    ctx.updateNavbar = () => updateNavbar();
    next();
}

//On Start
updateNavbar();
page.start();