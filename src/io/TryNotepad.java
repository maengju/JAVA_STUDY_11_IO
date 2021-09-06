package io;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

public class TryNotepad extends JFrame implements ActionListener {
	private JTextArea area;
	private MenuPane menu;
	private File file;

	public TryNotepad() {
		area = new JTextArea();
		area.setFont(new Font("���ü", Font.BOLD, 10));
		JScrollPane scroll = new JScrollPane(area);
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

		menu = new MenuPane();
		this.setJMenuBar(menu);

		this.add("Center", scroll);

		this.setBounds(400, 100, 500, 500);
		this.setVisible(true);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				int result = JOptionPane.showConfirmDialog(TryNotepad.this, "�����Ͻðڽ��ϱ�?", "�޸���",
						JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
				if (result == JOptionPane.YES_OPTION) {
					// ���� �����ϰ�
					saveDialog();
					fileWrite();
					
					System.exit(0);
				} else if (result == JOptionPane.NO_OPTION) {
					System.exit(0);
				}

			}
		});

		// �̺�Ʈ ó�� - �޴�
		menu.getNewM().addActionListener(this);
		menu.getOpenM().addActionListener(this);
		menu.getSaveM().addActionListener(this);
		menu.getExitM().addActionListener(this);
		menu.getCutM().addActionListener(this);
		menu.getCopyM().addActionListener(this);
		menu.getPasteM().addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == menu.getNewM()) {
			area.setText("");
		} else if (e.getSource() == menu.getOpenM()) {
			openDialog();// ���� ���̾�α� - ���ϼ���
			fileRead();// ������ ������ �о TextArea �Ѹ���

		} else if (e.getSource() == menu.getSaveM()) {
			// ���� - step
			saveDialog();
			fileWrite();

		} else if (e.getSource() == menu.getExitM()) {
			// System.exit(0);

			// ���̾�α�
			int result = JOptionPane.showConfirmDialog(this, "�����Ͻðڽ��ϱ�?", "�޸���", JOptionPane.YES_NO_CANCEL_OPTION,
					JOptionPane.QUESTION_MESSAGE);
			if (result == 0) { // JOptionPane.YES_OPTION
				// ���� �����ϰ�
				saveDialog();
				fileWrite();
				
				System.exit(0);
			} else if (result == JOptionPane.NO_OPTION) {
				System.exit(0);
			}

		} else if (e.getSource() == menu.getCutM()) {
			area.cut();

		} else if (e.getSource() == menu.getCopyM()) {
			area.copy();

		} else if (e.getSource() == menu.getPasteM()) {
			area.paste();
		}
	}

	private void fileWrite() {

		if (file == null)
			return;
		String data = null;
		
		BufferedWriter bw;
		try {
			bw = new BufferedWriter(new FileWriter(file));
			data = area.getText();
			
			bw.write(data);
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}//fileWrite()
	
	

	private void saveDialog() {
		JFileChooser chooser = new JFileChooser();
		int result = chooser.showSaveDialog(this);
		if (result == JFileChooser.APPROVE_OPTION) {//���� ����
			file = chooser.getSelectedFile();
		}
		JOptionPane.showMessageDialog(this, file);
		
		
	}//saveDialog

	
	private void fileRead() {
		if (file == null)
			return;
		area.setText("");
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			String line;
			while ((line = br.readLine()) != null) { // 1��(Enter�� ĥ �� ����)
				// area.setText(line); - �����
				area.append(line + "\n"); //��������� ���͸� �� �б� ������ ���͸� ���� ����� �ȴ�.

			} // while

			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}//fileRead
	

	private void openDialog() {
		JFileChooser chooser = new JFileChooser();
		int result = chooser.showOpenDialog(this);
		if (result == JFileChooser.APPROVE_OPTION) { // ���� ����
			file = chooser.getSelectedFile();
		}

		JOptionPane.showMessageDialog(this, file);
	}//openDialog

	
	
	
	
	
	
	
	public static void main(String[] args) {
		new TryNotepad();
	}//main

}
