package com.esprit.websemanticstroc.Services;
import org.apache.jena.query.*;
import org.apache.jena.sparql.engine.http.QueryExceptionHTTP;
import org.springframework.stereotype.Service;

@Service
public class SparqlService {
    private static final String ONTOLOGY_NS = "http://www.semanticweb.org/asus/ontologies/2023/9/untitled-ontology-7#";

    String sparqlEndpoint = "http://localhost:3030/Troc/query";

    public ResultSet getEventsWithDetails() {
        String query = "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
                "PREFIX owl: <http://www.w3.org/2002/07/owl#>\n" +
                "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n" +
                "PREFIX troc: <http://www.owl-ontologies.com/Troc.owl#>\n" +
                "SELECT ?event ?eventName ?eventDate ?eventDescription ?eventImage ?eventLocation ?eventStatus ?ticketPrice\n" +
                "WHERE {\n" +
                "  ?event rdf:type troc:OnlineEvent .\n" +
                "  OPTIONAL { ?event troc:EventName ?eventName }\n" +
                "  OPTIONAL { ?event troc:EventDate ?eventDate }\n" +
                "  OPTIONAL { ?event troc:EventDescription ?eventDescription }\n" +
                "  OPTIONAL { ?event troc:EventImage ?eventImage }\n" +
                "  OPTIONAL { ?event troc:EventLocation ?eventLocation }\n" +
                "  OPTIONAL { ?event troc:EventStatus ?eventStatus }\n" +
                "  OPTIONAL { ?event troc:Ticket_price ?ticketPrice }\n" +
                "}";

        return executeQuery(query);
    }

    public ResultSet getClientsWithDetails() {
        String query = "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
                "PREFIX owl: <http://www.w3.org/2002/07/owl#>\n" +
                "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n" +
                "PREFIX troc: <http://www.owl-ontologies.com/Troc.owl#>\n" +
                "SELECT ?user ?email ?password ?name\n" +
                "WHERE {\n" +
                "  ?user rdf:type/rdf:type* troc:Client .\n" +
                "  OPTIONAL { ?user troc:UserEmail ?email }\n" +
                "  OPTIONAL { ?user troc:pwd ?password }\n" +
                "  OPTIONAL { ?user troc:nom ?name }\n" +
                "}";

        return executeQuery(query);
    }

    public ResultSet getAdminsWithDetails() {
        String query = "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
                "PREFIX owl: <http://www.w3.org/2002/07/owl#>\n" +
                "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n" +
                "PREFIX troc: <http://www.owl-ontologies.com/Troc.owl#>\n" +
                "SELECT ?user ?email ?password ?name\n" +
                "WHERE {\n" +
                "  ?user rdf:type/rdf:type* troc:Admin .\n" +
                "  OPTIONAL { ?user troc:UserEmail ?email }\n" +
                "  OPTIONAL { ?user troc:pwd ?password }\n" +
                "  OPTIONAL { ?user troc:nom ?name }\n" +
                "}";

        return executeQuery(query);
    }

    public ResultSet executeQuery(String query) {
        try (QueryExecution queryExecution = QueryExecutionFactory.sparqlService(sparqlEndpoint, query)) {
            return queryExecution.execSelect();
        } catch (QueryParseException | QueryExceptionHTTP e) {
            // Handle exceptions appropriately
            e.printStackTrace();
            return null;
        }
    }
}
