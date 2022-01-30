# Mancala Implementation
This is an implementation of the game Kalah aka Mancala, written using Java and Angular.

# Tech Stack
|  | Backend | Frontend |
|---|---|---|
| **Framework** | SpringBoot | Angular |
| **Build Tool** | Maven | Npm |
# Build
- Run `mvn clean build` in the root folder
- The front end code will allso be built as part of the maven build. And the produced artifacts will be copied to `/static` folder of the final fat Jar.
# Test
Run `mvn verify` in the root folder
# Run
- Run `mvn spring-boot:run` in the root folder
- Navigate to `http://localhost:8080`
- Open Api documentation is availabe under the URL `http://localhost:8080/api`
