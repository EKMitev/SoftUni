import { getMemes } from '../api/data.js';
import { html } from '../lib.js';

const catalogTemplate = (promise) => html`
<!-- All Memes Page ( for Guests and Users )-->
<section id="meme-feed">
    <h1>All Memes</h1>
    <div id="memes">
        ${html`${promise}`}
    </div>
</section>`;

const memeCard = (meme) => html`
<!-- Display : All memes in database ( If any ) -->
<div class="meme">
    <div class="card">
        <div class="info">
            <p class="meme-title">${meme.title}</p>
            <img class="meme-image" alt="meme-img" src="${meme.imageUrl}">
        </div>
        <div id="data-buttons">
            <a class="button" href="/details/${meme._id}">Details</a>
        </div>
    </div>
</div>`;

export async function catalogPage(ctx) {
    ctx.render(catalogTemplate(await load()));
}

async function load() {
    const memeArr = await getMemes();
    if (memeArr.length > 0) {
        return memeArr.map(m => memeCard(m));
    }

    return html`
    <!-- Display : If there are no memes in database -->
    <p class="no-memes">No memes in database.</p>`;
}