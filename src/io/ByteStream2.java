package io;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class ByteStream2 {

	public static void main(String[] args)throws IOException {
		File file = new File("data.txt");
		int size = (int)file.length();
		
		byte[] b = new byte[size]; //배열생성
		BufferedInputStream bis = new BufferedInputStream(new FileInputStream("data.txt"));
		bis.read(b,0,size);
		
		System.out.println(new String(b));//byte[]을 String으로 변환해서 한번에 
		bis.close();
	}

}
