import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.prefs.Preferences;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;

public class HomeScreen {
	private Preferences prefs;
	JFrame mainFrame;
	JTable tblRecentReports;
	JTable tblPersonSearch;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		HomeScreen main = new HomeScreen(args);
		main.go();
	}

	public HomeScreen(String... args) {

	}

	public void go() {

		JPanel headerPanel = new JPanel();
		headerPanel.setBackground(new Color(27, 28, 50));

//        Home screen
		JPanel homePanel = new JPanel();
		homePanel.setBackground(new Color(27, 28, 50));
		homePanel.setLayout(null);
//		homePanel.setLayout(new BoxLayout(homePanel, BoxLayout.Y_AXIS));

		JPanel searchPanel = new JPanel();
		searchPanel.setBackground(new Color(27, 28, 50));

		JPanel createReportPanel = new JPanel();
		createReportPanel.setBackground(new Color(27, 28, 50));

		GridLayout gridLayout = new GridLayout(1, 1);
		JPanel middlePanel = new JPanel(gridLayout);
		middlePanel.setBackground(Color.red);
		middlePanel.add(homePanel);

//      Header panel buttons start

		JButton btnHome = new JButton("Home");
		btnHome.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnHome.setBackground(new Color(59, 89, 182));
		btnHome.setForeground(Color.WHITE);
		btnHome.setFocusPainted(false);
		btnHome.setBorderPainted(false);

		btnHome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				middlePanel.removeAll();
				middlePanel.add(homePanel);
				middlePanel.updateUI();
			}
		});

		JButton btnSearch = new JButton("Search Database");
		btnSearch.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnSearch.setBackground(new Color(59, 89, 182));
		btnSearch.setForeground(Color.WHITE);
		btnSearch.setFocusPainted(false);
		btnSearch.setBorderPainted(false);

		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ReportList obj = new ReportList();
				obj.setTitle("Reports");
				obj.setVisible(true);
				Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
				obj.setLocation(dim.width / 2 - obj.getSize().width / 2, dim.height / 2 - obj.getSize().height / 2);
			}
		});

		JButton btnCreateReport = new JButton("Create Report");
		btnCreateReport.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnCreateReport.setBackground(new Color(59, 89, 182));
		btnCreateReport.setForeground(Color.WHITE);
		btnCreateReport.setFocusPainted(false);
		btnCreateReport.setBorderPainted(false);

		btnCreateReport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				middlePanel.removeAll();
//				middlePanel.add(createReportPanel);
//				middlePanel.updateUI();

				NewReport obj = new NewReport();
				obj.setTitle("Create Report");
				obj.setVisible(true);
				Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
				obj.setLocation(dim.width / 2 - obj.getSize().width / 2, dim.height / 2 - obj.getSize().height / 2);

			}
		});

		JButton btnLogout = new JButton("Logout");
		btnLogout.setBackground(new Color(59, 89, 182));
		btnLogout.setForeground(Color.WHITE);
		btnLogout.setFocusPainted(false);
		btnLogout.setBorderPainted(false);
		btnLogout.setFont(new Font("Times New Roman", Font.PLAIN, 20));

		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int input = JOptionPane.showOptionDialog(null, "Logout - are you sure?", "Logout",
						JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);

				if (input == JOptionPane.YES_OPTION) {

					prefs = Preferences.userNodeForPackage(this.getClass());
					prefs.putInt("adminID", 0);

					mainFrame.dispose();

					LoginScreen obj = new LoginScreen();
					obj.setTitle("Admin-Login");
					obj.setVisible(true);
					Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
					obj.setLocation(dim.width / 2 - obj.getSize().width / 2, dim.height / 2 - obj.getSize().height / 2);
				}
			}
		});

		headerPanel.add(btnHome);
		headerPanel.add(btnSearch);
		headerPanel.add(btnCreateReport);
		headerPanel.add(btnLogout);

//    Header panel buttons end

