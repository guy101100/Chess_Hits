/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nz.ac.aut.pdc.ChessHits.model;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author gl
 */
public class PlayerTest {

    Player player;

    public PlayerTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
        this.player = new Player("yl", Color.BLACK);
    }

    @After
    public void tearDown() {
        this.player = null;
    }

    /*
     * Test of setColor method, of class Player.
     *
     @Test
     public void testSetBlackColorSuccessful() {
     Color color = Color.BLACK;
     player.setColor(color);
     assertTrue(Color.BLACK.equals(player.getSelectedColor()));
     }

     @Test
     public void testSetWhiteColorSuccessful() {
     Color color = Color.WHITE;
     player.setColor(color);
     assertFalse(Color.BLACK.equals(player.getSelectedColor()));
     }


     /**
     * Test of getName method, of class Player.
     *
     @Test
     public void testSetNameWhenNamesDifferent() {
     player.setName("hello");
     String result = player.getName();
     assertFalse("name was" + result, "CoolPlayer".equals(result));
     // TODO review the generated test code and remove the default call to fail.
     }

     @Test
     public void testSetNameWhenNamesSame() {
     player.setName("Awesome");
     String result = player.getName();
     assertTrue("name was " + result, "Awesome".equals(result));
     }
     */
    /**
     * Test of getIsTurn method, of class Player.
     */
    @Test
    public void testGetIsTurnWhenPlayerTurn() {
        player.setIsTurn(true);
        boolean result = player.getIsTurn();
        assertTrue(result);
    }

    @Test
    public void testGetIsTurnWhenPlayerIsNotTurn() {
        player.setIsTurn(false);
        boolean result = player.getIsTurn();
        assertFalse(result);
    }
}
