import java.util.Scanner;
import java.io.IOException;
import java.util.ArrayList;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 
 * @author owj3
 *
 */

public class Application {
	private static final String TREASURES_FILENAME = "treasures.txt";
	private static final String MONSTERS_FILENAME = "monsters.txt";
	private ArrayList<Treasure> treasures;
	private ArrayList<Monster> monsters;
	private Scanner in;
	Treasure treasure = null;
	Monster monster = null;

	//////////////////MAIN RUNNING THING
	public static void main(String args[]) throws IOException {
		Application app = new Application();
		app.runApp();
	}

	public Application() {
		monsters = new ArrayList<Monster>();
		treasures = new ArrayList<Treasure>();
		in = new Scanner(System.in);
	}

	public void runApp() throws IOException {
		
		String choice;
		System.out.println("*** HELLO - WELCOME TO MONSTER LAND ***o");
		do {
			printMenu();
					
			choice = in.next().toUpperCase();
			switch (choice) {
			
			case "M": //Create Monster
				monster = addMonster(monster);
				break;
			
			case "T": //Create Treasure
				treasure = addTreasure(treasure);
				break;
		
			case "G": //Give current Treasure to a monster
				monster = giveTreasure(treasure);			
				break;

			case "GT": //Find Treasure to give to current monster
				monster = giveSearchTreasure(monster);
				break;
				
			case "P": //Print Monster and Treasure Info
				printMonsterInfo("A");
				break;
				
			case "PM": //Print just the monsters
				printMonsterInfo("M");
				break;
				
			case "PT": //Print just the treasures
				printMonsterInfo("T");
				break;
				
			case "S": //Save to file
				saveToFile();
				break;
			
			case "L": //load from file
				loadFromFile();
				break;
				
			case "C":
				//m1.setFace();
				//m1.setHair();			
				break;
				
			case "FM": //Search for Monster and display info
				monster = searchMonster();
				break;
				
			case "FT": //Search for Treasure and display info
				treasure = searchTreasure();
				break;
				
			case "Q": //Exit program
				exitProgram();
				break;

			default:
				System.out.println("not a valid choice");
			}
		} while (!choice.equals("Q"));
	}

	public void printMenu() { //Print the menu options
		System.out.println("\n ===Main Menu=== "
						+ "\n m 	- monsters "
						+ "\n t 	- treasures "
						+ "\n g 	- Give curretly selected treasure to a Monster "
						+ "\n gt 	- Search for Treasure to give to the currently selected Monster "			
						+ "\n c 	- change monster's face and hair (Curretly Disabled) "
						+ "\n fm 	- Find Monster "
						+ "\n ft 	- Find Treasure "
						+ "\n p 	- print "
						+ "\n pm	- Print Monsters "
						+ "\n pt 	- Print Treasures "
						+ "\n s 	- Save to file "
						+ "\n l 	- Load from file "
						+ "\n q 	- Quit program " 
						+ "\n ===============");
		showSelected(monster, treasure);
		System.out.println("\n"); //print an empty line to keep a gap between the menu and the output
	}
	
	private void showSelected(Monster monster, Treasure treasure){
		if (monster != null){
				System.out.print(" Currently Selected Monster: " + monster.getType());
			} else {
				System.out.print(" Currently Selected Monster: None");
			}
		if (treasure != null){
				System.out.print(" 	Currently Selected Treasure: " + treasure.getName());
			} else {
				System.out.print(" 	Currently Selected Treasure: None");
			}
	}

	private Monster giveTreasure(Treasure treasure) {	
		Monster monster= null;
		if (treasure != null) {
			System.out.println("Which monster (name)? ");
			String toFind = in.next();
			monster = findMonster(toFind);
			if (monster != null){
				monster.addTreasure(treasure);
				System.out.println("DONE!");	
			} else {
				System.err.println("Couldn't find that monster: " + toFind);
			}
		} else {
			System.err.println("You haven't created any treasure");
		}
	return monster;
}
	
	private Monster giveSearchTreasure(Monster monster){
		Treasure treasure = null;
		if (monster!= null){
			System.out.println("What Treasure do you want to give (name?");
			String toFind = in.next();
			treasure = findTreasure(toFind);
			if (treasure != null){
				monster.addTreasure(treasure);
				System.out.println("DONE!");
				System.out.println("Gave " + toFind + " to " + monster.getType());
			} else {
				System.err.println("Couldn't find that treasure: " + toFind);
			}
		} else {
			System.err.println("You haven't created any monsters");
		}
		return monster;
	}

	private void exitProgram() {
		System.out.println("*** THANK YOU FOR USING MONSTER LAND ***");
	}

