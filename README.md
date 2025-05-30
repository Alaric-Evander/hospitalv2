# Rapport de la vidéo Spring MVC Thylemeaf Spring Data JPA, réalisée par Mr.Mohamed Youssfi
Cette vidéo offre une démonstration pratique de la mise en œuvre d'une application Spring Boot avec Spring MVC, JPA et Thymeleaf. Ce projet représente une application web simple permettant de gérer une liste de patients dans un système hospitalier.

Lien : [https://www.youtube.com/watch?v=Kfv_7m8INlU](https://www.youtube.com/watch?v=jDm-q-jEbiA)
         &&  https://www.youtube.com/watch?v=eoBE745lDE0

## Objectif du TP
Créer une application web en utilisant le framework Spring Boot , permettant de : 
 -  Afficher une liste paginée de patients.
 -  Rechercher des patients par leur nom.
 -  Ajouter des patients
 -  Modification des patients.
 -  Supprimer des patients.
 -  Utiliser Bootstrap 5 et Font Awesome (via Bootstrap Icons) pour la mise en forme visuelle.
 -  Utiliser Thymeleaf comme moteur de template HTML.
 -  Gestion de la sécurité.
 -  Gestion de l'authentification

## Structure du Projet
#### 1. Entité : Patient
 -  Package : net.zerhouani.hospitalspringmvc.entities
 -  Annotations utilisées :
     *   @Entity : Pour la persistance JPA.
     *  @Data, @NoArgsConstructor, @AllArgsConstructor, @Builder : Via Lombok pour réduire le code boilerplate.
 -  Champs : id (auto-généré) - nom - dateNaissance - malade (boolean) - score
#### 3. Repository : PatientRepository
 -  Package : net.zerhouani.hospitalspringmvc.repository
 -  Interface qui étend JpaRepository.
 -  Méthodes personnalisées :
     *  findByNomContains(String keyword, Pageable pageable)
     *  Requête personnalisée via JPQL : cherher(@Param("x") String keyword, Pageable pageable)
#### 4. Contrôleur : PatientController
 -  Package : net.zerhouani.hospitalspringmvc.web
 -  Gère les requêtes HTTP :
      * GET "/user/index"  : Affiche la liste paginée des patients.
      * GET "/admin/delete" : Supprime un patient par son ID (redirige vers /index).
      * GET "/"       : Redirection vers /index.
      * GET "/admin/formPatients" : Formulaire de création
      * POST "/admin/save" : Enregistrement d’un nouveau ou mise à jour
      * GET "/admin/editPatient" : Modification d’un patient existant
#### 5. Application principale : HospitalSpringMvcApplication
 -  Lance l’application Spring Boot.
 -  Implémente CommandLineRunner pour insérer des données de test à l’exécution :
         *  Insertion de trois patients dans la base H2 embarquée.
 - Point d’entrée de l'application Spring Boot.
 - Insertion automatique de données initiales via CommandLineRunner.
 - Configuration du PasswordEncoder.
#### 6. Vue Thymeleaf : patients.html
 -  Template HTML avec intégration de Bootstrap 5 via WebJars.
 -  Formulaire de recherche.
 -  Tableau affichant les patients.
 -  Pagination dynamique.
 -  Icônes FontAwesome (via Bootstrap Icons).
 -  Intégration jQuery pour interactivité.
 -  Button de suppression.
 -  Button de modification.
#### 7. Vue Thymeleaf : editPatient.html et formPatient.html
 -  Template HTML avec intégration de Bootstrap 5 via WebJars.
 -  Intégration jQuery pour interactivité.
 -  Ajout d'un nouveau patient depuis un formulaire.
 -  Modification d'un patient existant à partir d'un formulaire.
#### 8. Vue Thymeleaf : template1.html
 -  Template HTML avec intégration de Bootstrap 5 via WebJars.
 -  Intégration jQuery pour interactivité.
 -  Création d'un navbar intéractive.
#### 9. Vue Thymeleaf : NotAuthorized.html
 -  Template HTML avec intégration de Bootstrap 5 via WebJars.
 -  Intégration jQuery pour interactivité.
 -  Création d'une interface pour les requêtes non autorisée.
#### 10. Service Web : UserDetailsService.java
 - Implémente UserDetailsService.
 - Convertit AppUser en UserDetails avec ses rôles.
 - Utilise Lombok pour l'injection de dépendances.
#### 11. Security : SecurityConfig.java
 - Active Spring Security.
 - Configure les accès selon les rôles (/user/** pour USER, /admin/** pour ADMIN).
 - Personnalise la page de login et la gestion des erreurs d'accès.
#### 12. Web Controller : PatientController.java
 - Gère les routes CRUD : affichage, ajout, modification, suppression.
 - Pagination et recherche par mot-clé.
 - Utilisation de @Valid pour la validation côté serveur.
#### 13. Web Controller : SecurityController.java
 - Gère les vues de connexion et erreur d'accès (/login, /notAuthorized).


## Dépendances principales :
 -  Spring Data JPA : Persistance ORM
 -  Thymeleaf : Moteur de template
 -  Spring Web : Serveur web intégré
 -  H2 Database : Base de données en mémoire pour tests
 -  Bootstrap & Bootstrap-icons : Styling CSS/JS
 -  Lombok : Réduction du code boilerplate
 -  MySQL Connector : Connexion à MySQL (facultatif si utilisé)
 -  Spring Boot
 -  Spring Security

## Fonctionnalités Implémentées :
 -  Recherche par nom : Permet de filtrer les patients selon un mot-clé.
 -  Pagination : Affichage paginé (4 éléments par page).
 -  Suppression : Supprime un patient avec confirmation JavaScript.
 -  Redirection après suppression : Retourne à la page précédente avec le mot-clé actuel.
 -  Interface utilisateur : Design responsive avec Bootstrap 5.
 -  Données initiales : Insertion automatique au démarrage de l’appli.
 -  Création de nouveaux patients.
 -  Édition de patients : Permet de modifier les informations d'un patient existant.
 -  Validation des données : Permet de vérifier certaines contraintes dans la saisie avant la création d'n nouveau patient.
 -  Gestion des autorisations par rôles.
 -  Gestion d'authentification.
 -  Création des utilisateurs.

## Gestion de la Sécurité :
 -  Deux rôles principaux : USER et ADMIN.
 -  Accès restreint selon les rôles :
   * /user/** → accessible uniquement aux utilisateurs.
   * /admin/** → réservé aux administrateurs.
 -  Page de login personnalisée (/login).
 -  Gestion des erreurs d'accès non autorisé (/notAuthorized).
 -  Mots de passe encodés avec BCryptPasswordEncoder.

## Démonstration

### Essai :
![image](https://github.com/user-attachments/assets/66b846af-8e33-4c79-85e6-61a9075429d3)
![image](https://github.com/user-attachments/assets/02f8d4b2-4376-46e9-b9d4-aca08d1d2432)
![image](https://github.com/user-attachments/assets/64d3f5c4-3757-4696-aa2e-3089d2141fb9)


### Realisation :
![image](https://github.com/user-attachments/assets/b11fdd87-c511-4cd9-ab97-e2f11d2c0518)
![image](https://github.com/user-attachments/assets/1045709f-553a-4f61-ace7-50d0ec1585c7)
![image](https://github.com/user-attachments/assets/92d07d86-9773-4572-87f8-e417a6aa0bcf)


Recherche et positionnement :
![image](https://github.com/user-attachments/assets/678354bc-055e-4455-a548-ee99b1df61ee)
![image](https://github.com/user-attachments/assets/6479cd25-6a80-4133-8140-5085059f60b6)


Création d'une instance:
![image](https://github.com/user-attachments/assets/634f3f2b-cb85-49ec-95f3-de442c3334f7)
![image](https://github.com/user-attachments/assets/61fa6d57-e86a-4131-b36e-0d4e19059182)


Suppression :
![image](https://github.com/user-attachments/assets/8cd70ad8-baca-4925-831a-48fd9c3334a9)
![image](https://github.com/user-attachments/assets/42e722cc-cb74-4974-81f2-0646c1c83482)


Modification:
![image](https://github.com/user-attachments/assets/b9c121ea-64fe-45ae-9644-1db74f379316)

Sécurité :
![image](https://github.com/user-attachments/assets/0cd9d971-cbe5-4472-bbda-ba10ec85f4c5)
![image](https://github.com/user-attachments/assets/226fb24e-1f58-4ac3-9dcd-3aa45e7e1ef7)

![image](https://github.com/user-attachments/assets/76f10a42-6f82-4704-82ae-998f644f829b)
![image](https://github.com/user-attachments/assets/f7d348aa-7f07-469b-8013-d056edead784)
![image](https://github.com/user-attachments/assets/d9f42d66-0e91-4ba3-a0ae-5bea2308de3e)
![image](https://github.com/user-attachments/assets/1457c7a9-d260-4c2e-82b9-5594c959f698)

![image](https://github.com/user-attachments/assets/36800a70-9c29-4f42-a572-d8c67ea496e3)
![image](https://github.com/user-attachments/assets/93c4f10b-2297-4680-af38-cea962934509)








## Conclusion
Ce TP illustre parfaitement les bases de la création d’une application MVC avec Spring Boot , utilisant JPA , Thymeleaf et des outils modernes comme Bootstrap . Il s’agit d’un bon point de départ pour développer une application de gestion hospitalière plus complète, notamment en ajoutant des fonctionnalités CRUD et une sécurité robuste(Spring Security).
En approfondissant certaines parties (tests, internationalisation, déploiement), cette application pourrait facilement être utilisée dans un contexte professionnel.
