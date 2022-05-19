# Diversity Lunch App Helm Chart

**Disclaimer** Die Docker Registry muss ausgetauscht werden damit die Charts aus einer offenen Docker Registry das Frontend und Backend runterladen kann.

Die Diversity Lunch App bietet Helm Charts an. 
Dieses ermöglicht das einfache Deployment der Anwendung in Ihrem Kubernetes Cluster.

Inhalt:
1. [Anleitung](#anleitung)
2. [Charts](#charts)
   1. [Storage Chart](#storage)
   2. [Anwendungs Chart](#anwendung)
   3. [Beispiele](#beispiele)

## Anleitung

Um die Anwendung mit den Helm Charts zu starten müssen folgende Schritte beachtet werden:

Für die Anwendung muss Ingress auf dem Cluster installiert werden.

1. Lege eine oder mehre `Values.yaml` an. Diese Yaml Dateien verwendet Helm, um die Defaultwerte zu überschreiben.
   1. Eine `Values.Yaml` für die StorageClass wird nur dann benötigt, wenn keine eigene StorageClass vorhandene ist oder eine neue angelegt werden soll.  
   2. Die `Values.Yaml` für die Anwendung benötigt die mit `Required` markierten Werte(&#10004;) überschrieben. 
      Felder die mit einer Blume(&#10047;) makiert sind sollten für die Funktionsfähigkeit ebenfalls überschrieben werden.
      **Achtung:** Felder aus untergeordneten Objekten gelten nur dann als `Required`, wenn das Objekt als `Required` markiert ist.
   3. Wurde der `storageClass.name` in den Storage Charts neu gesetzt so muss dieser auch in der Anwendung gesetzt sein.
2. [Optional] Starte die Storage Chart `helm install -f <<pathToStorageValueFile>> <<ReleaseName>> <<pathToCharts>>/DiverstiyLunchStorage`.
3. Starte die Anwendung `helm install -f <<pathToValueAppValueFile>> <<ReleaseName>> <<pathToCharts>>/DiversityLunchApp`
4. [Optional] Wird die Anwendung in einer lokalen Umgebung verwendet muss ein Port-forwarding aktiviert werden `kubectl port-forward service/ingress-nginx-controller 8080:80 -n ingress-nginx`
## Charts

### Storage

Dieses Chart ist optional.
Wenn Sie eine eigenes Persistence-Volume auf dem Cluster betreiben, kann die Datenbank an dieses angeschlossen werden.

#### Konfigurierbare Werte der des Storages

Zu überschreibende Werte der `Values.yaml` sind: 

| Key                        | Value    | Required | Beschreibung                                                                                                                  |
|----------------------------|----------|----------|-------------------------------------------------------------------------------------------------------------------------------|
| `storageClass.name `       | `string` | &#10006; | Gibt den Namen der StorageClass des Clusters an                                                                               |
| `storageClass.provisioner` | `string` | &#10006; | Gibt den Provisioner der StorageClass an                                                                                      |
| `persistentVolume.name`    | `string` | &#10006; | Gibt den Namen der PersistentVolumes an. Der Wert wird nur Verwendet wenn der Provisioner `kubernetes.io/no-provisioner` ist. |



### Anwendung

Verwenden Sie dieses Chart um die Anwendung auf dem Cluster zu releasen.
#### Konfigurierbare Werte der Anwendung
Zu überschreibende Werte der `Values.yaml` sind:

| Key                         | Value                      | Required | Beschreibung                                                                                                                                 |
|-----------------------------|----------------------------|----------|----------------------------------------------------------------------------------------------------------------------------------------------|
| `namespace`                 | `string`                   | &#10006; | Name des Namespaces der Verwendet werden soll.                                                                                               |
| `persitentVolumeClaim.name` | `string`                   | &#10006; | Name der PersistentVolumeClaims der von dem StatefulSet der Datenbank verwendet wird.                                                        |
| `storageClass.name`         | `string`                   | &#10006; | Name der StorageClass auf dem Cluster die verwendet werden soll.                                                                             |
| `db.username`               | `string`                   | &#10006; | Username des Datenbank Users der für die Anwendung verwendet werden soll.                                                                    |
| `db.password`               | `string`                   | &#10006; | Passwort des Datenbank Users.                                                                                                                |
| `db.dbname`                 | `string`                   | &#10006; | Name der Datenbank die auf dem Datenbank Pod erstellt werden soll.                                                                           |
| `configBackend.clientId`    | `string`                   | &#10004; | ClientId der Anwendung, mit der sie bei einem IDP registriert wurde. (unterstützt momentan nur Azure Active Directory)                       |
| `configBackend.tenantId`    | `string`                   | &#10004; | TenantId der Anwendung, mit der sie bei einem IDP registriert wurde. (unterstützt momentan nur Azure Active Directory)                       |
| `configBackend.issuerURI`   | `string`                   | &#10004; | Die URI mit der die Anwendung an den _.Well-Known_ Endpunkt des IDPs  erhält um zugriff auf die öffentlichen Signatur Schlüssel zu erhalten. |
| `configBackend.mailHost`    | `string`                   | &#10047; | Name/Ip des verwendeten Mail Servers. Beispiel: `smtp.example.com`                                                                           |
| `configBackend.mailPort`    | `number`                   | &#10047; | Port über den der Mail Dienst zu erreichen ist.                                                                                              |
| `configBackend.mailAddress` | `string`                   | &#10047; | Eine Email Adresse in dessen Namen Erinnerung Mails an die Anwender versendet werden.                                                        |
| `configFrontend.appConfig`  | `stirng / MutlilineString` | &#10004; | Der Inhalt des app-config.js mit den fürs Frontend benötigten Konfigurationen.                                                               |
| `smtp.username`             | `string`                   | &#10047; | Username mit dem sich die Anwendung bei dem Mailhost anmelden kann.                                                                          |
| `smtp.password`             | `string`                   | &#10047; | Passwort des Users mit dem sie die Anwendung bei dem Mailost anmeldet kann.                                                                  |
| `tcUser.userId`             | `string`                   | &#10047; | Technischer User der Termine für die Anwender erzeugen kann. Muss Mitglied des Azure Active Directories sein                                 |
| `tc.clientSecret`           | `string`                   | &#10047; | ClientSecret mit dem der Technische User als Teil der Anwendung bei Azure Active Directory Authentifizieren kann                             |
| `ingressController`         | `Range(Object)`            | &#10047; | Verschieden Ingress Controller die angewandt werden sollen.                                                                                  |

Der IngressController nimmt folgende _Key: Value_ Paare an: 

| Key     | Value    | Required | Beschreibung                  |
|---------|----------|----------|-------------------------------|
| ingress | `Object` | &#10004; | Ingress Konfigurations Object |

Das Ingress `Object` besteht aus folgenden _Key: Value_ Paare an:

| Key                  | Value           | Required | Beschreibung                                              |
|----------------------|-----------------|----------|-----------------------------------------------------------|
| metadata.name        | `string`        | &#10004; | Name der Konfiguration                                    |
| metadata.annotations | `Object`        | &#10006; | Annotationen die der Ingress Controller erhalten soll.    |
| tls                  | `Object`        | &#10047; | Die TlS Konfigurationen für die Hosts.                    |
| rules                | `Range(Object)` | &#10004; | Die Verschieden Hosts für die die Regeln angewandt werden |

Das `metadata.annotations` Object ist frei bestückbar und wendet alle Annotationen die hier Angeben auf die Ingress Konfiguration an.

Die `tls` Object besteht aus: 

| Key          | Value           | Required | Beschreibung                                                                |     |
|--------------|-----------------|----------|-----------------------------------------------------------------------------|-----|
| `hosts`      | `Range(string)` | &#10004; | Die verschiedenen Hostname auf die TLS Konfiguration angewandt werden soll. |     |
| `secretName` | `string`        | &#10004; | Name des Pods in dem das TLS Zertifikat/Secret hinterlegt wurde             |     |


Die `rules` Range der YAML beinhalten folgendes Object: 

| Key    | Value    | Required | Beschreibung                                                                             |     |
|--------|----------|----------|------------------------------------------------------------------------------------------|-----|
| `host` | `string` | &#10004; | Den Host Namen des Ingress Controllers für den die Rules aus dem Chart angewandt werden. |     |

## Beispiele:

### TLS
```yaml
tls: 
    - hosts:
        - localhost
    secretName: localhost-cert
```

### rules
```yaml
rules:
  - host: localhost
  - host: www.example.com
```

### appConfig
```yaml
configFrontend:
  appConfig: |+
    window.appConfig = {
            REACT_APP_OIDC_CLIENT_ID: 'client-i-d',
            REACT_APP_OIDC_SCOPE: 'client-i-d/.default openid profile email',
            REACT_APP_OIDC_CONFIG_ENDPOINT: 'https://login.microsoftonline.com/<<tenatnid>>/.well-known/openid-configuration',
            REACT_APP_OIDC_REDIRECT_URI: 'localhost',
            REACT_APP_OIDC_AUTHORIZATION_ENDPOINT: 'https://login.microsoftonline.com/<<tenantid>>/oauth2/v2.0/authorize',
       };
```


