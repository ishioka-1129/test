public class Hensuu {
	public static void main(String[] args){

		System.out.println(momo(5));
		System.out.println(momo(9));
		System.out.println(obo(12,12));
		System.out.println(zone("田中"));
		System.out.println(zone("佐藤"));
		int total=momo(2);
		System.out.println(total);
	}
		public static int momo(int moyasi){
			return moyasi*moyasi;
		}
		public static int obo(int num1,int num2){
			return num1*num2;
		}
		public static String zone(String name){
			return name + "さん";
		}
	}