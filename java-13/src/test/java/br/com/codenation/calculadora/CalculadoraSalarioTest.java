
package br.com.codenation.calculadora;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
//import org.junit.jupiter.api.Test;


public class CalculadoraSalarioTest {

	@Test
	public void salarioLiquidoIsNotNull() {
		assertNotNull(new CalculadoraSalario().calcularSalarioLiquido(1000.0));
	}

}