# CatalogService
Create a catalog service for a webshop system. This catalog service will expose resources for adding, updating and deleting web shop items and searching for an item. Service will expose a restful API with JSON formatted messages.

Postman collection to use with API: https://www.getpostman.com/collections/44c1546bc85a27460bdf

You can use docker to test the app with using docker-compose file.
But after you use docker to make both app and mysql database.
You will need to uncomment the mysql required parameters in application.yml file.
Import V1__initOfDatabase.sql in order for app to work via terminal on docker mysql image using next command:

`mysql -u root -p test < V1__initOfDatabase.sql`
