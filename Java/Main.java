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
import java.io.File;
import java.io.IOException;
import javax.sound.sampled.*;
import javax.swing.event.*;

class Main {

  //declaring an array that will hold values in the Main class
  //array is 2D - one dimension holds the values and the other holds whether its being swapped
  static int[] array = new int[512/4];
  static int[] swap = new int[512/4];
  static int[] arraySorted = new int[512/4];
  static int arrayAccesses = 0; 
  static String sortType;
  static String sortProgress = "Sort in Progress";
  

  public static JPanel panel; 

  //public static JSlider slider;

  //static JSpinner spinner;
  
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

    panel.repaint();
 
		return array;
  }

  public int[] setArray(int[] arraySorted)
  {
    for(int i = 0; i<array.length; i++)
      {
        arraySorted[i] = i+1;
      }
    return arraySorted;
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
    //System.out.println("Painting");
    //casting my Graphics into a Graphics2D
    Graphics2D g2 = (Graphics2D) g;
    
    for(int i = array.length-1; i>-1; i--)
      {
        //setting colours
        String black = new String("000000");
        String red = new String("#FF00000");
        String green = new String("#228B22");
        String white = new String("#FFFFFF");
        String blue = new String("#00FFFF");

        
        int gradient = (0xffCC33FF - ((i/7) * 0x110000));

        String stringGradient = String.valueOf(gradient);
        
        if(array[i]*2 == 0)
          {
            System.out.println("L");
          }
        //testing rectangle
        g2.setColor(Color.decode(stringGradient));
        g2.fillRect(i*4, 360 - array[i]*2, 3, array[i]*2); 
        //adding white border
        g2.setColor(Color.decode(black));
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
          g2.setColor(Color.decode(black));
          g2.drawRect(i*4-1, 360 - array[i]*2-1, 5, array[i]*2);
        }
        else if(swap[i] == 3)
        {
          g2.setColor(Color.decode(green));
          g2.fillRect(i*4, 360 - array[i]*2, 3, array[i]*2);
          //adding white border
          g2.setColor(Color.decode(black));
          g2.drawRect(i*4-1, 360 - array[i]*2-1, 5, array[i]*2);
        }
        else if(swap[i] == 4)
        {
          g2.setColor(Color.decode(blue));
          g2.fillRect(i*4, 360 - array[i]*2, 3, array[i]*2);
          //adding white border
          g2.setColor(Color.decode(black));
          g2.drawRect(i*4-1, 360 - array[i]*2-1, 5, array[i]*2);
        }
        

        g2.setColor(Color.decode(white));
      }
  }
  public void drawHUD(Graphics g)
  {
    Graphics2D g2 = (Graphics2D) g;
    
    String white = new String("#FFFFFF");
    
    g.setColor(Color.decode(white));
    
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
    panel.setBackground(Color.black);
    f.getContentPane().add(panel);
    panel.validate();

    /*
    String[] sortingTypes = {"Bubble Sort", "Insertion Sort", "Quick Sort", "Selection Sort", "Bogo Sort"};

    
    spinner = new JSpinner(new SpinnerListModel(sortingTypes));
    spinner.setBounds(10, 10, 20, 40);
    panel.setLayout(null);
    f.add(spinner);
    */
    

    //NOTE - Could not figure out how to set the position of JSlider
   /* 
    //creating a slider to control the speed
    slider = new JSlider(0, 100, 50);
    slider.setPaintTrack(true);
    slider.setPaintTicks(true);
    slider.setPaintLabels(true);
 
    // set spacing
    slider.setMajorTickSpacing(50);
    slider.setMinorTickSpacing(5);

    //making panel vertical 
    slider.setOrientation(SwingConstants.VERTICAL);

    //adding slider to panel
    panel.add(slider, BorderLayout.SOUTH);
    
*/

    

    //randomizing and setting the arrays
    array = arrayRandomize(array);
    swap = swapSet(swap);
    arraySorted = setArray(arraySorted);
    panel.repaint();

    //calling methods from EDT
    SwingUtilities.invokeLater(new Runnable()
    {
      @Override
      public void run()
      {
        try
          {
            //Sorting Algorithms
            //SortingAlgorithms.bubbleSort();
            //SortingAlgorithms.selectionSort();
            SortingAlgorithms.insertionSort();
            //SortingAlgorithms.quickSort(0, 127);
            //SortingALgorithms.mergeSort(array, 0, 127);
            //SortingAlgorithms.bogoSort();
            //SortingAlgorithms.cocktailSort();
            arrayCheck(); 
          }
        catch(InterruptedException e)
          {
            e.printStackTrace();
          }
      }
    });
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

  public static void arrayCheck() throws InterruptedException
  {
    System.out.println("wowee");
    
    for(int i = 0; i<array.length; i++)
      {
        Thread.sleep(10);
        panel.paintImmediately(0, 0, panel.getWidth(), panel.getHeight());
        if(array[i] == arraySorted[i])
        {
          swap[i] = 3;
          panel.repaint();
        }
      }
  }
  
/*
  //sound effect when sorting
  public static void beep() throws Exception
  {
    try(File file = new File("beep.wav")){
        AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
        Clip clip = AudioSystem.getClip();
        clip.open(audioStream);
    
        clip.start();
    
        Thread.sleep(clip.getMicrosecondLength()/1000);
      }
    catch(Exception e)
      {
        e.printStackTrace();
      }

    }
*/
  
}
