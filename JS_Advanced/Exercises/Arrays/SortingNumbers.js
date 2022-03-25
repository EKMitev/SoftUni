function sortNumbers(array) {
  let ordered = [];

  let position = 0;
  while (array.length > 0) {
    const smallest = Math.min.apply(null, array);
    const biggest = Math.max.apply(null, array);

    let index = array.indexOf(smallest);
    array.splice(index, 1);
    array.splice(array.indexOf(biggest), 1);

    ordered[position] = smallest;
    if (smallest != biggest) {
      ordered[position + 1] = biggest;
    }

    position += 2;
  }

  return ordered;
}

console.log(sortNumbers([1, 65, 3, 52, 48, 63, 31, -3, 2, 18, 56]));
