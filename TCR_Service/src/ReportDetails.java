import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class ReportDetails extends JFrame {
	
	public int userId;
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {

    }

    /**
     * Create the frame.
     */
    public ReportDetails(int userId) {
    	this.userId = userId;
    	
    	setBounds(450, 190, 920, 700);
        setResizable(false);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblScreenTitle = new JLabel("Report Details", SwingConstants.CENTER);
        lblScreenTitle.setForeground(Color.BLACK);
        lblScreenTitle.setForeground(new Color(27,28,50));
        
        lblScreenTitle.setFont(new Font("Times New Roman", Font.BOLD, 35));
        lblScreenTitle.setBounds(0, 10, 900, 100);
        contentPane.add(lblScreenTitle);

        JLabel lblTitle = new JLabel("Title :");
        lblTitle.setBackground(Color.BLACK);
        lblTitle.setForeground(Color.BLACK);
        lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblTitle.setBounds(50, 100, 200, 50);
        contentPane.add(lblTitle);
        
        JLabel viewTitle = new JLabel("View Title");
        viewTitle.setBackground(Color.BLACK);
        viewTitle.setForeground(Color.BLACK);
        viewTitle.setFont(new Font("Tahoma", Font.PLAIN, 15));
        viewTitle.setBounds(200, 100, 150, 50);
        contentPane.add(viewTitle);
        
        JLabel lblDetails = new JLabel("Details:");
        lblDetails.setForeground(Color.BLACK);
        lblDetails.setBackground(Color.CYAN);
        lblDetails.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblDetails.setBounds(50, 170, 200, 50);
        contentPane.add(lblDetails);
        
        JLabel viewDetails = new JLabel(" ");
        viewDetails.setForeground(Color.BLACK);
        viewDetails.setBackground(Color.CYAN);
        viewDetails.setFont(new Font("Tahoma", Font.PLAIN, 15));
        viewDetails.setBounds(200, 170, 150, 50);
        contentPane.add(viewDetails);
        
        JLabel lblIncident = new JLabel("Incident :");
        lblIncident.setForeground(Color.BLACK);
        lblIncident.setBackground(Color.CYAN);
        lblIncident.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblIncident.setBounds(50, 240, 200, 50);
        
        contentPane.add(lblIncident);
        
        JLabel viewIncident = new JLabel("view incident");
        viewIncident.setForeground(Color.BLACK);
        viewIncident.setBackground(Color.CYAN);
        viewIncident.setFont(new Font("Tahoma", Font.PLAIN, 15));
        viewIncident.setBounds(200, 240, 150, 50);
        contentPane.add(viewIncident);
        
        JLabel lblFine = new JLabel("Fine :");
        lblFine.setForeground(Color.BLACK);
        lblFine.setBackground(Color.CYAN);
        lblFine.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblFine.setBounds(50, 310, 200, 50);
        contentPane.add(lblFine);
        
        JLabel viewFine = new JLabel("View fine ");
        viewFine.setForeground(Color.BLACK);
        viewFine.setBackground(Color.CYAN);
        viewFine.setFont(new Font("Tahoma", Font.PLAIN, 15));
        viewFine.setBounds(200, 310, 200, 50);
        contentPane.add(viewFine);
        
        JLabel lblCharges = new JLabel("Charges :");
        lblCharges.setForeground(Color.BLACK);
        lblCharges.setBackground(Color.CYAN);
        lblCharges.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblCharges.setBounds(500, 100, 200, 50);
        contentPane.add(lblCharges);
        
        
        JLabel viewCharges = new JLabel("view charges");
        viewCharges.setForeground(Color.BLACK);
        viewCharges.setBackground(Color.CYAN);
        viewCharges.setFont(new Font("Tahoma", Font.PLAIN, 15));
        viewCharges.setBounds(650, 100, 200, 50);
        contentPane.add(viewCharges);
        
        
        JLabel lblEvidence = new JLabel("Evidence :");
        lblEvidence.setForeground(Color.BLACK);
        lblEvidence.setBackground(Color.CYAN);
        lblEvidence.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblEvidence.setBounds(500, 170, 200, 50);
        contentPane.add(lblEvidence);
        
        JLabel viewEvidence = new JLabel("View evidence");
        viewEvidence.setForeground(Color.BLACK);
        viewEvidence.setBackground(Color.CYAN);
        viewEvidence.setFont(new Font("Tahoma", Font.PLAIN, 15));
        viewEvidence.setBounds(650, 170, 200, 50);
        contentPane.add(viewEvidence);
        
        JLabel lblOfficer = new JLabel("Officer :");
        lblOfficer.setForeground(Color.BLACK);
        lblOfficer.setBackground(Color.CYAN);
        lblOfficer.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblOfficer.setBounds(500, 240, 200, 50);
        contentPane.add(lblOfficer);
        
        JLabel viewOfficer = new JLabel("view officer");
        viewOfficer.setForeground(Color.BLACK);
        viewOfficer.setBackground(Color.CYAN);
        viewOfficer.setFont(new Font("Tahoma", Font.PLAIN, 15));
        viewOfficer.setBounds(650, 240, 200, 50);
        contentPane.add(viewOfficer);
        
        
        JLabel lblTime = new JLabel("Time :");
        lblTime.setForeground(Color.BLACK);
        lblTime.setBackground(Color.CYAN);
        lblTime.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblTime.setBounds(500, 310, 200, 50);
        contentPane.add(lblTime);
        
        JLabel viewTime = new JLabel("view time");
        viewTime.setForeground(Color.BLACK);
        viewTime.setBackground(Color.CYAN);
        viewTime.setFont(new Font("Tahoma", Font.PLAIN, 15));
        viewTime.setBounds(650, 310, 200, 50);
        contentPane.add(viewTime);
        
        try
        { 
        	Connection connection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/Traffic?serverTimezone=UTC",
                    "root", "Mahesh@123");
        	
        	String sql = "select * from report where `id` = ?";
	        PreparedStatement ps = connection.prepareStatement(sql);
	        ps.setInt(1, userId);
	        ResultSet rs = ps.executeQuery();
	        
	        if(rs.next())
	        {
//	        	int reportId = rs.getInt("id");
				String title = rs.getString("title");
				String details = rs.getString("details");
				String incident = rs.getString("incident");
//				String image = rs.getString("image");
				int fine = rs.getInt("fine");
				String charges = rs.getString("charges");
				String evidence = rs.getString("evidence");
				String officer = rs.getString("incident_officer");
				String created = rs.getString("created");
	        	
	        	viewTitle.setText(title);
	        	viewDetails.setText(details);
	        	viewIncident.setText(incident);
	        	viewFine.setText(String.valueOf(fine));
	        	viewCharges.setText(charges);
	        	viewEvidence.setText(evidence);
	        	viewOfficer.setText(officer);
	        	viewTime.setText(created);
	        	
	        }
        }
        catch(Exception ex)
        {
        	JOptionPane.showMessageDialog(null, ex.getMessage(),"Error",
        			JOptionPane.ERROR_MESSAGE);
        }
    }
}