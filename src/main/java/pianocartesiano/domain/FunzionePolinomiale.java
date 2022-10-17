package pianocartesiano.domain;

import javafx.scene.shape.Line;
import javafx.scene.shape.Shape;

public class FunzionePolinomiale extends Funzione {
	private Polinomio funzione;

	public FunzionePolinomiale(Polinomio p,int numTacks) {
		this.funzione = p;
		this.calcolaPuntiFunzione(numTacks/2);
	}

	@Override
	public Shape drawFunction(int dimTacks) {
		Punto min = this.punti.get(0).toJavaCoordinates(dimTacks);
		Punto peMin = this.punti.get(1).toJavaCoordinates(dimTacks);
		Shape funzPoli = new Line(min.getX(),min.getY(),peMin.getX(),peMin.getY());
		for(int i = 1;i<this.punti.size()-1;i++) {
			Punto p = this.punti.get(i).toJavaCoordinates(dimTacks);
			Punto p1 = this.punti.get(i+1).toJavaCoordinates(dimTacks);
			funzPoli = Shape.union(funzPoli, new Line(p.getX(),p.getY(),p1.getX(),p1.getY()));
		}
		funzPoli.setFill(this.getColorForFunzione());
		return funzPoli;
	}

	@Override
	public void calcolaPuntiFunzione(int numTacks) {
		for (double x = -numTacks; x <= numTacks; x+=INTERVAL) {
			double y = this.funzione.getValoreFor(x);
			this.punti.add(new Punto(x,y));
		}
	}

}
