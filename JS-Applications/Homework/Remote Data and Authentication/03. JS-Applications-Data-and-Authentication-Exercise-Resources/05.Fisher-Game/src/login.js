const form = document.getElementById('loginForm');
form.addEventListener('submit', async (event) => {
    event.preventDefault();

    const res = await fetch('http:localhost:3030/users/login');
    const data = await res.json();
    console.log(data);
})