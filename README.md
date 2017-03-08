# softwareneedsyou
Software Needs You ! [Client]

> description ici


----------

# Utilisation avec Gradle

Globalement, Gradle s'utilise avec des "tâches" : `gradle <task>`.

`gradlew` est un wrapper générer automatiquement par gradle.
Ce script va télécharger la version utilisé pour compilé le fichier et l'utilisé en local.

> **What is Gradle wrapper?**
> The Gradle wrapper allows you to run a Gradle task without requiring that Gradle is installed on your system.

## Tâches
Pour voir la liste de toutes les tâches possible avec gradle : `gradle tasks --all`.

## Compilation
Pour compiler et tester le projet : `gradle build`, puis `gradle run` pour lancer l'application.

## Modification de gradle
Lors de modifications de gradle, vérifier la configuration avec `gradle check` (aucune compilation).
