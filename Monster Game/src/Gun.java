import java.io.PrintWriter;
import java.util.Scanner;

public class Gun extends Treasure {
	private double calibre;
	private int numBullets;
	
	public Gun() {
		name = "no weapon";
	}
	
	public Gun(String n, int v, double c, int numB) {
		super(n, v);
		calibre = c;
		numBullets = numB;
	}
	
	@Override
	public void readKeyboard() {
		super.readKeyboard();
		Scanner in = new Scanner(System.in);
		System.out.print("Enter gun calibre (0.0): ");
		calibre = in.nextDouble();
		System.out.print("Enter number of bullets: ");
		numBullets = in.nextInt();
	}
	
	public int getNumBullets() {
		return numBullets;
	}
	
	public void setNumBullets(int numBullets) {
		this.numBullets = numBullets;
	}
	
	public double getCalibre() {
		return calibre;
	}
	
	@Override
	public String toString() {
		return super.toString() + " is a gun with "  + " calibre= " + calibre + ", numBullets= " + numBullets;
	}
	
	@Override
	public void save(PrintWriter pw) {
		pw.println("gun");
		super.save(pw);
		pw.println(calibre);
		pw.println(numBullets);
	}
	
	@Override
	public void load(Scanner scan) {
		super.load(scan);
		calibre = scan.nextDouble();
		scan.nextLine();
		numBullets = scan.nextInt();
		scan.nextLine();
	}

}
