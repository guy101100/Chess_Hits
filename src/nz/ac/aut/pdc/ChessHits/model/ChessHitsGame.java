/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nz.ac.aut.pdc.ChessHits.model;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.*;
import static nz.ac.aut.pdc.ChessHits.model.Color.BLACK;
import static nz.ac.aut.pdc.ChessHits.model.Color.WHITE;
import nz.ac.aut.pdc.ChessHits.model.pieces.*;
import userDB.UserDatabase;

/**
 * the wrapper class of ChessHitsGame.
 *
 * @author Yue Li
 * @version 31-07-13 class is built, implemented Chess board initialization
 * @version 01-08-13 the initialization function is done. loading data should be
 * at the root directory of the project. the game class is able to read from txt
 * file.
 * @version 20-08-13 implemented draft move control.
 */
public class ChessHitsGame {

    // constants
//    private static final String FILENAME = "SquareData.txt";
    //private static final String PLAYER_DATA = "PlayerData/PlayerData.txt";
    //private static final File USER_FILE = new File(PLAYER_DATA);
//    private static final String GAME_SAVE_DATA = "GameData/GameData.txt";
//    private static final File GAME_SAVE_FILE = new File(GAME_SAVE_DATA);
    // no function atm
//    private FileOutputStream playerFileOutput;
//    private OutputStreamWriter osw;
//    private Writer playerFileWriter;
    private Scanner userInputReader;
    private Board board;
    private Player blackPlayer;
    private Player whitePlayer;
    private boolean isGameAppRunning;
    private boolean isGameRunning;
//    private boolean arePLayers;
    private boolean firstSelected = false;
    private Square squareMove;
    private boolean whiteTurn;
//    private UserDatabase userDB;

