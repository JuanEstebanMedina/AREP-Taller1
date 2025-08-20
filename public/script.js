async function fetchGreeting(name) {
    try {
        const response = await fetch(`/api/hello?name=${name}`);
        const data = await response.json();
        document.getElementById('greeting').innerHTML = `<p>${data.mensaje}</p>`;
    } catch (error) {
        console.error('Error fetching greeting:', error);
    }
}