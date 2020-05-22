package br.com.codenation.calculadora;


public class CalculadoraSalario {

	public long calcularSalarioLiquido(double salarioBase) {
		//Use o Math.round apenas no final do método para arredondar o valor final.
		//Documentação do método: https://docs.oracle.com/javase/8/docs/api/java/lang/Math.html#round-double-
		double salarioINSS = calcularInss(salarioBase);
		double salarioLiquido = salarioINSS;
		if(salarioINSS > 3000 && salarioINSS <= 6000)
		{
			salarioLiquido = salarioINSS * 0.925;
		}
		else if (salarioINSS > 6000)
		{
			salarioLiquido = salarioINSS * 0.85;
		}

		return Math.round(salarioLiquido);
	}
	
	
	//Exemplo de método que pode ser criado para separar melhor as responsábilidades de seu algorítmo
	private double calcularInss(double salarioBase) {

		if(salarioBase <= 1500)
		{
			return salarioBase * 0.92;
		}
		else if (salarioBase <= 4000)
		{
			return salarioBase * 0.91;
		}
		else 
		{
			return salarioBase * 0.89;
		}
	}

}
/*Dúvidas ou Problemas?
Manda e-mail para o meajuda@codenation.dev que iremos te ajudar! 
*/