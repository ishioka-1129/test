import java.applet.Applet;
import java.applet.AudioClip;

/*
 * Created on 2005/07/03
 *
 */

/**
 * �����A�C�e��
 * @author mori
 *
 */
public class Accelerator extends Sprite {
    // �A�C�e�����Ƃ����Ƃ��̉�
    private AudioClip sound;

    public Accelerator(double x, double y, String fileName, Map map) {
        super(x, y, fileName, map);
        
        // �T�E���h�����[�h
        sound = Applet.newAudioClip(getClass().getResource("se/chari13_c.wav"));
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
        // �v���C���[�̃X�s�[�h���A�b�v�I
        player.setSpeed(player.getSpeed() * 2);
    }
}
