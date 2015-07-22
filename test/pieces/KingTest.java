/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pieces;

import nz.ac.aut.pdc.ChessHits.model.Board;
import nz.ac.aut.pdc.ChessHits.model.Color;
import nz.ac.aut.pdc.ChessHits.model.Position;
import nz.ac.aut.pdc.ChessHits.model.pieces.King;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author gl
 */
public class KingTest {

    private King king;
    private King kingCheck;
    private King stalemate;
    private King checkMate;
    private King kingCastle;
    private Position position;
    private Board board;

    public KingTest() {
    }

    @Before
    public void setUp() {
        position = new Position(1, 1);
        king = new King(board, 1, position, Color.WHITE, false);
        kingCheck = new King(board, 1, position, Color.WHITE, false);
        stalemate = new King(board, 1, position, Color.WHITE, false);
        checkMate = new King(board, 1, position, Color.WHITE, false);
        kingCastle = new King(board, 1, position, Color.WHITE, true);
        board = new Board();
    }

    @After
    public void tearDown() {
        king = null;
        kingCheck = null;
        stalemate = null;
        checkMate = null;
        kingCastle = null;
        position = null;
        board = null;
    }

    /**
     * Test of getCastleStatus method, of class King.
     */
    @Test
    public void testGetCastleStatus() {

        assertTrue(kingCastle.getCastleStatus());
        assertFalse(king.getCastleStatus());
    }

    /**
     * Test of disableCastle method, of class King.
     */
    @Test
    public void testDisableCastle() {

        assertTrue(kingCastle.getCastleStatus());
        kingCastle.disableCastle();
        assertFalse(kingCastle.getCastleStatus());
    }


    /**
     * Test of move method, of class King.
     */
    @Test
    public void testMoveSuccessful() {
        Position positionNew = new Position(1, 2);
        assertTrue(king.move(positionNew));
    }

    @Test
    public void testMoveUnableMoveToNonAdjacentPosition() {
        Position positionEast = new Position(1, 3);
        Position posSouth = new Position(3, 1);
        assertFalse(king.move(positionEast));
        assertFalse(king.move(posSouth));
    }

    /**
     * Test of getStringRepresentation method, of class King.
     */
    @Test
    public void testGetStringRepresentation() {
        assertEquals("WK", king.getStringRepresentation());

    }
}
