#Hibernate Lambda Example
Simple example of using Hibernate within an AWS Lambda function.  Example payloads for the POST and PUT methods can be
found in the Objects folder.

## Configuration
A settings.yaml file must be created under the /config folder in this
project. Populate that file with the following configuration:

    connection: connection string to your server/database
    username: your username
    password: your password
    
Once configured uncomment the following line within the persistence.xml file

```
<!--<property name="hibernate.hbm2ddl.auto" value="create"/>-->
```

On first load the tables will be created.

## Build

```
mvn clean package
```
