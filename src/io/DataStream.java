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
		dos.writeUTF("홍길동");
		dos.writeInt(25);
		dos.writeFloat(185.3f);
		dos.close(); //io에선 쓰고난다음 무조건 close
		
		DataInputStream dis = new DataInputStream(new FileInputStream("result.txt"));
		System.out.println("이름 = "+dis.readUTF());
		System.out.println("나이 = "+dis.readInt());
		System.out.println("키 = "+dis.readFloat());
		dis.close();
		
	}

}
