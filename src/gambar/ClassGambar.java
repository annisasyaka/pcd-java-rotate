/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gambar;

import com.sun.javafx.css.converters.PaintConverter;
import com.sun.javafx.geom.transform.Identity;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.PaintContext;
import java.awt.geom.Point2D;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.ColorModel;
import java.awt.image.ImageObserver;
import java.awt.image.WritableRaster;
import java.awt.image.renderable.RenderableImage;
import java.io.File;
import java.io.IOException;
import javafx.scene.transform.Affine;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;


/**
 *
 * @author DELL
 */
public class ClassGambar {
   public ImageIcon SourceIcon;
   public Image SourceImage;
   public ImageIcon ScaleIcon;
   public Image ScaleImage;
   public Image ResultImage;
   public Image ScaleResultImage;
   public ImageIcon ScaleResultIcon;
   public String URLImage;
   public boolean ScaledFlag=false;
   public BufferedImage SourceBuffer;
   public BufferedImage ResultBuffer;
   public long sWidth;
   public long sHeight;
   public double currentAngle;
   public Image image;
   
   
   ClassGambar(String Url, long width, long height){
      URLImage=Url;
      if(width<=0 ||height <=0)
      {
          ScaledFlag=false;
      }
      else
      {
          ScaledFlag=true;
          sWidth=width;
          sHeight=height;
      }
      
   }
   
   public ImageIcon GetIcon(){
       if(!URLImage.equals(""))
       {
           SourceIcon = new ImageIcon(URLImage);
           SourceImage = SourceIcon.getImage();
           try
           {
               SourceBuffer=ImageIO.read(new File(URLImage));
               
           }
           catch(IOException x){
               JOptionPane.showMessageDialog(null, x.getMessage(), "error", JOptionPane.ERROR_MESSAGE);
               
           }
           System.out.println(SourceIcon.getIconWidth());
           if(ScaledFlag)
           {
               ScaleImage=SourceImage.getScaledInstance((int)sWidth, (int)sHeight, Image.SCALE_DEFAULT);
               ScaleIcon=new ImageIcon(ScaleImage);
               return ScaleIcon;
                                    
           }
           else
           {
               return SourceIcon;
           }
       }
       else
       {
           return null;
       }
   }
   
   public void Grayscale(){
       ResultBuffer=deepCopy(SourceBuffer);
       long tWidth=ResultBuffer.getWidth();
       long tHeight=ResultBuffer.getHeight();
       
       long x,y;
       int RGB,Red,Green,Blue,Gray;
       
       Color tWarna;
       for(x=0;x<tWidth;x++)
       {
           for(y=0;y<tHeight;y++)
           {
               RGB=ResultBuffer.getRGB((int)x,(int)y);
               tWarna=new Color (RGB);
               
               Red=tWarna.getRed();
               Green=tWarna.getGreen();
               Blue=tWarna.getBlue();
               Gray=(Red+Green+Blue)/3;
               
               tWarna=new Color(Gray,Gray,Gray);
               ResultBuffer.setRGB((int)x, (int)y, tWarna.getRGB());
           }
       }
       
       ResultImage=(Image) ResultBuffer;
       ScaleResultImage=ResultImage.getScaledInstance((int)sWidth, (int)sHeight, Image.SCALE_DEFAULT);
       ScaleResultIcon=new ImageIcon(ScaleResultImage);
   }
   
   public void Biner(){
       ResultBuffer=deepCopy(SourceBuffer);
       long tWidth=ResultBuffer.getWidth();
       long tHeight=ResultBuffer.getHeight();
       
       long x,y;
       int RGB,Red,Green,Blue,Gray;
       
       Color tWarna;
       for(x=0;x<tWidth;x++)
       {
           for(y=0;y<tHeight;y++)
           {
              RGB=ResultBuffer.getRGB((int)x, (int)y);
              tWarna=new Color(RGB);
              
               Red=tWarna.getRed();
               Green=tWarna.getGreen();
               Blue=tWarna.getBlue();
               Gray=(Red+Green+Blue)/3;
              
               if (Gray<=128)
               {
                   Gray=0;
               }
               else
               {
                   Gray=255;
               }
               
               tWarna=new Color(Gray,Gray,Gray);
               ResultBuffer.setRGB((int)x, (int)y, tWarna.getRGB());
           }
       }
       
       ResultImage=(Image) ResultBuffer;
       ScaleResultImage=ResultImage.getScaledInstance((int)sWidth, (int)sHeight, Image.SCALE_DEFAULT);
       ScaleResultIcon=new ImageIcon(ScaleResultImage);
       
   }
   
