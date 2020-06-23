package hw2;

public class s20141362hw2 {
	public static void main(String[] args)
	{
		Employee[] employ= new Employee[7];
		
		employ[0]=new Employee(001,"John",27);
		employ[1]=new Employee(002,"Eujin",25);
		employ[2]=new Employee(003,"Alex",26);
		employ[3]=new Employee(004,"Jenny",23);
		employ[4]=new Employee(005,"Tom",25);
		employ[5]=new Manager(001,"Andy",33,"Marketing");
		employ[6]=new Manager(002,"Kate",30,"Sales");
		
		System.out.println("===Employee===");
		for(int i=0;i<5;i++)
		{
			employ[i].prem();
		}
		System.out.println("===Manager===");
		for(int i=5;i<7;i++)
		{
			employ[i].prem();
		}
	}
}

class Employee{
	private long id;
	private String name;
	private int age;
	
	public Employee(long id, String name, int age)
	{
		this.id=id;
		this.name=name;
		this.age=age;
	}
	public void prem() {
		System.out.print("[e00");
		this.em();
		System.out.println("]");
	}
	public void em() {
		System.out.print(id+", "+name+", "+age);
	}
}
class Manager extends Employee{
	private String department;
	
	
	public Manager(long id, String name, int age, String department)
	{
		super(id, name, age);
		this.department=department;
	}
	public void prem() {
		System.out.print("[m00");
		super.em();
		System.out.println(", "+department+"]");
	}
}
