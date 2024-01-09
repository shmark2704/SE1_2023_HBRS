package org.hbrs.se1.ws23.uebung8;

public class Adapter implements HotelSuche {

    private QueryObject obj;

    @Override
    public SuchErgebnis sucheHotel(SuchAuftrag suche) {
        return null;
    }

    private QueryObject transform(SuchAuftrag suche) {
        return null;
    }

    public QueryObject getObj() {
        return obj;
    }
}
