class Story {
    #comments;
    #likes;
    constructor(title, creator) {
        this.title = title;
        this.creator = creator;
        this.#comments = [];
        this.#likes = [];
        this.likeCount = 0;
    }

    get likes() {
        if (this.likeCount == 0) {
            return `${this.title} has 0 likes`
        }

        if (this.likeCount == 1) {
            return `${this.#likes[0]} likes this story!`
        }

        return `${this.#likes[0]} and ${this.likeCount - 1} others like this story!`
    }

    like(username) {
        if (this.#likes.some(user => user == username)) {
            throw new Error("You can't like the same story twice!")
        }

        if (username == this.creator) {
            throw new Error("You can't like your own story!")
        }

        this.likeCount++;
        this.#likes.push(username)

        return `${username} liked ${this.title}!`
    }

    dislike(username) {
        if (!this.#likes.some(user => user == username)) {
            throw new Error("You can't dislike this story!")
        }

        this.likeCount--;
        const index = this.#likes.indexOf(username)
        this.#likes.splice(index, 1)

        return `${username} disliked ${this.title}`
    }

    comment(Username, Content, Id) {
        let comment = {
            Id,
            Username,
            Content,
            Replies: []
        };
 
        let commentWithId = this.#comments.find(c => c.Id == Id);
 
        if (commentWithId) {
            let replyID = Number(commentWithId.Id + '.' + (commentWithId.Replies.length + 1));
            let reply = {
                Id: replyID,
                Username,
                Content
            };
 
            commentWithId.Replies.push(reply);
 
            return 'You replied successfully';
        }
 
        comment.Id = this.#comments.length + 1;
        this.#comments.push(comment);
 
        return `${Username} commented on ${this.title}`;
    }
    toString(sortingType) {
        function sorting(c1, c2) {
            if (sortingType == 'asc') {
                return c1.Id - c2.Id
            } else if (sortingType == 'desc') {
                return c2.Id - c1.Id
            }

            return c1.Username.localeCompare(c2.Username)
        }

        this.#comments.sort((c1, c2) => sorting(c1, c2))
            .forEach(c => c.Replies.sort((c1, c2) => sorting(c1, c2)))

        const output = [];
        output.push(`Title: ${this.title}`)
        output.push(`Creator: ${this.creator}`)
        output.push(`Likes: ${this.likeCount}`)
        output.push("Comments:")

        if (this.#comments.length > 0) {
            for (const c of this.#comments) {
                output.push(`-- ${c.Id}. ${c.Username}: ${c.Content}`)

                for (const r of c.Replies) {
                    output.push(`--- ${r.Id}. ${r.Username}: ${r.Content}`)
                }
            }
        }

        return output.join("\n")
    }
}

let art = new Story("My Story", "Anny");

art.comment("Sammy", "Some Content");
console.log(art.comment("Ammy", "New Content"));
art.comment("Zane", "Reply", 1);
art.comment("Jessy", "Nice :)");
console.log(art.comment("SAmmy", "Reply@", 1));
console.log()
console.log(art.toString('username'));
console.log()
art.like("Zane");
console.log(art.toString('desc'));