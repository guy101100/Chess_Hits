/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nz.ac.aut.pdc.ChessHits.GUI;

import java.awt.Component;
import nz.ac.aut.pdc.ChessHits.model.ChessHitsGame;
import nz.ac.aut.pdc.ChessHits.model.Color;
import nz.ac.aut.pdc.ChessHits.model.Position;
import nz.ac.aut.pdc.ChessHits.model.Square;
import nz.ac.aut.pdc.ChessHits.model.pieces.Piece;

/**
 *
 * @author gl
 */
public class SqurarePanel extends javax.swing.JPanel {
private Square square;
private Piece piece;
private ChessHitsGame game;
    private boolean oneClick;
    private int row;
    private int col;
    /**
     * Creates new form SqurarePanel
     */
    public SqurarePanel(ChessHitsGame game, int row , int col) {
        this.game = game;
        this.row= row;
        this.col =col;
      square =  game.getSquare(row, col);
     piece = square.getOccupiedPiece();
        initComponents();
      update();
         if(row == 0 && col == 0){
                this.setBackground(java.awt.Color.GRAY);
            }
            else if(row == 0 && col%2 == 0){
               this.setBackground(java.awt.Color.GRAY);
            }
            else if( row % 2 == 0 && col % 2 == 0){
                this.setBackground(java.awt.Color.GRAY);
            }
            else if(row % 2 ==0 && col == 0){
                this.setBackground(java.awt.Color.GRAY);
            }
             else if(row == 0 && col%2 == 0){
               this.setBackground(java.awt.Color.GRAY);
            }
            else if( row % 2 != 0 && col % 2 != 0){
                this.setBackground(java.awt.Color.GRAY);
            }
       
            else {
                this.setBackground(java.awt.Color.white);
            }
    }
    private void update(){
          if(!square.isSquareAvailable()){
            lblRep.setText(piece.getStringRepresentation());
            if(piece.getColor() == Color.BLACK){
                lblRep.setForeground (java.awt.Color.BLACK);
            }
            else{
                lblRep.setForeground (java.awt.Color.LIGHT_GRAY);
            }
           
        }
          else{
              lblRep.setText("");
          }
    }
    private void fullUpdate(){
             if(!square.isSquareAvailable()){
            lblRep.setText(piece.getStringRepresentation());
            if(piece.getColor() == Color.BLACK){
                lblRep.setForeground (java.awt.Color.BLACK);
            }
            else{
                lblRep.setForeground (java.awt.Color.LIGHT_GRAY);
            }
           
        }
          else{
              lblRep.setText("");
          }
                if(row == 0 && col == 0){
                this.setBackground(java.awt.Color.GRAY);
            }
            else if(row == 0 && col%2 == 0){
               this.setBackground(java.awt.Color.GRAY);
            }
            else if( row % 2 == 0 && col % 2 == 0){
                this.setBackground(java.awt.Color.GRAY);
            }
            else if(row % 2 ==0 && col == 0){
                this.setBackground(java.awt.Color.GRAY);
            }
             else if(row == 0 && col%2 == 0){
               this.setBackground(java.awt.Color.GRAY);
            }
            else if( row % 2 != 0 && col % 2 != 0){
                this.setBackground(java.awt.Color.GRAY);
            }
       
            else {
                this.setBackground(java.awt.Color.white);
            }
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblRep = new javax.swing.JLabel();

        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                formMouseReleased(evt);
            }
        });

        lblRep.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblRep.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                lblRepMouseReleased(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblRep, javax.swing.GroupLayout.DEFAULT_SIZE, 56, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(lblRep, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void formMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseReleased
        // TODO add your handling code here:

    }//GEN-LAST:event_formMouseReleased

    private void lblRepMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblRepMouseReleased
        // TODO add your handling code here:
        boolean turn = game.getSelectedSquare(square);
        this.setBackground(java.awt.Color.ORANGE);
          piece = square.getOccupiedPiece();
          update();
         for(Component component:getParent().getComponents()){
             SqurarePanel sp = (SqurarePanel) component;
             if(turn){
             sp.update();
             }
             else{
                sp.fullUpdate();
             }
            
         }
         
    }//GEN-LAST:event_lblRepMouseReleased

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel lblRep;
    // End of variables declaration//GEN-END:variables
}
