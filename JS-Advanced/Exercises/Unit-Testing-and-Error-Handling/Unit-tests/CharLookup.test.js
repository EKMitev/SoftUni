const { lookupChar } = require("./CharLookup");
const { expect } = require("chai");

describe("CharLookup", () => {
  it("should return undefined with invalid param", () => {
    expect(lookupChar(0, 0)).to.be.undefined;
  });

  it("should return undefined with invalid index", () => {
    expect(lookupChar("asd", "1")).to.be.undefined;
  });

  it("should return Incorrect index", () => {
    expect(lookupChar("asd", -1)).to.equal("Incorrect index");
  });

  it("should return index", () => {
    expect(lookupChar("asd", 1)).to.equal("s");
  });

  it("should return undefined", () => {
    expect(lookupChar("asd", 1.1)).to.be.undefined;
  });

  it("should return Incorrect index", () => {
    expect(lookupChar("asd", 3)).to.equal("Incorrect index");
  });
});
