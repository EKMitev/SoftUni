function attachEvents() {
    const submitBTN = document.getElementById('submit');
    const refreshBTN = document.getElementById('refresh');

    submitBTN.addEventListener('click', sendMessage);

    async function sendMessage() {
        const author = document.querySelector('[name="author"]');
        const content = document.querySelector('[name="content"]');

        const message = {
            author: author.value,
            content: content.value
        }

        try {
            await send(message);
        } catch (e) {
            alert(e.message);
        }

        author.value = '';
        content.value = '';
    }

    async function send(message) {
        if (!message || message.author == '' || message.content == ''){
            throw new Error('All fields are required!')
        }

        const res = await fetch('http://localhost:3030/jsonstore/messenger',
            {
                method: 'post',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify(message)
            })

        return await res.json();
    }

    refreshBTN.addEventListener('click', refresh);

    async function refresh() {
        const textarea = document.getElementById('messages');
        textarea.replaceChildren();
        const res = await fetch('http://localhost:3030/jsonstore/messenger');
        const data = await res.json();

        let str = '';
        Object.values(data)
        .forEach(c => str += c.content + '\n');

        textarea.value = str;
    }
}

attachEvents();