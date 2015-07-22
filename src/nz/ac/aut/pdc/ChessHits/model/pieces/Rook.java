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
 * Rook can move vertically or horizontally on chessboard
 *
 * @author Yue Li
 * @version 31-07-13 class is built. move method implemented
 */
public class Rook extends Piece {

    private final String STRING_REPRESENTATION = "R";
    private boolean isAbleToCastle;

    public Rook(Board board, int hitPoint, Position position, Color color) {
        super(board, hitPoint, position, color);
        this.isAbleToCastle = true;
    }

    @Override
    public boolean move(Position targetPosition) {
        boolean isMoveSuccessful = false;
        Position currentPos = super.getCurrentPosition();
        if (currentPos.getColumn() == targetPosition.getColumn() && currentPos.getRow() != targetPosition.getRow()
                || currentPos.getRow() == targetPosition.getRow() && currentPos.getColumn() != targetPosition.getColumn()) {
            isMoveSuccessful = true;
        }
        return isMoveSuccessful;
    }

    public void disableCastle() {
        this.isAbleToCastle = false;
    }

    public boolean getCastleStatus() {
        return this.isAbleToCastle;
    }

    @Override
    public String getStringRepresentation() {
        return super.determineColor() + this.STRING_REPRESENTATION;
    }

    @Override
    public Collection<Square> allPossibleMoves(Position end) {
        Collection<Square> squares = new HashSet<>();
        Board board = super.getBoard();
        boolean isVertical = false, isHorizontal = false;
        boolean isOccupiedNotFound = true;
        boolean shouldAddElement = true;
        if (getCurrentPosition().getColumn() == end.getColumn()) {
            //vertical
            isVertical = true;
        } else if (getCurrentPosition().getRow() == end.getRow()) {
            //horizontal
            isHorizontal = true;
        }
        Position tempPos = null;
        if (isVertical && getCurrentPosition().getRow() <= end.getRow()) {
            int row = getCurrentPosition().getRow() + 1;
            for (int index = row; index <= end.getRow(); index++) {
                tempPos = board.getPositions()[index][getCurrentPosition().getColumn()];
                Square temSquare = board.getSquare(tempPos);
                isOccupiedNotFound = temSquare.isSquareAvailable();
                if (shouldAddElement) {
                    squares.add(temSquare);
                }
                if (!isOccupiedNotFound) {
                    shouldAddElement = false;
                }
            }
        } else if (isVertical && getCurrentPosition().getRow() >= end.getRow()) {
            int row = getCurrentPosition().getRow() - 1;
            for (int index = row; index >= end.getRow(); index--) {
                tempPos = board.getPositions()[index][getCurrentPosition().getColumn()];
                Square temSquare = board.getSquare(tempPos);
                isOccupiedNotFound = !temSquare.isSquareAvailable();
                if (shouldAddElement) {
                    squares.add(temSquare);
                }
                if (isOccupiedNotFound) {
                    shouldAddElement = false;
                }
            }
        } else if (isHorizontal && getCurrentPosition().getColumn() >= end.getColumn()) {
            int col = getCurrentPosition().getColumn() - 1;
            for (int index = col; index >= end.getColumn(); index--) {
                tempPos = board.getPositions()[getCurrentPosition().getRow()][index];
                Square temSquare = board.getSquare(tempPos);
                isOccupiedNotFound = !temSquare.isSquareAvailable();
                if (shouldAddElement) {
                    squares.add(temSquare);
                }
                if (isOccupiedNotFound) {
                    shouldAddElement = false;
                }
            }
        } else if (isHorizontal && getCurrentPosition().getColumn() <= end.getColumn()) {
            int col = getCurrentPosition().getColumn() + 1;
            for (int index = col; index <= end.getColumn(); index++) {
                tempPos = board.getPositions()[getCurrentPosition().getRow()][index];
                Square temSquare = board.getSquare(tempPos);
                isOccupiedNotFound = !temSquare.isSquareAvailable();
                if (shouldAddElement) {
                    squares.add(temSquare);
                }
                if (isOccupiedNotFound) {
                    shouldAddElement = false;
                }
            }
        }

        return squares;
    }
}
