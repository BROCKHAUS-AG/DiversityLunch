<h1>Index</h1>

1. Setting up the app
    - 1.1 Adding customers to project
    - 1.2 Setup Authentication
        - 1.2.1 Backend 
        - 1.2.2 Frontend
    - 1.3 Build and start the app
2. Used Technologies
3. Getting started with create react app
4. Fronted - Available scripts
5. Backend - IntelliJ
6. Liquibase


<h1>1. Setting up the app</h1>
<h2>1.1 Adding customers to project</h2>

To add a list of customers to Diversity Lunch, you have to edit the file `customers.txt` located
in `Diversity-Lunch-App`. Take your list and paste it into the file, **with one customer a line**.

Afterwards start `AddCustomersToProject.js`. This file takes care of adding the customers to all necessary files.

You can start the script with the console by typing `node AddCustomersToProject.js`. Assure that you are
located in `Diversity-Lunch-App` where `diversity-lunch-be` and `diversity-lunch-fe` are located.

**Be aware to leave an entry `Sonstiges` in `customers.txt`. The reason for this is that the tests
need this entry to work properly.**

If you dont want to have an entry like this you have to edit the tests
in `diversity-lunch-fe` in `ProfileOverview.test.tsx`. Under `projects:'Sonstiges'` you have to replace `Sonstiges`
with one customer/ project thats inside `customers.txt`.


<h2>1.2 Setup authentication<h2>

<h3>1.2.1 Backend Configuration</h3>
A `.env` file is required for the local installation of the backend. A sample file is located in the subfolder  `docker`. Creates the `.env` file in the same place and populate it with the following content:

```
POSTGRES_USER=exampleUser
POSTGRES_PASSWORD=examplePassword
POSTGRES_DB=exampleDBName
DB_HOST=IP-Address of DBHost eg. localhost
MAIL_HOST=IP-Address of MailHost
MAIL_PORT=1025
MAIL_USERNAME=exampleUser
MAIL_PASSWORD=examplePassword
MAIL_ADDRESS=exampleMail
ISSUER_URI=https://sts.windows.net/TENANT_ID/`
TENANT_ID=your Tenant ID
CLIENT_ID=your Client ID
CLIENT_SECRET=your Client Secret
DIVERSITY_LUNCH_USER_ID=your Diversity Lunch User ID
```



<h3>How to set the Ids?</h3>

We use azure active directory for authentication. 
To get the required ids you have to go into your azure account to your application.
You have to copy and paste the **application-id** to `CLIENT_ID`, **directory-id** to `TENANT_ID`,
and your **private key** to `CLIENT_SECRET`. To get the `ISSUER_URI` you have to concatenate
the `TENANT_ID` to `https://sts.windows.net/`.
<br/>
<br/>
Example:
`ISSUER_URI=https://sts.windows.net/TENANT_ID/`

The `DIVERSITY_LUNCH_USER_ID` is required to send emails to users after a match.
You have to create a technical user in your azure app. The technical users task is to
send notification-mails to each user of a match.<br/>
To get the `DIVERSITY_LUNCH_USER_ID` just copy the **Object-Id** from your technical user and
paste it to `DIVERSITY_LUNCH_USER_ID`.