    /**
     * create a new game.
     */
    public ChessHitsGame() {
        //try {
//            this.playerFileOutput = new FileOutputStream(ChessHitsGame.USER_FILE);
        //this.osw = new OutputStreamWriter(this.playerFileOutput);
        //this.playerFileWriter = new BufferedWriter(osw);
        //      } catch (FileNotFoundException ex) {
        //        Logger.getLogger(ChessHitsGame.class.getName()).log(Level.SEVERE, null, ex);
        //  }

        //this.userDB.createTable();
        this.board = new Board();
        generateRankPieces(BLACK);
        generateRankPieces(WHITE);
        generatePawns(BLACK);
        generatePawns(WHITE);
    }

//    public void newGame() {
//        this.isGameRunning = true;
//        this.board = new Board();
//        if (this.arePLayers) {
//            initializePlayers();
//            this.arePLayers =false;
//        }
//        initializeGameFromFile(FILENAME);
//        controlPlayerTurns();
//    }
//
//    public void stopGame() {
//        System.out.println("Current game has stopped.");
//        this.isGameRunning = false;
//        menu();
//    }
//
//    public void endGame() {
//        System.out.println("It is glad to have your effort on ChessHits. \nThank you\n\t\t\t Guy & Yue");
//        this.isGameAppRunning = false;
//        this.isGameRunning = false;
//    }
//
//    public void controlPlayerTurns() {
//        while (this.isGameAppRunning && this.isGameRunning) {
//            if (this.whitePlayer.getIsTurn()) {
//                playerTurn(whitePlayer);
//            } else {
//                playerTurn(blackPlayer);
//            }
//        }
//    }
//    public final void playerTurn(Player player) {
//        boolean yetToMove = true;
//        while (yetToMove && this.isGameRunning) {
//            System.out.println(player.getName() + "'s turn");
//            Position currentPosition = userSelecteMovingPosition(player, false);
//            Position newPosition = null;
//            if (currentPosition != null) {
//                newPosition = userSelecteMovingPosition(player, true);
//            }//if the move is sucessful if not will repete untill valid
//            if (newPosition != null && movePlayerPiece(currentPosition, newPosition)) {
//                yetToMove = false;
//            }
//            board.draw();
////            }
//            Color tempColor = player.getSelectedColor();
//            switch (tempColor) {
//                case WHITE:
//                    blackPlayer.setIsTurn(true);
//                    whitePlayer.setIsTurn(false);
//                    break;
//                case BLACK:
//                    blackPlayer.setIsTurn(false);
//                    whitePlayer.setIsTurn(true);
//            }
//        }
//
//    }
    private boolean isPawnAbleFork(Piece piece, Position toPos) {
        boolean isSuccessful = false;
        Pawn forkPawn = (Pawn) piece;
        Piece anotherPiece = null;
        if (this.board.getSquare(toPos).getOccupiedPiece() != null) {
            anotherPiece = this.board.getSquare(toPos).getOccupiedPiece();
        }
        //black
        boolean isOkayToForkPiece = forkPawn.canForkPosition(toPos) && anotherPiece != null && !this.board.getSquare(toPos).isSquareAvailable();
        if (isOkayToForkPiece) {
            isSuccessful = true;
        }
        return isSuccessful;
    }

//    private void initializePlayers() {
//        this.blackPlayer = new Player();
//        this.whitePlayer = new Player();
//        System.out.println("Please enter your user name (e.g. Yue):");
//        Player player1 = new Player();
//        createPlayerAccount(player1);
//        System.out.println("Welcome to ChessHits, " + player1.getName());
//        Color p1Color = player1.chooseColor();
//        System.out.println("The other user, please enter your name (e.g. Guy)");
//        Player player2 = new Player();
//        createPlayerAccount(player2);
//        System.out.println("Also welcome " + player2.getName());
//        try {
//            this.playerFileWriter.close();
//        } catch (IOException ex) {
//            Logger.getLogger(ChessHitsGame.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        if (p1Color == Color.BLACK) {
//            this.blackPlayer = player1;
//            this.whitePlayer = player2;
//        } else {
//            this.whitePlayer = player1;
//            this.blackPlayer = player2;
//        }
//        this.blackPlayer.setIsTurn(false);
//        this.whitePlayer.setIsTurn(true);
//        this.blackPlayer.setColor(Color.BLACK);
//        this.whitePlayer.setColor(Color.WHITE);
//    }
//    private void createPlayerAccount(Player player) {
//        String name = userInputReader.nextLine();
//        String[] splitName = name.split(" ");
//        player.setName(splitName[0]);
//        try {
//            this.playerFileWriter.append(splitName[0]);
//            this.playerFileWriter.append(',');
//        } catch (IOException ex) {
//            Logger.getLogger(ChessHitsGame.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
//    /**
//     * initialize Chessboard data from a txt file.
//     *
//     * @param filename name of resource file
//     */
//    private void initializeGameFromFile(String filename) {
//        try {
//            Scanner input = new Scanner(new File(filename));
//            input.useDelimiter("\\s*,\\s*");
//            generatePawns(Color.BLACK);
//            generatePawns(Color.WHITE);
//            generateRankPieces(Color.BLACK);
//            generateRankPieces(Color.WHITE);
//            board.draw();
//        } catch (FileNotFoundException e) {
//            System.err.println("unable to find the file " + filename);
//        }
//    }
    /**
     * generate pawn pieces for selected color.
     *
     * @param color the color for Pawn to be
     */
    private void generatePawns(Color color) {
        int row;
        if (color == Color.BLACK) {
            row = 6;
        } else {
            row = 1;
        }
        for (int col = 0; col < 8; col++) {
            Position temPosition = this.board.getPositions()[row][col];
            Square tempSquare = this.board.getSquare(temPosition);
            Piece pawn = new Pawn(this.board, 3, temPosition, color);
            tempSquare.addPiece(pawn);
        }
    }

    public Square getSquare(int row, int col) {
        return this.board.getSquare(board.getPositions()[row][col]);

    }

    public Board getBoard() {
        return this.board;
    }

    private Piece getPiece(Position position) {
        return board.getSquare(position).getOccupiedPiece();

    }

    /**
     * moves the players Piece to the new place and attacks a piece if it is in
     * its way
     *
     * @param curPos where the players piece is currently
     * @param tarPos where you want to move the piece
     */
    private boolean movePlayerPiece(Position curPos, Position tarPos) {
        Piece piece = getPiece(curPos);
        Square tarSquare = this.board.getSquare(tarPos);
        boolean isMoved = false;
        // checking if it is a valid move and on the board
        boolean isPawn = piece instanceof Pawn;
        if (positionOnBoard(tarPos)) {
            isMoved = determineMoveOrAttack(piece, tarPos);
        }
        return isMoved;
    }

