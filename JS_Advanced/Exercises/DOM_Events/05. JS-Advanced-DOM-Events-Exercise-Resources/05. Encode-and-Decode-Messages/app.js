function encodeAndDecodeMessages() {
  const inputElement = document.getElementById("main").children[0].children[1];

  const outputElement = document.getElementById("main").children[1].children[1];

  const encryptBtn = inputElement.nextElementSibling;
  encryptBtn.addEventListener("click", encrypt);

  let txt = "";

  const decryptBtn = outputElement.nextElementSibling;
  decryptBtn.addEventListener("click", () => {
    outputElement.value = txt;
    txt = "";
  });

  function encrypt() {
    let encryptedMessage = "";
    txt = inputElement.value;
    for (let i = 0; i < inputElement.value.length; i++) {
      let code = inputElement.value.charCodeAt(i);
      encryptedMessage += String.fromCharCode(code + 1);
    }
    outputElement.value = encryptedMessage;
    inputElement.value = "";
  }
}
