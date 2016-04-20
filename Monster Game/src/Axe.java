import java.io.PrintWriter;
import java.util.Scanner;

public class Axe extends Treasure {
	private int length;
	
	public Axe() {
		name = "no weapon";
	}
	
	public Axe(String n, int v, int length) {
		super(n, v);
		this.length = length;
	}
	
	@Override
	public void readKeyboard() {
		super.readKeyboard();
		Scanner in = new Scanner(System.in);
		System.out.print("Enter axe length: ");
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
		return super.toString() + " is a axe with "   + " Length= " + length;
	}
	
	@Override
	public void save(PrintWriter pw) {
		pw.println("axe");
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
