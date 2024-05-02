FROM openjdk:17
#设定时区
ENV TZ=Asia/Shanghai
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

COPY labsystem-0.0.1-SNAPSHOT.jar /app.jar

EXPOSE 5000
ENTRYPOINT ["java","-jar","app.jar"]  #执行jar包

