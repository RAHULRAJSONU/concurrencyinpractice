package com.silvertech.training.imageprocessing;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.Buffer;
import javax.imageio.ImageIO;

public class ImageRecolourProcessor {

  private final String SOURCE_FILE = "C:/Users/Rahul_Raj3/Documents/workspace_rnd/java/concurrencyinpractice/src/resources/many-flowers.jpg";
  private final String DESTINATION_FILE = "C:/Users/Rahul_Raj3/Documents/workspace_rnd/java/concurrencyinpractice/src/resources/out/many-flowers.jpg";

  public ImageRecolourProcessor() {

  }

  public int getBlue(int rgb){
    return rgb & 0x000000FF;
  }
  public int getGreen(int rgb){
    return (rgb & 0x0000FF00) >> 8;
  }

  public int getRed(int rgb){
    return (rgb & 0x00FF0000) >> 16;
  }

  public int createRGBFromColors(int red, int green, int blue){
    int rgb = 0;
    rgb |= blue;
    rgb |= green << 8;
    rgb |= red << 16;
    rgb |= 0xFF000000;
    return rgb;
  }

  public boolean isShadesOfGray(int red, int green, int blue){
    return Math.abs(red-green) < 30 && Math.abs(red-blue) < 30 && Math.abs(green-blue) < 30;
  }

  public void recolorPixel(BufferedImage originalImage, BufferedImage resultImage, int x, int y){
    int rgb = originalImage.getRGB(x,y);
    int red = getRed(rgb);
    int green = getGreen(rgb);
    int blue = getBlue(rgb);

    int newRed;
    int newGreen;
    int newBlue;

    if(isShadesOfGray(red,green,blue)){
      newRed = Math.min(255, red+10);
      newGreen = Math.max(0, green - 80);
      newBlue = Math.max(0, blue - 20);
    } else {
      newRed = red;
      newGreen = green;
      newBlue = blue;
    }

    int newRGB = createRGBFromColors(newRed, newGreen, newBlue);
    setRGB(resultImage,x,y,newRGB);
  }

  public void setRGB(BufferedImage image, int x, int y, int rgb){
    image.getRaster().setDataElements(x,y,image.getColorModel().getDataElements(rgb, null));
  }

  public void recolorSingleThreaded(BufferedImage original,BufferedImage resultImage){
    recolorImage(original, resultImage,0,0,original.getWidth(), original.getHeight());
  }

  public void recolorImage(BufferedImage originalImage,BufferedImage resultImage, int leftCorner, int topCorner, int width, int height){
    for(int x = leftCorner; x< leftCorner+width && x<originalImage.getWidth(); x++){
      for(int y = topCorner; y<topCorner+height && y<originalImage.getHeight(); y++){
        recolorPixel(originalImage,resultImage,x,y);
      }
    }
  }

  public void apply() throws IOException {
    BufferedImage originalImage = ImageIO.read(new File(SOURCE_FILE));
    BufferedImage resultImage = new BufferedImage(originalImage.getWidth(),originalImage.getHeight(),BufferedImage.TYPE_INT_RGB);
    recolorSingleThreaded(originalImage,resultImage);
    File outputFile = new File(DESTINATION_FILE);
    ImageIO.write(resultImage,"jpg",outputFile);
  }

}
