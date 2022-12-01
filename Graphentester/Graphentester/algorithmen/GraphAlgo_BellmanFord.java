package algorithmen;

import java.util.List;
import java.util.Collections;
import java.nio.file.*;

import graph.*;
/**
 * Dieser Algorithmus findet die kürzesten Pfade in einem gewichteten Graphen.
 * Algorithmus: Bellman-Ford
 *
 * @version 1.0 from 10.12.2020
 * @author Thomas Schaller
 */

public class GraphAlgo_BellmanFord extends GraphAlgo {

    // Anfang Attribute

    public  String getBezeichnung() {
        return "Kürzester Pfad (Bellman-Ford)";
    }

    private String knoteninfo(Knoten k) {
        if (!k.getInfotext().equals("")) {
            return k.getInfotext()+" (Wert "+k.getDoubleWert()+")";
        } else {
            return "Knoten Nr. "+g.getNummer(k)+" (Wert "+k.getDoubleWert()+")";
        }
    }

    // Anfang Methoden
    public void fuehreAlgorithmusAus() {
        if (g.getAnzahlKnoten()==0) {
            return;
        } 
        for(Knoten k : g.getAlleKnoten()) {
            k.setWert(1000);
        }
        info("Setze alle Entfernungen auf unendlich (1000)");
        getStartKnoten().setWert(0);
        info("Setze Startknoten auf Entfernung 0");
        step();
        for(int i = 0; i < g.getAnzahlKnoten()-1; i++) {
            info(""+i+". Durchgang");
            infoIndentMore();
            for(Kante k: g.getAlleKanten()) {
                info("Kante von "+knoteninfo(k.getStart())+" nach "+knoteninfo(k.getZiel()));
                Knoten von = k.getStart();
                Knoten nach = k.getZiel();
                if(von.getDoubleWert()+k.getGewicht() < nach.getDoubleWert()){
                    nach.setWert(von.getDoubleWert()+k.getGewicht());
                    List<Kante> alterWeg = g.getEingehendeKanten(nach, ka -> ka.isMarkiert());
                    if(alterWeg.size()>0) alterWeg.get(0).setMarkiert(false);
                    info("Neue Entfernung für "+knoteninfo(nach)+":"+nach.getDoubleWert());
                    k.setMarkiert(true);
                }
                if(!g.isGerichtet() && nach.getDoubleWert()+k.getGewicht() < von.getDoubleWert()){
                    von.setWert(nach.getDoubleWert()+k.getGewicht());
                    info("Neue Entfernung für "+knoteninfo(von)+":"+von.getDoubleWert());
                    List<Kante> alterWeg = g.getEingehendeKanten(von, ka -> ka.isMarkiert());
                    if(alterWeg.size()>0) alterWeg.get(0).setMarkiert(false);
                    k.setMarkiert(true);
                }
                step();
            }
            infoIndentLess();
            step();
        }
        info("Zyklenkontrolle");
        for(Kante k: g.getAlleKanten()) {
            if(k.getStart().getDoubleWert()+k.getGewicht() < k.getZiel().getDoubleWert()){
                melde("Es gibt einen Zyklus negativen Gewichts");
                info("Es gibt einen Zyklus negativen Gewichts");
                g.initialisiereAlleKnoten();
                return;
            }
        }
        step();
    } // end of for

    // Ende Methoden
}
