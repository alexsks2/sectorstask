### IN ORDER TO RUN APPLICATION:
1. Clone git repository from https://github.com/alexsks2/sectorstask
2. Start a postgres instance locally or in docker. 
   To deploy docker container execute command in the project root:
    ```` cmd
    docker-compose up -d
    ````
3. Run the application by executing command
    ```` cmd
    ./mvnw spring-boot:run
    ````
   Or if Maven is installed locally
    ```` cmd
    mvn spring-boot:run
    ````
4. Follow this link in browser: http://localhost:8080
5. Click SAVE button to export data from index file to database. 