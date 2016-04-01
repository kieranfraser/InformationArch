package gui;

import java.net.URL;
import java.util.ResourceBundle;

import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.query.ResultSetFormatter;
import com.hp.hpl.jena.rdf.model.InfModel;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.reasoner.Reasoner;
import com.hp.hpl.jena.reasoner.ReasonerRegistry;
import com.hp.hpl.jena.util.FileManager;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

public class GUIController implements Initializable{
	
	@FXML // fx:id="question1"
    private Button question1; // Value injected by FXMLLoader

    @FXML // fx:id="question4"
    private Button question4; // Value injected by FXMLLoader

    @FXML // fx:id="question2"
    private Button question2; // Value injected by FXMLLoader
    
    @FXML // fx:id="question11"
    private Button question11; // Value injected by FXMLLoader

    @FXML // fx:id="question3"
    private Button question3; // Value injected by FXMLLoader

    @FXML // fx:id="question5"
    private Button question5; // Value injected by FXMLLoader

    @FXML // fx:id="question6"
    private Button question6; // Value injected by FXMLLoader

    @FXML // fx:id="question7"
    private Button question7; // Value injected by FXMLLoader

    @FXML // fx:id="question8"
    private Button question8; // Value injected by FXMLLoader

    @FXML // fx:id="question9"
    private Button question9; // Value injected by FXMLLoader

    @FXML // fx:id="question10"
    private Button question10; // Value injected by FXMLLoader

    @FXML // fx:id="resultTextBox"
    private TextArea resultTextBox; // Value injected by FXMLLoader
    
    private Model schema;
    private Model data;
    private Reasoner reasoner;
    private InfModel infmodel;
    
    @Override
	public void initialize(URL location, ResourceBundle resources) {
    	schema = FileManager.get().loadModel("file:Project_16_45.owl");
    	data = FileManager.get().loadModel("file:Project_16_45.rdf");
    	reasoner = ReasonerRegistry.getOWLReasoner();
    	reasoner = reasoner.bindSchema(schema);
    	infmodel = ModelFactory.createInfModel(reasoner, data);
	}
    
    /*private String prefix = "PREFIX owl: <http://www.w3.org/2002/07/owl#>"+"\n"+
		"PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>"+"\n"+
		"PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>"+"\n"+
		"PREFIX pv: <http://dacura.cs.tcd.ie/data/politicalviolence#>"+"\n"+
		"PREFIX ev: <http://www.heml.org/schemas/2003-09-17/>"+"\n"+
		"PREFIX nvpe: <http://www.co-ode.org/ontologies/ont.owl#>"+"\n"+
        "PREFIX uspv: <http://dacura.cs.tcd.ie/data/politicalviolence/uspv/>"+"\n"+
		"PREFIX geo:<http://www.w3.org/2003/01/geo/wgs84_pos#>"
        + "\n";*/
    
    private String prefix = "PREFIX owl: <http://www.w3.org/2002/07/owl#>"+
    	"PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>"+
    	"PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>"+
    	"PREFIX pv: <http://dacura.cs.tcd.ie/data/politicalviolence#>"+
    	"PREFIX ev: <http://www.heml.org/schemas/2003-09-17/>"+
    	"PREFIX nvpe: <http://www.semanticweb.org/claire/ontologies/2016/3/newOntology#>"+
    	"PREFIX geo:<http://www.w3.org/2003/01/geo/wgs84_pos#>"+
    	"PREFIX clon: <http://www.semanticweb.org/claire/ontologies/2016/3/newOntology#>"+
    	"PREFIX foaf: <http://xmlns.com/foaf/0.1/>"+
    	"PREFIX dbo: <http://live.dbpedia.org/ontology/>";

    @FXML
    void fireQueryOne(ActionEvent event) {
    	this.resultTextBox.setText("Firing query one.");
    	    	
    	//runQueryAndShow(query);
    }

    @FXML
    void fireQueryTwo(ActionEvent event) {
    	this.resultTextBox.setText("Firing query two.");
    	
    	String query = prefix+
    		"SELECT ?z WHERE {"+
    		"?a rdf:type nvpe:USPVAndUSNationalEvent ;"+
    		"ev:hemlKeywordClassificationSetRef ?z ;"+
    		"rdfs:label ?x ."+
    		"FILTER regex(?x, \"Elijah\")"+	
    	"}";
    	
    	runQueryAndShow(query);
    }
    
    @FXML
    void fireQueryThree(ActionEvent event) {
    	this.resultTextBox.setText("Firing query three.");
    	
    	String query = prefix+
    	"SELECT * WHERE {"+
    		
    		"?a rdf:type nvpe:USPVAndUSNationalEvent ;"+
    		"ev:hemlKeywordClassificationSetRef ?z ;"+
    		"rdfs:label ?x ."+
    		
    		"FILTER regex(?x, \"A\")	"+
    	"}";
    	
    	runQueryAndShow(query);
    }
    
