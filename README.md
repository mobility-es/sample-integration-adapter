sample-integration-adapter
==========================

### Introduction:
An integration adapter is a project that connects backend systems to the mobile devices through the server. This
project is a sample integration adapter created to showcase how the AppearIQ Java integration framework can be leveraged 
to develop an integration adapter. The project implements the use case of a backend for a simple train damage reporting 
system with the aim to help developers such that they can easily create their own integration adapters.

The project should be treated as example code. There are inline comments and java doc explaining the concepts and logic.

### Features:
This sample integration adapter supports the train damage application for devices and provides following functionality:
* Generates a static list of trains.
* Generates a set of default damage report for each train.
* Users can list all trains using the train damage application.
* Users can add new damage reports using the train damage application.

All the data is being stored in memory and will be reset on restart.

### Dependencies:
Sample integration adapter is a maven web project and all dependencies are listed in the pom.xml file.

### Configuring sample integration adapter:
Configuration for the integration adapter can be found in file `src/main/aiq.properties`. There you can set the follow
properties:

| Property               | Description                                         |
|------------------------|-----------------------------------------------------|
| `orgname`              | The name of the organization                        |
| `solution`             | The solution to connect the integration adapter to  |
| `username`             | The username of a user with integration permission  |
| `password`             | The password of the above mentioned user            |
| `integration.url`      | The URL of this integration adapter. If this is set, the integration adapter will automatically register itself on startup |
| `integration.password` | The password that the AIQ server will use when communicating with the integration adapter. If this is empty a randomly generated password will be used instead. Setting this password is useful for testing the endpoints without using a mobile device and a web app. |

### Running sample integration adapter locally:
Sample integration adapter is configured to run in jetty on port 8088. User the following command to run the project:
* `mvn jetty:run`

### Monitor logs from sample integration adapter in in Development environment:
* `mvn aiq:ia.logs.tail`