//		home panel start

		JLabel lblNewLabel = new JLabel("Home", SwingConstants.CENTER);
		lblNewLabel.setForeground(Color.YELLOW);
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 35));
		lblNewLabel.setBounds(500, 0, 100, 50);
		homePanel.add(lblNewLabel);

		JLabel lblRecentReports = new JLabel("RECENT REPORTS", SwingConstants.LEFT);
		lblRecentReports.setForeground(Color.YELLOW);
		lblRecentReports.setFont(new Font("Times New Roman", Font.BOLD, 35));
		lblRecentReports.setBounds(50, 50, 500, 50);
		homePanel.add(lblRecentReports);

//		JLabel lblPersonSearch = new JLabel("PERSON SEARCHES", SwingConstants.LEFT);
//		lblPersonSearch.setForeground(Color.YELLOW);
//		lblPersonSearch.setFont(new Font("Times New Roman", Font.BOLD, 35));
//		lblPersonSearch.setBounds(600, 50, 500, 50);
//		homePanel.add(lblPersonSearch);

		JTableModel tblPersonModel;

		tblPersonModel = new JTableModel();

		tblPersonSearch = new JTable(tblPersonModel);
		tblPersonSearch.setBounds(0, 100, 400, 200);
		tblPersonSearch.setBackground(new Color(27, 28, 50));
		tblPersonSearch.setAutoCreateColumnsFromModel(false);
		tblPersonSearch.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		tblPersonSearch.setFillsViewportHeight(true);
		tblPersonSearch.setRowHeight(100);
		tblPersonSearch.removeEditor();
		Color color = Color.BLACK;
		MatteBorder border = new MatteBorder(1, 1, 1, 1, color);
		tblPersonSearch.setBorder(border);

		JScrollPane scrollPerson = new JScrollPane(tblPersonSearch);

		tblPersonSearch.setFillsViewportHeight(true);

		TableCellRenderer panelRendererPerson = new JTablePanelRenderer();
		tblPersonSearch.getColumn("").setCellRenderer(panelRendererPerson);
		tblPersonSearch.addMouseListener(new JTableButtonMouseListener(tblPersonSearch));

		scrollPerson.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPerson.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

		scrollPerson.setBounds(50, 100, 400, 300);
		scrollPerson.setBackground(new Color(27, 28, 50));

		JTableModel tblModel;

		tblModel = new JTableModel();

		tblRecentReports = new JTable(tblModel);
		tblRecentReports.setBounds(0, 100, 400, 200);
		tblRecentReports.setBackground(new Color(27, 28, 50));
		tblRecentReports.setAutoCreateColumnsFromModel(false);
		tblRecentReports.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		tblRecentReports.setFillsViewportHeight(true);
		tblRecentReports.setRowHeight(100);
		tblRecentReports.removeEditor();
//		Color color = Color.BLACK;
//		MatteBorder border = new MatteBorder(1, 1, 1, 1, color);
		tblRecentReports.setBorder(border);

		JScrollPane scroll = new JScrollPane(tblRecentReports);

		tblRecentReports.setFillsViewportHeight(true);

		TableCellRenderer panelRenderer = new JTablePanelRenderer();
		tblRecentReports.getColumn("").setCellRenderer(panelRenderer);
		tblRecentReports.addMouseListener(new JTableButtonMouseListener(tblRecentReports));

		scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

		scroll.setBounds(50, 100, 400, 300);
		scroll.setBackground(new Color(27, 28, 50));

		homePanel.add(scrollPerson);
//		tblRecentReports.revalidate();
//		tblRecentReports.repaint();
//		home panel end

		mainFrame = new JFrame();
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		mainFrame.setResizable(false);
		mainFrame.add(headerPanel, BorderLayout.NORTH);
		mainFrame.add(middlePanel, BorderLayout.CENTER);
