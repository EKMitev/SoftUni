function toggle() {
   const button = document.querySelector('.button');
   const text = document.getElementById('extra');

   if (button.textContent == 'More'){
       text.style.display = 'block';
       button.textContent = 'Less';
   } else{
    text.style.display = 'none';
    button.textContent = 'More';
   }
}