function largestNum(a, b, c){
    let arr= [a, b, c]
    let largest = a;
    for(let i = 0; i < 3; i++){
        if (arr[i] > largest){
            largest = arr[i];
        }
    }
    console.log(`The largest number is ${largest}.`)
}

largestNum(5, -3, 16)
largestNum(-5, -3, -16)