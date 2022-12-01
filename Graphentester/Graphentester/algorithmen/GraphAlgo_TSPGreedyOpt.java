package algorithmen;

import java.util.List;
import java.util.ArrayList;
import java.nio.file.*;

import graph.*;

/**
 *
 * Dieser Algorithmus sucht einen möglichst kurzen Hamilton-Kreis (Traveling
 * Salesman Problem).
 * Algorithmus: Greedy mit anschließender Optimierung: 
 * Jeder Knoten wird der Reihe nach aus der Rundreise entfernt und dort wieder eingefügt, wo die Rundreise
 * sich am wenigsten verlängert. Diese Optimierung wird 5x wiederholt.
 *
 * @version 1.0 from 11.12.2020
 * @author Thomas Schaller
 */

public class GraphAlgo_TSPGreedyOpt extends GraphAlgo {

    // Anfang Attribute

    public String getBezeichnung() {
        return "TSP (Greedy: Knoten + Optimierung)";
    }

    // Anfang Methoden
    public void fuehreAlgorithmusAus() {
        Knoten start = this.getStartKnoten();
        Knoten akt = start;
        List<Knoten> reihung = new ArrayList<Knoten>();
        reihung.add(start);
        Kante min;
        do{
            List<Kante> kanten = g.getAusgehendeKanten(akt);
            min = null;
            double mindist = Double.MAX_VALUE;
            for(Kante k: kanten) {
                if(!k.getAnderesEnde(akt).isMarkiert()) {
                    if(min == null || mindist > k.getGewicht()) {
                        min = k;
                        mindist = k.getGewicht();
                    }
                }
            }
            akt.setMarkiert(true);
            if(min != null) {
                min.setMarkiert(true);
                akt = min.getAnderesEnde(akt);
                reihung.add(akt);
            }
            step();
        }while (min!=null);
        g.getKante(akt,start).setMarkiert(true);
        step();
        // Versuch der Optimierung
        for(int o=0; o<5 ; o++)
            for(Knoten kn : g.getAlleKnoten()) {
                List<Kante> markierteKanten = g.getAusgehendeKanten(kn, ka->ka.isMarkiert());
                for(Kante k: markierteKanten){
                    k.setMarkiert(false);
                }
                g.getKante(markierteKanten.get(0).getAnderesEnde(kn),markierteKanten.get(1).getAnderesEnde(kn)).setMarkiert(true);
                double laengeBest = Double.MAX_VALUE;
                Kante kanteBest = null;
                for(Kante k2: g.getAlleKanten()) {
                    if(k2.isMarkiert()) {
                        double laengeNeu = g.getKante(k2.getStart(),kn).getGewicht()+g.getKante(k2.getZiel(),kn).getGewicht()-k2.getGewicht() ;
                        if(laengeBest > laengeNeu) {
                            laengeBest = laengeNeu;
                            kanteBest = k2;
                        }
                    }
                }
                kanteBest.setMarkiert(false);
                g.getKante(kanteBest.getStart(),kn).setMarkiert(true);
                g.getKante(kanteBest.getZiel(),kn).setMarkiert(true); 
                step();
            }
        step(); 
        melde("Rundreise gefunden:"+ getInfo());

    } // end of for

    public double getLaenge() {
        List<Kante> kanten = g.getAlleKanten();
        double laenge = 0;
        for(Kante k:kanten) {
            if(k.isMarkiert()) laenge+=k.getGewicht();
        }
        return laenge;
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

        return "Weglänge ("+anz+" von "+g.getAnzahlKnoten()+" Kanten): "+laenge+" km.";
    }
    // Ende Methoden

}

