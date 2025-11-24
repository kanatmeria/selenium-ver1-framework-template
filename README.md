Automated UI Test Framework
Built with:

Java 17+

Selenium WebDriver

JUnit 5

WebDriverManager

Jackson (for JSON data)

Log4j2

Allure Reports (optional)

Project Structure
src
├── main
│   └── java
│       └── org.example
│           ├── base/
│           │    ├── BasePage.java
│           │    └── BaseTest.java
│           ├── pages/
│           └── utils/
└── test
├── java/org/example/tests/
└── resources/
├── configs/
│    ├── config.dev.properties
│    ├── config.stage.properties
│    └── config.prod.properties
└── testdata/
├── gazprom_accounts.json
└── utilities_accounts.json

Configuration
Environment Configs

Located in:

src/test/resources/configs/


Each file contains environment-specific properties.

Example — config.stage.properties:

#1. prod URL
base.url=https://e-meria.kg/

#2. staging URL
#base.url=https://e.meria.kg/

#3 transport-map page
transport.map.url=https://e-meria.kg/transport-map

#4 evakuator page
evacuator.url=https://e-meria.kg/evacuator

#5 license plate
license.plate.no.tickets=01KG000XAM

#6 Evacuator admin WebSite
evacuator.admin.url=https://evacuator.meria.kg/login

#7 Evacuator admin WebSite login
evacuator.admin.login=ADMIN_LOGIN

#8 Evacuator admin WebSite password
evacuator.admin.password=ADMIN_PASSWORD

#10 Evacuator admin WebSite url home page
evacuator.admin.home.page.url=https://evacuator.meria.kg/superadmin/TowTruck

#11 Real Estate Tax URL
tax.calculation.categories.url=https://e-meria.kg/tax-calculation/category

#11 Utilities page
utilities.url=https://e-meria.kg/utilities




Switch environment via CLI:

mvn test -Denv=prod
mvn test -Denv=stage
mvn test -Denv=dev


Default environment → stage.

Browser Selection

Available browsers:

 Chrome (default)

 Firefox

 Edge

Set via:

mvn test -Dbrowser=edge


or inside the .properties file:

browser=firefox

 Running Tests

Run all tests:

mvn test


Run a specific class:

mvn -Dtest=UtilitiesPageTest test


Run with environment:

mvn test -Denv=prod -Dbrowser=edge
 
JSON Test Data

Stored under:

src/test/resources/testdata/


Example:

[
{
"personalAccount": "123456789",
"typeOfUtilities": "gazprom",
"expectedCompanyName": "«Газпром Кыргызстан» ЖЧКсы",
"expectedAccountNumber": "123456789"
}
]


Used with:

List<GaspromAccountData> testData =
JsonDataReader.readList("testdata/gazprom_accounts.json", UtilitiesAccountData.class);

Base Framework Components
Component	Description
BaseTest	Initializes WebDriver and manages setup/teardown
BasePage	Provides reusable Selenium actions (click, type, wait, refresh, scroll)
ConfigReader	Loads environment configuration dynamically
JsonDataReader	Reads test data from JSON files
Page Objects	Encapsulate UI elements and interactions
Tests	Contain assertions and workflows
Environment Switching Logic

Automatically determined from system property:

System.getProperty("env", "stage");


Expected file names:

config.dev.properties
config.stage.properties
config.prod.properties


Stored under /resources/configs/.

 Logging

All browser actions are logged using Log4j2.
Typical output:

[18:13:25] INFO  MeriaPage - Clicking on element: //a[@href='/utilities']
[18:13:26] DEBUG UtilitiesPage - Text from WebElement: 'Бишкек'

🧹 Common Maven Commands
Command	Purpose
mvn clean	Clean previous builds
mvn test	Run all tests
mvn test -Denv=stage	Run in staging
mvn test -Denv=prod -Dbrowser=edge	Run in prod on Edge
mvn surefire-report:report	Generate basic HTML report
allure serve target/allure-results	Generate Allure Report

👤 Author

Kanat
Java | Selenium | Automation QA Engineer