	private void loadFromFile() throws FileNotFoundException{
		treasures.clear(); //load treasures first
		Scanner infile = new Scanner(new FileReader(TREASURES_FILENAME)); //Clear out treasures so that we can load afresh
		int num = infile.nextInt();
		for (int i = 0; i < num; i++){
			infile.nextLine();
			String n = infile.nextLine();
			int v = infile.nextInt();
			Treasure t = new Treasure(n, v);
			treasures.add(t);
		}
		infile.close();
		
   		monsters.clear(); //now lets load the monsters
		infile = new Scanner(new FileReader(MONSTERS_FILENAME));
		infile.useDelimiter("\r?\n|\r"); //UNIX and Windows line endings
		num = infile.nextInt();
		for (int i = 0; i < num; i++) {
			String type = infile.next();
			String hair = infile.next();
			String face = infile.next();
			int powerPoints = infile.nextInt();
			String characteristics = infile.next();	
			Monster m = new Monster(type, hair, face, characteristics, powerPoints);
			monsters.add(m);
			
			int numTreasures = infile.nextInt();
			for (int nt = 0; nt < numTreasures; nt++) {
				String toFind = infile.next();
				Treasure foundTreasure = findTreasure(toFind);
				m.addTreasure(foundTreasure);
			}		
		}
		infile.close();
		System.out.println("Monster details have been loaded from file");
	}

	private void saveToFile() throws IOException {
		int num = treasures.size();
		PrintWriter pw = new PrintWriter(new FileWriter(TREASURES_FILENAME));
		pw.println(num);
		for (Treasure t : treasures){
			pw.println(t.getName());
			pw.println(t.getValue());
		}
		pw.close();
		
		num = monsters.size();
		pw = new PrintWriter(new FileWriter(MONSTERS_FILENAME));
		pw.println(num);
		for (Monster m : monsters) {
			pw.println(m.getType());
			pw.println(m.getHair());
			pw.println(m.getFace());
			pw.println(m.getPowerPoints());
			pw.println(m.getCharacteristics());
			Treasure[] loot = m.getLoot();
			pw.println(loot.length);
			for (Treasure mt : loot) {
				pw.println(mt.getName());
			}
		}
		pw.close();
		
		System.out.println("Monster details have been saved to file");
	}

	private void printMonsterInfo(String choice) {
		if (choice == "A"){
			System.out.println(this.toString());
		} else if(choice == "M"){
			System.out.println(monsters);
		} else if(choice == "T"){
			System.out.println(treasures);
		} else {
			System.err.println("Error - not a valid choice in Application.printMonsterInfo");
		}
	}

	private Monster addMonster(Monster monster) {
		System.out.println("enter info about monster 1");
		monster = new Monster();
		monster.readKeyboard();
		monsters.add(monster);
		return monster;
	}
	
	private Treasure addTreasure(Treasure treasure) {
		System.out.println("enter info about the new treasure");
		treasure = new Treasure();
		treasure.readKeyboard();
		treasures.add(treasure);
		return treasure;
	}

	public String toString() {
		return " All Treasures created: " + treasures
				+ "\n All Monsters and their Treasures: " + monsters;
	}
	
	private Treasure findTreasure(String toFind){
		Treasure foundTreasure = null;
		for (Treasure t : treasures) {
			if (t.getName().equals(toFind)){
				foundTreasure = t;
				break;
			}
		}
		return foundTreasure;
	}
	
	private Monster findMonster(String toFind){
		Monster foundMonster = null;
		for (Monster m : monsters) {
			if (m.getType().equals(toFind)){
				foundMonster = m;
				break;
			} else {
				System.out.print("Monster not found");
				return null;
			}
		}
		return foundMonster;
	}
	
	private Monster searchMonster(){
		String i;
		String answer;
		Monster result;
		System.out.print("Enter monster name to search: ");
		i = in.next();
		result = findMonster(i);
		System.out.println(result);
		System.out.println("Do you want to set " + result.getType() + " as you currently selected Monster? (y/n)");
		answer = in.next().toUpperCase();
		switch (answer) {
		case "Y":
			System.out.println(result.getType() + " has been set as your currently selected monster");
			monster = result;
			break;
			
		case "N":
			break;
			
		default:
			System.out.println("not a valid choice");
		}
		return monster;
	}
	
	private Treasure searchTreasure(){
		String i;
		String answer;
		Treasure result;
		System.out.print("Enter treasure name to search: ");
		i = in.next();
		result = findTreasure(i);
		System.out.println(result);
		System.out.println("Do you want to set " + result.getName() + " as you currently selected Treasure? (y/n)");
		answer = in.next().toUpperCase();
		switch (answer) {
		case "Y":
			System.out.println(result.getName() + " has been set as your currently selected monster");
			treasure = result;
			break;
			
		case "N":
			break;
			
		default:
			System.out.println("not a valid choice");
			}
		return treasure;
	}
}
