FROM amd64/amazoncorretto:17
WORKDIR /app
COPY ./build/libs/kuvis-0.0.1-SNAPSHOT.jar /app/kuvis.jar
CMD ["java", "-Duser.timezone=Asia/Seoul", "-jar", "-Dspring.profiles.active=dev", "kuvis.jar"]