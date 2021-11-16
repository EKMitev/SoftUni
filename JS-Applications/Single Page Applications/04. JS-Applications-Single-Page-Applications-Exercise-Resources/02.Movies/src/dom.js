const main = document.querySelector('main');

export function showSection(section) {
    main.replaceChildren(section);
}

export function e(type, attributes, ...value) {
    const el = document.createElement(type);
    for (const prop in attributes) {
        el[prop] = attributes[prop];
    }

    for (let item of value) {
        if (typeof item == 'string' || typeof item == 'number') {
            item = document.createTextNode(item);
        }
        el.appendChild(item);
    }

    return el;
}