import {post, redirectHome} from './home.js'
import {displayPost} from './post.js'

const divTitles = document.getElementById('titles');
divTitles.addEventListener('click', (event) => {displayPost(event)});

const form = document.querySelector('form');
form.addEventListener('submit', (event) => {post(form, event)});

const homeBtn = document.getElementById('home');
homeBtn.addEventListener('click', ()=>{redirectHome(form, divTitles)})
