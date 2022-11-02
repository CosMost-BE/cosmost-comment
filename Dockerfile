ARG JAR_FILE=build/libs/*jar

FROM openjdk:17-ea-11-jdk-slim
EXPOSE 9003
ENV TZ Asia/Seoul
COPY ./ ./
RUN chmod 755 gradlew
RUN ./gradlew build -x test

COPY  ${JAR_FIlE} CommentService.jar
ENTRYPOINT ["java", "-jar", "CommentService.jar"]