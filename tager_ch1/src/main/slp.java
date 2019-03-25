package main ;



abstract class Stm {
	abstract int maxargs();
	abstract Table interpStm(Table t);
}

class CompoundStm extends Stm {
   Stm stm1, stm2;
   CompoundStm(Stm s1, Stm s2) {stm1=s1; stm2=s2;}
   int maxargs()
   {
	   return Math.max(stm1.maxargs(),stm2.maxargs());
   }
   Table interpStm(Table t) {
	   return stm2.interpStm(stm1.interpStm(t));
   }
   
}

class AssignStm extends Stm {
   String id; Exp exp;
   AssignStm(String i, Exp e) {id=i; exp=e;}
   int maxargs()
   {
	   return exp.maxargs();
   }
   Table interpStm(Table t) {
	   IntAndTable it =  exp.interpExp(t);
	   return new Table(id,it.i,t);
   }
}

class PrintStm extends Stm {
   ExpList exps;
   PrintStm(ExpList e) {exps=e;}
   int maxargs()
   {
	   return Math.max(exps.size(),exps.maxargs());
   }
   Table interpStm(Table t) {
	   ExpList head = exps;
	   Table current_table = t;
	   while( head != null) {
		   
		   IntAndTable head_it = head.interpExp(current_table);
		   System.out.print(head_it.i);
		   head =  head.next();
		   current_table = head_it.t;
		   if (head != null) System.out.print(" ");
	   }
	   System.out.println();
	   return current_table;
   }
}

abstract class Exp {
	abstract int maxargs();
	abstract IntAndTable interpExp(Table t);
}

class IdExp extends Exp {
   String id;
   IdExp(String i) {id=i;}
   int maxargs()
   {
	   return 0;
   }
   IntAndTable interpExp(Table t) {
	   return new IntAndTable(t.lookup(id),t);
   }
}

class NumExp extends Exp {
   int num;
   NumExp(int n) {num=n;}
   int maxargs()
   {
	   return 0;
   }
   IntAndTable interpExp(Table t) {
	   return new IntAndTable(num,t);
   }
   
}

class OpExp extends Exp {
   Exp left, right; int oper;
   final static int Plus=1,Minus=2,Times=3,Div=4;
   OpExp(Exp l, int o, Exp r) {left=l; oper=o; right=r;}
    int maxargs()
   {
	   return Math.max(left.maxargs(),right.maxargs() );
   }
   IntAndTable interpExp(Table t) {
	   IntAndTable left_it = left.interpExp(t);
	   IntAndTable right_it = right.interpExp(left_it.t);
		
	   int l = left_it.i;
	   int r = right_it.i;
		
	   int num = (Plus == oper) ? l + r : (Minus == oper) ? l - r : (Times == oper) ? l * r : (Div == oper) ? l / r : 0;
	   return new IntAndTable(num, right_it.t);
	}
   
}

class EseqExp extends Exp {
   Stm stm; Exp exp;
   EseqExp(Stm s, Exp e) {stm=s; exp=e;}
   int maxargs()
   {
	   return Math.max(stm.maxargs(),exp.maxargs() );
   }
   IntAndTable interpExp(Table t) {
	   Table t2 = stm.interpStm(t);
	   return exp.interpExp(t2);
	   
   }
}

abstract class ExpList {
	abstract int maxargs();
	abstract int size();
	abstract IntAndTable interpExp(Table t);
	ExpList next() {
		return null;
	}
}

class PairExpList extends ExpList {
   Exp head; ExpList tail;
   public PairExpList(Exp h, ExpList t) {head=h; tail=t;}
   // ExpList derivation exp; exp -> (stm,Exp); stm -> print(Ex[List)
   // so here is not the end
   int maxargs()
   {
	   return Math.max(head.maxargs(),tail.maxargs());
   }
   int size()
   {
	   return 1 + tail.size();
   }
   ExpList next() {
		return tail;
	}
   IntAndTable interpExp(Table t) {
	   return head.interpExp(t);
   }
   
}

class LastExpList extends ExpList {
   Exp head; 
   public LastExpList(Exp h) {head=h;}
   int maxargs()
   {
	   return head.maxargs();
   }
   int size()
   {
	   return 1 ;
   }
   IntAndTable interpExp(Table t) {
	   return head.interpExp(t);
   }
}
