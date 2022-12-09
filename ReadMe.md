# User Management Service
### what does it do?
This service manages authentication and profile management of users. Users can see other users in the system and also view their details.
Operations like profile update and account deletion are also supported.

### what's the purpose?
Well, nothing serious, this is a pet project to flex my springboot skills.

## How to install?
- Make sure you have Java 17 installed on your laptop.
- Install mysql on port 3306
- Clone repository to your system.
- IntelliJ is recommended
- Install pom dependencies
- Run flyway migration ( this will create the required schemas, table, and stored procedures)
- Run the app

## Other things to note?
- At the moment, the application doesn't contain any data, so you're to signup and create as many account as you wish.
- Link to system design - https://lucid.app/lucidchart/efb76d1e-d483-4e66-b9b1-efca8c945e9c/edit?viewport_loc=-213%2C-287%2C3335%2C1763%2C0_0&invitationId=inv_6570b5e3-fcf1-4b1a-bc0b-28bd8f6fbca7
- link to documentation - https://documenter.getpostman.com/view/14290929/2s8YzS1j9H
- Make sure to run flyway migration before starting the app, you can find that under maven plugins.