
public class Test {
	public static void main(String[] args){
		TestUserDAO dao = new TestUserDAO();
		dao.select("taro","123");
		dao.selectAll();
		dao.selectByName("taro");
		dao.selectByPassword("123");
		dao.updateUserNameByUserName("saburo","taro");
		dao.delete("shiro");
	}

}
