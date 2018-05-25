
public class Test {

	public static void main(String[] args) {
		Person taro = new Person();
		taro.name="山田太郎";
		taro.age=20;

		Person jiro = new Person();
		jiro.name="木村次郎";
		jiro.age=18;

		Person hanako = new Person();
		hanako.name="鈴木花子";
		hanako.age=16;

		System.out.println(taro.name);
		System.out.println(taro.age);
		taro.talk();
		taro.walk();
		taro.run();
		System.out.print("\n");

		System.out.println(jiro.name);
		System.out.println(jiro.age);
		System.out.print("\n");

		System.out.println(hanako.name);
		System.out.println(hanako.age);
	}

}
