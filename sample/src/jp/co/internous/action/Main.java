package jp.co.internous.action;

public class Main {

	public static void main(String[] args){
		System.out.println("Hello world"+"\n");
		System.out.println(tashizan(10,10));
		System.out.println(hikizan(10,10));
		System.out.println(kakezan(10,10));
		System.out.println(warizan(10,10));
		System.out.print("\n");
		System.out.println(name("田中"));
	}
	public static int tashizan(int number1,int number2){
		return number1+number2;
	}
	public static int hikizan(int number1,int number2){
		return number1-number2;
	}
	public static int kakezan(int number1,int number2){
		return number1*number2;
	}
	public static int warizan(int number1,int number2){
		return number1/number2;
	}
	public static String name(String name1){
		return name1+"さん";
	}

}