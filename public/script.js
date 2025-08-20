async function fetchGreeting(name) {
    try {
        const response = await fetch(`/api/hello?name=${name}`);
        if (!response.ok) {
            throw new Error('Error en la respuesta del servidor');
        }
        const data = await response.json();
        const greetingDiv = document.getElementById('greeting');
        greetingDiv.innerHTML = `<p>${data.mensaje}</p>`;
        greetingDiv.classList.add('visible');
    } catch (error) {
        console.error('Error fetching greeting:', error);
        document.getElementById('greeting').innerHTML = '<p>Error al obtener el saludo. Intenta de nuevo.</p>';
    }
}