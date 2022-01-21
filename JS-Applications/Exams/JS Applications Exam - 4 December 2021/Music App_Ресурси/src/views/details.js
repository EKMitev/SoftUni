import { del, getById } from '../api/data.js';
import { getUserData } from '../api/sessionHandler.js';
import { html, page } from '../lib.js';

const template = (album, user) => html`
<!--Details Page-->
<section id="detailsPage">
    <div class="wrapper">
        <div class="albumCover">
            <img src=${album.imgUrl}>
        </div>
        <div class="albumInfo">
            <div class="albumText">

                <h1>Name: ${album.name}</h1>
                <h3>Artist: ${album.artist}</h3>
                <h4>Genre: ${album.genre}</h4>
                <h4>Price: $${album.price}</h4>
                <h4>Date: ${album.releaseDate}</h4>
                <p>${album.description}</p>
            </div>

            ${user && user.id == album._ownerId 
            ? html`
            <div class="actionBtn">
                <a href="/edit/${album._id}" class="edit">Edit</a>
                <a @click=${() => remove(album._id)} href="javascript:void(0)" class="remove">Delete</a>
            </div>`
            : null}
            
        </div>
    </div>
</section>`;




export async function detailsPage(ctx) {
    ctx.render(template(await getById(ctx.params.id), getUserData()));
}

async function remove(id) {
    if (confirm('Are you sure you want to remove this game?')) {
        await del(id);
        page.redirect('/catalog')
    }
}