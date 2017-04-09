# Software Needs You
[![CircleCI](https://circleci.com/gh/softwareneedsyou/softwareneedsyou/tree/develop.svg?style=shield)](https://circleci.com/gh/softwareneedsyou/softwareneedsyou/tree/develop)

Ce depôt constitue la partie logiciel du projet [Software Needs You](https://github.com/softwareneedstou).
La partie serveur est accessible sur [ce dépôt github](https://github.com/softwareneedsyou/softwareneedsyou-server)

## Setup
Ce projet utilise [gradle](https://gradle.org/) comme système de build.

Pour compiler et lancer le programme une fois le dépôt cloné:
```bash
gradle build run
```

## Git
Le  workflow git est le suivant:
```bash
# Clone du repo
git clone https://github.com/softwareneedsyou/softwareneeds.git
# Changement de branche
git checkout develop
# Création d'une branche pour la feature (à faire depuis la branche origin/develop)
git checkout -b <github_username>/<feature> #feature individuelle
git checkout -b feature/<feature>           #feature commune
```
Une fois la feature complétée, le merge sur la branche develop se fait via une [pull-request](https://github.com/softwareneedsyou/softwareneedsyou/pulls), et un membre de l'organisation review les changements avant d'accepter le merge.

* Les merges sur la branches develop sont en fast-forward
* Les merges sur la branche master sont ponctuels et manuels. Se seront des commits de rebase, c'est à dire qu'ils rassembleront tout les changements sous un seul commit.
  L'idéal est donc de les effectuer par changement de version mineure.
* Les features sont discutées et décrites sur [github](https://github.com/softwareneedsyou/softwareneedsyou/projects)
* Chaque pull-request répond à une issue qui correspond à une feature 

## Gradle
>Le fichier executable `gradlew` est un wrapper générer automatiquement par gradle qui permet de télécharger la
version utilisée lors de l'init pour qu'elle soit utilisée en local.

Une documentation exhaustive est disponible sur [leur site](https://docs.gradle.org/3.4.1/userguide/userguide.html).

Globalement, Gradle s'utilise avec des "tâches" : `gradle <task>`.

### Configuration
Lors de modifications de gradle, vérifier la configuration avec la commande `gradle` ou `gradle help` qui ne nécessite
aucune compilation.

### Liste des projets/modules
Pour lister les projets ou moudles gradle : ```gradle projects```.

### Libérer une release
Pour ```compile``` ou ```install``` une relase, il suffit d'ajouter ```release``` dans les tâches de gradle.
```bash
gradle <task(s)> release
```

### Tâches
Pour voir la liste de toutes les tâches possible avec gradle : `gradle tasks --all`.
Les tâches de la forme `sub:task` est une tâche s'appliquant qu'au sous-module du même nom.

### Compilation
Pour compiler et tester le projet : `gradle build`, puis `gradle run` pour lancer l'application.

### Modification de gradle
Lors de modifications de gradle, vérifier la configuration avec `gradle` ou `gradle help` (aucune compilation).
