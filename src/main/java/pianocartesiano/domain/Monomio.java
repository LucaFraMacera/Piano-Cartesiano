package pianocartesiano.domain;

class EspressioneMonomialeException extends Exception {
	public EspressioneMonomialeException() {
		super();
	}

	public EspressioneMonomialeException(String messaggio) {
		super(messaggio);
	}

	public EspressioneMonomialeException(String messaggio, Throwable causa) {
		super(messaggio, causa);
	}
}

public class Monomio implements Comparable<Monomio> {
	private double coefficiente;
	private double esponente;

	public Monomio() {
		this(0, 0);
	}

	public Monomio(double coeff) {
		this(coeff, 0);
	}
	public Monomio(double coefficiente, double esponente) {
		this.coefficiente = coefficiente;
		this.esponente = esponente;
	}

	public double getCoefficiente() {
		return coefficiente;
	}

	public void setCoefficiente(double coefficiente) {
		this.coefficiente = coefficiente;
	}

	public double getEsponente() {
		return esponente;
	}

	public void setEsponente(double esponente) {
		this.esponente = esponente;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Monomio))
			return false;
		Monomio other = (Monomio) obj;
		return this.coefficiente == other.coefficiente && this.esponente == other.esponente;
	}

	@Override
	public String toString() {
		String result = (this.coefficiente >= 0) ? " +" + String.valueOf(this.coefficiente)
				: " "+String.valueOf(this.coefficiente);
		if (this.esponente >= 2)
			result += "x^" + String.valueOf(this.esponente);
		else if (this.esponente == 1)
			result += "x";
		return result;
	}

	public static Monomio fromString(String testo) throws EspressioneMonomialeException {
		if (testo.length() < 1)
			throw new EspressioneMonomialeException("Stringa vuota");
		try {
			String coeff = "";
			String esp = "";
			int posX = 0;
			if(testo.contains("x^")) {
				posX = testo.indexOf("x^");
				coeff = testo.substring(0,posX);
				esp = testo.substring(posX+2,testo.length()); 
			}
			else if(testo.contains("x")) {
				posX = testo.indexOf("x");
				coeff = testo.substring(0,posX);
				esp += 1;
			}
			else
				coeff = testo;
			
			if(coeff.length()<1)
				coeff+=1;
			if(coeff.length()<=1 && (coeff.startsWith("-")||coeff.startsWith("+")))
				coeff+=1;
			if(esp.length()<1)
				esp+="0";
			return new Monomio(Double.parseDouble(coeff),Double.parseDouble(esp));
			//return new Monomio(Double.parseDouble(coeff.toString()),Integer.parseInt(esp.toString()));		
		} catch (NumberFormatException e) {
			throw new EspressioneMonomialeException("I dati inseriti sono errati.", e.getCause());
		}
	}

	@Override
	public int compareTo(Monomio o) {
		if (this.esponente > o.esponente)
			return 1;
		else if (o.esponente > this.esponente)
			return -1;
		else if (this.coefficiente > o.coefficiente)
			return 1;
		else if (o.coefficiente > this.coefficiente)
			return -1;
		return 0;
	}

}