    private boolean determineMoveOrAttack(Piece piece, Position toPos) {
        boolean isMoved = false;
        Square destSquare = this.board.getSquare(toPos);
        Piece dPiece = this.board.getSquare(toPos).getOccupiedPiece();
        boolean shouldAttack = false;
        //moves
        Collection<Square> pieceSquares = piece.allPossibleMoves(toPos);
        if (pieceSquares.contains(destSquare)) {
            System.out.println("on the move");
            isMoved = movePieceTo(piece, destSquare);
            if (!isMoved) {
                shouldAttack = true;
            }
            if (destSquare.getOccupiedPiece() != null && shouldAttack && piece.getColor() != destSquare.getOccupiedPiece().getColor()) {//when game think
                isMoved = attackPiece(piece, dPiece);
            }
            //all the rest should be considered as illegal input
            if (piece.isAlive() && piece instanceof Pawn) {
                Pawn temPawn = (Pawn) piece;
                Color pieceColor = piece.getColor();
//                switch (pieceColor) {
//                    case WHITE:
//                        if (piece.getCurrentPosition().getRow() == 7) {
//                            askForPromotion(temPawn);
//                        }
//                        break;
//                    case BLACK:
//                        if (piece.getCurrentPosition().getRow() == 0) {
//                            askForPromotion(temPawn);
//                        }
//                        break;
//                }
            }
        }
        return isMoved;

    }

    private boolean attackPiece(Piece fromPiece, Piece toPiece) {
        boolean isSuccessful = false;
        if (fromPiece instanceof Pawn && toPiece != null && toPiece.isAlive()) {
            Pawn pawn = (Pawn) fromPiece;
            if (isPawnAbleFork(pawn, toPiece.getCurrentPosition())) {
                pawn.attack(toPiece);
                isSuccessful = true;
                if (toPiece instanceof Pawn && toPiece.isAlive()) {
                    Pawn anotherPawn = (Pawn) toPiece;
                    if (isPawnAbleFork(anotherPawn, fromPiece.getCurrentPosition())) {
                        anotherPawn.attack(fromPiece);
                    }
                }
            }
        } else if (toPiece != null) {
            System.out.println(fromPiece.getStringRepresentation() + " attacks " + toPiece.getStringRepresentation());
            fromPiece.attack(toPiece);
            if (toPiece.isAlive() && toPiece instanceof Pawn && isPawnAbleFork(toPiece, fromPiece.getCurrentPosition())) {
                toPiece.attack(fromPiece);
            } else if (toPiece.isAlive() && toPiece.move(fromPiece.getCurrentPosition())) {
                System.out.println(toPiece.getStringRepresentation() + " attacks " + fromPiece.getStringRepresentation());
                toPiece.attack(fromPiece);
            }
            isSuccessful = true;

        }// should add checking whether the attack piece reaches 0 hp
        if (!fromPiece.isAlive()) {
            Position temPos = fromPiece.getCurrentPosition();
            this.board.getSquare(temPos).removePiece(fromPiece);
        }
        if (toPiece != null && !toPiece.isAlive()) {
            Position temPos = toPiece.getCurrentPosition();
            Square toSquare = this.board.getSquare(temPos);
            Position temFromPos = fromPiece.getCurrentPosition();
            Square fromSquare = this.board.getSquare(temFromPos);
            fromSquare.removePiece(fromPiece);
            toSquare.removePiece(toPiece);
            toSquare.addPiece(fromPiece);
        }
        if (toPiece instanceof King && !toPiece.isAlive()) {
            switch (toPiece.getColor()) {
                case BLACK:
                    System.out.print(this.blackPlayer.getName());
                case WHITE:
                    System.out.print(this.whitePlayer.getName());
            }
            System.out.println(" lost the game");
            this.isGameRunning = false;
            System.out.println("press any key to continue");
            userInputReader.nextLine();
        } else {
            if (isSuccessful && fromPiece.isAlive()) {
                System.out.println(fromPiece.getStringRepresentation() + " NOW at row: " + fromPiece.getCurrentPosition().getRow()
                        + " column: " + fromPiece.getCurrentPosition().getColumn() + "; " + fromPiece.getStringRepresentation()
                        + " has " + fromPiece.getHP() + " left");
            }
            if (isSuccessful && toPiece != null && toPiece.isAlive()) {
                System.out.println(toPiece.getStringRepresentation() + " NOW at row: " + toPiece.getCurrentPosition().getRow()
                        + " column: " + toPiece.getCurrentPosition().getColumn() + "; " + toPiece.getStringRepresentation()
                        + " has " + toPiece.getHP() + " left.");
            }
        }
        return isSuccessful;
    }


