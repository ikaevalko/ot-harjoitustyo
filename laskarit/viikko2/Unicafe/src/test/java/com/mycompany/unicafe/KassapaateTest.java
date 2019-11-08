package com.mycompany.unicafe;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class KassapaateTest {
    
    Kassapaate kassapaate;
    Maksukortti kortti;
    
    @Before
    public void setUp() {
        kassapaate = new Kassapaate();
        kortti = new Maksukortti(1000);
    }

    @Test
    public void konstruktoriAsettaaMuuttujatOikein() {
        
        assertEquals(100000, kassapaate.kassassaRahaa());
        assertEquals(0, kassapaate.edullisiaLounaitaMyyty());
        assertEquals(0, kassapaate.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void syoEdullisestiToimiiRiittavallaKateismaksulla() {
        
        assertEquals(20, kassapaate.syoEdullisesti(260));
        assertEquals(100240, kassapaate.kassassaRahaa());
        assertEquals(1, kassapaate.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void syoEdullisestiToimiiRiittamattomallaKateismaksulla() {
        
        assertEquals(220, kassapaate.syoEdullisesti(220));
        assertEquals(100000, kassapaate.kassassaRahaa());
        assertEquals(0, kassapaate.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void syoMaukkaastiToimiiRiittavallaKateismaksulla() {
        
        assertEquals(20, kassapaate.syoMaukkaasti(420));
        assertEquals(100400, kassapaate.kassassaRahaa());
        assertEquals(1, kassapaate.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void syoMaukkaastiToimiiRiittamattomallaKateismaksulla() {
        
        assertEquals(380, kassapaate.syoMaukkaasti(380));
        assertEquals(100000, kassapaate.kassassaRahaa());
        assertEquals(0, kassapaate.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void syoEdullisestiToimiiRiittavallaKorttimaksulla() {
        
        assertTrue(kassapaate.syoEdullisesti(kortti));
        assertEquals(760, kortti.saldo());
        assertEquals(100000, kassapaate.kassassaRahaa());
        assertEquals(1, kassapaate.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void syoEdullisestiToimiiRiittamattomallaKorttimaksulla() {
        
        kassapaate.syoEdullisesti(kortti);
        kassapaate.syoEdullisesti(kortti);
        kassapaate.syoEdullisesti(kortti);
        kassapaate.syoEdullisesti(kortti);
        assertFalse(kassapaate.syoEdullisesti(kortti));
        assertEquals(40, kortti.saldo());
        assertEquals(100000, kassapaate.kassassaRahaa());
        assertEquals(4, kassapaate.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void syoMaukkaastiToimiiRiittavallaKorttimaksulla() {
        
        assertTrue(kassapaate.syoMaukkaasti(kortti));
        assertEquals(600, kortti.saldo());
        assertEquals(100000, kassapaate.kassassaRahaa());
        assertEquals(1, kassapaate.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void syoMaukkaastiToimiiRiittamattomallaKorttimaksulla() {
        
        kassapaate.syoMaukkaasti(kortti);
        kassapaate.syoMaukkaasti(kortti);
        assertFalse(kassapaate.syoMaukkaasti(kortti));
        assertEquals(200, kortti.saldo());
        assertEquals(100000, kassapaate.kassassaRahaa());
        assertEquals(2, kassapaate.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void kortinLataaminenKasvattaaSaldoaJaKassanRahamaaraa() {
        
        kassapaate.lataaRahaaKortille(kortti, 500);
        assertEquals(1500, kortti.saldo());
        assertEquals(100500, kassapaate.kassassaRahaa());
    }
    
    @Test
    public void korttiaEiVoiLadataNegatiivisellaSummalla() {
        
        kassapaate.lataaRahaaKortille(kortti, -500);
        assertEquals(1000, kortti.saldo());
        assertEquals(100000, kassapaate.kassassaRahaa());
    }
}
