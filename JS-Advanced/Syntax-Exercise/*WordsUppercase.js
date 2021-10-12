function extract(arg){
    let result = arg
    .split(/\W+/)
    .filter(w => w.length > 0)
    .join(", ");

    console.log(result.toUpperCase());
}

extract('Hi, how are you?')