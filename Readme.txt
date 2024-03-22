Application de suivi des corrections dorsales
Description
Cette application API a été développée pour répondre aux besoins de l'entreprise dock-dorsal, spécialisée dans la commercialisation d'appareils dorsaux visant à corriger la posture du dos. Son objectif est de permettre aux utilisateurs de saisir quotidiennement les informations sur la correction apportée par l'appareil, de consulter et de gérer leurs saisies, ainsi que de s'inscrire et de se connecter de manière sécurisée.

Fonctionnalités
Les fonctionnalités principales de l'application comprennent :

Inscription et Connexion : Les utilisateurs peuvent s'inscrire en fournissant leurs informations de base et choisir un mot de passe sécurisé. La connexion nécessite une authentification.
Saisie de correction journalière : Les utilisateurs peuvent saisir quotidiennement la valeur de la correction apportée par l'appareil dorsal.
Modification et Suppression de saisie : Les utilisateurs ont la possibilité de modifier ou supprimer leurs saisies précédentes en cas d'erreur.
Consultation des saisies : Les utilisateurs peuvent consulter une saisie spécifique en fonction de son ID, consulter toutes leurs saisies renseignées, ou remonter leurs saisies sur une période donnée.
Gestion des utilisateurs : L'API distingue les utilisateurs simples des utilisateurs ayant le rôle "ADMIN". Les utilisateurs "ADMIN" ont accès à toutes les fonctionnalités et peuvent consulter les informations de n'importe quel utilisateur sans restriction.
Sécurité
La sécurité est une préoccupation majeure de l'application :

L'authentification est requise pour accéder à toutes les fonctionnalités de l'API, à l'exception de l'inscription et de la connexion.
Les mots de passe des utilisateurs sont stockés de manière sécurisée à l'aide de BCryptPasswordEncoder.
Utilisation
L'API expose plusieurs endpoints pour permettre aux utilisateurs d'interagir avec l'application, notamment pour l'inscription, la connexion, la saisie de correction, la consultation des saisies et la gestion des utilisateurs.

Technologies utilisées
L'application a été développée en utilisant Java, Spring Boot, Spring Security, Hibernate et MySQL.