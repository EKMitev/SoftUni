window.addEventListener('DOMContentLoaded', load);

const loadBtn = document.getElementById('loadBooks');
const table = document.querySelector('tbody');
const formCreate = document.getElementById('create');
const formEdit = document.getElementById('edit');

async function load(){
    table.innerHTML = 'Loading...';

    const books = await loadAllBooks()
    table.innerHTML = '';
    Object.entries(books)
        .forEach(b => table.innerHTML += `<tr data-id="${b[0]}">
    <td>${b[1].title}</td>
    <td>${b[1].author}</td>
    <td>
        <button>Edit</button>
        <button>Delete</button>
    </td>
</tr>`);
}
loadBtn.addEventListener('click', load);

table.addEventListener('click', async (ev) => {
    if (ev.target.tagName != 'BUTTON') {
        return;
    }

    if (ev.target.innerText == 'Edit') {
        formCreate.style.display = 'none';
        formEdit.style.display = 'block';

        const id = ev.target.parentElement.parentElement.dataset.id;

        formEdit.dataset.id = id;

        const [author, title] = ev.target.parentElement.parentElement.children;

        formEdit.children[2].value = author.innerText;
        formEdit.children[4].value = title.innerText;
    } else if (ev.target.innerText == 'Delete') {
        ev.target.parentElement.parentElement.remove();
        await deleteBook(id);
    }
});

formCreate.addEventListener('submit', async (e) => {
    e.preventDefault();
    const [title, author] = formCreate.querySelectorAll('input');
    
    try {
        await createBook(author.value, title.value);
        formCreate.reset();

    } catch (err) {
        alert(err.message);
    }
});

formEdit.addEventListener('submit', async (ev) => {
    ev.preventDefault();
    const id = formEdit.dataset.id;
    const [title, author] = formEdit.querySelectorAll('input');
    try{
        await updateBook(author.value, title.value, id)
        await load();
        formCreate.style.display = 'block';
        formEdit.style.display = 'none';
    }catch(err){
        alert(err.message);
    }
});

async function loadAllBooks() {
    const res = await fetch('http://localhost:3030/jsonstore/collections/books');
    return await res.json();
}

async function createBook(author, title) {
    if (!author || !title || author == '' || title == '') {
        throw new Error('All fields are required');
    }

    const book = {
        author,
        title
    }

    const res = await fetch('http://localhost:3030/jsonstore/collections/books',
        {
            method: 'post',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(book)
        });

    return await res.json();
}

async function updateBook(author, title, id) {
    if (!author || !title || author == '' || title == '') {
        throw new Error('All fields are required');
    }

    const book = {
        author,
        title
    }

    const res = await fetch('http://localhost:3030/jsonstore/collections/books/' + id,
        {
            method: 'put',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(book)
        });

    return await res.json();
}

async function deleteBook(id) {
    const res = await fetch('http://localhost:3030/jsonstore/collections/books/' + id, { method: 'delete' });
    return await res.json();
}