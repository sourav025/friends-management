
# Friend Management Api Specification

## Basic Assumptions
* As there is no separate registration api, so user registration will occur for each request with new email address
* Every server restart will refresh the database

## How to run or deploy <code>FriendManagement</code> APIs
### Ideal requirements
* Linux Based System or Apple Mac
* Java-8
* Maven-3.3.3
* docker-17.12.0-ce
* docker-compose-1.18.0

### Steps to run application
* Go to the root directory using <code>cd friends-management</code>
* Execute <code>./build</code> command, provided in the root directory
    * It will build the runnalbe jar and build docker container that can be pushed to any docker registry for production usage
* Execute <code>./rundocker</code>
    * This script will remove the old running container and launch a new container. The image was created in the last <code>build</code> command will be used.
* Please open browser and hit http://localhost:8080/
    * It should open the SwaggerUI API documentation. Please try using swagger web to check api behavior.
