package eigeneAlgorithmen;

 

import java.util.List;
import java.nio.file.*;

import graph.*;
import algorithmen.*;

/**
 * Dieser Algorithmus färbt einen Graphen, so dass keine benachbarten Knoten
 * die gleiche Farbe haben und möglichst wenige Farben benutzt werden.
 * Algorithmus: Beispieldatei, in der Schüler den Algorithmus selbst umsetzen können
 *
 * @version 1.0 from 10.12.2020
 * @author Thomas Schaller
 */

public class GraphAlgo_Coloring_Schueler extends GraphAlgo {


  
  public  String getBezeichnung() {
    return "Greedy-Coloring (toDo)";
  }

  // Ende Attribute
  
  // Anfang Methoden
  public void fuehreAlgorithmusAus() {
    getStartKnoten().setFarbe(3);
     
    // Hole alle Knoten vom Graph g
    
    // Schleife über alle Knoten

      // Erzeuge Liste (Größe #Knoten +1) in der die Farben der adjazenten Knoten abgehakt werden. Die Farben
      // sind von 1 bis n (# Knoten) kodiert und werden spaeter in Farben decodiert 

      // Hole alle Nachbarknoten vom aktuellen Knoten
            
      // Gehe alle Nachbarn durch und markiere die Farben des Knoten in dem Array farbenliste[] als benutzt
      
      // suche die erste Position (>0) aus der Farbliste, die noch nicht benutzt wurden und faerbe den Knoten k mit dieser Farbe (setze den Farbwert des Knotens auf die Position im Array) 

      // Unterbreche die Ausführung
      step();
    
    // Ende der Schleife über alle Knoten   
 
  }
  
  // Ende Methoden
  
}

