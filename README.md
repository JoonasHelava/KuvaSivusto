# KuvaSivusto
Web server programming practical work
# Info
This project was made to simulate very simple version of promotion algorithm. Promotion algorithm is working as a kind of feedback loop, where content that is getting popular (votes or comments) will be showing in frontpage and getting more views via that.
The code can be modified to promote any kind of content, not only pictures like in this use case example.
# Getting Started
Download the code or deploy straigth to hosting site.

Using NetBeansIDE:
Open the project in IDE. Run build one time to download all the depencies. Now you are ready to run the code, press "Run Project" or F6 short cut. The main file is KuvaSivustoApplication.java. You can also start the code by running this file.
# The Code
The project is using Spring Boot as a framework.
Thymeleaf as a template engine.
H2 as the database when running locally and PostgreSQL when in production use.
Project also contains depencies for Lombok (faster code writing) and JPA (management of relational data).

The code is build around the FileObject class. FileObjects ID key is used to track everything else.
When new vote is given or comment added, that object gets the FileObjects key and is used keep count of votes and comments per FileObject.

Projects main routes are
    "/pictures" , directs you to first picture. GET, POST
    "/addpicture", here you can add the picture. The form´s POST request is send to /pictures. GET
    "/voteplus/{FileObject_ID}", adds one to plus votes. GET
    "/voteminus/{FileObject_ID}", adds one to minus votes. GET
    "/pictures/{FileObject_ID}", shows one picture at a time. GET
    "/pictures/{FileObject_ID}/content", gives you the file's content. GET
    "/comments/{FileObject_ID}", adds comment from form. POST
# License
KuvaSivusto is published under the CC BY.

