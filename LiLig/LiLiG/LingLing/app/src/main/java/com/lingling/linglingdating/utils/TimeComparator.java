package com.lingling.linglingdating.utils;

import com.lingling.linglingdating.bean.TimeDatas;

import java.util.Comparator;

public class TimeComparator implements Comparator<TimeDatas> {

	@Override
	public int compare(TimeDatas lhs, TimeDatas rhs) {
		return rhs.getDate().compareTo(lhs.getDate());
	}
}
