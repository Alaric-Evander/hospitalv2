Rapport d'Analyse Technologique : Application HospitalV2

L'application est un système de gestion de patients, exploitant un ensemble de technologies modernes et éprouvées dans l'écosystème Java pour le développement d'applications web.

Vue d'Ensemble de l'Architecture
L'application est construite sur le framework Spring Boot, qui simplifie considérablement la création d'applications Java autonomes et de qualité production. 
Spring Boot, par sa nature, encourage une architecture en couches, typiquement :

Couche de Présentation (Interface Utilisateur) : Gérée par Thymeleaf et Bootstrap.

Couche Contrôleur (Web) : Gérée par Spring MVC.

Couche Service/Logique Métier (implicite dans ce cas, potentiellement intégrée dans le contrôleur ou directement via le repository).

Couche d'Accès aux Données (Persistance) : Gérée par Spring Data JPA avec Hibernate comme fournisseur JPA sous-jacent.

Technologies Détaillées
1. Java
Le langage de programmation principal utilisé est Java. C'est un langage robuste, orienté objet, et largement adopté pour les applications d'entreprise.

2. Spring Boot
Spring Boot est le socle de l'application. Il fournit :

Configuration automatique : Réduit la nécessité de configurations XML ou Java manuelles complexes.

Serveur web embarqué  : Permet de lancer l'application comme un simple fichier JAR exécutable.

Gestion des dépendances simplifiée : Via les "starters" de Spring Boot.

Métriques, vérification de l'état de santé et fonctionnalités de production.

Le fichier HospitalV2Application.java est le point d'entrée de l'application Spring Boot, annoté avec @SpringBootApplication. 
Il implémente également CommandLineRunner, ce qui lui permet d'exécuter du code au démarrage de l'application, ici utilisé pour initialiser la base de données avec quelques patients de test.

3. Spring MVC (Model-View-Controller)
Utilisé pour la gestion des requêtes web et la navigation. Le fichier PatientController.java illustre cette utilisation :

@Controller : Marque la classe comme un contrôleur Spring MVC.

@GetMapping : Mappe les requêtes HTTP GET vers des méthodes spécifiques (par exemple, /index pour afficher la liste des patients, /delete pour supprimer un patient).

Model : Utilisé pour passer des données du contrôleur à la vue.

@RequestParam : Utilisé pour extraire les paramètres des requêtes HTTP (comme les numéros de page, la taille de la page et les mots-clés de recherche).

4. Spring Data JPA
Pour la persistance des données, l'application utilise Spring Data JPA. Cela simplifie l'implémentation des couches d'accès aux données en réduisant la quantité de code répétitif.

PatientRepository.java est une interface qui étend JpaRepository<Patient, Long>. Spring Data JPA génère automatiquement les implémentations pour les opérations CRUD (Create, Read, Update, Delete) de base.

Méthodes de requête dérivées : findByNomContains est un exemple où Spring Data JPA déduit la requête SQL à partir du nom de la méthode.

Requêtes personnalisées : L'annotation @Query("select p from Patient p where p.nom like :x") permet de définir des requêtes JPQL (Java Persistence Query Language) personnalisées.

Pagination et Tri : L'utilisation de Pageable et Page permet de gérer facilement la pagination des résultats.

5. Jakarta Persistence API (JPA) / Hibernate
JPA est la spécification Java pour la gestion de la persistance et du mapping objet-relationnel (ORM). Hibernate est l'implémentation JPA la plus populaire, utilisée par défaut par Spring Boot lorsque spring-boot-starter-data-jpa est inclus.

Patient.java est une entité JPA, annotée avec @Entity.

@Id et @GeneratedValue(strategy = GenerationType.IDENTITY) : Définissent la clé primaire et sa stratégie de génération (auto-incrémentation gérée par la base de données).

Les autres champs (nom, dateNaissance, malade, score) sont mappés aux colonnes de la table Patient en base de données.

6. Lombok
La bibliothèque Lombok est utilisée pour réduire la verbosité du code Java, notamment pour les objets "POJO" (Plain Old Java Objects) comme les entités.

Dans Patient.java :

@Data : Génère automatiquement les getters, setters, toString(), equals(), et hashCode().

@NoArgsConstructor : Génère un constructeur sans arguments.

