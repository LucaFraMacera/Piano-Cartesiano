package pianocartesiano;

import javafx.application.Application;
import javafx.stage.Stage;
import pianocartesiano.viste.GestoreViste;

public class MainApp extends Application {
	@Override
	public void start(Stage stage) {
		GestoreViste.getGestore().startApp(stage);
	}

	public static void main(String[] args) {
		launch();
	}

}