Ensure that Java 8 is downloaded: https://www.java.com/download/ie_manual.jsp

To load the project, download 'ThreThousendFiveA3Q1' from the github repo and open 
it in IntelliJ (to download Intellij: https://www.jetbrains.com/idea/download/?section=windows)

Maven should download all of the dependencies

To load the database, download 'SchemaMakerIntoTabler.txt', open pdAdmin 4 and create a new 
databases called 'Students'. Right click on this database and choose query tool and then 
copy paste all the text in 'SchemaMakerIntoTabler.txt' into this and press the run button. 
The database will now be created an filled with a table called 'students' and some initial data.

In ThreThousendFiveA3Q1/src/main/java/Main.java, change the Strings user and password to match with the 
username and password associated with your PostgreSQL server.

Hit the green run button to run the code and view the output in the console.