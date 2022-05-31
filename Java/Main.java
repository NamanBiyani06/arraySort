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
  

  public JPanel panel; 

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
    System.out.println("Painting");
    //casting my Graphics into a Graphics2D
    Graphics2D g2 = (Graphics2D) g;
    
    for(int i = 0; i<array.length; i++)
      {
        //setting colours
        String black = new String("000000");
        String red = new String("#FF00000");
        String green = new String("#228B22");
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
        else if(swap[i] == 3)
        {
          g2.setColor(Color.decode(green));
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

    //Sorting Algorithms
    //bubbleSort();
    //selectionSort();
    //insertionSort();
    //quickSort(0, 127);
    //bogoSort();
    cocktailSort();
    arrayCheck();
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

  public void arrayCheck() throws InterruptedException
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
  
  //SORTING ALGORITHMS
  //Bubble Sort
  //Timer Complexity: (O(n^2))
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
          //arrayCheck();
          sortProgress = " Sorted!";
          System.out.println("Sorted!");
          ((Timer) e.getSource()).stop();
        }
        if(j>=array.length-1-i)
        {
          j = 0;
          i++;
        }
        //beep();
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
  } //okok i'll see if i can make merge sort work with thread.sleep/another time method
//start typing in comments so run doesnt break
  //ill show nirev quick sort 
  //Cocktail Sort
  public void cocktailSort() throws InterruptedException
  {
    sortType = "Cocktail Sort";

    Timer timer = new Timer(5, null);
    ActionListener timerAction = new ActionListener()
    {
      public void actionPerformed(ActionEvent e)
        {

    int startingIndex = 0;
    int endingIndex = array.length - 1;
    boolean sorted = false;
    int temp;

    while(sorted == false)
      {
        sorted = true;
        for(int i = 0; i<endingIndex; i++)
        {
          swap[i] = 2;
          if(array[i] > array[i+1])
          {
            temp = array[i];
            array[i] = array[i+1];
            array[i+1] = temp;
            sorted = false;
            swap[i] = 2;
            swap[i+1] = 2;
            panel.paintImmediately(panel.getBounds());
          }
          swap[i] = 1;
          swap[i+1] = 1;
        }
      endingIndex--;
  
      if(sorted == true)
      {
        try
        {
          arrayCheck();
        }
        catch(InterruptedException e2)
        {
          e2.printStackTrace();
        }
        sortProgress = " Sorted!";
        System.out.println("Sorted!");
        panel.repaint(); 
        ((Timer) e.getSource()).stop();
      }
  
      sorted = true;

      for(int i = endingIndex; i>0; i--)
        {
          swap[i] = 2;
          if(array[i] < array[i-1])
          {
            temp = array[i];
            array[i] = array[i-1];
            array[i-1] = temp;
            sorted = false;
            swap[i] = 2;
            swap[i+1] = 2;
            panel.paintImmediately(panel.getBounds());
          }
          swap[i] = 1;
          swap[i+1] = 1;
        }
      startingIndex++;
      }
      }
    };
    timer.addActionListener(timerAction);
    timer.start();
  }
  //Selection Sort
  //Timer Complexity: (O(n^2))
  public void selectionSort() 
  {
    sortType = "Selection Sort";
    Timer timer = new Timer(20, null);
    ActionListener timerAction = new ActionListener()
      {
        int start = 0;
        public void actionPerformed(ActionEvent e)
        {
          int minValue = start;
          if (start==array.length-1) 
          {
            try{
            arrayCheck();
            }
            catch(InterruptedException e2)
              {
                e2.printStackTrace();
              }
            sortProgress = " Sorted!";
            System.out.println(" Sorted!");
              panel.repaint();
            ((Timer) e.getSource()).stop();
          }
          for (int i = start; i<(array.length-1); i++)
            {
              for (int j = start; j<array.length; j++)
              {
                if (array[j]<array[minValue])
                {
                  minValue = j;
                }
              }  
                swap[minValue] = 2;
                swap[start] = 2;
                int temp = array[minValue];
                array[minValue] = array[start];
                array[start] = temp;
                arrayAccesses+=2; 
                panel.repaint();
              
            }
              
              swap[start] = 1;
              if (minValue!=array.length-1)
              {
                swap[minValue] = 1;
              }
              panel.repaint();
          start++;

        }
      };
    timer.addActionListener(timerAction);
    timer.start();
 }      

  //Quick Sort
  //rewrite
  //Time Complexity: (O(nLog(n)))
  public void quickSort(int low, int high) throws InterruptedException
  {
    sortType = "Quick Sort";

    if(low<high)
    {
      int partition = partition(low, high);

      quickSort(low, partition - 1);
      quickSort(partition + 1, high);
    }
  }

  //partition for quick sort
  public int partition(int low, int high) throws InterruptedException
  {
    int pivot = array[high];

    int i = (low-1);

    for(int j = low; j<= high - 1; j++)
      {
        if(array[j] <= pivot)
        {
          i++;

          int temp = array[i];
          array[i] = array[j];
          array[j] = temp;
          panel.paintImmediately(panel.getBounds()); 
          Thread.sleep(50);
        }
      }
    int temp_two = array[i + 1];
    array[i+1] = array[high];
    array[high] = temp_two;
    return (i + 1);
    
  }
  
 
  
  public void insertionSort() 
  {
    
    sortType = "Insertion Sort";
    Timer timer = new Timer(5, null);
    ActionListener timerAction = new ActionListener()
    {
      public void actionPerformed(ActionEvent e)
        {
          for (int i = 1; i<=array.length; i++)
            {
              int j = i-1;
              int k = i;
              if (i==array.length)
              {
                try
                  {
                    arrayCheck();
                  }
                catch(InterruptedException e2)
                  {
                    e2.printStackTrace();
                  }
                sortProgress = " Sorted!";
                System.out.println(" Sorted!");
                ((Timer) e.getSource()).stop();
              }
              while (array[k]<array[j])
              {
                swap[k] = 2;
                swap[j] = 2;
                int temp = array[j];
                array[j] = array[k];
                array[k] = temp;
                arrayAccesses+=2; 
                panel.paintImmediately(0, 0, panel.getWidth(), panel.getHeight());
                j--;
                k--;
                if (j<0)
                {
                  break;
                }
                  swap[k] = 1;
                  swap[j] = 1;
                  panel.paintImmediately(0, 0, panel.getWidth(), panel.getHeight());

              }
            }

        }
    };
    timer.addActionListener(timerAction);
    timer.start();
  }

  //Bogo Sort
  //Time Complexity: O((N-1)*N!)
  public void bogoSort() throws InterruptedException
  {
    sortType = "Bogo Sort";
    Timer timer = new Timer(25, null);

    ActionListener timerAction = new ActionListener()
      {
        public void actionPerformed(ActionEvent e)
        {
          boolean sorted = true;

          for(int i = 0; i<array.length-1; i++)
            {
              if(array[i]>array[i+1])
              {
                sorted = false;
                for(int j = 0; j<array.length; j++)
                  {
                    swap[j] = 1;
                  }
                panel.repaint();
              }
              else{
                swap[i] = 2;
                swap[i+1] = 2;
                panel.repaint();
              }
            }

          if(sorted)
          {
            sortProgress = " Sorted!";
            System.out.println(" Sorted!");
            ((Timer) e.getSource()).stop();
          }
          else 
          {
            //randomizing
            Random rand = new Random();  
         
        		for (int i=0; i<array.length; i++) 
              {
                arrayAccesses++;
        		    int position = rand.nextInt(array.length);
        		    int temp = array[i];
        		    array[i] = array[position];
        		    array[position] = temp;
        		  }
            panel.repaint();
          }
          
        }
      };
    timer.addActionListener(timerAction);
    timer.start();
  }
}
