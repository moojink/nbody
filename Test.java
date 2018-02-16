public class Test {
	public static void main(String[] args) {
		Planet Samh = new Planet(1, 0, 0, 0, 10, "");
		Planet AEgir = new Planet(3, 3, 0, 0, 5, "");

		System.out.println("Force along x: " + Samh.calcForceExertedByX(AEgir));
		System.out.println("Force along y: " + Samh.calcForceExertedByY(AEgir));
	}
}