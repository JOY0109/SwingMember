package login;

//1. 로그인 가능한창 만들기 
// 1-1. 프레임과 버튼 만들기 
// 1-2. 버튼에 액션 추가 
// 1-3. 입력 받을 공간 만들고 그거랑 비교하기 >>로그인 완료 혹은 실패 안내 띄우기 

//2. 파일 읽어오기 
// 2-1. 파일 reader로 읽어오기 
// 2-2. 1-3 입력 받을 공간에 입력되도록 하기 

//3. 파일을 읽어오는 대신 db에서 로그인 정보 받아오기

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import SwingMain.BasicFrame;
import SwingMain.MasterTable;

public class Login extends JFrame {

	public static void main(String[] args) {
		new Login();

	}

	public String putID = "";
	public String putPW = "";

	// 로그인 관련 매소드 전체를 불러옴
	public Login() {
		super("로그인 창");
		initC();

		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		pack();
		this.setVisible(true);
	}

	
	// 로그인 프레임, 로그인 버튼 클릭 액션
	private void initC() {
		this.setLayout(new GridLayout(3, 2));

		// 1-1. 프레임과 버튼 만들기
		// 컴포넌트 생성
		JLabel userID = new JLabel("아이디");
		JLabel userPW = new JLabel("비밀번호");
		JTextField userTxtID = new JTextField(10);
		JPasswordField userTxtPW = new JPasswordField(10);

		this.add(userID);
		this.add(userTxtID);
		this.add(userPW);
		this.add(userTxtPW);

		// 단추추가
		JButton btLogin = new JButton("로그인");
		JButton btCancel = new JButton("취  소");
		this.add(btLogin);
		this.add(btCancel);

		// 액션 ================================================================
		// 로그인
		btLogin.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				//System.out.println("로그인 버튼 클릭 ok"); - 확인ok 
				String wID = userTxtID.getText().toLowerCase();
				@SuppressWarnings("deprecation")
				String wPW = userTxtPW.getText();
				String loginResult = null;
				dispose();
				
				try {
					loginResult = checkLogin(wID, wPW); //아래에 로그인 체크 
				} catch (IOException e1) {

					e1.printStackTrace();
				}

			}
			
			
			//로그인 체크 - 파일을 불러오는 부분 
			//나중에 db에 연결하기 
			private String checkLogin(String wID, String wPW) throws IOException {

			
				String loginResult = "로그인 되지않음";
				
				//로그인 가능한 아이디 비번 위치 - 관리자 전용 
				File filename = new File("C:\\JOY_WORKSPACE\\P10JoySwing\\src\\login\\Members.txt");
				FileReader reader = new FileReader(filename);
				BufferedReader buff = new BufferedReader(reader);

				String line = "";
				while ((line = buff.readLine()) != null) {
					String[] li = line.trim().split(",");
					String userid = li[0].trim().toLowerCase();
					String password = li[1].trim();
					String name = li[2].trim();

					if ((userid.equals(wID)) && (password.equals(wPW))) {
						System.out.println(name + "님 환영");
						//마스터 페이지는 로그인하면 볼 수 있도록 하기 
						new MasterTable();
						return name;

					} else
						System.out.println("다시 입력 하세요");

				}

				buff.close();
				reader.close();

				return loginResult;
			}
		});

		// 로그인 취소
		btCancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String title = "로그인 취소";
				String mes = "취소 확실?";
				JOptionPane.showMessageDialog(null, mes, title, JOptionPane.ERROR_MESSAGE);
				dispose();
				
			}

			
			
		});

	}


}
