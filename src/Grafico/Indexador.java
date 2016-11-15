/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Grafico;

import Clases.ArbolAVL;
import java.awt.Font;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.*;
import java.io.*;
import java.util.*;
/**
 * Esta es la clase Indexador en la cual se crea una ventana en la cual se puede seleccionar el archivo en el
 * cual se buscara un dato segun el campo seleccionado. 
 * @author IngridNiño, Zuleyka Guzman
 */
public class Indexador extends javax.swing.JFrame{
    /**
     * Esta es la variable jrb de tipo  JRadioButton
     */
    private         JRadioButton             jrb;
    /**
     * Este es el arreglo arre de tipo String para guardar los datos que se leen del archivo,
     * separados mediante el metodo split de String 
     * y asi poder guardarlos en el arbol
     */
    private         String                   arre[];
    /**
     * En esta linea se declara un ArrayList de botones de tipo JRadioButton el cual nos servira para 
     * darle funcionalidad a los botones que nos ayudaran a buscar la informacion en el arbol
     */
    private         ArrayList<JRadioButton>  botones;
    /**
     * Esta es la variable pathArchivo de tipo String para guardar la direccion del archivo
     */
    private         String                   pathArchivo;
    /**
     * Aqui se declara un arbol de tipo ArbolAVL  en el que se iran insertando los datos por categoria para 
     * poder buscarlos posteriormente
     */
    public  static  ArbolAVL                 arbol;
     /**
     * Este es el constructor de la clase en el que se le pone un titulo a la ventana y 
     * se exporta una imagen que es utilizada como el fondo de la ventana Y se crea un arreglo de botones 
     * para poder darle funcionalidad a los botones que se crean segun la primera linea del archivo.
     */
    public Indexador() {
        initComponents();
        botones  =  new ArrayList<>();
        
        this.setTitle("Indexador");
        this.setResizable(false);
        this.setIconImage(new ImageIcon(getClass().getResource("../Imagenes/consultar.png")).getImage());
         
        jTextField1.setEditable(false);
       
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        textArea1 = new java.awt.TextArea();
        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jTextField1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setOpaque(false);
        jPanel1.setLayout(new java.awt.GridLayout(0, 1));
        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(43, 120, 260, 190));

