package DeviceManager;

import DeviceManager.DeviceInsert;
import DeviceManager.DeviceSelect;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Toolkit;

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
		frmDevicemanager
				.setIconImage(Toolkit.getDefaultToolkit().getImage(DeviceUI.class.getResource("/res/device_1.png")));
		frmDevicemanager.setTitle("\u8BBE\u5907\u7BA1\u7406");
		frmDevicemanager.setBounds(100, 100, 236, 286);
		frmDevicemanager.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmDevicemanager.getContentPane().setLayout(null);

		JButton button_insert = new JButton("\u6DFB\u52A0\u8BBE\u5907");
		button_insert.setBounds(52, 61, 120, 23);
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

		JButton button_select = new JButton("\u67E5\u627E\u8BBE\u5907");
		button_select.setBounds(52, 94, 120, 23);
		frmDevicemanager.getContentPane().add(button_select);
		button_select.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new DeviceSelect();
			}
		});

		JButton btn_showAll = new JButton("显示全部设备");
		btn_showAll.setBounds(52, 127, 120, 23);
		frmDevicemanager.getContentPane().add(btn_showAll);
		btn_showAll.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				DeviceList dl = new DeviceList();
				dl.selectAll();
			}
		});

		JLabel label = new JLabel(
				"*\u67E5\u627E\u8F83\u591A\u8BBE\u5907\u53EF\u80FD\u9700\u8981\u8F83\u957F\u65F6\u95F4");
		label.setForeground(Color.GRAY);
		label.setBounds(10, 196, 280, 15);
		frmDevicemanager.getContentPane().add(label);
	}
}
