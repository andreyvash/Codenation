package br.com.codenation.desafioexe;

import java.util.ArrayList;
import java.util.List;

public class DesafioApplication {

	public static List<Integer> fibonacci() 
	{
		List<Integer> fibonacci = new ArrayList<>();

		
		fibonacci.add(0);
		fibonacci.add(1);
		Integer soma = fibonacci.get(fibonacci.size()-1) + fibonacci.get(fibonacci.size() - 2);
		while (soma <= 377)
		{
			fibonacci.add(soma);
			soma = fibonacci.get(fibonacci.size()-1) + fibonacci.get(fibonacci.size() - 2);
		}

		return fibonacci;
	}

	public static Boolean isFibonacci(Integer a) 
	{
		List<Integer> fibonacci = fibonacci();
		
		if(fibonacci.indexOf(a) != -1)
			return true;
		return false;	
	}

}