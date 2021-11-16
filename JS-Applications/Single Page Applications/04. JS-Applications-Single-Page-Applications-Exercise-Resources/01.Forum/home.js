import { attachPost } from './dom.js';

let flag = false;

const btn = document.querySelector('.cancel');
btn.addEventListener('click', (e) => {
    e.preventDefault();
    document.querySelector('form').reset();
    flag = true;
})

export async function post(form, event) {
    event.preventDefault();

    if (flag) {
        return
    }

    const formData = new FormData(form);
    const currDate = new Date();


    const title = formData.get('topicName');
    const username = formData.get('username');
    const content = formData.get('postText');
    const date =
        `${currDate.getFullYear()}-${currDate.getMonth()}-${currDate.getDate()} ${currDate.getHours()}:${currDate.getMinutes()}:${currDate.getSeconds()}`;

    if (!title || !username || !content || title === '' || username === '' || content === '') {
        alert('Please fill all fields!');
        return;
    }

    const post = {
        title,
        username,
        content,
        date
    }

    form.reset();

    const res = await fetch('http://localhost:3030/jsonstore/collections/myboard/posts',
        {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(post)
        });
    const data = await res.json();
    attachPost(data, document.querySelector('.topic-container'));

};


export async function redirectHome(form, divTitles) {
   
    const main = document.querySelector('main');
    main.replaceChildren();

    main.innerHTML =
     ` <div id="main" class="new-topic-border">
            <div class="header-background">
                <span>New Topic</span>
            </div>
       </div>
       <div id="titles" class="topic-title">

                <!-- topic component  -->
                <div class="topic-container">

                </div>`;

    document.querySelector('#main').appendChild(form)
    const titles = document.querySelector('#titles');
    titles.appendChild(divTitles)
    divTitles.replaceChildren();
    
    const res = await fetch('http://localhost:3030/jsonstore/collections/myboard/posts');
    const data = await res.json();
   
    Object.values(data).forEach(p => attachPost(p, divTitles))
}