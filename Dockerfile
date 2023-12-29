FROM tomcat:10.0.14-jdk17-openjdk

# Delete existing ROOT folder in Tomcat
RUN rm -rf /usr/local/tomcat/webapps/ROOT

# Copy the war file to the deployments directory and extract it
COPY ./target/*.war /usr/local/tomcat/webapps/ROOT.war
RUN mkdir -p /usr/local/tomcat/webapps/ROOT && unzip /usr/local/tomcat/webapps/ROOT.war -d /usr/local/tomcat/webapps/ROOT

# Copy ROOT.xml to the correct location in the Docker image
COPY ./ROOT.xml /usr/local/tomcat/conf/Catalina/localhost/ROOT.xml

# Run the web service on container startup.
CMD ["catalina.sh", "run"]
