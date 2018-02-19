package team.sdk.sdkauto.UI;

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
import javax.swing.JOptionPane;

import com.sun.jna.examples.CLibrary;

import team.sdk.sdkauto.bean.Device;
import team.sdk.sdkauto.devicemanager.DeviceInsert;
import team.sdk.sdkauto.devicemanager.DeviceList;
import team.sdk.sdkauto.devicemanager.DeviceSelect;
import team.sdk.sdkauto.hcnetsdk.HCNetSDK;
import team.sdk.sdkauto.stresstest.StressXML;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.ImageIcon;

/**
 * @author quzhe
 *
 */
public class StartUI {

	private JFrame frmDevicemanager;
	
	static HCNetSDK hCNetSDK = HCNetSDK.INSTANCE;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StartUI window = new StartUI();
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
	public StartUI() throws Exception {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() throws Exception {
		frmDevicemanager = new JFrame();
		frmDevicemanager.getContentPane().setBackground(Color.LIGHT_GRAY);
		frmDevicemanager.getContentPane().setForeground(Color.WHITE);
		frmDevicemanager.setResizable(false);
		frmDevicemanager
				.setIconImage(Toolkit.getDefaultToolkit().getImage(StartUI.class.getResource("/resources/device_icon.png")));
		frmDevicemanager.setTitle("SDKAuto");
		frmDevicemanager.setBounds(100, 100, 916, 328);
		frmDevicemanager.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmDevicemanager.getContentPane().setLayout(null);
		//SDK�汾��Ϣ
		JLabel label_SdkVersion = new JLabel("");
		label_SdkVersion.setForeground(Color.WHITE);
		label_SdkVersion.setBounds(779, 279, 121, 15);
		frmDevicemanager.getContentPane().add(label_SdkVersion);
		
		boolean initSuc = hCNetSDK.NET_DVR_Init();
		if (initSuc != true) {
			JOptionPane.showMessageDialog(null, "��ʼ��ʧ��");
		}
		int buildVersion = hCNetSDK.NET_DVR_GetSDKBuildVersion();
		int v1 = (buildVersion >> 24) & 0xff;
		int v2 = (buildVersion >> 16) & 0xff;
		int v3 = (buildVersion >> 8) & 0xff;
		int v4 = buildVersion & 0xff;
		String text_version = "HCNetSDK  V" + v1 + "." + v2 + "." + v3 + "." + v4;
		label_SdkVersion.setText(text_version);
		
		//����豸
		JButton button_insert = new JButton("");
		button_insert.setIcon(new ImageIcon(StartUI.class.getResource("/resources/btn_insert.png")));
		button_insert.setBounds(10, 21, 120, 30);
		frmDevicemanager.getContentPane().add(button_insert);
		button_insert.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				try {
					new DeviceInsert();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		//�����豸
		JButton button_select = new JButton("");
		button_select.setIcon(new ImageIcon(StartUI.class.getResource("/resources/btn_select.png")));
		button_select.setBounds(10, 61, 120, 30);
		frmDevicemanager.getContentPane().add(button_select);
		button_select.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new DeviceSelect();
			}
		});
		
		//��ʾȫ���豸
		JButton btn_showAll = new JButton("");
		btn_showAll.setIcon(new ImageIcon(StartUI.class.getResource("/resources/btn_all.png")));
		btn_showAll.setBounds(10, 141, 120, 30);
		frmDevicemanager.getContentPane().add(btn_showAll);
		btn_showAll.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				DeviceSelect.vectors = new Vector<Thread>();
				DeviceSelect.vectors_device = new Vector<Device>();
				DeviceSelect.index = 5;
				DeviceList dl = new DeviceList("");
				for (int i = 0; i < dl.mapList.size(); i++) {
					Map<String, Object> map = dl.mapList.get(i);
					try {
						// �豸����
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
						Device deviceInfo = new Device(status, deviceId, type, model, ip, port, userName,
								password, language, position, comment);
						DeviceSelect.vectors_device.add(deviceInfo);
						Thread t = new Thread(deviceInfo);
						DeviceSelect.vectors.add(t);
						t.start();
					} catch (Exception exception) {
					}
				}
				// ȷ���������ݶ���ȡ���ٻص����߳�
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
		
		//�����ȶ���XML
		JButton btn_xml = new JButton("");
		btn_xml.setIcon(new ImageIcon(StartUI.class.getResource("/resources/btn_stress.png")));
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
		btn_xml.setBounds(10, 101, 120, 30);
		frmDevicemanager.getContentPane().add(btn_xml);
		
		//HIK logo
		JLabel label_pic = new JLabel(new ImageIcon(
				Toolkit.getDefaultToolkit().getImage(StartUI.class.getResource("/resources/HIKlogo.jpg"))));
		label_pic.setForeground(Color.WHITE);
		label_pic.setBounds(0, 0, 912, 300);
		frmDevicemanager.getContentPane().add(label_pic);
	}
}
