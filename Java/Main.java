//Array Sort Visualizer
//ICS3U
//EL & NB
//May 12, 2022 - June 8, 2022

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
import java.awt.Color;

class Main {

//declaring an array that will hold values in the Main class
//array is 2D - one dimension holds the values and the other holds whether its being swapped
static int[] array = new int[512/4];
static int[] swap = new int[512/4];
static int[] arraySorted = new int[512/4];
static int arrayAccesses = 0; 
static String sortType;
static String sortProgress = "Sort in Progress";
static String colourTheme;


public static JPanel panel; 

//public static JSlider slider;

//static JSpinner spinner;

public static void numberSpinner()
{
  JFrame f=new JFrame("Number of Elements");    
  SpinnerModel value =  
           new SpinnerNumberModel(128, 10, 1500,  5); 
  JSpinner spinner = new JSpinner(value);   
          spinner.setBounds(100,100,50,30);    
          f.add(spinner);    
          f.setSize(300,300);    
          f.setLayout(null);    
          f.setVisible(true);     
}

public int[] arrayRandomize(int[] array) throws InterruptedException
{
  //setting the elements of the array to ordered numbers
  for(int i = 0; i<array.length; i++)
    {
      array[i] = i+1;
    }
  panel.paintImmediately(panel.getBounds());
  Thread.sleep(1000);

  sortType = "Randomizing";
  sortProgress = "Randomizing";
  //randomizing
  Random rand = new Random();  

		for (int i=0; i<array.length; i++) 
    {
		    int position = rand.nextInt(array.length);
		    int temp = array[i];
		    array[i] = array[position];
		    array[position] = temp;
      panel.paintImmediately(panel.getBounds());
      Thread.sleep(5);
		  }

  System.out.println(Arrays.toString(array));

  Thread.sleep(3000);

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

      double jump = 360.0/(50000*1.0);
      Color[] colors = new Color[128];
      for(int j = 0; j< array.length; j++)
        {
          colors[j] = Color.getHSBColor((float) ((jump*j)), 1.0f, 1.0f );
        }

      //setting color based on user input
      if(colourTheme != null && colourTheme.equals("White"))
      {
        g2.setColor(Color.decode(white));
      }
      else{
        if(array[i]-1 != -1)
        {
          g2.setColor(colors[array[i]-1]);
        }
      }
      
      g2.fillRect(i*4, 360 - array[i]*2, 3, array[i]*2); 
      //adding black border
      g2.setColor(Color.decode(black));
      g2.drawRect(i*4-1, 360 - array[i]*2-1, 4, array[i]*2);
      

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
        g2.drawRect(i*4-1, 360 - array[i]*2-1, 4, array[i]*2);
      }
      else if(swap[i] == 3)
      {
        g2.setColor(Color.decode(green));
        g2.fillRect(i*4, 360 - array[i]*2, 3, array[i]*2);
        //adding white border
        g2.setColor(Color.decode(black));
        g2.drawRect(i*4-1, 360 - array[i]*2-1, 4, array[i]*2);
      }
      else if(swap[i] == 4)
      {
        g2.setColor(Color.decode(blue));
        g2.fillRect(i*4, 360 - array[i]*2, 3, array[i]*2);
        //adding white border
        g2.setColor(Color.decode(black));
        g2.drawRect(i*4-1, 360 - array[i]*2-1, 4, array[i]*2);
      }
      

      g2.setColor(Color.decode(white));
    }
}
public void drawHUD(Graphics g)
{
  Graphics2D g2 = (Graphics2D) g;
  
  String white = new String("#FFFFFF");
  g.setFont(new Font("Courier", Font.PLAIN, 11)); 
  
  g.setColor(Color.decode(white));
  
  //drawing sorting method
  g.drawString(sortType + "", 10, 20);
  
  //drawing array accesses
  g.drawString("Array Accesses: " + arrayAccesses, 10, 40);

  //drawing sorting progress
  g.drawString(sortProgress, 10, 60);
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
  //f.pack();
  f.setLocationRelativeTo(null);
  f.setVisible(true);



  //calling methods from EDT
  SwingUtilities.invokeLater(new Runnable()
  {
    @Override
    public void run()
    {
     try
       {
         int inputValue = Integer.parseInt(JOptionPane.showInputDialog(null, "Please input a value", "Number of Elements", JOptionPane.DEFAULT_OPTION));
         
         //put in method later
         Object[] AlgoOptions = {"Bubble Sort", "Selection Sort", "Insertion Sort", "Quick Sort", "Merge Sort", "Cocktail Sort", "Bogo Sort"};
  Object AlgoChoice = JOptionPane.showInputDialog(null, "Select an Algorithm", "Algorithm Selection", JOptionPane.ERROR_MESSAGE, null, AlgoOptions, AlgoOptions[0]); 
         String strAlgoChoice = (String) AlgoChoice;

         Object[] colourThemes = {"White", "Rainbow"};
        colourTheme = (String) JOptionPane.showInputDialog(null, "Select a Colour Theme", "Theme Selection", JOptionPane.ERROR_MESSAGE, null, colourThemes, colourThemes[0]); 
         
         //randomizing and setting the arrays
          array = arrayRandomize(array);
          swap = swapSet(swap);
          arraySorted = setArray(arraySorted);
          panel.repaint();

          sortProgress = "Sort in Progress";
//          //Sorting Algorithms
         if(strAlgoChoice.equals("Bubble Sort"))
         {
           SortingAlgorithms.bubbleSort();
         } 
         else if(strAlgoChoice.equals("Selection Sort"))
         {
           SortingAlgorithms.selectionSort();
         }
         else if(strAlgoChoice.equals("Insertion Sort"))
         {
           SortingAlgorithms.insertionSort();
         }
         else if(strAlgoChoice.equals("Quick Sort"))
         {
           SortingAlgorithms.quickSort(0, 127);
         }
         else if(strAlgoChoice.equals("Merge Sort"))
         {
           SortingAlgorithms.mergeSort(array, 0, 127); 
         }
         else if(strAlgoChoice.equals("Cocktail Sort"))
         {
           SortingAlgorithms.cocktailSort();
         }
         else if(strAlgoChoice.equals("Bogo Sort"))
         {
           SortingAlgorithms.bogoSort();
         } 
         
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
  System.out.println("checking array like rnrn");
  
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
public static void beep(String filename)
{
  try
  {
      Clip clip = AudioSystem.getClip();
      clip.open(AudioSystem.getAudioInputStream(new File(filename)));
      clip.start();
  }
  catch (Exception exc)
  {
      exc.printStackTrace(System.out);
  }
}
}

