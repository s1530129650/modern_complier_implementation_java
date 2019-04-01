
package main ;


class Table
{
	String id;
	int value;
	Table tail;
	Table(String i, int v,Table t)
	{id = i; value = v; tail = t;}
	Table update(String key, int value)
	{
		return new Table(key, value, this);
	}
	int lookup(String key)
	{
		if (id.equals(key))     {return value;}
		else if(tail != null)   {return tail.lookup(key);}
		else   {return 0;}	
	}
	
	
}

class IntAndTable{
	int i;
	Table t;
	IntAndTable(int ii, Table tt){
		i = ii;
		t = tt;
	}
}

class interp {
  static void interp(Stm s) { /* you write this part */ 
	  s.interpStm(null);
	  
  }

  static int maxargs(Stm s) { /* you write this part */
        return s.maxargs();
  }

  public static void main(String args[]) throws java.io.IOException {
     
     System.out.println("maximum number of arguments of any print statement is "+maxargs(prog.prog));
	// interp
     System.out.println("the result of  interp is ");
     interp(prog.prog);
  }
}
