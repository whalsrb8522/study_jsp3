package orm;

import java.io.IOException;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class DatabaseBuilder {
	private static SqlSessionFactory factory;
	private static final String config = "orm/MybatisConfig.xml";
	
	static {
		try {
			factory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream(config));
		}catch (IOException e) {
			System.out.println(">>> MyBatis 설정 오류");
			e.printStackTrace();
		}		
	}
	
	public static SqlSessionFactory getFactory() {
		return factory;
	}
}
