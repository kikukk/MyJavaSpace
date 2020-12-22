package Application.KeyPress;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

/**
 * @author kikukk
 */
public class clientSend extends JFrame implements KeyListener {
    JLabel labelStrIp = new JLabel("IP地址");
    JLabel labelStrPort = new JLabel("端口");
    JTextField textFieldIp = new JTextField("127.0.0.1");
    JTextField textFieldPort = new JTextField("8800");
    JTextField textFieldInput  = new JTextField(20);
    JButton buttonConfirm = new JButton("确认");
    JPanel panelTop = new JPanel();
    JPanel panel = new JPanel();

    private clientSend(){
        buttonConfirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("confirm");
                if(connectToServer()){
                    textFieldIp.setEditable(false);
                    textFieldPort.setEditable(false);
                    buttonConfirm.setEnabled(false);
                    System.out.println("true");
                }
                else{
                    System.out.println("false");
                }
            }
        });




        panelTop.setLayout(new GridLayout(2,2));
        panelTop.add(labelStrIp);
        panelTop.add(textFieldIp);
        panelTop.add(labelStrPort);
        panelTop.add(textFieldPort);
        panel.setLayout(new FlowLayout());
        panel.add(panelTop);
        panel.add(buttonConfirm);
        panel.add(textFieldInput);
        this.setLayout(new BorderLayout());
        this.getContentPane().add(panel,BorderLayout.CENTER);
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setBounds(100,100,360,140);
    }


    public static void main(String[] args) {
        new clientSend();
    }

    private boolean connectToServer(){
        try {
            Socket socket = new Socket(InetAddress.getByName(textFieldIp.getText()), Integer.parseInt(textFieldPort.getText()));
        } catch (IOException e) {
            return false;
        }
        return true;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        System.out.println("KeyTyped"+e.getKeyChar());
    }

    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println("KeyPressed"+e.getKeyChar());
    }

    @Override
    public void keyReleased(KeyEvent e) {
        System.out.println("KeyReleased"+e.getKeyChar());
    }
}
