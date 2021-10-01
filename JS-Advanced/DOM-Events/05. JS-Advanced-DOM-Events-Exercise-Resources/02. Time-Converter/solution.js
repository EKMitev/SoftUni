function attachEventsListeners() {
  const mainEl = document.querySelector("main");
  mainEl.addEventListener("click", onClick);

  function convert(unit, val) {
    const ratios = {
      days: 1,
      hours: 24,
      minutes: 1440,
      seconds: 86400,
    };

    const inDays = val / ratios[unit];
    return {
      days: inDays,
      hours: inDays * ratios.hours,
      minutes: inDays * ratios.minutes,
      seconds: inDays * ratios.seconds,
    };
  }

  function onClick(e) {
    const target = e.target;
    if (target.tagName == "INPUT" && target.id.includes("Btn")) {
      const input = target.previousElementSibling;
      const newTime = convert(input.id, Number(input.value));
      document.getElementById("days").value = newTime.days;
      document.getElementById("hours").value = newTime.hours;
      document.getElementById("minutes").value = newTime.minutes;
      document.getElementById("seconds").value = newTime.seconds;
    }
  }
}
