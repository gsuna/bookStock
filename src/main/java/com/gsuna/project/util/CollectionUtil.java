package com.gsuna.project.util;

import java.util.List;

public class CollectionUtil {
	
	@SuppressWarnings("rawtypes")
	public static boolean isEmpty(List list) {
		if(list==null || list.size()==0) {
			return true;
		}
		return false;
	}

}
