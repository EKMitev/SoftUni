import { editMeme, getById } from '../api/data.js';
import { html } from '../lib.js';
import { notify } from '../notify.js';

const editTemplate = (onSubmit, meme) => html`
<!-- Edit Meme Page ( Only for logged user and creator to this meme )-->
<section id="edit-meme">
    <form @submit=${onSubmit} id="edit-form">
        <h1>Edit Meme</h1>
        <div class="container">
            <label for="title">Title</label>
            <input id="title" type="text" placeholder="Enter Title" name="title" .value="${meme.title}">
            <label for="description">Description</label>
            <textarea id="description" placeholder="Enter Description" name="description"
                .value="${meme.description}"></textarea>
            <label for="imageUrl">Image Url</label>
            <input id="imageUrl" type="text" placeholder="Enter Meme ImageUrl" name="imageUrl"
                .value="${meme.imageUrl}">
            <input type="submit" class="registerbtn button" value="Edit Meme">
        </div>
    </form>
</section>`;

export async function editPage(ctx) {
    ctx.render(editTemplate(onSubmit, await load()))

    async function load() {
        return await getById(ctx.params.id)
    }

    async function onSubmit(event){
        event.preventDefault();

        const formData = new FormData(document.querySelector('form'));
        const missing = [...formData.entries()]
            .some(x => x[1] == '');

        if (missing) {
            return notify('All fields are required!');
        }

        const meme = {};
        [...formData.entries()].forEach(x => meme[x[0]] = x[1]);

        await editMeme(ctx.params.id, meme);
        ctx.page.redirect('/details/' + ctx.params.id);
    }
}