With the plugin [EnvFile](https://plugins.jetbrains.com/plugin/7861-envfile) IntelliJ can set the file as an environment for the run time configuration:
![image.png](.attachments/image-0ea4fb06-178f-4e0f-9535-5294d87eab38.png)



<h3>1.2.2 Frontend Configuration</h3>

To configure this, the file 'app-config.js' must be created in the folder 'Public/config' and the following content must be added:
```javascript
    window.appConfig = {
       REACT_APP_OIDC_CLIENT_ID: 'your Client ID',
       REACT_APP_OIDC_SCOPE: 'your Client ID/.default openid profile email',
       REACT_APP_OIDC_CONFIG_ENDPOINT: 'Microsoft Endpoint Configuration',
       REACT_APP_OIDC_REDIRECT_URI: 'https://DomainOfYourSite.com/',
       REACT_APP_OIDC_AUTHORIZATION_ENDPOINT: 'Microsoft Endpoint Authorization',
    };
```

The client-Id ist the id from your App that you already used for the backend. <br/><br/>
`REACT_APP_OIDC_CLIENT_ID`: Your Client-Id.<br/><br/>
`REACT_APP_OIDC_SCOPE`: `REACT_APP_OIDC_CLIENT_ID/.default openid profile email`<br/><br/>
`REACT_APP_OIDC_CONFIG_ENDPOINT`: `https://login.microsoftonline.com/TENANT_ID/.well-known/openid-configuration` <br/><br/>
`REACT_APP_OIDC_REDIRECT_URI`: URL to your Diversity-Lunch-App<br/><br/>
`REACT_APP_OIDC_AUTHORIZATION_ENDPOINT`: `'https://login.microsoftonline.com/TENANT_ID/oauth2/v2.0/authorize` <br/><br/>



<h1>1.3 Build and start the app</h1>

<h2>1.3.1 Frontend</h2>
Go to the diversity-lunch-fe directory and run ```npm run build-fe-win``` on windows or ```npm run build-fe``` otherwise.

<h2>1.3.2 Backend</h2>
Using the Maven tool window or via `./mvnw package` the project can be compiled, tested and packaged.

<h2>1.3.3 Running the app</h2>
<h3>  Docker </h3>

<h3>  Docker Compose</h3>

The Docker compose 'docker/docker-compose.yaml' can be used to start the database, mailhog and backend locally.
Inside the folder ```DiversityLunch/diversity-lunch-be/docker``` you can run ```docker-compose build```, then ```docker-compose up```.

In order for the containers to be configured correctly, there must be a file with environment variables under the path
'docker/.env', in which the variables 'POSTGRES_USER', 'POSTGRES_PASSWORD' and 'POSTGRES_DB' are set.

Locally the app runs on http://localhost:8080


<h2>  Kubernetes</h2>

We recommend a Kubernetes cluster to deploy.




<h1> 2. Used Technologies</h1>
ReactJS, TypeScript, SwaggerUI, Jest, JavaScript, Redux, Sass, EsLint, Lombok, ModelMapper, H2 Database, Material, Java Spring Boot, Postgres, Docker, Kubernetes, Azure, Git
<br>
<h1> 3. Getting Started with Create React App</h1>

This project was bootstrapped with [Create React App](https://github.com/facebook/create-react-app).

<h1> 4. Frontend - Available scripts</h1>

In the project directory, you can run:

<h3>  

`npm start` 

</h3>

Runs the app in the development mode.\
Open [http://localhost:3000](http://localhost:3000) to view it in the browser.

The page will reload if you make edits.\
You will also see any lint errors in the console.

<h3>  

`npm test`

</h3>

Launches the test runner in the interactive watch mode.\
See the section about [running tests](https://facebook.github.io/create-react-app/docs/running-tests) for more information.

<h3>  

`npm run build`

</h3>

Builds the app for production to the `build` folder.\
It correctly bundles React in production mode and optimizes the build for the best performance.

The build is minified and the filenames include the hashes.\
Your app is ready to be deployed!

See the section about [deployment](https://facebook.github.io/create-react-app/docs/deployment) for more information.

<h3>  

`npm run eject`

</h3>

**Note: this is a one-way operation. Once you `eject`, you can’t go back!**

If you aren’t satisfied with the build tool and configuration choices, you can `eject` at any time. This command will remove the single build dependency from your project.

Instead, it will copy all the configuration files and the transitive dependencies (webpack, Babel, ESLint, etc) right into your project so you have full control over them. All of the commands except `eject` will still work, but they will point to the copied scripts so you can tweak them. At this point you’re on your own.

You don’t have to ever use `eject`. The curated feature set is suitable for small and middle deployments, and you shouldn’t feel obligated to use this feature. However we understand that this tool wouldn’t be useful if you couldn’t customize it when you are ready for it.

<h2>  Learn More </h2>

You can learn more in the [Create React App documentation](https://facebook.github.io/create-react-app/docs/getting-started).

To learn React, check out the [React documentation](https://reactjs.org/).

<h1> 5. Backend - IntelliJ </h1>

<h3>  1. build </h3>

Using the Maven tool window or via `./mvnw package` the project can be compiled, tested and packaged.

<h3>  2. resources </h3>

Via the Docker Compose file, a database and SMTP server can be started for development.
The command ``docker-compoise up -d db`` can be used for the database
and the command ``docker-compose up -d mailhog`` for the SMTP server.

The application.yml under ``src/main/resources`` must be adapted for local development.
For this the commented out part under ``<h1>  Mail Settings Local`` must be inserted and the part under ``<h1>  Mail Setting Sendgrid`` must be commented out.
must be commented out.

**!Important!:** For the Dev environment SendGrind must be inserted again.

**Disclaimer:** A local application.yml still needs to be introduced.

<h3>  3. run / debug</h3>

A new Spring Boot Config must be created, which points to the class `en.brockhausag.diversitylunchspringboot.DiversityLunchSpringBootApplication`.
To start, the database must be running (can be started via Docker Compose).
In order for the backend to connect to the DB, the environment variables must be set in IntelliJ Config as they are entered in the file ``docker/.env``.
Additionally the environment variable DB_HOST=localhost must be set.<br>
<h3>  4. Testing</h3>

<h3>Unit und Integration Tests</h3>
The tests are found under ''src/test/''. <br>
For coverage, the coverage runner in IntelliJ should be converted to JaCoCo.

------------------------



<h1> 6. Liquibase</h1>

More information at: https://www.liquibase.org/
``` 
url=jdbc:postgresql://localhost:5432/diversity-lunch
username=spring-backend
password=SomeRandomPassword
driver=org.postgresql.Driver
outputChangeLogFile=src/main/resources/db/changelog/migrations/init_db_migration.sql
changeLogFile=src/main/resources/db/changelog/db.changelog-master.yaml
```

For existing databases:<br>
`liquibase changelog-sync`<br>
More helpers to use Liquibase at<br>
`liquibase --help`<br>
Rough overview:<br>
`liquibase validate` validate<br>
`liquibase XXXX-sql` Test variant of the command. Always recommended to run beforehand:<br>
i.e.<br>
`liquibase update-sql` -> `liquibase update`<br>
`liquibase status` returns the status<br>
`liquibase rollback` rollback of changesets.<br>

Liquibase should be used as in Example Migration file.
1. One change per changeset.
2. Changesets as commented in example
3. Added rollback plan
4. Always write new migration files even if something is deleted from the db. -> Update, Age, Insert etc.
