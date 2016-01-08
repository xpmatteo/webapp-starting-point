//   Copyright 2012,2013 Vaughn Vernon
//
//   Licensed under the Apache License, Version 2.0 (the "License");
//   you may not use this file except in compliance with the License.
//   You may obtain a copy of the License at
//
//       http://www.apache.org/licenses/LICENSE-2.0
//
//   Unless required by applicable law or agreed to in writing, software
//   distributed under the License is distributed on an "AS IS" BASIS,
//   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
//   See the License for the specific language governing permissions and
//   limitations under the License.

//   MODIFIED BY MATTEO VACCARI

package com.saasovation.common.domain.model;

import java.lang.reflect.*;
import java.util.Date;

public abstract class DomainEvent {

	private Date occurredOn = new Date();

    public int eventVersion() {
    	return 0;
    }

    public Date occurredOn() {
    	return occurredOn;
    }


	@Override
	public boolean equals(Object obj) {
		if (null == obj)
			return false;
	    if (!(obj.getClass().equals(this.getClass())))
	    	return false;

	    try {
		    for (Field f : this.getClass().getDeclaredFields()) {
		    	f.setAccessible(true);
		    	if (!(f.get(this).equals(f.get(obj))))
		    		return false;
		    }
		    return true;
	    } catch (IllegalAccessException e) {
	    	throw new RuntimeException(e);
	    }
	}

	@Override
	public int hashCode() {
	    try {
	    	int code = 0;
		    for (Field f : this.getClass().getDeclaredFields()) {
		    	f.setAccessible(true);
		    	code = code | f.get(this).hashCode();
		    }
		    return code;
	    } catch (IllegalAccessException e) {
	    	throw new RuntimeException(e);
	    }
	}
}
