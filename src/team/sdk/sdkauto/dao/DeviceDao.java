package team.sdk.sdkauto.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import team.sdk.sdkauto.util.JdbcUtil;

/**
 * <p>
 * Title: DeviceDao<／p>
 * <p>
 * Description: <／p>
 * 
 * @author quzhe
 * 
 *         2018年2月12日
 */
public class DeviceDao {
	JdbcUtil jdbcUtil = null;
	// 设备属性
	public String deviceId;
	public String type;
	public String model;
	public String ip;
	public int port = 8000;
	public String userName = "admin";
	public String password;
	public String language;
	public boolean status = true;// 添加时默认true，查询时修改
	public int errorCode = 0;
	public String comment;
	public String position;

	public DeviceDao() {
	}

	public DeviceDao(String deviceId, String type, String model, String ip, int port, String userName, String password,
			String language, String position, String comment, boolean status) {
		this.deviceId = deviceId;
		this.type = type;
		this.model = model;
		this.ip = ip;
		this.port = port;
		this.userName = userName;
		this.password = password;
		this.language = language;
		this.position = position;
		this.comment = comment;
		this.status = status;
	}

	public boolean insert() {
		// INSERT INTO 表名称 VALUES (值1, 值2,....)
		String sql = "INSERT INTO DEVICE VALUES (?,?,?,?,?,?,?,?,?,?)";
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(deviceId);
		paramList.add(type);
		paramList.add(model);
		paramList.add(ip);
		paramList.add(port);
		paramList.add(userName);
		paramList.add(password);
		paramList.add(language);
		paramList.add(position);
		paramList.add(comment);

		boolean bool = false;
		try {
			jdbcUtil = new JdbcUtil();
			jdbcUtil.getConnection(); // 获取数据库链接
			bool = jdbcUtil.updateByPreparedStatement(sql, paramList);
		} catch (SQLException e) {
		} finally {
			if (jdbcUtil != null) {
				jdbcUtil.releaseConn(); // 一定要释放资源
			}
		}
		if (bool) {
			JOptionPane.showMessageDialog(null, "添加成功");
		} else {
			JOptionPane.showMessageDialog(null, "添加失败", "错误", JOptionPane.ERROR_MESSAGE);
		}
		return bool;
	}
}
