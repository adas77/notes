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
- [https://medium.com/shoutloudz/spring-boot-upload-and-download-images-using-jpa-b1c9ef174dc0](https://medium.com/shoutloudz/spring-boot-upload-and-download-images-using-jpa-b1c9ef174dc0)

## Dane logowania
Użytkownicy w bazie (u=username,p=pass)

```
String u1 = "qwertyuiop123456789";
String e1 = "qwertyuiop@gmail.com";
String p1 = "!as#(Hseh5ejddBBvdSFseg%%g";
String u2 = "_" + u1;
String e2 = "_" + e1;
String p2 = "_" + p1;
```