package main;
import java.io.FileNotFoundException;

import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.ResultSetFormatter;
import com.hp.hpl.jena.rdf.model.InfModel;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.reasoner.Reasoner;
import com.hp.hpl.jena.reasoner.ReasonerRegistry;
import com.hp.hpl.jena.util.FileManager;

public class ReadModel {

    public static OntModel Ontologymodel = null;

    public static void main(String args[])  throws FileNotFoundException {

    	Model schema = FileManager.get().loadModel("file:ontologySameAs.owl");
    	Model data = FileManager.get().loadModel("file:ontologySameAs.rdf");
    	Reasoner reasoner = ReasonerRegistry.getOWLReasoner();
    	reasoner = reasoner.bindSchema(schema);
    	InfModel infmodel = ModelFactory.createInfModel(reasoner, data);
    	
    	
        /*OntModel m = ModelFactory.createOntologyModel(OntModelSpec.OWL_LITE_MEM, null);
        m.read(new FileInputStream(localSource),null);
        

        Iterator RootClasses = m.listHierarchyRootClasses();

        while (RootClasses.hasNext()) {
            String RootclassSTR = RootClasses.next().toString();
            System.out.println("ROOTCLASS: " + RootclassSTR);
            OntClass query = m.getOntClass(RootclassSTR);

            for (Iterator i = query.listSubClasses(); i.hasNext();) {
                OntClass c = (OntClass) i.next();
                System.out.println("                SubClass: " + c);
                
                for(Iterator it = c.listSubClasses(); it.hasNext();){
                	OntClass value = (OntClass) it.next();
                	System.out.println("                            SubClass: "+ value);
                }
                
                
            }

        }*/
                    
        String queryString = 
        	"PREFIX owl: <http://www.w3.org/2002/07/owl#>"+
        	"PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>"+
        	"PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>"+
        	"PREFIX pv: <http://dacura.cs.tcd.ie/data/politicalviolence#>"+
        	"PREFIX ev: <http://www.heml.org/schemas/2003-09-17/>"+
        	"PREFIX uspv: <http://dacura.cs.tcd.ie/data/politicalviolence/uspv/>"+
        	        	
        	"SELECT ?x ?y WHERE {"+
        		"?x rdf:type ev:hemlLocation ;"+
        		"pv:unstructuredLocation ?y ."+
        	"} \n";
        
        Query query = QueryFactory.create(queryString);

        System.out.println("----------------------");

        System.out.println("Query Result Sheet");

        System.out.println("----------------------");
       
        // Execute the query and obtain results
        QueryExecution qe = QueryExecutionFactory.create(query, infmodel);
        com.hp.hpl.jena.query.ResultSet results =  qe.execSelect();

        // Output query results    
        ResultSetFormatter.out(System.out, results, query);

        qe.close();
    }
} 
