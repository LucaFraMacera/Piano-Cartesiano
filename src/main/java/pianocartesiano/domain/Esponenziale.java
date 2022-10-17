package pianocartesiano.domain;

import javafx.scene.shape.Line;
import javafx.scene.shape.Shape;

public class Esponenziale extends Funzione {
	private double base;

	public Esponenziale(double base, int numTacks) {
		this.base = base;
		this.calcolaPuntiFunzione(numTacks / 2);
	}

	@Override
	public Shape drawFunction(int dimTacks) {
		Punto min = this.punti.get(0).toJavaCoordinates(dimTacks);
		Punto peMin = this.punti.get(1).toJavaCoordinates(dimTacks);
		Shape esponenziale = new Line(min.getX(), min.getY(), peMin.getX(), peMin.getY());
		for (int i = 1; i < this.punti.size() - 1; i++) {
			Punto p = this.punti.get(i).toJavaCoordinates(dimTacks);
			Punto p1 = this.punti.get(i + 1).toJavaCoordinates(dimTacks);
			esponenziale = Shape.union(esponenziale, new Line(p.getX(), p.getY(), p1.getX(), p1.getY()));
		}
		esponenziale.setFill(this.getColorForFunzione());
		return esponenziale;
	}

	@Override
	public void calcolaPuntiFunzione(int numTacks) {
		for (double x = -numTacks; x <= numTacks; x += INTERVAL) {
			double y = Math.pow(this.base, x);
			this.punti.add(new Punto(x, y));
		}
	}

}
