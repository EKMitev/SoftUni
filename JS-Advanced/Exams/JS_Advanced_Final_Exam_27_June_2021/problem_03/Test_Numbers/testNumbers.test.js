const testNumbers = require('./testNumbers.js');
const { expect } = require('chai');

describe("Tests …", () => {
    describe("sumNumber", () => {
        it("should return correct", () => {
           expect(testNumbers.sumNumbers(2, 3)).equal('5.00');
           expect(testNumbers.sumNumbers(-10, 5)).equal('-5.00');
           expect(testNumbers.sumNumbers(5, -10)).equal('-5.00');
           expect(testNumbers.sumNumbers(5.1, 10.34)).equal('15.44');
        });

        it("should return undefined", () => {
            expect(testNumbers.sumNumbers("2", 3)).equal(undefined);
            expect(testNumbers.sumNumbers(5)).equal(undefined);
            expect(testNumbers.sumNumbers(5, null)).equal(undefined);
         });
     });

     describe("numberChecker", () => {
         it("should return correct", () => {
             expect(testNumbers.numberChecker(5)).equal("The number is odd!")
             expect(testNumbers.numberChecker(4)).equal("The number is even!");
             expect(testNumbers.numberChecker('4')).equal("The number is even!");

             expect(() => {testNumbers.numberChecker('a')}).throw("The input is not a number!")
             expect(() => {testNumbers.numberChecker(undefined)}).throw("The input is not a number!")
             expect(() => {testNumbers.numberChecker({})}).throw("The input is not a number!")
         })
     })

     describe("averageSumArray", () => {
         it ("should return correct", () => {
             expect(testNumbers.averageSumArray([2, 2, 2, 2])).equal(2)
             expect(testNumbers.averageSumArray([2, 2, 2, 2])).equal(2)
             expect(testNumbers.averageSumArray([1, 2, 3])).equal(2)
         })
     })
});