# Simple HTTP Server

A lightweight HTTP server implemented in Java that serves static files and handles basic API requests. The server can handle both text-based content (HTML, CSS, JavaScript) and binary files (images), making it suitable for serving simple web applications.

## Features

- Static file serving (HTML, CSS, JS, images)
- Basic API endpoint support (/api/hello)
- Content-type detection
- Simple routing system
- Support for GET requests
- Directory path security (prevents directory traversal)

## Getting Started

These instructions will help you get a copy of the project up and running on your local machine for development and testing purposes.

### Prerequisites

To run this project, you need:

- Java 8 or higher
- Maven 3.6.3 or higher
- Git (optional, for cloning the repository)

### Installing

1. Clone the repository (or download it as a ZIP file):
```bash
git clone https://github.com/[your-username]/AREP-Taller1.git
```

2. Navigate to the project directory:
```bash
cd AREP-Taller1
```

3. Build the project using Maven:
```bash
mvn clean install
```

### Running the Server

1. Start the server using Maven:
```bash
mvn exec:java -Dexec.mainClass="escuelaing.edu.co.httpserver.HttpServer"
```

2. The server will start on port 35000. You can access it at:
```
http://localhost:35000
```

## Usage

### Static File Serving

The server will serve static files from the `public` directory. By default, accessing the root URL will serve `index.html`.

Supported file types include:
- HTML files
- CSS files
- JavaScript files
- Images (PNG, JPG, ICO)

### API Endpoints

The server includes a basic API endpoint:

- `/api/hello?name=[yourname]`: Returns a greeting message with the provided name parameter

### Example

1. Access the main page at `http://localhost:35000`
2. You'll see a form where you can enter your name
3. Upon submission, the server will respond with a greeting message

## Project Structure

- `src/main/java/escuelaing/edu/co/httpserver/`: Contains the main server implementation
- `public/`: Contains static files served by the server
  - `index.html`: Main webpage
  - `script.js`: Frontend JavaScript
  - `styles.css`: CSS styles
  - `img/`: Directory containing images

## Built With

* [Java](https://www.java.com/) - The programming language used
* [Maven](https://maven.apache.org/) - Dependency Management


## Authors

* **Juan Esteban Medina** - Universidad Escuela Colombiana de Ingeniería Julio Garavito

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details

