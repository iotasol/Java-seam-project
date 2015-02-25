package com.iotasol.util;

import java.util.ArrayList;

import javax.faces.model.SelectItem;

/**
 * 
 * @author Iotasol Technologies Pvt. Ltd.
 *
 * @param <E>
 */
public class SelectItemArrayList<E> extends ArrayList<E> 
{

	public boolean equals(Object second)
	{
		SelectItem firstObj = null;
		SelectItem secondObj = (SelectItem) second;
		
		for(Object temp : this)
		{
			firstObj = (SelectItem) temp;
			
			if( firstObj.getLabel().equalsIgnoreCase(secondObj.getLabel()) )
				return true;
		}
		return false;
	}
	
	public String retireveLabel(long value)
	{
		SelectItem tempObj = null;
		
		for(Object temp : this)
		{
			tempObj = (SelectItem) temp;
			
			if( Long.valueOf(tempObj.getValue().toString()) == value )
				return tempObj.getLabel().toString();
		}
		return null;
	}
}
