async function fetchGreeting(name) {
    try {
        const response = await fetch(`/api/hello?name=${name}`);
        if (!response.ok) {
            throw new Error('Error en la respuesta del servidor');
        }
        //const data = await response.json();   // json response
        const data = await response.text();
        const greetingDiv = document.getElementById('greeting');
        greetingDiv.innerHTML = `<p>${data}</p>`;
        //greetingDiv.innerHTML = `<p>${data.mensaje}</p>`; // json response
        greetingDiv.classList.add('visible');
    } catch (error) {
        console.error('Error fetching greeting:', error);
        const greetingDiv = document.getElementById('greeting');
        greetingDiv.innerHTML = '<p>Error al obtener el saludo. Intenta de nuevo.</p>';
        greetingDiv.classList.add('visible');
    }
}