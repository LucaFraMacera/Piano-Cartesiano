package pianocartesiano.viste;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Slider;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.VBox;
import pianocartesiano.domain.TipiFunzioni;

public class MenuController implements Initializable {
	@FXML
	private VBox vbox = new VBox();
	@FXML
	private VBox opzioni = new VBox();
	@FXML
	private ComboBox<TipiFunzioni> funzioni = new ComboBox<TipiFunzioni>();
	@FXML
	private Slider sliderTacks = new Slider();
	@FXML
	private ToggleButton themeSwitch = new ToggleButton();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		funzioni.getItems().addAll(TipiFunzioni.values());
		this.sliderTacks.valueProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				Piano.getPiano().setDimTacks(newValue.intValue());
			}
		});
	}

	public void changeTheme() {
		if (this.themeSwitch.isSelected()) {
			this.themeSwitch.setText("Tema Bianco");
			GestoreViste.getGestore().changeTheme("stileNero");
		} else {
			this.themeSwitch.setText("Tema Nero");
			GestoreViste.getGestore().changeTheme("stileBianco");
		}
	}

	public void changeMenu() {
		vbox.getChildren().retainAll(opzioni);
		switch (funzioni.getValue()) {
		case RETTA:
			vbox.getChildren().add(MenuGenerator.generaMenuRetta());
			break;
		case PARABOLA:
			vbox.getChildren().add(MenuGenerator.generaMenuParabola());
			break;
		case ESPONENZIALE:
			vbox.getChildren().add(MenuGenerator.generaMenuEsponenziale());
			break;
		case POLINOMIO:
			vbox.getChildren().add(MenuGenerator.generaMenuPolinomio());
			break;
		case IPERBOLE:
			vbox.getChildren().add(MenuGenerator.generaMenuIperbole());
			break;
		}
	}
}
