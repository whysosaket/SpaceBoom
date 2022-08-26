import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;


public class MainScreen extends JPanel implements KeyListener, ActionListener {

    ImageIcon jetImage  = new ImageIcon("./Assets/Images/jet.png");
    JLabel jetLabel;
    JLabel lost;
    Random random;

    Timer timer;

    int [][] backgroundArray = new int[9][3];
    int pointX=720,prevDig=3;
    private int points=0, health=200, speed=5, pointIncrement=1, jetSpeed=5,boost=0;
    boolean play=true;

    MainScreen(){
        this.setBackground(Color.black);
        this.setLayout(null);
        this.addKeyListener(this);
        random = new Random();

        //timer
        timer = new Timer(20, this);

        //healthTimer
        Timer healthTimer = new Timer(1500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(health<200){
                    health+=1;
                }
            }
        });
        healthTimer.start();


        jetLabel = new JLabel();
        jetLabel.setIcon(jetImage);
        defaultValues();


        this.add(jetLabel);
        this.setFocusable(true);
        timer.start();
    }


    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(play)
        switch (e.getKeyCode()){
            case 37-> jetLabel.setLocation(jetLabel.getX()-jetSpeed-boost,jetLabel.getY());
            case 39-> jetLabel.setLocation(jetLabel.getX()+jetSpeed+boost,jetLabel.getY());
            case 40-> jetLabel.setLocation(jetLabel.getX(),jetLabel.getY()+jetSpeed+boost);
            case 38-> jetLabel.setLocation(jetLabel.getX(),jetLabel.getY()-jetSpeed-boost);
            case 88-> boost = 10;
            }
        else if(e.getKeyCode()==32){
            play=true;
            defaultValues();
            timer.start();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode()==88) boost=0;
    }


    // background

    public void paint(Graphics g){
        super.paint(g);
        Graphics2D g2D = (Graphics2D) g;

        g2D.setPaint(Color.gray);
        g2D.fillOval(backgroundArray[0][0], backgroundArray[0][1],backgroundArray[0][2],backgroundArray[0][2]);
        g2D.fillOval(backgroundArray[1][0], backgroundArray[1][1],backgroundArray[1][2],backgroundArray[1][2]);
        g2D.fillOval(backgroundArray[2][0], backgroundArray[2][1],backgroundArray[2][2],backgroundArray[2][2]);
        g2D.fillOval(backgroundArray[3][0], backgroundArray[3][1],backgroundArray[3][2],backgroundArray[3][2]);
        g2D.fillOval(backgroundArray[4][0], backgroundArray[4][1],backgroundArray[4][2],backgroundArray[4][2]);
        g2D.fillOval(backgroundArray[5][0], backgroundArray[5][1],backgroundArray[5][2],backgroundArray[5][2]);
        g2D.fillOval(backgroundArray[6][0], backgroundArray[6][1],backgroundArray[6][2],backgroundArray[6][2]);
        g2D.fillOval(backgroundArray[7][0], backgroundArray[7][1],backgroundArray[7][2],backgroundArray[7][2]);
        g2D.fillOval(backgroundArray[8][0], backgroundArray[8][1],backgroundArray[8][2],backgroundArray[8][2]);


        g2D.setPaint(Color.white);
        g2D.setFont(new Font("Ink Free",Font.BOLD,30));
        g2D.drawString(""+points,pointX,50);

        g2D.setPaint(Color.red);
        g2D.drawString(""+health,720,80);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i=0;i<backgroundArray.length;i++){
            if(new Rectangle(jetLabel.getX(),jetLabel.getY(),33,33).intersects(new Rectangle(backgroundArray[i][0],backgroundArray[i][1],backgroundArray[i][2],backgroundArray[i][2])))
            {
                if(health>0) {
                    backgroundArray[i][1]=random.nextInt(-500,-20);
                    health-=backgroundArray[i][2];
                }
                else {
                    health=0;
                    play=false;
                    timer.stop();
                }
            }
            backgroundArray[i][1]+=speed;
            if(backgroundArray[i][1]>610) {
                backgroundArray[i][0]=random.nextInt(0,800);
                backgroundArray[i][1]=random.nextInt(-500,-20);
                backgroundArray[i][2]=random.nextInt(5,50);
            }
        }
        points+=pointIncrement;
        if((int)Math.log10(points)+1>prevDig){
            prevDig =(int) Math.log10(points)+1;
            pointX -= 20;
            speed+=2;
            jetSpeed+=2;
            pointIncrement*=5;
        }
        repaint();
    }

    void defaultValues(){
        health=200;
        pointX=720;
        prevDig=3;
        points=0;
        speed=5;
        pointIncrement=1;
        jetSpeed=5;
        boost=0;
        jetLabel.setBounds(350,500,60,60);
        //Filling background array
        for (int i = 0; i < backgroundArray.length; i++) {
            backgroundArray[i][0]=random.nextInt(0,800);
            backgroundArray[i][1]=random.nextInt(-500,-20);
            backgroundArray[i][2]=random.nextInt(5,50);
        }
    }
}
