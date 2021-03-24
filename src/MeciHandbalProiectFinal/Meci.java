package MeciHandbalProiectFinal;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Map.Entry;
import java.util.stream.Collectors;


public class Meci {

	
	public static int meniu() {
    	System.out.println();
    	System.out.println("1.Afisare Goluri");
    	System.out.println("2.Afisare goluri ordonate per echipa");
    	System.out.println("3.Golgheterii echipelor");
    	System.out.println("4.Afisare Scor");
    	System.out.println("0.terminare program");
    	return citireNr("Optiunea ta: ");
    }
	
	public static int citireNr(String sir) {
		try {
    		System.out.print(sir);
    		Scanner s=new Scanner (System.in); 
    		int L= s.nextInt();		
    		return L;					
    	}
    	catch(Exception e) {
    		System.out.println("Ai introdus gresit, da un numar intreg.");
    		return citireNr(sir);	
    	}
	}
	
	public static void afisareGoluri(String[] sirGoluri){
		System.out.println("Jucatorii care au marcat goluri, in ordinea golurilor marcate, sunt: ");
		for (int i=0; i < sirGoluri.length; i++){
			System.out.print(sirGoluri[i]+"  ");
		}
		System.out.println("");
	}
	
	private static boolean DESC = false;
	
	private static Map<String, Integer> sortByValue(Map<String, Integer> unsortMap, final boolean order)
	    {
	        List<Entry<String, Integer>> list = new LinkedList<>(unsortMap.entrySet());

	        // Sorting the list based on values
	        list.sort((o1, o2) -> order ? o1.getValue().compareTo(o2.getValue()) == 0
	                ? o1.getKey().compareTo(o2.getKey())
	                : o1.getValue().compareTo(o2.getValue()) : o2.getValue().compareTo(o1.getValue()) == 0
	                ? o2.getKey().compareTo(o1.getKey())
	                : o2.getValue().compareTo(o1.getValue()));
	        return list.stream().collect(Collectors.toMap(Entry::getKey, Entry::getValue, (a, b) -> b, LinkedHashMap::new));

	    }
	private static void golgheter(Map<String,Integer> map) {
    	
   	 Map.Entry<String,Integer> entry = map.entrySet().iterator().next();
   	 String key = entry.getKey();
   	 Integer value = entry.getValue();
   	
   	System.out.println(key+" : " + value + " goluri");
      
}
	
	private static int scor(Map<String,Integer> map) {
		int scor=0;
		for (int valoare : map.values()) {
			scor+=valoare;
		}
		return scor;
	}
	
	public static void main(String[] args) {
		String[] goluri= {"A1", "A2", "A6", "A1", "A3", "A1", "A6", "B3", "B3", "B5", "B2", "B3", "B6", "B3", "B3"};
		Map<String, Integer> echipaA = new HashMap<>();
		Map <String, Integer> echipaB = new HashMap<>();
		
		int optiune = meniu();
		while (optiune !=0) {
			switch ((int)optiune) {
				case 1: 
					System.out.println();
					afisareGoluri(goluri);//afisare lista goluri
					for (String s : goluri) {
						if (s.contains("A")) {
							int prev=0;
							if (echipaA.get(s) != null) 
								prev = echipaA.get(s); //afiseaza frecventa cheii s
							
							echipaA.put(s, prev+1);
						
						} else if (s.contains("B")) {
							int prev=0;
							if (echipaB.get(s) != null) 
								prev = echipaB.get(s); //afiseaza frecventa cheii s
							
							echipaB.put(s, prev+1);
						
						}
					}
					break;
				case 2:
					System.out.println();
					System.out.println("Jucatorii echipei A care au dat goluri, ordonati in functie de numarul de goluri marcate: ");
					echipaA = sortByValue(echipaA, DESC);
			        System.out.println(echipaA);
			        System.out.println("Jucatorii echipei B care au dat goluri, ordonati in functie de numarul de goluri marcate: ");
			        echipaB = sortByValue(echipaB, DESC);
			        System.out.println(echipaB);
					break;
					
				case 3:
					System.out.println();
					System.out.println("Golgheterul echipei A este: ");
					golgheter(echipaA);
					System.out.println("Golgheterul echipei B este: ");
					golgheter(echipaB);
					break;
					
				case 4:
					System.out.println();
					int scorA=scor(echipaA);
			        int scorB=scor(echipaB);
			        if (scorA>scorB) {
			        	System.out.println("Scorul este \nEchipa A:  Echipa B ");
			        	System.out.println("    "+scorA + "   :     "+scorB);
			        } else if (scorA<scorB) {
			        	System.out.println("Scorul este \nEchipa B:  Echipa A ");
			        	System.out.println("    "+scorB + "   :     "+scorA);
			        	} else {
			        		System.out.println("Scorul este egal \nEchipa B:  Echipa A ");
				        	System.out.println("    "+scorB + "   :     "+scorA);
			        	}
			        break;
			    
				default: 
					   System.out.println("Nu ai introdus o optiune valida. Alege din meniu.");
				}
		        System.out.println();
				optiune=meniu();
					
				}
			
		
		
		
	}

}
