import { html, render } from './node_modules/lit-html/lit-html.js';
import { towns as townNames } from './towns.js';

const template = (towns) => html`
<ul>
   ${towns.map(t => html`<li class=${t.match ? 'active' : '' }>${t.name}</li>`)}
</ul>`;

const towns = townNames.map(t =>
({
   name: t,
   match: false,
}));

document.querySelector('button').addEventListener('click', search);

update();

function update() {
   render(template(towns), document.getElementById('towns'));
}

function search() {
   const input = document.getElementById('searchText');
   const match = input.value.toLocaleLowerCase();

   let counter = 0;
   towns.forEach(t => {
      if (match && t.name.toLocaleLowerCase().includes(match)) {
         t.match = true;
         counter++;
      } else {
         t.match = false;
      }
      return;
   });

   input.value = '';
   document.getElementById('result').textContent = `${counter} matches found`
   update();
}