package org.formation.test;

public class Singleton {

	private static Singleton instance; //attribut priv� static de la classe

	public static Singleton getInstance() {
		
		if(instance==null){
			instance= new Singleton();
		}
		
		return instance;
		
	}
	
	private Singleton (){};
}
