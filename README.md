# Chat Application

This project is a simple chat application developed as part of a practical work for a Java course. The application demonstrates network connectivity using Java sockets. It allows users to send and receive messages over a network(localhost via port 5000).

## Features

- **Client-Server Architecture**: The application consists of a client and a server that communicate over a network.
- **Message Sending**: Users can send messages from the client to the server.
- **Real-time Communication**: Messages are displayed in real-time in the chat area.

## Technologies Used

- **Java**: The primary programming language used for development.
- **JavaFX**: Used for building the graphical user interface (GUI) and managing input/output.
- **Sockets**: Utilized for network communication between the client and server.
- **Threads**: Implemented to handle multiple connections and improve responsiveness.


## Getting Started

### Installation

1. Clone the repository:
```
git clone https://github.com/[yourusername]/chat-java.git
   ```
Replace yourusername with your actual GitHub username.
1. Navigate to the project directory:
```
cd chat-app
```
2. Build the project using Maven:
```
mvn clean install
```
2. Running the Application
2.1. Start the server:
    
```
mvn exec:java -Dexec.mainClass="com.poo.chatapp.Server"
```
2.2. Start the client:
```
mvn exec:java -Dexec.mainClass="com.poo.chatapp.Client"
```
Usage

Enter the server address and port in the client application.
Type your message in the input field and press the send button to communicate with the server.
   
