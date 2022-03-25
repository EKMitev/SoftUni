const library = require('./library.js')
const { expect } = require('chai')

describe("Tests …", function () {
    describe("calcPriceOfBook", function () {
        it("should throw an error", function () {
            expect(() => { library.calcPriceOfBook(1, 1) }).throw("Invalid input");
            expect(() => { library.calcPriceOfBook(null, 1) }).throw("Invalid input");
            expect(() => { library.calcPriceOfBook(undefined, 1) }).throw("Invalid input");
            expect(() => { library.calcPriceOfBook("asd", 1.1) }).throw("Invalid input");
            expect(() => { library.calcPriceOfBook("asd", null) }).throw("Invalid input");
            expect(() => { library.calcPriceOfBook("asd", undefined) }).throw("Invalid input");
            expect(() => { library.calcPriceOfBook("asd", "asd") }).throw("Invalid input");
        });

        it("should return", function () {
            expect(library.calcPriceOfBook("asd", 1980)).equal(`Price of asd is 10.00`);
            expect(library.calcPriceOfBook("asd", 1979)).equal(`Price of asd is 10.00`);
            expect(library.calcPriceOfBook("asd", 1981)).equal(`Price of asd is 20.00`);
            expect(library.calcPriceOfBook("asd", 2021)).equal(`Price of asd is 20.00`);
        });
    });

    describe("findBook", () => {
        it("should return correct", () => {
            expect(library.findBook(["Troy", "Life Style", "Torronto"], "Troy")).equal("We found the book you want.");
            expect(library.findBook(["Troy", "Life Style", "Torronto"], "Life Style")).equal("We found the book you want.");
            expect(library.findBook(["Troy", "Life Style", "Torronto"], "asd")).equal("The book you are looking for is not here!");
        })

        it("should throw", () => {
            expect(() => { library.findBook([], "asd") }).throw("No books currently available");
        })
    })

    describe("arrangeTheBooks", () => {
        it("should throw", () => {
            expect(() => { library.arrangeTheBooks(undefined) }).throw("Invalid input");
            expect(() => { library.arrangeTheBooks(null) }).throw("Invalid input");
            expect(() => { library.arrangeTheBooks(1.1) }).throw("Invalid input");
            expect(() => { library.arrangeTheBooks(-1) }).throw("Invalid input");
            expect(() => { library.arrangeTheBooks("asd") }).throw("Invalid input");
        })

        it("should return correct", () => {
            expect(library.arrangeTheBooks(1000)).equal("Insufficient space, more shelves need to be purchased.")
            expect(library.arrangeTheBooks(41)).equal("Insufficient space, more shelves need to be purchased.")
            expect(library.arrangeTheBooks(40)).equal("Great job, the books are arranged.")
            expect(library.arrangeTheBooks(10)).equal("Great job, the books are arranged.")
            expect(library.arrangeTheBooks(0)).equal("Great job, the books are arranged.")

        })
    })
});