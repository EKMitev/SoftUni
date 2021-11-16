import { attachComment } from './dom.js';

export async function displayPost(event) {
    if (event.target.tagName != 'H2' && event.target.tagName != 'A') {
        return;
    }

    let target = null;
    if (event.target.tagName == 'H2') {
        target = event.target.parentElement;
    }

    const res = await fetch('http://localhost:3030/jsonstore/collections/myboard/posts/' + target.dataset.id);
    const post = await res.json();

    document.querySelector('main').innerHTML =
        `<div class="comment">
    <div class="header">
        <img src="./static/profile.png" alt="avatar">
        <p><span>${post.username}</span> posted on <time>${post.date}</time></p>

        <p class="post-content">${post.content}</p>
    </div>
</div>
<div id="user-comment"></div>`
        +
        ` <form id="comment">
<div class="new-topic-title">
    <label for="username">Username <span class="red">*</span></label>
    <input type="text" name="username" id="username">
</div>
<div class="new-topic-content">
    <label for="postText">Post <span class="red">*</span></label>
    <textarea type="text" name="postText" id="postText" rows="8" class="height"></textarea>
</div>
<div class="new-topic-buttons">
    <button class="public">Post</button>
</div>

</form>`;


    const form = document.getElementById('comment');
    form.addEventListener('submit', (e) => { comment(e, form, target.dataset.id) })

    const response = await fetch('http://localhost:3030/jsonstore/collections/myboard/comments');
    const comments = await response.json();

    Object.values(comments)
        .filter(c => c.postId == target.dataset.id)
        .forEach(c => attachComment(c, document.querySelector('.comment')))
};



async function comment(event, form, id) {
    event.preventDefault();
    
    const formData = new FormData(form);
    const currDate = new Date();


    const username = formData.get('username');
    const content = formData.get('postText');
    const date =
        `${currDate.getFullYear()}-${currDate.getMonth()}-${currDate.getDate()} ${currDate.getHours()}:${currDate.getMinutes()}:${currDate.getSeconds()}`;

    if (!username || !content || username === '' || content === '') {
        alert('Please fill all fields!');
        return;
    }

    const post = {
        username,
        content,
        date,
        postId: id
    }

    form.reset();

    const res = await fetch('http://localhost:3030/jsonstore/collections/myboard/comments',
        {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(post)
        });
    const data = await res.json();
    attachComment(data, document.querySelector('.comment'));

}