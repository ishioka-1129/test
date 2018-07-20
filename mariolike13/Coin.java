import java.applet.Applet;
import java.applet.AudioClip;

/*
 * Created on 2005/06/24
 *
 */

/**
 * @author mori
 *
 */
public class Coin extends Sprite {
    // �R�C�����Ƃ����Ƃ��̉�
    private AudioClip sound;

    public Coin(double x, double y, String fileName, Map map) {
        super(x, y, fileName, map);
        
        // �T�E���h�����[�h
        sound = Applet.newAudioClip(getClass().getResource("se/coin03.wav"));
    }

    public void update() {
    }
    
    /**
     * �T�E���h���Đ�
     */
    public void play() {
        sound.play();
    }
}
