# Defi-Meninges

Defi-Meninges est une plateforme SaaS de quiz interactifs. Le projet est conçu pour offrir une expérience utilisateur ultra-rapide et une architecture hautement scalable, capable de soutenir une forte croissance.

Ce dépôt utilise une approche Monorepo pour centraliser le code du frontend et du backend, facilitant ainsi la maintenance et la synchronisation des contrats de données.

## Stack Technique

Le projet repose sur des technologies modernes orientées performance et maintenabilité :

**Frontend (Dossier /frontend)**
- Framework : Angular 21
- Architecture : Standalone Components (sans modules)
- Rendu : SSR (Server-Side Rendering) natif pour optimiser le SEO et le temps de chargement initial.
- Styles : SCSS

**Backend (Dossier /backend)**
- Langage : Java 21 (utilisation des Records pour garantir l'immutabilité des données)
- Framework : Spring Boot 3
- Accès aux données : Spring Data JPA / Hibernate
- Base de données : PostgreSQL

## Principes d'Architecture

- API-First : Le backend expose des contrats de données stricts (DTOs) que le frontend consomme via des interfaces TypeScript miroir.
- Séparation des responsabilités : La logique métier est strictement isolée dans les services Java. Le frontend gère uniquement l'affichage et l'état de l'interface.
- Scalabilité : L'architecture sans état (stateless) du backend permet un déploiement horizontal facile.

## Installation et Lancement en local

### Prérequis
- Node.js (version 22 ou supérieure recommandée)
- Java Development Kit (JDK) 21
- Maven (inclus via le wrapper)

### 1. Démarrer le Backend (API)
Ouvrez un terminal et placez-vous à la racine du projet :

```bash
cd backend
./mvnw spring-boot:run
```
L'API REST sera accessible sur : http://localhost:8080/api

### 2. Démarrer le Frontend (Application Web)
Ouvrez un second terminal et placez-vous à la racine du projet :

```bash
cd frontend
npm install
npm start
```
L'interface utilisateur sera accessible sur : http://localhost:4200

## Roadmap et État d'avancement

- Initialisation du Monorepo Git
- Configuration du socle Angular 21 (SSR & Standalone)
- Définition des DTOs (Java Records & Interfaces TS)
- Implémentation de la logique métier et des Services (Backend)
- Configuration et connexion à PostgreSQL
- Développement de l'interface de jeu (Frontend)
- Déploiement CI/CD et mise en production

## Contribution

Ce projet a pour vocation d'évoluer vers une plateforme SaaS complète. Si vous souhaitez contribuer, merci de créer une branche à partir de `main` en respectant la convention de nommage `feature/nom-de-la-fonctionnalite` ou `fix/description-du-bug`, et de soumettre une Pull Request détaillée.