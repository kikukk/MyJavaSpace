package StudyDemo.first_2020.A09;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

/**
 * @ClassName test
 * @Description TODO
 * @date 2020/10/5 16:05
 */

public class test {
    public static void main(String[] args) throws BadLocationException {
        JTextPane textPane = new JTextPane();
        StyledDocument doc = (StyledDocument) textPane.getDocument();
        Style style = doc.addStyle("StyleName", null);
        StyleConstants.setIcon(style,new ImageIcon("imagefile"));
        doc.insertString(doc.getLength(), "ignored text", style);


    }
}
