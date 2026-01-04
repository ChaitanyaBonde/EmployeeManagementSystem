FROM openjdk:22-jdk
ADD /target/empmgmt.jar empmgmt.jar
ENTRYPOINT ["java","-jar","empmgmt.jar"]
