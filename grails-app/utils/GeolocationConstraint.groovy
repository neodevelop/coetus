/*
 * Copyright 2002-2008 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
import org.codehaus.groovy.grails.validation.AbstractConstraint
import org.springframework.validation.Errors
 
class GeolocationConstraint extends AbstractConstraint {
 
    private static final String DEFAULT_NOT_GEOLOCATION_MESSAGE_CODE = "default.not.geolocation.message";
    public static final String GEOLOCATION_CONSTRAINT = "geolocation";
 
    private boolean geolocation;
  
    public void setParameter(Object constraintParameter) {
        if(!(constraintParameter instanceof Boolean))
        throw new IllegalArgumentException("Parameter for constraint ["
               +GEOLOCATION_CONSTRAINT+"] of property ["
               +constraintPropertyName+"] of class ["
               +constraintOwningClass+"] must be a boolean value");
 
        this.geolocation = ((Boolean)constraintParameter).booleanValue();
        super.setParameter(constraintParameter);
    }

    protected void processValidate(Object target, Object propertyValue, Errors errors) {
		if(geolocation) {
			def l = propertyValue.split(",")
			if(!l.size().equals(2)) {
				def args = (Object[]) [constraintPropertyName, constraintOwningClass, propertyValue]
				super.rejectValue(target, errors, DEFAULT_NOT_GEOLOCATION_MESSAGE_CODE, "not." + GEOLOCATION_CONSTRAINT, args);
			}
		}
    }
 
    boolean supports(Class type) {
        return type != null && String.class.isAssignableFrom(type);
    }
 
    String getName() {
        return GEOLOCATION_CONSTRAINT;
    }

}
