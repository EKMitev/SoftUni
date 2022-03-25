import { createCar } from '../api/data.js';
import { html } from '../lib.js';

const template = (onSubmit) => html`
<!-- Create Listing Page -->
<section id="create-listing">
    <div class="container">
        <form @submit=${onSubmit}id="create-form">
            <h1>Create Car Listing</h1>
            <p>Please fill in this form to create an listing.</p>
            <hr>

            <p>Car Brand</p>
            <input type="text" placeholder="Enter Car Brand" name="brand">

            <p>Car Model</p>
            <input type="text" placeholder="Enter Car Model" name="model">

            <p>Description</p>
            <input type="text" placeholder="Enter Description" name="description">

            <p>Car Year</p>
            <input type="number" placeholder="Enter Car Year" name="year">

            <p>Car Image</p>
            <input type="text" placeholder="Enter Car Image" name="imageUrl">

            <p>Car Price</p>
            <input type="number" placeholder="Enter Car Price" name="price">

            <hr>
            <input type="submit" class="registerbtn" value="Create Listing">
        </form>
    </div>
</section>`;

export async function createPage(ctx) {
    ctx.render(template(onSubmit));

    async function onSubmit(event) {
        event.preventDefault();

        const formData = new FormData(document.querySelector('form'));

        const missing = [...formData.entries()].some(e => e[1] == '');
        if (missing) {
            return alert('All fields are required!')
        }

        const price = Number(formData.get('price'));
        const year = Number(formData.get('year'));
        if (!price || !year || (price < 0 || year < 0)) {
            return alert('Price and Year must be positive numbers!');
        }

        const car = {};
        [...formData.entries()].map(e => {
            if (e[0] == 'price' || e[0] == 'year') {
                car[e[0]] = Number(e[1]);
            } else {
                car[e[0]] = e[1];
            }
        });
        await createCar(car);
        ctx.page.redirect('/catalog');
    }
}