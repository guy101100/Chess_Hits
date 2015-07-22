/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nz.ac.aut.pdc.ChessHits.model.pieces;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import nz.ac.aut.pdc.ChessHits.model.Board;
import nz.ac.aut.pdc.ChessHits.model.Color;
import nz.ac.aut.pdc.ChessHits.model.Position;
import nz.ac.aut.pdc.ChessHits.model.Square;

/**
 *
 * @author Yue Li
 * @version 31-08-13 fixed knight move bug
 */
public class Knight extends Piece {

    private final String STRING_REPRESENTATION = "N";

    public Knight(Board board, int hitPoint, Position position, Color color) {
        super(board, hitPoint, position, color);
    }

    @Override
    public boolean move(Position targetPosition) {
        boolean isMoveSuccessful = false;
        int currentRow = super.getCurrentPosition().getRow();
        int currentCol = super.getCurrentPosition().getColumn();
        if (Math.abs(currentRow - targetPosition.getRow()) == 2 && Math.abs(currentCol - targetPosition.getColumn()) == 1
                || Math.abs(currentRow - targetPosition.getRow()) == 1 && Math.abs(currentCol - targetPosition.getColumn()) == 2) {
            isMoveSuccessful = true;
        }
        return isMoveSuccessful;

    }

    @Override
    public String getStringRepresentation() {
        return super.determineColor() + this.STRING_REPRESENTATION;
    }

    @Override
    public Collection<Square> allPossibleMoves(Position end) {
        Board board = super.getBoard();
        Position nwt = null, nwd = null, net = null, ned = null, swt = null, swd = null, set = null, sed = null;
        //Position[] positions = {nwt, nwd, net, ned, swt, swd, set, sed};
        ArrayList<Position> positions = new ArrayList<>();
        Collection<Square> squares = new HashSet<>();
        int currentCol = getCurrentPosition().getColumn();
        int currentRow = getCurrentPosition().getRow();
//        if (currentRow > 0 && currentCol > 0 && board.getPositions()[currentRow - 2][currentCol - 1] != null) {
//            nwt = board.getPositions()[currentRow - 2][currentCol - 1];
//            positions.add(nwt);
//        }
//        if (currentRow > 0 && currentCol > 1 && board.getPositions()[currentRow - 1][currentCol - 2] != null) {
//            nwd = board.getPositions()[currentRow - 1][currentCol - 2];
//            positions.add(nwd);
//        }
//        if (currentRow > 2 && currentCol < 6 && board.getPositions()[currentRow - 2][currentCol + 1] != null) {
//            net = board.getPositions()[currentRow - 2][currentCol + 1];
//            positions.add(net);
//        }
//        if (currentRow > 0 && currentCol < 5 && board.getPositions()[currentRow - 1][currentCol + 2] != null) {
//            ned = board.getPositions()[currentRow - 1][currentCol + 2];
//            positions.add(ned);
//        }
//        if (currentRow < 7 && currentCol > 1 && board.getPositions()[currentRow + 1][currentCol - 2] != null) {
//            swt = board.getPositions()[currentRow + 1][currentCol - 2];
//            positions.add(swt);
//        }
//        if (currentRow < 6 && currentCol > 0 && board.getPositions()[currentRow + 2][currentCol - 1] != null) {
//            swd = board.getPositions()[currentRow + 2][currentCol - 1];
//            positions.add(swd);
//        }
//        if (currentRow < 7 && currentCol < 6 && board.getPositions()[currentRow + 1][currentCol + 2] != null) {
//            set = board.getPositions()[currentRow + 1][currentCol + 2];
//            positions.add(set);
//        }
//        if (currentRow < 6 && currentCol < 6
//                && board.getPositions()[currentRow + 2][currentCol + 1] != null) {
//            sed = board.getPositions()[currentRow + 2][currentCol + 1];
//            positions.add(sed);
//        }
        if (currentRow > 1 && currentCol > 0 && board.getPositions()[currentRow - 2][currentCol - 1] != null) {
            nwt = board.getPositions()[currentRow - 2][currentCol - 1];
            positions.add(nwt);
        }
        if (currentRow > 0 && currentCol > 1 && board.getPositions()[currentRow - 1][currentCol - 2] != null) {
            nwd = board.getPositions()[currentRow - 1][currentCol - 2];
            positions.add(nwd);
        }
        if (currentRow > 1 && currentCol < 7 && board.getPositions()[currentRow - 2][currentCol + 1] != null) {
            net = board.getPositions()[currentRow - 2][currentCol + 1];
            positions.add(net);
        }
        if (currentRow > 0 && currentCol < 5 && board.getPositions()[currentRow - 1][currentCol + 2] != null) {
            ned = board.getPositions()[currentRow - 1][currentCol + 2];
            positions.add(ned);
        }
        if (currentRow < 7 && currentCol > 1 && board.getPositions()[currentRow + 1][currentCol - 2] != null) {
            swt = board.getPositions()[currentRow + 1][currentCol - 2];
            positions.add(swt);
        }
        if (currentRow < 6 && currentCol > 0 && board.getPositions()[currentRow + 2][currentCol - 1] != null) {
            swd = board.getPositions()[currentRow + 2][currentCol - 1];
            positions.add(swd);
        }
        if (currentRow < 7 && currentCol < 6 && board.getPositions()[currentRow + 1][currentCol + 2] != null) {
            set = board.getPositions()[currentRow + 1][currentCol + 2];
            positions.add(set);
        }
        if (currentRow < 6 && currentCol < 7
                && board.getPositions()[currentRow + 2][currentCol + 1] != null) {
            sed = board.getPositions()[currentRow + 2][currentCol + 1];
            positions.add(sed);
        }


        for (Position pos : positions) {
            if (pos != null) {
                Square tempSqr = board.getSquare(pos);

                //if (tempSqr.isSquareAvailable()) {
                squares.add(tempSqr);
                //}
            }
        }
        return squares;
    }
}
