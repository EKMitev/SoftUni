function func(n) {
    let flag = true;

    n = n + "";
    let total = parseInt(n[0]);

    for (let i = 1; i < n.length; i++) {
        if (n[i] != n[i - 1]) {
            flag = false;
        }
        total += parseInt(n[i]);
    }

    console.log(flag)
    console.log(total)
}

func(2222222)