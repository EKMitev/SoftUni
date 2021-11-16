import { getById, removeMovie } from "../api/data.js";

const section = document.getElementById('movie-example');
section.remove();

export async function showDetailsPage(ctx, id) {
    const movie = await getById(id);

    const div = section.querySelector('.container');
    div.innerHTML =
        ` <div class="row bg-light text-dark">
    <h1>Movie title: ${movie.title}</h1>

    <div class="col-md-8">
        <img class="img-thumbnail"
            src="${movie.img}" alt="Movie">
    </div>
    <div class="col-md-4 text-center">
        <h3 class="my-3 ">Movie Description</h3>
        <p>${movie.description}</p>
        <a class="btn btn-danger" href="#">Delete</a>
        <a class="btn btn-warning" href="#">Edit</a>
        <a class="btn btn-primary" href="#">Like</a>
        <span class="enrolled-span">Liked 1</span>
    </div>
</div>`;
    const [delBtn, editLink, likeBtn] = [...section.querySelectorAll('a')];

    const userId = JSON.parse(sessionStorage.getItem('userData')).id;
    if (userId == movie._ownerId) {
        delBtn.style.display = 'inline-block';
        editLink.style.display = 'inline-block';
        likeBtn.style.display = 'none';
    } else {
        delBtn.style.display = 'none';
        editLink.style.display = 'none';
        likeBtn.style.display = 'inline-block';
    }

    delBtn.addEventListener('click', async () => {
        await removeMovie(movie._id);
        ctx.goTo('home');
    });

    editLink.addEventListener('click', () => {
       ctx.goTo('edit', movie);
    });

    ctx.showSection(section);
}
