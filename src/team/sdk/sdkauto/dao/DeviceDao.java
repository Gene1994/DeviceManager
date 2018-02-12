package team.sdk.sdkauto.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import team.sdk.sdkauto.util.JdbcUtil;

/**
 * <p>
 * Title: DeviceDao<��p>
 * <p>
 * Description: <��p>
 * 
 * @author quzhe
 * 
 *         2018��2��12��
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
	public boolean status = true;// ���ʱĬ��true����ѯʱ�޸�
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
		return bool;
	}
}
