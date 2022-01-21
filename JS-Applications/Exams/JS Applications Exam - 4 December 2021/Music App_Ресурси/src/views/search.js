import { getSearched } from '../api/data.js';
import { getUserData } from '../api/sessionHandler.js';
import { html, render } from '../lib.js';

const template = () => html`
<!--Search Page-->
<section id="searchPage">
    <h1>Search by Name</h1>

    <div class="search">
        <input id="search-input" type="text" name="search" placeholder="Enter desired albums's name">
        <button @click=${loadAlbums} class="button-list">Search</button>
    </div>

    <h2>Results:</h2>

    <!--Show after click Search button-->
    <div id="results" class="search-result">
       
    </div>
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
            <p class="date">Release Date: ${album.releaseDate}</p>
        </div>
        ${user 
        ?html`
        <div class="btn-group">
            <a href="/details/${album._id}" id="details">Details</a>
        </div>`
        : null}
    </div>
</div>`;


export async function searchPage(ctx) {
    ctx.render(template());
}

async function loadAlbums() {
    const searched = document.getElementById('search-input').value;
    const albums = await getSearched(searched);
    if (albums.length > 0) {
        render(albums.map(g => albumCard(g, getUserData())), document.getElementById('results'));
        return;
    }

    render(html`<!--If there are no matches-->
<p class="no-result">No result.</p>`, document.getElementById('results'));
}