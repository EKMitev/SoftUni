import { getRecent } from '../api/data.js';
import { html } from '../lib.js';

const template = (games) => html`
<!--Home Page-->
<section id="welcome-world">

    <div class="welcome-message">
        <h2>ALL new games are</h2>
        <h3>Only in GamesPlay</h3>
    </div>
    <img src="./images/four_slider_img01.png" alt="hero">

    <div id="home-page">
        <h1>Latest Games</h1>
        ${games};
    </div>
</section>`;

const gameCard = (game) => html`
<div class="game">
    <div class="image-wrap">
        <img src="${game.imageUrl}">
    </div>
    <h3>${game.title}</h3>
    <div class="rating">
        <span>☆</span><span>☆</span><span>☆</span><span>☆</span><span>☆</span>
    </div>
    <div class="data-buttons">
        <a href="/details/${game._id}" class="btn details-btn">Details</a>
    </div>
</div>`;

export async function homePage(ctx){
    ctx.render(template(await loadGames()))
}

async function loadGames(){
    const games = await getRecent();
    if (games.length > 0) {
        return games.map(g => gameCard(g));
    }

    return html`<p class="no-articles">No games yet</p>`;
}