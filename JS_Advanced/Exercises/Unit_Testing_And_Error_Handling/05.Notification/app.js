function notify(message) {
  const button = document.querySelector('button');
  const notification = document.querySelector('#notification');
  notification.innerHTML = message;
  notification.style.display = 'block';

  notification.addEventListener('click', () =>{
    notification.style.display = 'none';
  })
}