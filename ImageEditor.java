import java.util.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.*;

public class ImageEditor{

    public static void main(String []args){

        Scanner sc = new Scanner(System.in);

        if(args.length != 3){
            System.out.println("Image Editor : Usage -> <command> <input-file_path> <output-file_path>");
            System.exit(1);
        }
        
        String[] commands = {"mirror","to-grayscale","to-monochrome","rotate-clockwise", "rotate-anticlockwise","brightness-control"};

        String command = "";

        boolean commandExists = false;

        for(int i  = 0; i < commands.length; i++){

            if(commands[i].equals(args[0])){
                commandExists = true;
                command = commands[i];
                break;
            }
        }


        if(!commandExists){
            System.out.printf("%s : not a valid command !\n",args[0]);
            System.out.println("Possible Commands : mirror , to-grayscale , to-monochrome , rotate-clockwise , rotate-anticlockwise, brightness-control");
            System.exit(1);

        }

        if(!args[1].contains(".jpg")){
            System.out.println("[argument] : only .jpg files supported !");
            System.exit(1);
        }

        File outputImage = new File(args[2]+".jpg");

        try {
            File inputFile = new File(args[1]);

            BufferedImage inputImage = ImageIO.read(inputFile);
            
            
            switch(command){
                case "mirror":
                    ImageIO.write(mirrorImage(inputImage), "jpg", outputImage);
                    break;
                
                case "to-grayscale":
                    ImageIO.write(convert_to_grayscale(inputImage), "jpg", outputImage);
                    break;

                case "to-monochrome":
                    ImageIO.write(convert_to_monochrome(inputImage), "jpg", outputImage);
                    break;

                case "rotate-clockwise":
                    ImageIO.write(rotateImageClockWise(inputImage), "jpg", outputImage);
                    break;

                case "brightness-control":
                    System.out.println("Enter the percentage of change in brightness (increase/decrease) :");
                    int percent = sc.nextInt();
                    System.out.println("");
                    ImageIO.write(change_brightness(inputImage, percent), "jpg", outputImage);
                    break;

                case "rotate-anticlockwise":
                    ImageIO.write(rotateImageAntiClockWise(inputImage),"jpg", outputImage);
                    break;

                default:
                    System.out.println("Some error ocurred !");
                    break;

            }

            System.out.printf("Final output image %s created.\n",outputImage);
            System.exit(1);
            
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println(e + "File not Found !");
        }
        sc.close();
        System.exit(1);

    }

    public static BufferedImage mirrorImage(BufferedImage image){

        int height = image.getHeight();
        int width = image.getWidth();

        BufferedImage imgOut = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);

        for(int i = 0 ; i < height; i++){
            for(int j = 0; j < width; j++){
                //Replaces the first and last pixels' colors.
                imgOut.setRGB(width-1-j,i,image.getRGB(j,i));
                imgOut.setRGB(j,i,image.getRGB(width-1-j,i));

            }
        }
        return imgOut;

    }

    public static BufferedImage rotateImageClockWise(BufferedImage image){

        int height = image.getHeight();
        int width = image.getWidth();

        BufferedImage imgOut = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);


        for(int i = 0 ; i < width; i++){
            for(int j = 0; j < height; j++){

                imgOut.setRGB(j,i,image.getRGB(i,j));

            }
        }

        imgOut = mirrorImage(imgOut);


        return imgOut;

    }

    public static BufferedImage rotateImageAntiClockWise(BufferedImage image){
        int height = image.getHeight();
        int width = image.getWidth();

        BufferedImage outputImage = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);

        outputImage = rotateImageClockWise(image);
        outputImage = mirrorImage(outputImage);

        return outputImage;

    }


    public static BufferedImage convert_to_grayscale(BufferedImage image){

        int height = image.getHeight();
        int width = image.getWidth();

        BufferedImage imgOut = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_GRAY);

        for(int i = 0 ; i < height; i++){
            for(int j = 0; j < width; j++){

                imgOut.setRGB(j,i,image.getRGB(j,i));

            }
        }
        return imgOut;

    }

    public static BufferedImage convert_to_monochrome(BufferedImage image){

        int height = image.getHeight();
        int width = image.getWidth();

        BufferedImage imgOut = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);

        for(int i = 0 ; i < height; i++){
            for(int j = 0; j < width; j++){
                int rgb = image.getRGB(j, i);

                Color color = new Color(rgb);

                int red = color.getRed();
                int blue = color.getBlue();
                int green = color.getGreen();

                int avgCol = (int)(Math.ceil((red + green + blue)/3.0));

                int bgr = new Color(avgCol,avgCol,avgCol).getRGB();

                imgOut.setRGB(j,i,bgr);

            }
        }
        return imgOut;

    }

    public static BufferedImage change_brightness(BufferedImage image, double percent){

        int height = image.getHeight();
        int width = image.getWidth();

        BufferedImage imgOut = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);

        for(int i = 0 ; i < height; i++){
            for(int j = 0; j < width; j++){

                int rgb = image.getRGB(j, i);

                Color color = new Color(rgb);

                int red = (int)(Math.ceil(color.getRed()*((percent + 100)/100.0)));
                int green = (int)(Math.ceil(color.getGreen()*((percent + 100)/100.0)));
                int blue = (int)(Math.ceil(color.getBlue()*((percent + 100)/100.0)));

                if(red > 255){
                    red = 255;
                }

                if(green > 255 ){
                    green = 255;
                }

                if(blue > 255){
                    blue = 255;
                }

                int bgr = new Color(red,green,blue).getRGB();

                imgOut.setRGB( j, i, bgr);

            }
        }
        return imgOut;

    }

}