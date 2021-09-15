function area(r){
    if (typeof r != 'number'){
        console.log(`We can not calculate the circle area, because we receive a ${typeof r}.`)
    } else {
        console.log((Math.PI * r ** 2).toFixed(2))
    }
}

area(5)
area('asd')