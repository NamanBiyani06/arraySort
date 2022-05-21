//Array Sort Visualizer
//ICS3U
//EL & NB
//May 12, 2022

//imports
import java.util.*;
import java.io.*;
import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;
import javax.swing.Timer;
import java.awt.event.*;


class Main {

  //declaring an array that will hold values in the Main class
  //array is 2D - one dimension holds the values and the other holds whether its being swapped
  static int[] array = new int[512/4];
  static int[] swap = new int[512/4];
  static int arrayAccesses = 0; 
  static String sortType;
  static String sortProgress = "Sort in Progress";

  public JPanel panel; 
  
  public int[] arrayRandomize(int[] array)
  {
    //setting the elements of the array to ordered numbers
    for(int i = 0; i<array.length; i++)
      {
        array[i] = i+1;
      }

    //randomizing
    Random rand = new Random();  
 
		for (int i=0; i<array.length; i++) 
      {
		    int position = rand.nextInt(array.length);
		    int temp = array[i];
		    array[i] = array[position];
		    array[position] = temp;
		  }

    System.out.println(Arrays.toString(array));
 
		return array;
  }

  public int[] swapSet(int[] swap)
  {
    //setting all elements of swap to 1
    //'1' represents that the element is not being swapped
    //'2' represents that the element is being swapped
    for(int i = 0; i<swap.length; i++)
      {
        swap[i] = 1;
      }

    return swap;
  }

  public void drawArray(Graphics g)
  {
    //casting my Graphics into a Graphics2D
    Graphics2D g2 = (Graphics2D) g;
    
    for(int i = 0; i<array.length; i++)
      {
        //setting colours
        String black = new String("000000");
        String red = new String("#FF00000");
        String white = new String("#FFFFFF");

        if(array[i]*2 == 0)
          {
            System.out.println("L");
          }
        //testing rectangle
        g2.setColor(Color.decode(black));
        g2.fillRect(i*4, 360 - array[i]*2, 3, array[i]*2); 
        //adding white border
        g2.setColor(Color.decode(white));
        g2.drawRect(i*4-1, 360 - array[i]*2-1, 5, array[i]*2);
        

        if(swap[i] == 2)
        {
          if(array[i]*2 == 0)
          {
            System.out.println("L");
          }
          g2.setColor(Color.decode(red));
          g2.fillRect(i*4, 360 - array[i]*2, 3, array[i]*2);
          //adding white border
          g2.setColor(Color.decode(white));
          g2.drawRect(i*4-1, 360 - array[i]*2-1, 5, array[i]*2);
        }
        

        g2.setColor(Color.decode(black));
      }
  }
  public void drawHUD(Graphics g)
  {
    Graphics2D g2 = (Graphics2D) g;

    //drawing sorting method
    g.drawString(sortType + "  -", 10, 20);
    
    //drawing array accesses
    g.drawString("Array Accesses: " + arrayAccesses + "  -", 103, 20);

    //drawing sorting progress
    g.drawString(sortProgress, 254, 20);
  }

  //main in which I can use static variables
  public Main() throws InterruptedException
  {
    // Creating the JFrame
    JFrame f = new JFrame("Array Sorting Visualizer");
    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    f.setSize(512, 380);
    f.setVisible(true);

    panel = new JPanel() {
      @Override
      //paint section that is recalled everytime panel.repaint() is called
      public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        super.paint(g);
        drawArray(g);
        drawHUD(g);
      }
    };
    f.getContentPane().add(panel);
    panel.validate();

    //randomizing and setting the arrays
    array = arrayRandomize(array);
    swap = swapSet(swap);
    panel.repaint();

    bubbleSort();
  }

  //diverting to an alternate main so I can use static variables
  public static void main(String[] args) throws InterruptedException 
  {
    SwingUtilities.invokeLater(new Runnable() 
    {
      @Override
      public void run() 
      {
        try 
        {
          new Main();
        } 
        catch (InterruptedException e) 
        {
          e.printStackTrace();
        }
      }
    });
  }

  //SORTING ALGORITHMS
  public void bubbleSort() throws InterruptedException
  {
    sortType = "Bubble Sort";
    Timer timer = new Timer(1, null);
    ActionListener timerAction  = new ActionListener()
    {
      int i = 0; 
      int j = 0;
      @Override
      public void actionPerformed(ActionEvent e)
      {
        if(i>=array.length-1)
        {
          sortProgress = " Sorted!";
          System.out.println("Sorted!");
          ((Timer) e.getSource()).stop();
        }
        if(j>=array.length-1-i)
        {
          j = 0;
          i++;
        }
        if(array[j] > array[j+1])
        {
          swap[j] = 2;
          swap[j+1] = 2;
          int temp = array[j];
          array[j] = array[j+1];
          array[j+1] = temp;
          arrayAccesses+=2;
        }
        panel.repaint();
        //EVAN - can you figure out why some things stay red after theyre swapped
        //nevermind fixed it but idk how. I didnt change anything and it worked
        //after a point(around half way through) the red line starts blinking.
        swap[j] = 1;
        j++;
        if(j!=512/4-1)
        {
          swap[j+1] = 1; 
        }
        panel.repaint();
      }
    };
    timer.addActionListener(timerAction);
    timer.start();
  }

  public void selectionSort() throws InterruptedException
  {
    sortType = "Selection Sort";
    Timer timer = new Timer(1, null);
    ActionListener timerAction = new ActionListener()
      {
        
      };
  }
}
