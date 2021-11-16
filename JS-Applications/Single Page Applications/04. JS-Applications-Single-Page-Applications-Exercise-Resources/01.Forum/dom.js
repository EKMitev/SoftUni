export function attachPost(post, div) {

    const title =
        `<div class="topic-container">
          <div class="topic-name-wrapper">
            <div class="topic-name">
                <a href="#" class="normal" data-id=${post._id}>
                    <h2>${post.title}</h2>
                </a>
                <div class="columns">
                    <div>
                        <p>Date: <time>${post.date}</time></p>
                        <div class="nick-name">
                            <p>Username: <span>${post.username}</span></p>
                        </div>
                    </div>
                </div>
            </div>
         </div>
        </div>`;

    div.innerHTML += title;
}

export function attachComment(post, div) {
    const title =
        `<div class="topic-name-wrapper">
            <div class="topic-name">
                <p><strong>${post.username}</strong> commented on <time>${post.date}</time></p>
                <div class="post-content">
                    <p>${post.content}</p>
                </div>
            </div>
        </div>`;

    div.innerHTML += title;
}