import { editCar, getById } from '../api/data.js';
import { html } from '../lib.js';

const template = (car, onSubmit) => html`
<!-- Edit Listing Page -->
<section id="edit-listing">
    <div class="container">

        <form @submit=${onSubmit} id="edit-form">
            <h1>Edit Car Listing</h1>
            <p>Please fill in this form to edit an listing.</p>
            <hr>

            <p>Car Brand</p>
            <input type="text" placeholder="Enter Car Brand" name="brand" .value="${car.brand}">

            <p>Car Model</p>
            <input type="text" placeholder="Enter Car Model" name="model" .value="${car.model}">

            <p>Description</p>
            <input type="text" placeholder="Enter Description" name="description" .value="${car.description}">

            <p>Car Year</p>
            <input type="number" placeholder="Enter Car Year" name="year" .value="${car.year}">

            <p>Car Image</p>
            <input type="text" placeholder="Enter Car Image" name="imageUrl" .value="${car.imageUrl}">

            <p>Car Price</p>
            <input type="number" placeholder="Enter Car Price" name="price" .value="${car.price}">

            <hr>
            <input type="submit" class="registerbtn" value="Edit Listing">
        </form>
    </div>
</section>`;

export async function editPage(ctx) {
    ctx.render(template(await getById(ctx.params.id), onSubmit));

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
        await editCar(ctx.params.id, car);
        ctx.page.redirect('/details/' + ctx.params.id);
    }
}