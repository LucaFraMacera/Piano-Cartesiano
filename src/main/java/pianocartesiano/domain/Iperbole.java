package pianocartesiano.domain;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

import javafx.scene.shape.Line;
import javafx.scene.shape.Shape;
import pianocartesiano.viste.Piano;

public class Iperbole extends Funzione {
	private Polinomio polinomio;
	private double coefficiente;
	private double termineNoto;
	private DecimalFormat format;
	
	public Iperbole(Polinomio polinomio, double coefficiente, double termineNoto, int numTacks) {
		DecimalFormatSymbols simbolo = new DecimalFormatSymbols();
		simbolo.setDecimalSeparator('.');
		format = new DecimalFormat("#.00",simbolo);
		format.setMaximumFractionDigits(2);
		this.polinomio = polinomio;
		this.coefficiente = coefficiente;
		this.termineNoto = termineNoto;
		this.calcolaPuntiFunzione(numTacks / 2);
	}

	@Override
	public Shape drawFunction(int dimTacks) {
		Shape iperbole = new Line();
		for (int i = 0; i < this.punti.size() - 1; i++) {
			Punto p = this.punti.get(i).toJavaCoordinates(dimTacks);
			Punto p1 = this.punti.get(i + 1).toJavaCoordinates(dimTacks);
			if(isAsintoto(p) && isAsintoto(p1))
				continue;
			Line linea = new Line(p.getX(),p.getY(),p1.getX(),p1.getY());
			iperbole = Shape.union(iperbole, linea);
		}
		iperbole.setFill(this.getColorForFunzione());
		return iperbole;
	}

	@Override
	public void calcolaPuntiFunzione(int numTacks) {
		for (double x = -numTacks; x <= numTacks; x += INTERVAL) {
			x = Double.parseDouble(format.format(x));
			if(checkAsintoto(x)) {
				calcolaLimitiAsintoticiFor(x);
				continue;
			}
			if(checkAsintoto(Double.parseDouble(format.format(x-INTERVAL/2)))) {
				calcolaLimitiAsintoticiFor(Double.parseDouble(format.format(x-INTERVAL/2)));
				continue;
			}
			if(checkAsintoto(Double.parseDouble(format.format(x+INTERVAL/2)))) {
				calcolaLimitiAsintoticiFor(Double.parseDouble(format.format(x+INTERVAL/2)));
				continue;
			}
			double y = (this.coefficiente / this.polinomio.getValoreFor(x)) + this.termineNoto;
			y = Double.parseDouble(format.format(y));
			this.punti.add(new Punto(x, y));
		}
	}
	private boolean isAsintoto(Punto p) {
		if(p.getY() <=0 || p.getY() >= 800)
			return true;
		return false;
	}
	private boolean checkAsintoto( double x) {
		double delta = 0.199;
		if(this.polinomio.getValoreFor(x) <= delta && this.polinomio.getValoreFor(x) >= -delta)
			return true;
		return false;
	}
	private void calcolaLimitiAsintoticiFor(double x) {
		double y = (this.coefficiente / this.polinomio.getValoreFor(x-INTERVAL/2)) + this.termineNoto;
		y= Double.parseDouble(format.format(y));
		Punto left = new Punto(x-INTERVAL/2,y*Piano.getPiano().getNumTacks());
		y = (this.coefficiente / this.polinomio.getValoreFor(x+INTERVAL/2)) + this.termineNoto;
		Punto right = new Punto(x+INTERVAL/2,y*Piano.getPiano().getNumTacks());
		this.punti.add(left);
		this.punti.add(right);
	}
}
