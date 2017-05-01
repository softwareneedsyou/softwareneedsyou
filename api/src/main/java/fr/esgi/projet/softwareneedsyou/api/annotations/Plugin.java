/**
 * 
 */
package fr.esgi.projet.softwareneedsyou.api.annotations;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.SOURCE;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * @author Blixel
 *
 */
@Documented
@Retention(SOURCE)
@Target(TYPE)
public @interface Plugin {
	Class<?>[] value();
}
