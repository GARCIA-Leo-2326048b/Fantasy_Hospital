# **Hopital Fantastique et Colonie de Lycanthropes**

Une application Java qui simule la gestion d'un hôpital fantastique et la vie d'une colonie de lycanthropes. Ce projet est divisé en 2 parties : l'hôpital fantastique et la colonie de lycanthropes, chacun ayant des fonctionnalités spécifiques.

---

## **Fonctionnalités Principales**

### **Hôpital Fantastique**
- Gestion de créatures fantastiques (elfes, nains, orques, zombies, vampires, etc.).
- Prise en charge des maladies comme :
    - Maladie Débilitante Chronique (MDC).
    - Syndrome Fear of Missing Out (FOMO).
    - Dépendance aux Réseaux Sociaux (DRS).
- Gestion des services médicaux : révision des budgets, soins, et transferts de patients.
- Simulation dynamique :
    - Évolution de l'état des créatures (santé, moral, etc.).
    - Mise à jour des services médicaux (température, isolation, etc.).
    - Interaction avec les médecins via un menu utilisateur.

### **Colonie de Lycanthropes**
- Simulation de meutes organisées selon une hiérarchie stricte (α, β, γ…).
- Gestion des interactions : domination, soumission, hurlements.
- Reproduction au sein du couple α pendant la saison des amours.
- Transformation de lycanthropes en humains et gestion des conséquences.
- Simulation dynamique :
    - Évolution naturelle des rangs hiérarchiques.
    - Génération aléatoire d’événements comme les hurlements et les transformations.

---

## **Prérequis**
- **Java 11** ou version supérieure.
- **Maven** pour la gestion des dépendances.
- IDE recommandé : **IntelliJ IDEA** ou **Eclipse**.

---

## **Installation**
1. Clonez ce dépôt Git :
   ```bash
   git clone git@github.com:GARCIA-Leo-2326048b/Hopital_Fantastique.git
    ```
2. Importez le projet dans votre IDE préféré.
3. Compilez et exécutez le projet.

## **Usage**
- Lancez l'application depuis la méthode main dans la classe principale.
- Utilisez le menu interactif pour :
  - Diriger les médecins dans l'hôpital.
  - Gérer les meutes et leurs interactions dans la colonie.

## **Structure du Projet**

- `src/TP3/main/java` : Contient les classes principales liées à l'hôpital fantastique.
  - `Creatures` : Gestion des créatures par catégorie (bestiales, mort-vivantes, VIP, etc.).
  - `HopitalsFantastiques` : Classes centrales pour la gestion des hôpitaux.
  - `Maladies` : Gestion des pathologies des patients.
  - `Medecins` : Classes dédiées à la gestion des médecins.
  - `ServicesMedicaux` : Organisation et gestion des services dans l'hôpital.
  - `Threads` : Gestion des processus parallèles pour l'hôpital.

- `src/TP3/test/java` : Contient les tests unitaires liées à l'hôpital fantastique.

- `src/TP4/main/java` : Contient les classes principales liées à la colonie de lycanthropes.
  - `Colonie` : Gestion globale de la colonie et des interactions.
  - `Lycanthrope2` : Classes liées aux lycanthropes (catégories, hiérarchie, types de hurlements, etc.).
  - `Meute` : Gestion spécifique des meutes dans la colonie.
  - `Threads` : Gestion des processus parallèles pour les colonies et meutes.

- `src/TP4/test/java` : Contient les tests unitaires pour la colonie de lycanthropes.
  - `TestColonie` : Tests associés aux fonctionnalités de la colonie.
  - `TestLycanthrope2` : Tests associés aux lycanthropes.
  - `TestMeute` : Tests associés à la gestion des meutes.

## **Auteurs**
Développé par **Jalil Kanboui** et **Félicité Gary** dans le cadre de notre formation BUT informatique, pour la ressource **Qualité de développement**.