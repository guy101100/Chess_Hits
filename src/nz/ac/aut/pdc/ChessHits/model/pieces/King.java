/*
 * 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nz.ac.aut.pdc.ChessHits.model.pieces;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;
import nz.ac.aut.pdc.ChessHits.model.*;

/**
 * King is the most important piece in the game
 *
 * @author Yue Li
 * @version 24-07-13 class is built, abstract implementation done, probably not
 * the best time to do the move method, basic function is done.
 * @version 25-07-13 the move method implemented. yet to pass proper tests.
 * contains commented lines.
 * @version 2-08-13 add function for castling with a rook
 * @version 5-08-13 refined move method
 */
public class King extends Piece {

    private final String STRING_REPRESENTATION = "K";
    private boolean isAbleToCastle;
    private ChessHitsGame game;

    /**
     * construct a king object
     *
     * @param hitPoint the times of attacks king can take
     * @param position the initial position king is
     * @param color the color of king
     * @param isNotChecked resource check status of king
     * @param isNotCheckmate resource checkmate status of king
     * @param isNotStalemate resource stalemate status of king
     * @param isAbleToCastle resource castle ability of king
     */
    public King(Board board, int hitPoint, Position position, Color color, boolean isAbleToCastle) {
        super(board, hitPoint, position, color);
        this.isAbleToCastle = isAbleToCastle;
    }

    /**
     * get the castle status of king
     *
     * @return true if king is able to perform a castle, false otherwise
     */
    public boolean getCastleStatus() {
        return this.isAbleToCastle;
    }

    /**
     * disable the ability of castling
     */
    public void disableCastle() {
        this.isAbleToCastle = false;
    }

    /**
     * king hits every piece down with only 1 hit
     *
     * @param piece the target
     */
    @Override
    public void attack(Piece piece) {
        while (piece.isAlive()) {
            piece.reduceHP();
        }
    }

    public boolean performCastling() {
        boolean isPerformed = false;
        if (this.getCastleStatus()) {
            Scanner input = new Scanner(System.in);
            System.out.println("Would you wish to perform castling?(y/n)"); //can be replaced by do while loop?
            String userAnwser = input.next();
            if (userAnwser.equals("y")) {
            }
        }
        return isPerformed;
    }

    /**
     * move King to a new position on the board
     *
     * @param board The resource board
     * @param targetPosition The target position
     * @return true if move is legal, false otherwise
     */
    @Override
    public boolean move(Position targetPosition) {
        boolean isMoveSucessful = false;
        int horizontalDifference = targetPosition.getColumn() - super.getCurrentPosition().getColumn();
        int verticalDifference = targetPosition.getRow() - super.getCurrentPosition().getRow();

        if (horizontalDifference >= -1 && horizontalDifference <= 1 && verticalDifference >= -1 && verticalDifference <= 1) {

            isMoveSucessful = true;
        }

        return isMoveSucessful;
    }

    /**
     * string representation
     *
     * @return piece's color+"K"
     */
    @Override
    public String getStringRepresentation() {
        return super.determineColor() + this.STRING_REPRESENTATION;
    }

    @Override
    public Collection<Square> allPossibleMoves(Position end) {
        Collection<Square> squares = new HashSet<>();
        ArrayList<Position> positions = new ArrayList<>();
        Board board = super.getBoard();
        int startRow = getCurrentPosition().getRow(), startCol = getCurrentPosition().getColumn(), endRow = end.getRow(), endCol = end.getColumn();

        if (startRow > 0 && startCol > 0 && startCol - endCol == 1 && startRow - endRow == 1) {
            Position northWest = board.getPositions()[startRow - 1][startCol - 1];
            positions.add(northWest);
        }
        if (startRow > 1 && startCol == endCol && startRow - endRow == 1) {
            Position north = board.getPositions()[startRow - 1][startCol];
            positions.add(north);
        }
        if (startRow > 1 && startCol < 7 && startCol - endCol == -1 && startRow - endRow == 1) {
            Position northEast = board.getPositions()[startRow - 1][startCol + 1];
            positions.add(northEast);
        }
        if (startCol > 0 && startCol - endCol == 1 && startRow == endRow) {
            Position west = board.getPositions()[startRow][startCol - 1];
            positions.add(west);
        }
        if (startCol < 7 && startCol - endCol == -1 && startRow == endRow) {
            Position east = board.getPositions()[startRow][startCol + 1];
            positions.add(east);
        }
        if (startRow < 7 && startCol > 0 && startCol - endCol == 1 && startRow - endRow == -1) {
            Position sthWst = board.getPositions()[startRow + 1][startCol - 1];
            positions.add(sthWst);
        }
        if (startRow < 7 && startCol == endCol && startRow - endRow == -1) {
            Position sth = board.getPositions()[startRow + 1][startCol];
            positions.add(sth);
        }
        if (startRow < 7 && startCol < 7 && startCol - endCol == -1 && startRow - endRow == -1) {
            Position sthEst = board.getPositions()[startRow + 1][startCol + 1];
            positions.add(sthEst);
        }

        for (Position temPos : positions) {
            Square temSquare = board.getSquare(temPos);
            squares.add(temSquare);
        }
        return squares;
    }
}