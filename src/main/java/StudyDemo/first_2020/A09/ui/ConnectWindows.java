package StudyDemo.first_2020.A09.ui;

import StudyDemo.first_2020.A09.MessageTool.MessageListener;
import StudyDemo.first_2020.A09.MessageTool.MessageManager;
import StudyDemo.first_2020.A09.utils.NetUtils;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @ClassName Windows
 * @Description TODO
 * @date 2020/9/29 8:51
 */
public class ConnectWindows extends JFrame {
    JLabel labelDestIp = new JLabel("I P",SwingConstants.CENTER);
    JLabel labelDestPort = new JLabel("PORT",SwingConstants.CENTER);

    public JTextField textDestIp = new JTextField(15);
    public JTextField textDestPort = new JTextField(15);

    public JButton buttonSingle = new JButton("个人");
    JButton buttonMany = new JButton("多人");

    JPanel panel = new JPanel();
    static NetUtils netUtils = NetUtils.getInstance();
    public static MessageManager manager;

    public ConnectWindows() {
        super("简单UDP聊天工具-连接窗口");
//        textDestIp.setText("192.168.1.111");
        textDestIp.setText("127.0.0.1");
        textDestPort.setText("8811");

        buttonSingle.addActionListener(e -> {
            //连接窗口点击事件
            String destIp = textDestIp.getText();
            int destPort = Integer.parseInt(textDestPort.getText());
            byte[] data = "connect\tgetConnect".getBytes();
            //创建数据报，包含发送的数据信息
            DatagramPacket packet;
            try {
                packet = new DatagramPacket(data, data.length, InetAddress.getByName(destIp), destPort);
                netUtils.getSocket().send(packet);
                netUtils.getSocket().connect(InetAddress.getByName(destIp), destPort);
            } catch (IOException unknownHostException) {
                unknownHostException.printStackTrace();
            }

            manager = MessageManager.getInstance();
            manager.addMessageListener(new MessageListener());// 给门1增加监听器
        });

        panel.setLayout(new GridLayout(3,2,5,10));
        panel.add(labelDestIp);
        panel.add(textDestIp);
        panel.add(labelDestPort);
        panel.add(textDestPort);
        panel.add(buttonSingle);
        panel.add(buttonMany);

        this.setLayout(new FlowLayout());
        this.add(panel);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBounds(500,500,450,170);
    }
}
