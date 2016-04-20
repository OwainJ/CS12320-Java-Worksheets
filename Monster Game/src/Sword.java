import java.io.PrintWriter;
import java.util.Scanner;

public class Sword extends Treasure {
	private int length;
	
	public Sword() {
		name = "no weapon";
	}
	
	public Sword(String n, int v, int length) {
		super(n, v);
		this.length = length;
	}
	
	@Override
	public void readKeyboard() {
		super.readKeyboard();
		Scanner in = new Scanner(System.in);
		System.out.print("Enter sword length: ");
		length= in.nextInt();
	}
	
	public int getLength() {
		return length;
	}
	
	public void setLength(int length) {
		this.length = length;
	}
	
	@Override
	public String toString() {
		return super.toString() + " is a sword with "  + " Length= " + length;
	}
	
	@Override
	public void save(PrintWriter pw) {
		pw.println("sword");
		super.save(pw);
		pw.println(length);
	}
	
	@Override
	public void load(Scanner scan) {
		super.load(scan);
		length = scan.nextInt();
		scan.nextLine();
	}

}