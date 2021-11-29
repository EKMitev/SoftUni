import { html, render } from './node_modules/lit-html/lit-html.js';
import { cats as catsArr } from './catSeeder.js';

const template = (cats) => html`
    <ul>
        ${cats.map(c => html`
        <li>
            <img src="./images/${c.imageLocation}.jpg" width="250" height="250" alt="Card image cap">
            <div class="info">
                <button @click=${()=> toggle(c)} class="showBtn">${c.info ? 'Hide' : 'Show'} status code</button>
                ${c.info ? html`<div class="status" id="${c.id}">
                    <h4>Status Code: ${c.statusCode}</h4>
                    <p>${c.statusMessage}</p>
                </div>` : null}
            </div>
        </li>`)}
    </ul>`;


catsArr.forEach(c => c.info = false);
update();

function toggle(cat) {
    cat.info = !cat.info;
    update();
}

function update() {
    render(template(catsArr), document.getElementById('allCats'));
}