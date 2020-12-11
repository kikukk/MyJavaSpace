package StudyDemo.wangke.Demo3;

import java.applet.Applet;
import java.applet.AudioClip;
import java.io.File;
import java.net.MalformedURLException;
import java.util.Scanner;

public class Demo3_3 {
    public static void main(String[] args)throws MalformedURLException {
        Scanner input = new Scanner(System.in);
         //播放音乐代码
        File sound1 = new File("sounds//xxxx.wav");
        AudioClip sound_choose = Applet.newAudioClip(sound1.toURI().toURL());
        sound_choose.play();//播放音乐

        //需要让程序暂停下，以便播放音乐
        System.out.println("请输入任意键继续！");
        input.nextLine();
    }
}
