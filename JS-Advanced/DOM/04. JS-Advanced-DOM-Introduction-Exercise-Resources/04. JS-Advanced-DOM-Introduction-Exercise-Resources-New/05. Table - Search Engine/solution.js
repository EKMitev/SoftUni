function solve() {
   document.querySelector('#searchBtn').addEventListener('click', onClick);

   function onClick() {
      const input = document.getElementById('searchField').value;
      const table = document.querySelectorAll('tbody tr');

      for (let i = 0; i < table.length; i++) {
         if (table[i].textContent.includes(input)){
            table[i].classList.add('select')
         } else{
            table[i].classList.remove('select')
         }
      }
      document.getElementById('searchField').value = '';
   }
}