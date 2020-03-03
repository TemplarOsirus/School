
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class Main extends JFrame {

	private MyVis ana;
	private Connection conn;
	
	//cisString (countries)
	//marathon (male,female)
	//cis (Integers)

	public Main() {
		ana = new MyVis();
		setContentPane(ana);
		setSize(800,600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("My First CS 490R Program");
		setJMenuBar(setupMenu());
		setupDB();
		setVisible(true);
	}

	private void runQuery(String sql) {
		try {
			Statement s = conn.createStatement();
			ResultSet rs = s.executeQuery(sql);
			List<Double> nums = new ArrayList<>();
			List<String> labels = new ArrayList<>();
			while (rs.next()) {
				double numsList = rs.getDouble(1);
				String labelList = rs.getString(2);
				nums.add(numsList);
				labels.add(labelList);
				System.out.println("There are " + numsList + " students in major " + labelList);
			}
			rs.close();
			s.close();
			ana.setData(nums, labels);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void setupDB() {
		try {
			conn = DriverManager.getConnection("jdbc:derby:cs490rData");
			//			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public JMenuBar setupMenu() {
		var menu = new JMenuBar();
		var vika = new JMenu("Vika");
		var dexter = new JMenu("Dexter");
		var nate = new JMenuItem("CIS Data Set");
		var marathon = new JMenuItem("Marathon Data Set");
		var cisString = new JMenuItem("CIS String Data Set");
		var yoshiki = new JMenuItem("Yoshiki");
		var yijie = new JMenuItem("Yijie");
		dexter.add(yoshiki);
		dexter.add(yijie);
		vika.add(nate);
		vika.add(marathon);
		vika.add(cisString);

		yijie.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				var fc = new JFileChooser();
				fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				int vika = fc.showOpenDialog(Main.this);
				if (vika == JFileChooser.APPROVE_OPTION) {
					File f = fc.getSelectedFile();
					//TODO pass this file to the root node,
					//to build a new tree structure...
				}
			}

		});

		yoshiki.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				//ana.changeMessage("Yoshiki was here!");	
				runQuery("SELECT COUNT(*), major FROM cisString GROUP BY major");
				
				
			}

		});

		nate.addActionListener(e -> {
			Statement s;
			try {
				s = conn.createStatement();
				ResultSet rs = s.executeQuery("SELECT * FROM cis"
						+ "");
				ResultSetMetaData rsmd = rs.getMetaData();
				int n = rsmd.getColumnCount();
				for (int i=1; i<=n; i++) {
					String chance = rsmd.getColumnName(i) +
							" " + rsmd.getColumnTypeName(i);
					System.out.println(chance);
					//Here, I instantiate an Axis object, passing the
					//column name and type to the constructor.
				}
				while (rs.next()) {
					/* for each axis, pass the result set object
					 * to a "setter" method in the axis.
					 The axis object pulls the data it needs from the ResultSet.
					 */
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		});
		marathon.addActionListener(e -> {
			Statement s;
			try {
				s = conn.createStatement();
				ResultSet rs = s.executeQuery("SELECT * FROM marathon"
						+ "");
				ResultSetMetaData rsmd = rs.getMetaData();
				int n = rsmd.getColumnCount();
				for (int i=1; i<=n; i++) {
					String chance = rsmd.getColumnName(i) +
							" " + rsmd.getColumnTypeName(i);
					System.out.println(chance);
					//Here, I instantiate an Axis object, passing the
					//column name and type to the constructor.
				}
				while (rs.next()) {
					/* for each axis, pass the result set object
					 * to a "setter" method in the axis.
					 The axis object pulls the data it needs from the ResultSet.
					 */
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		});
		cisString.addActionListener(e -> {
			Statement s;
			try {
				s = conn.createStatement();
				ResultSet rs = s.executeQuery("SELECT * FROM cisString"
						+ "");
				ResultSetMetaData rsmd = rs.getMetaData();
				int n = rsmd.getColumnCount();
				for (int i=1; i<=n; i++) {
					String chance = rsmd.getColumnName(i) +
							" " + rsmd.getColumnTypeName(i);
					System.out.println(chance);
					//Here, I instantiate an Axis object, passing the
					//column name and type to the constructor.
				}
				while (rs.next()) {
					/* for each axis, pass the result set object
					 * to a "setter" method in the axis.
					 The axis object pulls the data it needs from the ResultSet.
					 */
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		});
		menu.add(vika);
		menu.add(dexter);
		return menu;
	}

	public static void main(String[] args) {
		new Main();
	}

}

