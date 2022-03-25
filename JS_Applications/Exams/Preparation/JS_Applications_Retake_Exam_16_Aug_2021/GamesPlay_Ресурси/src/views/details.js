import { del, getById, getComments, postComment } from '../api/data.js';
import { getUserData } from '../api/sessionHandler.js';
import { html, page } from '../lib.js';

const template = (game, user, comments, form) => html`
<!--Details Page-->
<section id="game-details">
    <h1>Game Details</h1>
    <div class="info-section">

        <div class="game-header">
            <img class="game-img" src="${game.imageUrl}" />
            <h1>${game.title}</h1>
            <span class="levels">MaxLevel: ${game.maxLevel}</span>
            <p class="type">${game.category}</p>
        </div>

        <p class="text">${game.summary}</p>

        <!-- Bonus ( for Guests and Users ) -->
        <div class="details-comments">
            <h2>Comments:</h2>
            ${comments}
        </div>


        ${user && user.id == game._ownerId
            ? html`
        <!-- Edit/Delete buttons ( Only for creator of this game )  -->
        <div class="buttons">
            <a href="/edit/${game._id}" class="button">Edit</a>
            <a @click=${() => remove(game._id)} href="javascript:void(0)" class="button">Delete</a>
        </div>`
            : null}
    </div>

            ${user && user.id != game._ownerId ? form : null};
</section>`;

const commentForm = (onSubmit) => html`
<!-- Bonus -->
<!-- Add Comment ( Only for logged-in users, which is not creators of the current game ) -->
<article class="create-comment">
    <label>Add new comment:</label>
    <form @submit=${onSubmit} class="form">
        <textarea name="comment" placeholder="Comment......"></textarea>
        <input class="btn submit" type="submit" value="Add Comment">
    </form>
</article>`;

const commentTemplate = (comments) => html`
<!-- list all comments for current game (If any) -->
<ul>
   ${comments.map(c => html`<li class="comment"><p>${c.comment}</p></li>`)} 
</ul>`;

export async function detailsPage(ctx) {
    ctx.render(template(await getById(ctx.params.id), getUserData(), await loadComments(), commentForm(onSubmit)));

    async function loadComments() {
        const comments = await getComments(ctx.params.id);
        if (comments.length > 0) {
            return commentTemplate(comments);
        }

        return html`<p class="no-comment">No comments.</p>`;
    }

    async function onSubmit(event) {
        event.preventDefault();
        const formData = new FormData(document.querySelector('form'));
        const content = formData.get('comment')
        if (content == '') {
            return alert('Please enter a valid comment!');
        }

       await postComment({gameId: ctx.params.id, comment: content});
       document.querySelector('form').reset();
       ctx.page.redirect('/details/' + ctx.params.id);
    }
}

async function remove(id) {
    if (confirm('Are you sure you want to remove this game?')) {
        await del(id);
        page.redirect('/')
    }
}