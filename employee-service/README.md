## Kafka Startup in local

### Start Zookeeper Server

`sh bin/zookeeper-server-start.sh config/zookeeper.properties`

### Start Kafka Server / Broker

`sh bin/kafka-server-start.sh config/server.properties`

### Create topic

`sh bin/kafka-topics.sh --bootstrap-server localhost:9092 --create --topic NewTopic --partitions 3 --replication-factor 1`

### list out all topic names

`sh bin/kafka-topics.sh --bootstrap-server localhost:9092 --list`

### Describe topics

`sh bin/kafka-topics.sh --bootstrap-server localhost:9092 --describe --topic NewTopic`

### Produce message

`sh bin/kafka-console-producer.sh --broker-list localhost:9092 --topic NewTopic`

### consume message

`sh bin/kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic NewTopic --from-beginning`