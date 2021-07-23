package network;

public class TCPClient {
    public static void main(String[] args) throws java.net.UnknownHostException, java.io.IOException {
        java.net.Socket so = new java.net.Socket("192.168.0.149", 8888);
        java.io.DataInputStream dis = new java.io.DataInputStream(so.getInputStream());
        java.io.DataOutputStream dos = new java.io.DataOutputStream(so.getOutputStream());
        dos.writeUTF("请求登陆");
        String s;
        if ((s = dis.readUTF()) != null) {        //先写后读~
            System.out.println(s);
        }
        dos.close();
        dis.close();
        so.close();
    }
}

