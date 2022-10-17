package pianocartesiano.viste;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import pianocartesiano.domain.Esponenziale;
import pianocartesiano.domain.Funzione;
import pianocartesiano.domain.FunzionePolinomiale;
import pianocartesiano.domain.Iperbole;
import pianocartesiano.domain.Parabola;
import pianocartesiano.domain.Polinomio;
import pianocartesiano.domain.Retta;

public class MenuGenerator {
	private final static VBox MENU = new VBox();
	private final static Button INVIA_FUNZIONE = new Button("Disegna");
	private final static Label ERRORE = new Label();
	private final static Label TITOLO = new Label();
	private final static FunctionInformationArea INFORMAZIONI_FUNZIONE = new FunctionInformationArea();
	static {
		INVIA_FUNZIONE.getStyleClass().add("bottone");
		ERRORE.getStyleClass().add("errore");
		TITOLO.getStyleClass().add("titolo");
		ERRORE.setMaxWidth(MENU.getMaxWidth());
		ERRORE.setMinHeight(MENU.getMaxHeight());
		TITOLO.setMaxWidth(MENU.getMaxWidth());
		TITOLO.setMinHeight(MENU.getMaxHeight());

	}

	public static VBox generaMenuRetta() {
		MENU.getChildren().clear();
		TITOLO.setText("Inserisci i dati della Retta");
		INVIA_FUNZIONE.disableProperty().unbind();
		LabelledTextField coeff = new LabelledTextField("Coefficiente: ");
		LabelledTextField termineNoto = new LabelledTextField("Termine: ");
		INVIA_FUNZIONE.disableProperty().bind(coeff.isEmpty().or(termineNoto.isEmpty()));
		INVIA_FUNZIONE.setOnAction((ActionEvent e) -> {
			try {
				Funzione retta = new Retta(Double.parseDouble(coeff.getDato()),
						Double.parseDouble(termineNoto.getDato()), Piano.getPiano().getNumTacks());
				Piano.getPiano().setFunzione(retta);
				INFORMAZIONI_FUNZIONE.setInformations(retta);
				ERRORE.setText("");
			} catch (Exception ex) {
				String tn = termineNoto.getDato();
				if (!tn.startsWith("-") & !tn.startsWith("+"))
					tn = "+" + tn;
				ERRORE.setText(
						"Dati inseriti errati.\nLa formula [" + coeff.getDato() + "x " + tn + "]\n non e' valida.");
			}
		});
		;
		MENU.getChildren().addAll(TITOLO, coeff, termineNoto, INVIA_FUNZIONE, ERRORE, INFORMAZIONI_FUNZIONE);
		return MENU;
	}

	public static VBox generaMenuParabola() {
		MENU.getChildren().clear();
		TITOLO.setText("Inserisci i dati della Parabola");
		INVIA_FUNZIONE.disableProperty().unbind();
		LabelledTextField coeffQuad = new LabelledTextField("Ampiezza: ");
		LabelledTextField coeff = new LabelledTextField("Spostamento: ");
		LabelledTextField termineNoto = new LabelledTextField("Termine: ");
		INVIA_FUNZIONE.disableProperty().bind(coeffQuad.isEmpty().or(coeff.isEmpty().or(termineNoto.isEmpty())));
		INVIA_FUNZIONE.setOnAction((ActionEvent e) -> {
			try {
				Funzione parabola = new Parabola(Double.parseDouble(coeffQuad.getDato()),
						Double.parseDouble(coeff.getDato()), Double.parseDouble(termineNoto.getDato()),
						Piano.getPiano().getNumTacks());
				Piano.getPiano().setFunzione(parabola);
				INFORMAZIONI_FUNZIONE.setInformations(parabola);
				ERRORE.setText("");
			} catch (Exception ex) {
				String cff = coeff.getDato();
				String tn = termineNoto.getDato();
				if (!cff.startsWith("-") & !cff.startsWith("+"))
					cff = "+" + cff;
				if (!tn.startsWith("-") & !tn.startsWith("+"))
					tn = "+" + tn;
				ERRORE.setText("Dati inseriti errati.\nLa formula [" + coeffQuad.getDato() + "x^2 " + cff + "x " + tn
						+ "]\n non e' valida.");
			}
		});
		;
		MENU.getChildren().addAll(TITOLO, coeffQuad, coeff, termineNoto, INVIA_FUNZIONE, ERRORE, INFORMAZIONI_FUNZIONE);
		return MENU;
	}

