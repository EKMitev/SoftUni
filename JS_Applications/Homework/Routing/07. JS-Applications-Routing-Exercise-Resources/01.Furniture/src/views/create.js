import { createItem } from "../api/data.js";
import { html } from "../lib.js";

const createTemplate = (onSubmit, validator) => html`
 <div class="row space-top">
            <div class="col-md-12">
                <h1>Create New Furniture</h1>
                <p>Please fill all fields.</p>
            </div>
        </div>
        <form @submit=${onSubmit}>
            <div class="row space-top">
                <div class="col-md-4">
                    <div class="form-group">
                        <label class="form-control-label" for="new-make">Make</label>
                        <input class="form-control${validator.make ? ' is-invalid' : ''}" id="new-make" type="text" name="make">
                    </div>
                    <div class="form-group has-success">
                        <label class="form-control-label" for="new-model">Model</label>
                        <input class="form-control${validator.model ? ' is-invalid' : ''}" id="new-model" type="text" name="model">
                    </div>
                    <div class="form-group has-danger">
                        <label class="form-control-label" for="new-year">Year</label>
                        <input class="form-control${validator.year ? ' is-invalid' : ''}" id="new-year" type="number" name="year">
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" for="new-description">Description</label>
                        <input class="form-control${validator.description ? ' is-invalid' : ''}" id="new-description" type="text" name="description">
                    </div>
                </div>
                <div class="col-md-4">
                    <div class="form-group">
                        <label class="form-control-label" for="new-price">Price</label>
                        <input class="form-control${validator.price ? ' is-invalid' : ''}" id="new-price" type="number" name="price">
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" for="new-image">Image</label>
                        <input class="form-control${validator.img ? ' is-invalid' : ''}" id="new-image" type="text" name="img">
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" for="new-material">Material (optional)</label>
                        <input class="form-control" id="new-material" type="text" name="material">
                    </div>
                    <input type="submit" class="btn btn-primary" value="Create" />
                </div>
            </div>
        </form>`

export function createPage(ctx) {
    const validator = {};
    ctx.render(createTemplate(onSubmit, validator));

    async function onSubmit(e){
        e.preventDefault();
        const formData = [...new FormData(document.querySelector('form')).entries()];

        const item = {}
        formData.forEach(arr => item[arr[0]] = arr[1].trim());

        formData.forEach(arr => validator[arr[0]] = false);

        for (let i = 0; i < Object.entries(validator).length; i++) {
            if (Object.entries(item)[i][1] == ''){
                validator[Object.entries(item)[i][0]] = true;
            }                          
        }
        

        if (Object.entries(validator).some(([k, v]) => v == true && k != 'material')) {
            ctx.render(createTemplate(onSubmit, validator));
            return alert('All mandatory fields are required');
        }
        
        const res = await createItem(item);
        ctx.page.redirect('/details/' + res._id);
    }
}
