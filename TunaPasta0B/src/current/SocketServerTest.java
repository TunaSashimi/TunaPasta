package current;
//好像这个是跟安卓程序相连接的socketsTest
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
public class SocketServerTest {
	private ServerSocket ss;
	private List<Socket> sockets;
	public SocketServerTest(){
		try {
			ss=new ServerSocket(8090);
			sockets=new ArrayList();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void startSerivce(){
		while (true) {
			try {
				Socket s = ss.accept();
				sockets.add(s);
				new ServerThread(s).start();
			} catch (Exception e) {
			}
		}
	}
	class ServerThread extends Thread{
		private Socket s;
		private BufferedReader br;
		public ServerThread(Socket s)
		{
			this.s=s;
			try {
				br=new BufferedReader(new InputStreamReader(s.getInputStream(),"gbk"));
				new PrintWriter(new OutputStreamWriter(s.getOutputStream(),"gbk"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		public void run(){
			String str;
			try {
				while (true){
				str = br.readLine();
				if(str.split(":")[1].equals("exit")){
					sockets.remove(s);
					sendMessageToAllClient(str.split(":")[0]+"退出聊天室");
				}
				System.out.println(s.getInetAddress()+" LoaclPort"+s.getLocalPort()+" Port"+s.getPort()+":"+str);
				
				sendMessageToAllClient(str);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		private void sendMessageToAllClient(String mesg) throws IOException{
			long times=System.currentTimeMillis();
			Date date=new Date(times);
			System.out.println(mesg+"\t["+date+"]");
			for (Socket temps : sockets) {
				PrintWriter pw=new PrintWriter(new OutputStreamWriter(temps.getOutputStream(),"gbk"));
				pw.println(mesg+"\t["+date+"]");
				pw.flush();
			}
		}
	}
	public static void main(String[] args) {
		new SocketServerTest().startSerivce();
	}
}
