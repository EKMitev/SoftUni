import { html, render } from './node_modules/lit-html/lit-html.js';

async function solve() {

   const template = (data) => Object.values(data).map(s => html`
   <tr class=${s.match ? 'select' : ''}>
      <td>${s.firstName}</td>
      <td>${s.email}</td>
      <td>${s.course}</td>
   </tr>`);

   //on start
   document.querySelector('#searchBtn').addEventListener('click', onClick);

   const students = await getData();
   update()

   function update() {
      render(template(students), document.getElementById('root'));
   }

   async function getData() {
      const res = await fetch('http://localhost:3030/jsonstore/advanced/table');
      return res.json();
   }

   function onClick() {
      const input = document.getElementById('searchField');
      const match = input.value.toLocaleLowerCase();

      Object.values(students).forEach(s => {
         if (match && (s.firstName.toLocaleLowerCase().includes(match) || s.email.toLocaleLowerCase().includes(match) || s.course.toLocaleLowerCase().includes(match))) {
            s.match = true;

         } else {
            s.match = false;
         }
         return;
      });

      input.value = '';
      update();
   }
}

solve();