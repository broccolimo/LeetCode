package jy;

import java.util.ArrayList;
import java.util.List;

public class T{
	
	
	public static void main(String[] args) {
		List<Class<? extends QA>> list = new ArrayList<>();
		list.add(QA.class);
		list.add(QA.class);
		list.add(QA.class);
	}
	

	
}

class QA{}
class QB extends QA{}
class QC extends QB{}


