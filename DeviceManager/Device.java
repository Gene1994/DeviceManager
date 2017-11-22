package DeviceManager;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.JOptionPane;

import JDBC.JdbcUtil;

/**
 * <p>
 * Title: Device<��p>
 * <p>
 * Description: <��p>
 * 
 * @author quzhe
 * 
 *         2017��11��16��
 */
public class Device {
	// �豸����
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

	// ����豸
	public void insert() {
		// INSERT INTO ������ VALUES (ֵ1, ֵ2,....)
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
		if (bool) {
			JOptionPane.showMessageDialog(null, "��ӳɹ�");
		} else {
			JOptionPane.showMessageDialog(null, "���ʧ��", "����", JOptionPane.ERROR_MESSAGE);
		}
	}

	// ɾ���豸
	public void delete() {

	}

	// �޸��豸
	public void update() {

	}

	// //�����豸
	// public Vector selectByIp(){
	// DeviceSelect ds = new DeviceSelect();
	//
	// return null;
	//
	// }
}
