
public class Test2 {

	public static void main(String[] args) {
		Robot aibo=new Robot();
		aibo.name="アイボ";

		Robot asimo=new Robot();
		asimo.name="アシモ";

		Robot pepper=new Robot();
		pepper.name="ペッパー";

		Robot doraemon=new Robot();
		doraemon.name="ドラえもん";

		aibo.talk();
		aibo.walk();
		aibo.run();
		System.out.print("\n");
		asimo.talk();
		asimo.walk();
		asimo.run();
		System.out.print("\n");
		pepper.talk();
		pepper.walk();
		pepper.run();
		System.out.print("\n");
		doraemon.talk();
		doraemon.walk();
		doraemon.run();

	}

}