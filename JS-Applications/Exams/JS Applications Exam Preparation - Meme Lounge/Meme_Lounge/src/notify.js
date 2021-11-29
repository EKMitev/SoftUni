const el = document.getElementById('errorBox');
const output = el.querySelector('span');

export function notify(message) {
    output.textContent = message;
    el.style.display = 'block';

    setTimeout(() => el.style.display = 'none', 3000);
}