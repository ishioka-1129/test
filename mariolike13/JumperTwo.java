import java.applet.Applet;
import java.applet.AudioClip;

/*
 * Created on 2005/07/03
 *
 */

/**
 * ��i�W�����v�A�C�e��
 * @author mori
 *
 */
public class JumperTwo extends Sprite {
    // �A�C�e�����Ƃ����Ƃ��̉�
    private AudioClip sound;

    public JumperTwo(double x, double y, String fileName, Map map) {
        super(x, y, fileName, map);
        
        // �T�E���h�����[�h
        sound = Applet.newAudioClip(getClass().getResource("se/pyoro57_b.wav"));
    }

    public void update() {
    }
    
    /**
     * �T�E���h���Đ�
     */
    public void play() {
        sound.play();
    }
    
    /**
     * �A�C�e�����g��
     */
    public void use(Player player) {
        // �v���C���[����i�W�����v�\�ɁI
        player.setJumperTwo(true);
    }
}
