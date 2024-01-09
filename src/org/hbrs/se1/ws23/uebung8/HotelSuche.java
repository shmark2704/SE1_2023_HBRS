package org.hbrs.se1.ws23.uebung8;

public interface HotelSuche {
    SuchAuftrag auftrag = new SuchAuftrag();
    SuchErgebnis erg = new SuchErgebnis();
    public SuchErgebnis sucheHotel(SuchAuftrag suche);


}
