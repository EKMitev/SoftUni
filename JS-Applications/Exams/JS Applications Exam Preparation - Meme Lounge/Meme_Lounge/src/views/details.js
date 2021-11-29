import { deleteMeme, getById } from '../api/data.js';
import { getUserData } from '../api/util.js';
import { html } from '../lib.js';

const detailsTemplate = (removeMeme, meme) => html`
<!-- Details Meme Page (for guests and logged users) -->
<section id="meme-details">
    <h1>Meme Title: ${meme.meme.title}</h1>

    </h1>
    <div class="meme-details">
        <div class="meme-img">
            <img alt="meme-alt" src="${meme.meme.imageUrl}">
        </div>
        <div class="meme-description">
            <h2>Meme Description</h2>
            <p>${meme.meme.description}</p>

            ${meme.isOwner 
            ? html`
                <!-- Buttons Edit/Delete should be displayed only for creator of this meme  -->
                <a class="button warning" href="/edit/${meme.meme._id}">Edit</a>
                <button @click=${removeMeme} class="button danger">Delete</button>`
            : null} 

        </div>
    </div>
</section>`;

export async function detailsPage(ctx) {
    ctx.render(detailsTemplate(removeMeme, await load()));

    async function load() {        
        const data = await getById(ctx.params.id)

        const meme = {
            meme: data
        };

        const user = getUserData();
        if (user && user.id == data._ownerId) {
            meme.isOwner = true;
        } else {
            meme.isOwner = false;
        }

        return meme;
    }

    async function removeMeme(){
        if (confirm('Are you sure?') == true){
            await deleteMeme(ctx.params.id);
            ctx.page.redirect('/catalog');
        }
    }
}