import { getAll } from '../api/data.js';
import { html } from '../lib.js';

const template = (games) => html`
<!-- Catalogue -->
<section id="catalog-page">
    <h1>All Games</h1>
    ${games};
</section>`;

const gameCard = (game) => html`
<div class="allGames">
    <div class="allGames-info">
        <img src="${game.imageUrl}">
        <h6>${game.category}</h6>
        <h2>${game.title}</h2>
        <a href="/details/${game._id}" class="details-button">Details</a>
    </div>
</div>`;

export async function cataloguePage(ctx) {
    ctx.render(template(await loadGames()))
}

async function loadGames() {
    const games = await getAll();
    if (games.length > 0) {
        return games.map(g => gameCard(g));
    }

    return html`<h3 class="no-articles">No articles yet</h3>`;
}