package hw1;

public class s20141362hw1_2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		int num[]= new int[5];
		int counter=0, match=0;
		
		while(match<3){
			counter++; //반복 횟수
			match=0;//match 다시 초기화

			for(int i=0;i<5;i++)
			{
				num[i]=(int)(50*Math.random());
				//중복확인
				for(int j=0;j<i;j++)
				{
					if(num[i]==num[j])
					{
						i--;
						break;
					}
				}
			}
			for(int i=0;i<5;i++)
			{
				switch(num[i])
				{
					case 7:
					case 18:
					case 32:
					case 37:
					case 44: match++; break;//맞는숫자있을 경우 세줌
					default : break;
				}
			}
		}	
		
		System.out.println("The number of iterations : "+counter);
		System.out.print("My lot numbers are : ");
		
		for(int i=0;i<5;i++)
		{
			System.out.print(" "+num[i]);
		}
	}

}
