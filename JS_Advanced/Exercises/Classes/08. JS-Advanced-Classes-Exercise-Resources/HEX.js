class Hex {
    constructor(value) {
        this.value = value;
        this.hexString = '';
    }

    valueOf() {
        this.hexString = this.value.toString(16)
        return parseInt(this.hexString, 16);
    }

    toString() {
        this.hexString = this.value.toString(16)
        return `0x${this.value.toString(16).toUpperCase()}`;
    }

    plus(number) {
        const newVal = this.value.valueOf() + number
        return '0x' + newVal.toString(16).toUpperCase()
    }

    minus(number) {
        const newVal = this.value.valueOf() - number
        return '0x' + newVal.toString(16).toUpperCase()
    }

    parse(string){
        return parseInt(string, 16)
    }
}
let FF = new Hex(255);
console.log(FF.toString());
console.log(FF.valueOf() + 1 == 256);
let a = new Hex(10);
let b = new Hex(5);
console.log(a.plus(b).toString());
console.log(a.plus(b).toString() === '0xF');
console.log(FF.parse('AAA'));