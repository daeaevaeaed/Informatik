package algorithmen;

import java.util.List;
import java.nio.file.*;
import java.util.Comparator;
import java.util.Collections;

import graph.*;

/**
 *
 * Dieser Algorithmus sucht einen möglichst kurzen Hamilton-Kreis (Traveling
 * Salesman Problem).
 * Algorithmus: Greedy
 * Strategie: Sortiere Kanten der Länge nach. Füge sie der Reihe nach der Route hinzu, wenn nicht schon ein
 * Weg zwischen den beiden Knoten vorhanden ist und die Knoten nicht schon Grad zwei erreicht haben.
 * vgl. Minimal Spanning Tree (Kruskal)
 *
 * @version 1.0 from 11.12.2020
 * @author Thomas Schaller
 */

public class GraphAlgo_TSPGreedy2 extends GraphAlgo {

    // Anfang Attribute

    public String getBezeichnung() {
        return "TSP (Greedy: kürzeste Kante)";
    }

    // Anfang Methoden
    public void fuehreAlgorithmusAus() {
        int farbe = 1;
        int anzkanten = 0;
        List<Kante> kanten = g.getAlleKanten();
        List<Knoten> knoten = g.getAlleKnoten();
        info("Hole eine Liste aller Kanten und eine aller Knoten");
        Collections.sort(kanten);
        info("Sortiere Kanten aufsteigend");
        info("Wiederhole für jede Kante");
        for (Kante k: kanten) {
            info("Bearbeite Kante mit Gewicht: "+k.getGewicht());
            infoIndentMore();
            int f1 = k.getStart().getFarbe();
            int f2 = k.getZiel().getFarbe();
            if(f1 == 0 && f2 == 0) {
                info("Beide Knoten gehören noch zu keinem Teilgraphen");
                k.getStart().setFarbe(farbe);
                k.getZiel().setFarbe(farbe);
                k.setMarkiert(true);
                anzkanten++;
                info("=> setze beide auf Farbe "+farbe+" und markiere die Kante");
                farbe++;
            } else
            if(f1 == 0 && g.getAusgehendeKanten(k.getZiel(), k2->k2.isMarkiert()).size()==1) {
                info("Der Knoten Nr. "+g.getNummer(k.getStart())+" gehört noch zu keinem Teilgraphen und verlängert eine Route");
                k.getStart().setFarbe(f2);
                k.setMarkiert(true);
                anzkanten++;
                info("=> setze ihn auf die Farbe des Knotens Nr. "+g.getNummer(k.getZiel())+" und markiere die Kante");
            } else
            if(f2 == 0 && g.getAusgehendeKanten(k.getStart(), k2->k2.isMarkiert()).size()==1) {
                info("Der Knoten Nr. "+g.getNummer(k.getZiel())+" gehört noch zu keinem Teilgraphen und verlängert eine Route");
                k.getZiel().setFarbe(f1);
                k.setMarkiert(true);
                anzkanten++;
                info("=> setze ihn auf die Farbe des Knotens Nr. "+g.getNummer(k.getStart())+" und markiere die Kante");
            } else
            if(f1 == f2) {
                if(anzkanten == g.getAnzahlKnoten()-1 && istRoutenende(k.getZiel()) && istRoutenende(k.getStart())){
                    k.setMarkiert(true);
                    info("=> markiere die Kante und schließe damit die Rundreise");  
                    infoIndentLess();
                    step();
                    break;
                } else {
                    info("Beide Knoten gehören zum gleichen Teilgraphen");
                }
            } else 
            if(istRoutenende(k.getZiel()) && istRoutenende(k.getStart())){
                info("Beide Knoten gehören zu unterschiedlichen Teilgraphen, die vereinigt werden können.");
                int min = Math.min(f1,f2);
                int max = Math.max(f1,f2);
                for(Knoten k1 : knoten) {
                    if(k1.getFarbe() == max) k1.setFarbe(min);
                }
                info("=> färbe alle Knoten mit Farbe "+max+" mit der Farbe "+min);
                k.setMarkiert(true);
                anzkanten++;
                info("   und markiere die Kante");
            }
            infoIndentLess();
            step();
        }
        melde("Rundreise gefunden:"+ getInfo());
    }

    private boolean istRoutenende(Knoten k) {
        return g.getAusgehendeKanten(k, k2->k2.isMarkiert()).size()==1;
    }
    // Knoten start = this.getStartKnoten();

    // List<Kante> kanten = g.getAlleKanten();
    // kanten.sort(Comparator.comparingDouble(Kante::getGewicht));

    // for(Kante k: kanten) {
    // for (Knoten v: g.getAlleKnoten()) v.setBesucht(false);
    // if(!findeWeg(k.getStart(), k.getZiel()) && bestimmeGrad(k.getStart())!=2 && bestimmeGrad(k.getZiel())!=2) {
    // k.setMarkiert(true);
    // } else {
    // k.setMarkiert(true);
    // boolean alleZwei=true;
    // for(Knoten v: g.getAlleKnoten()) {
    // if(bestimmeGrad(v) != 2) {
    // alleZwei = false;
    // }
    // }
    // if(alleZwei) {
    // break;
    // }
    // k.setMarkiert(false);
    // }
    // step(); 
    // } // end of for
    // step();
    // melde("Rundreise gefunden:"+ getInfo());
    // }

    private int bestimmeGrad(Knoten k) {
        List<Kante> kantenV = g.getAusgehendeKanten(k, k2->k2.isMarkiert());
        return kantenV.size();
    }

    // /**
    // * Hilfsmethode zum kurzsichtigen Algorithmus.
    // * Findet die minimale Kante von einem gegebenen StartKnoten.
    // *
    // * @param  Knoten startKnoten  Der StartKnoten, von dem aus die Adjazenzliste durchlaufen wird
    // * @return  Kante   Die gesuchte minimale Kante
    // */
    // public boolean findeWeg(Knoten s, Knoten z) {
    // if(s==z) return true;

    // boolean gefunden = false;
    // s.setBesucht(true);
    // List<Kante> kanten = g.getAusgehendeKanten(s);
    // for (Kante k: kanten) {
    // if(k.isMarkiert()) { // Nur markierte Kanten zaehlen als Weg
    // if(!k.getAnderesEnde(s).isBesucht()) {
    // if(findeWeg(k.getAnderesEnde(s), z)) return true;
    // }
    // }
    // } // end of for
    // return false;
    // }

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
