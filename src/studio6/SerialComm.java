package studio6;

import jssc.*;

public class SerialComm {

	SerialPort port;

	private boolean debug;  // Indicator of "debugging mode"
	
	// This function can be called to enable or disable "debugging mode"
	void setDebug(boolean mode) {
		debug = mode;
	}	
	

	// Constructor for the SerialComm class
	public SerialComm(String name) throws SerialPortException {
		port = new SerialPort(name);		
		port.openPort();
		port.setParams(SerialPort.BAUDRATE_9600,
			SerialPort.DATABITS_8,
			SerialPort.STOPBITS_1,
			SerialPort.PARITY_NONE);
		
		debug = false; // Default is to NOT be in debug mode
	}
		
	// TODO: Add writeByte() method from Studio 5
	public void writeByte(byte output) throws SerialPortException {
		if(debug) {
			System.out.println("<0x" + String.format("%x", output) + ">");
		}
		
		this.port.writeByte(output);
	}
	
	// TODO: Add available() method
	public boolean available() throws SerialPortException {
		return port.getInputBufferBytesCount() > 0;
	}
	
	// TODO: Add readByte() method
	public byte readByte() throws SerialPortException {
		return port.readBytes(1)[0];
	}
	
	// TODO: Add a main() method
	public static void main(String args[]) throws SerialPortException {
		SerialComm scom = new SerialComm("/dev/ttyS0");
		for(;;) {
			if (scom.available()) {
				char read = (char)scom.readByte();
				System.out.println(read);
			}
		}
		
	}
}
