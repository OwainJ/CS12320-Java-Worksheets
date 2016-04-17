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
 *Not sure how far I've got in Worksheet 12
 *But the Search Monster/Treasure function doesn't seem to work properly
 *neither does give treasure
 */

public class Application {
	private static final String TREASURES_FILENAME = "treasures.txt";
	private static final String MONSTERS_FILENAME = "monsters.txt";
	private ArrayList<Treasure> treasures;
	private ArrayList<Monster> monsters;
	private Scanner in;

	public Application() {
		monsters = new ArrayList<Monster>();
		treasures = new ArrayList<Treasure>();
		in = new Scanner(System.in);
	}

	public void runApp() throws IOException {
		Treasure treasure = null;
		Monster monster = null;
		String choice;
		System.out.println("*** HELLO - WELCOME TO MONSTER LAND ***o");
		do {
			printMenu();
			choice = in.next().toUpperCase();
			switch (choice) {
			case "M":
				monster = addMonster(monster);
				break;
			
			case "T":
				System.out.println("enter info about the new treasure");
				treasure = new Treasure();
				treasure.readKeyboard();
				treasures.add(treasure);
				break;
		
			case "G":
				monster = giveTreasure(treasure);			
				break;
		
			case "P":
				printMonsterInfo();
				break;
				
			case "S":
				saveToFile();
				break;
			
			case "L":
				loadFromFile();
				break;
				
			case "C":
				//m1.setFace();
				//m1.setHair();			
				break;
				
			case "FM":
				searchMonster();
				break;
				
			case "FT":
				searchTreasure();
				break;
				
			case "Q":
				exitProgram();
				break;

			default:
				System.out.println("not a valid choice");
			}
		} while (!choice.equals("Q"));
	}

	private Monster giveTreasure(Treasure treasure) {
		Monster monster= null;
		if(treasure != null) {
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

	private void printMonsterInfo() {
		System.out.println(this.toString());
	}

	private Monster addMonster(Monster monster) {
		System.out.println("enter info about monster 1");
		monster = new Monster();
		monster.readKeyboard();
		monsters.add(monster);
		return monster;
	}

	public void printMenu() {
		System.out
				.println("\n ===Main Menu=== \n m - monsters \n t - treasures \n g - give treasures to monsters \n p - print "
						+ "\n s - save monster \n c - change monster's face and hair \n fm - Find Monster \n ft - Find Treasure \n l - load \n q - quit \n===============");
	}

	public String toString() {
		return "All Treasures created: " + treasures
				+ " All Monsters and their Treasures: " + monsters;
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
			//} else {
			//	System.out.print("Monster not found");
			//	return null;
			}
		}
		return foundMonster;
	}
	
	private void searchMonster(){
		String i;
		Monster result;
		System.out.print("Enter monster name to search: ");
		i = in.next();
		result = findMonster(i);
		System.out.print(result);
	}
	
	private void searchTreasure(){
		String i;
		Treasure result;
		System.out.print("Enter treasure name to search: ");
		i = in.next();
		result = findTreasure(i);
		System.out.print(result);
	}
	
	

	// ////////////////
	public static void main(String args[]) throws IOException {
		Application app = new Application();
		app.runApp();
	}
}
