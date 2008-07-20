package org.codehaus.groovy.grails.plugins.springsecurity;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.metadata.Attributes;
import org.springframework.security.SecurityConfig;
import org.springframework.security.annotation.Secured;

/**
 * Re-implementation of Acegi's <code>SecurityAnnotationAttributes</code> as a temporary
 * fix until I can figure out how to do this correctly in 2.0.
 *
 * @author <a href='mailto:beckwithb@studentsonly.com'>Burt Beckwith</a>
 */
public class SecurityAnnotationAttributes implements Attributes {

	/**
	 * {@inheritDoc}
	 * @see org.springframework.metadata.Attributes#getAttributes(java.lang.Class)
	 */
	@SuppressWarnings("unchecked")
	public Set<SecurityConfig> getAttributes(final Class target) {
		Set<SecurityConfig> attributes = new HashSet<SecurityConfig>();

		for (Annotation annotation : target.getAnnotations()) {
			if (annotation instanceof Secured) {
				Secured attr = (Secured)annotation;
				for (String auth : attr.value()) {
					attributes.add(new SecurityConfig(auth));
				}
				break;
			}
		}

		return attributes;
	}

	/**
	 * {@inheritDoc}
	 * @see org.springframework.metadata.Attributes#getAttributes(java.lang.reflect.Method)
	 */
	public Set<SecurityConfig> getAttributes(final Method method) {
		Set<SecurityConfig> attributes = new HashSet<SecurityConfig>();

		Annotation[] annotations = AnnotationUtils.getAnnotations(method);
		for (Annotation annotation : annotations) {
			if (annotation instanceof Secured) {
				Secured attr = (Secured)annotation;
				for (String auth : attr.value()) {
					attributes.add(new SecurityConfig(auth));
				}

				break;
			}
		}

		return attributes;
	}

	/**
	 * {@inheritDoc}
	 * @see org.springframework.metadata.Attributes#getAttributes(java.lang.Class, java.lang.Class)
	 */
	@SuppressWarnings("unchecked")
	public Collection getAttributes(final Class clazz, final Class filter) {
		throw new UnsupportedOperationException("Unsupported operation");
	}

	/**
	 * {@inheritDoc}
	 * @see org.springframework.metadata.Attributes#getAttributes(java.lang.reflect.Method, java.lang.Class)
	 */
	@SuppressWarnings("unchecked")
	public Collection getAttributes(final Method method, final Class clazz) {
		throw new UnsupportedOperationException("Unsupported operation");
	}

	/**
	 * {@inheritDoc}
	 * @see org.springframework.metadata.Attributes#getAttributes(java.lang.reflect.Field)
	 */
	@SuppressWarnings("unchecked")
	public Collection getAttributes(final Field field) {
		throw new UnsupportedOperationException("Unsupported operation");
	}

	/**
	 * {@inheritDoc}
	 * @see org.springframework.metadata.Attributes#getAttributes(java.lang.reflect.Field, java.lang.Class)
	 */
	@SuppressWarnings("unchecked")
	public Collection getAttributes(final Field field, final Class clazz) {
		throw new UnsupportedOperationException("Unsupported operation");
	}
}
