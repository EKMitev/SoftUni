/**
 *
 * @param {Array} arr
 * @param {String} order
 */
function solve(arr, order) {
  arr.sort((f, s) => {
    if (order == "asc") {
      return f - s;
    }
    return s - f;
  });

  return arr;
}

console.log(solve([14, 7, 17, 6, 8], 'de'))