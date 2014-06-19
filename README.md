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
Enter your organization name, username and password in file `src/main/aiq.properties`.

### Running sample integration adapter locally:
Sample integration adapter is configured to run in jetty on port 8088. User the following command to run the project:
* `mvn jetty:run`

### Deploy and start sample integration adapter to Development environment:
* `mvn aiq:ia.deploy`

### Stop sample integration adapter in Development environment:
* `mvn aiq:ia.stop`

### Start sample integration adapter in Development environment:
* `mvn aiq:ia.start`

### Monitor logs from sample integration adapter in in Development environment:
* `mvn aiq:ia.logs.tail`

