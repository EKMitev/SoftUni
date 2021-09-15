//1 km/h = 16.6666666667 m/min
function walk(a, b, c) {
    let distance = a * b; // metres
    let restTime = Math.floor(distance / 500);
    let time = (distance / (c * 16.6666666667)) + restTime;
    time = time * 60;


    
    let minutes = Math.floor(time / 60);
    let seconds = Math.round(time - (minutes * 60));
    let hours = Math.floor(time / 3600);

    console.log((hours < 10 ? "0" : "") + hours + ":" + (minutes < 10 ? "0" : "") + (minutes) + ":" + (seconds < 10 ? "0" : "") + seconds);
}
walk(9000, 0.6, 5)