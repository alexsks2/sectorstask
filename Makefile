build:
	cd be  && ./gradlew build
	cd ..
	docker-compose up -d
