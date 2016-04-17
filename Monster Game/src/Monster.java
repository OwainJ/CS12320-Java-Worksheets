import java.util.Scanner;
import java.util.ArrayList;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * This is the class Monster - a 'blueprint' for all Monsters lots of get and
 * set methods and comments deleted for space saving on handout
 */
public class Monster {
	// *****************instance variables are the 'attributes' of a thing
	private String type;
	private String hair;
	private String face;
	private String characteristics;
	private int powerPoints;
	private ArrayList<Treasure> loot;

	// *****************methods are the 'action' the thing can do
	public Monster() {
		type = "unknown";
		loot = new ArrayList<Treasure>(); // always has a treasure - may be
											// nothing
	}

	public Monster(String startType, String startHair, String startFace,
			String startChara, int powerP) {
		type = startType;
		hair = startHair;
		face = startFace;
		characteristics = startChara;
		powerPoints = powerP;
		loot = new ArrayList<Treasure>();

	}



	public String toString() {
		if (loot != null)
			return "This scary monster is of type " + type + " with " + hair
					+ " hair and " + face + " face and " + powerPoints
					+ " points holding " + loot + " and is "
					+ characteristics;
		else
			return "This scary monster is of type " + type + " with " + hair
					+ " hair and " + face + " face and " + powerPoints
					+ " points" + " and is " + characteristics;
	}

	public void addTreasure(Treasure newstuff) {
		loot.add(newstuff);
	}

	public void readKeyboard() {
		Scanner in = new Scanner(System.in);
		System.out.print("enter type: ");
		type = in.next();
		System.out.print("enter hair: ");
		hair = in.next();
		System.out.print("enter face: ");
		face = in.next();
		System.out.print("Enter characteristics: ");
		characteristics = in.next();
		System.out.print("enter power: ");
		powerPoints = in.nextInt();
		

	}

	public void load(String fileName) throws FileNotFoundException {
		loot.clear();
		Scanner infile = new Scanner(new FileReader(fileName));
		infile.useDelimiter("\r?\n|\r");
		type = infile.next();
		hair = infile.next();
		face = infile.next();
		characteristics = infile.next();
		powerPoints = infile.nextInt();
		int num = infile.nextInt();
		for (int i = 0; i < num; i++) {
			String n = infile.next();
			int v = infile.nextInt();
			Treasure t = new Treasure(n, v);
			loot.add(t);
		}
		infile.close();

	}

	public void save(String fileName) throws IOException {
		PrintWriter outfile = new PrintWriter(new FileWriter(fileName));
		outfile.println(type);
		outfile.println(hair);
		outfile.println(face);
		outfile.println(characteristics);
		outfile.println(powerPoints);
		outfile.println(loot.size());
		for (Treasure treasure : loot) {
			outfile.println(treasure.getName());
			outfile.println(treasure.getValue());
		}
		outfile.close();
	}

	public String getType() {
		return type;
	}

	public String getHair() {
		return hair;
	}

	public void setHair(String hair) {
		Scanner in = new Scanner(System.in);
		System.out.print("enter new hair: ");
		hair = in.next();
		in.close();
	}

	public String getFace() {
		return face;
	}

	public void setFace(String face) {
		Scanner in = new Scanner(System.in);
		System.out.print("enter new face: ");
		face = in.next();
		in.close();
	}

	public String getCharacteristics() {
		return characteristics;
	}

	public void setCharacteristics(String characteristics) {
		this.characteristics = characteristics;
	}

	public int getPowerPoints() {
		return powerPoints;
	}

	public void setPowerPoints(int powerPoints) {
		this.powerPoints = powerPoints;
	}

	public Treasure[] getLoot() {
		Treasure[] lootCopy = new Treasure[loot.size()];
		loot.toArray(lootCopy);
		return lootCopy;
	}

	public void setLoot(ArrayList<Treasure> loot) {
		this.loot = loot;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	
}