import java.util.Scanner;
import java.io.PrintWriter;

public class Treasure {
	String name;
	int value;
	String firePower;
	
	public Treasure() {
		name = "no weapon";
	}

	public Treasure(String n, int v) {
		name = n;
		value = v;
		
	}

	public void readKeyboard() {
		Scanner in = new Scanner(System.in);
		System.out.print("Enter treasure Name: ");
		name = in.next();
		System.out.print("enter value: ");
		value = in.nextInt();
		
	}

	@Override
	public String toString() {
		return name + " worth " + value;
	}

	public String getName() {
		return name;
	}

	public int getValue() {
		return value;
	}
	
	public void save(PrintWriter pw) {
		pw.println(name);
		pw.println(value);
	}
	
	public void load(Scanner scan) {
		name = scan.nextLine();
		value = scan.nextInt();
		scan.nextLine();
	}
		
}
