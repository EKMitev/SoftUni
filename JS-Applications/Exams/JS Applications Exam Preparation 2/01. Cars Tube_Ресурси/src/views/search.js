import { getSearched } from '../api/data.js';
import { html, render } from '../lib.js';

const template = (cars) => html`
< <!-- Search Page -->
    <section id="search-cars">
        <h1>Filter by year</h1>

        <div class="container">
            <input id="search-input" type="text" name="search" placeholder="Enter desired production year">
            <button @click=${loadCars} class="button-list">Search</button>
        </div>

        <h2>Results:</h2>
        <div id="results" class="listings">

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


export async function searchPage(ctx) {
    ctx.render(template());
}

async function loadCars() {
    const searchedYear = Number(document.getElementById('search-input').value);
    const cars = await getSearched(searchedYear);
    if (cars.length > 0) {
        render(cars.map(g => carCard(g)), document.getElementById('results'));
        return;
    }

    render(html`<!-- Display if there are no matches -->
<p class="no-cars"> No results.</p>`, document.getElementById('results'));
}