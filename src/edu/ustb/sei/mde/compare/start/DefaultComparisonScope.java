package edu.ustb.sei.mde.compare.start;

import static com.google.common.base.Predicates.not;
import static edu.ustb.sei.mde.compare.util.EMFComparePredicates.IS_EGENERIC_TYPE_WITHOUT_PARAMETERS;

import org.eclipse.emf.common.notify.Notifier;

/**
 * This is the default implementation of an {@link IComparisonScope}. When matching EObjects through their
 * identifier, we simply retrieve all content under a {@link org.eclipse.emf.ecore.resource.Resource}, filter
 * out the {@link org.eclipse.emf.ecore.EGenericType}s since they are handled through special means by EMF
 * (mutually derived references such as eSuperTypes&lt;->eGenericSuperTypes, eType&lt;->eGenericType...) and
 * iterate over this list as a whole.
 * 
 * @author <a href="mailto:laurent.goubet@obeo.fr">Laurent Goubet</a>
 */
public class DefaultComparisonScope extends FilterComparisonScope {
	/**
	 * Simply delegates to the super constructor.
	 * 
	 * @param left
	 *            Left root of this comparison.
	 * @param right
	 *            Right root of this comparison.
	 * @param origin
	 *            Common ancestor of <code>left</code> and <code>right</code>.
	 */
	public DefaultComparisonScope(Notifier left, Notifier right, Notifier origin) {
		super(left, right, origin);
		setEObjectContentFilter(not(IS_EGENERIC_TYPE_WITHOUT_PARAMETERS));
		setResourceContentFilter(not(IS_EGENERIC_TYPE_WITHOUT_PARAMETERS));
	}
}

