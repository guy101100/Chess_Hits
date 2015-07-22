/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nz.ac.aut.pdc.ChessHits.model.pieces;

import java.util.Collection;
import java.util.HashSet;
import nz.ac.aut.pdc.ChessHits.model.Board;
import nz.ac.aut.pdc.ChessHits.model.Color;
import nz.ac.aut.pdc.ChessHits.model.Position;
import nz.ac.aut.pdc.ChessHits.model.Square;

/**
 * pawn is the most basic piece.
 *
 * @author Yue Li
 * @version 25-07-13 constructed class with basic idea of a pawn can do.
 * @version 26-07-13 detailed how pawn moves. rearranged string representation.
 * promotion functions
 * @version 2-8-13 rearranged move method for pawns
 */
public class Pawn extends Piece {

    private final String STRING_REPRESENTATION = "P";
    private boolean isPromoted;
    private boolean isNotMoved;

    /**
     * constructs a Pawn object
     *
     * @param hitPoint The times of attacks that Pawns can bear
     * @param position The resource position
     * @param color The side that a pawn belongs to
     */
    public Pawn(Board board, int hitPoint, Position position, Color color) {
        super(board, hitPoint, position, color);
        this.isNotMoved = true;
        this.isPromoted = false;
    }

    /**
     * promote a pawn to any piece that the player wants
     *
     * @param board the board contains all the piece for the game
     * @param piece The piece that pawn is gonna be
     */
    public void promote(Board board, Piece piece) {
        Position currentPos = super.getCurrentPosition();
        Square currentSquare = board.getSquare(currentPos);
        currentSquare.removePiece(this);
        currentSquare.addPiece(piece);
        this.isPromoted = true;
    }

    /**
     * get promotion status
     *
     * @return true if pawn is promoted, false otherwise
     */
    public boolean getIsPromoted() {
        return this.isPromoted;
    }

    /**
     * get the status about if the initial move is made. this determines if a
     * pawn is able to move 2 square range.
     *
     * @return true if pawn is not moved yet, false otherwise
     */
    public boolean getInitMoveStatus() {
        return this.isNotMoved;
    }

    /**
     * when a pawn has moved, its initial move status is set as false
     */
    public void deactiviateInitMoveStatus() {
        this.isNotMoved = false;
    }

    /**
     * move a pawn forwards
     *
     * @param board The board contains all the pieces
     * @param targetPosition The position that pawn intends to move to
     * @return true if move is successful, false otherwise
     */
    @Override
    public boolean move(Position targetPosition) {
        boolean isMoveSuccessful = false;
        Position currentPos = super.getCurrentPosition();
        if (getInitMoveStatus() && super.getColor() == Color.BLACK) {
            isMoveSuccessful = currentPos.getColumn() == targetPosition.getColumn() && currentPos.getRow() - targetPosition.getRow() <= 2;
        } else if (!getInitMoveStatus() && super.getColor() == Color.BLACK) {
            isMoveSuccessful = currentPos.getColumn() == targetPosition.getColumn() && currentPos.getRow() - targetPosition.getRow() == 1;
        } else if (getInitMoveStatus() && super.getColor() == Color.WHITE) {
            isMoveSuccessful = currentPos.getColumn() == targetPosition.getColumn() && targetPosition.getRow() - currentPos.getRow() <= 2 && targetPosition.getRow() > currentPos.getRow();
        } else if (!getInitMoveStatus() && super.getColor() == Color.WHITE) {
            isMoveSuccessful = currentPos.getColumn() == targetPosition.getColumn() && targetPosition.getRow() - currentPos.getRow() == 1 && targetPosition.getRow() > currentPos.getRow();
        }

        if (isMoveSuccessful) {

            deactiviateInitMoveStatus();
        }
        return isMoveSuccessful;
    }

    public boolean canForkPosition(Position targetPosition) {
        boolean canFork = false;
        if (super.getColor() == Color.BLACK && Math.abs(targetPosition.getColumn() - super.getCurrentPosition().getColumn()) == 1
                && super.getCurrentPosition().getRow() - targetPosition.getRow() == 1) {
            canFork = true;
        } else if (super.getColor() == Color.WHITE && Math.abs(targetPosition.getColumn() - super.getCurrentPosition().getColumn()) == 1
                && super.getCurrentPosition().getRow() - targetPosition.getRow() == -1) {
            canFork = true;
        }
        return canFork;
    }

    /**
     *
     * get the string representation plus the color of the piece
     *
     * @return the color of piece and string P
     */
    @Override
    public String getStringRepresentation() {

        return super.determineColor() + this.STRING_REPRESENTATION;
    }

    @Override
    public Collection<Square> allPossibleMoves(Position end) {
        Collection<Square> squares = new HashSet<>();
        if (super.getColor() != Color.BLACK) {
            if (getCurrentPosition().getColumn() < end.getColumn()) {
                Position temp = super.getBoard().getPositions()[getCurrentPosition().getRow() + 1][getCurrentPosition().getColumn() + 1];
                Square tempSqr = super.getBoard().getSquare(temp);
                squares.add(tempSqr);
            } else if (getCurrentPosition().getColumn() > end.getColumn()) {
                Position temp = super.getBoard().getPositions()[getCurrentPosition().getRow() + 1][getCurrentPosition().getColumn() - 1];
                Square tempSqr = super.getBoard().getSquare(temp);
                squares.add(tempSqr);
            } else if (getCurrentPosition().getColumn() == end.getColumn()) {
                Position temp1 = super.getBoard().getPositions()[getCurrentPosition().getRow() + 1][getCurrentPosition().getColumn()];
                Position temp2 = super.getBoard().getPositions()[getCurrentPosition().getRow() + 2][getCurrentPosition().getColumn()];
                Square tempSqr1 = super.getBoard().getSquare(temp2);
                Square tempSqr2 = super.getBoard().getSquare(temp1);
                if (super.getBoard().getSquare(temp1).isSquareAvailable()) {
                    squares.add(tempSqr1);
                    if (super.getBoard().getSquare(temp2).isSquareAvailable()) {
                        squares.add(tempSqr2);
                    }
                }
            }
        } else {
            if (getCurrentPosition().getColumn() < end.getColumn()) {
                Position temp = super.getBoard().getPositions()[getCurrentPosition().getRow() - 1][getCurrentPosition().getColumn() + 1];
                Square tempSqr = super.getBoard().getSquare(temp);
                squares.add(tempSqr);
            } else if (getCurrentPosition().getColumn() > end.getColumn()) {
                Position temp = super.getBoard().getPositions()[getCurrentPosition().getRow() - 1][getCurrentPosition().getColumn() - 1];
                Square tempSqr = super.getBoard().getSquare(temp);
                squares.add(tempSqr);
            } else if (getCurrentPosition().getColumn() == end.getColumn()) {
                Position temp1 = super.getBoard().getPositions()[getCurrentPosition().getRow() - 1][getCurrentPosition().getColumn()];
                Position temp2 = super.getBoard().getPositions()[getCurrentPosition().getRow() - 2][getCurrentPosition().getColumn()];
                Square tempSqr1 = super.getBoard().getSquare(temp2);
                Square tempSqr2 = super.getBoard().getSquare(temp1);
                if (super.getBoard().getSquare(temp1).isSquareAvailable()) {
                    squares.add(tempSqr1);
                    if (super.getBoard().getSquare(temp2).isSquareAvailable()) {
                        squares.add(tempSqr2);
                    }
                }
            }
        }
        return squares;
    }
}
