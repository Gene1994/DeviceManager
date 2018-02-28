package team.sdk.sdkauto.stresstest;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.CardLayout;
import java.awt.Toolkit;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JPanel;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

import team.sdk.sdkauto.bean.Device;
import team.sdk.sdkauto.bean.SceneBean;
import team.sdk.sdkauto.devicemanager.DeviceSelect;

/**
 * <p>
 * Title: StressXML<��p>
 * <p>
 * Description: <��p>
 * 
 * @author quzhe
 * 
 *         2017��12��22��
 */
public class StressXML extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JScrollPane scrollPane;
	private JTree tree;
	private JPanel panel_tree;
	private JPanel panel_scene;
	private JPanel panel_button;
	private CardLayout card = null;

	private JPanel panel_addDevice;
	private JPanel p_1;
	private JPanel p_2;
	private JPanel p_3;
	private JPanel p_4;
	private JPanel p_5;
	private JPanel p_6;
	private JPanel p_7;
	private JPanel p_8;
	private JPanel p_9;
	private JPanel p_10;
	private JPanel p_11;
	private JPanel p_12;
	private JPanel p_13;
	private JPanel p_14;

	private SceneBean b1;
	private SceneBean b2;
	private SceneBean b3;
	private SceneBean b4;
	private SceneBean b5;
	private SceneBean b6;
	private SceneBean b7;
	private SceneBean b8;
	private SceneBean b9;
	private SceneBean b10;
	private SceneBean b11;
	private SceneBean b12;
	private SceneBean b13;
	private SceneBean b14;

	private String ip;
	private int port;
	private String user;
	private String password;

	private JTextField tfIp;
	private JTextField tfPort;
	private JTextField tfUser;
	private JTextField tfPassword;

	private DefaultMutableTreeNode select;
	private DefaultMutableTreeNode node_device;
	
	private Device device;

	private JButton btn_next;
	private JButton btn_add;
	private JButton btn_init;
	private JButton btn_delete;

	/**
	 * Create the application.
	 */
	public StressXML() {
		tfIp = new JTextField();
		tfPort = new JTextField();
		tfUser = new JTextField();
		tfPassword = new JTextField();
		card = new CardLayout();

		this.setBounds(100, 100, 600, 330);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setIconImage(Toolkit.getDefaultToolkit().getImage(DeviceSelect.class.getResource("/resources/device_icon.png")));
		this.setTitle("生成稳定性XML");
		getContentPane().setLayout(null);
		this.setVisible(true);
		this.setResizable(false);

		panel_tree = new JPanel();
		panel_tree.setLayout(null);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 200, 310);

		tree = new JTree();
		tree.setBounds(0, 0, 200, 900);
		panel_tree.add(tree);

		DefaultMutableTreeNode root = new DefaultMutableTreeNode();
		DefaultMutableTreeNode preview = new DefaultMutableTreeNode();
		DefaultMutableTreeNode playback = new DefaultMutableTreeNode();
		DefaultMutableTreeNode download = new DefaultMutableTreeNode();
		DefaultMutableTreeNode login = new DefaultMutableTreeNode();
		DefaultMutableTreeNode findlog = new DefaultMutableTreeNode();
		DefaultMutableTreeNode findfile = new DefaultMutableTreeNode();
		DefaultMutableTreeNode alarm = new DefaultMutableTreeNode();
		DefaultMutableTreeNode listen = new DefaultMutableTreeNode();
		DefaultMutableTreeNode paramconfig = new DefaultMutableTreeNode();
		DefaultMutableTreeNode serial = new DefaultMutableTreeNode();
		DefaultMutableTreeNode passivedecode = new DefaultMutableTreeNode();
		DefaultMutableTreeNode startvoicecom = new DefaultMutableTreeNode();
		DefaultMutableTreeNode clientaudiostart = new DefaultMutableTreeNode();
		DefaultMutableTreeNode startvoicecommr = new DefaultMutableTreeNode();

		root.setUserObject("场景");
		preview.setUserObject("预览preview");
		playback.setUserObject("回放playback");
		download.setUserObject("下载download");
		login.setUserObject("注册login");
		findlog.setUserObject("日志搜索findlog");
		findfile.setUserObject("文件搜索findfile");
		alarm.setUserObject("布防alarm");
		listen.setUserObject("监听listen");
		paramconfig.setUserObject("参数配置paramconfig");
		serial.setUserObject("透明通道serial");
		passivedecode.setUserObject("被动解码passivedecode");
		startvoicecom.setUserObject("语音对讲startvoicecom");
		clientaudiostart.setUserObject("语音广播clientaudiostart");
		startvoicecommr.setUserObject("语音转发startvoicecommr");

		root.add(preview);
		root.add(playback);
		root.add(download);
		root.add(login);
		root.add(findlog);
		root.add(findfile);
		root.add(alarm);
		root.add(listen);
		root.add(paramconfig);
		root.add(serial);
		root.add(passivedecode);
		root.add(startvoicecom);
		root.add(clientaudiostart);
		root.add(startvoicecommr);

		DefaultTreeModel dtm = new DefaultTreeModel(root);
		tree.setModel(dtm);
		scrollPane.setViewportView(tree);
		this.add(scrollPane);

		p_1 = new JPanel();
		p_2 = new JPanel();
		p_3 = new JPanel();
		p_4 = new JPanel();
		p_5 = new JPanel();
		p_6 = new JPanel();
		p_7 = new JPanel();
		p_8 = new JPanel();
		p_9 = new JPanel();
		p_10 = new JPanel();
		p_11 = new JPanel();
		p_12 = new JPanel();
		p_13 = new JPanel();
		p_14 = new JPanel();
		panel_addDevice = new JPanel();
		JPanel p_blank = new JPanel();
		p_blank.setLayout(null);
		JLabel lb = new JLabel();
		lb.setText("请选择场景");
		lb.setBounds(150, 130, 70, 20);
		p_blank.add(lb);

		panel_button = new JPanel();
		panel_button.setBounds(200, 250, 400, 50);
		this.add(panel_button);

		btn_next = new JButton();
		btn_next.setText("下一步");
		panel_button.add(btn_next);
		btn_next.setVisible(false);

		btn_add = new JButton();
		btn_add.setText("添加");
		panel_button.add(btn_add);
		btn_add.setVisible(false);

		btn_delete = new JButton();
		btn_delete.setText("删除");
		panel_button.add(btn_delete);
		btn_delete.setVisible(false);
		
		btn_init = new JButton();
		btn_init.setText("生成XML");
		panel_button.add(btn_init);
		btn_init.setVisible(true);

		panel_scene = new JPanel();
		panel_scene.setBounds(200, 0, 400, 250);
		this.add(panel_scene);
		panel_scene.setLayout(card);
		panel_scene.add(p_1, "preview");
		panel_scene.add(p_2, "playback");
		panel_scene.add(p_3, "download");
		panel_scene.add(p_4, "login");
		panel_scene.add(p_5, "findlog");
		panel_scene.add(p_6, "findfile");
		panel_scene.add(p_7, "alarm");
		panel_scene.add(p_8, "listen");
		panel_scene.add(p_9, "paramconfig");
		panel_scene.add(p_10, "serial");
		panel_scene.add(p_11, "passivedecode");
		panel_scene.add(p_12, "startvoicecom");
		panel_scene.add(p_13, "clientaudiostart");
		panel_scene.add(p_14, "startvoicecommr");
		panel_scene.add(panel_addDevice, "addDevice");
		panel_scene.add(p_blank, "p_blank");
		card.show(panel_scene, "p_blank");

		p_1.setLayout(null);
		p_2.setLayout(null);
		p_3.setLayout(null);
		p_4.setLayout(null);
		p_5.setLayout(null);
		p_6.setLayout(null);
		p_7.setLayout(null);
		p_8.setLayout(null);
		p_9.setLayout(null);
		p_10.setLayout(null);
		p_11.setLayout(null);
		p_12.setLayout(null);
		p_13.setLayout(null);
		p_14.setLayout(null);
		panel_addDevice.setLayout(null);

		b1 = new SceneBean();
		b2 = new SceneBean();
		b3 = new SceneBean();
		b4 = new SceneBean();
		b5 = new SceneBean();
		b6 = new SceneBean();
		b7 = new SceneBean();
		b8 = new SceneBean();
		b9 = new SceneBean();
		b10 = new SceneBean();
		b11 = new SceneBean();
		b12 = new SceneBean();
		b13 = new SceneBean();
		b14 = new SceneBean();

		List<Device> deviceList1 = new ArrayList<Device>();
		List<Device> deviceList2 = new ArrayList<Device>();
		List<Device> deviceList3 = new ArrayList<Device>();
		List<Device> deviceList4 = new ArrayList<Device>();
		List<Device> deviceList5 = new ArrayList<Device>();
		List<Device> deviceList6 = new ArrayList<Device>();
		List<Device> deviceList7 = new ArrayList<Device>();
		List<Device> deviceList8 = new ArrayList<Device>();
		List<Device> deviceList9 = new ArrayList<Device>();
		List<Device> deviceList10 = new ArrayList<Device>();
		List<Device> deviceList11 = new ArrayList<Device>();
		List<Device> deviceList12 = new ArrayList<Device>();
		List<Device> deviceList13 = new ArrayList<Device>();
		List<Device> deviceList14 = new ArrayList<Device>();
		Map<SceneBean, List<Device>> deviceListList = new HashMap<SceneBean, List<Device>>();

		List<SceneBean> sceneList = new ArrayList<SceneBean>();

		// 设置cardPanel
		setScene1();
		setScene2();
		setScene3(p_3, b3,
				new String[] { "0-NET_DVR_GetFileByName", "1-NET_DVR_GetFileByTime", "2-NET_DVR_GetFileByTime_V40" });
		setScene4(p_4, b4, new String[] { "0-NET_DVR_Login", "1-NET_DVR_Login_V30", "2-NET_DVR_Login_V40 " });
		setScene3(p_5, b5, new String[] { "0-NET_DVR_FindDVRLog", "1-NET_DVR_FindDVRLog_V30" });
		setScene3(p_6, b6, new String[] { "0-NET_DVR_FindFile", "1-NET_DVR_FindFileByCard", "2-NET_DVR_FindFile_V30",
				"3-NET_DVR_FindFile_V40" });
		setScene4(p_7, b7, new String[] { "0-NET_DVR_SetupAlarmChan", "1-NET_DVR_SetupAlarmChan_V30" });
		setScene5();
		setScene3(p_9, b9, new String[] { "0-NET_DVR_GET/SET_TIMECFG", "1-NET_DVR_GET/SET_NETCFG_V30",
				"2-NET_DVR_GET/SET_DEVICECFG", "3-NET_DVR_GET/SET_DEVICECFG_V40" });
		setScene3(p_10, b10, new String[] { "0-NET_DVR_SerialStart_V40" });// serial
		setScene3(p_11, b11, new String[] { "0-NET_DVR_MatrixStartPassiveDecode" });// passivedecode
		setScene6();// startvoicecom
		setScene7(p_13, b13, new String[] { "0-NET_DVR_ClientAudioStart_V30" });// clientaudiostart
		b13.setSleeptime(0);
		setScene7(p_14, b14, new String[] { "0-NET_DVR_StartVoiceCom_MR_V30" });// startvoicecommr
		// 设置添加设备页面
		setSceneAddDevice();

		// JPanel panel_show = panel_scene;
		tree.addTreeSelectionListener(new TreeSelectionListener() {
			public void valueChanged(TreeSelectionEvent e) {
				select = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
				btn_next.setVisible(true);
				btn_add.setVisible(false);
				if (select.toString().equals("预览preview")) {
					card.show(panel_scene, "preview");
					btn_delete.setVisible(false);
				} else if (select.toString().equals("回放playback")) {
					card.show(panel_scene, "playback");
					btn_delete.setVisible(false);
				} else if (select.toString().equals("下载download")) {
					card.show(panel_scene, "download");
					btn_delete.setVisible(false);
				} else if (select.toString().equals("注册login")) {
					card.show(panel_scene, "login");
					btn_delete.setVisible(false);
				} else if (select.toString().equals("日志搜索findlog")) {
					card.show(panel_scene, "findlog");
					btn_delete.setVisible(false);
				} else if (select.toString().equals("文件搜索findfile")) {
					card.show(panel_scene, "findfile");
					btn_delete.setVisible(false);
				} else if (select.toString().equals("布防alarm")) {
					card.show(panel_scene, "alarm");
					btn_delete.setVisible(false);
				} else if (select.toString().equals("监听listen")) {
					card.show(panel_scene, "listen");
					btn_delete.setVisible(false);
				} else if (select.toString().equals("监听listen")) {
					card.show(panel_scene, "listen");
					btn_delete.setVisible(false);
				} else if (select.toString().equals("参数配置paramconfig")) {
					card.show(panel_scene, "paramconfig");
					btn_delete.setVisible(false);
				} else if (select.toString().equals("透明通道serial")) {
					card.show(panel_scene, "serial");
					btn_delete.setVisible(false);
				} else if (select.toString().equals("被动解码passivedecode")) {
					card.show(panel_scene, "passivedecode");
					btn_delete.setVisible(false);
				} else if (select.toString().equals("语音对讲startvoicecom")) {
					card.show(panel_scene, "startvoicecom");
					btn_delete.setVisible(false);
				} else if (select.toString().equals("语音广播clientaudiostart")) {
					card.show(panel_scene, "clientaudiostart");
					btn_delete.setVisible(false);
				} else if (select.toString().equals("语音转发startvoicecommr")) {
					card.show(panel_scene, "startvoicecommr");
					btn_delete.setVisible(false);
				} else {
					card.show(panel_scene, "p_15");
					btn_next.setVisible(false);
					btn_delete.setVisible(true);
				}
				scrollPane.setViewportView(tree);
			}
		});

		btn_next.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				btn_next.setVisible(false);
				btn_add.setVisible(true);
				// setSceneAddDevice();
				card.show(panel_scene, "addDevice");
			}
		});

		btn_add.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (isMatches(ip)) {
					device = new Device();
					device.setIp(ip);
					device.setPort(port);
					device.setUserName(user);
					device.setPassword(password);
					node_device = new DefaultMutableTreeNode(device.getIp());
					if (select.toString().equals("预览preview")) {
						deviceList1.add(device);
						deviceListList.put(b1, deviceList1);
						if (!sceneList.contains(b1)) {
							sceneList.add(b1);
						}
						preview.add(node_device);
					} else if (select.toString().equals("回放playback")) {
						deviceList2.add(device);
						deviceListList.put(b2, deviceList2);
						if (!sceneList.contains(b2)) {
							sceneList.add(b2);
						}
						playback.add(node_device);
					} else if (select.toString().equals("下载download")) {
						deviceList3.add(device);
						deviceListList.put(b3, deviceList3);
						if (!sceneList.contains(b3)) {
							sceneList.add(b3);
						}
						download.add(node_device);
					} else if (select.toString().equals("注册login")) {
						deviceList4.add(device);
						deviceListList.put(b4, deviceList4);
						if (!sceneList.contains(b4)) {
							sceneList.add(b4);
						}
						login.add(node_device);
					} else if (select.toString().equals("日志搜索findlog")) {
						deviceList5.add(device);
						deviceListList.put(b5, deviceList5);
						if (!sceneList.contains(b5)) {
							sceneList.add(b5);
						}
						findlog.add(node_device);
					} else if (select.toString().equals("文件搜索findfile")) {
						deviceList6.add(device);
						deviceListList.put(b6, deviceList6);
						if (!sceneList.contains(b6)) {
							sceneList.add(b6);
						}
						findfile.add(node_device);
					} else if (select.toString().equals("布防alarm")) {
						deviceList7.add(device);
						deviceListList.put(b7, deviceList7);
						if (!sceneList.contains(b7)) {
							sceneList.add(b7);
						}
						alarm.add(node_device);
					} else if (select.toString().equals("监听listen")) {
						deviceList8.add(device);
						deviceListList.put(b8, deviceList8);
						if (!sceneList.contains(b8)) {
							sceneList.add(b8);
						}
						listen.add(node_device);
					} else if (select.toString().equals("参数配置paramconfig")) {
						deviceList9.add(device);
						deviceListList.put(b9, deviceList9);
						if (!sceneList.contains(b9)) {
							sceneList.add(b9);
						}
						paramconfig.add(node_device);
					} else if (select.toString().equals("透明通道serial")) {
						deviceList10.add(device);
						deviceListList.put(b10, deviceList10);
						if (!sceneList.contains(b10)) {
							sceneList.add(b10);
						}
						serial.add(node_device);
					} else if (select.toString().equals("被动解码passivedecode")) {
						deviceList11.add(device);
						deviceListList.put(b11, deviceList11);
						if (!sceneList.contains(b11)) {
							sceneList.add(b11);
						}
						passivedecode.add(node_device);
					} else if (select.toString().equals("语音对讲startvoicecom")) {
						deviceList12.add(device);
						deviceListList.put(b12, deviceList12);
						if (!sceneList.contains(b12)) {
							sceneList.add(b12);
						}
						startvoicecom.add(node_device);
					} else if (select.toString().equals("语音广播clientaudiostart")) {
						deviceList13.add(device);
						deviceListList.put(b13, deviceList13);
						if (!sceneList.contains(b13)) {
							sceneList.add(b13);
						}
						clientaudiostart.add(node_device);
					} else if (select.toString().equals("语音转发startvoicecommr")) {
						deviceList14.add(device);
						deviceListList.put(b14, deviceList14);
						if (!sceneList.contains(b14)) {
							sceneList.add(b14);
						}
						startvoicecommr.add(node_device);
					}
					scrollPane.setViewportView(tree);
					tfIp.setText("");
					tfUser.setText("");
					tfPassword.setText("");
					ip = null;
					port = 8000;
					user = null;
					password = null;
				}else{
					JOptionPane.showMessageDialog(null, "请输入正确的IP地址", "Error!",JOptionPane.ERROR_MESSAGE); 
				}
				
			}
		});

		btn_init.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					XMLWriter writer = null;// 声明写XML的对象
