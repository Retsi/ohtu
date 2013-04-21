/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import ohtu.verkkokauppa.*;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 *
 * @author retsi
 */
public class KauppaTest {

    public KauppaTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}

    @Test
    public void ostoksenPaaytyttyapankinMetodiaTilisiirtoKutsutaan() {
        // luodaan ensin mock-oliot
        Pankki pankki = mock(Pankki.class);

        Viitegeneraattori viite = mock(Viitegeneraattori.class);
        when(viite.uusi()).thenReturn(1);

        Varasto varasto = mock(Varasto.class);
        when(varasto.saldo(1)).thenReturn(10);
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));

        // sitten testattava kauppa 
        Kauppa k = new Kauppa(varasto, pankki, viite);

        // tehdään ostokset
        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        k.tilimaksu("pekka", "12345");

        // sitten suoritetaan varmistus, että pankin metodia tilisiirto on kutsuttu
        verify(pankki).tilisiirto(anyString(), anyInt(), anyString(), anyString(), anyInt());
    }
    
    @Test
    public void eka(){
        Pankki pankki = mock(Pankki.class);
        Viitegeneraattori viite = mock(Viitegeneraattori.class);
        when(viite.uusi()).thenReturn(1);
        Varasto varasto = mock(Varasto.class);
        when(varasto.saldo(1)).thenReturn(10);
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));
        Kauppa k = new Kauppa(varasto, pankki, viite);
        
        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        k.tilimaksu("Jeesus", "13254");
        
        verify(pankki).tilisiirto(eq("Jeesus"), eq(1), eq("13254"), anyString(), eq(5));
    }
    
    @Test
    public void toka(){
        Pankki pankki = mock(Pankki.class);
        Viitegeneraattori viite = mock(Viitegeneraattori.class);
        when(viite.uusi()).thenReturn(32);
        Varasto varasto = mock(Varasto.class);
        when(varasto.saldo(1)).thenReturn(10);
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));
        when(varasto.saldo(2)).thenReturn(10);
        when(varasto.haeTuote(2)).thenReturn(new Tuote(2, "Bisse", 2));
        Kauppa k = new Kauppa(varasto, pankki, viite);
        
        
        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        k.lisaaKoriin(2);
        k.tilimaksu("Jeesus", "13254");
        
        verify(pankki).tilisiirto(eq("Jeesus"), eq(32), eq("13254"), anyString(), eq(7));
    }
    
    @Test
    public void neljas(){
        Pankki pankki = mock(Pankki.class);
        Viitegeneraattori viite = mock(Viitegeneraattori.class);
        when(viite.uusi()).thenReturn(1337);
        Varasto varasto = mock(Varasto.class);
        when(varasto.saldo(1)).thenReturn(0);
        when(varasto.saldo(2)).thenReturn(10);
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "Kalja", 2));
        when(varasto.haeTuote(2)).thenReturn(new Tuote(2, "Bisse", 2));
        Kauppa k = new Kauppa(varasto, pankki, viite);
        
        
        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        k.lisaaKoriin(2);
        k.tilimaksu("Jeesus", "13254");
        
        verify(pankki).tilisiirto(eq("Jeesus"), eq(1337), eq("13254"), anyString(), eq(2));
    }
    
    @Test
    public void kolmas(){
        Pankki pankki = mock(Pankki.class);
        Viitegeneraattori viite = mock(Viitegeneraattori.class);
        when(viite.uusi()).thenReturn(323);
        Varasto varasto = mock(Varasto.class);
        when(varasto.saldo(2)).thenReturn(10);
        when(varasto.haeTuote(2)).thenReturn(new Tuote(2, "Bisse", 2));
        Kauppa k = new Kauppa(varasto, pankki, viite);
        
        
        k.aloitaAsiointi();
        k.lisaaKoriin(2);
        k.lisaaKoriin(2);
        k.tilimaksu("Jeesus", "13254");
        
        verify(pankki).tilisiirto(eq("Jeesus"), eq(323), eq("13254"), anyString(), eq(4));
    }
    
    @Test
    public void kuudes(){
        Pankki pankki = mock(Pankki.class);
        Viitegeneraattori mockViite = mock(Viitegeneraattori.class);
        when(mockViite.uusi()).thenReturn(1);
        Varasto varasto = mock(Varasto.class);
        when(varasto.saldo(1)).thenReturn(10);
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));
        
        Kauppa k = new Kauppa(varasto, pankki, mockViite);
        
        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        k.tilimaksu("Jeesus", "13254");
        
        verify(mockViite, times(1)).uusi();
        
        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        k.tilimaksu("Jorma", "13224");
        
        verify(mockViite, times(2)).uusi();
    }
    
    @Test
    public void viides(){
        Pankki pankki = mock(Pankki.class);
        Viitegeneraattori mockViite = mock(Viitegeneraattori.class);
        when(mockViite.uusi()).thenReturn(1);
        Varasto varasto = mock(Varasto.class);
        when(varasto.saldo(1)).thenReturn(10);
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));
        
        Kauppa k = new Kauppa(varasto, pankki, mockViite);
        
        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        
        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        k.tilimaksu("Jeesus", "13254");
        verify(pankki).tilisiirto(eq("Jeesus"), anyInt(), eq("13254"), anyString(), eq(5));
    }
}