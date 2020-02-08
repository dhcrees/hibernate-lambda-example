# Hibernate Lambda Example
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

Jackson specific annotations have been added to the Book (@JsonBackReference) and Author (@JsonManagedReference) models
to resolve the recursive error jackson was throwing when serialising the objects to Json.

If running in Intellij, use the EnvFile plug in to select the settings.yaml file and then execute
the JettyLocalRunner.java file.  API's will be available on http://localhost:8000

## Build

```
mvn clean package
```

## End Points
```
[POST] /1.0/author
[GET] /1.0/author
[GET] /1.0/author/{name}
[PUT] /1.0/book/add/{id}
[GET] /1.0/book
[GET] /1.0/book/{id}
```
