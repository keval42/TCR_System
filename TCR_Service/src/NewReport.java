import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
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
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;

import com.mysql.cj.ParseInfo;

public class NewReport  extends JFrame {
	
    private static final long serialVersionUID = 1L;
     
    private JTextField txtIncident;
    private JTextField txtDetails;
    private JTextField txtEvidence;
    private JTextField txtTitle;
    
    private JPanel contentPane;
    
    JTable tblInfractions;
    JTableModel tableModel;
    JTextArea lblChargesText;
    int totalFine = 0;
//    int totalPunishment = 0;
    

    /**
     * Create the frame.
     */
    public NewReport() {
    	setBounds(450, 190, 920, 700);
        setResizable(false);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setBackground(new Color(27,28,50));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewReport = new JLabel("NEW REPORT", SwingConstants.CENTER);
        lblNewReport.setForeground(Color.YELLOW);
        lblNewReport.setFont(new Font("Times New Roman", Font.BOLD, 35));
        lblNewReport.setBounds(0, 10, 900, 100);
        contentPane.add(lblNewReport);

        JLabel lblName = new JLabel("NAME");
        lblName.setForeground(Color.yellow);
        lblName.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        lblName.setBounds(50, 100, 120, 50);
        contentPane.add(lblName);
        
        JLabel lblNameText = new JLabel("Mark Martin");
        lblNameText.setForeground(Color.white);
        lblNameText.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        lblNameText.setBounds(150, 100, 120, 50);
        contentPane.add(lblNameText);
        
        JLabel lblTitle = new JLabel("NAME");
        lblTitle.setForeground(Color.yellow);
        lblTitle.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        lblTitle.setBounds(50, 170, 120, 50);
        contentPane.add(lblTitle);
        
        
        
        

        JLabel lblIncident = new JLabel("INCIDENT");
        lblIncident.setForeground(Color.yellow);
        lblIncident.setBackground(Color.CYAN);
        lblIncident.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        lblIncident.setBounds(50, 240, 120, 50);
        contentPane.add(lblIncident);
        
        JLabel lblDetails = new JLabel("DETAILS");
        lblDetails.setForeground(Color.yellow);
        lblDetails.setBackground(Color.CYAN);
        lblDetails.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        lblDetails.setBounds(50, 310, 120, 50);
        contentPane.add(lblDetails);
        
        JLabel lblEvidence = new JLabel("EVIDENCE");
        lblEvidence.setForeground(Color.yellow);
        lblEvidence.setBackground(Color.CYAN);
        lblEvidence.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        lblEvidence.setBounds(50, 380, 120, 50);
        contentPane.add(lblEvidence);
        
        JLabel lblCharges = new JLabel("CHARGES");
        lblCharges.setForeground(Color.yellow);
        lblCharges.setBackground(Color.CYAN);
        lblCharges.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        lblCharges.setBounds(50, 450, 120, 50);
        contentPane.add(lblCharges);
        
        lblChargesText = new JTextArea ("");
        lblChargesText.setLineWrap(true);
        lblChargesText.setWrapStyleWord(true);
        JScrollPane scroll2 = new JScrollPane (lblChargesText, 
        JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scroll2.setBounds(150, 450, 200, 200);
        contentPane.add(scroll2);
       
        
        txtTitle = new JTextField();
        txtTitle.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        txtTitle.setBounds(150, 170, 200, 50);
        contentPane.add(txtTitle);
        txtTitle.setColumns(10);
        
        txtIncident = new JTextField();
        txtIncident.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        txtIncident.setBounds(150, 240, 200, 50);
        contentPane.add(txtIncident);
        txtIncident.setColumns(10);
        
        txtDetails = new JTextField();
        txtDetails.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        txtDetails.setBounds(150, 310, 200, 50);
        contentPane.add(txtDetails);
        txtDetails.setColumns(10);
        
        txtEvidence = new JTextField();
        txtEvidence.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        txtEvidence.setBounds(150, 380, 200, 50);
        contentPane.add(txtEvidence);
        txtEvidence.setColumns(10);
        
        
        JButton btnCreateReport = new JButton("Create Report");
        btnCreateReport.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            	String strTitle = txtTitle.getText();
                String strIncident = txtIncident.getText();
                String strDetails = txtDetails.getText();
                String strEvidence = txtEvidence.getText();
                String strOfficerName = lblNameText.getText();
                String strCharges = lblChargesText.getText();
                
                
                try {
                    Connection connection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/Traffic?serverTimezone=UTC",
                            "root", "Mahesh@123");

                    PreparedStatement st = (PreparedStatement) connection
                        .prepareStatement("Insert into report ( `title`,`details`, `incident`,`evidence`, `incident_officer`,`fine`,`charges`,`created`) values (?,?,?,?,?,?,?,now())");

                    st.setString(1, strTitle);
                    st.setString(2, strDetails);
                    st.setString(3, strIncident);
                    st.setString(4, strEvidence);
                    st.setString(5, strOfficerName);
                    st.setInt(6, totalFine);
                    st.setString(7, strCharges);
                    
                    st.execute();
                    dispose();
                } catch (SQLException sqlException) {
                    sqlException.printStackTrace();
                    System.out.println("SQL Error : "+ sqlException.getLocalizedMessage());
                }
            }
        });
        
        btnCreateReport.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        btnCreateReport.setBackground(new Color(240, 240, 240));
        btnCreateReport.setBounds(600, 450, 200, 50);
        contentPane.add(btnCreateReport);

        JLabel lblTraffic = new JLabel("TRAFFIC INFRACTIONS");
        lblTraffic.setForeground(Color.yellow);
        lblTraffic.setBackground(Color.CYAN);
        lblTraffic.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        lblTraffic.setBounds(500, 100, 200, 30);
        contentPane.add(lblTraffic);
        
        
        
        tableModel = new JTableModel();
        
        tblInfractions = new JTable(tableModel);
		tblInfractions.setBounds(0, 100, 400, 200);
		tblInfractions.setBackground(new Color(27, 28, 50));
		tblInfractions.setAutoCreateColumnsFromModel(false);
		tblInfractions.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		tblInfractions.setFillsViewportHeight(true);
		tblInfractions.setRowHeight(30);
		tblInfractions.removeEditor();
		Color color = Color.white;
		MatteBorder border = new MatteBorder(1, 1, 1, 1, color);
		tblInfractions.setBorder(border);

		tblInfractions.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
	        public void valueChanged(ListSelectionEvent event) {
	            // do some actions here, for example
	            // print first column value from selected row
	        	
	        	JLabel lbl = (JLabel) tblInfractions.getValueAt(tblInfractions.getSelectedRow(), 0);
	        	String fullText = lbl.getText();
	        	 
	        	String[] values = fullText.split(", ");
	        	
	        	String infractionName = values[0];
	        	String fine = values[1];
//	        	String punishment = values[2];
	        	
	        	
	        	String oldValue = lblChargesText.getText();
	        	if(!oldValue.contains(infractionName)) {
	        		String abc  = "";
	        		if(oldValue.isEmpty()) {
	        			abc = oldValue + infractionName;
	        		} else {
	        		abc = oldValue +", "+ infractionName;
	        		}
	        		totalFine = totalFine + Integer.parseInt(fine);
//	        		totalPunishment = totalPunishment + Integer.parseInt(punishment);
	        		lblChargesText.setText(abc);
	        	}
	        }
	    });
		
		JScrollPane scroll = new JScrollPane(tblInfractions);

		TableCellRenderer buttonRenderer = new JTableLabelRenderer();
		tblInfractions.getColumn("").setCellRenderer(buttonRenderer);
		
		tblInfractions.setFillsViewportHeight(true);
		scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

		scroll.setBounds(500, 140, 400, 300);
		scroll.setBackground(new Color(27, 28, 50));

		contentPane.add(scroll);
		
    }
    
    
    
    public static class JTableModel extends AbstractTableModel {
		
		private static final long serialVersionUID = 1L;
		private static final String[] COLUMN_NAMES = new String[] {""};
		private static final Class<?>[] COLUMN_TYPES = new Class<?>[] {JLabel.class};
		
		ArrayList<TrafficInfraction> arrInfraction = new ArrayList<TrafficInfraction>();
		
		public JTableModel() {
			// TODO Auto-generated constructor stub
			getDataFromDatabase();
		}
		private void getDataFromDatabase() {

			for (int i = 0; i < arrInfraction.size(); i++) {
				arrInfraction.remove(i);
			}

			try {
				Connection connection = (Connection) DriverManager
						.getConnection("jdbc:mysql://localhost:3306/Traffic?serverTimezone=UTC", "root", "Mahesh@123");

				String sql = "select * from traffic_Infractions order by ID desc";
				PreparedStatement ps = connection.prepareStatement(sql);
				ResultSet rs = ps.executeQuery();
				int i = 0;
				while (rs.next()) {
					int infractionID = rs.getInt("id");
					String strTitle = rs.getString("title");
					String strFine = rs.getString("fine_amount");
					String strPunish = rs.getString("punishment");
					String strcreated = rs.getString("created");
					
		        	arrInfraction.add(new TrafficInfraction(infractionID, strTitle, strFine,strPunish,strcreated));
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
			return COLUMN_NAMES.length;
		}

		@Override 
		public int getRowCount() {
			return arrInfraction.size();
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
				case 0: final JLabel lblView = new JLabel(COLUMN_NAMES[columnIndex]);
				String fullStr = arrInfraction.get(rowIndex).getTitle()+", "+arrInfraction.get(rowIndex).getFine_amount()+", "+arrInfraction.get(rowIndex).getPunishment();
				lblView.setText(fullStr);
				return lblView;
				default: return "";
			}
		}	
	}

	private static class JTableLabelRenderer implements TableCellRenderer {		
		@Override public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
			JLabel lbl = (JLabel)value;
			lbl.setBounds(20,20,200,30);
			lbl.setForeground(Color.white);
			return lbl;	
		}
	}
}