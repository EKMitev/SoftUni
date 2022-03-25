import { editGame, getById } from '../api/data.js';
import { html } from '../lib.js';

const template = (game, onSubmit) => html`
<!-- Edit Page ( Only for the creator )-->
<section id="edit-page" class="auth">
    <form @submit=${onSubmit} id="edit">
        <div class="container">

            <h1>Edit Game</h1>
            <label for="leg-title">Legendary title:</label>
            <input type="text" id="title" name="title" .value=${game.title} />

            <label for="category">Category:</label>
            <input type="text" id="category" name="category" .value=${game.category}>

            <label for="levels">MaxLevel:</label>
            <input type="number" id="maxLevel" name="maxLevel" min="1" .value=${game.maxLevel}>

            <label for="game-img">Image:</label>
            <input type="text" id="imageUrl" name="imageUrl" .value=${game.imageUrl}>

            <label for="summary">Summary:</label>
            <textarea name="summary" id="summary" .value=${game.summary}></textarea>
            <input class="btn submit" type="submit" value="Edit Game">

        </div>
    </form>
</section>`;

export async function editPage(ctx) {
    ctx.render(template(await getById(ctx.params.id), onSubmit));

    async function onSubmit(event) {
        event.preventDefault();

        const formData = new FormData(document.querySelector('form'));

        const game = {};
        const missing = [...formData.entries()].some(e => e[1] == '');
        if (missing) {
            return alert('All fields are required!')
        }
        [...formData.entries()].map(e => game[e[0]] = e[1]);
        await editGame(ctx.params.id, game);
        ctx.page.redirect('/details/' + ctx.params.id);
    }
}