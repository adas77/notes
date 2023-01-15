# spirng-template
## Specyfikacja
- Backend
  - `Java 17`
  - `Spring Boot` & `Spring Security`
- Frontend
  - `...`
- DB
  - `POSTGRESQL`
- Server
  - `Nginx`
- Run
  - `Docker` & `docker-compose`

## Konfiguracja
File: `.env`
## Run `docker-compose`
With `Makefile`:
- `make build` budowanie kontenerów
- `make up` uruchomienie kontenerów
- `make db` połączenie z bazą danych `POSTGRES` przez konsolę
## Bibliografia
- [https://jwt.io/](https://jwt.io/) jwt
- [https://www.baeldung.com/spring-security-registration-password-encoding-bcrypt](https://www.baeldung.com/spring-security-registration-password-encoding-bcrypt) bcrypt
- [https://www.baeldung.com/java-aes-encryption-decryption](https://www.baeldung.com/java-aes-encryption-decryption) aes
- [https://www.npmjs.com/package/sanitize-html](https://www.npmjs.com/package/sanitize-html) sanitaze
- [https://www.baeldung.com/sql-injection](https://www.baeldung.com/sql-injection) hibernate sql injection
- [https://stackoverflow.com/questions/73617743/is-springboot-data-jpa-repository-safe-against-sql-injection](https://stackoverflow.com/questions/73617743/is-springboot-data-jpa-repository-safe-against-sql-injection) jpa interface injection
- [https://security.snyk.io/vuln/SNYK-JS-QUILL-1245047](https://security.snyk.io/vuln/SNYK-JS-QUILL-1245047) quill lib vulnerabilities
- [https://security.snyk.io/package/npm/quill](https://security.snyk.io/package/npm/quill) quill lib vulnerabilities
- [https://generatepasswords.org/how-to-calculate-entropy/](https://generatepasswords.org/how-to-calculate-entropy/) password strength
- [https://github.com/danielmiessler/SecLists/blob/master/Passwords/Common-Credentials/10k-most-common.txt](https://github.com/danielmiessler/SecLists/blob/master/Passwords/Common-Credentials/10k-most-common.txt) most common passwords
- [https://www.learnbestcoding.com/post/17/ssl-https-with-nginx](https://www.learnbestcoding.com/post/17/ssl-https-with-nginx) nginx conf
- [https://raw.githubusercontent.com/react-boilerplate/react-boilerplate/master/app/.nginx.conf](https://raw.githubusercontent.com/react-boilerplate/react-boilerplate/master/app/.nginx.conf) nginx conf
- [https://stackoverflow.com/questions/21113154/spring-boot-ddl-auto-generator](https://stackoverflow.com/questions/21113154/spring-boot-ddl-auto-generator) hibernate run