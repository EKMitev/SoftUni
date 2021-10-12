class Stringer {
    constructor(string, length) {
        this.innerString = string;
        this.innerLength = Number(length);
        this.fullString = string;
    }

    increase(length) {
        if (this.innerString.length < length) {
            this.innerString += this.fullString.substring(this.innerString.length-1, length);
            this.innerLength = this.innerString.length
        }
    }
    
    decrease(length) {
        this.innerString = this.fullString.substring(0, this.innerLength - length);
        this.innerLength = this.innerString.length
        if (this.innerLength < 0){
            this.innerLength = 0;
        }
    }
    toString(){
        if (this.innerString != this.fullString){
            return this.innerString + '...'
        }
        return this.innerString;
    }
}

let s = new Stringer("Viktor", 6);
s.decrease(3);
console.log(s.toString()); // Test

let test = new Stringer("Test", 5);
console.log(test.toString()); // Test

test.decrease(3);
console.log(test.toString()); // Te...

test.decrease(5);
console.log(test.toString()); // ...

test.increase(10); 
console.log(test.toString()); // Test