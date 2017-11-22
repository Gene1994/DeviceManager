package DeviceManager;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.JOptionPane;

import JDBC.JdbcUtil;

/**
 * <p>
 * Title: Device<／p>
 * <p>
 * Description: <／p>
 * 
 * @author quzhe
 * 
 *         2017年11月16日
 */
public class Device {
	// 设备属性
	public String type;
	public String model;
	public String ip;
	public int port = 8000;
	public String userName = "admin";
	public String password;
	public String language;
	public boolean isOnline;
	public int errorCode = 0;
	// public int indexCode;
	public String comment;
	public String position;
	public String deviceId;

	/**
	 * 
	 */
	public Device() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param type
	 * @param model
	 * @param ip
	 * @param port
	 * @param userName
	 * @param password
	 * @param language
	 * @param isOnline
	 * @param errorCode
	 * @param indexCode
	 * @param comment
	 */
	public Device(String deviceId, String type, String model, String ip, int port, String userName, String password,
			String language, boolean isOnline, int errorCode, String position, String comment) {
		super();
		this.deviceId = deviceId;
		this.type = type;
		this.model = model;
		this.ip = ip;
		this.port = port;
		this.userName = userName;
		this.password = password;
		this.language = language;
		this.isOnline = isOnline;
		this.errorCode = errorCode;
		this.position = position;
		this.comment = comment;
	}

	// 添加设备
	public void insert() {
		// INSERT INTO 表名称 VALUES (值1, 值2,....)
		String sql = "INSERT INTO DEVICE VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(deviceId);
		paramList.add(type);
		paramList.add(model);
		paramList.add(ip);
		paramList.add(port);
		paramList.add(userName);
		paramList.add(password);
		paramList.add(language);
		String text_isOnline = isOnline == true ? "Y" : "N";
		paramList.add(text_isOnline);
		paramList.add(errorCode);
		paramList.add(position);
		paramList.add(comment);
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
		if (bool) {
			JOptionPane.showMessageDialog(null, "添加成功");
		} else {
			JOptionPane.showMessageDialog(null, "添加失败", "错误", JOptionPane.ERROR_MESSAGE);
		}
	}

	// 删除设备
	public void delete() {

	}

	// 修改设备
	public void update() {

	}

	// //查找设备
	// public Vector selectByIp(){
	// DeviceSelect ds = new DeviceSelect();
	//
	// return null;
	//
	// }
}
