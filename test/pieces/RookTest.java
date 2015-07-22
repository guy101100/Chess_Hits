/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pieces;

import nz.ac.aut.pdc.ChessHits.model.Board;
import nz.ac.aut.pdc.ChessHits.model.Color;
import nz.ac.aut.pdc.ChessHits.model.Position;
import nz.ac.aut.pdc.ChessHits.model.pieces.Rook;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author gl
 */
public class RookTest {

    private Rook rookBlack;
    private Rook rookWhite;
    private Position position;
    private Board board;

    public RookTest() {
    }

    @Before
    public void setUp() {
        this.board = new Board();
        position = new Position(1, 1);
        rookBlack = new Rook(board, 1, position, Color.BLACK);
        rookWhite = new Rook(board, 1, position, Color.WHITE);
    }

    @After
    public void tearDown() {
        position = null;
        rookBlack = null;
        rookWhite = null;
    }

    /**
     * Test of move method, of class Rook.
     */
    @Test
    public void testMove() {
        Position positionNew = new Position(1, 5);
        assertTrue(rookBlack.move(positionNew));
    }

    /**
     * Test of disableCastle method, of class Rook.
     */
    @Test
    public void testDisableCastle() {
        assertTrue(rookBlack.getCastleStatus());
        rookBlack.disableCastle();
        assertFalse(rookBlack.getCastleStatus());
    }

    /**
     * Test of enableCastle method, of class Rook.
     */
    @Test
    public void testEnableCastle() {
        rookBlack.disableCastle();
        assertFalse(rookBlack.getCastleStatus());
    }

    /**
     * Test of getCastleStatus method, of class Rook.
     */
    @Test
    public void testGetCastleStatus() {

        rookBlack.disableCastle();
        assertFalse(rookBlack.getCastleStatus());
    }

    /**
     * Test of getStringRepresentation method, of class Rook.
     */
    @Test
    public void testGetStringRepresentation() {
        assertEquals("BR", rookBlack.getStringRepresentation());
        assertEquals("WR", rookWhite.getStringRepresentation());

    }
}
