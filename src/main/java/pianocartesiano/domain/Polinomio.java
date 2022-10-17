package pianocartesiano.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public class Polinomio {
	private List<Monomio> termini = new ArrayList<Monomio>();
	
	public Polinomio() {
		
	}
	public void addTermini(Monomio...monomi) {
		for(Monomio m : monomi)
			this.addTermine(m);
		this.riduci();
	}
	public boolean addTermine(Monomio t) {
		if(t == null)
			return false;
		this.termini.add(t);
		this.riduci();
		return true;
	}
	public List<Monomio> getTermini(){
		return this.termini;
	}
	private void riduci() {
		List<Monomio> newPol = new ArrayList<Monomio>();
		Collections.sort(this.termini);
		Monomio m1,m2;
		if(this.termini.isEmpty())
			return;
		Iterator<Monomio> iter = this.termini.iterator();
		m1 = iter.next();
		newPol.add(m1);
		while(iter.hasNext()) {
			m2 = iter.next();
			if(m1.getEsponente() == m2.getEsponente())
				m1.setCoefficiente(m1.getCoefficiente()+m2.getCoefficiente());
			else {
				newPol.add(m2);
				m1=m2;
			}
		}
		this.termini = newPol;
		this.ordina();
	}
	public void print() {
		for(Monomio m : this.termini)
			System.out.print(m);
		System.out.println();
	}
	public boolean isZero() {
		return this.termini.size() < 1;
	}
	private void ordina() {
		Collections.sort(this.termini, new Comparator<Monomio>() {
			@Override
			public int compare(Monomio o1, Monomio o2) {
				return o2.compareTo(o1);
			}
		});
		Iterator<Monomio> iter = this.termini.iterator();
		while(iter.hasNext()) {
			Monomio m = iter.next();
			if(m.getCoefficiente() == 0)
				iter.remove();
		}
	}
	public double getValoreFor(double x) {
		double result =0;
		for(Monomio mon : this.termini)
			result += mon.getCoefficiente()*Math.pow(x, mon.getEsponente());
		return result;
	}
	public static Polinomio fromString(String espressione) throws EspressioneMonomialeException{
		if(espressione.length() <1)
			throw new EspressioneMonomialeException("Stringa vuota");
		Polinomio polinomio = new Polinomio();
		espressione = espressione.replace("+", " +").replace("-"," -");
		String[] termini = espressione.trim().split(" ");
		for(String termine : termini)
			polinomio.addTermine(Monomio.fromString(termine));
		return polinomio;
	}
	@Override
	public String toString(){
		String result = "";
		for(Monomio m : this.termini)
			result+=m.toString();
		return result;
	}
}
