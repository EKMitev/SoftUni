const numberOperations = require("./03. Number Operations_Resources")
const {expect} = require("chai");

describe("tests...", () => {
    describe("test_powerNumber", () => {
        it ("should return power number", () => {
            expect(numberOperations.powNumber(2)).equal(4)
            expect(numberOperations.powNumber(5)).equal(25)
        })
    })

    describe("numberChecker", () => {
        it("should throw", () => {
            expect(() => {numberOperations.numberChecker(NaN)}).throw('The input is not a number!')
            expect(() => {numberOperations.numberChecker(undefined)}).throw('The input is not a number!')
            expect(() => {numberOperations.numberChecker('NaN')}).throw('The input is not a number!')
        })

        it("should return  < 100", () => {
           expect(numberOperations.numberChecker(1)).equal('The number is lower than 100!')
           expect(numberOperations.numberChecker(-1)).equal('The number is lower than 100!')
           expect(numberOperations.numberChecker(0)).equal('The number is lower than 100!')
           expect(numberOperations.numberChecker(99)).equal('The number is lower than 100!')
        })

        it("should return  > 100", () => {
            expect(numberOperations.numberChecker(100)).equal('The number is greater or equal to 100!')
            expect(numberOperations.numberChecker(999999)).equal('The number is greater or equal to 100!')
           
         })
    })

    describe("sumArrays", () => {
        it ("should return new Array", () => { 
            expect(numberOperations.sumArrays([], [1, 2, 3])).include.members([1, 2, 3])
            expect(numberOperations.sumArrays([0, 0, 0], [1, 2, 3])).include.members([1, 2, 3])
            expect(numberOperations.sumArrays([1], [1, 2, 3])).include.members([2, 2, 3])
            expect(numberOperations.sumArrays([1, 2, 3, 4], [1, 2, 3])).include.members([2, 4, 6, 4])
        })
    })
})