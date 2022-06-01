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

class SortingAlgorithms
  {
    //SORTING ALGORITHMS
    //Bubble Sort
    //Timer Complexity: (O(n^2))
    public static void bubbleSort() throws InterruptedException
    {
      Main.sortType = "Bubble Sort";
      Timer timer = new Timer(1, null);
      ActionListener timerAction  = new ActionListener()
      {
        int i = 0; 
        int j = 0;
        @Override
        public void actionPerformed(ActionEvent e)
        {
          if(i>=Main.array.length-1)
          {
            //arrayCheck();
            Main.sortProgress = " Sorted!";
            System.out.println("Sorted!");
            ((Timer) e.getSource()).stop();
          }
          if(j>=Main.array.length-1-i)
          {
            j = 0;
            i++;
          }
          //beep();
          if(Main.array[j] > Main.array[j+1])
          {
            Main.swap[j] = 2;
            Main.swap[j+1] = 2;
            int temp = Main.array[j];
            Main.array[j] = Main.array[j+1];
            Main.array[j+1] = temp;
            Main.arrayAccesses+=2;
          }
          Main.panel.repaint();
          Main.swap[j] = 1;
          j++;
          if(j!=512/4-1)
          {
            Main.swap[j+1] = 1; 
          }
          Main.panel.repaint();
        }
      };
      timer.addActionListener(timerAction);
      timer.start();
    }
    //Cocktail Sort
    public static void cocktailSort() throws InterruptedException
    {
      Main.sortType = "Cocktail Sort";
  
      Timer timer = new Timer(5, null);
      ActionListener timerAction = new ActionListener()
      {
        public void actionPerformed(ActionEvent e)
          {
  
      int startingIndex = 0;
      int endingIndex = Main.array.length - 1;
      boolean sorted = false;
      int temp;
  
      while(sorted == false)
        {
          sorted = true;
          for(int i = 0; i<endingIndex; i++)
          {
            Main.swap[i] = 2;
            if(Main.array[i] > Main.array[i+1])
            {
              temp = Main.array[i];
              Main.array[i] = Main.array[i+1];
              Main.array[i+1] = temp;
              sorted = false;
              Main.swap[i] = 2;
              Main.swap[i+1] = 2;
              Main.panel.paintImmediately(Main.panel.getBounds());
            }
            Main.swap[i] = 1;
            Main.swap[i+1] = 1;
          }
        endingIndex--;
    
        if(sorted == true)
        {
          try
          {
            Main.arrayCheck();
          }
          catch(InterruptedException e2)
          {
            e2.printStackTrace();
          }
          Main.sortProgress = " Sorted!";
          System.out.println("Sorted!");
          Main.panel.repaint(); 
          ((Timer) e.getSource()).stop();
        }
    
        sorted = true;
  
        for(int i = endingIndex; i>0; i--)
          {
            Main.swap[i] = 2;
            if(Main.array[i] < Main.array[i-1])
            {
              temp = Main.array[i];
              Main.array[i] = Main.array[i-1];
              Main.array[i-1] = temp;
              sorted = false;
              Main.swap[i] = 2;
              Main.swap[i+1] = 2;
              Main.panel.paintImmediately(Main.panel.getBounds());
            }
            Main.swap[i] = 1;
            Main.swap[i+1] = 1;
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
    public static void selectionSort() 
    {
      Main.sortType = "Selection Sort";
      Timer timer = new Timer(20, null);
      ActionListener timerAction = new ActionListener()
        {
          int start = 0;
          public void actionPerformed(ActionEvent e)
          {
            int minValue = start;
            if (start==Main.array.length-1) 
            {
              try{
              Main.arrayCheck();
              }
              catch(InterruptedException e2)
                {
                  e2.printStackTrace();
                }
              Main.sortProgress = " Sorted!";
              System.out.println(" Sorted!");
                Main.panel.repaint();
              ((Timer) e.getSource()).stop();
            }
            for (int i = start; i<(Main.array.length-1); i++)
              {
                for (int j = start; j<Main.array.length; j++)
                {
                  if (Main.array[j]<Main.array[minValue])
                  {
                    minValue = j;
                  }
                }  
                  Main.swap[minValue] = 2;
                  Main.swap[start] = 2;
                  int temp = Main.array[minValue];
                  Main.array[minValue] = Main.array[start];
                  Main.array[start] = temp;
                  Main.arrayAccesses+=2; 
                  Main.panel.repaint();
                
              }
                
                Main.swap[start] = 1;
                if (minValue!=Main.array.length-1)
                {
                  Main.swap[minValue] = 1;
                }
                Main.panel.repaint();
            start++;
  
          }
        };
      timer.addActionListener(timerAction);
      timer.start();
   }      
    // 
    //Quick Sort
    //rewrite
    //Time Complexity: (O(nLog(n)))
    public static void quickSort(int low, int high) throws InterruptedException
    {
      Main.sortType = "Quick Sort";
  
      if(low<high)
      {
        int partition = partition(low, high);
  
        quickSort(low, partition - 1);
        quickSort(partition + 1, high);
      }
    }
  
    //partition for quick sort
    public static int partition(int low, int high) throws InterruptedException
    {
      int pivot = Main.array[high];
  
      Main.swap[high] = 4;
      
      int i = (low-1);
  
      for(int j = low; j<= high - 1; j++)
        {
          if(Main.array[j] <= pivot)
          {
            i++;
  
            int temp = Main.array[i];
            Main.array[i] = Main.array[j];
            Main.array[j] = temp;
            Main.swap[i] = 2;
            Main.swap[j] = 2;
            Main.panel.paintImmediately(Main.panel.getBounds()); 
            Thread.sleep(50);
          }
          Main.swap[i+1] = 1;
          Main.swap[j] = 1;
        }
      int temp_two = Main.array[i + 1];
      Main.array[i+1] = Main.array[high];
      Main.array[high] = temp_two;
      Main.swap[i+1] = 2;
      Main.swap[high] = 2;
      Main.panel.paintImmediately(Main.panel.getBounds());
      Main.swap[high] = 1;
      return (i + 1);
      
    }
    
    public static void merge(int[] array, int l, int m, int r)
    {
      int arrayOneLength=(m-l+1);
      int arrayTwoLength=(r-m);
  
      int[] L = new int[arrayOneLength];
      int[] R = new int[arrayTwoLength];
      for (int i = 0; i<128; i++)
        {
              System.out.print(Main.array[i]+", ");
        }
      System.out.println("merge break");
  
      for (int i = 0; i<arrayOneLength; i++)
        {
          L[i] = Main.array[l+i];
          //System.out.println("array="+array[l+i]);
          //System.out.println("L"+i+"="+L[i]);
        }
      for (int i = 0; i<arrayTwoLength; i++)
        {
          R[i] = Main.array[m+1+i];
          //System.out.println("R"+i+"="+R[i]);
        }
  
      int a = 0;
      int b = 0;
  
      int key = l;
  
      while (a<arrayOneLength&&b<arrayTwoLength)
        {
          if (L[a]>R[b])
          {
            Main.array[key] = L[a];
            a++;
          }
          else 
          {
            Main.array[key] = L[b];
            b++;
          }
          //System.out.println(array[key]);
          key++;
        }
  
      while (a<arrayOneLength)
        {
          Main.array[key] = L[a];
          //System.out.println(array[key]);
          key++;
          a++;
        }
      while (b<arrayTwoLength)
        {
          Main.array[key] = R[b];
          //System.out.println(array[key]);
          key++;
          b++;
        }
    }
     public static void mergeSort(int[] array, int l, int r)
    {
  
      Main.sortType = "Merge Sort";
      if (r>l)
      {
        int m =(l+r)/2;
        mergeSort(array, l, m);
        mergeSort(array, m+1, r);
        merge(array, l, m, r);
      }
    }
    
    public static void insertionSort() 
    {
      
      Main.sortType = "Insertion Sort";
      Timer timer = new Timer(5, null);
      ActionListener timerAction = new ActionListener()
      {
        public void actionPerformed(ActionEvent e)
          {
            for (int i = 1; i<=Main.array.length; i++)
              {
                int j = i-1;
                int k = i;
                if (k==128)
                  {
                    break;
                  }
                if (i==Main.array.length)
                {
                  try
                    {
                      Main.arrayCheck();
                    }
                  catch(InterruptedException e2)
                    {
                      e2.printStackTrace();
                    }
                  Main.sortProgress = " Sorted!";
                  System.out.println(" Sorted!");
                  ((Timer) e.getSource()).stop();
                }
                while (k<128&&Main.array[k]<Main.array[j])
                {
                  Main.swap[k] = 2;
                  Main.swap[j] = 2;
                  int temp = Main.array[j];
                  Main.array[j] = Main.array[k];
                  Main.array[k] = temp;
                  Main.arrayAccesses+=2;                Main.panel.paintImmediately(Main.panel.getBounds());
                  Main.swap[k] = 1;
                  Main.swap[j] = 1;
                  Main.panel.paintImmediately(Main.panel.getBounds());
                  j--;
                  k--;              
                  if (j<0)
                  {
                    break;
                  }
  
                }
              }
  
          }
      };
      timer.addActionListener(timerAction);
      timer.start();
    }
  
    //Bogo Sort
    //Time Complexity: O((N-1)*N!)
    public static void bogoSort() throws InterruptedException
    {
      Main.sortType = "Bogo Sort";
      Timer timer = new Timer(25, null);
  
      ActionListener timerAction = new ActionListener()
        {
          public void actionPerformed(ActionEvent e)
          {
            boolean sorted = true;
  
            for(int i = 0; i<Main.array.length-1; i++)
              {
                if(Main.array[i]>Main.array[i+1])
                {
                  sorted = false;
                  for(int j = 0; j<Main.array.length; j++)
                    {
                      Main.swap[j] = 1;
                    }
                  Main.panel.repaint();
                }
                else{
                  Main.swap[i] = 2;
                  Main.swap[i+1] = 2;
                  Main.panel.repaint();
                }
              }
  
            if(sorted)
            {
              Main.sortProgress = " Sorted!";
              System.out.println(" Sorted!");
              ((Timer) e.getSource()).stop();
            }
            else 
            {
              //randomizing
              Random rand = new Random();  
           
          		for (int i=0; i<Main.array.length; i++) 
                {
                  Main.arrayAccesses++;
          		    int position = rand.nextInt(Main.array.length);
          		    int temp = Main.array[i];
          		    Main.array[i] = Main.array[position];
          		    Main.array[position] = temp;
          		  }
              Main.panel.repaint();
            }
            
          }
        };
      timer.addActionListener(timerAction);
      timer.start();
    }
}
