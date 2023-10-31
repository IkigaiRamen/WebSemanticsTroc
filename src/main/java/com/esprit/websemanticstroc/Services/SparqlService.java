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

    public ResultSet getVenuesWithDetails() {
        String query = "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
                "PREFIX troc: <http://www.owl-ontologies.com/Troc.owl#>\n" +
                "SELECT ?venue ?phone ?address ?capacity ?description ?image ?name ?website\n" +
                "WHERE {\n" +
                "  ?venue rdf:type troc:Local .\n" +
                "  OPTIONAL { ?venue troc:Phone ?phone }\n" +
                "  OPTIONAL { ?venue troc:VenueAddress ?address }\n" +
                "  OPTIONAL { ?venue troc:VenueCapacity ?capacity }\n" +
                "  OPTIONAL { ?venue troc:VenueDescription ?description }\n" +
                "  OPTIONAL { ?venue troc:VenueImage ?image }\n" +
                "  OPTIONAL { ?venue troc:VenueName ?name }\n" +
                "  OPTIONAL { ?venue troc:website ?website }\n" +
                "}";

        return executeQuery(query);
    }
    public ResultSet getAnnoncesWithDetails() {
        String query = "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
                "PREFIX owl: <http://www.w3.org/2002/07/owl#>\n" +
                "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n" +
                "PREFIX troc: <http://www.owl-ontologies.com/Troc.owl#>\n" +
                "SELECT ?annonce ?description ?echange ?photo ?rubrique ?titre ?email\n" +
                "WHERE {\n" +
                "  ?annonce rdf:type troc:Annonce .\n" +
                "  ?annonce troc:Description ?description .\n" +
                "  ?annonce troc:Echange ?echange .\n" +
                "  ?annonce troc:Photo ?photo .\n" +
                "  ?annonce troc:Rubrique ?rubrique .\n" +
                "  ?annonce troc:Titre ?titre .\n" +
                "  ?annonce troc:email ?email .\n" +
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

    public ResultSet getBarterRequests() {
        String query = "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
                "PREFIX troc: <http://www.owl-ontologies.com/Troc.owl#>\n" +
                "SELECT ?barterRequest ?description ?price ?title\n" +
                "WHERE {\n" +
                "  ?barterRequest rdf:type troc:BarterRequest .\n" +
                "  OPTIONAL { ?barterRequest troc:BarterDescription ?description }\n" +
                "  OPTIONAL { ?barterRequest troc:BarterPrice ?price }\n" +
                "  OPTIONAL { ?barterRequest troc:BarterTitle ?title }\n" +
                "}";

        return executeQuery(query);
    }

    public ResultSet getReportsWithDetails() {
        String query = "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
                "PREFIX troc: <http://www.owl-ontologies.com/Troc.owl#>\n" +
                "SELECT ?report ?cause ?senderName\n" +
                "WHERE {\n" +
                "  ?report rdf:type troc:Report .\n" +
                "  OPTIONAL { ?report troc:ReportCause ?cause }\n" +
                "  OPTIONAL { ?report troc:SenderName ?senderName }\n" +
                "}";

        return executeQuery(query);
    }

    public ResultSet getResponsesWithDetails() {
        String query = """
            PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
            PREFIX troc: <http://www.owl-ontologies.com/Troc.owl#>

            SELECT ?response ?message ?status
            WHERE {
              ?response rdf:type troc:Response .
              OPTIONAL { ?response troc:ResponseMessage ?message }
              OPTIONAL { ?response troc:ResponseStatus ?status }
            }
            """;

        return executeQuery(query);
    }


    public ResultSet getReviewsWithDetails() {
        String query = """
            PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
            PREFIX troc: <http://www.owl-ontologies.com/Troc.owl#>

            SELECT ?review ?comments ?status ?rating
            WHERE {
              ?review rdf:type troc:Review .
              OPTIONAL { ?review troc:ReviewComments ?comments }
              OPTIONAL { ?review troc:ReviewStatus ?status }
              OPTIONAL { ?review troc:Star_Rating ?rating }
            }
            """;

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

    public ResultSet getFormsWithDetails() {
        String query = """
            PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
            PREFIX troc: <http://www.owl-ontologies.com/Troc.owl#>

            SELECT ?form ?description ?image ?receiverName
            WHERE {
              ?form rdf:type troc:Form .
              OPTIONAL { ?form troc:FormDescription ?description }
              OPTIONAL { ?form troc:FormImage ?image }
              OPTIONAL { ?form troc:ReceiverName ?receiverName }
            }
            """;

        return executeQuery(query);
    }

    public ResultSet getDigitalProductsWithDetails() {
        String query = """
            PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
            PREFIX troc: <http://www.owl-ontologies.com/Troc.owl#>

            SELECT ?product ?address ?category ?description ?image ?name ?price ?status ?units ?weight
            WHERE {
              ?product rdf:type troc:DigitalProduct .
              OPTIONAL { ?product troc:ProductAddress ?address }
              OPTIONAL { ?product troc:ProductCategory ?category }
              OPTIONAL { ?product troc:ProductDescription ?description }
              OPTIONAL { ?product troc:ProductImage ?image }
              OPTIONAL { ?product troc:ProductName ?name }
              OPTIONAL { ?product troc:ProductPrice ?price }
              OPTIONAL { ?product troc:ProductStatus ?status }
              OPTIONAL { ?product troc:ProductUnits ?units }
              OPTIONAL { ?product troc:ProductWeight ?weight }
            }
            """;

        return executeQuery(query);
    }

    public ResultSet getForumsWithDetails() {
        String query = """
            PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
            PREFIX troc: <http://www.owl-ontologies.com/Troc.owl#>

            SELECT ?forum ?likes ?post
            WHERE {
              ?forum rdf:type troc:Forum .
              OPTIONAL { ?forum troc:ForumNbLike ?likes }
              OPTIONAL { ?forum troc:ForumPost ?post }
            }
            """;

        return executeQuery(query);
    }

    public ResultSet getCartsWithDetails() {
        String query = """
            PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
            PREFIX troc: <http://www.owl-ontologies.com/Troc.owl#>

            SELECT ?cart ?numProducts ?status
            WHERE {
              ?cart rdf:type troc:Cart .
              OPTIONAL { ?cart troc:CartNbProduct ?numProducts }
              OPTIONAL { ?cart troc:CartStatus ?status }
            }
            """;

        return executeQuery(query);
    }

}
