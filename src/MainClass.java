import java.util.*;

public class MainClass 
{
	  
	public static void main(String[] args) 
	{
		//citirea din consola
		Scanner scanner = new Scanner(System.in);
		ReadImageFromFile ri = new ReadImageFromFile();
		ImageTransform pi = new ImageTransform();
		WriteImageToFile  wi = new WriteImageToFile();
		try 
		{
			while (true) 
			{

				boolean imageRead = false;
				do 
				{
					System.out.println("Introduceti calea catre un fisier de intrare sau \"quit\" pentru a iesi");
					//citirea caii spre fisier
					String line = scanner.nextLine();

					if ("quit".equals(line)) {
						System.out.println("Am terminat");
						return;
					}
					//citirea imaginii de la calea introdusa in consola
					imageRead = ri.readFile(line);
				  } while (!imageRead);
				
				ri.timpCitire();
				//transformarea imaginii
				pi.process();
				//display();
				pi.timpProcesare();
				System.out.println("Introduceti calea catre un fisier de iesire");
				String line = scanner.nextLine();
				wi.writeImage(line);
				wi.timpScriere();
			}
		} finally 
			{
				scanner.close();
			}
	}

}
