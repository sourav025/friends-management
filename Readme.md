
# Friend Management Api Specification

## Basic Assumptions
* As there is no separate registration api, so user registration will occur for each request with new email address
* Every server restart will refresh the database

### Technology used & Reasons
* Spring-Boot
    * Easy to catch up and flexible to switch between development and production mode.
* JPA & Hibernate
    * Easy to manage database queries with Repository interface provided by Hibernate.
    * Switch database is possible and no extra headache needed.
* Swagger
    * Nice UI to clearly specify the specification of API
    * Easy to test without any other tool like postman.
* H2 DB
    * Any other db is also ok. But everytime needs to manage start and stop that's quiet inconvenient while development. 
    * Of course, for production it's recommended to use other database like oracle, mysql or postgres.

## How to run or deploy <code>FriendManagement</code> APIs

### System specification
* Java-8
* Maven-3.3.3
* docker-17.12.0-ce
* docker-compose-1.18.0

### Steps to run application
* Go to the root directory using <code>cd friends-management</code>
* Execute <code>./build_run</code> command, provided in the root directory
    * It will build the runnable jar and build docker container that can be pushed to any docker registry for production usage
    * It remove the old running docker container and launch a new docker container.
* Please open browser and hit http://localhost:8080/
    * It should open the SwaggerUI API documentation. Please try using swagger web to check api behavior.

### <code>Friends Api</code> functionality
* PUT <code>/makefriend</code> - 
    * This api is responsible to make friends with 2 provided emails
    * Friendship is only possible if any one of two given friends, are not blocked. e.g. `alice@anymail.com` blocked `bob@anymail.com`. in this case, friendship is not possible.
* POST <code>/friends</code>
    * Returns the friends of given email. It 
* POST <code>/mutualfriends</code>
    * This api takes 2 email address and finds the mutual friends. It also includes the blocked friends.
* PUT <code>/subscribe</code>
    * Responsible to subscribe/follow the updates from friends.
* POST <code>/block</code>
    * Requests to block a user for receiving updates
* POST <code>/post</code>
    * Update a text of status for friends
    * Returns the update status and recipients.
    * e.g.
    Eligibility for receiving updates from i.e. "john@example.com": has not blocked updates from "john@example.com", and at least one of the following: has a friend connection with "john@example.com" has subscribed to updates from  "john@example.com" has been @mentioned in the update