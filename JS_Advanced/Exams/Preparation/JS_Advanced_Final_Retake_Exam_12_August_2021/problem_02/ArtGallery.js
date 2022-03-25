class ArtGallery {
    constructor(creator) {
        this.creator = creator;
        this.possibleArticles = { "picture": 200, "photo": 50, "item": 250 };
        this.listOfArticles = [];
        this.guests = [];
    }

    addArticle(articleModel, articleName, quantity) {
        articleModel = articleModel.toLowerCase();
        if (!this.possibleArticles.hasOwnProperty(articleModel)) {
            throw new Error("This article model is not included in this gallery!");
        }

        const article = {
            articleModel,
            articleName,
            quantity,
        }

        const existing = this.listOfArticles.find(a => a.articleName == article.articleName);

        if (existing != undefined && existing.articleModel == article.articleModel) {
            existing.quantity += article.quantity
        } else {
            this.listOfArticles.push(article)
        }

        return `Successfully added article ${articleName} with a new quantity- ${quantity}.`
    }

    inviteGuest(guestName, personality) {
        if (this.guests.some(g => g.guestName == guestName)) {
            throw new Error(`${guestName} has already been invited.`)
        }

        const newGuest = {
            guestName,
            personality,
            points: 0,
            purchaseArticle: 0
        }

        switch (newGuest.personality) {
            case 'Vip':
                newGuest.points = 500;
                break;
            case 'Middle':
                newGuest.points = 250;
                break;
            default:
                newGuest.points = 50;
        }

        this.guests.push(newGuest);

        return `You have successfully invited ${guestName}!`
    }

    buyArticle(articleModel, articleName, guestName) {
        const article = this.listOfArticles.find(a => a.articleName == articleName && a.articleModel == articleModel.toLowerCase());

        if (!article) {
            throw new Error("This article is not found.");
        }

        if (article.quantity == 0) {
            return `The ${articleName} is not available.`
        }

        const guest = this.guests.find(g => g.guestName == guestName);

        if (!guest) {
            return "This guest is not invited.";
        }

        const pointsNeeded = this.possibleArticles[articleModel]

        if (guest.points - pointsNeeded < 0) {
            return "You need to more points to purchase the article.";
        }

        guest.points -= pointsNeeded;
        guest.purchaseArticle++;
        article.quantity--;

        return `${guestName} successfully purchased the article worth ${pointsNeeded} points.`
    }

    showGalleryInfo(criteria) {
        const out = [];

        if (criteria == 'article') {
            out.push('Articles information:');
            this.listOfArticles.forEach(article => { out.push(`${article.articleModel} - ${article.articleName} - ${article.quantity}`) });
        } else if (criteria == 'guest') {
            out.push('Guests information:');
            this.guests.forEach(guest => out.push(`${guest.guestName} - ${guest.purchaseArticle}`))
        }

        return out.join('\n');
    }
}


const artGallery = new ArtGallery('Curtis Mayfield'); 
artGallery.addArticle('picture', 'Mona Liza', 3);
artGallery.addArticle('Item', 'Ancient vase', 2);
artGallery.addArticle('picture', 'Mona Liza', 1);
artGallery.inviteGuest('John', 'Vip');
artGallery.inviteGuest('Peter', 'Middle');
artGallery.buyArticle('picture', 'Mona Liza', 'John');
artGallery.buyArticle('item', 'Ancient vase', 'Peter');
console.log(artGallery.showGalleryInfo('article'));
console.log(artGallery.showGalleryInfo('guest'));