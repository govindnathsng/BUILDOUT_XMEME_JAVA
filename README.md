# XMeme

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-6DB33F?style=for-the-badge&logo=spring-boot&logoColor=white)
![MongoDB](https://img.shields.io/badge/MongoDB-47A248?style=for-the-badge&logo=mongodb&logoColor=white)
![Docker](https://img.shields.io/badge/Docker-2496ED?style=for-the-badge&logo=docker&logoColor=white)
![Crio](https://img.shields.io/badge/Crio-Learn%20by%20Doing-FFDD00?style=for-the-badge&logo=crio)

> **Date:** August 2023 - March 2024

## 📘 Overview
**XMeme** is a backend web application that allows users to post and view memes. This project was developed from scratch, with a focus on designing a robust backend, implementing essential REST APIs, and ensuring smooth data storage and retrieval from MongoDB.

During this project:
- Developed the backend server from scratch using Spring Boot.
- Implemented REST APIs for posting and fetching memes.
- Leveraged Docker for containerized deployment and used MongoDB for data persistence.

## 🛠️ Project Structure

### **Backend API Development**
#### Scope of Work
- Created a backend server with Spring Boot and implemented necessary REST API handlers.
- Built a **POST API** to allow users to post memes.
- Developed **GET APIs**:
  - To fetch the 100 newest memes with `name`, `caption`, and `image URLs`.
  - To retrieve individual memes by meme ID.

#### Skills Used
- **Spring Boot**, **REST API**, **MongoDB**, **Jackson**

---

### **Data Storage and Error Handling**
#### Scope of Work
- Utilized MongoDB to store meme data, including user `name`, `caption`, and `image URL`.
- Managed error handling with appropriate HTTP response codes for invalid requests.
- Followed MVCS (Model-View-Controller-Service) layering for clear code organization and modularity.

#### Skills Used
- **MongoDB**, **HTTP Response Codes**, **MVCS Architecture**

---

### **Testing and Documentation**
#### Scope of Work
- Tested all implemented functionalities to ensure smooth operation.
- Documented each module with clear comments and followed best practices.

#### Skills Used
- **Unit Testing**, **Swagger for API Documentation**

---

## 🚀 Getting Started

1. **Clone the Repository**
   ```bash
   git clone https://github.com/govindnathsng/XMeme.git
   cd XMeme

    Build the Project

    bash

./gradlew build

Create Executable Jar

bash

    ./gradlew bootjar

    The executable JAR will be located in the build directory.

🐳 Docker Deployment

    Build Docker Image

    bash

docker build -t xmeme-app .

Run Docker Container

bash

    docker run -p 8080:8080 xmeme-app

    This will run the XMeme server on port 8080. Adjust the ports as needed.

⚙️ Technologies & Tools

    Backend: Spring Boot, MongoDB, Docker
    Build Tool: Gradle
    Testing: JUnit
    API Documentation: Swagger

📈 Features

    Post Memes: Allows users to post new memes with a name, caption, and image URL.
    View Memes: Retrieves the latest 100 memes and fetches individual memes by ID.
    Error Handling: Provides appropriate HTTP status codes for different scenarios.
    Containerization: Docker support for deployment flexibility.

📄 License

This project is licensed under the Apache License 2.0. Please ensure the README.md and LICENSE files are included with any modifications.
📞 Contact

For questions or feedback, reach out to:

    GitHub: govindnathsng
    Email: govindnathsng@gmail.com

Check out my <a href="https://www.crio.do/learn/portfolio/govindnathsng/" target="_blank">Crio Portfolio</a> for more projects like this!
