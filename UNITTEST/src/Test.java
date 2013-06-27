import java.util.PriorityQueue;


public class Test {
	public static void main(String[] arg)
	{
		PriorityQueue<Unit> units = new PriorityQueue<Unit>();
		units.add(new Unit("Test1",1));
		units.add(new Unit("Test2",2));
		units.add(new Unit("Test3",2));
		int x =units.size();
		units.poll();
		units.size();
	}
}


class Unit implements Comparable<Unit>{
	public String field;
	public int it;
	public Unit(String field, int it) {
		// TODO Auto-generated constructor stub
		this.field = field;
		this.it = it;
	}
	@Override
	public int compareTo(Unit o) {
		// TODO Auto-generated method stub
		return it;
	}
}
