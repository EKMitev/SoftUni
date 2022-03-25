import { html, render } from './node_modules/lit-html/lit-html.js';

const template = (items) => html`
<select id="menu">
    ${Object.values(items).map(item => html`<option>${item.text}</option>`)}
</select>`


//on start
const data = await getData();
update(data);

document.querySelector('form').addEventListener('submit', addItem);


async function getData() {
    const res = await fetch('http://localhost:3030/jsonstore/advanced/dropdown');
    return res.json();
}

async function addItem(event) {
    event.preventDefault();

    const item = {
        text: document.getElementById('itemText').value
    }
    event.target.reset();

    const [res, data] = await Promise.all([fetch('http://localhost:3030/jsonstore/advanced/dropdown',
        {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(item)
        }), getData()]);

    update(data);
}

function update(data) {
    render(template(data), document.getElementById('root'));
}