function fib() {
    let n1 = 0;
    let n2 = 1;
    let next = 0;
 
    return function getFib() {
        next = n1 + n2
        n1 = n2;
        n2 = next
        return n1;
    }
    return getFib;
}

let f = fib();

console.log(f());
console.log(f());
console.log(f());
console.log(f());
console.log(f());
