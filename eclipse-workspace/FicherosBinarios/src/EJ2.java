import java.io.*;
import java.util.*;

public class EJ2 {
	public static void main(String[] args) throws Exception {
		DataInputStream d_i_s = new DataInputStream(new FileInputStream(new File("FicherosDatos.dat")));
		
		ArrayList<String> names = new ArrayList<>();
		ArrayList<Integer> edades = new ArrayList<>();
		
		try {
			int i = 0;
			while(true) {
				names.add(i, d_i_s.readUTF());
				edades.add(i, d_i_s.readInt());
				i++;
			}
			
		}catch(EOFException eof) {
			for(int i = 0; i < names.size(); i++) {
				System.out.println(names.get(i) + " " + edades.get(i));
			}
		}
		
		d_i_s.close();
	}
}
