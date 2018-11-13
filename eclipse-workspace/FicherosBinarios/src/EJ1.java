import java.io.*;

// EJ 1
/*
 * Averigua jerárquicamente, ¿cuál es la relación entre DataInputStream y FileInputStream?
 * FileInputStream hereda de InputStream, mientras que DataInputStream hereda de FilterInputStream, que a su vez hereda de InputStream  
 * */

// EJ 2

public class EJ1 {
	public static void main(String[] args) throws Exception {
		String[] names = {"Pedro", "Winnie", "Vladimir", "Perro", "cyka"};
		int[] edades = {23, 45, 22, 66, 89};
		DataOutputStream d_o_s = new DataOutputStream(new FileOutputStream(new File("FicherosDatos.dat")));
		
		for(int i = 0; i < names.length; i++) {
			d_o_s.writeUTF(names[i]);
			d_o_s.writeInt(edades[i]);
		}
		
		d_o_s.close();
	}
}

/*
 * EJ 4
 * Cuando abro el fichero "FicherosDatos.dat" se muestran un seguido de Strings con símbulos extraños 
 * */
 