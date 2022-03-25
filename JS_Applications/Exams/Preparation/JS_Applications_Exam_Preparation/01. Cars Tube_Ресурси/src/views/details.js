import { del, getById, getComments, postComment } from '../api/data.js';
import { getUserData } from '../api/sessionHandler.js';
import { html, page } from '../lib.js';

const template = (car, user) => html`
<!-- Listing Details Page -->
<section id="listing-details">
    <h1>Details</h1>
    <div class="details-info">
        <img src="${car.imageUrl}">
        <hr>
        <ul class="listing-props">
            <li><span>Brand:</span>${car.brand}</li>
            <li><span>Model:</span>${car.model}</li>
            <li><span>Year:</span>${car.year}</li>
            <li><span>Price:</span>${car.price}$</li>
        </ul>

        <p class="description-para">${car.description}</p>

        ${user && user.id == car._ownerId
            ? html`
            <div class="listings-buttons">
            <a href="/edit/${car._id}" class="button-list">Edit</a>
            <a @click=${() => remove(car._id)} href="javascript:void(0)" class="button-list">Delete</a>
            </div>`
            : null}
        
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