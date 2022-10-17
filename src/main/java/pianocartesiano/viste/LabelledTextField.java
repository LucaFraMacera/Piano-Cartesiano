package pianocartesiano.viste;

import javafx.beans.binding.BooleanBinding;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class LabelledTextField extends HBox {
	private Label etichetta;
	private TextField dato;

	public LabelledTextField(String etichetta) {
		super();
		this.getStyleClass().add("labelled-text-field");
		this.etichetta = new Label(etichetta);
		this.dato = new TextField();
		this.etichetta.getStyleClass().add("etichetta");
		this.getChildren().addAll(this.etichetta, this.dato);
	}

	public String getDato() throws NumberFormatException {
		return this.dato.getText();
	}

	public BooleanBinding isEmpty() {
		return this.dato.textProperty().isEmpty();
	}
}