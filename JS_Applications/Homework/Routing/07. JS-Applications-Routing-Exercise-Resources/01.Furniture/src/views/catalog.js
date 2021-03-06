import { html, until } from "../lib.js";
import { getAllItems } from '../api/data.js';

const catalogTemplate = (promise) => html`
<div class="row space-top">
    <div class="col-md-12">
        <h1>Welcome to Furniture System</h1>
        <p>Select furniture from the catalog to view details.</p>
    </div>
</div>
<div class="row space-top">
  ${until(promise, html`<p>Loading &hellip;</p>`)}
</div>`;

const itemTemplate = (item) => html`
<div class="col-md-4">
    <div class="card text-white bg-primary">
        <div class="card-body">
            <img src="${item.img}" />
            <p>Description here</p>
            <footer>
                <p>Price: <span>${item.price} $</span></p>
            </footer>
            <div>
                <a href="/details/${item._id}" class="btn btn-info">Details</a>
            </div>
        </div>
    </div>
</div>`

async function load(){
    const res = await getAllItems()
    
    return res.map(item => itemTemplate(item));
}

export function homePage(ctx) {
    ctx.render(catalogTemplate(load()));
}
