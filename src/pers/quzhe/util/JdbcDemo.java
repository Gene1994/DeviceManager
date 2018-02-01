package pers.quzhe.util;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.junit.Test;

import pers.quzhe.util.JdbcUtil;

/**
 * <p>
 * Title: JdbcTest<��p>
 * <p>
 * Description: <��p>
 * 
 * @author quzhe
 *
 *         2017��11��15��
 */
public class JdbcDemo {

	/**
	 * �����û���Ϣ
	 */
	@Test
	public void updateUser() {
		String name = "����";
		int age = 18;
		int score = 60;
		int id = 1;
		String sql = "update test_table set name=?,age=?,score=? where id=?";
		// ������������list
		List<Object> paramList = new ArrayList<Object>();
		// ������
		paramList.add(name);
		paramList.add(age);
		paramList.add(score);
		paramList.add(id);

		JdbcUtil jdbcUtil = null;
		boolean bool = false;
		try {
			jdbcUtil = new JdbcUtil();
			jdbcUtil.getConnection(); // ��ȡ���ݿ�����
			bool = jdbcUtil.updateByPreparedStatement(sql, paramList);
		} catch (SQLException e) {
			System.out.println(this.getClass() + "ִ�и��²����׳��쳣��");
			e.printStackTrace();
		} finally {
			if (jdbcUtil != null) {
				jdbcUtil.releaseConn(); // һ��Ҫ�ͷ���Դ
			}
		}
		System.out.println("ִ�и��µĽ����" + bool);
	}

	/**
	 * ����id��ѯ�û���Ϣ
	 */
	@Test
	public void findUserById() {
		int id = 1;
		String sql = "select * from test_table where id = ?";
		// ������������list
		List<Object> paramList = new ArrayList<Object>();
		// ������
		paramList.add(id);
		JdbcUtil jdbcUtil = null;
		try {
			jdbcUtil = new JdbcUtil();
			jdbcUtil.getConnection(); // ��ȡ���ݿ�����
			List<Map<String, Object>> mapList = jdbcUtil.findResult(sql.toString(), paramList);
			if (mapList.size() == 1) {
				Map<String, Object> map = mapList.get(0);
				String name = (String) map.get("name");
				int age = (int) map.get("age");
				int score = (int) map.get("score");
				System.out.println("����:" + name + ";����:" + age + ";�ɼ�:" + score);
			}
		} catch (SQLException e) {
			System.out.println(this.getClass() + "ִ�в�ѯ�����׳��쳣��");
			e.printStackTrace();
		} finally {
			if (jdbcUtil != null) {
				jdbcUtil.releaseConn(); // һ��Ҫ�ͷ���Դ
			}
		}

	}
}