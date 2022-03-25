const table = document.querySelector('tbody');

window.addEventListener('DOMContentLoaded', async () => {
    const res = await fetch('http://localhost:3030/jsonstore/collections/students');
    const data = await res.json();

    Object.values(data)
        .forEach(s => table.innerHTML +=
            `<tr>
                <td>${s.firstName}</td>
                <td>${s.lastName}</td>
                <td>${s.facultyNumber}</td>
                <td>${s.grade}</td>
            </tr>`);
});

document.querySelector('form').addEventListener('submit', async (ev) => {
    ev.preventDefault();

    const form = document.getElementById('form');
    const formData = new FormData(form);

    const firstName = formData.get('firstName');
    const lastName = formData.get('lastName');
    const facultyNumber = formData.get('facultyNumber');
    const grade = formData.get('grade');

    if (Array.from(form.querySelectorAll('input')).some(x => x.value == '')) {
        alert('All fields are required');
        return;
    }

    form.reset();

    const student = {
        firstName,
        lastName,
        facultyNumber,
        grade
    }

    await fetch('http://localhost:3030/jsonstore/collections/students',
        {
            method: 'post',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(student)
        }
    );

    table.innerHTML += `<tr>
    <td>${student.firstName}</td>
    <td>${student.lastName}</td>
    <td>${student.facultyNumber}</td>
    <td>${student.grade}</td>
</tr>`

});