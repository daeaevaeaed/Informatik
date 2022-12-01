package algorithmen;

import java.util.List;
import java.nio.file.*;
import java.util.Collections;

import graph.*;
/**
 * Dieser Algorithmus färbt einen Graphen, so dass keine benachbarten Knoten
 * die gleiche Farbe haben und möglichst wenige Farben benutzt werden.
 * Algorithmus: Näherungslösung mit Greedy-Algorithmus (Knotenreihenfolge zufällig)
 *
 * @version 1.0 from 10.12.2020
 * @author Thomas Schaller
 */

public class GraphAlgo_ColoringGreedyRandom extends GraphAlgo {

  // Anfang Attribute

  
  
  public  String getBezeichnung() {
    return "Map-Coloring (Greedy,Random)";
  }
  

  // Ende Attribute
  
  // Anfang Methoden
  public void fuehreAlgorithmusAus() {
    List<Knoten> knoten = g.getAlleKnoten();
    Collections.shuffle(knoten);
    info("Wiederhole für jeden Knoten");
    for (Knoten aktuellerKnoten: knoten ) {
      // Liste in der die Farben der adjazenten Knoten abgehakt werden. Die Farben
      // sind von 1 bis n (# Knoten) kodiert und werden spaeter in Farben decodiert 
      boolean[] farbenliste = new boolean[g.getAnzahlKnoten()+1];
      List<Knoten> nachbarn = g.getNachbarknoten(aktuellerKnoten);

      info("Bearbeite Knoten "+g.getNummer(aktuellerKnoten));
      infoIndentMore();
      info("Setze alle Farbe der Farbenliste auf unbenutzt");
      info("Wiederhole für jeden Nachbarknoten");
      infoIndentMore();
      // speichere alle Farben in dem Array farbenliste[], die in der Adjazenzliste vom Knoten k als Wert vorkommen
      for (Knoten k : nachbarn){
        info("Knoten "+g.getNummer(k)+": Setze Farbe "+k.getFarbe()+" auf benutzt");
        farbenliste[k.getFarbe()]=true;
      }
      infoIndentLess();
      
      info("Suche in Farbenliste nach unbenutzer Farbe");
      infoIndentMore();
      // faerbe den Knoten k (setze den Farbwert des Knotens) mit der niedrigst-moeglichen Farbe (kleinster Index > 0 in der farbenliste)
      for (int i=1; i<farbenliste.length; i++){
        if (!farbenliste[i]) {
          info("Farbe "+i+" ist unbenutzt");
          infoIndentLess();
          aktuellerKnoten.setFarbe(i);
          info("Setze Knoten "+g.getNummer(aktuellerKnoten)+" auf Farbe "+i);
          break;
        }
        info("Farbe "+i+" ist benutzt");
      }
      infoIndentLess();
      step();
    } // end of for
  }
  
  // Ende Methoden
  
}

