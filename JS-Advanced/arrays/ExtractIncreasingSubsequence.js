function extract(arr) {
  for (let i = 0; i < arr.length; i++) {
    if (arr[i] < arr[i - 1]) {
      arr.splice(i, 1);
      i--;
    }
  }
  return arr;
}

console.log(extract([1, 3, 8, 4, 10, 12, 3, 2, 24]));
