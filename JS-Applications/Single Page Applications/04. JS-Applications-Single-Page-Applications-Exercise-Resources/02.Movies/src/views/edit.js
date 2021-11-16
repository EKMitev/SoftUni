import { editMovie } from "../api/data.js";

const section = document.getElementById('edit-movie');
section.remove();

export async function showEditPage(ctx, movie) {

    const form = section.querySelector('form');


    form.addEventListener('submit', async (event) => {
        event.preventDefault();
        const formData = new FormData(form);

        const title = formData.get('title');
        const description = formData.get('description');
        const img = formData.get('imageUrl');

        if (title == '' || description == '' || img == '') {
            return alert('All fields are required!');
        }
        
        const m = {
            title,
            description,
            img,
        }
        form.reset();
        await editMovie(movie._id, m)
        ctx.goTo('home');
    })

    ctx.showSection(section);
}