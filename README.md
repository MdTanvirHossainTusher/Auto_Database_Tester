# Database testing project

## Problem Statement
The goal of this project is to run a Test to this [website](https://www.euronews.com/). After successfully running the test case, I will fetch some data from the previous test and add those as a 
record in the database. After this, I did some certain operatino such as add, update, delete and make validation of those operations.

## To build and run the project

1. Clone the repository by this command-
   `git clone https://github.com/MdTanvirHossainTusher/DB.git`

2. Build the `pom.xml` file. If any dependencies requires updated version, then go to [this website](https://mvnrepository.com/). Search for those particular dependencies and update the dependencies
   version no.

3. Add you Gmail credentials on `ClientCredentials.java` file.
4. Add database name and authenticators on `ConfigData.json` which is located here - `src/test/resources/testdata/ConfigData.json`.
5. Run the `WebApiTest.java` testcase first.
6. Check whether a record with the information of `WebApiTest.java` testcase added or not. If testcase runs succesfully, a record should be added.
7. After successfully running `WebApiTest.java`, now run `DBTest.java` file. 
