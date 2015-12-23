package uoc.ei.practica;

import java.util.Comparator;

import uoc.ei.tads.ClauValor;
import uoc.ei.tads.ContenidorAfitat;
import uoc.ei.tads.DiccionariVectorImpl;
import uoc.ei.tads.Iterador;
import uoc.ei.tads.IteradorVectorImpl;

/**
 * TAD que implementa un vector ordenat. L'ordenació del vector
 * es determina amb el comparador.
 */
public class OrderedVector<E> implements ContenidorAfitat<E>{

	
	private Comparator<E> comparator;
	
	private E[] data;
	private int len;


	public OrderedVector(int max, Comparator<E> comparator) {
		this.comparator = comparator;
		this.data= (E[])new Object[max];
		this.len=0;
	}

	public E elementAt(int i) {
		return this.data[i];
	}
	
	/**
	 * mètode que indica si un element és igual que el segon
	 * @param elem1
	 * @param elem2
	 * @return
	 */
	private boolean compare(E elem1, E elem2) {
		boolean res = ((Comparable<E>)elem1).compareTo(elem2)==0;
		return res;

	}
	
	public void rshift(int i) {
		// desplaçament de tots els elements una posició
		int p=this.len-1;
		while (p>=i) {
			this.data[p+1]=this.data[p];
			p--;
		}
	}

	public void lshift(int i) {
		// desplaçament de tots els elements una posició
		int p=i;
		while (p<this.len) {
			this.data[p]=this.data[p+1];
			p++;
		}
	}

	
	public void update(E vIn) {
		int i = 0;
		boolean end=false;
		E v = null;
		
		// Si existeix l'element esborrem l'element per tornar-lo a inserir a la seva posició
		this.delete(vIn);
		
		if (this.estaPle()) {
			E pOut = this.last();
			if (comparator.compare(pOut, vIn)>0) {
				this.delete(pOut);
				this.update(vIn);
			}
		}
		
		// fem un recorregut per determinar la posició a inserir
		
		while (i<this.len && this.data[i]!=null && this.comparator.compare(this.data[i], vIn)>=0) 
			i++;
		
		// desplaçament cap a la dreta de tots els elements
		rshift(i);
		
		// s'insereix l'element a la posició 
		this.data[i]=vIn;
		this.len++;
		
	}
	
	public void delete (E elem) {
		int i=0;
		boolean found=false;
		
		while (!found && i<this.len) 
			found= (compare(elem, this.data[i++]));		
		
		if (found) {
			lshift(i-1);
			this.len--;
		}
		
	}

	
	@Override
	public Iterador<E> elements() {
		// TODO Auto-generated method stub
		return (Iterador<E>)new IteradorVectorImpl(this.data, this.len,0);

	}


	@Override
	public boolean estaBuit() {
		return this.len==0;
	}


	@Override
	public int nombreElems() {
		return this.len;
	}

	@Override
	public boolean estaPle() {
		// TODO Auto-generated method stub
		return this.len==this.data.length;
	}



	

	/**
	 * mètode de prova
	 * @param args
	 */
	public static void main(String[] args) {
		Comparator<Integer> cmp = new Comparator<Integer>() {

			@Override
			public int compare(Integer arg0, Integer arg1) {
				// TODO Auto-generated method stub
				return arg0.compareTo(arg1);
			}
			
		};
		OrderedVector<Integer> v = new OrderedVector<Integer>(10, cmp);
		
		v.update(7);
		v.update(9);
		v.update(5);
		v.update(2);
		v.update(3);
		v.update(1);
		//v.afegir(4);
		v.update(6);

		System.out.println("estaPle "+v.estaPle());
		
		for (Iterador<Integer> it = v.elements(); it.hiHaSeguent();) {
			System.out.println(it.seguent());
		}
		
		System.out.println("delete 3");
		v.delete(3);
		
		for (Iterador<Integer> it = v.elements(); it.hiHaSeguent();) {
			System.out.println(it.seguent());
		}

		System.out.println("post delete 3");
		
		v.update(9);
		v.update(10);
		v.update(11);
		

		
		
		for (Iterador<Integer> it = v.elements(); it.hiHaSeguent();) {
			System.out.println(it.seguent());
		}

	
	}

	public E last() {
		// TODO Auto-generated method stub
		return this.data[this.len-1];
	}
	
	
}
