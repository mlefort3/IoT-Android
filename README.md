# Projet AMIO - Android : Maxence Lefort et Yann Ricci

## Membres du projet
* Maxence LEFORT (maxence.lefort@telecomnancy.eu)
* Yann RICCI (yann.ricci@telecomnancy.eu)

## Fonctionnalités de l'application
* Liste des dernières données (avec timestamp, type de donnée, valeur et adresse du capteur) recueillies par les capteurs :
les données sont récupérées automatiquement au démarrage de l'application si l'utilisateur selon les préférences de l'utilisateur
* Liste des capteurs disponibles dans un écran séparé, avec leur emplacement et leur adresse
* Carte des capteurs, grâce à une vue internet sur le site http://iotlab.telecomnancy.eu/
* Une récupération automatique des données grâce à des tâches aynchrones, réalisées selon une certaine fréquence
* Une notification par mail si une lumière est allumée entre 19h et 23h

## Écrans de l'application
* L'activité principale contient différents boutons : un bouton d'accès aux préférences de l'application, un pour accéder à
la liste des capteurs et un dernier pour réaliser un rafraîchissement manuel des données. Enfin, l'écran principal affiche les
dernières données recueillies par les capteurs
* L'activité de préférences contient une case à cocher pour autoriser ou non le rafraîchissement des données au démarrage de
l'application. Les préférences en matière de notifications et de mails ne sont pas fonctionnelles
* L'activité de liste des motes affiche la liste de tous les capteurs disponibles, qu'ils soient actifs ou non, et affiche leurs
différentes valeurs. Un bouton permet le rafraîchissement manuel de ces données, un autre permet d'accéder à la vue internet de
la carte des capteurs, carte présente sur le site http://iotlab.telecomnancy.eu/