	public static VBox generaMenuEsponenziale() {
		MENU.getChildren().clear();
		TITOLO.setText("Inserisci i dati dell'Esponenziale");
		INVIA_FUNZIONE.disableProperty().unbind();
		LabelledTextField termineNoto = new LabelledTextField("Termine: ");
		INVIA_FUNZIONE.disableProperty().bind(termineNoto.isEmpty());
		INVIA_FUNZIONE.setOnAction((ActionEvent e) -> {
			try {
				Funzione esponenziale = new Esponenziale(Double.parseDouble(termineNoto.getDato()),
						Piano.getPiano().getNumTacks());
				Piano.getPiano().setFunzione(esponenziale);
				INFORMAZIONI_FUNZIONE.setInformations(esponenziale);
				ERRORE.setText("");
			} catch (Exception ex) {
				String tn = termineNoto.getDato();
				if (!(tn.startsWith("-") | !tn.startsWith("+")))
					tn = "+" + tn;
				ERRORE.setText("Dati inseriti errati.\nLa formula [" + tn + "^x]\n non e' valida.");
			}
		});
		;
		MENU.getChildren().addAll(TITOLO, termineNoto, INVIA_FUNZIONE, ERRORE, INFORMAZIONI_FUNZIONE);
		return MENU;
	}

	public static VBox generaMenuPolinomio() {
		INVIA_FUNZIONE.disableProperty().unbind();
		MENU.getChildren().clear();
		TITOLO.setText("Inserisci un Polinomio");
		LabelledTextField polinomio = new LabelledTextField("Funzione: ");
		INVIA_FUNZIONE.disableProperty().bind(polinomio.isEmpty());
		INVIA_FUNZIONE.setOnAction((ActionEvent e) -> {
			try {
				Funzione funzPolin = new FunzionePolinomiale(Polinomio.fromString(polinomio.getDato()),
						Piano.getPiano().getNumTacks());
				Piano.getPiano().setFunzione(funzPolin);
				INFORMAZIONI_FUNZIONE.setInformations(funzPolin);
				ERRORE.setText("");
			} catch (Exception ex) {
				ERRORE.setText("Dati Inseriti errati.\nLa formula [" + polinomio.getDato() + "] non è valida.");
			}
		});
		;
		MENU.getChildren().addAll(TITOLO, polinomio, INVIA_FUNZIONE, ERRORE, INFORMAZIONI_FUNZIONE);
		return MENU;
	}

	public static VBox generaMenuIperbole() {
		INVIA_FUNZIONE.disableProperty().unbind();
		MENU.getChildren().clear();
		TITOLO.setText("Inserisci un i dati per l'Iperbole");
		LabelledTextField coeff = new LabelledTextField("Coefficiente: ");
		LabelledTextField polinomio = new LabelledTextField("Termine frazionario: ");
		LabelledTextField termineNoto = new LabelledTextField("Termine Noto: ");
		INVIA_FUNZIONE.disableProperty().bind(polinomio.isEmpty().or(coeff.isEmpty().or(termineNoto.isEmpty())));
		INVIA_FUNZIONE.setOnAction((ActionEvent e) -> {
			try {
				Funzione iperbole = new Iperbole(Polinomio.fromString(polinomio.getDato()),
						Double.parseDouble(coeff.getDato()), Double.parseDouble(termineNoto.getDato()),
						Piano.getPiano().getNumTacks());
				Piano.getPiano().setFunzione(iperbole);
				INFORMAZIONI_FUNZIONE.setInformations(iperbole);
				ERRORE.setText("");
			} catch (Exception ex) {
				ex.printStackTrace();
				ERRORE.setText("Dati Inseriti errati.\nLa formula [" + polinomio.getDato() + "] non è valida.");
			}
		});
		;
		MENU.getChildren().addAll(TITOLO, coeff, polinomio, termineNoto, INVIA_FUNZIONE, ERRORE, INFORMAZIONI_FUNZIONE);
		return MENU;
	}
}
