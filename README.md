sample-integration-adapter
==========================

### Introduction:
An integration adapter is a project that connects backend systems to the mobile devices through the platform. This
project is a sample integration adapter created to showcase how the AIQ Java library can be leveraged to develop an
integration adapter. The project implements the use case of a backend for a simple train damage reporting system with
the aim to help developers such that they can easily create their own integration adapters.

The project should be treated as example code. There are inline comments and java doc explaining the concepts and logic.

### Features:
This sample integration adapter supports the train damage application for devices and provides following functionality:
* Generates a static list of trains.
* Generates a set of default damage report for each train.
* Users can list all trains using the train damage application.
* Users can add new damage reports using the train damage application.

All the data is being stored in memory and will be reset on restart.

### Dependencies:
Sample integration adapter is a maven web project and all dependencies are listed in the pom.xml.

### Running sample integration adapter:
Sample integration adapter is configured to run in jetty on port 8088. User the following command to run the project:
* `maven jetty:run`

The jetty http port is configurable in pom.xml