   public void Brightness(){
       ResultBuffer=deepCopy(SourceBuffer);
       long tWidth=ResultBuffer.getWidth();
       long tHeight=ResultBuffer.getHeight();
       
       long x,y;
       int RGB,Red,Green,Blue,Gray;
       int k = 10;
       
       Color tWarna;
       for(x=0;x<tWidth;x++)
       {
           for(y=0;y<tHeight;y++)
           {
              RGB=ResultBuffer.getRGB((int)x, (int)y);
              tWarna=new Color(RGB);
              
               Red=tWarna.getRed();
               Green=tWarna.getGreen();
               Blue=tWarna.getBlue();
              
                 
                   Red=Red+k;
                   if(Red>255)
                       Red=255;
                   Green=Green+k;
                   if(Green>255)
                       Green=255;
                   Blue=Blue+k;
                   if(Blue>255)
                       Blue=255;
                   else if(Red<=0)
                       Red=0;
                   else if(Green<=0)
                       Green=0;
                   else if(Blue<=0)
                       Blue=0;
                   
               tWarna=new Color(Red,Green,Blue);
               ResultBuffer.setRGB((int)x, (int)y, tWarna.getRGB());
               
           }
       }
       
       ResultImage=(Image) ResultBuffer;
       ScaleResultImage=ResultImage.getScaledInstance((int)sWidth, (int)sHeight, Image.SCALE_DEFAULT);
       ScaleResultIcon=new ImageIcon(ScaleResultImage);
   }

   public void Default(){
       ResultBuffer=deepCopy(SourceBuffer);
       long tWidth=ResultBuffer.getWidth();
       long tHeight=ResultBuffer.getHeight();
       
       long x,y;
       int RGB,Red,Green,Blue,Gray;
       
       Color tWarna;
       for(x=0;x<tWidth;x++){
           for(y=0;y<tHeight;y++)
           {
               RGB=ResultBuffer.getRGB((int)x, (int)y);
               tWarna=new Color(RGB);
               Red=tWarna.getRed();
               Green=tWarna.getGreen();
               Blue=tWarna.getBlue();
               tWarna=new Color(Red,Green,Blue);
           }
       }
       
       ResultImage=(Image) ResultBuffer;
       ScaleResultImage=ResultImage.getScaledInstance((int)sWidth, (int)sHeight, Image.SCALE_DEFAULT);
       ScaleResultIcon=new ImageIcon(ScaleResultImage);
   }
   
 static BufferedImage deepCopy(BufferedImage bi){ 
     ColorModel cm=bi.getColorModel();
     boolean isAlphaPremultiplied = cm.isAlphaPremultiplied(); WritableRaster raster = bi.copyData(null);
     return new BufferedImage(cm,raster,isAlphaPremultiplied,null);

 }
 
 public void SaveImage(String url)
 {
    
           
        File tFile = new File(url);
        System.out.println(url); 
        
        try
        {
            String fileName = tFile.getCanonicalPath();
            if(!fileName.endsWith(".jpeg"))
            {
            tFile=new File(fileName+".jpeg");
            }
            ImageIO.write(ResultBuffer, "jpeg", tFile);
            System.out.println("sukses");
        }
        catch(IOException x)
        {
            JOptionPane.showMessageDialog(null,x.getMessage(),"error",JOptionPane.ERROR_MESSAGE);
        }
 
    }
 
 public void flipV(){
     ResultBuffer=deepCopy(SourceBuffer);
     BufferedImage imge=new BufferedImage(SourceBuffer.getWidth(), 
             SourceBuffer.getHeight(), BufferedImage.TYPE_INT_RGB);
     for(int y=0; y<SourceBuffer.getHeight(); y++){
         for(int x=0; x<SourceBuffer.getWidth(); x++){
             imge.setRGB(x, y, SourceBuffer.getRGB(x, SourceBuffer.getHeight()-1-y));
             
         }
     }
     ResultImage=imge;
     ScaleResultImage=ResultImage.getScaledInstance((int)sWidth, 
             (int)sHeight, Image.SCALE_DEFAULT);
     ScaleResultIcon=new ImageIcon(ScaleResultImage);
 }
 
 public void flipH(){
     ResultBuffer=deepCopy(SourceBuffer);
     BufferedImage img=new BufferedImage(SourceBuffer.getWidth(), 
             SourceBuffer.getHeight(), BufferedImage.TYPE_INT_RGB);
     for(int y=0; y<SourceBuffer.getHeight(); y++){
         for(int x=0; x<SourceBuffer.getWidth();x++){
             img.setRGB(x, y, SourceBuffer.getRGB(SourceBuffer.getWidth()-1-x, y));
             
         }
     }
     ResultImage=img;
     ScaleResultImage=ResultImage.getScaledInstance((int)sWidth, 
             (int)sHeight, Image.SCALE_DEFAULT);
     ScaleResultIcon=new ImageIcon(ScaleResultImage);
 }
 
 public void zoomin(){
   ResultBuffer=deepCopy(SourceBuffer);
     ResultImage=(Image) ResultBuffer;
     sWidth = sWidth *2;
     sHeight = sHeight *2;
     ScaleResultImage=ResultImage.getScaledInstance((int)sWidth, (int)sHeight, Image.SCALE_DEFAULT);
     ScaleResultIcon=new ImageIcon(ScaleResultImage); 
 }
 
