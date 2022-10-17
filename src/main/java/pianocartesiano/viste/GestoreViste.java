package pianocartesiano.viste;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class GestoreViste {
	private static GestoreViste gestore = new GestoreViste();
	private Stage stage;
	private Scene scene;
	private BorderPane pane;
	
	private GestoreViste() {}
	
	public static GestoreViste getGestore() {
		return gestore;
	}
	
	public void startApp(Stage stage) {
		try {		
			this.stage = stage;
			this.stage.setTitle("Visualizzazione Funzioni");
			this.stage.setResizable(false);
			FXMLLoader loader = new FXMLLoader(getClass().getResource("MenuPrincipale.fxml"));
			this.pane= (BorderPane)loader.load();
			this.pane.setCenter(Piano.getPiano());
			this.scene = new Scene(pane);
			this.scene.getStylesheets().add(this.getClass().getResource("stileBianco.css").toExternalForm());
			this.stage.setScene(this.scene);
			this.stage.show();
		}catch(Exception e) {
			e.printStackTrace();
			System.exit(0);
		}
	}
	public void changeTheme(String tema) {
		this.scene.getStylesheets().set(0, this.getClass().getResource(tema+".css").toExternalForm());
	}
}