    /*
     * generate higher rank pieces for selected color
     *
     * @param color the color for a set of pieces to be
     */
    private void generateRankPieces(Color color) {
        int row;
        if (color == Color.WHITE) {
            row = 0;
        } else {
            row = 7;
        }
        //rook init
        Position temPosition = board.getPositions()[row][0];
        Square temSquare = board.getSquare(temPosition);
        Rook rook1 = new Rook(this.board, 2, temPosition, color);
        temSquare.addPiece(rook1);
        temPosition = board.getPositions()[row][7];
        temSquare = board.getSquare(temPosition);
        Rook rook2 = new Rook(this.board, 2, temPosition, color);
        temSquare.addPiece(rook2);
        //knight initnir
        temPosition = board.getPositions()[row][1];
        Knight knight1 = new Knight(this.board, 2, temPosition, color);
        temSquare = board.getSquare(temPosition);
        temSquare.addPiece(knight1);
        temPosition = board.getPositions()[row][6];
        Knight knight2 = new Knight(this.board, 2, temPosition, color);
        temSquare = board.getSquare(temPosition);
        temSquare.addPiece(knight2);
        //bishop init
        temPosition = board.getPositions()[row][2];
        Bishop bishop1 = new Bishop(this.board, 2, temPosition, color);
        temSquare = board.getSquare(temPosition);
        temSquare.addPiece(bishop1);
        temPosition = board.getPositions()[row][5];
        Bishop bishop2 = new Bishop(this.board, 2, temPosition, color);
        temSquare = board.getSquare(temPosition);
        temSquare.addPiece(bishop2);
        //queen init
        temPosition = board.getPositions()[row][3];
        Queen queen = new Queen(this.board, 1, temPosition, color);
        temSquare = board.getSquare(temPosition);
        temSquare.addPiece(queen);
        //king init
        temPosition = board.getPositions()[row][4];
        King king = new King(this.board, 1, temPosition, color, true);
        temSquare = board.getSquare(temPosition);
        temSquare.addPiece(king);
    }

    private boolean positionOnBoard(Position position) {
        boolean isOnBoard = false;
        if (position.getRow() >= 0 && position.getColumn() >= 0
                && position.getColumn() <= 7 && position.getRow() <= 7) {

            isOnBoard = true;
        }
        return isOnBoard;
    }

    /**
     * for console version only the user input of the position and new position
     *
     */
    private Position userSelecteMovingPosition(Player player, Boolean isPieceSelected) {

        boolean moveDidntWorked = true;
        Position newPosition = null;
        while (moveDidntWorked) {
            try {
                moveDidntWorked = false;
                newPosition = userSelectPosition(player, isPieceSelected);
            } catch (NullPointerException e) {
            } catch (Exception e) {
                System.out.println(player.getName() + ", sorry that is not a valid position");
                moveDidntWorked = true;
            }
        }
        return newPosition;
    }

    private Position userSelectPosition(Player player, Boolean isPieceSelected) {
        int row, col;
        Position newPosition = null;
        boolean willLoop = true;

        while (willLoop) {
            Color color = player.getSelectedColor();
            String name = player.getName();
            if (!isPieceSelected) {
                System.out.println(name + " " + color + " please select a piece in row col e.g. 8 h and\"m\" for menu");
            } else {
                System.out.println(name + " " + color + " please select a New position for piece e.g. 8 h ");
            }
            String tempUserInput;
            String[] inputContent;
            tempUserInput = userInputReader.nextLine();
            inputContent = tempUserInput.split(" ");

            if (inputContent[0].equals("m")) {
                willLoop = false;
                Square nudollSquare = this.board.getSquare(newPosition);// defined as invalid information, as the exception shall be thrown
            } else {
                row = convertToRow(inputContent[0]);
                col = convertToCol(inputContent[1]);
                willLoop = false;
                if (row < 0 || row > 7 || col < 0 || col > 7) {
                    System.out.println("sorry position is not on the board");
                    willLoop = true;
                }
                newPosition = board.getPositions()[row][col];
                if (!isPieceSelected) {
                    Color piColor = board.getSquare(newPosition).getOccupiedPiece().getColor();
                    if (board.getSquare(newPosition).isSquareAvailable()) {
                        System.out.println("sorry there is no piece there try again");
                        willLoop = true;
                    }
                    if (piColor != color) {
                        System.out.println("sorry that is not your piece");
                        willLoop = true;
                    }
                }
            }
        }
        return newPosition;
    }

    /**
     * convert row text representation to the value that java can manipulate
     *
     * @param row The string representation of each row
     * @return an integer value
     */
    private int convertToRow(String row) {
        int exactRow = 10;
        switch (row) {
            case "8":
                exactRow = 0;
                break;
            case "7":
                exactRow = 1;
                break;
            case "6":
                exactRow = 2;
                break;
            case "5":
                exactRow = 3;
                break;
            case "4":
                exactRow = 4;
                break;
            case "3":
                exactRow = 5;
                break;
            case "2":
                exactRow = 6;
                break;
            case "1":
                exactRow = 7;
                break;
        }
        return exactRow;
    }

