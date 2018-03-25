FROM java:8-jre
ADD target/friends-management-0.0.1-SNAPSHOT.jar /friends-management.jar
CMD ["java", "-jar", "/friends-management.jar"]
