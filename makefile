
APP_NAME = assessment
DOCKER_IMAGE_NAME = $(APP_NAME)-image
DOCKER_CONTAINER_NAME = $(APP_NAME)-container

.PHONY: build clean run

build:
	mvn clean package -DskipTests

clean:
	mvn clean

run:
	docker run -d -p 8080:8080 --name $(DOCKER_CONTAINER_NAME) $(DOCKER_IMAGE_NAME)

stop:
	docker stop $(DOCKER_CONTAINER_NAME)
	docker rm $(DOCKER_CONTAINER_NAME)
