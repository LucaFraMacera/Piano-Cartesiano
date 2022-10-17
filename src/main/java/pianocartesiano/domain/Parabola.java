package pianocartesiano.domain;

import javafx.scene.shape.Line;
import javafx.scene.shape.Shape;

public class Parabola extends Funzione{
	private double coefficienteAmpiezza;
	private double coefficienteSpostamento;
	private double termineNoto;
	
	public Parabola(double coefficienteAmpiezza, double coefficienteSpostamento, double termineNoto,int numTacks) {
		this.coefficienteAmpiezza = coefficienteAmpiezza;
		this.coefficienteSpostamento = coefficienteSpostamento;
		this.termineNoto = termineNoto;
		this.calcolaPuntiFunzione(numTacks/2);
	}

	@Override
	public Shape drawFunction(int dimTacks) {
		Punto min = this.punti.get(0).toJavaCoordinates(dimTacks);
		Punto peMin = this.punti.get(1).toJavaCoordinates(dimTacks);
		Shape parabola = new Line(min.getX(),min.getY(),peMin.getX(),peMin.getY());
		for(int i = 1;i<this.punti.size()-1;i++) {
			Punto p = this.punti.get(i).toJavaCoordinates(dimTacks);
			Punto p1 = this.punti.get(i+1).toJavaCoordinates(dimTacks);
			parabola = Shape.union(parabola, new Line(p.getX(),p.getY(),p1.getX(),p1.getY()));
		}
		parabola.setFill(this.getColorForFunzione());
		return parabola;
	}

	@Override
	public void calcolaPuntiFunzione(int numTacks) {
		for(double x = -numTacks;x<=numTacks; x+=INTERVAL) {
			double aX =(this.coefficienteAmpiezza*Math.pow(x, 2));
			double bX = this.coefficienteSpostamento*x;
			double y = aX+bX+this.termineNoto;
			this.punti.add(new Punto(x,y));
		}
	}

}
