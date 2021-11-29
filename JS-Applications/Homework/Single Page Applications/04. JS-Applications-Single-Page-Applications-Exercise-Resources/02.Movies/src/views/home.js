import { getAllMovies } from "../api/data.js";

const section = document.getElementById('home');
section.remove();

const addSec = section.querySelector('#add-movie-button')

let ctx = null;

export async function showHomePage(ctxTarget) {
    const userData = JSON.parse(sessionStorage.getItem('userData'));
    if (userData) {
        addSec.style.display = 'block';
    } else {
        addSec.style.display = 'none';
    }

    ctx = ctxTarget;
    ctx.showSection(section);
    await loadMovies();
}


section.querySelector('a').addEventListener('click', (event) => {
    event.preventDefault();
    ctx.goTo('add');
})

async function loadMovies() {
    const div = document.querySelector('.card-deck.d-flex.justify-content-center');
    div.replaceChildren('loading...')
    const movies = await getAllMovies();

    div.replaceChildren();

    movies.forEach(m => div.innerHTML +=
        `
        <div class="card mb-4">
        <img class="card-img-top" src="${m.img}"
                alt="Card image cap" width="400">
        <div class="card-body">
            <h4 class="card-title">${m.title}</h4>
        </div>
        <div class="card-footer">
            <a class="link" href="#/details/6lOxMFSMkML09wux6sAF">
                <button data-id="${m._id}" type="button" class="btn btn-info">Details</button>
            </a>
        </div>
    
    </div>
        `);

        const [...links] = div.querySelectorAll('.link');

        const userData = JSON.parse(sessionStorage.getItem('userData'));
        if (userData) {
            links.forEach(l => (l.style.display = 'block'));
        } else {
            links.forEach(l => (l.style.display = 'none'));
        }    

    div.addEventListener('click', showDetails)
}

async function showDetails(ev) {
    if (ev.target.tagName == 'BUTTON') {
        ctx.goTo('details', ev.target.dataset.id)
    }
}