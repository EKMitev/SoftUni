function solve() {
  const text = document.getElementById("text").value.toLowerCase();
  const convertType = document.getElementById("naming-convention").value;

  let result = '';

  if (convertType == 'Pascal Case'){
    const arr = text.split(' ');
    for (let i = 0; i < arr.length; i++) {
     result += arr[i].charAt(0).toUpperCase()
      + arr[i].substring(1);
    }
  } else if (convertType == 'Camel Case'){
    const arr = text.split(' ');
    result = arr[0].trim();
    for (let i = 1; i < arr.length; i++) {
      result += arr[i].charAt(0).toUpperCase()
       + arr[i].substring(1);
     }
  } else {
    result = 'Error!'
  }

  document.getElementById("result").innerHTML = result;
}