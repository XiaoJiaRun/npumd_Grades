package npumd;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageInputStream;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class Verification_code {
        public Verification_code() throws IOException {
            String NPUMD="http://urp.mdit.edu.cn//validateCodeAction.do?random=0.42101651514554983";
            URL url=new URL(NPUMD);
            HttpURLConnection urlConnection=(HttpURLConnection) url.openConnection();
            urlConnection.connect();
            InputStream is=urlConnection.getInputStream();
            FileOutputStream fos=new FileOutputStream("two.jpg");
            byte[] buffer =new byte[1000000];
            int len;
            while((len=is.read(buffer))!=-1){
                fos.write(buffer,0,len);
            }
            is.close();
            fos.close();
            urlConnection.disconnect();
            File file = new File("two.jpg");
            BufferedImage bufImg = null;
            bufImg = ImageIO.read(file);

            Panel pane = new Panel(bufImg);
            JFrame frame = new JFrame();

            frame.add(pane);
            frame.setSize(300,150);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);

        }
        class Panel extends JPanel{
            Image img;
            public Panel(Image img){
                this.img = img;
                repaint();
            }
            protected void paintComponent(Graphics g) {

                super.paintComponent(g);
                g.drawImage(img,0 ,0 , getWidth(), getHeight(), this);

            }
    }

}

