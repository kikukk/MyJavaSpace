package StudyDemo.first_2020.test;

import java.awt.event.*;
import javax.swing.*;

public class MyMenuButton extends JToggleButton{
    private JPopupMenu menu = new JPopupMenu();
    private static int i=0;
    public MyMenuButton(){
        super();
        this.setText("▲");
        this.setHorizontalTextPosition(SwingConstants.RIGHT );

        JMenuItem editServerItem = new JMenuItem("修改服务器");
        JMenuItem exitItem = new JMenuItem("退出");


        menu.add(editServerItem);
        menu.add(exitItem);

//        exitItem.addMenu




        addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent arg0) {
                if(isSelected()){
                    setText("▼");
                    menu.show(MyMenuButton.this, 0, MyMenuButton.this.getHeight());
                }else{
                    setText("▲");
                    menu.setVisible(false);
                }

            }

        });
    }

    public void addMenu(JPopupMenu menu){
        this.menu=menu;
    }
}