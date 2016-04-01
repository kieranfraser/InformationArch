package main;
import java.io.FileNotFoundException;
import java.net.URL;

import com.hp.hpl.jena.ontology.OntModel;
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

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class LaunchGUI extends Application{

    public static OntModel Ontologymodel = null;

    public static void main(String args[])  throws FileNotFoundException {
    	launch(args);
    }

	@Override
	public void start(Stage primaryStage) throws Exception {

	    URL url = getClass().getResource("/gui/InformationArch.fxml");
	    AnchorPane pane = FXMLLoader.load( url );
	    Scene scene = new Scene( pane );
		
	    primaryStage.setScene( scene );
	    primaryStage.setTitle( "Demo" );
	    //primaryStage.setFullScreen(true);
	    primaryStage.show();
	}
} 
