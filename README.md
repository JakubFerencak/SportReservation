# Rezervačný Systém pre Športové Aktivity

**Tento projekt je REST API aplikácia vytvorená pomocou Spring Boot, ktorá slúži ako backend pre systém na rezerváciu športových aktivít. Aplikácia umožňuje plnú správu (CRUD - Create, Read, Update, Delete) používateľov, miest konania, športových aktivít a ich následných rezervácií.**

## Použité Technológie

* **Java 17****: Hlavný programovací jazyk**
* **Spring Boot 3.2.5****: Rámec pre zjednodušenie vývoja aplikácie**
* **Spring Data JPA (Hibernate)****: Pre prácu s databázou a mapovanie objektov na relačné tabuľky**
* **Maven****: Nástroj na správu závislostí a buildovanie projektu**
* **MySQL****: Relačná databáza na ukladanie dát**
* **SpringDoc OpenAPI (Swagger UI)****: Pre automatické generovanie a vizualizáciu API dokumentácie**
* **Lombok****: Knižnica na redukciu boilerplate kódu v entitách**

## Požiadavky na Spustenie

**Pred spustením projektu je potrebné mať nainštalované nasledujúce nástroje:**

* **JDK 17** alebo novšia verzia
* **Apache Maven**
* **MySQL Server**
* **Git** (voliteľné, pre klonovanie repozitára)
* **IDE podľa preferencie (napr. IntelliJ IDEA, VS Code)**
* **Nástroj na testovanie API (napr. Postman)**

## Inštalácia a Spustenie

**Postupujte podľa nasledujúcich krokov na lokálne spustenie aplikácie:**

**1. Klonovanie Repozitára**

```
git clone <URL_adresa_vashto_repozitara>
cd SportReservation

```

**2. Nastavenie Databázy**

* **Spustite MySQL server.**
* **Pripojte sa k nemu pomocou klienta (napr. MySQL Workbench, DBeaver).**
* **Spustite priložený SQL skript, ktorý vytvorí databázu **`<span class="selected">sport_reservation_db</span>`, používateľa `<span class="selected">mysqluser</span>` s potrebnými právami a naplní tabuľky počiatočnými dátami.

**3. Konfigurácia Aplikácie**

* **Overte, či sú údaje pre pripojenie k databáze v súbore **`<span class="selected">src/main/resources/application.properties</span>` správne: `<span class="selected">properties spring.datasource.url=jdbc:mysql://localhost:3306/sport_reservation_db?useSSL=false&serverTimezone=UTC spring.datasource.username=mysqluser spring.datasource.password=mysqluser </span>`

**4. Spustenie Aplikácie**

* **Aplikáciu môžete spustiť priamo z vášho IDE (spustením hlavnej triedy **`<span class="selected">SportReservationApplication.java</span>`).
* **Alebo pomocou Mavenu cez príkazový riadok z koreňového priečinka projektu: **`<span class="selected">bash mvn spring-boot:run </span>` Aplikácia by sa mala spustiť na porte **8081****.**

## API Dokumentácia

**Po úspešnom spustení aplikácie je interaktívna API dokumentácia (Swagger UI) dostupná na nasledujúcej adrese:**

[http://localhost:8081/swagger-ui.html](http://localhost:8081/swagger-ui.html "null")

**V tomto rozhraní je možné prehľadne vidieť všetky dostupné API endpointy, ich parametre, očakávané vstupy a priamo ich aj testovať.**

## Prehľad API Endpointov

### Používatelia (`<span class="selected">/users</span>`)

* `<span class="selected">POST /users</span>` - Vytvorí nového používateľa.
* `<span class="selected">GET /users</span>` - Získa zoznam všetkých používateľov.
* `<span class="selected">GET /users/{id}</span>` - Získa detaily konkrétneho používateľa.
* `<span class="selected">PUT /users/{id}</span>` - Aktualizuje údaje existujúceho používateľa.
* `<span class="selected">DELETE /users/{id}</span>` - Zmaže používateľa a všetky jeho rezervácie.

### Miesta Konania (`<span class="selected">/locations</span>`)

* `<span class="selected">POST /locations</span>` - Vytvorí nové miesto konania.
* `<span class="selected">GET /locations</span>` - Získa zoznam všetkých miest.
* `<span class="selected">GET /locations/{id}</span>` - Získa detaily konkrétneho miesta.
* `<span class="selected">PUT /locations/{id}</span>` - Aktualizuje údaje existujúceho miesta.
* `<span class="selected">DELETE /locations/{id}</span>` - Zmaže miesto a všetky aktivity, ktoré sa na ňom konajú.

### Aktivity (`<span class="selected">/activities</span>`)

* `<span class="selected">POST /activities</span>` - Vytvorí novú športovú aktivitu.
* `<span class="selected">GET /activities</span>` - Získa zoznam všetkých aktivít.
* `<span class="selected">GET /activities/{id}</span>` - Získa detaily konkrétnej aktivity.
* `<span class="selected">PUT /activities/{id}</span>` - Aktualizuje údaje existujúcej aktivity.
* `<span class="selected">DELETE /activities/{id}</span>` - Zmaže aktivitu a všetky jej rezervácie.

### Rezervácie (`<span class="selected">/reservations</span>`)

* `<span class="selected">POST /reservations</span>` - Vytvorí novú rezerváciu pre používateľa na danú aktivitu.
* `<span class="selected">GET /reservations</span>` - Získa zoznam všetkých rezervácií v systéme.
* `<span class="selected">PUT /reservations/{id}</span>` - Aktualizuje čas existujúcej rezervácie.
* `<span class="selected">DELETE /reservations/{id}</span>` - Zmaže konkrétnu rezerváciu.
* `<span class="selected">GET /users/{userId}/reservations</span>` - Získa všetky rezervácie pre konkrétneho používateľa.
* `<span class="selected">GET /activities/{activityId}/reservations</span>` - Získa všetkých účastníkov (rezervácie) pre konkrétnu aktivitu.
