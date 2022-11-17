FROM tomcat:9-jdk17-corretto


COPY ./target/javarush-quest.war /usr/local/tomcat/webapps/