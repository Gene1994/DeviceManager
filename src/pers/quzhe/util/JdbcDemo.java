package pers.quzhe.util;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.junit.Test;

import pers.quzhe.util.JdbcUtil;

/**
 * <p>
 * Title: JdbcTest<／p>
 * <p>
 * Description: <／p>
 * 
 * @author quzhe
 *
 *         2017年11月15日
 */
public class JdbcDemo {

	/**
	 * 更新用户信息
	 */
	@Test
	public void updateUser() {
		String name = "张三";
		int age = 18;
		int score = 60;
		int id = 1;
		String sql = "update test_table set name=?,age=?,score=? where id=?";
		// 创建填充参数的list
		List<Object> paramList = new ArrayList<Object>();
		// 填充参数
		paramList.add(name);
		paramList.add(age);
		paramList.add(score);
		paramList.add(id);

		JdbcUtil jdbcUtil = null;
		boolean bool = false;
		try {
			jdbcUtil = new JdbcUtil();
			jdbcUtil.getConnection(); // 获取数据库链接
			bool = jdbcUtil.updateByPreparedStatement(sql, paramList);
		} catch (SQLException e) {
			System.out.println(this.getClass() + "执行更新操作抛出异常！");
			e.printStackTrace();
		} finally {
			if (jdbcUtil != null) {
				jdbcUtil.releaseConn(); // 一定要释放资源
			}
		}
		System.out.println("执行更新的结果：" + bool);
	}

	/**
	 * 根据id查询用户信息
	 */
	@Test
	public void findUserById() {
		int id = 1;
		String sql = "select * from test_table where id = ?";
		// 创建填充参数的list
		List<Object> paramList = new ArrayList<Object>();
		// 填充参数
		paramList.add(id);
		JdbcUtil jdbcUtil = null;
		try {
			jdbcUtil = new JdbcUtil();
			jdbcUtil.getConnection(); // 获取数据库链接
			List<Map<String, Object>> mapList = jdbcUtil.findResult(sql.toString(), paramList);
			if (mapList.size() == 1) {
				Map<String, Object> map = mapList.get(0);
				String name = (String) map.get("name");
				int age = (int) map.get("age");
				int score = (int) map.get("score");
				System.out.println("姓名:" + name + ";年龄:" + age + ";成绩:" + score);
			}
		} catch (SQLException e) {
			System.out.println(this.getClass() + "执行查询操作抛出异常！");
			e.printStackTrace();
		} finally {
			if (jdbcUtil != null) {
				jdbcUtil.releaseConn(); // 一定要释放资源
			}
		}

	}
}