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

import static java.util.Arrays.*;
import static java.util.stream.Collectors.*;
import it.xpug.todolist.*;

import java.lang.reflect.*;
import java.util.*;

public abstract class DomainEvent {

	private Date occurredOn = new Date();

    public int eventVersion() {
    	return 0;
    }

    public Date occurredOn() {
    	return occurredOn;
    }

    @Override
    public String toString() {
    	String className = this.getClass().getSimpleName();
    	String fields = stream(getFields()).map(f -> getValue(f).toString()).collect(joining(", "));
        return String.format("%s(%s)", className, fields);
    }

	@Override
	public boolean equals(Object obj) {
		if (null == obj)
			return false;
	    if (!(obj.getClass().equals(this.getClass())))
	    	return false;

	    DomainEvent other = (DomainEvent) obj;
	    for (Field f : getFields()) {
	    	if (!(getValue(f).equals(other.getValue(f))))
	    		return false;
	    }
	    return true;
	}

	@Override
	public int hashCode() {
    	int code = 0;
	    for (Field f : getFields()) {
	    	code = code | getValue(f).hashCode();
	    }
	    return code;
	}

	protected Field[] getFields() {
	    Field[] fields = this.getClass().getDeclaredFields();
	    for (Field field : fields) {
	        field.setAccessible(true);
        }
		return fields;
	}

	private Object getValue(Field f) {
	    try {
	        return f.get(this);
	    } catch (IllegalArgumentException | IllegalAccessException e) {
	        throw new RuntimeException(e);
	    }
	}

	public String getId() {
        try {
        	Field field = getClass().getDeclaredField("id");
	        field.setAccessible(true);
	        return (String) field.get(this);
        } catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
	        throw new RuntimeException(e);
        }
    }

	public void applyTo(Object object) {
    }
}
