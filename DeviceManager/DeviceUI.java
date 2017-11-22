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

/**
 * 
 */

/**
 * @author Gene1994
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
		frmDevicemanager.setTitle("\u8BBE\u5907\u7BA1\u7406");
		frmDevicemanager.setBounds(100, 100, 281, 267);
		frmDevicemanager.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmDevicemanager.getContentPane().setLayout(null);

		JButton button_insert = new JButton("\u6DFB\u52A0\u8BBE\u5907");
		button_insert.setBounds(76, 60, 120, 23);
		frmDevicemanager.getContentPane().add(button_insert);
		button_insert.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				try {
					new DeviceInsert();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					// System.out.println("gg");
				}
			}
		});

		JButton button_select = new JButton("\u67E5\u627E\u8BBE\u5907");
		button_select.setBounds(76, 93, 120, 23);
		frmDevicemanager.getContentPane().add(button_select);
		button_select.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new DeviceSelect();
			}
		});

		JButton btn_showAll = new JButton("显示全部设备");
		btn_showAll.setBounds(76, 126, 120, 23);
		frmDevicemanager.getContentPane().add(btn_showAll);
		btn_showAll.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				DeviceList dl = new DeviceList();
				dl.selectAll();
			}
		});

		JLabel lbHint = new JLabel(
				"*\u8FDB\u884C\u4FEE\u6539\u3001\u5220\u9664\u8BBE\u5907\u64CD\u4F5C\u524D\uFF0C\u8BF7\u5148\u67E5\u627E\u8BBE\u5907");
		lbHint.setForeground(Color.GRAY);
		lbHint.setBounds(10, 204, 280, 15);
		frmDevicemanager.getContentPane().add(lbHint);
	}
}
