import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ReadImageFromFile extends TimeProcessing {
	
	private long time;
	private static BufferedImage image;
	public ReadImageFromFile()
	{		//timpul actual al sistemului
		  time = System.currentTimeMillis();
	}
	public static BufferedImage getImage() {
	        return image;
	    }
	public boolean readFile(String file) {
			time = System.currentTimeMillis();
			//calea spre poza introdusa de la tastatura
			try (FileInputStream stream = new FileInputStream(file)) {
				//actualizarea variabilei image cu poza dorita
			image = ImageIO.read(stream);
			} catch (FileNotFoundException e) {
				System.out.println("Nu am gasit fisierul " + file);
				return false;
			} catch (IOException e) {
				System.out.println("Eroare in timpul citirii din fisier");
				return false;
			}
			// aflarea timpului de citire a imaginii
			time = System.currentTimeMillis() - time;
			//afisarea timpului de citire
		
	
			return true;
		}
	public static void display(String... values)
	{  
		for(String s:values)
		{  
			   System.out.print(" " + s );  
		}  

	}
	@Override
	public long timpCitire()
		{
		display("Acesta", "este", "timpul","de", "citire ");
		System.out.println( time / 1000.0f + " secunde");
			return time;
		}
	
	@Override
	public long timpProcesare()
		{
			return 0;
		}
	
	
}