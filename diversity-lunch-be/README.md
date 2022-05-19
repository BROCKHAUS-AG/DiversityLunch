# Diversity Lunch Spring Boot Backend

Neues Backend für die Diversity Lunch App. \
Aktuelle Stände (Swagger Docs):
- Dev: https://dev-diversitylunch.brockhaus-ag.de/swagger-ui/index.html#/
- Main: Missing
--------------------

## IntelliJ
### 1. Bauen
Über das Maven Tool-Window oder über `./mvnw package` kann das Projekt kompiliert, getestet und gepackaged werden.

### 2. Ressourcen
Über das Docker Compose File kann eine Datenbank und ein SMTP-Server für die Entwickelung gestartet werden.
Der Befehl ``docker-compoise up -d db`` kann für die Datenbank verwendet werden 
und der Befehel ``docker-compose up -d mailhog`` für den SMTP-Server.

Die Application.yml unter ``src/main/resources`` muss für die lokale Entwickelung angepasst werden.
Dafür muss der auskommentierte Teil unter ``# Mail Settings Local`` eingefügt werden und der Teil unter ``# Mail Setting Sendgrid``
auskommentiert werden.

**!Wichtig!:** Für die Dev-Umgebung muss SendGrind wieder eingefügt werden.

**Disclaimer:** Eine lokale application.yml muss noch eingeführt werden. 

### 3. Run / Debug
Es muss eine neue Spring Boot Config angelegt werden, welche auf die Klasse `de.brockhausag.diversitylunchspringboot.DiversityLunchSpringBootApplication` verweist.
Zum starten muss die Datenbank laufen (kann über Docker Compose gestartet werden).
Damit das Backend sich mit der DB verbinden kann, müssen in der IntelliJ Config die Environment Variablen so gesetzt werden, wie sie in der Datei ``docker/.env`` eingetragen sind.
Zusätzlich muss die environment variable DB_HOST=localhost gesetzt werden.

### 4. Testen
#### Unit und Integration Tests
Die Tests liegen unter ``src/test/``. <br>
Für die Coverage sollte der Coveragerunner in IntelliJ auf JaCoCo umgestellt werden.

------------------------

## Docker
### 1. Dockerfile
### 2. Docker Compose
Die Docker Compose `docker/docker-compose.yaml` kann genutzt werden, um Datenbank und Backend lokal zu starten.
Damit die Container richtig konfiguriert werden muss es eine Datei mit Environment Variablen unter dem Pfad 
`docker/.env` geben, in der die Variablen `POSTGRES_USER`, `POSTGRES_PASSWORD` und `POSTGRES_DB` gesetzt sind.


## Kubernetes

Wir empfehlen ein Kubernetes Cluster zum Deployen.

Weiter Informationen für BAG Mitarbeiter: https://dev.azure.com/brockhaus-ag/Diversity-Lunch/_wiki/wikis/Diversity-Lunch.wiki/2857/Kubernetes 

-------------------------

## CI / CD
Die CI Pipeline läuft automatisch bei Pushed auf ``dev`` und baut ein Docker Image, welches in unsere Registry geladen wird.
Die CD Pipeline läuft automatisch nachdem die CI Pipeline durchgelaufen ist und deployed unsere Kubernetes Configs in unser Cluster.

-------------------------