    @FXML
    void fireQueryFour(ActionEvent event) {
    	this.resultTextBox.setText("Firing query four.");
    	
    	String query = prefix+
    	"SELECT ?x ?y WHERE {"+
		"?a rdf:type nvpe:nationalPoliticalViolenceLocation ;"+
		"geo:lat ?x ;"+
		"geo:long ?y ;"+
		"rdfs:label ?z ."+
		
		"?c rdf:type nvpe:nationalPoliticalViolenceLocation ;"+
		"pv:unstructuredLocation ?b ."+
		
		"FILTER regex(?z, \"Illinois\")"+
		"FILTER regex(?b, \"Alton\")"+
    	"}";

    	runQueryAndShow(query);
    }
    
    @FXML
    void fireQueryFive(ActionEvent event) {
    	this.resultTextBox.setText("Firing query five.");
    	
    	String query = prefix+
		"SELECT ?x ?y WHERE {"+
		"?a rdf:type nvpe:nationalPoliticalViolenceLocation ;"+
		"geo:lat ?x ;"+
		"geo:long ?y ;"+
		"rdfs:label ?z ."+
		"?c rdf:type nvpe:nationalPoliticalViolenceLocation ;"+
		"pv:unstructuredLocation ?b ."+
		"FILTER regex(?z, \"Baltimore\")"+
		"FILTER regex(?b, \"Baltimore\")"+
		"}";

    	runQueryAndShow(query);
    }
    
    @FXML
    void fireQuerySix(ActionEvent event) {
    	this.resultTextBox.setText("Firing query six.");
    	
    	String query = prefix+
    	    	"SELECT ?x ?y WHERE {"+
    	    		
    			"?a rdf:type nvpe:USPVAndUSNationalEvent ;"+
    			"rdfs:label ?x ."+
    			
    			"?b rdf:type nvpe:USPVAndUSNationalEvent ;"+
    			"pv:description ?y ."+

    			"FILTER regex(?x, \"A\")"+
    			"FILTER regex(?y, \"A\")"+
    	    	"}";

    	    	runQueryAndShow(query);
    }
    
    @FXML
    void fireQuerySeven(ActionEvent event) {
    	this.resultTextBox.setText("Firing query seven.");
    	
    	String query = prefix+
    	"SELECT ?x ?y WHERE {"+
		"?a rdf:type nvpe:USPVAndUSNationalEvent ;"+
		"rdfs:label ?x ."+
		"?b rdf:type nvpe:USPVAndUSNationalEvent ;"+
		"pv:description ?y ."+
		"FILTER regex(?x, \"E\")"+
		"FILTER regex(?y, \"E\")"+
    	"}";
    	
    	runQueryAndShow(query);
    }
    
    @FXML
    void fireQueryEight(ActionEvent event) {
    	this.resultTextBox.setText("Firing query eight.");
    	
    	String query = prefix+
    	"SELECT ?x ?y WHERE {"+
    	"?x rdf:type nvpe:StateLocation ;"+
    	"pv:unstructuredLocation ?y ."+
    	"} ORDER BY ?y";
    	
    	runQueryAndShow(query);
    }
    
    @FXML
    void fireQueryNine(ActionEvent event) {
    	this.resultTextBox.setText("Firing query nine.");
    	
    	//String query = prefix+
    	
    	
    	//runQueryAndShow(query);
    }
    
    @FXML
    void fireQueryTen(ActionEvent event) {
    	this.resultTextBox.setText("Firing query ten.");
    	
    	String query = prefix+
    	"SELECT ?x ?y WHERE {"+
    		
		"?a rdf:type foaf:Person ;"+
		"rdfs:label ?x ;"+
		"dbo:abstract ?y ."+
		
		"FILTER regex(?x, \"Elijah\")"+
    	"}";
    	
    	runQueryAndShow(query);
    }
    
    @FXML
    void fireQueryEleven(ActionEvent event) {
    	this.resultTextBox.setText("Firing query eleven.");
    	
    	String query = prefix+
		"SELECT ?x ?y WHERE {"+
			
		"?a rdf:type foaf:Person ;"+
		"rdfs:label ?x ;"+
		"dbo:abstract ?y ."+
		
		"FILTER regex(?x, \"James\")"+
		"FILTER regex(?y, \"James Monroe \")"+
		"}";
    	
    	runQueryAndShow(query);
    }
	
    /**
     * Run the query and show in the text box
     * @param queryString
     */
	private void runQueryAndShow(String queryString){
    	
    	         
        Query query = QueryFactory.create(queryString);
        System.out.println("----------------------");
        System.out.println("Query Result Sheet");
        System.out.println("----------------------");
       
        // Execute the query and obtain results
        QueryExecution qe = QueryExecutionFactory.create(query, infmodel);
        ResultSet results =  qe.execSelect();
        this.resultTextBox.setText(results.toString());

        
        ResultSetFormatter.out(System.out, results, query);
       
        
        /* List<String> resultList = new ArrayList<String>();
        while (results.hasNext()) {
        	QuerySolution soln =  (QuerySolution) results.next();
            String epName=soln.getLiteral("x").getLexicalForm();
            resultList.add(epName);
            System.out.println(epName);
        }*/

        // Output query results    
        qe.close();
    }

}