@AllArgsConstructor : Génère un constructeur avec tous les arguments.

@Builder : Fournit le pattern de conception "Builder" pour la création d'objets.

7. Thymeleaf
Thymeleaf est un moteur de template Java côté serveur utilisé pour la couche de présentation. Il permet de créer des vues HTML dynamiques.

Le fichier patients.html est un template Thymeleaf.

xmlns:th="http://thymleaf.org" : Déclare l'espace de noms Thymeleaf.

Attributs th:* : Utilisés pour intégrer la logique et les données du serveur dans le HTML (par exemple, th:each pour itérer sur une collection, th:text pour afficher du texte, th:href pour les liens dynamiques, th:action pour les formulaires, th:value pour les valeurs d'input).

Expressions Thymeleaf : ${...} pour accéder aux variables du modèle (par exemple, ${ListPatients}, ${keyword}, ${currentPage}).

8. Bootstrap
Bootstrap est un framework CSS front-end populaire utilisé pour styliser l'interface utilisateur et la rendre responsive.

Dans patients.html, les liens vers les fichiers CSS de Bootstrap sont inclus via WebJars :

<link rel="stylesheet" href="/webjars/bootstrap/5.3.5/css/bootstrap.min.css">

<link rel="stylesheet" href="/webjars/bootstrap-icons/1.13.1/font/bootstrap-icons.css">

Des classes Bootstrap sont utilisées pour la mise en page et le style des éléments HTML (par exemple, p-3, card, card-header, card-body, table, btn, btn-info, btn-danger, nav, nav-pills, ms-1).

9. WebJars
WebJars sont des bibliothèques JavaScript et CSS côté client empaquetées dans des fichiers JAR. Cela permet de gérer les dépendances front-end avec les outils de build Java (comme Maven ou Gradle). L'application utilise WebJars pour Bootstrap et Bootstrap Icons.

Flux de Données Typique (Exemple : Affichage de la liste des patients)
L'utilisateur accède à l'URL /index (avec des paramètres optionnels pour la page, la taille et le mot-clé).

Le DispatcherServlet de Spring MVC reçoit la requête et la transmet au PatientController.

La méthode index du PatientController est exécutée.

Le contrôleur appelle la méthode findByNomContains du PatientRepository avec le mot-clé et les informations de pagination (PageRequest.of(p,s)).

Spring Data JPA (via Hibernate) génère et exécute une requête SQL pour récupérer les patients correspondants de la base de données.

Les résultats sont retournés sous forme d'un objet Page<Patient>.

Le contrôleur ajoute la liste des patients (pagePatients.getContent()), le nombre total de pages, la page actuelle et le mot-clé au Model.

Le contrôleur retourne le nom de la vue, "patients".

Spring MVC résout ce nom en utilisant Thymeleaf pour rendre le template patients.html.

Thymeleaf traite le template, remplaçant les expressions th:* par les données du modèle, et génère le HTML final.

Le HTML est renvoyé au navigateur de l'utilisateur, qui affiche la liste des patients stylisée avec Bootstrap.

Conclusion
L'application HospitalV2 est un exemple typique d'une application web Java moderne construite avec le framework Spring Boot.
Elle tire parti de Spring Data JPA pour une interaction simplifiée avec la base de données, de Spring MVC pour la gestion des requêtes web, et de Thymeleaf avec Bootstrap pour une interface utilisateur dynamique et responsive. L'utilisation de Lombok contribue à la concision du code.
Cette pile technologique est robuste, évolutive et bien adaptée pour le développement d'applications web de taille petite à moyenne, voire grande.
Captures d'écran :
![image](https://github.com/user-attachments/assets/a2d5756a-5df0-4125-93ea-0187e32d0754)
![image](https://github.com/user-attachments/assets/b7de7f18-f8de-439d-83ce-c803b455b8d8)
![image](https://github.com/user-attachments/assets/3da7814d-72c2-4329-a4d4-69d4822b388f)
![image](https://github.com/user-attachments/assets/fd38e331-972e-4560-ba75-030ed619966a)
![image](https://github.com/user-attachments/assets/8101fcb4-b618-45be-87c9-9c4c0a3eb912)
![image](https://github.com/user-attachments/assets/d9154a73-187f-4214-a114-fa1ad17740c8)



