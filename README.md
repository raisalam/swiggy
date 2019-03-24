# Swiggy

Swiggy is online food order management build with the help of independent spring-boot microservice pattern  

## Getting Started
Each module is independent spring boot application and communicate to each other with the help of kafka. Checkout the project and import to IDE

### Prerequisites and Installing

1. Install and run zookepper and kafka
2. Install and run MSql. Make sure MySql binlog replication is on
3. Install and run eventuate cdc service 
4. Start each module one by one. Go to each module directory and execute below command

```
mvn spring-boot:run 
```

## Running the tests

Explain how to run the automated tests for this system

### Break down into end to end tests

Explain what these tests test and why

```
Give an example
```

### And coding style tests

Explain what these tests test and why

```
Give an example
```

## Deployment

Add additional notes about how to deploy this on a live system

## Built With

* [Eventuate](https://eventuate.io/) - The web framework used to develop microservice
* [Maven](https://maven.apache.org/) - Dependency Management
* [Kafka](https://kafka.apache.org/) - Used for building real-time data pipelines and intercommunication between services

## Contributing

Please read [CONTRIBUTING.md](https://gist.github.com/raisalam/edd68b6b209b0ac4ea5acdf2e1f9318c) for details on our code of conduct, and the process for submitting pull requests to us.

## Versioning


## Authors

* **Rais Alam** - *Contributor* - [RaisAlam](https://github.com/raisalam)
* **Amit Kumar** - *Contributor* - [RaisAlam](https://github.com/amitknain)

See also the list of [contributors](https://github.com/raisalam/swiggy/settings/collaboration) who participated in this project.

## License

This project is only for demonstration and learning purpose for implementation on microservice.
## Acknowledgments

* Coding example and convetion was use from open source project **Eventuate**
* Inspired by **Chris Richardson**
* etc
