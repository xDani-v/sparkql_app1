/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package main;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 *
 * @author NITRO
 */
public class app {

    public static void main(String[] args) throws IOException {
        final Model model = ModelFactory.createDefaultModel();
        Files.walkFileTree(Paths.get("C:/Users/NITRO/Documents/NetBeansProjects/sparkql_app1/src/main/programa.rdf"),
                new SimpleFileVisitor<Path>() {
                    @Override
                    public FileVisitResult visitFile(final Path file, final BasicFileAttributes attrs)
                            throws IOException {
                        model.read(file.toUri().toString());
                        return super.visitFile(file, attrs);
                    }
                });

        // Realizar la consulta SPARQL
        executeSparqlQuery(model);
    }
    
     private static void executeSparqlQuery(Model model) {
        // Realizar la consulta SPARQL
        String sparqlQuery = "prefix po: <http://purl.org/ontology/po/>\n"
                + "select ?pelicula ?genero ?cat ?pais ?horario\n" + "where {\n" + "  ?a1 po:Brand ?pelicula.\n"
                + "  ?a1 po:Genre ?genero.\n" + "  ?a1 po:Category ?cat.\n" + "  ?a1 po:Broadcast ?a2.\n"
                + "  ?a2 po:Place ?pais.\n" + "  ?a2 po:time ?horario\n" + "}";

        QueryExecution queryExecution = QueryExecutionFactory.create(sparqlQuery, model);

        try {
            ResultSet resultSet = queryExecution.execSelect();

            // Imprimir resultados
            while (resultSet.hasNext()) {
                QuerySolution querySolution = resultSet.next();
                System.out.println("Pelicula: " + querySolution.get("pelicula"));
                System.out.println("Genero: " + querySolution.get("genero"));
                System.out.println("Categoria: " + querySolution.get("cat"));
                System.out.println("Pais: " + querySolution.get("pais"));
                System.out.println("Horario: " + querySolution.get("horario"));
                System.out.println("--------------");
            }
        } finally {
            queryExecution.close();
        }
    }
     
     private static List<Map<String, String>> executeSparqlQueryP(Model model, String pelicula, String genero, String categoria, String pais, String horario) {
    List<Map<String, String>> resultList = new ArrayList<>();

    // Construir la consulta SPARQL
    StringBuilder sparqlQuery = new StringBuilder();
    sparqlQuery.append("prefix po: <http://purl.org/ontology/po/>\n");
    sparqlQuery.append("select ?pelicula ?genero ?cat ?pais ?horario\n");
    sparqlQuery.append("where {\n");
    sparqlQuery.append("  ?a1 po:Brand ?pelicula.\n");
    sparqlQuery.append("  ?a1 po:Genre ?genero.\n");
    sparqlQuery.append("  ?a1 po:Category ?cat.\n");
    sparqlQuery.append("  ?a1 po:Broadcast ?a2.\n");
    sparqlQuery.append("  ?a2 po:Place ?pais.\n");
    sparqlQuery.append("  ?a2 po:time ?horario\n");

    // Agregar condiciones de búsqueda según los parámetros proporcionados
    if (pelicula != null) {
        sparqlQuery.append("  FILTER regex(?pelicula, \"" + pelicula + "\", \"i\")\n");
    }
    if (genero != null) {
        sparqlQuery.append("  FILTER regex(?genero, \"" + genero + "\", \"i\")\n");
    }
    if (categoria != null) {
        sparqlQuery.append("  FILTER regex(?cat, \"" + categoria + "\", \"i\")\n");
    }
    if (pais != null) {
        sparqlQuery.append("  FILTER regex(?pais, \"" + pais + "\", \"i\")\n");
    }
    if (horario != null) {
        sparqlQuery.append("  FILTER regex(?horario, \"" + horario + "\", \"i\")\n");
    }

    sparqlQuery.append("}");

    QueryExecution queryExecution = QueryExecutionFactory.create(sparqlQuery.toString(), model);

    try {
        ResultSet resultSet = queryExecution.execSelect();

        // Almacenar resultados en la lista
        while (resultSet.hasNext()) {
            QuerySolution querySolution = resultSet.next();
            Map<String, String> resultRow = new HashMap<>();
            resultRow.put("Pelicula", querySolution.get("pelicula").toString());
            resultRow.put("Genero", querySolution.get("genero").toString());
            resultRow.put("Categoria", querySolution.get("cat").toString());
            resultRow.put("Pais", querySolution.get("pais").toString());
            resultRow.put("Horario", querySolution.get("horario").toString());
            resultList.add(resultRow);
        }
    } finally {
        queryExecution.close();
    }

    return resultList;
}

}
