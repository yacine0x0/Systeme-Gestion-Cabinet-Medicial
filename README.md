# ClinicaPro — Système de Gestion de Cabinet Médical

Application de gestion complète pour cabinet médical, développée en **Java** avec **Maven**. Elle permet de gérer les patients, les rendez-vous, les consultations, la facturation et l'authentification des utilisateurs.

---

## 📑 Rapport du Projet

Un rapport complet du projet est disponible au format **PDF**. Il contient :

- Le cahier des charges détaillé.
- Les diagrammes UML conçus (cas d’utilisation, classes, séquence, etc.).

Pour le consulter, rendez-vous dans le dépôt et ouvrez le fichier PDF correspondant

## 📋 Fonctionnalités

### 🔐 Authentification & Sécurité
- Système de connexion sécurisé pour les utilisateurs (médecins, secrétaires, administrateurs).
- Gestion des rôles et des permissions.
- Authentification fonctionnelle (commit récent : « authentification works good »).

### 👤 Gestion des Patients
- Ajout, modification et suppression de dossiers patients.
- Consultation de l'historique médical.
- Recherche rapide par nom, prénom ou numéro de dossier.

### 📅 Gestion des Rendez-vous
- Planification et suivi des rendez-vous.
- Gestion des disponibilités des médecins.
- Notification des patients (à venir).

### 💰 Facturation
- Génération de factures pour les consultations et actes médicaux.
- Modèle de facture intégré (commit récent : « added facture model »).
- Suivi des paiements et des impayés.

### 📊 Tableau de Bord
- Vue d'ensemble des activités du cabinet.
- Statistiques et rapports (à venir).

---

## 🛠️ Technologies Utilisées

| Technologie | Description |
|-------------|-------------|
| **Java** | Langage principal du projet |
| **Maven** | Gestionnaire de dépendances et de build |
| **JavaFX / Swing** | Interface graphique (selon le framework utilisé) |
| **MySQL / PostgreSQL** | Base de données (à confirmer) |
| **Git** | Contrôle de version |

---

## 🚀 Installation

### Prérequis
- **Java JDK 17** ou supérieur.
- **Maven** installé.
- **MySQL** ou **PostgreSQL** (selon la configuration).

### Commandes
   ```bash
   git clone https://github.com/yacine0x0/Systeme-Gestion-Cabinet-Medicial.git
   cd Systeme-Gestion-Cabinet-Medicial
   cd code_source
   mvn clean compile
   mvn exec:java
   ```
   
---

## 🔄 État du Projet

- ✅ Authentification fonctionnelle
- ✅ Modèle de facture intégré
- 🚧 Gestion des patients en cours
- 🚧 Interface graphique en développement
 
---

##    Licence

Ce projet est sous licence **MIT**. Voir le fichier [LICENSE](LICENSE) pour plus de détails.