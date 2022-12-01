package algorithmen;

import java.util.List;
import java.nio.file.*;
import java.util.Collections;

import graph.*;

/**
 *
 * Dieser Algorithmus sucht einen möglichst kurzen Hamilton-Kreis (Traveling
 * Salesman Problem).
 * Algorithmus: Greedy
 * Strategie: Verlängere den Weg immer mit der kürzesten Kante, die vom aktuellen Ende der Route ausgeht.
 * vlg. Minimal Spanning Tree (Prim)
 *
 * @version 1.0 from 11.12.2020
 * @author Thomas Schaller
 */

public class GraphAlgo_TSPGreedy extends GraphAlgo {

    // Anfang Attribute

    public String getBezeichnung() {
        return "TSP (Greedy: Knoten)";
    }


    // Anfang Methoden
    public void fuehreAlgorithmusAus() {
        Knoten start = this.getStartKnoten();
        Knoten akt = start;
        Kante min;
        int anz = 0;
        int laenge = 0;
        info("Starte mit Knoten Nr. "+g.getNummer(start));
        do{
            akt.setMarkiert(true);
            info("Markiere diesen Knoten");
            final Knoten aktK = akt;
            min = null;
            List<Kante> kanten = g.getAusgehendeKanten(akt, ka -> !ka.getAnderesEnde(aktK).isMarkiert());
            info("Betrachte alle ausgehenden Kanten zu unmarkierten Knoten.\n und sortiere diese Kanten nach Gewicht.");
            if(kanten.size() > 0) {
                Collections.sort(kanten);
                min = kanten.get(0);
                info("Kürzeste Kante geht zu Knoten "+g.getNummer(min.getAnderesEnde(akt)));
                laenge += min.getGewicht();
                anz++;
                min.setMarkiert(true);
                info("Markiere diese Kante (Länge "+laenge+" nach "+anz+" von "+g.getAlleKnoten().size()+" Knoten)");
                akt = min.getAnderesEnde(akt);
                info("mache mit diesem Knoten weiter");
            }
            step();
        }while (min!=null);
        g.getKante(akt,start).setMarkiert(true);
        info("Markiere die Kante vom letzten Knoten zum Startknoten");
        step(); 
        melde("Route gefunden: "+getInfo());
    } // end of for

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

        return "Weglänge ("+anz+" von "+g.getAnzahlKnoten()+" Kanten): "+laenge+" km.";
    }

    // Ende Methoden

}

