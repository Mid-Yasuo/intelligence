COPY *.jar /app.jar

#地址映射
CMD ["--server.port=8082"]

#对外暴露端口
EXPOSE 8082

#执行命令
ENTRYPOINT ["java","-jar","/app.jar"]