window.addEventListener('load', solution);

function solution() {
  const data = Array.from(document.querySelectorAll('#form > div'));

  const ul = document.getElementById('infoPreview');
  const filledFieldsData = [];

  const submitBTN = document.getElementById('submitBTN');
  submitBTN.addEventListener('click', add)

  const editBTN = document.getElementById('editBTN');
  editBTN.addEventListener('click', edit);

  const continueBTN = document.getElementById('continueBTN');
  continueBTN.addEventListener('click', finish);


  function add() {
    if (data[0].children[1].value != '' && data[1].children[1].value != '') {
      data.filter(el => el.children[1].value != '')
        .forEach(el => {
          const li = document.createElement('li');
          li.textContent = el.children[0].textContent + " " + el.children[1].value;
          ul.appendChild(li);
          filledFieldsData.push(el.children[1].value);
          el.children[1].value = '';
        })

      submitBTN.disabled = true;
      editBTN.disabled = false;
      continueBTN.disabled = false;

    }
  }

  function edit() {
    for (let i = 0; i < filledFieldsData.length; i++) {
      data[i].children[1].value = filledFieldsData[i]
    }
    filledFieldsData.splice(0, filledFieldsData.length)

    ul.innerHTML = '';
    submitBTN.disabled = false;
    editBTN.disabled = true;
    continueBTN.disabled = true;
  }

  function finish() {
    document.getElementById('block').innerHTML = '<h3>Thank you for your reservation!</h3>'
  }
}
