package team.sdk.sdkauto.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import team.sdk.sdkauto.devicemanager.DeviceSelect;
import team.sdk.sdkauto.util.JdbcUtil;

/**
 * <p>
 * Title: DeviceDao
 * <p>
 * Description:DeviceDao
 * 
 * @author quzhe
 * 
 */
public class DeviceDao {
	JdbcUtil jdbcUtil = null;
	// �豸����
	public String deviceId;
	public String type;
	public String model;
	public String ip;
	public int port = 8000;
	public String userName = "admin";
	public String password;
	public String language;
	public boolean status = true;
	public int errorCode = 0;
	public String comment;
	public String position;

	public DeviceDao() {
	}
	
	public DeviceDao(String deviceId){
		this.deviceId = deviceId;
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

	//增
	public boolean insert() {
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
			jdbcUtil.getConnection();
			bool = jdbcUtil.updateByPreparedStatement(sql, paramList);
		} catch (SQLException e) {
		} finally {
			if (jdbcUtil != null) {
				jdbcUtil.releaseConn();
			}
		}
		if (bool) {
			JOptionPane.showMessageDialog(null, "添加成功！");
		} else {
			JOptionPane.showMessageDialog(null, "添加失败！", "错误", JOptionPane.ERROR_MESSAGE);
		}
		return bool;
	}
	
	//删
	public void delete(){
		String sql = "delete from device where deviceid=?";

		List<Object> paramList = new ArrayList<Object>();

		paramList.add(deviceId);

		boolean bool = false;
		try {
			jdbcUtil = new JdbcUtil();
			jdbcUtil.getConnection();
			bool = jdbcUtil.updateByPreparedStatement(sql, paramList);
		} catch (SQLException exception) {
			System.out.println(this.getClass() + "执行数据库操作异常");
			exception.printStackTrace();
		} finally {
			if (jdbcUtil != null) {
				jdbcUtil.releaseConn();
			}
		}
		System.out.println("删除操作结果：" + bool);
		if (bool) {
			JOptionPane.showMessageDialog(null, "删除成功");
		} else {
			JOptionPane.showMessageDialog(null, "删除失败", "失败", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	//改
	public boolean update(){
		String sql = "update device set deviceid=?,type=?,model=?,ip=?,port=?,username=?,password=?,language=?,position=?,comment=? where deviceid=?";
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
		paramList.add(deviceId);
		
		boolean bool = false;
		try {
			jdbcUtil = new JdbcUtil();
			jdbcUtil.getConnection();
			bool = jdbcUtil.updateByPreparedStatement(sql, paramList);
		} catch (SQLException e1) {
			System.out.println(this.getClass() + "执行数据库操作异常");
			e1.printStackTrace();
		} finally {
			if (jdbcUtil != null) {
				jdbcUtil.releaseConn();
			}
		}
		System.out.println("修改操作结果：" + bool);
		if (bool) {
			JOptionPane.showMessageDialog(null, "修改成功");
		} else {
			JOptionPane.showMessageDialog(null, "修改失败", "失败", JOptionPane.ERROR_MESSAGE);
		}
		
		return bool;
	}
	
	//查
	public void select(){

	}
}
