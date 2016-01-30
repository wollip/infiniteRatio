import  java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.Arrays;;

public class InfinityRatio {
	// don't touch this, it is for numFractions only.
	private static ArrayList<Integer> unique;
	
	public static void main(String[] args){
		BigDecimal sum = BigDecimal.valueOf(0);
		BigDecimal inte;
		int temp;
		unique = new ArrayList<Integer>();
		for(int i = 2; i < 1000000; i++){
			temp = numFractions(i);
			sum = sum.add(BigDecimal.valueOf(temp));
			
			inte = BigDecimal.valueOf(i);
			inte = inte.multiply(inte);
			
			System.out.println(temp + " " + i);
			System.out.println(sum.divide(inte, MathContext.DECIMAL32));
		}
		
	}
	
	public static int numFractions(int n){
		int ret = n-1;
		ArrayList<int[]> primeFactors = factorize(n);
		int[] factors = factors(primeFactors);
		for(int i = 1; i < factors.length -1; i++){
			if(unique.get(factors[i]-2) != null){
				ret -= unique.get(factors[i]-2);

			}else{
				numFractions(factors[i]);
			}
		}
		unique.add(n-2,ret);
		
		
		return ret;
	}
	
	public static ArrayList<int[]> factorize(int n){
		ArrayList<int[]> ret = new ArrayList<int[]>();
		
		int points = 0;
		
		for(int i = 2; n!=1; i++){
			
			while(n%i == 0){
				n = n/i;
				points++;			
			}
			
			if(points !=0){
				int[] exp = {i,points};
				ret.add(exp);
				points = 0;
			}
			
		}
		
		//ret.add(n);
		return ret;
	}
	
	public static int[] factors(ArrayList<int[]> primefactorize){
		int size = 1;
		int length = primefactorize.size();
		for(int i = 0; i< length; i++){
			size *= (primefactorize.get(i)[1] + 1);
		}
		//System.out.println("size: "+size);
		int[] ret = new int[size];
		Arrays.fill(ret,1);
		
		int cycle = size;
		int base, power, currentPower, currentIndex , multiply;
		for(int i = 0; i< length; i++){
			power = primefactorize.get(i)[1];
			base = primefactorize.get(i)[0];
			cycle = cycle / (power + 1);
			currentPower = 0;
			currentIndex = 0;
			
			while(currentIndex<size){
				
				multiply = (int) Math.pow(base, currentPower);
				for(int i2 = 0; i2< cycle; i2++){
					ret[currentIndex] *= multiply;
					//System.out.println(ret[currentIndex]);
					currentIndex++;
					
				}
				currentPower++;
				currentPower = currentPower%(power+1);	
			}
			
			
			
			
		}
		
		
		return ret;
		
	}
}
