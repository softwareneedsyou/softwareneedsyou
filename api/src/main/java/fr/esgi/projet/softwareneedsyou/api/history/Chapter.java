/**
 * 
 */
package fr.esgi.projet.softwareneedsyou.api.history;

import java.util.Collection;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.ToString;

/**
 * Histoire d'un langage
 * 
 * @author Tristan
 */
@EqualsAndHashCode
//@ToString
public abstract class Chapter {
	/**
	 * UUID du compilateur compatible
	 * @return UUID du compilateur
	 */
	public abstract UUID getCompiler();
	
	/**
	 * Titre de l'histoire
	 * @return titre
	 */
	public abstract String getTitle();
	
	/**
	 * Description de l'histoire
	 * @return description
	 */
	public abstract Optional<String> getResume();
	
	/**
	 * Niveau(x) constituant l'histoire
	 * @return niveau(x) (instance)
	 */
	public abstract Collection<Story> getStories();

	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	/*@Override
	public int compareTo(History arg0) {
		return ;
	}*/
	
	@Override
	public int hashCode() {
		@NonNull final Optional<?> op = this.getResume();
		return Objects.hash( this.getCompiler(), this.getTitle(), op.isPresent() ? op.get() : new Object());
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Chapter [getCompiler()=" + getCompiler() + ", getTitle()=" + getTitle() + ", getResume()=" + getResume()
				+ ", getStories()=" + getStories() + "]";
	}

	@Override
	public boolean equals(@NonNull final Object other) {
		if(super.equals(other))
			return true;
		else if((other instanceof Chapter)) {
			final Chapter hist = (Chapter) other;
			return this.getCompiler().equals(hist.getCompiler()) && this.getTitle().equals(hist.getTitle()) && this.getResume().equals(hist.getResume());
		} else
			return false;
	}
}