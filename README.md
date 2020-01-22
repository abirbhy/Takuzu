# Takuzu
Implémentation d'une version console et d'une interface graphique du jeu Takuzu (Binario)

Takuzu or Binairo is a puzzle game consisting of filling a grid with numbers 0 and 1 by logical deduction. It's implemented in java.

## Constraints to respect

4x4, 6x6 or 8x8 grids, each grid contains only 0 and 1, and must be completed respecting three rules2:

1/ As many as 1 and 0 on each line and on each column.

2/ No more than 2 identical digits side by side.

3/ 2 rows or 2 columns can not be identical.

## Technologies

Java 8

Swing

## IDE

Eclipse Oxygen

## Exécution

Pour éxecuter la partie console du projet Takuzu, tapez la commande suivante :
    
           java -cp takuzuRunnable.jar takuzu.jeu.console.Main

Pour éxecuter la partie graphique du projet Takuzu, tapez la commande suivante :
    
           java -cp takuzuRunnable.jar takuzu.jeu.graphique.MainFrame
