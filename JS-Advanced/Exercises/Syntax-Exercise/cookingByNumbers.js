function cook(...args) {
    let n = parseInt(args[0]);


    args.forEach(e => {
        switch (e) {
            case 'chop':
                n /= 2;
                console.log(n);
                break;
            case 'dice':
                n = Math.sqrt(n);
                console.log(n);
                break;
            case 'spice':
                n += 1;
                console.log(n);
                break;
            case 'bake':
                n *= 3;
                console.log(n);
                break;
            case 'fillet':
                n = n * 0.8;
                console.log(n);
                break;
        }
    });
}

cook('9', 'dice', 'spice', 'chop', 'bake', 'fillet')