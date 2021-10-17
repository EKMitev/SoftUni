function print(arr, step) {
    let outputArr = [];
    let count = 0;
    for (let i = 0; i < arr.length; i += step) {
        outputArr[count++] = arr[i];
    }
    return outputArr;
}

console.log(print(['5',
    '20',
    '31',
    '4',
    '20'],
    2))
