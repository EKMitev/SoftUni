function calculator() {
    let n1;
    let n2;
    let result;
  function init() {
    n1 = document.getElementById("num1");
    n2 = document.getElementById("num2");
    result = document.getElementById("result");
  }

  function add() {
    result.value = Number(n1.value) + Number(n2.value);
  }

  function subtract() {
    result.value = Number(n1.value) - Number(n2.value);
  }

  return {
      init,
      add,
      subtract,
  }
}
const calculate = calculator ();
calculate.init ('#num1', '#num2', '#result');
