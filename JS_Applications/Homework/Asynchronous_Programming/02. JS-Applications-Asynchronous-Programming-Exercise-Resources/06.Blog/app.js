function attachEvents() {
    const posts = document.getElementById('posts');

    const loadBtn = document.getElementById('btnLoadPosts');
    loadBtn.addEventListener('click', load);

    const viewBtn = document.getElementById('btnViewPost');
    viewBtn.addEventListener('click', view);

    async function load() {
        posts.innerHTML = `<option>loading...</option>`;
        const res = await fetch('http://localhost:3030/jsonstore/blog/posts')
        const data = await res.json();

        posts.replaceChildren();
        Object.entries(data).forEach(entry => {
            const option = `<option value="${entry[0]}">${entry[1].title}</option>`;
            posts.innerHTML += option
        })
    }

    async function view() {
        const ulCom = document.getElementById('post-comments');
        ulCom.replaceChildren();

        const ulPost = document.getElementById('post-body');
        ulPost.replaceChildren();

        const h1 = document.getElementById('post-title');
        h1.innerText = 'Loading...'

        const id = posts.selectedOptions[0].value;

        const [resPost, resComments] = await Promise.all([
            fetch(`http://localhost:3030/jsonstore/blog/posts/${id}`),
            fetch('http://localhost:3030/jsonstore/blog/comments')
        ]);

        const post = await resPost.json();
        const comments = await resComments.json();

        h1.innerText = `${post.title}`;
        ulPost.innerText = post.body;


        Object.entries(comments)
            .filter(e => e[1].postId == id)
            .forEach(e => {
                const li = `<li>${e[1].text}</li>`
                ulCom.innerHTML += li;
                return;
            });

    }
}

attachEvents();