 public void zoomout(){
    ResultBuffer=deepCopy(SourceBuffer);
     ResultImage=(Image) ResultBuffer;
     sWidth = sWidth /2;
     sHeight = sHeight /2;
     ScaleResultImage=ResultImage.getScaledInstance((int)sWidth, (int)sHeight, Image.SCALE_DEFAULT);
     ScaleResultIcon=new ImageIcon(ScaleResultImage); 
 }
 
 public void rot180(){
     ResultBuffer=deepCopy(SourceBuffer);
     BufferedImage imge=new BufferedImage(SourceBuffer.getWidth(), 
             SourceBuffer.getHeight(), BufferedImage.TYPE_INT_RGB);
     for(int y=0; y<SourceBuffer.getHeight(); y++){
         for(int x=0; x<SourceBuffer.getWidth(); x++){
            imge.setRGB(x, y, SourceBuffer.getRGB(SourceBuffer.getWidth()-1-x, SourceBuffer.getHeight()-1-y));
             
         }
     }
     ResultImage=imge;
     ScaleResultImage=ResultImage.getScaledInstance((int)sWidth, 
             (int)sHeight, Image.SCALE_DEFAULT);
     ScaleResultIcon=new ImageIcon(ScaleResultImage);
   }
 
 public void rot90(){
     ResultBuffer=deepCopy(SourceBuffer);
     BufferedImage imgei =new BufferedImage(SourceBuffer.getWidth(), 
             SourceBuffer.getHeight(), BufferedImage.TYPE_INT_RGB);
     for(int y=0; y<SourceBuffer.getHeight(); y++){
         for(int x=0; x<SourceBuffer.getWidth(); x++){
            imgei.setRGB(SourceBuffer.getHeight()-1-y,x, SourceBuffer.getRGB(x, y));
             
         }
     }
     ResultImage=imgei;
     ScaleResultImage=ResultImage.getScaledInstance((int)sWidth, 
             (int)sHeight, Image.SCALE_DEFAULT);
     ScaleResultIcon=new ImageIcon(ScaleResultImage);
 }
 
 public void rot270(){
     ResultBuffer=deepCopy(SourceBuffer);
     BufferedImage imgei =new BufferedImage(SourceBuffer.getWidth(), 
             SourceBuffer.getHeight(), BufferedImage.TYPE_INT_RGB);
     for(int y=0; y<SourceBuffer.getHeight(); y++){
         for(int x=0; x<SourceBuffer.getWidth(); x++){
            imgei.setRGB(y,SourceBuffer.getWidth()-x-1, SourceBuffer.getRGB(x, y));
             
         }
     }
     ResultImage=imgei;
     ScaleResultImage=ResultImage.getScaledInstance((int)sWidth, 
             (int)sHeight, Image.SCALE_DEFAULT);
     ScaleResultIcon=new ImageIcon(ScaleResultImage);
 }
 
public void rot45() {
   
   
    ResultBuffer=deepCopy(SourceBuffer);
    
     BufferedImage imgei =new BufferedImage(SourceBuffer.getWidth(), 
             SourceBuffer.getHeight(), BufferedImage.TYPE_INT_ARGB);
     
     
     AffineTransform img = new AffineTransform();
    
     img.translate(SourceBuffer.getWidth()/2, 
             SourceBuffer.getHeight()/2);
     img.rotate(Math.PI/4);
     img.scale(0.5, 0.5);
    
     img.translate(-imgei.getWidth()/2, -imgei.getHeight()/2);
     
     Graphics2D g2d = (Graphics2D) imgei.getGraphics();
     g2d.drawImage(imgei, img, null);
     g2d = imgei.createGraphics();
     
    
 }

public void rotate (int degree){
    
    
    double sin = Math.sin(degree);
    double cos = Math.cos(degree);
    int nWeight = (int) Math.floor(SourceBuffer.getWidth()*cos+SourceBuffer.getHeight()*sin);
    int nHeight = (int) Math.floor(SourceBuffer.getHeight()*cos+SourceBuffer.getWidth()*sin);
    ResultBuffer = new BufferedImage(nWeight, nHeight, SourceBuffer.getType());
    Graphics2D g = ResultBuffer.createGraphics();
    g.translate((nWeight-SourceBuffer.getWidth())/2, (nHeight-SourceBuffer.getHeight())/2);
    g.rotate(Math.toRadians(degree),SourceBuffer.getWidth()/2, SourceBuffer.getHeight()/2);
    g.drawRenderedImage(SourceBuffer, null);
    
    ResultImage=(Image) ResultBuffer;
    ScaleResultImage=ResultImage.getScaledInstance((int)sWidth, (int)sHeight, Image.SCALE_DEFAULT);
    ScaleResultIcon=new ImageIcon(ScaleResultImage);
    
            
    }

 }