//        mainFrame.add(footerPanel, BorderLayout.SOUTH);
		mainFrame.pack();
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		mainFrame.setBounds(0, 0, dimension.width, dimension.height - 80);
		mainFrame.setVisible(true);
	}
	static ArrayList<ReportModel> arrReport;
	public static class JTableModel extends AbstractTableModel {

		private static final long serialVersionUID = 1L;
//		private static final String[] COLUMN_NAMES = new String[] { "First Name", "Last Name", "Email", "Phone No",
//				"Address", "Pincode", "View", "Edit", "Delete" };
		private static final Class<?>[] COLUMN_TYPES = new Class<?>[] { JPanel.class };

	
		public JTableModel() {
			arrReport = new ArrayList<ReportModel>();
			getDataFromDatabase();
		}

		private void getDataFromDatabase() {

			for (int i = 0; i < arrReport.size(); i++) {
				arrReport.remove(i);
			}

			try {
				Connection connection = (Connection) DriverManager
						.getConnection("jdbc:mysql://localhost:3306/Traffic?serverTimezone=UTC", "root", "Mahesh@123");

				String sql = "select * from report order by ID desc limit 0,3";
				PreparedStatement ps = connection.prepareStatement(sql);
				ResultSet rs = ps.executeQuery();
				int i = 0;
				while (rs.next()) {
					int reportId = rs.getInt("id");
					String title = rs.getString("title");
					String details = rs.getString("details");
					String incident = rs.getString("incident");
					String image = rs.getString("image");
					int fine = rs.getInt("fine");
					String charges = rs.getString("charges");
					String evidence = rs.getString("evidence");
					String officer = rs.getString("incident_officer");
					String created = rs.getString("created");
		        	arrReport.add(new ReportModel(reportId, title, details, incident, image, fine, charges, evidence, officer, created));

					i++;
				}

				if (i < 1) {
					JOptionPane.showMessageDialog(null, "No Record Found", "Error", JOptionPane.ERROR_MESSAGE);
				}
				if (i == 1) {
					System.out.println(i + " Record Found");
				} else {
					System.out.println(i + " Records Found");
				}
				this.fireTableDataChanged();
			}

			catch (Exception ex) {
				JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			}
		}

		@Override
		public int getColumnCount() {
			return 1;
		}

		@Override
		public int getRowCount() {
			return arrReport.size();
		}

		@Override
		public String getColumnName(int columnIndex) {
			return "";
		}

		@Override
		public Class<?> getColumnClass(int columnIndex) {
			return COLUMN_TYPES[columnIndex];
		}

		@Override
		public Object getValueAt(final int rowIndex, final int columnIndex) {

			final JPanel cellPanel = new JPanel();
			
			return cellPanel;
		}
	}

	private static class JTablePanelRenderer implements TableCellRenderer {
		@Override
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
				int row, int column) {

			JPanel cellPanel = new JPanel();
			cellPanel.setBackground(new Color(0, 0, 0, 0));
			cellPanel.setLayout(null);

			JLabel btnDate = new JLabel(arrReport.get(row).getCreated(), SwingConstants.LEFT);
			btnDate.setBounds(20, 10, 200, 30);
			btnDate.setForeground(Color.cyan);
			btnDate.setBackground(Color.yellow);
			btnDate.setFont(new Font("Times New Roman", Font.BOLD, 14));

			cellPanel.add(btnDate);

			JButton btnTitle = new JButton(arrReport.get(row).getCharges());
			btnTitle.setFont(new Font("Times New Roman", Font.PLAIN, 14));
			btnTitle.setBackground(Color.yellow);
			btnTitle.setBounds(20, 50, 300, 50);
//			btnTitle.setForeground(new Color(255,255,255));
//			btnTitle.setFocusPainted(false);
//			btnTitle.setBorderPainted(false);
			btnTitle.setHorizontalAlignment(SwingConstants.CENTER);
			cellPanel.add(btnTitle);
//			JButton button = (JButton)value;
//			if (isSelected) {
//				btnTitle.setForeground(table.getSelectionForeground());
//				btnTitle.setBackground(table.getSelectionBackground());
//		    } else {
//		    	btnTitle.setForeground(table.getForeground());
//		    	btnTitle.setBackground(UIManager.getColor("Button.background"));
//		    }

			return cellPanel;
		}
	}

	private static class JTableButtonMouseListener extends MouseAdapter {
		private final JTable table;

		public JTableButtonMouseListener(JTable table) {
			this.table = table;
		}

		public void mouseClicked(MouseEvent e) {
			int column = table.getColumnModel().getColumnIndexAtX(e.getX());
			int row = e.getY() / table.getRowHeight();

			if (row < table.getRowCount() && row >= 0 && column < table.getColumnCount() && column >= 0) {
				Object value = table.getValueAt(row, column);
				if (value instanceof JButton) {
					((JButton) value).doClick();
				}
			}
		}
	}

}