package pers.quzhe.UI;

import java.awt.EventQueue;
import java.awt.Color;
import java.awt.Toolkit;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Map;
import java.util.Vector;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;

import pers.quzhe.bean.DeviceInfo;
import pers.quzhe.devicemanager.DeviceInsert;
import pers.quzhe.devicemanager.DeviceList;
import pers.quzhe.devicemanager.DeviceSelect;
import pers.quzhe.stresstest.StressXML;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.ImageIcon;

/**
 * @author quzhe
 *
 */
public class DeviceUI {

	private JFrame frmDevicemanager;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DeviceUI window = new DeviceUI();
					window.frmDevicemanager.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * 
	 * @throws Exception
	 */
	public DeviceUI() throws Exception {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() throws Exception {
		frmDevicemanager = new JFrame();
		frmDevicemanager.setResizable(false);
		frmDevicemanager
				.setIconImage(Toolkit.getDefaultToolkit().getImage(DeviceUI.class.getResource("/pers/quzhe/res/device_1.png")));
		frmDevicemanager.setTitle("SDKAuto");
		frmDevicemanager.setBounds(100, 100, 937, 371);
		frmDevicemanager.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmDevicemanager.getContentPane().setLayout(null);
		
		//添加设备
		JButton button_insert = new JButton("\u6DFB\u52A0\u8BBE\u5907");
		button_insert.setBounds(10, 10, 132, 23);
		frmDevicemanager.getContentPane().add(button_insert);
		button_insert.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				try {
					new DeviceInsert();
				} catch (Exception e) {
					// TODO Auto-generated catch block
				}
			}
		});
		
		//查找设备
		JButton button_select = new JButton("\u67E5\u627E\u8BBE\u5907");
		button_select.setBounds(152, 10, 132, 23);
		frmDevicemanager.getContentPane().add(button_select);
		button_select.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new DeviceSelect();
			}
		});
		
		//显示全部设备
		JButton btn_showAll = new JButton("显示全部设备");
		btn_showAll.setBounds(437, 10, 132, 23);
		frmDevicemanager.getContentPane().add(btn_showAll);
		btn_showAll.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				DeviceSelect.vectors = new Vector<Thread>();
				DeviceSelect.vectors_device = new Vector<DeviceInfo>();
				DeviceSelect.index = 5;
				DeviceList dl = new DeviceList("");
				for (int i = 0; i < dl.mapList.size(); i++) {
					Map<String, Object> map = dl.mapList.get(i);
					try {
						// 设备属性
						boolean status = false;
						String deviceId = (String) map.get("deviceid");
						String type = (String) map.get("type");
						String model = (String) map.get("model");
						String ip = (String) map.get("ip");
						int port = (int) map.get("port");
						String userName = (String) map.get("username");
						String password = (String) map.get("password");
						String language = (String) map.get("language");
						String position = (String) map.get("position");
						String comment = (String) map.get("comment");
						DeviceInfo deviceInfo = new DeviceInfo(status, deviceId, type, model, ip, port, userName,
								password, language, position, comment);
						DeviceSelect.vectors_device.add(deviceInfo);
						Thread t = new Thread(deviceInfo);
						DeviceSelect.vectors.add(t);
						t.start();
					} catch (Exception exception) {
					}
				}
				// 确保所有数据都读取后再回到主线程
				for (Thread thread : DeviceSelect.vectors) {
					try {
						thread.join();
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				dl.show();
			}
		});
		
		//生成稳定性XML
		JButton btn_xml = new JButton("\u751F\u6210\u7A33\u5B9A\u6027XML");
		btn_xml.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				new StressXML();
			}
		});
		btn_xml.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btn_xml.setBounds(295, 10, 132, 23);
		frmDevicemanager.getContentPane().add(btn_xml);
		
		//HIK logo
		JLabel label_pic = new JLabel(new ImageIcon(
				Toolkit.getDefaultToolkit().getImage(DeviceUI.class.getResource("/pers/quzhe/res/HIKlogo.jpg"))));
		label_pic.setBounds(10, 33, 912, 313);
		frmDevicemanager.getContentPane().add(label_pic);
		
		//待开发
		JButton button_more = new JButton("\u2026\u2026");
		button_more.setEnabled(false);
		button_more.setBounds(581, 10, 132, 23);
		frmDevicemanager.getContentPane().add(button_more);
	}
}
