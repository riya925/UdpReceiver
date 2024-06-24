import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class MyReceiver
{
	public static void main(String[] args)
	{
		try
		{
			DatagramSocket receiver=new DatagramSocket(3333);
			String msg="";
			byte b[]=new byte[1024];
			DatagramPacket pack=null;
			
			pack=new DatagramPacket(b,b.length);
			receiver.receive(pack);
			b=pack.getData();
			msg=new String(b);
			
			String data="";
			for(int i=0;i<msg.length();i++)
			{
				char ch=msg.charAt(i);
				if(ch>=65 && ch<=90)
				{
					ch=(char)(ch+32);
				}
				else if(ch>=97 && ch<=122)
				{
					ch=(char)(ch-32);
				}
				data=data+ch;
			}
			
			b=data.getBytes();
			InetAddress ip=InetAddress.getByName("localhost");
			pack=new DatagramPacket(b,b.length,ip,2222);
			receiver.send(pack);
			
			receiver.close();
		}
		catch(Exception e)
		{
			System.out.println("\n Receiver error : "+e.getMessage());
		}
	}
}