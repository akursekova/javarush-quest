# javarush-quest

Deploy war file on Tomcat Docker Container
- Install [Docker](https://docs.docker.com/install).
- Clone this repository $git clone https://github.com/akursekova/javarush-quest.git
- cd javarush-quest # from your root directory
- $docker build -t javarush-quest .
- $docker run -p 7777:8080 javarush-quest
- http://localhost:7777/javarush-quest/quest