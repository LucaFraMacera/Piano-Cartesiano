package pianocartesiano.viste;
import javafx.event.ActionEvent;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;

public class FunctionInformationArea extends VBox{
	private CheckBox box = new CheckBox("Visualizzare Dettagli?");
	private TextArea area = new TextArea();
	public FunctionInformationArea() {
		area.setEditable(false);
		area.setWrapText(true);
		area.setMinSize(this.getMaxWidth(), this.getMaxHeight());
		box.getStyleClass().add("etichetta");
		area.setVisible(false);
		this.box.setOnAction((ActionEvent e)->{
			this.area.setVisible(!this.area.isVisible());
		});
		this.getChildren().addAll(box,area);
	}
	public void setInformations(Object informations) {
		this.area.setText(informations.toString());
	}
}