        jTextField1.setEditable(false);
        jTextField1.setBackground(new java.awt.Color(255, 255, 255));
        jTextField1.setFont(new java.awt.Font("Arial Black", 0, 11)); // NOI18N
        jTextField1.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });
        getContentPane().add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(152, 30, 190, 30));

        jButton1.setFont(new java.awt.Font("Arial Black", 0, 11)); // NOI18N
        jButton1.setText("Abrir");
        jButton1.setFocusable(false);
        jButton1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 30, 68, 30));

        jButton2.setFont(new java.awt.Font("Arial Black", 0, 11)); // NOI18N
        jButton2.setText("Aceptar");
        jButton2.setFocusable(false);
        jButton2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton2.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 290, -1, -1));

        jLabel2.setFont(new java.awt.Font("Arial Black", 0, 11)); // NOI18N
        jLabel2.setText("Indexar por:");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(43, 87, -1, -1));

        jLabel1.setFont(new java.awt.Font("Arial Black", 0, 11)); // NOI18N
        jLabel1.setText("Nombre archivo:");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 30, 104, 30));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/FondoAzul.png"))); // NOI18N
        jLabel3.setText("jLabel3");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 480, 340));

        pack();
    }// </editor-fold>//GEN-END:initComponents
        /**
     * Este es el metodo del boton abrir, con el que se puede seleccionar un archivo mediante la clase
     * JFileChooser, el boton se pone visible poniendole el valor de true para que este pueda verse,
     * tambien se le puso un filtro para que solo puedan seleccionarse archivos en formato .csv y .txt
     * Y en el recuadro que aparece en blanco aparece el nombre del archivo que se seleccionó.
     * Despues se lee la primera linea del archivo que es la que tiene el nombre de los campos del archivo,
     * con esto se crea un boton por cada campo que podra ser seleccionado para buscar el dato que queramos.
     * @param evt 
     */
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        JFileChooser jf = new JFileChooser();
        int opcion      = 0;
        
        jf.setVisible(true);
        jf.setFileSelectionMode(JFileChooser.FILES_ONLY);
        jf.setMultiSelectionEnabled(false);
        FileNameExtensionFilter filtro=new FileNameExtensionFilter("Separado por comas", "csv","Archivo de texto","txt");
	jf.setFileFilter(filtro);
        
                opcion =  jf.showOpenDialog(jButton1);
            if (opcion == jf.APPROVE_OPTION) { 
                        pathArchivo   = jf.getSelectedFile().getPath();
                        String nombre = jf.getSelectedFile().getName();
                        jTextField1.setText(nombre);
                        String nomArchivo =pathArchivo;
                try{
                        BufferedReader br = new BufferedReader(new FileReader(nomArchivo));
                        String l;
                        l = br.readLine();
                        arre = l.split(",");
                    if(l!=null){
                        for(int i=0;i<arre.length;i++){
                               jrb=new JRadioButton(arre[i]);
                               buttonGroup1.add(jrb);
                               jrb.setFont(new Font("Arial Black", Font.PLAIN, 11));
                               jPanel1.add(jrb);
                               botones.add(jrb);
                               jPanel1.updateUI();
                        } 
                    }
                    br.close();
                }catch(IOException e){
                       JOptionPane.showMessageDialog(null, "Archivo no encontrado", "Error",JOptionPane.ERROR_MESSAGE);
                }
            }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        
    }//GEN-LAST:event_jTextField1ActionPerformed
    /**
 * En este boton se recorren cada uno de los botones que se crean segun el encabezado del archivo
 * y segun el que sea seleccionado se recorre el archivo y secrea un arbol de ese tipo, 
 * una vez que se crea el arbol se manda a llamar a la ventana secundaria para poder buscar un elemento.
 * Asi tambien se arroja el mensaje de "Primero debes seleccionar un archivo" en caso de no haber seleccionado un archivo 
 * o el de "Ninguna opción ha sido seleccionada" en caso de no haber seleccionado ningun boton 
 * @param evt 
 */
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        String nombre="";
        String recorrer[];
        String nomArch =pathArchivo;
        boolean band = false;
        Iterator<JRadioButton> iter=botones.iterator();
        while(iter.hasNext()){
            JRadioButton jr=iter.next();
            if(jr.isSelected()){
                 arbol = new ArbolAVL();
              for(int i=0;i<arre.length;i++){
                 if(jr.getText().equals(arre[i])){
                     try{
                        BufferedReader b = new BufferedReader(new FileReader(nomArch));
                        String l;
                        l = b.readLine();
                        l = b.readLine();
                       while(l!=null){
                              recorrer = l.split(",");
                              arbol.insertar(recorrer[i]);
                              l = b.readLine();    
                        }
                         b.close();
                     }catch(IOException e){
                       JOptionPane.showMessageDialog(null, "Archivo no encontrado", "Error",JOptionPane.ERROR_MESSAGE);
                     }
                 }
              }
                band = true;
                nombre=jr.getText();
            }
        }
        
        if(band==true){
                   
                   VentanaSecundaria cb= new VentanaSecundaria();
                   cb.setVisible(true);
                   cb.setTitle("Buscar por " + nombre);
                   cb.getjLabel1().setText(nombre);
                   
            }else{ if(botones.size()==0){
            JOptionPane.showMessageDialog(null, "Primero debes seleccionar un archivo");
            }else{
            JOptionPane.showMessageDialog(null, "Ninguna opción ha sido seleccionada");
            }
            }
    }//GEN-LAST:event_jButton2ActionPerformed
    /**
     * Este es el metodo get del arbol
     * @return el arbol
     */
    public static ArbolAVL getArbol() {
        return arbol;
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Indexador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Indexador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Indexador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Indexador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Indexador().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField jTextField1;
    private java.awt.TextArea textArea1;
    // End of variables declaration//GEN-END:variables


}
