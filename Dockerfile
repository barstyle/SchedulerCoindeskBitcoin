FROM bellsoft/liberica-openjdk-alpine-musl
COPY ./target/SchedulerCoindeskBitcoin-0.0.1-SNAPSHOT.jar .
CMD ["java","-jar","SchedulerCoindeskBitcoin-0.0.1-SNAPSHOT.jar"]