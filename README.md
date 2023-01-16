# spirng-template
## Specyfikacja
- Backend
  - `Java 17`
  - `Spring Boot` & `Spring Security`
- Frontend
  - `React TS`
- DB
  - `POSTGRESQL`
- Server
  - `Nginx`
- Run
  - `Docker` & `docker-compose`

## Konfiguracja
Dodać do `etc/hosts`: `127.0.0.1	notes.pl`

## Run `docker-compose`
`Makefile`:
- `make b` lub `docker-compose -f docker-compose.final.yml build` budowanie kontenerów
- `make up` lub `docker-compose -f docker-compose.final.yml up`   uruchomienie kontenerów
- `make db` lub `docker-compose --f docker-compose.final.yml run apdibi bash -c "psql -h apdibi -d pilewski-baza -U postgres"` połączenie z bazą danych `POSTGRES` przez konsolę
- do `/etc/hosts` dodać `127.0.0.1	notes.pl`
- Link do strony: `https://notes.pl/notes` lub `https://notes.pl/notes/login`

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
- - [https://generatepasswords.org/how-to-calculate-entropy/](https://generatepasswords.org/how-to-calculate-entropy/) password strength
- [https://github.com/danielmiessler/SecLists/blob/master/Passwords/Common-Credentials/10k-most-common.txt](https://github.com/danielmiessler/SecLists/blob/master/Passwords/Common-Credentials/10k-most-common.txt) most common passwords
- [https://www.learnbestcoding.com/post/17/ssl-https-with-nginx](https://www.learnbestcoding.com/post/17/ssl-https-with-nginx) nginx conf
- [https://raw.githubusercontent.com/react-boilerplate/react-boilerplate/master/app/.nginx.conf](https://raw.githubusercontent.com/react-boilerplate/react-boilerplate/master/app/.nginx.conf) nginx conf
- [https://stackoverflow.com/questions/21113154/spring-boot-ddl-auto-generator](https://stackoverflow.com/questions/21113154/spring-boot-ddl-auto-generator) hibernate run
- [http://nginx.org/en/docs/http/ngx_http_ssl_module.html#ssl_password_file](http://nginx.org/en/docs/http/ngx_http_ssl_module.html#ssl_password_file) nginx ssl pass
- [https://stackoverflow.com/questions/67525595/cross-origin-request-blocked-the-same-origin-policy-disallows-reading-the-remot](https://stackoverflow.com/questions/67525595/cross-origin-request-blocked-the-same-origin-policy-disallows-reading-the-remot)

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
