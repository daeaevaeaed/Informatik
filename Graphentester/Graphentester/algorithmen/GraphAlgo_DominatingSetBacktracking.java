package algorithmen;

import java.util.List;
import java.util.Collections;
import java.nio.file.*;

import graph.*;
/**
 * Dieser Algorithmus bestimmt die kleinste dominierende Menge in einem Graphen
 * und bestimmt den Zeitbedarf.
 * Algorithmus: Backtracking
 *
 * @version 1.0 from 10.12.2020
 * @author Thomas Schaller
 */

public class GraphAlgo_DominatingSetBacktracking extends GraphAlgo {

    // Anfang Attribute
    int besteAnzahl;
    List<String> beste;

    public  String getBezeichnung() {
        return "Dominierende Menge (Vollständig)";
    }


    // Anfang Methoden
    public void fuehreAlgorithmusAus() {
        long starttime = System.currentTimeMillis();
        if (g.getAnzahlKnoten()==0) {
            return;
        } 
        besteAnzahl = Integer.MAX_VALUE;
        bestimmeDominierendeMenge(0);
        g.setStatus(beste);
        long endtime = System.currentTimeMillis();
        melde("Minimale dominierende Menge in "+((endtime-starttime)/1000)+" Sekunden gefunden.");
        step();
    }



    private void bestimmeDominierendeMenge(int knoten) {
        List<String> status = g.getStatus();
      
        List<Knoten> markierte = g.getAlleKnoten(kn->kn.isMarkiert());  
        List<Knoten> nichtbesucht = g.getAlleKnoten(kn->!kn.isBesucht() && !kn.isMarkiert());  
  
        // Verbessert die Laufzeit deutlich, aber verhindert das exponentielle Wachstum nicht
        // if(markierte.size() >=besteAnzahl) return;
        
        Knoten k = g.getKnoten(knoten);
        if(k != null && nichtbesucht.size()>0) {
          bestimmeDominierendeMenge(knoten+1);
          g.setStatus(status);
          k.setMarkiert(true);
          k.setBesucht(false);
          for(Knoten n: g.getNachbarknoten(k, kn->!kn.isBesucht() && !kn.isMarkiert())) {
              n.setBesucht(true);
          }
          bestimmeDominierendeMenge(knoten+1);
          g.setStatus(status);
        } else {
          step();
            
          if(nichtbesucht.size()==0){
              if(markierte.size() < besteAnzahl)  {
                besteAnzahl = markierte.size();
                beste = status;
            }
          }
 
        }
    }

    // Ende Methoden
}
