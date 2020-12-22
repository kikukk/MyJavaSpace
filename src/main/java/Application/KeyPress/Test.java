package Application.KeyPress;

import java.applet.*;
import java.awt.*;
import java.awt.event.*;
/**
 * @author kikukk
 */
public class Test extends Applet implements KeyListener{
    int count =0;
    Button button = new Button();
    TextArea text = new TextArea(5,20);
    @Override
    public void init(){
        button.addKeyListener(this);
        add(button);
        add(text);
    }
    @Override
    public void keyPressed(KeyEvent e){
        int t = e.getKeyCode();
        if(t>=KeyEvent.VK_A&&t<=KeyEvent.VK_Z){
            text.append((char)t+" ");
            count++;
            if(count%10==0) {
                text.append("/n");
            }
        }
    }
    @Override
    public void keyTyped(KeyEvent e){}
    @Override
    public void keyReleased(KeyEvent e){}

    public static void main(String[] args) {
        new Test();
    }
}