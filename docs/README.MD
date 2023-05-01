# Event Based Architecture

## kafka
- topics
  - eba_topic/EBA.ORDER
  - eba_topic/EBA.PAYMENT
  - eba_topic/EBA.REPORT
  - eba_topic/EBA.NOTIFY
  - eba_topic/EBA.BILL
## client

## api gateway
- to avoid client to call with host:port and use domain name 

## foundation
common code packaged as jar
- call http end points
- publish message to event service

## event
- creates events 
- 
## order service
- Publish message to event service when it is up or down
- Creates an order
- saves order to database.
- Publish message for an order that is created

## payment service
- Publish message to event service when it is up or down
- Creates an payment
- saves order to database.
- Publish message for an order that is created
## reporting service
- reports to another system of the order and payment
- 
## notification service
- Sends notification to client

## billing service
- creates a bill after creation of order
- runs every month to recreate bill if there is a balance
## event service
Captures events from each service and writes it to db

## apache jmeter
load test client