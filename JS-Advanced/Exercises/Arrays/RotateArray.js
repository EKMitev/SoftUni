function rotate(arr, n) {
  if (n > arr.length) {
    n = n % arr.length;
  }
  for (let i = 0; i < n; i++) {
    const temp = arr.pop();
    arr.unshift(temp);
  }

  console.log(arr.join(" "));
}

rotate(["1", "2", "3", "4"], 2);
rotate(["Banana", "Orange", "Coconut", "Apple"], 15);
