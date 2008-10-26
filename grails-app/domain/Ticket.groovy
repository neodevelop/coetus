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
class Ticket implements Comparable {
	Event event
	String name
	BigDecimal price
	Currency currency
	Boolean free
	Long quantityAvailable
	Date startSelling
	Date endSelling
	String description
	Date dateCreated
	Date lastUpdated
	
	static constraints = {
		name(blank:false,nullable:false,size:1..30)
		price(nullable:false)
		currency(nullable:false)
		free()
		quantityAvailable(nullable:false)
		startSelling()
		endSelling()
		description(blank:true,nullable:true,size:0..700)
		dateCreated(nullable:true)
		lastUpdated(nullable:true)
	}
	
	/**
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	public int compareTo(Object o) {
		return 0;
    }

}
