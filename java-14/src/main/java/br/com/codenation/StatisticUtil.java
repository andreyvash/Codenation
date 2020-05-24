package br.com.codenation;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class StatisticUtil {

	public static int average(int[] elements) {
		int soma = 0;
		for (int i = 0; i < elements.length; i++)
		{
			soma += elements[i];
		}
		return soma/elements.length;
	}

	public static int mode(int[] elements) {
		int[] freq = new int[elements.length];

		for(int i = 0; i < elements.length; i++)
		{
			freq[i] = 0;
		}

		for(int i = 0; i < elements.length; i++)
		{
			freq[elements[i]] += 1;
		}

		int maior = 0;
		for(int i = 0; i < elements.length; i++)
		{
			if(freq[i] > maior)
				maior = i;
		}

		return maior;
	}

	public static int median(int[] elements) {

		int median;

		if(elements.length % 2 == 0)
			median = (elements[elements.length/2 - 1] + elements[elements.length/2]);
		else
			median = elements[(elements.length - 1) /2 ];

		return median;
	}
}