import java.awt.Color;
import java.awt.image.BufferedImage;

import java.util.ArrayList;


public class ImageTransform extends ReadImageFromFile {
	
	private long timeProcess;
	public ImageTransform()
	{
		timeProcess =  System.currentTimeMillis();
	}
	public  BufferedImage process() {
	        int red;
	        int green;
	        int blue;
	        int alpha;
	        int newPixel = 0;
	       timeProcess =  System.currentTimeMillis();
	        // obinem tabelul histogramei
	        ArrayList<int[]> histLUT = equalizeHistogram();
	   
	   	 
	        BufferedImage histogramEQ = new BufferedImage(super.getImage().getWidth(),super. getImage().getHeight(), super.getImage().getType());
	 
	        for(int i=0; i < super.getImage().getWidth(); i++) {
	            for(int j=0; j < super.getImage().getHeight(); j++) {
	 
	                // obtinem pixelii pentru R, G, B
	                alpha = new Color(super.getImage().getRGB (i, j)).getAlpha();
	                red = new Color(super.getImage().getRGB (i, j)).getRed();
	                green = new Color(super.getImage().getRGB (i, j)).getGreen();
	                blue = new Color(super.getImage().getRGB (i, j)).getBlue();
	 
	                // Setam noile valori ale pixelilor folosind histograma
	                red = histLUT.get(0)[red];
	                green = histLUT.get(1)[green];
	                blue = histLUT.get(2)[blue];
	 
	                // le combinam în forma initiala
	                newPixel = convertColorToRGB(alpha, red, green, blue);
	 
	                // scriem pixelii in imagine
	                histogramEQ.setRGB(i, j, newPixel);
	 
	            }
	        }
	        
	        timeProcess = System.currentTimeMillis() - timeProcess;
	       
	        return histogramEQ;
	        
	    }
	 
	    // obtinem histograme pentru fiecare canal de culoare R, B, B
	   public static ArrayList<int[]> equalizeHistogram() {
		   BufferedImage original = getImage();
	        // calculam valoare histogramei pentru fiecare canal de culoare
	        ArrayList<int[]> imageHist = saveHistogram();
	 
	        // vectorul în care se vor salva valorile
	        ArrayList<int[]> imageLUT = new ArrayList<int[]>();
	 
	        // Declaram vectorii
	        int[] rhistogram = new int[256];
	        int[] ghistogram = new int[256];
	        int[] bhistogram = new int[256];
	 
	        for(int i=0; i<rhistogram.length; i++) rhistogram[i] = 0;
	        for(int i=0; i<ghistogram.length; i++) ghistogram[i] = 0;
	        for(int i=0; i<bhistogram.length; i++) bhistogram[i] = 0;
	 
	        long sumr = 0;
	        long sumg = 0;
	        long sumb = 0;
	 
	        //Calculam factorul de scalare
	        float scaleFactor = (float) (255.0 / (original.getWidth() * original.getHeight()));
	 
	        for(int i=0; i<rhistogram.length; i++) {
	            sumr += imageHist.get(0)[i];
	            int valr = (int) (sumr * scaleFactor);
	            if(valr > 255) {
	                rhistogram[i] = 255;
	            }
	            else rhistogram[i] = valr;
	 
	            sumg += imageHist.get(1)[i];
	            int valg = (int) (sumg * scaleFactor);
	            if(valg > 255) {
	                ghistogram[i] = 255;
	            }
	            else ghistogram[i] = valg;
	 
	            sumb += imageHist.get(2)[i];
	            int valb = (int) (sumb * scaleFactor);
	            if(valb > 255) {
	                bhistogram[i] = 255;
	            }
	            else bhistogram[i] = valb;
	        }
	 
	        imageLUT.add(rhistogram);
	        imageLUT.add(ghistogram);
	        imageLUT.add(bhistogram);
	 
	        return imageLUT;
	 
	    }
	 
	    // Returnam un ArrayList care va avea valorile histogramei salvate pentru fiecare canal de culoare
	    public static ArrayList<int[]> saveHistogram() {
	    	BufferedImage original = getImage();
	        int[] rhistogram = new int[256];
	        int[] ghistogram = new int[256];
	        int[] bhistogram = new int[256];
	 
	        for(int i=0; i<rhistogram.length; i++) rhistogram[i] = 0;
	        for(int i=0; i<ghistogram.length; i++) ghistogram[i] = 0;
	        for(int i=0; i<bhistogram.length; i++) bhistogram[i] = 0;
	 
	        for(int i=0; i<original.getWidth(); i++) {
	            for(int j=0; j<original.getHeight(); j++) {
	 
	                int red = new Color(original.getRGB (i, j)).getRed();
	                int green = new Color(original.getRGB (i, j)).getGreen();
	                int blue = new Color(original.getRGB (i, j)).getBlue();
	 
	                
	                rhistogram[red]++; ghistogram[green]++; bhistogram[blue]++;
	 
	            }
	        }
	 
	        ArrayList<int[]> hist = new ArrayList<int[]>();
	        hist.add(rhistogram);
	        hist.add(ghistogram);
	        hist.add(bhistogram);
	 
	        return hist;
	 
	    }
	 
	    // Converteste R, G, B, Alpha la 8 biti
	  public static int convertColorToRGB(int alpha, int red, int green, int blue) {
	 
	        int newPixel = 0;
	        newPixel += alpha; newPixel = newPixel << 8;
	        newPixel += red; newPixel = newPixel << 8;
	        newPixel += green; newPixel = newPixel << 8;
	        newPixel += blue;
	 
	        return newPixel;
	 
	    }
	  @Override
	 public long timpProcesare() {
		 super.display("Acesta","este", "timpul", "de", "procesare " );
		 System.out.println( timeProcess/ 1000.0f + " secunde");
	
		 return timeProcess;
	 }
	  
	 
}
	
