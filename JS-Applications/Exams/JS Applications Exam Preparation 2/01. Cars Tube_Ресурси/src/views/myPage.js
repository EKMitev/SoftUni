import { getMyItems } from '../api/data.js';
import { html } from '../lib.js';

const template = (cars) => html`
<!-- My Listings Page -->
<section id="my-listings">
    <h1>My car listings</h1>
    <div class="listings">
        ${cars}
    </div>
</section>`;

const carCard = (car) => html`
<!-- Display all records -->
<div class="listing">
    <div class="preview">
        <img src="${car.imageUrl}" />
    </div>
    <h2>${car.brand} ${car.model}</h2>
    <div class="info">
        <div class="data-info">
            <h3>Year: ${car.year}</h3>
            <h3>Price: ${car.price} $</h3>
        </div>
        <div class="data-buttons">
            <a href="/details/${car._id}" class="button-carDetails">Details</a>
        </div>
    </div>
</div>`;


export async function myPage(ctx) {
    ctx.render(template(await loadCars()));
}

async function loadCars() {
    const cars = await getMyItems();
    if (cars.length > 0) {
        return cars.map(g => carCard(g));
    }

    return html`<!-- Display if there are no records -->
<p class="no-cars"> You haven't listed any cars yet.</p>`;
}