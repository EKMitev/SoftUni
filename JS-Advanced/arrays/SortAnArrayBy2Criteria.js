function solve(array) {
  array.sort((f, s) => {
    if (f.length == s.length) {
     return f.localeCompare(s);
    }
    return f.length - s.length;
  });
  console.log(array.join("\n"));
}

solve(["test", "Deny", "omen", "Default"]);
