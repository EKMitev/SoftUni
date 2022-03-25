function listNames(array) {
  let position = 1;
  let out = array
    .sort((f, s) => f.localeCompare(s))
    .map((e) => (position++).toString() + "." + e)
    .join("\n");

  console.log(out);
}

listNames(["John", "Bob", "Christina", "Ema"]);