    private int convertToCol(String row) {
        int exactRow = 10;
        switch (row) {
            case "a":
                exactRow = 0;
                break;
            case "b":
                exactRow = 1;
                break;
            case "c":
                exactRow = 2;
                break;
            case "d":
                exactRow = 3;
                break;
            case "e":
                exactRow = 4;
                break;
            case "f":
                exactRow = 5;
                break;
            case "g":
                exactRow = 6;
                break;
            case "h":
                exactRow = 7;
                break;
        }
        return exactRow;
    }

    private Piece askForPromotion(Pawn pawn) {
        Piece piece = null;
        Position currentPos = pawn.getCurrentPosition();
        Square currentSquare = this.board.getSquare(currentPos);
        Color promoteColor = pawn.getColor();
        System.out.println("Select the piece for promotion of the pawn\n(please input the short form for the piece such as 'q', 'b', 'n' and 'r')");
        String promotedPiece = null;
        while (promotedPiece == null) {
            promotedPiece = userInputReader.nextLine();
        }
        promotedPiece = promotedPiece.split(" ")[0];
        switch (promotedPiece) {
            case "q":
                piece = new Queen(this.board, pawn.getHP(), currentPos, promoteColor);
                break;
            case "b":
                piece = new Bishop(this.board, pawn.getHP(), currentPos, promoteColor);
                break;
            case "n":
                piece = new Knight(this.board, pawn.getHP(), currentPos, promoteColor);
                break;
            case "r":
                piece = new Rook(this.board, pawn.getHP(), currentPos, promoteColor);
        }
        currentSquare.removePiece(pawn);
        currentSquare.addPiece(piece);
        return piece;
    }

    private boolean movePieceTo(Piece piece, Square square) {
        boolean isSuccessful = false;
        if (square.isSquareAvailable() && piece.move(square.getPosition())) {
            board.getSquare(piece.getCurrentPosition()).removePiece(piece);
            square.addPiece(piece);
            isSuccessful = true;
            System.out.println(piece.getStringRepresentation() + " moved to row: "
                    + square.getPosition().getRow() + " column: " + square.getPosition().getColumn());
        } else {
            System.out.println(piece.getStringRepresentation() + " cannot move to the target position");
        }
        return isSuccessful;
    }

    private void printUserInstruction() {
        System.out.println("#!1 Pawns are toughest (in the most cases, they are the defendants)\n"
                + "#!2 second level pieces have less hp, but wider attack range.\n"
                + "#!3 highest level pieces only have 1hp (which make em vulnerable) yet, a \n\tqueen has the widest range, under proper protection, you can think it's the major piece in the game.\n"
                + "#!4 a king is limited by its attack range, however, it can take down any \n\tpiece just with one hit.\n"
                + "#!5 another feature of the game, my game applies bi-directional attacks \n\tduring one hit scenario. When a piece is hit by another,\n\t it shall try to fight back if the piece is at the\n\t attack range.\n"
                + "#!5 more,when the attacking piece takes another piece down. it moves to the\n\t place. (This can make a tradeoff piece and let player\n\t weight the value between different pieces)");
    }

    public boolean getSelectedSquare(Square square) {
        boolean turn = false;
        try {
            if (!firstSelected) {
                Piece piece = square.getOccupiedPiece();
                if (whiteTurn) {

                    if (piece.getColor() == WHITE) {
                        squareMove = square;
                        firstSelected = true;
                        turn = true;

                    }
                } else if (piece.getColor() == BLACK) {
                    squareMove = square;
                    firstSelected = true;
                    turn = true;

                }

            } else {
                firstSelected = false;
                if (movePlayerPiece(squareMove.getPosition(), square.getPosition())) {
                    whiteTurn = !whiteTurn;
                    whitePlayer.setIsTurn(!whitePlayer.getIsTurn());
                    blackPlayer.setIsTurn(!blackPlayer.getIsTurn());


                }
            }

        } catch (Exception e) {
        }
        return turn;
    }

    public Player getPlayer(Color color) {
        Player player = null;
        switch (color) {
            case WHITE:
                player = this.whitePlayer;
                break;
            case BLACK:
                player = this.blackPlayer;
                break;
            default:
                break;
        }

        return player;
    }

    public void setBlackPlayer(Player player) {
        blackPlayer = player;
    }

    public void setWhitePlayer(Player player) {
        whitePlayer = player;
    }

    public void setWhiteTurn(boolean whiteTurn) {
        this.whiteTurn = whiteTurn;
    }
}
