package network;

public class TCPServer {
    public static void main(String[] args) throws java.io.IOException {
        java.net.ServerSocket ss = new java.net.ServerSocket(8888);
        while (true) {
            java.net.Socket so = ss.accept();
            java.io.DataInputStream dis = new java.io.DataInputStream(so.getInputStream());
            java.io.DataOutputStream dos = new java.io.DataOutputStream(so.getOutputStream());
            String s;
            if ((s = dis.readUTF()) != null) {        //先读后写~
                System.out.println(s);
                System.out.println("from:	" + so.getInetAddress());
                System.out.println("Port:		" + so.getPort());
            }
            dos.writeUTF("初始化处理中");
            dis.close();
            dos.close();
            so.close();
        }
    }
}