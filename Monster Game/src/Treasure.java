import java.util.Scanner;

public class Treasure {
	private String name;
	private int value;
	//private String firePower;
	
	public Treasure() {
		name = "no weapon";
	}

	public Treasure(String n, int v) {
		name = n;
		value = v;
		
	}

	public void readKeyboard() {
		Scanner in = new Scanner(System.in);
		System.out.print("Enter treasure info: ");
		name = in.next();
		System.out.print("enter value: ");
		value = in.nextInt();
		
	}

	public String toString() {
		return name + " worth " + value;
	}

	public String getName() {
		return name;
	}

	public int getValue() {
		return value;
	}
		
}
