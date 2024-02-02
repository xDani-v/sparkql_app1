/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package main;

import java.util.ArrayList;
import java.util.List;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import java.io.Console;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
/**
 *
 * @author NITRO
 */
public class japp extends javax.swing.JFrame {

      final Model model = ModelFactory.createDefaultModel();
    /**
     * Creates new form japp
     */
    public japp() throws IOException {
        initComponents();
        
        Files.walkFileTree(Paths.get("C:/Users/NITRO/Documents/NetBeansProjects/sparkql_app1/src/main/programa.rdf"),
                new SimpleFileVisitor<Path>() {
                    @Override
                    public FileVisitResult visitFile(final Path file, final BasicFileAttributes attrs)
                            throws IOException {
                        model.read(file.toUri().toString());
                        return super.visitFile(file, attrs);
                    }
                });
 
         List<String> peliculasList = cargarDatosDesdeRDF("pelicula", "po:Brand",model);
        for (String pelicula : peliculasList) {
            jComboBox1.addItem(pelicula);
        }

        // Cargar géneros
        List<String> generosList = cargarDatosDesdeRDF("genero", "po:Genre",model);
        for (String genero : generosList) {
            jComboBox2.addItem(genero);
        }
        
        List<String> categoriasList = cargarDatosDesdeRDF("cat", "po:Category",model);
        for (String var : categoriasList) {
            jComboBox3.addItem(var);
        }
        
        List<String> paisList = cargarDatosDesdeRDF("pais", "po:Place",model);
        for (String var : paisList) {
            jComboBox4.addItem(var);
        }
        
        List<String> horarioList = cargarDatosDesdeRDF("horario", "po:time",model);
        for (String var : horarioList) {
            jComboBox5.addItem(var);
        }
    }
    
    
     private static List<String> cargarDatosDesdeRDF(String variable, String propiedad, Model model) {
        List<String> datosList = new ArrayList<>();

        // Realizar la consulta SPARQL
        String sparqlQuery = "prefix po: <http://purl.org/ontology/po/>\n"
                + "select distinct ?" + variable + "\n" + "where {\n" + "  ?a1 " + propiedad + " ?" + variable + ".\n"
                + "}";

        QueryExecution queryExecution = QueryExecutionFactory.create(sparqlQuery, model);

        try {
            ResultSet resultSet = queryExecution.execSelect();

            // Agregar resultados a la lista
            while (resultSet.hasNext()) {
                QuerySolution querySolution = resultSet.next();
                datosList.add(querySolution.get(variable).toString());
            }
        } finally {
            queryExecution.close();
        }
        
        for (int i = 0; i < datosList.size(); i++) {
           String dato = datosList.get(i);
           // Eliminar la etiqueta @es si está presente
           dato = dato.replaceAll("@es$", "");
           datosList.set(i, dato);
       }

        return datosList;
    }
 
     
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox<>();
        jComboBox3 = new javax.swing.JComboBox<>();
        jComboBox4 = new javax.swing.JComboBox<>();
        jComboBox5 = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Nirmala UI", 0, 14)); // NOI18N
        jLabel1.setText("Busqueda de RDF");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(329, 20, -1, -1));

        jLabel2.setFont(new java.awt.Font("Nirmala UI", 0, 14)); // NOI18N
        jLabel2.setText("Categoria");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 90, -1, -1));

        jComboBox1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione" }));
        jPanel1.add(jComboBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 90, 250, -1));

        jLabel3.setFont(new java.awt.Font("Nirmala UI", 0, 14)); // NOI18N
        jLabel3.setText("Peliculas");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 90, -1, -1));

        jLabel4.setFont(new java.awt.Font("Nirmala UI", 0, 14)); // NOI18N
        jLabel4.setText("Genero");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 140, -1, -1));

        jLabel5.setFont(new java.awt.Font("Nirmala UI", 0, 14)); // NOI18N
        jLabel5.setText("Horario");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 190, -1, -1));

        jLabel6.setFont(new java.awt.Font("Nirmala UI", 0, 14)); // NOI18N
        jLabel6.setText("Pais");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 140, -1, -1));

        jComboBox2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione" }));
        jPanel1.add(jComboBox2, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 140, 250, -1));

        jComboBox3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione" }));
        jPanel1.add(jComboBox3, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 90, 250, -1));

        jComboBox4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jComboBox4.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione" }));
        jPanel1.add(jComboBox4, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 140, 250, -1));

        jComboBox5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jComboBox5.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione" }));
        jPanel1.add(jComboBox5, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 190, 250, -1));

        jButton1.setText("Realizar busqueda");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 190, 310, 30));

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 270, 710, 250));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 856, 641));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        mostrarDatosSeleccionados();
    }//GEN-LAST:event_jButton1ActionPerformed

    private  void mostrarDatosSeleccionados() {
        // Obtener datos seleccionados de JComboBox
        String seleccion1  = jComboBox1.getSelectedItem().toString();
        String seleccion2 = jComboBox2.getSelectedItem().toString();
        String seleccion3 = jComboBox3.getSelectedItem().toString();
        String seleccion4 = jComboBox4.getSelectedItem().toString();
        String seleccion5 = jComboBox5.getSelectedItem().toString();
        // Obtén más datos según sea necesario

        StringBuilder sparqlQuery = new StringBuilder();
        sparqlQuery.append("prefix po: <http://purl.org/ontology/po/>\n");
        sparqlQuery.append("select ?pelicula ?genero ?cat ?pais ?horario\n");
        sparqlQuery.append("where {\n");
        sparqlQuery.append("  ?a1 po:Brand ?pelicula.\n");
        sparqlQuery.append("  ?a1 po:Genre ?genero.\n");
        sparqlQuery.append("  ?a1 po:Category ?cat.\n");
        sparqlQuery.append("  ?a1 po:Broadcast ?a2.\n");
        sparqlQuery.append("  ?a2 po:Place ?pais.\n");
        sparqlQuery.append("  ?a2 po:time ?horario.\n");

        // Agregar condiciones de filtro según las selecciones
    if (!"Seleccione".equals(seleccion1)) {
        sparqlQuery.append("  FILTER (?pelicula = '").append(seleccion1).append("'@es)\n");
    }
    if (!"Seleccione".equals(seleccion2)) {
        sparqlQuery.append("  FILTER (?genero = '").append(seleccion2).append("'@es)\n");
    }
    if (!"Seleccione".equals(seleccion3)) {
        sparqlQuery.append("  FILTER (?cat = '").append(seleccion3).append("'@es)\n");
    }
    if (!"Seleccione".equals(seleccion4)) {
        sparqlQuery.append("  FILTER (?pais = '").append(seleccion4).append("'@es)\n");
    }
    if (!"Seleccione".equals(seleccion5)) {
        sparqlQuery.append("  FILTER (?horario = '").append(seleccion5).append("'@es)\n");
    }

        sparqlQuery.append("}");

        System.err.println(sparqlQuery);
         
        QueryExecution queryExecution = QueryExecutionFactory.create(sparqlQuery.toString(), model);

        try {
            ResultSet resultSet = queryExecution.execSelect();

            // Limpiar el JTextArea antes de mostrar nuevos resultados
            jTextArea1.setText("");

            // Mostrar resultados en el JTextArea
            while (resultSet.hasNext()) {
                QuerySolution querySolution = resultSet.next();
                jTextArea1.append("Pelicula: " + querySolution.get("pelicula") + "\n");
                jTextArea1.append("Genero: " + querySolution.get("genero") + "\n");
                jTextArea1.append("Categoria: " + querySolution.get("cat") + "\n");
                jTextArea1.append("Pais: " + querySolution.get("pais") + "\n");
                jTextArea1.append("Horario: " + querySolution.get("horario") + "\n");
                jTextArea1.append("--------------\n");
            }
        } finally {
            queryExecution.close();
        }
    
          
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
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(japp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(japp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(japp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(japp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new japp().setVisible(true);
                } catch (IOException ex) {
                    Logger.getLogger(japp.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JComboBox<String> jComboBox3;
    private javax.swing.JComboBox<String> jComboBox4;
    private javax.swing.JComboBox<String> jComboBox5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    // End of variables declaration//GEN-END:variables
}
