# dvt-whatsapp-business-api-demo
DVT Whatsapp Business API POC

# How to run this
## First-First step
Pull origin/producer (https://github.com/RuaanKlem/dvt-whatsapp-business-api-demo/tree/producer)

## Second-First step
Run `docker-compose up` from the root directory 

## Third-First step
Build the jar `mvn clean compile package install` (probably not necessary for all these tags, but I like the pattern)

## Fourth-First step
Run the jar `java -jar dvt-whatsapp-business-demo-0.0.1-SNAPSHOT.jar`

## Conclusion-First
This should start the producer and rest controller up so to publish a message simply
navigate to your browser and run `http://localhost:8080/dvt-rabbitmq/producer?message={your-message-goes-here}&code={just-a-code-to-identify-your-message}`

The `code` portion of the call isn't used for anything yet, however I thought it wise to include it in case I need it later

This part surfaces a Rest API to send messages to a RabbitMQ topic that the second part (below) uses

## Second-First step
Pull origin/consumer to a different folder (https://github.com/RuaanKlem/dvt-whatsapp-business-api-demo/tree/consumer)

## Second-Second step 
Build the jar `mvn clean compile package install` (probably not necessary for all these tags, but I like the pattern)

## Third-Second step
Run the jar `java -jar dvt-whatsapp-business-demo-consumer-0.0.1-SNAPSHOT.jar`

## Conclusion-Second
This should start the consumer portion of this project, simply have a look at the logs of the project as well as
`https://autochat.io/whatsapp-api-sandbox` logins are my email (DVT) and password will be sent upon request...(Security reasons, I guess)

You would probably notice that I only have unit tests in the producer portion as I felt it was a bit excessive to add tests to both
as they basically are two sides of the same coin

The cell number is hardcoded for now cause I can't figure out how to add new numbers to Autochat.io through the API and I didn't
want to "waste" time on that as it probably wouldn't matter if we use a different **real** provider like Twilio, but let me know
what you think and I could have a look at that as well