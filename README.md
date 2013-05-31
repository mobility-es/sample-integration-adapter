sample-integration-adapter
==========================

### Introduction:
The sample integration adapter show cases how the AIQ java library can be leveraged to develop an integration adapter.
It demonstrates how the developers should develop their integration adapters.

There are inline comments and java doc explaining the concepts and logic.

### Features:
This sample integration adapter supports the train damage application and provides following functionality
* Generates a static list of trains.
* Generates a set of default damage report for each train.
* Users can add new damage reports using the train damage application.

All the data is being stored in memory.

### Dependencies:
Sample integration adapter is a maven web project and all dependencies are listed in the pom.xml. The required dependencies would be fetched
when the project is imported to an IDE or when it is run.

### Running sample integration adapter:
To run the sample integration adapter use the following command.
* `maven jetty:run` will start the jetty server on port 8088

The jetty http port is configurable in pom.xml


