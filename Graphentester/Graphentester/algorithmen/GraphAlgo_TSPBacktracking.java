package algorithmen;

import java.util.List;
import java.nio.file.*;

import graph.*;

/**
  *
  * Dieser Algorithmus sucht einen möglichst kurzen Hamilton-Kreis (Traveling
  * Salesman Problem).
  * Algorithmus: Backtracking
  *
  * @version 1.0 from 11.12.2020
  * @author Thomas Schaller
  */

public class GraphAlgo_TSPBacktracking extends GraphAlgo {

  private List<String> besteLoesung = null;
  private double besteStrecke = Double.MAX_VALUE;
  private Knoten start;
    
  // Anfang Attribute

  
  public String getBezeichnung() {
    return "TSP (Vollständig)";
  }

  


  // Anfang Methoden
  public void fuehreAlgorithmusAus() {
     start = this.getStartKnoten();
     probiere(start);
     g.setStatus(besteLoesung);
      step();
      melde("beste Route gefunden:" +getInfo());
  } // end of for
  
  public void probiere(Knoten akt) {
     boolean fertig = true;
     infoIndentMore();
     akt.setMarkiert(true);
     info("Markiere Knoten Nr."+g.getNummer(akt));
     step();

     List<Knoten> nochNichtBesucht = g.getAlleKnoten(kn->!kn.isMarkiert());
     if(nochNichtBesucht.isEmpty()) {
       info("Keine weiteren nicht besuchten Knoten übrig");
       g.getKante(akt,start).setMarkiert(true);  
       info("Markiere Kante zum Startpunkt");
       List<Kante> gewaehlteKanten = g.getAlleKanten(ka->ka.isMarkiert());
       double laenge = 0;
       for(Kante k:gewaehlteKanten) {
         laenge+=k.getGewicht();
       }   
       info("Summiere alle Streckenlängen: Gesamtlänge ist "+laenge);
       if(laenge < besteStrecke) {
           info("Neue beste Strecke => merke diese Strecke");
           besteStrecke = laenge;
           besteLoesung = g.getStatus();;
       }
       step();
       infoIndentLess();
       g.getKante(akt,start).setMarkiert(false);
       akt.setMarkiert(false);
       info("Kehre zum vorherigen Knoten zurück");
       step();
       return;
    }
           
     info("untersuche alle ausgehenden Kanten:");
     List<Kante> kanten = g.getAusgehendeKanten(akt);
     for(Kante k: kanten) {
        if(!k.getAnderesEnde(akt).isMarkiert()) {
          k.setMarkiert(true);
          info("Kante zu Knoten Nr. "+g.getNummer(k.getAnderesEnde(akt))+"=> nicht markiert, probiere diesen Weg");
          probiere(k.getAnderesEnde(akt));
          k.setMarkiert(false);
        } else {
          info("Kante zu Knoten Nr. "+g.getNummer(k.getAnderesEnde(akt))+"=> schon markiert, kein sinnvoller Weg");
        }
     }

     akt.setMarkiert(false);
     info("kehre zu vorherigem Knoten zurück");
     infoIndentLess();
     step(); 
  }
  
  public String getInfo() {
    List<Kante> kanten = g.getAlleKanten();
    double laenge = 0;
    int anz =0;
    for(Kante k:kanten) {
        if(k.isMarkiert()) {
            laenge+=k.getGewicht();
            anz++;
        }
    }
    
    return "Weglänge ("+anz+" von "+g.getAnzahlKnoten()+" Kanten): "+laenge+" km. Bisher beste Gesamtlösung "+this.besteStrecke+" km";
}
  
  
  // Ende Methoden
  
}

