# Steps

* [Application](https://github.com/beeNotice/hello-prod)
    * [Create Spring boot Application](https://spring.io/projects/spring-boot)
    * [Actuator](https://docs.spring.io/spring-boot/docs/current/reference/html/production-ready-features.html)
* [Initialisation de l'infrastructure](https://github.com/beeNotice/hello-prod-deploy)
    * https://github.com/terraform-providers/terraform-provider-azurerm/tree/master/examples
* Déploiement
    * GitHub Actions
        *  Configuration de la clé de déploiement
            * Azure > App Service > Deployment Center > Manage publish profile > Download
            * GitHub > repo > Settings > Secrets > dev > AZURE_WEBAPP_PUBLISH_PROFILE
    * https://github.com/actions/starter-workflows/blob/main/ci/azure.yml

# Application

## Decisions

* Utilisation de logback-spring.xml pour la configuration des logs plutôt via via application.yml / Séparation configuration des logs
    * https://docs.spring.io/spring-boot/docs/current/reference/html/spring-boot-features.html#boot-features-logging
* Utilisation du profile spring pour la gestion des configurations d'environnement / Fonctionnement spring-boot
    * https://docs.spring.io/spring-boot/docs/current/reference/html/spring-boot-features.html#boot-features-external-config
* Utilisation .yml au lieu de .properties, question de goût !
    * https://docs.spring.io/spring-boot/docs/current/reference/html/spring-boot-features.html#boot-features-external-config-yaml 
  
    
## Usage

```
# Hello
curl --location --request GET 'http://localhost:8080/hello-world?name=Lol'

# Actuator
curl --location --request GET 'http://localhost:8080/management'
```

# Sizing

* Sur la plan App Service doit être au moins en Standard pour avoir accès au déploiement continu


# Annexes
## Documentation

* [Architecture de référence](https://docs.microsoft.com/fr-fr/azure/architecture/reference-architectures/app-service-web-app/basic-web-app)
* [Exemples Terraform Azure](https://github.com/terraform-providers/terraform-provider-azurerm/tree/master/examples)


# TODO

* Excel des conventions Azure
* Convention nomage Github actions
* Coûts de build Github Actions
* Vérifier tous les paramètres spring-boot par à activer sur production
* Sécurisation heatlh

