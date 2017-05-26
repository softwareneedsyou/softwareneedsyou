/**
 * 
 */
package fr.esgi.projet.softwareneedsyou.util;

import org.apache.commons.lang3.StringUtils;

import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.ToString;

/**
 * @author Tristan
 *
 */
@ToString(of="str", doNotUseGetters=true)
@EqualsAndHashCode(of="str", doNotUseGetters=true)
public /*final*/ class VersionUtils implements Comparable<VersionUtils> {
	@NonNull private String str;
	private long complete;
	@NonNull private int[] part;
	
	/**
	 * 
	 * @param obj
	 */
	public VersionUtils(@NonNull final Object obj) {
		this(obj.getClass());
	}

	
	public VersionUtils(@NonNull final Class<?> cls) {
		this.str = getVersionAPI(cls);
		this.complete = Long.parseLong(this.str.replaceAll(".", ""));
		String[] tmp = this.str.split(".");
		//this.version = Utils.convert(tmp, Integer::parseInt, int[]::new);
		this.part = new int[tmp.length];
		int i;
		for(i=0 ; i < tmp.length ; i++)
			this.part[i] = Integer.parseInt(tmp[i]);
	}
	
	/**
	 * Retourne la version du Jar utilisée
	 * @return la version sous forme de chaine de caractère.
	 */
	@NonNull
	public static String getVersionAPI(@NonNull final Class<?> classe) {
		return classe.getPackage().getSpecificationVersion();
	}

	/**
	 * Retourne la version du Jar utilisée
	 * @return la version sous forme de chaine de caractère.
	 */
	@NonNull
	public static String getVersionAPI(@NonNull final Object obj) {
		return getVersionAPI(obj.getClass());
	}

	@Override
	public int compareTo(@NonNull final VersionUtils v) {
		long tmp = VersionUtils.getCompleteAdapt(this.str, v.str) - VersionUtils.getCompleteAdapt(v.str, this.str);
		return (tmp > Integer.MAX_VALUE) ? Integer.MAX_VALUE : (tmp < Integer.MIN_VALUE) ? Integer.MIN_VALUE : (int) tmp;
	}

	/**
	 * Adapte la chaine de caractère pour permettre la comparaison sans fausser le résultat.
	 * ex :
	 * si "1.1.2" et "1.10.3.1" alors "1.1.2" -> "1.01.2.0" pour conversion en string
	 *  => "1.01.2.0" = "10120" et "1.10.3.1" = "11031"
	 * 
	 * @param src
	 * @param cmp
	 * @return src version sous forme de long
	 */
	private static long getCompleteAdapt(@NonNull final String src, @NonNull final String cmp) {
		final String[] asrc = src.split(".");
		final String[] acmp = src.split(".");
		final String[] tmp = new String[Math.max(asrc.length, acmp.length)];
		int i, min = Math.min(asrc.length, acmp.length);
		for(i=0 ; i < min ; i++)
			tmp[i] = StringUtils.repeat('0', acmp[i].length() - asrc[i].length()) + asrc[i];
		for( ; i < asrc.length ; i++) //if asrc.length > acmp.length
			tmp[i] = asrc[i];
		for( ; i < /*tmp*/acmp.length ; i++) //if asrc.length < acmp.length
			tmp[i] = StringUtils.repeat('0', acmp[i].length());
		return Long.parseLong(StringUtils.join(tmp, null));
	}

}
