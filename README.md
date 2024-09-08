# AnyQuizAI

AnyQuizAI is a full-stack project I developed to practice and showcase my full stack developer skills. This project incorporates novel technologies such as Spring AI and demonstrates the development of LLM-driven applications.

## Project Purpose

**Important:** I created AnyQuizAI as a personal project to hone my full stack development skills and gain hands-on experience with cutting-edge technologies. It serves as a practical example of building a complete web application that integrates various components of modern web such as Angular and Tailwind CSS.

## Features

### 🔍 Discover

Discover allows users to find quizzes to play with advanced search options. The search functionality is powered by Elasticsearch, providing improved filtering and speed.

![Discovering quizzes](screenshots/discover.gif)

### ✨ Create

Create leverages the power of OpenAI through Spring AI to generate quizzes and save them to the database. Users can specify the topic, difficulty, number of questions, and more.

![Creating a quiz](screenshots/create1.gif)

![Creating a quiz](screenshots/create2.gif)

### 🎮 Play

Once you've discovered a quiz, you can play it and compete for a spot on the leaderboard.

![Playing a quiz](screenshots/play1.gif)

![Playing a quiz](screenshots/play2.gif)

![Playing a quiz](screenshots/play3.gif)

## Getting Started

To run the application for development, follow these steps:

1. Ensure you have Docker installed on your system.

2. Set your OpenAI API key as an environment variable:
   ```
   export OPENAI_API_KEY="yourOpenAIKey"
   ```

3. Start the required services using Docker Compose:
   ```
   docker-compose up -d
   ```

4. Run the Spring Boot application:
   ```
   ./mvnw spring-boot:run
   ```

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.