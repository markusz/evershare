package util.data;

import java.text.DecimalFormat;

public class CurrencyUtil {
	
	public static String getPriceFormatted(double p) {
		DecimalFormat nf = new DecimalFormat("0.00");
		return nf.format(p);
	}

}
