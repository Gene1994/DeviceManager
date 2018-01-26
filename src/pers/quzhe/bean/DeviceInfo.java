package pers.quzhe.bean;

import java.util.Vector;

import com.sun.jna.NativeLong;

import pers.quzhe.hcnetsdk.HCNetSDK;

/**
 * <p>
 * Title: DeciveInfo<／p>
 * <p>
 * Description: <／p>
 * 
 * @author quzhe
 * 
 *         2018年1月26日
 */
public class DeviceInfo implements Runnable{
	boolean status;
	String deviceId;
	String type;
	String model;
	String ip;
	int port;
	String userName;
	String password;
	String language;
	String position;
	String comment;
	Vector hang = new Vector();
	
	NativeLong lUserID;// 用户句柄
	static HCNetSDK hCNetSDK = HCNetSDK.INSTANCE;
	HCNetSDK.NET_DVR_DEVICEINFO m_strDeviceInfo;// 设备信息
	
	public DeviceInfo(boolean status,
	String deviceId,
	String type,
	String model,
	String ip,
	int port,
	String userName,
	String password,
	String language,
	String position,
	String comment){
		this.status = status;
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
	}

	/**
	 * @return the status
	 */
	public boolean getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(boolean status) {
		this.status = status;
	}

	/**
	 * @return the deviceId
	 */
	public String getDeviceId() {
		return deviceId;
	}

	/**
	 * @param deviceId the deviceId to set
	 */
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the model
	 */
	public String getModel() {
		return model;
	}

	/**
	 * @param model the model to set
	 */
	public void setModel(String model) {
		this.model = model;
	}

	/**
	 * @return the ip
	 */
	public String getIp() {
		return ip;
	}

	/**
	 * @param ip the ip to set
	 */
	public void setIp(String ip) {
		this.ip = ip;
	}

	/**
	 * @return the port
	 */
	public int getPort() {
		return port;
	}

	/**
	 * @param port the port to set
	 */
	public void setPort(int port) {
		this.port = port;
	}

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the language
	 */
	public String getLanguage() {
		return language;
	}

	/**
	 * @param language the language to set
	 */
	public void setLanguage(String language) {
		this.language = language;
	}

	/**
	 * @return the position
	 */
	public String getPosition() {
		return position;
	}

	/**
	 * @param position the position to set
	 */
	public void setPosition(String position) {
		this.position = position;
	}

	/**
	 * @return the comment
	 */
	public String getComment() {
		return comment;
	}

	/**
	 * @param comment the comment to set
	 */
	public void setComment(String comment) {
		this.comment = comment;
	}
	
	

	/**
	 * @return the hang
	 */
	public Vector getHang() {
		return hang;
	}

	/**
	 * @param hang the hang to set
	 */
	public void setHang(Vector hang) {
		this.hang = hang;
	}

	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		// TODO Auto-generated method stub
		m_strDeviceInfo = new HCNetSDK.NET_DVR_DEVICEINFO();
		lUserID = hCNetSDK.NET_DVR_Login(ip, (short) port, userName, password, m_strDeviceInfo);
		status = (lUserID.intValue() == -1) ? false : true;
		
		if (getStatus()) {
			hang.add("#ONLINE#");
		} else {
			hang.add("#OFFLINE#");
		}
		hang.add(getDeviceId());
		hang.add(getType());
		hang.add(getModel());
		hang.add(getIp());
		hang.add(getPort());
		hang.add(getUserName());
		hang.add(getPassword());
		hang.add(getLanguage());
		hang.add(getPosition());
		hang.add(getComment());
	}
	
}
