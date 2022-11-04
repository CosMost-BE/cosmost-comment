FROM openjdk:17-ea-11-jdk-slim
EXPOSE 9003

COPY build/libs/cosmost-comment-1.0.jar CommentService.jar
ENTRYPOINT ["java", "-jar", "CommentService.jar"]
