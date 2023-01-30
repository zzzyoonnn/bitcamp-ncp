package bitcamp.util;

public class BinaryEncoder {
	public static byte[] write(int value) {
		byte[] bytes = new byte[4];
		bytes[0] = (byte) (value >> 24); // 00 00 00 00 
		bytes[1] = (byte) (value >> 16);
		bytes[2] = (byte) (value >> 8);
		bytes[3] = (byte) value;
		return bytes;
	}
	
	public static byte[] write(String value) {
		// [2byte : 문자열의 바이트 배열 길이][nbyte : 문자열의 바이트배열]
		byte[] strBytes = value.getBytes();
		byte[] bytes = new byte[strBytes.length + 2];
		
		// System.arraycopy(원본, 원본 소스에서 어디서 부터 읽어올지,
							// 복사할 대상, 복사본에서 어디서부터 쓸 것인지,
								// 원본에서 복사본으로 데이터를 읽어서 쓸 길이
		System.arraycopy(strBytes, 0, bytes, 2, strBytes.length);
		
		// 길이 정보 삽임
		bytes[0] = (byte)(strBytes.length >> 8);
		bytes[1] = (byte)(strBytes.length);
		
		return bytes;
	}
	
	public static void main(String[] args) {
		/*
		int value = 0xabcedf31;
		byte[] bytes = ByteArrayGenerator.write(value);
		*/
		byte[] bytes = BinaryEncoder.write("ABC가각간");
		for (int i = 0; i < bytes.length; i++) {
			System.out.printf("%02x", bytes[i]);
		}
	}
}
