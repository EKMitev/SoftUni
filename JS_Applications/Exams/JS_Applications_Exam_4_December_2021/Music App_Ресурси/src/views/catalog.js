import { getAll } from '../api/data.js';
import { getUserData } from '../api/sessionHandler.js';
import { html } from '../lib.js';

const template = (albums) => html`
< <!--Catalog-->
    <section id="catalogPage">
        <h1>All Albums</h1>
            ${albums}
    </section>`;

const albumCard = (album, user) => html`
<div class="card-box">
    <img src="${album.imgUrl}">
    <div>
        <div class="text-center">
            <p class="name">Name: ${album.name}</p>
            <p class="artist">Artist: ${album.artist}</p>
            <p class="genre">Genre: ${album.genre}</p>
            <p class="price">Price: $${album.price}</p>
            <p class="date">Release Date: ${album.date}</p>
        </div>

        ${user 
        ?html`
        <div class="btn-group">
            <a href="/details/${album._id}" id="details">Details</a>
        </div>`
        : null}
        
    </div>
</div>`;

export async function catalogPage(ctx) {
    ctx.render(template(await loadAlbums()))
}

async function loadAlbums() {
    const albums = await getAll();
    if (albums.length > 0) {
        return albums.map(g => albumCard(g, getUserData()));
    }

    return html`
    <!--No albums in catalog-->
    <p>No Albums in Catalog!</p>`;
}