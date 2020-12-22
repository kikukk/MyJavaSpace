package StudyDemo.first_2020.A09.ui;

import StudyDemo.first_2020.A09.Client;
import StudyDemo.first_2020.A09.Content;
import StudyDemo.first_2020.A09.User;
import StudyDemo.first_2020.A09.utils.MyBytes;
import StudyDemo.first_2020.A09.utils.NetUtils;

import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.InetAddress;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyledDocument;

/**
 * @author 14417
 */
public class ChatWindows extends JFrame {
    private JScrollPane panelHistory;
    private JTextPane textAreaHistory;
    /**
     * 插入按钮;清除按钮;插入图片按钮
     */
    private JButton buttonSend, btnRemove, btnPicture;
    /**
     *  文字输入框
     */
    private JTextArea textAreaInput;

    private static final NetUtils NET_UTILS = NetUtils.getInstance();
    private static final InetAddress LOCAL_ADDRESS = NET_UTILS.getLocalAddress();
    private static final int LOCAL_PORT = NET_UTILS.getLocalPort();

    InetAddress destIp;
    int destPort;

    private StyledDocument doc;

    public ChatWindows() {
        super();
        try { // 使用Windows的界面风格
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }

        initResources();
        initListener();
        initLayout();

        pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        textAreaInput.requestFocus();
    }


    private void initResources(){
        textAreaHistory = new JTextPane();
        textAreaHistory.setEditable(false);
        // 获得JTextPane的Document
        doc = textAreaHistory.getStyledDocument();
        panelHistory = new JScrollPane(textAreaHistory);
        panelHistory.setPreferredSize(new Dimension(400, 400));
        textAreaInput = new JTextArea("",3,18);
        buttonSend = new JButton("发送");
        btnRemove = new JButton("清空");
        btnPicture = new JButton("图片");
    }

    private void initListener(){
        // 插入文字的事件
        buttonSend.addActionListener(e -> {
            if(!"".equals(textAreaInput.getText())){
                String text = textAreaInput.getText();
                sendText(text);
                textAreaInput.setText("");
            }
        });

        // 清除事件
        btnRemove.addActionListener(e -> textAreaHistory.setText(""));

        // 插入图片事件
        btnPicture.addActionListener(arg0 -> {
            JFileChooser f = new JFileChooser(); // 查找文件
            f.showOpenDialog(null);
            File imgFile = f.getSelectedFile();
            if(imgFile.exists()){
                insertIcon(imgFile); // 插入图片
                sendPicture(imgFile);
            }
        });
    }

    private void initLayout(){
        // 放输入组件的容器
        Box box = Box.createHorizontalBox();
        Box box1 = Box.createVerticalBox();
        Box box2 = Box.createVerticalBox();
        box.add(Box.createHorizontalStrut(8));
        box.add(box1);
        box.add(Box.createHorizontalStrut(8));
        box.add(box2);
        box.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
        // 开始将所需组件加入容器
        box1.add(textAreaInput);
        box2.add(btnPicture);
        box2.add(Box.createVerticalStrut(8));
        box2.add(buttonSend);
        box2.add(Box.createVerticalStrut(8));
        box2.add(btnRemove);
        this.getRootPane().setDefaultButton(buttonSend);
        this.getContentPane().add(panelHistory);
        this.getContentPane().add(box, BorderLayout.SOUTH);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(false);
    }

    private void insertIcon(File file) {
        insert(new Content(new User(LOCAL_ADDRESS, LOCAL_PORT),"picture",null));
        // 设置插入位置
        textAreaHistory.setCaretPosition(doc.getLength());
        Image img = new ImageIcon(file.getPath()).getImage();
        img = img.getScaledInstance(img.getWidth(null)/10,img.getHeight(null)/10,Image.SCALE_DEFAULT);
        // 插入图片
        textAreaHistory.insertIcon(new ImageIcon(img));
        //换行
        insert(new Content());
    }

    public void insert(Content content) {
        try { // 插入文本
            doc.insertString(doc.getLength(), content.getContent() + "\n",new SimpleAttributeSet());
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
        switch(content.getType()){
            case "text":
                System.out.println("聊天记录框添加文本信息");
                break;
            case "picture":
                System.out.println("聊天记录框添加图片信息");
                break;
            case "nextline":
                break;
            default:
                System.out.println("聊天记录框添加信息other");
        }
    }

    public void setUser(User user){
        destIp = user.getAddress();
        destPort = user.getPort();
        this.setTitle("简单UDP聊天工具-聊天窗口"+destIp+":"+destPort);
    }

    private void sendText(String text){
        String strTmp = "contact\ttext\t"+text;
        NET_UTILS.send(new User(destIp,destPort),strTmp.getBytes());
        Client.contents.add(new Content(new User(LOCAL_ADDRESS, LOCAL_PORT),"text",new MyBytes(text.getBytes())));
        Client.manager.fireWorkspaceOpened();
        System.out.println(LOCAL_ADDRESS +String.valueOf(LOCAL_PORT)+"说"+text);
    }

    private void sendPicture(File file){
        MyBytes sendBytes = new MyBytes("contact\tpicture\t".getBytes());
        FileInputStream fis;
        byte[] tmpBytes = new byte[(int) file.length()];
        try {
            fis = new FileInputStream(file);
            fis.read(tmpBytes,0, (int) file.length());
        } catch (IOException e) {
            e.printStackTrace();
        }
        sendBytes.addBytes(tmpBytes);
        NET_UTILS.send(new User(destIp,destPort),sendBytes.getMybytes());
        System.out.println(LOCAL_ADDRESS +String.valueOf(LOCAL_PORT)+"发送了图片");
    }

    public static void main(String[] args){
        new ChatWindows();
    }


}
