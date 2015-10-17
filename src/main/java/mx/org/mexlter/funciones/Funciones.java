package mx.org.mexlter.funciones;

public abstract class Funciones {
	
	
	public static String calcularValor(int variable, String valor) {

		int grados, mins, seconds;
		double decimales;
		String min;

		if ((variable == 2 || variable == 7) && (isNumeric(valor))) {

			grados = new Double(valor).intValue();
			decimales = Double.parseDouble(valor) - grados;
			mins = new Double((decimales * 60)).intValue();
			decimales = (decimales * 60) - mins;
			seconds = new Double(Math.round(decimales * 60)).intValue();

			if (grados < 0) {
				grados = grados * -1;
				mins = mins * -1;
				seconds = seconds * -1;
			}

			min = grados + "°" + mins + "'" + seconds + "''";

			if (variable == 2)
				min += new Double(valor).intValue() < 0 ? "S" : "N";

			else if (variable == 7)
				min += new Double(valor).intValue() < 0 ? "W" : "E";

			return min;
		}
		return valor;
	}

	public static boolean isNumeric(String str) {
		try {
			Double.parseDouble(str);
		} catch (NumberFormatException nfe) {
			return false;
		}
		return true;
	}
}
