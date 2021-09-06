package io;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class DataStream {

	public static void main(String[] args) throws IOException {
		
		DataOutputStream dos = new DataOutputStream(new FileOutputStream("result.txt"));
		dos.writeUTF("ȫ�浿");
		dos.writeInt(25);
		dos.writeFloat(185.3f);
		dos.close(); //io���� �������� ������ close
		
		DataInputStream dis = new DataInputStream(new FileInputStream("result.txt"));
		System.out.println("�̸� = "+dis.readUTF());
		System.out.println("���� = "+dis.readInt());
		System.out.println("Ű = "+dis.readFloat());
		dis.close();
		
	}

}
