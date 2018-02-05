package team.sdk.sdkauto.devicemanager;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;

import team.sdk.sdkauto.util.JdbcUtil;

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
	public String comment;
	public String position;
	public String deviceId;
	public boolean status;// true���ߣ�false������

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
			String language, String position, String comment, boolean status) {
		super();
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

	// ����豸
	public void insert() {
		// INSERT INTO ������ VALUES (ֵ1, ֵ2,....)
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
		JdbcUtil jdbcUtil = null;
		boolean bool = false;
		try {
			jdbcUtil = new JdbcUtil();
			jdbcUtil.getConnection(); // ��ȡ���ݿ�����
			bool = jdbcUtil.updateByPreparedStatement(sql, paramList);
		} catch (SQLException e) {
		} finally {
			if (jdbcUtil != null) {
				jdbcUtil.releaseConn(); // һ��Ҫ�ͷ���Դ
			}
		}
		if (bool) {
			JOptionPane.showMessageDialog(null, "��ӳɹ�");
		} else {
			JOptionPane.showMessageDialog(null, "���ʧ��", "����", JOptionPane.ERROR_MESSAGE);
		}
	}

	/**
	 * ���IP��ʽ
	 * 
	 * @param ip
	 * @return
	 */
	public boolean isMatches(String ip) {
		boolean flag = false;
		try {
			String regex = "^((\\d|[1-9]\\d|1\\d\\d|2[0-4]\\d|25[0-5])\\.){3}(\\d|[1-9]\\d|1\\d\\d|2[0-4]\\d|25[0-5]|[*])$";
			Pattern p = Pattern.compile(regex);
			Matcher m = p.matcher(ip);
			if (m.find()) {
				return true;
			} else {
			}
		} catch (Exception e) {
		}
		return flag;
	}
}
