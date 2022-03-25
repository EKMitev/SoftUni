import { html, render } from './node_modules/lit-html/lit-html.js';

const template = (data) =>html`
<ul>
    ${data.map(c => html`<li>${c}</li>`)}
</ul>`;

document.querySelector('form').addEventListener('submit', (e) => {
    e.preventDefault();

    const dataArr = document.getElementById('towns').value.split(',').map(c => c.trim());
    const root = document.getElementById('root');

    render(template(dataArr), root)
    e.target.reset();
});