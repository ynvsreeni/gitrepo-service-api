# gitrepo-service-api
gitrepo-service-api
Software Required
Java8 JDK or Java10 JDK (this project was built with jdk8)
Apache Maven

Run API Service Locally
Run the following in the root directory:

Windows
mvnw.cmd clean install
mvnw.cmd spring-boot:run
(we need modifiy the pom if any build issue wth docker related config)

API Spec: endpoints
 User repository info : localhost:8080/projects/{user}
 User repository activities: localhost:8080/projects/{user}/{repo}

References
Used GitHub API (The GitHub Java API (org.eclipse.egit.github.core) library is part of the GitHub Mylyn Connector and aims to support the entire GitHub v3 API. ) to get access repository.

CI:
 CircleCI used for Continuous integration- to build ,test and create docker image (moving to Docker hub) for every github repo code commits. It will send notification for actitivity(sucess/failure).
 https://circleci.com/
 https://cloud.docker.com
