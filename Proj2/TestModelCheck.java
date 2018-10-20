public class TestModelCheck{
	public static void main(String[] args) {

		System.out.println("Problem 1");
		System.out.print("ModusPonens : ");
		ModusPonens MP = new ModusPonens();
		System.out.println("");

		System.out.println("Problem 2");
		System.out.print("WumpusWorld P12 : ");
		WumpusWorld WW = new WumpusWorld();
		System.out.println("");

		System.out.println("Problem 3");
		System.out.print("unicorn is mythical ? : ");
		HornClauses HC = new HornClauses("mythical");

		System.out.print("unicorn is magical ? : ");
		HC = new HornClauses("magical");

		System.out.print("unicorn is horned ? : ");
		HC = new HornClauses("horned");
		System.out.println("");

		System.out.println("Problem 4(a)");
		System.out.print("Amy : ");
		LiarsAndTruthA LTA = new LiarsAndTruthA("Amy");

		System.out.print("Bob : ");
		LTA = new LiarsAndTruthA("Bob");

		System.out.print("Cal : ");
		LTA = new LiarsAndTruthA("Cal");
		System.out.println("");

		System.out.println("Problem 4(b)");
		System.out.print("Amy : ");
		LiarsAndTruthB LTB = new LiarsAndTruthB("Amy");

		System.out.print("Bob : ");
		LTB = new LiarsAndTruthB("Bob");

		System.out.print("Cal : ");
		LTB = new LiarsAndTruthB("Cal");

	}
}