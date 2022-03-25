import { getMemes, getMyMemes } from '../api/data.js';
import { getUserData } from '../api/util.js';
import { html } from '../lib.js';

const myMemesTemplate = (user, memes) => html`
<!-- Profile Page ( Only for logged users ) -->
<section id="user-profile-page" class="user-profile">
    <article class="user-info">
        <img id="user-avatar-url" alt="user-profile" src="/images/${user.gender}.png">
        <div class="user-content">
            <p>Username: ${user.username}</p>
            <p>Email: ${user.email}</p>
            <p>My memes count: ${memes.length || 0}</p>
        </div>
    </article>
    <h1 id="user-listings-title">User Memes</h1>
    <div class="user-meme-listings">
        <!-- Display : All created memes by this user (If any) -->
        ${html`${memes}`}
    </div>
</section>`;

const memeCard = (meme) => html`
<div class="user-meme">
    <p class="user-meme-title">${meme.title}</p>
    <img class="userProfileImage" alt="meme-img" src="${meme.imageUrl}">
    <a class="button" href="/details/${meme._id}">Details</a>
</div>`;

export async function myPage(ctx) {
    const user = getUserData();
    ctx.render(myMemesTemplate(user, await load(user)));
}

async function load(user) {
    const memeArr = await getMyMemes(user.id);
    if (memeArr.length > 0) {
        return memeArr.map(m => memeCard(m));
    }

    return html`
<!-- Display : If user doesn't have own memes  -->
<p class="no-memes">No memes in database.</p>`;
}