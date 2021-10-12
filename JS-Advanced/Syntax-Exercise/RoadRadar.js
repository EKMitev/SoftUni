function radar(speed, roadType){
    let limit = 0;
    switch(roadType){
        case 'motorway':
            limit = 130;
            break;
        case 'interstate':
            limit = 90;
            break;
        case 'city':
            limit = 50;
            break;
        case 'residential':
            limit = 20;
            break;
    }

    if (limit >= speed){
        console.log(`Driving ${speed} km/h in a ${limit} zone`)
        return;
    }

    let speedingType = null;
    if (speed - limit <= 20){
        speedingType = 'speeding';
    } else if (speed - limit <= 40){
        speedingType = 'excessive speeding';
    } else {
        speedingType = 'reckless driving';
    }

    console.log(`The speed is ${speed - limit} km/h faster than the allowed speed of ${limit} - ${speedingType}`);
}

radar(50, 'city')
radar(21, 'residential')
radar(120, 'interstate')
radar(200, 'motorway')