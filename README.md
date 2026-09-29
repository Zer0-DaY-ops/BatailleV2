# Bataille Navale - SAE en binôme

IUT de Lens - BUT Informatique - Année 2025-2026

## Présentation du projet

Ce projet a été réalisé en binôme dans le cadre d'une SAE de BUT Informatique à l'IUT de Lens, pendant l'année 2025-2026. Le but était de créer un jeu de Bataille Navale en mode console en Java, où un joueur affronte une IA sur une grille de 10x10.

On a voulu garder une trace de comment le projet a évolué, parce que le code final ne ressemble plus du tout à ce qu'on avait au début. Il y a eu pas mal d'erreurs en route, mais c'est aussi grâce à elles qu'on a compris comment mieux organiser notre code.

## V1 - Le premier jet

La première version tenait quasiment dans un seul fichier. Tout le jeu reposait sur des `if / else if / else` imbriqués, puis on est passés à un `switch` pour les menus, ce qui était déjà plus lisible.

**Ce qui a marché :** on avait un jeu qui tournait, avec une grille, un placement de navires et un système de tir côté joueur.

**Ce qui n'a pas marché :** le code devenait vite illisible, les variables s'écrasaient entre elles, et à chaque nouvelle fonctionnalité on cassait quelque chose d'autre. À la fin de la V1, l'IA ne ripostait même pas encore, le jeu était donc à sens unique.

**Ce qu'on en a retenu :** découper le code en méthodes (affichage de la grille, vérification des coordonnées...) nous a beaucoup aidés, mais ça ne suffisait plus pour un projet de cette taille.

## V2 - On repart en objet

Pour la V2, on a décidé de tout reprendre depuis le début en programmation orientée objet. C'était un peu frustrant de repartir de zéro, mais au final ça nous a fait gagner du temps.

**Choix techniques :**

- Une classe abstraite `Navire` avec cinq navires qui en héritent : `Titan` (5 cases), `Warden` (4), `Interceptor` (3), `Phantom` (2) et `Dart` (1). La classe `Flotte` crée la flotte complète.
- Une classe abstraite `Joueur` avec deux versions : `Jhumain` pour le joueur au clavier et `Jia` pour l'ordinateur. Le placement aléatoire est dans `Joueur`, comme ça les deux peuvent l'utiliser.
- Une classe `Grille` qui gère le placement, les tirs et vérifie si tous les navires sont coulés.
- Un enum `Orientation` (horizontale ou verticale) et un enum `ResultatTir` (manqué, touché, coulé, déjà touché), ce qui évite de manipuler des chiffres ou des chaînes de caractères partout.
- Une interface `VueJeu` avec `VueConsole` pour l'affichage. Tout l'affichage passe par là, donc en théorie on pourrait faire une interface graphique sans toucher au reste du code.
- Une classe `Partie` qui gère la boucle de jeu et alterne les tours.

**Nos réussites :**

- L'IA riposte enfin, le jeu est maintenant complet du début à la fin.
- Le placement automatique marche et gère les collisions et les navires qui sortent de la grille.
- Le joueur peut choisir entre placer ses navires lui-même ou les placer automatiquement.
- Les saisies invalides sont gérées : si on tape n'importe quoi, le programme redemande au lieu de planter.
- On a fait une classe `TestGrille` pour vérifier le placement et les tirs sans devoir lancer une partie entière à chaque fois.

**Nos erreurs et difficultés :**

- Au début on comparait les positions avec `==` et ça ne marchait jamais. On a compris qu'il fallait redéfinir `equals` et `hashCode` dans `Position`, sinon le `HashSet` ne reconnaît pas deux positions identiques.
- On a eu des soucis avec les lignes et les colonnes inversées entre la saisie (ex : `B5`) et le tableau, ce qui plaçait les tirs au mauvais endroit.
- L'IA garde sa propre liste des cases déjà tirées alors que la grille le fait déjà. Ça marche, mais c'est un doublon qu'on aurait pu éviter.
- L'IA tire complètement au hasard. On voulait qu'elle vise autour d'une case touchée mais on n'a pas eu le temps.
- Il reste quelques petites fautes dans les messages et les noms (par exemple `estCoulee` au lieu de `estCoule`).

## Organisation des fichiers

```
BatailleV2/
├── src/
│   ├── main/
│   │   └── java/
│   │       └── BatailleV2/
│   │           ├── BatailleNavale.java   point d'entrée (main), crée les joueurs, la vue et lance la partie
│   │           ├── Partie.java           boucle de jeu : placement, alternance des tours, fin de partie
│   │           │
│   │           ├── Joueur.java           classe abstraite d'un joueur (nom, grille, placement aléatoire)
│   │           ├── Jhumain.java          joueur humain : lit les saisies au clavier (tirs, placement, orientation)
│   │           ├── Jia.java              joueur ordinateur : tire au hasard sur une case pas encore visée
│   │           │
│   │           ├── Grille.java           grille 10x10 : placement des navires, réception des tirs, tous coulés ?
│   │           ├── Position.java         une case (ligne, colonne), avec equals/hashCode et affichage type "B5"
│   │           ├── Orientation.java      enum HORIZONTALE / VERTICALE avec le décalage ligne/colonne
│   │           ├── ResultatTir.java      enum MANQUE / TOUCHE / COULE / DEJA_TOUCHE
│   │           │
│   │           ├── Navire.java           classe abstraite d'un navire (nom, taille, points de vie, touché, coulé)
│   │           ├── Titan.java            navire de 5 cases
│   │           ├── Warden.java           navire de 4 cases
│   │           ├── Interceptor.java      navire de 3 cases
│   │           ├── Phantom.java          navire de 2 cases
│   │           ├── Dart.java             navire de 1 case
│   │           ├── Flotte.java           crée la liste des 5 navires d'un joueur
│   │           │
│   │           ├── VueJeu.java           interface d'affichage (messages, grilles, résultats, vainqueur)
│   │           └── VueConsole.java       affichage du jeu dans la console
│   │
│   └── test/
│       └── java/
│           └── BatailleV2/
│               └── TestGrille.java       tests du placement et des tirs sur une grille, sans lancer de partie
│
├── .idea/                                configuration du projet IntelliJ
├── out/                                  fichiers compilés (.class), générés automatiquement
├── BatailleV2.iml                        configuration du module IntelliJ (dossiers sources et tests)
├── .gitignore                            fichiers à ne pas envoyer sur Git (out/, .class)
└── README.md                             ce fichier
```

## Lancer le jeu

Ouvrir le projet dans IntelliJ puis lancer la classe `BatailleNavale`. Pour les tests, lancer `TestGrille`.

Pour jouer, on donne une case avec une lettre puis un chiffre (ex : `C7`), et pour l'orientation `H` ou `V`.

## Ce qu'on a appris

- Découper un problème en classes qui ont chacune leur rôle.
- Utiliser l'héritage, les classes abstraites, les interfaces et les enums dans un vrai projet.
- Accepter de refaire du code quand il ne tient plus la route, plutôt que de rajouter des rustines.
- Travailler à deux sur le même code et se répartir les tâches.
