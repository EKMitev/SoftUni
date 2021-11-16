import { addMovie } from "../api/data.js";

const section = document.getElementById('add-movie');
section.remove();
let ctx = null;

export async function showAddPage(ctxTarget) {
    ctx = ctxTarget;
    ctx.showSection(section);
}

const form = section.querySelector('form');
form.addEventListener('submit', createMovie)

async function createMovie(event) {
    event.preventDefault();

    const formData = new FormData(form);

    const title = formData.get('title');
    const description = formData.get('description');
    const img = formData.get('imageUrl');
    const ownerId = JSON.parse(sessionStorage.getItem('userData')).id;

    if (title == '' || description == '' || img == '') {
        return alert('All fields are required!');
    }


    const movie = {
        title,
        description,
        img,
        _ownerId: ownerId
    }

    form.reset();
    await addMovie(movie);
    ctx.goTo('home');
}