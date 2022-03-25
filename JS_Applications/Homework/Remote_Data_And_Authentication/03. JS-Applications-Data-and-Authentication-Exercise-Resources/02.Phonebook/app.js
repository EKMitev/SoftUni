function attachEvents() {
    const createBtn = document.getElementById('btnCreate');
    createBtn.addEventListener('click', async () => {
        const person = document.getElementById('person');
        const phone = document.getElementById('phone');

        const contact = {
            person: person.value,
            phone: phone.value
        }

        person.value = '';
        phone.value = '';

        await createContact(contact);
       
    })

    async function createContact(contact) {
        try {
            if (!contact || contact.person == '' || contact.phone == '') {
                throw new Error('All fields are required');
            }

            const res = await fetch('http://localhost:3030/jsonstore/phonebook',
                {
                    method: 'post',
                    headers: { 'Content-Type': 'application/json' },
                    body: JSON.stringify(contact)
                });
                
            return res.json();
        } catch (error) {
            alert(error.message);
        }
    }

    const loadBtn = document.getElementById('btnLoad');

    const ul = document.getElementById('phonebook');
    ul.addEventListener('click', async (ev) => {
        if (ev.target.tagName == 'BUTTON') {
            const id = ev.target.dataset.id;
            await fetch('http://localhost:3030/jsonstore/phonebook/' + id, { method: 'delete' })
            ev.target.parentElement.remove();
        }
    })

    loadBtn.addEventListener('click', async () => {
        ul.replaceChildren();

        const res = await loadContacts();

        Object.values(res)
            .forEach(c => ul.innerHTML += `<li>${c.person}: ${c.phone}<button data-id=${c._id}>Delete</button></li>`);

    })

    async function loadContacts() {
        const res = await fetch('http://localhost:3030/jsonstore/phonebook');
        return await res.json();
    }
}

attachEvents();