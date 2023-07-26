import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
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

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;

public class ReportList extends JFrame {
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JTable tblMember;
	private JTableModel tblModel;
	private JPanel contentPane;
	
	/**
     * Launch the application.
     */
	
    public static void main(String[] args) {
        
    }

	public ReportList() {
		// TODO Auto-generated constructor stub	
		
		setBounds(450, 190, 900, 500);
        setResizable(false);

        contentPane = new JPanel();
        contentPane.setBackground(new Color(27,28,50));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        
        JLabel lblScreenTitle = new JLabel("Reports", SwingConstants.CENTER);
        lblScreenTitle.setForeground(Color.yellow);
        lblScreenTitle.setFont(new Font("Times New Roman", Font.BOLD, 35));
        lblScreenTitle.setBounds(0, 10, 900, 100);
        contentPane.add(lblScreenTitle);
        
   
//        DefaultTableModel model = new DefaultTableModel();
//        model.setColumnIdentifiers(columnNames);
        
        tblModel = new JTableModel();
        tblMember = new JTable(tblModel);
        tblMember.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
      	tblMember.setFillsViewportHeight(true);
      	tblMember.setRowHeight(30);
      	tblMember.removeEditor();
      	
      	Color color = Color.BLACK;
      	MatteBorder border = new MatteBorder(1, 1, 1, 1, color);
      	tblMember.setBorder(border);
      
      	JScrollPane scroll = new JScrollPane(tblMember);
      	tblMember.setFillsViewportHeight(true);	
		
		TableCellRenderer buttonRenderer = new JTableButtonRenderer();
		tblMember.getColumn("View").setCellRenderer(buttonRenderer);
		tblMember.addMouseListener(new JTableButtonMouseListener(tblMember));
		
		scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
      	scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

       	scroll.setBounds(0, 100, 900, 500);
        contentPane.add(scroll);
        
	}
	
	public static class JTableModel extends AbstractTableModel {
		
		private static final long serialVersionUID = 1L;
		private static final String[] COLUMN_NAMES = new String[] {"Title", "Charges", "Fine", "Time","View"};
		private static final Class<?>[] COLUMN_TYPES = new Class<?>[] {String.class, String.class, String.class,String.class, String.class,String.class, JButton.class, JButton.class, JButton.class};
		
		ArrayList<ReportModel> arrReport = new ArrayList<ReportModel>();
		
		public JTableModel() {
			// TODO Auto-generated constructor stub
			getDataFromDatabase();
		}
		private void getDataFromDatabase() {
			
			for(int i = 0;i<arrReport.size();i++) {
				arrReport.remove(i);
			}
			
			try
	        { 
	        	Connection connection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/Traffic?serverTimezone=UTC",
	                    "root", "Mahesh@123");
	        	
		        String sql = "select * from report order by ID desc";
		        PreparedStatement ps = connection.prepareStatement(sql);
		        ResultSet rs = ps.executeQuery();
		        int i = 0;
		        while(rs.next())
		        {
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
		        
		        if(i < 1)
		        {
		        	JOptionPane.showMessageDialog(null, "No Record Found","Error",
		        	JOptionPane.ERROR_MESSAGE);
		        }
		        if(i == 1)
		        {
		        	System.out.println(i+" Record Found");
		        }
		        else
		        {
		        	System.out.println(i+" Records Found");
		        }
		        this.fireTableDataChanged();
	        }
			
	        catch(Exception ex)
	        {
	        	JOptionPane.showMessageDialog(null, ex.getMessage(),"Error",
	        			JOptionPane.ERROR_MESSAGE);
	        }
		}
		
		
		@Override 
		public int getColumnCount() {
			return COLUMN_NAMES.length;
		}

		@Override 
		public int getRowCount() {
			return arrReport.size();
		}
		
		@Override 
		public String getColumnName(int columnIndex) {
	        return COLUMN_NAMES[columnIndex];
	    }
		
		@Override 
		public Class<?> getColumnClass(int columnIndex) {
			return COLUMN_TYPES[columnIndex];
		}

		@Override 
		public Object getValueAt(final int rowIndex, final int columnIndex) {
			
			switch (columnIndex) {
			case 0 : return arrReport.get(rowIndex).getTitle();
			case 1 : return arrReport.get(rowIndex).getCharges();
			case 2 : return arrReport.get(rowIndex).getFine();
			case 3 : return arrReport.get(rowIndex).getCreated();
			case 4: final JButton btnView = new JButton(COLUMN_NAMES[columnIndex]);
			btnView.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {
							
							System.out.println("facultyArr.get(rowIndex).getId() : "+arrReport.get(rowIndex).getId());
							
							ReportDetails obj = new ReportDetails(arrReport.get(rowIndex).getId());
			            	
			            	obj.setTitle("View User");
			                obj.setVisible(true);
			                Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
			                obj.setLocation(dim.width/2 - obj.getSize().width/2, dim.height/2 - obj.getSize().height/2);

			                
							}
						});
						return btnView;
				default: return "Testing";
			}
		}	
	}

	private static class JTableButtonRenderer implements TableCellRenderer {		
		@Override public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
			JButton button = (JButton)value;
			return button;	
		}
	}
	
	private static class JTableButtonMouseListener extends MouseAdapter {
		private final JTable table;
		
		public JTableButtonMouseListener(JTable table) {
			this.table = table;
		}
		

		public void mouseClicked(MouseEvent e) {
			int column = table.getColumnModel().getColumnIndexAtX(e.getX());
			int row    = e.getY()/table.getRowHeight(); 

			if (row < table.getRowCount() && row >= 0 && column < table.getColumnCount() && column >= 0) {
			    Object value = table.getValueAt(row, column);
			    if (value instanceof JButton) {
			    	((JButton)value).doClick();
			    }
			}
		}
	}
}