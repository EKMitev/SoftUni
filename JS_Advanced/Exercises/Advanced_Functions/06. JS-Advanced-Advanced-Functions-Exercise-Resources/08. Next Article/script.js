function getArticleGenerator(articles) {
  const arr = articles;
  let count = 0;

  function getArticle() {
    if (count < arr.length) {
      const div = document.getElementById("content");
      const article = document.createElement("article");
      article.textContent += arr[count++];
      div.appendChild(article);
    }
  }

  return getArticle;
}
