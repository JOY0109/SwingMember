package SwingMain;


import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import DB.MemberDao;
import DB.SqlParameter;

public class MasterTable extends JDialog implements ActionListener{
	DefaultTableModel dtm;
	JTable jTable;
	JScrollPane pane;
	JPanel panel0;
	JPanel panel1;
	JButton comentB,resfreshB,surchB,cometMo;
	JTextField surchF;
	
	Vector v;   //data list  
	Vector cols; //list의 제목
	
	GridBagLayout gb;
	GridBagConstraints gbc;
	
	
	
	public MasterTable() {
		//super(" Jtable TEST ");
		this.setModal(true);
		this.setTitle("관리자 전용 회원 목록 조회");
		DBConponent(); //테이블 모양 
		this.setSize(1200,600);
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setVisible(true);
	}

	
	private void DBConponent() {
		dtm=initTable(); //테이블 내용을 담을 곳 
		jTable=new JTable(dtm);
		pane=new JScrollPane(jTable);
		this.add(pane);
		
		gb=new GridBagLayout();
		this.setLayout(gb);
		gbc=new GridBagConstraints();
		gbc.fill =GridBagConstraints.BOTH;
		gbc.weightx=1.0;
		gbc.weighty=1.0;
			
		panel0=new JPanel();
		panel1=new JPanel();
		
		comentB=new JButton("코멘트 수정"); //아이디만 넣어도 수정 가능 하도록 
		panel0.add(comentB);
		
		
		resfreshB=new JButton("새로고침");
		panel0.add(resfreshB);
		
		surchB=new JButton("이름검색");
		panel1.add(surchB);
		surchF=new JTextField(60); //검색입력 받는 곳 
		panel1.add(surchF);
		
		gbAdd(panel0, 0, 0, 1, 1);
		gbAdd(panel1, 0, 1, 1, 1);
		gbAdd(pane, 0, 2, 1, 1);
		
		this.resfreshB.addActionListener(this);
		this.surchB.addActionListener(this);
		this.comentB.addActionListener(this);

	}

	
	//버튼 전체 이벤트 연결 
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton btn=(JButton) e.getSource();
		switch (btn.getText()) {
		case "새로고침":
			RefreshTb(); 
			break;
		case "검색":
			surchEvent(); 
			break;
		case "코멘트 수정":
			cometModify();
			break;

		}
		
	}
	

	 //코멘트 수정 이벤트-ok
	private void cometModify() {
		new comentModifyPanel();	
	}


	//검색 이벤트 연결 -수정 필요 
	private void surchEvent() {
		MemberDao mDao=new MemberDao();
		SqlParameter sp=getSurchData();
		mDao.surchMember(sp); //이거 수정 해야 됨****************** 
		System.out.println("검색완료");

	}

	//검색 데이터 
	private SqlParameter getSurchData() {
		SqlParameter sp=new SqlParameter();
		String userid= this.surchF.getText();
		sp.setUserid(userid);
		
		String username=this.surchF.getText();
		sp.setUsername(username);
		
		String phonNum=this.surchF.getText();
		sp.setPhone(phonNum);
		
		return sp;
	}


	private DefaultTableModel initTable() {
		cols=getCol(); //제목 줄 
		v=dataList();  //테이블 내용 
		DefaultTableModel dtm=new DefaultTableModel(v, cols); //이게 있어야 창에 출력 됨 
		return dtm;
	}
	
	//새로고침 이벤트 -ok
	private void RefreshTb() {
		DefaultTableModel dataModel=initTable();
		jTable.setModel(dataModel);
		jTable.repaint();
		
	}



	//initTable 안에 들어갈 애들 
	private Vector dataList() {
		MemberDao mDao=new MemberDao();
		Vector v=mDao.getMemberList();
		
		return v;
	}

	//initTable 안에 들어갈 애들 
	private Vector getCol() {
		Vector cols=new Vector();
		cols.add("아이디");
		cols.add(" 이 름 ");
		cols.add("전화번호");
		cols.add("성별");
		cols.add("생일");
		cols.add("요구사항(코멘트)");
		
		
		return cols;
	}

	private void gbAdd(JComponent c, int x, int y, int w, int h) {
		gbc.gridx = x;
		gbc.gridy = y;
		gbc.gridwidth = w;
		gbc.gridheight = h;
		gb.setConstraints(c, gbc);
		gbc.insets = new Insets(2, 2, 2, 2);
		this.add(c, gbc);

	}
	

	
	
	
}//class close