//					SAXReader reader = new SAXReader();

					String xml_Url = "..\\SDKAuto\\Generate Files\\hikstresstestXml\\hiksdkstresstest_"
							+ System.currentTimeMillis() + ".xml";
					File xmlFile = new File(xml_Url);

					OutputFormat format = OutputFormat.createPrettyPrint();
					format.setEncoding("UTF-8");// 设置XML文件的编码格式

					Document _document = DocumentHelper.createDocument();
					Element _root = _document.addElement("hiksdkstresstest");
					for (SceneBean scene : sceneList) {
						if (scene == b1) {
							Element _preview = _root.addElement("preview");
							_preview.addAttribute("api", b1.getApi() + "");
							_preview.addAttribute("linktype", b1.getLinktype() + "");
							_preview.addAttribute("linkmode", b1.getLinkmode() + "");
							_preview.addAttribute("streamonly", b1.getStreamonly() + "");
							_preview.addAttribute("savedata", b1.getSavedata() + "");
							_preview.addAttribute("threadcount", b1.getThreadcount() + "");
							_preview.addAttribute("loopinterval", b1.getLoopinterval() + "");
							_preview.addAttribute("enable", "1");
							for (Device device : deviceListList.get(b1)) {
								Element _device = _preview.addElement("device");
								_device.addAttribute("ip", device.getIp());
								_device.addAttribute("port", device.getPort()+"");
								_device.addAttribute("user", device.getUserName());
								_device.addAttribute("password", device.getPassword());
							}
						} else if (scene == b2) {
							Element _playback = _root.addElement("playback");
							_playback.addAttribute("api", b2.getApi() + "");
							_playback.addAttribute("streamonly", b2.getStreamonly() + "");
							_playback.addAttribute("savedata", b2.getSavedata() + "");
							_playback.addAttribute("threadcount", b2.getThreadcount() + "");
							_playback.addAttribute("loopinterval", b2.getLoopinterval() + "");
							_playback.addAttribute("enable", "1");
							for (Device device : deviceListList.get(b2)) {
								Element _device = _playback.addElement("device");
								_device.addAttribute("ip", device.getIp());
								_device.addAttribute("port", device.getPort()+"");
								_device.addAttribute("user", device.getUserName());
								_device.addAttribute("password", device.getPassword());
							}
						} else if (scene == b3) {
							Element _download = _root.addElement("download");
							_download.addAttribute("api", b3.getApi() + "");
							_download.addAttribute("threadcount", b3.getThreadcount() + "");
							_download.addAttribute("loopinterval", b3.getLoopinterval() + "");
							_download.addAttribute("enable", "1");
							for (Device device : deviceListList.get(b3)) {
								Element _device = _download.addElement("device");
								_device.addAttribute("ip", device.getIp());
								_device.addAttribute("port", device.getPort()+"");
								_device.addAttribute("user", device.getUserName());
								_device.addAttribute("password", device.getPassword());
							}
						} else if (scene == b4) {
							Element _login = _root.addElement("login");
							_login.addAttribute("api", b4.getApi() + "");
							_login.addAttribute("taskcount", b4.getTaskcount() + "");
							_login.addAttribute("threadcount", b4.getThreadcount() + "");
							_login.addAttribute("loopinterval", b4.getLoopinterval() + "");
							_login.addAttribute("enable", "1");
							for (Device device : deviceListList.get(b4)) {
								Element _device = _login.addElement("device");
								_device.addAttribute("ip", device.getIp());
								_device.addAttribute("port", device.getPort()+"");
								_device.addAttribute("user", device.getUserName());
								_device.addAttribute("password", device.getPassword());
							}
						} else if (scene == b5) {
							Element _findlog = _root.addElement("findlog");
							_findlog.addAttribute("api", b5.getApi() + "");
							_findlog.addAttribute("threadcount", b5.getThreadcount() + "");
							_findlog.addAttribute("loopinterval", b5.getLoopinterval() + "");
							_findlog.addAttribute("enable", "1");
							for (Device device : deviceListList.get(b5)) {
								Element _device = _findlog.addElement("device");
								_device.addAttribute("ip", device.getIp());
								_device.addAttribute("port", device.getPort()+"");
								_device.addAttribute("user", device.getUserName());
								_device.addAttribute("password", device.getPassword());
							}
						} else if (scene == b6) {
							Element _findfile = _root.addElement("findfile");
							_findfile.addAttribute("api", b6.getApi() + "");
							_findfile.addAttribute("threadcount", b6.getThreadcount() + "");
							_findfile.addAttribute("loopinterval", b6.getLoopinterval() + "");
							_findfile.addAttribute("enable", "1");
							for (Device device : deviceListList.get(b6)) {
								Element _device = _findfile.addElement("device");
								_device.addAttribute("ip", device.getIp());
								_device.addAttribute("port", device.getPort()+"");
								_device.addAttribute("user", device.getUserName());
								_device.addAttribute("password", device.getPassword());
							}
						} else if (scene == b7) {
							Element _alarm = _root.addElement("alarm");
							_alarm.addAttribute("api", b7.getApi() + "");
							_alarm.addAttribute("taskcount", b7.getTaskcount() + "");
							_alarm.addAttribute("threadcount", b7.getThreadcount() + "");
							_alarm.addAttribute("loopinterval", b7.getLoopinterval() + "");
							_alarm.addAttribute("enable", "1");
							for (Device device : deviceListList.get(b7)) {
								Element _device = _alarm.addElement("device");
								_device.addAttribute("ip", device.getIp());
								_device.addAttribute("port", device.getPort()+"");
								_device.addAttribute("user", device.getUserName());
								_device.addAttribute("password", device.getPassword());
							}
						} else if (scene == b8) {
							Element _listen = _root.addElement("listen");
							_listen.addAttribute("api", b8.getApi() + "");
							_listen.addAttribute("listenPort", b8.getListenPort() + "");
							_listen.addAttribute("listenPort2", b8.getListenPort2() + "");
							_listen.addAttribute("threadcount", b8.getThreadcount() + "");
							_listen.addAttribute("loopinterval", b8.getLoopinterval() + "");
							_listen.addAttribute("enable", "1");
							for (Device device : deviceListList.get(b8)) {
								Element _device = _listen.addElement("device");
								_device.addAttribute("ip", device.getIp());
								_device.addAttribute("port", device.getPort()+"");
								_device.addAttribute("user", device.getUserName());
								_device.addAttribute("password", device.getPassword());
							}
						} else if (scene == b9) {
							Element _paramconfig = _root.addElement("paramconfig");
							_paramconfig.addAttribute("api", b9.getApi() + "");
							_paramconfig.addAttribute("threadcount", b9.getThreadcount() + "");
							_paramconfig.addAttribute("loopinterval", b9.getLoopinterval() + "");
							_paramconfig.addAttribute("enable", "1");
							for (Device device : deviceListList.get(b9)) {
								Element _device = _paramconfig.addElement("device");
								_device.addAttribute("ip", device.getIp());
								_device.addAttribute("port", device.getPort()+"");
								_device.addAttribute("user", device.getUserName());
								_device.addAttribute("password", device.getPassword());
							}
						} else if (scene == b10) {
							Element _serial = _root.addElement("serial");
							_serial.addAttribute("api", b10.getApi() + "");
							_serial.addAttribute("threadcount", b10.getThreadcount() + "");
							_serial.addAttribute("loopinterval", b10.getLoopinterval() + "");
							_serial.addAttribute("enable", "1");
							for (Device Device : deviceListList.get(b10)) {
								Element _device = _serial.addElement("device");
								_device.addAttribute("ip", device.getIp());
								_device.addAttribute("port", device.getPort()+"");
								_device.addAttribute("user", device.getUserName());
								_device.addAttribute("password", device.getPassword());
							}
						} else if (scene == b11) {
							Element _passivedecode = _root.addElement("passivedecode");
							_passivedecode.addAttribute("api", b11.getApi() + "");
							_passivedecode.addAttribute("threadcount", b11.getThreadcount() + "");
							_passivedecode.addAttribute("loopinterval", b11.getLoopinterval() + "");
							_passivedecode.addAttribute("enable", "1");
							for (Device device : deviceListList.get(b11)) {
								Element _device = _passivedecode.addElement("device");
								_device.addAttribute("ip", device.getIp());
								_device.addAttribute("port", device.getPort()+"");
								_device.addAttribute("user", device.getUserName());
								_device.addAttribute("password", device.getPassword());
							}
						} else if (scene == b12) {
							Element _startvoicecom = _root.addElement("startvoicecom");
							_startvoicecom.addAttribute("api", b12.getApi() + "");
							_startvoicecom.addAttribute("threadcount", b12.getThreadcount() + "");
							_startvoicecom.addAttribute("dwVoiceChan", b12.getDwVoiceChan() + "");
							_startvoicecom.addAttribute("bNeedCBNoEncData", b12.getbNeedCBNoEncData() + "");
							_startvoicecom.addAttribute("cbVoiceDataCallBack", b12.getCbVoiceDataCallBack() + "");
							_startvoicecom.addAttribute("loopinterval", b12.getLoopinterval() + "");
							_startvoicecom.addAttribute("enable", "1");
							for (Device device : deviceListList.get(b12)) {
								Element _device = _startvoicecom.addElement("device");
								_device.addAttribute("ip", device.getIp());
								_device.addAttribute("port", device.getPort()+"");
								_device.addAttribute("user", device.getUserName());
								_device.addAttribute("password", device.getPassword());
							}
						} else if (scene == b13) {
							Element _clientaudiostart = _root.addElement("clientaudiostart");
							_clientaudiostart.addAttribute("api", b13.getApi() + "");
							_clientaudiostart.addAttribute("threadcount", b13.getThreadcount() + "");
							_clientaudiostart.addAttribute("dwVoiceChan", b13.getDwVoiceChan() + "");
							_clientaudiostart.addAttribute("cbVoiceDataCallBack", b13.getCbVoiceDataCallBack() + "");
							_clientaudiostart.addAttribute("sleeptime", "0");
							_clientaudiostart.addAttribute("loopinterval", b13.getLoopinterval() + "");
							_clientaudiostart.addAttribute("enable", "1");
							for (Device device : deviceListList.get(b13)) {
								Element _device = _clientaudiostart.addElement("device");
								_device.addAttribute("ip", device.getIp());
								_device.addAttribute("port", device.getPort()+"");
								_device.addAttribute("user", device.getUserName());
								_device.addAttribute("password", device.getPassword());
							}
						} else if (scene == b14) {
							Element _startvoicecommr = _root.addElement("startvoicecommr");
							_startvoicecommr.addAttribute("api", b14.getApi() + "");
							_startvoicecommr.addAttribute("threadcount", b14.getThreadcount() + "");
							_startvoicecommr.addAttribute("dwVoiceChan", b14.getDwVoiceChan() + "");
							_startvoicecommr.addAttribute("cbVoiceDataCallBack", b14.getCbVoiceDataCallBack() + "");
							_startvoicecommr.addAttribute("loopinterval", b14.getLoopinterval() + "");
							_startvoicecommr.addAttribute("enable", "1");
							for (Device device : deviceListList.get(b14)) {
								Element _device = _startvoicecommr.addElement("device");
								_device.addAttribute("ip", device.getIp());
								_device.addAttribute("port", device.getPort()+"");
								_device.addAttribute("user", device.getUserName());
								_device.addAttribute("password", device.getPassword());
							}
						}
					}

					writer = new XMLWriter(new FileWriter(xmlFile), format);
					writer.write(_document);
					writer.close();
					JOptionPane.showMessageDialog(null, "生成成功！");  
				} catch (Exception e1) {
				}
			}
		});

	}

	private void setSceneAddDevice() {
		JLabel lblIp = new JLabel("IP\uFF1A");
		lblIp.setBounds(30, 30, 54, 15);
		panel_addDevice.add(lblIp);

		tfIp.setBounds(90, 27, 130, 21);
		tfIp.setColumns(10);
		tfIp.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				ip = tfIp.getText();
			}
		});
		panel_addDevice.add(tfIp);

		JLabel lbPort = new JLabel("\u7AEF\u53E3\uFF1A");
		lbPort.setBounds(30, 70, 54, 15);
		panel_addDevice.add(lbPort);

		tfPort.setText("8000");
		port = 8000;
		tfPort.setColumns(10);
		tfPort.setBounds(90, 67, 130, 21);
		tfPort.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				try {
					port = Integer.parseInt(tfPort.getText());
				}catch(NumberFormatException exception) {}
			}
		});
		panel_addDevice.add(tfPort);

		JLabel lbUser = new JLabel("\u7528\u6237\u540D\uFF1A");
		lbUser.setBounds(30, 110, 54, 15);
		panel_addDevice.add(lbUser);

		tfUser.setColumns(10);
		tfUser.setBounds(90, 107, 130, 21);
		tfUser.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				user = tfUser.getText();
			}
		});
		panel_addDevice.add(tfUser);

		JLabel lbPassword = new JLabel("\u5BC6\u7801\uFF1A");
		lbPassword.setBounds(30, 150, 54, 15);
		panel_addDevice.add(lbPassword);

		tfPassword.setColumns(10);
		tfPassword.setBounds(90, 147, 130, 21);
		tfPassword.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				password = tfPassword.getText();
			}
		});
		panel_addDevice.add(tfPassword);
	}

	private void setScene1() {
		JLabel lblApi = new JLabel("API:");
		lblApi.setBounds(10, 10, 70, 15);
		p_1.add(lblApi);

		b1.setApi(0);
		JComboBox cbApi = new JComboBox();
		cbApi.setModel(new DefaultComboBoxModel(
				new String[] { "0-NET_DVR_RealPlay", "1-NET_DVR_RealPlay_V30", "2-NET_DVR_RealPlay_V40" }));
		cbApi.setBounds(90, 7, 230, 21);
		cbApi.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				int i = cbApi.getSelectedIndex();
				b1.setApi(i);
			}
		});
		p_1.add(cbApi);

		JLabel lbLinktype = new JLabel("linktype:");
		lbLinktype.setBounds(10, 30, 70, 15);
		p_1.add(lbLinktype);

		b1.setLinktype(0);
		JComboBox cbLinktype = new JComboBox();
		cbLinktype.setModel(new DefaultComboBoxModel(new String[] { "0-main", "1-sub", "2-stream3", "3-stream4" }));
		cbLinktype.setBounds(90, 27, 230, 21);
		cbLinktype.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				int i = cbLinktype.getSelectedIndex();
				b1.setLinktype(i);
			}
		});
		p_1.add(cbLinktype);

		JLabel lbLinkmode = new JLabel("linkmode:");
		lbLinkmode.setBounds(10, 50, 70, 15);
		p_1.add(lbLinkmode);

		b1.setLinkmode(0);
		JComboBox cbLinkmode = new JComboBox();
		cbLinkmode.setModel(new DefaultComboBoxModel(
				new String[] { "0-tcp", "1-udp", "2-mcast", "3-rtp", "4-rtp/rtsp", "5-rtsp/http" }));
		cbLinkmode.setBounds(90, 47, 230, 21);
		cbLinkmode.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				int i = cbLinktype.getSelectedIndex();
				b1.setLinkmode(i);
			}
		});
		p_1.add(cbLinkmode);

		JLabel lbProtoType = new JLabel("*protoType:");
		lbProtoType.setBounds(10, 70, 70, 15);
		p_1.add(lbProtoType);

		b1.setProtoType(0);
		JComboBox cbProtoType = new JComboBox();
		cbProtoType.setModel(new DefaultComboBoxModel(new String[] { "0-private", "1-RTSP" }));
		cbProtoType.setBounds(90, 67, 230, 21);
		cbProtoType.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				int i = cbProtoType.getSelectedIndex();
				b1.setProtoType(i);
			}
		});
		p_1.add(cbProtoType);

		JLabel lbStreamonly = new JLabel("streamonly:");
		lbStreamonly.setBounds(10, 90, 70, 15);
		p_1.add(lbStreamonly);

		b1.setStreamonly(0);
		JComboBox cbStreamonly = new JComboBox();
		cbStreamonly.setModel(new DefaultComboBoxModel(new String[] { "0-no", "1-yes" }));
		cbStreamonly.setBounds(90, 87, 230, 21);
		cbStreamonly.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				int i = cbStreamonly.getSelectedIndex();
				b1.setStreamonly(i);
			}
		});
		p_1.add(cbStreamonly);

		JLabel lbSavedata = new JLabel("savedata:");
		lbSavedata.setBounds(10, 110, 70, 15);
		p_1.add(lbSavedata);

		b1.setSavedata(0);
		JComboBox cbSavedata = new JComboBox();
		cbSavedata.setModel(new DefaultComboBoxModel(new String[] { "0-no", "1-yes" }));
		cbSavedata.setBounds(90, 107, 230, 21);
		cbSavedata.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				int i = cbSavedata.getSelectedIndex();
				b1.setSavedata(i);
			}
		});
		p_1.add(cbSavedata);

		JLabel lbThreadcount = new JLabel("threadcount:");
		lbThreadcount.setBounds(10, 130, 72, 15);
		p_1.add(lbThreadcount);

		b1.setThreadcount(1);
		JTextField tfThreadcount = new JTextField("[1,2048]", 10);
		tfThreadcount.setBounds(90, 127, 100, 21);
		p_1.add(tfThreadcount);
		tfThreadcount.setColumns(10);
		tfThreadcount.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				try {
					int i = Integer.parseInt(tfThreadcount.getText());
					if (i < 1 || i > 2048) {
						JOptionPane.showMessageDialog(null, "请输入[1,2048]的整数！", "Error!", JOptionPane.ERROR_MESSAGE);
						tfThreadcount.setText("1");
					}
					b1.setThreadcount(i);
				} catch (Exception exception) {
				}
			}
		});
		tfThreadcount.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (tfThreadcount.getText().equals("[1,2048]")) {
					tfThreadcount.setText("");
				}
			}
		});

		JLabel lbLoopinterval = new JLabel("loopinterval:");
		lbLoopinterval.setBounds(10, 150, 72, 15);
		p_1.add(lbLoopinterval);

		b1.setLoopinterval(15);
		JTextField tfLoopinterval = new JTextField("请输入>=15整数", 10);
		tfLoopinterval.setBounds(90, 147, 100, 21);
		p_1.add(tfLoopinterval);
		// tfLoopinterval.setColumns(10);
		tfLoopinterval.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				try {
					int i = Integer.parseInt(tfLoopinterval.getText());
					b1.setLoopinterval(i);
				} catch (Exception exception) {
				}
			}
		});
		tfLoopinterval.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (tfLoopinterval.getText().equals("请输入>=15整数")) {
					tfLoopinterval.setText("");
				}
			}
		});

		JLabel lbMention = new JLabel("protoType:设备同时支持私有协议和RTSP协议时，");
		lbMention.setBounds(10, 180, 300, 15);
		p_1.add(lbMention);

		JLabel lbMention1 = new JLabel("该参数才有效，默认使用私有协议，可选RTSP协议");
		lbMention1.setBounds(10, 195, 300, 15);
		p_1.add(lbMention1);
	}

	private void setScene2() {
		JLabel lblApi = new JLabel("API:");
		lblApi.setBounds(10, 10, 70, 15);
		p_2.add(lblApi);

		b2.setApi(0);
		JComboBox cbApi = new JComboBox();
		cbApi.setModel(new DefaultComboBoxModel(
				new String[] { "0-NET_DVR_PlayBackByName", "1-NET_DVR_PlayBackByTime", "2-NET_DVR_PlayBackByTime_V40",
						"3-NET_DVR_PlayBackReverseByName", "4-NET_DVR_PlayBackReverseByTime_V40" }));
		cbApi.setBounds(90, 7, 230, 21);
		cbApi.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				int i = cbApi.getSelectedIndex();
				b2.setApi(i);
			}
		});
		p_2.add(cbApi);

		JLabel lbStreamonly = new JLabel("streamonly:");
		lbStreamonly.setBounds(10, 30, 70, 15);
		p_2.add(lbStreamonly);

		b2.setStreamonly(0);
		JComboBox cbStreamonly = new JComboBox();
		cbStreamonly.setModel(new DefaultComboBoxModel(new String[] { "0-no", "1-yes" }));
		cbStreamonly.setBounds(90, 27, 230, 21);
		cbStreamonly.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				int i = cbStreamonly.getSelectedIndex();
				b2.setStreamonly(i);
			}
		});
		p_2.add(cbStreamonly);

		JLabel lbSavedata = new JLabel("savedata:");
		lbSavedata.setBounds(10, 50, 70, 15);
		p_2.add(lbSavedata);

		b2.setSavedata(0);
		JComboBox cbSavedata = new JComboBox();
		cbSavedata.setModel(new DefaultComboBoxModel(new String[] { "0-no", "1-yes" }));
		cbSavedata.setBounds(90, 47, 230, 21);
		cbSavedata.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				int i = cbSavedata.getSelectedIndex();
				b1.setSavedata(i);
			}
		});
		p_2.add(cbSavedata);

		JLabel lbThreadcount = new JLabel("threadcount:");
		lbThreadcount.setBounds(10, 70, 72, 15);
		p_2.add(lbThreadcount);

		b2.setThreadcount(1);
		JTextField tfThreadcount = new JTextField("[1,2048]", 10);
		tfThreadcount.setBounds(90, 67, 100, 21);
		p_2.add(tfThreadcount);
		tfThreadcount.setColumns(10);
		tfThreadcount.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				try {
					int i = Integer.parseInt(tfThreadcount.getText());
					if (i < 1 || i > 2048) {
						JOptionPane.showMessageDialog(null, "请输入[1,2048]的整数！", "Error!", JOptionPane.ERROR_MESSAGE);
						tfThreadcount.setText("1");
					}
					b2.setThreadcount(i);
				} catch (Exception exception) {
				}
			}
		});
		tfThreadcount.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (tfThreadcount.getText().equals("[1,2048]")) {
					tfThreadcount.setText("");
				}
			}
		});

		JLabel lbLoopinterval = new JLabel("loopinterval:");
		lbLoopinterval.setBounds(10, 90, 72, 15);
		p_2.add(lbLoopinterval);

		b2.setLoopinterval(15);
		JTextField tfLoopinterval = new JTextField("请输入>=15整数", 10);
		tfLoopinterval.setBounds(90, 87, 100, 21);
		p_2.add(tfLoopinterval);
		// tfLoopinterval.setColumns(10);
		tfLoopinterval.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				try {
					int i = Integer.parseInt(tfLoopinterval.getText());
					b2.setLoopinterval(i);
				} catch (Exception exception) {
				}
			}
		});
		tfLoopinterval.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (tfLoopinterval.getText().equals("请输入>=15整数")) {
					tfLoopinterval.setText("");
				}
			}
		});
	}

	private void setScene3(JPanel p, SceneBean b, String[] s) {
		JLabel lblApi = new JLabel("API:");
		lblApi.setBounds(10, 10, 70, 15);
		p.add(lblApi);

		b.setApi(0);
		JComboBox cbApi = new JComboBox();
		cbApi.setModel(new DefaultComboBoxModel(s));
		cbApi.setBounds(90, 7, 230, 21);
		cbApi.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				int i = cbApi.getSelectedIndex();
				b.setApi(i);
			}
		});
		p.add(cbApi);

		JLabel lbThreadcount = new JLabel("threadcount:");
		lbThreadcount.setBounds(10, 30, 72, 15);
		p.add(lbThreadcount);

		b.setThreadcount(1);
		JTextField tfThreadcount = new JTextField("[1,2048]", 10);
		tfThreadcount.setBounds(90, 27, 100, 21);
		p.add(tfThreadcount);
		tfThreadcount.setColumns(10);
		tfThreadcount.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				try {
					int i = Integer.parseInt(tfThreadcount.getText());
					if (i < 1 || i > 2048) {
						JOptionPane.showMessageDialog(null, "请输入[1,2048]的整数！", "Error!", JOptionPane.ERROR_MESSAGE);
						tfThreadcount.setText("1");
					}
					b.setThreadcount(i);
				} catch (Exception exception) {
				}
			}
		});
		tfThreadcount.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (tfThreadcount.getText().equals("[1,2048]")) {
					tfThreadcount.setText("");
				}
			}
		});

		JLabel lbLoopinterval = new JLabel("loopinterval:");
		lbLoopinterval.setBounds(10, 50, 72, 15);
		p.add(lbLoopinterval);

		b.setLoopinterval(15);
		JTextField tfLoopinterval = new JTextField("请输入>=15整数", 10);
		tfLoopinterval.setBounds(90, 47, 100, 21);
		p.add(tfLoopinterval);
		// tfLoopinterval.setColumns(10);
		tfLoopinterval.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				try {
					int i = Integer.parseInt(tfLoopinterval.getText());
					b.setLoopinterval(i);
				} catch (Exception exception) {
				}
			}
		});
		tfLoopinterval.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (tfLoopinterval.getText().equals("请输入>=15整数")) {
					tfLoopinterval.setText("");
				}
			}
		});
	}

	private void setScene4(JPanel p, SceneBean b, String[] s) {
		JLabel lblApi = new JLabel("API:");
		lblApi.setBounds(10, 10, 70, 15);
		p.add(lblApi);

		b.setApi(0);
		JComboBox cbApi = new JComboBox();
		cbApi.setModel(new DefaultComboBoxModel(s));
		cbApi.setBounds(90, 7, 230, 21);
		cbApi.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				int i = cbApi.getSelectedIndex();
				b.setApi(i);
			}
		});
		p.add(cbApi);

		JLabel lbTaskcount = new JLabel("taskcount:");
		lbTaskcount.setBounds(10, 30, 72, 15);
		p.add(lbTaskcount);

		b.setTaskcount(1);
		JTextField tfTaskcount = new JTextField("[1,2048]", 10);
		tfTaskcount.setBounds(90, 27, 100, 21);
		p.add(tfTaskcount);
		tfTaskcount.setColumns(10);
		tfTaskcount.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				try {
					int i = Integer.parseInt(tfTaskcount.getText());
					if (i < 1 || i > 2048) {
						JOptionPane.showMessageDialog(null, "请输入[1,2048]的整数！", "Error!", JOptionPane.ERROR_MESSAGE);
						tfTaskcount.setText("1");
					}
					b.setTaskcount(i);
				} catch (Exception exception) {
				}
			}
		});
		tfTaskcount.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (tfTaskcount.getText().equals("[1,2048]")) {
					tfTaskcount.setText("");
				}
			}
		});

		JLabel lbThreadcount = new JLabel("threadcount:");
		lbThreadcount.setBounds(10, 50, 72, 15);
		p.add(lbThreadcount);

		b.setThreadcount(1);
		JTextField tfThreadcount = new JTextField("taskcount * threadcount <= 2048", 10);
		tfThreadcount.setBounds(90, 47, 200, 21);
		p.add(tfThreadcount);
		tfThreadcount.setColumns(10);
		tfThreadcount.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				try {
					int i = Integer.parseInt(tfThreadcount.getText());
					if (i < 1 || i > 2048) {
						JOptionPane.showMessageDialog(null, "请输入[1,2048]的整数！", "Error!", JOptionPane.ERROR_MESSAGE);
						tfThreadcount.setText("1");
					}
					b.setThreadcount(i);
				} catch (Exception exception) {
				}
			}
		});
		tfThreadcount.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (tfThreadcount.getText().equals("taskcount * threadcount <= 2048")) {
					tfThreadcount.setText("");
				}
			}
		});

		JLabel lbLoopinterval = new JLabel("loopinterval:");
		lbLoopinterval.setBounds(10, 70, 72, 15);
		p.add(lbLoopinterval);

		b.setLoopinterval(15);
		JTextField tfLoopinterval = new JTextField("请输入>=15整数", 10);
		tfLoopinterval.setBounds(90, 67, 100, 21);
		p.add(tfLoopinterval);
		// tfLoopinterval.setColumns(10);
		tfLoopinterval.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				try {
					int i = Integer.parseInt(tfLoopinterval.getText());
					b.setLoopinterval(i);
				} catch (Exception exception) {
				}
			}
		});
		tfLoopinterval.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (tfLoopinterval.getText().equals("请输入>=15整数")) {
					tfLoopinterval.setText("");
				}
			}
		});
	}

	private void setScene5() {
		JLabel lblApi = new JLabel("API:");
		lblApi.setBounds(10, 10, 70, 15);
		p_8.add(lblApi);

		b8.setApi(0);
		JComboBox cbApi = new JComboBox();
		cbApi.setModel(new DefaultComboBoxModel(new String[] { "0-NET_DVR_StartListen", "1-NET_DVR_StartListen_V30" }));
		cbApi.setBounds(90, 7, 230, 21);
		cbApi.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				int i = cbApi.getSelectedIndex();
				b8.setApi(i);
			}
		});
		p_8.add(cbApi);

		JLabel lbListenPort = new JLabel("listenPort:");
		lbListenPort.setBounds(10, 30, 72, 15);
		p_8.add(lbListenPort);

		b8.setListenPort(1);
		JTextField tfListenPort = new JTextField();
		tfListenPort.setBounds(90, 27, 100, 21);
		p_8.add(tfListenPort);
		tfListenPort.setColumns(10);
		tfListenPort.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				try {
					int i = Integer.parseInt(tfListenPort.getText());
					b8.setListenPort(i);
				} catch (Exception exception) {
				}
			}
		});

		JLabel lbListenPort2 = new JLabel("listenPort2:");
		lbListenPort2.setBounds(10, 50, 72, 15);
		p_8.add(lbListenPort2);

		b8.setListenPort2(1);
		JTextField tfListenPort2 = new JTextField();
		tfListenPort2.setBounds(90, 47, 100, 21);
		p_8.add(tfListenPort2);
		tfListenPort2.setColumns(10);
		tfListenPort2.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				try {
					int i = Integer.parseInt(tfListenPort2.getText());
					b8.setListenPort2(i);
				} catch (Exception exception) {
				}
			}
		});

		JLabel lbThreadcount = new JLabel("threadcount:");
		lbThreadcount.setBounds(10, 70, 72, 15);
		p_8.add(lbThreadcount);

		b8.setThreadcount(1);
		JTextField tfThreadcount = new JTextField("1", 10);
		tfThreadcount.setBounds(90, 67, 100, 21);
		p_8.add(tfThreadcount);
		tfThreadcount.setColumns(10);
		tfThreadcount.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				try {
					int i = Integer.parseInt(tfThreadcount.getText());
					if (i != 1) {
						JOptionPane.showMessageDialog(null, "写死1，代码需要", "Error!", JOptionPane.ERROR_MESSAGE);
						tfThreadcount.setText("1");
					}
					b8.setThreadcount(i);
				} catch (Exception exception) {
				}
			}
		});

		JLabel lbLoopinterval = new JLabel("loopinterval:");
		lbLoopinterval.setBounds(10, 90, 72, 15);
		p_8.add(lbLoopinterval);

		b8.setLoopinterval(0);
		JTextField tfLoopinterval = new JTextField("0", 10);
		tfLoopinterval.setBounds(90, 87, 100, 21);
		p_8.add(tfLoopinterval);
		// tfLoopinterval.setColumns(10);
		tfLoopinterval.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				try {
					int i = Integer.parseInt(tfLoopinterval.getText());
					if (i != 0) {
						JOptionPane.showMessageDialog(null, "监听不需要轮巡，写死0", "Error!", JOptionPane.ERROR_MESSAGE);
						tfLoopinterval.setText("0");
					}
					b8.setLoopinterval(i);
				} catch (Exception exception) {
				}
			}
		});
	}

	private void setScene6() {
		JLabel lblApi = new JLabel("API:");
		lblApi.setBounds(10, 10, 130, 15);
		p_12.add(lblApi);

		b12.setApi(0);
		JComboBox cbApi = new JComboBox();
		cbApi.setModel(new DefaultComboBoxModel(
				new String[] { "0-API_NET_DVR_StartVoiceCom_V30", "1-API_NET_DVR_StartVoiceCom" }));
		cbApi.setBounds(141, 7, 230, 21);
		cbApi.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				int i = cbApi.getSelectedIndex();
				b12.setApi(i);
			}
		});
		p_12.add(cbApi);

		JLabel lbThreadcount = new JLabel("threadcount:");
		lbThreadcount.setBounds(10, 30, 130, 15);
		p_12.add(lbThreadcount);

		b12.setThreadcount(1);
		JTextField tfThreadcount = new JTextField("[1,512]", 10);
		tfThreadcount.setBounds(141, 27, 100, 21);
		p_12.add(tfThreadcount);
		tfThreadcount.setColumns(10);
		tfThreadcount.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				try {
					int i = Integer.parseInt(tfThreadcount.getText());
					if (i < 1 || i > 512) {
						JOptionPane.showMessageDialog(null, "请输入[1,512]的整数！", "Error!", JOptionPane.ERROR_MESSAGE);
						tfThreadcount.setText("1");
					}
					b12.setThreadcount(i);
				} catch (Exception exception) {
				}
			}
		});
		tfThreadcount.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (tfThreadcount.getText().equals("[1,512]")) {
					tfThreadcount.setText("");
				}
			}
		});

		b12.setDwVoiceChan(1);

		JLabel lbBNeedCBNoEncData = new JLabel("bNeedCBNoEncData:");
		lbBNeedCBNoEncData.setBounds(10, 50, 130, 15);
		p_12.add(lbBNeedCBNoEncData);

		b12.setbNeedCBNoEncData(0);
		JComboBox cbBNeedCBNoEncData = new JComboBox();
		cbBNeedCBNoEncData.setModel(new DefaultComboBoxModel(new String[] { "0-编码后的语音数据", "1-编码前的PCM原始数据" }));
		cbBNeedCBNoEncData.setBounds(141, 47, 230, 21);
		cbBNeedCBNoEncData.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				int i = cbBNeedCBNoEncData.getSelectedIndex();
				b12.setbNeedCBNoEncData(i);
			}
		});
		p_12.add(cbBNeedCBNoEncData);

		JLabel lbCbVoiceDataCallBack = new JLabel("cbVoiceDataCallBack:");
		lbCbVoiceDataCallBack.setBounds(10, 70, 130, 15);
		p_12.add(lbCbVoiceDataCallBack);

		b12.setCbVoiceDataCallBack(0);
		JComboBox cbCbVoiceDataCallBack = new JComboBox();
		cbCbVoiceDataCallBack.setModel(new DefaultComboBoxModel(new String[] { "0-NULL", "1-cbVoiceDataCallBack" }));
		cbCbVoiceDataCallBack.setBounds(141, 67, 230, 21);
		cbCbVoiceDataCallBack.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				int i = cbCbVoiceDataCallBack.getSelectedIndex();
				b12.setCbVoiceDataCallBack(i);
			}
		});
		p_12.add(cbCbVoiceDataCallBack);

		JLabel lbLoopinterval = new JLabel("loopinterval:");
		lbLoopinterval.setBounds(10, 90, 130, 15);
		p_12.add(lbLoopinterval);

		b12.setLoopinterval(15);
		JTextField tfLoopinterval = new JTextField("请输入>=15整数", 10);
		tfLoopinterval.setBounds(141, 87, 100, 21);
		p_12.add(tfLoopinterval);
		// tfLoopinterval.setColumns(10);
		tfLoopinterval.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				try {
					int i = Integer.parseInt(tfLoopinterval.getText());
					b12.setLoopinterval(i);
				} catch (Exception exception) {
				}
			}
		});
		tfLoopinterval.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (tfLoopinterval.getText().equals("请输入>=15整数")) {
					tfLoopinterval.setText("");
				}
			}
		});
	}

	private void setScene7(JPanel p, SceneBean b, String[] s) {
		JLabel lblApi = new JLabel("API:");
		lblApi.setBounds(10, 10, 130, 15);
		p.add(lblApi);

		b.setApi(0);
		JComboBox cbApi = new JComboBox();
		cbApi.setModel(new DefaultComboBoxModel(s));
		cbApi.setBounds(141, 7, 230, 21);
		cbApi.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				int i = cbApi.getSelectedIndex();
				b.setApi(i);
			}
		});
		p.add(cbApi);

		JLabel lbThreadcount = new JLabel("threadcount:");
		lbThreadcount.setBounds(10, 30, 130, 15);
		p.add(lbThreadcount);

		b.setThreadcount(1);
		JTextField tfThreadcount = new JTextField("[1,512]", 10);
		tfThreadcount.setBounds(141, 27, 100, 21);
		p.add(tfThreadcount);
		tfThreadcount.setColumns(10);
		tfThreadcount.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				try {
					int i = Integer.parseInt(tfThreadcount.getText());
					if (i < 1 || i > 512) {
						JOptionPane.showMessageDialog(null, "请输入[1,512]的整数！", "Error!", JOptionPane.ERROR_MESSAGE);
						tfThreadcount.setText("1");
					}
					b.setThreadcount(i);
				} catch (Exception exception) {
				}
			}
		});
		tfThreadcount.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (tfThreadcount.getText().equals("[1,512]")) {
					tfThreadcount.setText("");
				}
			}
		});

		b.setDwVoiceChan(1);

		JLabel lbCbVoiceDataCallBack = new JLabel("cbVoiceDataCallBack:");
		lbCbVoiceDataCallBack.setBounds(10, 50, 130, 15);
		p.add(lbCbVoiceDataCallBack);

		b.setCbVoiceDataCallBack(0);
		JComboBox cbCbVoiceDataCallBack = new JComboBox();
		cbCbVoiceDataCallBack.setModel(new DefaultComboBoxModel(new String[] { "0-NULL", "1-cbVoiceDataCallBack" }));
		cbCbVoiceDataCallBack.setBounds(141, 47, 230, 21);
		cbCbVoiceDataCallBack.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				int i = cbCbVoiceDataCallBack.getSelectedIndex();
				b.setCbVoiceDataCallBack(i);
			}
		});
		p.add(cbCbVoiceDataCallBack);

		JLabel lbLoopinterval = new JLabel("loopinterval:");
		lbLoopinterval.setBounds(10, 70, 130, 15);
		p.add(lbLoopinterval);

		b.setLoopinterval(15);
		JTextField tfLoopinterval = new JTextField("请输入>=15整数", 10);
		tfLoopinterval.setBounds(141, 67, 100, 21);
		p.add(tfLoopinterval);
		// tfLoopinterval.setColumns(10);
		tfLoopinterval.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				try {
					int i = Integer.parseInt(tfLoopinterval.getText());
					b.setLoopinterval(i);
				} catch (Exception exception) {
				}
			}
		});
		tfLoopinterval.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (tfLoopinterval.getText().equals("请输入>=15整数")) {
					tfLoopinterval.setText("");
				}
			}
		});
	}
	
	public boolean isMatches(String ip) {
		boolean flag = false;
		try {
			String regex = "^((2[0-4]\\d|25[0-5]|[01]?\\d\\d?)\\.){3}(2[0-4]\\d|25[0-5]|[01]?\\d\\d?)";
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