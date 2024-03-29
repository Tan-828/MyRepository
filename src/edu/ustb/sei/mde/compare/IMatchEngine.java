package edu.ustb.sei.mde.compare;

import java.util.List;

import org.eclipse.emf.ecore.EObject;

/**
 * This class defines the general contract of a Matching engine. We expect subclasses to have a public,
 * no-argument default constructor for instantiation.
 * <p>
 * We generally expect that a call to {@link #match(IComparisonScope)} will return us every single
 * {@link org.eclipse.emf.compare.Match matches} that can be determined from the given {@link IComparisonScope
 * context}. This includes all three of :
 * <ul>
 * <li>Elements that are present on all three sides of the comparison scope,</li>
 * <li>Elements that are present on only two sides,</li>
 * <li>Elements that are only present on a single side.</li>
 * </ul>
 * </p>
 * <p>
 * Clients can subclass the {@link DefaultMatchEngine default implementation} when all that is needed is to
 * change the matching strategy.
 * </p>
 * 
 * @author <a href="mailto:laurent.goubet@obeo.fr">Laurent Goubet</a>
 * @see DefaultMatchEngine
 */
public interface IMatchEngine {

	/**
	 * This is the entry point of a Comparison process. It is expected to use the provided scope in order to
	 * determine all objects that need to be matched.
	 * <p>
	 * The returned Comparison should include both matched an unmatched objects. It is not the match engine's
	 * responsibility to determine differences between objects, only to match them together.
	 * </p>
	 * 
	 * @param scope
	 *            The comparison scope that should be used by this engine to determine the objects to match.
	 * @param monitor
	 *            The monitor to report progress or to check for cancellation
	 * @return An initialized {@link Comparison} model with all matches determined.
	 */
	Comparison match(IComparisonScope scope);
	
	// lyt
	void matchADD(Comparison comparison, List<EObject> leftEObjects, List<EObject> rightEObjects);

	/**
	 * Wrapper describing the given match engine.
	 * 
	 * @author <a href="mailto:axel.richard@obeo.fr">Axel Richard</a>
	 * @since 3.0
	 */
	interface Factory {

		/**
		 * Returns the wrapped match engine.
		 * 
		 * @return the wrapped match engine.
		 */
		IMatchEngine getMatchEngine();

		/**
		 * Returns the ranking of this match engine factory.
		 * 
		 * @return The ranking.
		 * @since 3.0
		 */
		int getRanking();

		/**
		 * Set the ranking of this match engine factory.
		 * 
		 * @param parseInt
		 *            The ranking.
		 * @since 3.0
		 */
		void setRanking(int parseInt);

		/**
		 * Check if the match engine factory is a good candidate for comparison.
		 * 
		 * @param scope
		 *            The scope on which the match engine factory will be applied.
		 * @return True if it is the good candidate, false otherwise.
		 * @since 3.0
		 */
		boolean isMatchEngineFactoryFor(IComparisonScope scope);

		/**
		 * A registry of {@link IMatchEngine.Factory}.
		 * 
		 * @since 3.0
		 * @noimplement
		 * @noextend This interface is not intended to be extended by clients.
		 */
		interface Registry {

			// lyt
			IMatchEngine.Factory getMatchEngineFactory();

			/**
			 * Add to the registry the given {@link IMatchEngine.Factory}.
			 * 
			 * @param matchEngineFactory
			 *            The given {@link IMatchEngine.Factory}.
			 * @return The previous value associated with the class name of the given
			 *         {@link IMatchEngine.Factory}, or null if there was no entry in the registry for the
			 *         class name.
			 */
			IMatchEngine.Factory add(IMatchEngine.Factory matchEngineFactory);

			/**
			 * Remove from the registry the {@link IMatchEngine.Factory} designated by the given
			 * {@link String} .
			 * 
			 * @param className
			 *            The given {@link String} representing a {@link IMatchEngine.Factory}.
			 * @return The {@link IMatchEngine.Factory} designated by the given {@link String}.
			 */
			IMatchEngine.Factory remove(String className);

			/**
			 * Clear the registry.
			 */
			void clear();
		}
	}
}
