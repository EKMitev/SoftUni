function lockedProfile() {
  const main = document.getElementById("main");
  main.addEventListener("click", onClick);

  function onClick(ev) {
    if (ev.target.tagName == "BUTTON") {
      const profile = ev.target.parentElement;
      const radio = profile.querySelector(
        'input[type="radio"][value = "lock"]'
      ).checked;
      if (!radio) {
        const btn = ev.target;
        const hiddenInfo = btn.previousElementSibling;
        if (btn.textContent == "Show more") {
          hiddenInfo.style.display = "block";
          btn.textContent = "Hide it";
        } else {
          hiddenInfo.style.display = "";
          btn.textContent = "Show more";
        }
      }
    }
  }
}
