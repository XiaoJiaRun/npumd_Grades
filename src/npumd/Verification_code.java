package npumd;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageInputStream;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Verification_code {
        public Verification_code() throws IOException {

            String strurl="http://urp.mdit.edu.cn/loginAction.do";
            String Login_html="";
            String regStr="/validateCodeAction.do.random=...................";
            try {
                URL url=new URL(strurl);
                URLConnection conn=url.openConnection();
                java.io.InputStream is=conn.getInputStream();
                BufferedReader br=new BufferedReader(new InputStreamReader(is));
                String line=null;
                while((line=br.readLine())!=null){
                    Login_html+=line;
                }
                System.out.println(Login_html);
                Pattern pattern=Pattern.compile(regStr);
                Matcher matcher=pattern.matcher(Login_html);
                while(matcher.find()) {
                    System.out.println(matcher.group());
                }
                br.close();
            } catch (Exception e) {

                e.printStackTrace();
            }



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

