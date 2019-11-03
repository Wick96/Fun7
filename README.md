#### REQUIREMENTS:
* Java SDK 11
* Maven 3.6.*
* App Engine DataStore

#### LOCAL BUILD:
1. [Create App Engine Project] & [DataStore database]
2. [Auth your local machine]
3. fix API credentials `USERNAME` & `PASSWORD` in `com.luka.fun7.AdsFeature` 
2. Go to project folder (`fun7`)
3. run `mvn package`
4. run `java -jar target\fun7-1-jar-with-dependencies.jar`
5. Endpoint is now live on http://localhost:4567/features

#### HOW TO USE IT?

###### Sending params:
* Simple add `?cc=XX&userId=XXXX&timezone=XXXX` to url

#### UNCOMPLETED TASKS AND PROBLEMS:
Im very sorry, but there is only one example of JUnit tests (`UserSupportFeatureTest`). 
I had problems with mocking SparkJava Request that my other classes uses.
I could test some private methods like `AdsFeature.areAdsAvailable`,
but that means i need to turn them public for no reason except tests.
 

[DataStore database]: https://cloud.google.com/datastore/docs/quickstart#create_a_database
[Create App Engine Project]: https://cloud.google.com/appengine/docs/standard/java11/building-app/creating-project
[Auth your local machine]: https://cloud.google.com/docs/authentication/getting-started