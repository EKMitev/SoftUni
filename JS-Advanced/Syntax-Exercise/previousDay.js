function previousDay(yy, mm, dd) {
    let date = new Date(yy, mm - 1, dd - 1);
    console.log(date.getFullYear() + '-' + (date.getMonth() +1) + '-' + date.getDate())
}
previousDay(2016, 9, 30)