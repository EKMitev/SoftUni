function solve() {
   const [creator, title, category] = document.querySelectorAll('input');
   const content = document.querySelector('textarea');
   const createBTN = document.querySelector('.btn');

   createBTN.addEventListener('click', create);

   function create(e) {
      e.preventDefault();
      const p1 = createEl('p', {}, 'Category:', createEl('strong', {}, category.value))
      const p2 = createEl('p', {}, 'Creator:', createEl('strong', {}, creator.value))
      const p3 = createEl('p', {}, content.textContent)

      const btn1 = createEl('button', { className: 'btn delete' }, 'Delete')
      btn1.addEventListener('click', () => { deleteArticle(article) });

      const btn2 = createEl('button', { className: "btn archive" }, 'Archive')
      btn2.addEventListener('click', () => { archive(article) });

      const div = createEl('div', { className: "buttons" }, btn1, btn2)

      const article = createEl('article', {},
         createEl('h1', {}, title.value),
         p1,
         p2,
         p3,
         div)

      document.querySelector('main > section').appendChild(article);
   }

   function deleteArticle(article) {
      article.remove();
   }

   function archive(article) {
      const ol = document.querySelector('ol');
      const li = createEl('li', {}, article.firstChild.textContent)
      ol.appendChild(li)
      Array.from(ol.getElementsByTagName("LI"))
         .sort((a, b) => a.textContent.localeCompare(b.textContent))
         .forEach(li => ol.appendChild(li));
      article.remove()
   }

   function createEl(type, attributes, ...value) {
      const el = document.createElement(type);

      for (const prop in attributes) {
         el[prop] = attributes[prop];
      }

      for (let item of value) {
         if (typeof item == 'string' || typeof item == 'number') {
            item = document.createTextNode(item);
         }
         el.appendChild(item);
      }

      return el;
   }
}
