function parseToJSON(array) {
  const arr = [];
  for (let i = 1; i < array.length; i++) {
    const startIndex = 2;
    const endIndex = array[i].length - 2;
    const [town, latitude, longitude] = array[i]
      .substring(startIndex, endIndex)
      .split(" | ");
    const obj = {
      Town: town,
      Latitude: Number(Number(latitude).toFixed(2)),
      Longitude: Number(Number(longitude).toFixed(2)),
    };
    arr.push(obj);
  }
  return JSON.stringify(arr);
}

console.log(
  parseToJSON([
    "| Town | Latitude | Longitude |",
    "| Sofia | 42.696552 | 23.32601 |",
    "| Beijing | 39.913818 | 116.363625 |",
  ])
);
