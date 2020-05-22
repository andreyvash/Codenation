package br.com.codenation;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import br.com.codenation.desafio.annotation.Desafio;
import br.com.codenation.desafio.app.MeuTimeInterface;
import br.com.codenation.Time;
import br.com.codenation.Jogador;
import br.com.codenation.desafio.exceptions.CapitaoNaoInformadoException;
import br.com.codenation.desafio.exceptions.IdentificadorUtilizadoException;
import br.com.codenation.desafio.exceptions.JogadorNaoEncontradoException;
import br.com.codenation.desafio.exceptions.TimeNaoEncontradoException;

public class DesafioMeuTimeApplication implements MeuTimeInterface {

	private List<Time> times;

	private Time buscaTimePorId(List<Time> times, Long id){
        for(Time time : times){
            if(time.getId() == id)
                return time;
        }
        return null;
	}

	private Jogador buscaJogadorPorId(List<Time> times, Long id){
        for(Time time : times){
			for(Jogador jogador : time.getJogadores())
            	if(jogador.getId() == id)
              	  return jogador;
        }
        return null;
	}
	
	@Desafio("incluirTime")
	public void incluirTime(Long id, String nome, LocalDate dataCriacao, String corUniformePrincipal, String corUniformeSecundario) {

		Time time = buscaTimePorId(times, id);
		if (time == null)
			time = new Time(id, nome, dataCriacao, corUniformePrincipal, corUniformeSecundario);
		else
			throw new IdentificadorUtilizadoException();

		times.add(time);
	}

	@Desafio("incluirJogador")
	public void incluirJogador(Long id, Long idTime, String nome, LocalDate dataNascimento, Integer nivelHabilidade, BigDecimal salario) {

		Jogador jogador = buscaJogadorPorId(times, id);

		if(jogador == null)
			jogador = new Jogador(id, idTime, nome, dataNascimento, nivelHabilidade, salario);
		else
			throw new IdentificadorUtilizadoException();

		Time time = buscaTimePorId(times, idTime);

		if (time == null)
			throw new TimeNaoEncontradoException();

		if(time != null){
			List<Jogador> jogadores = time.getJogadores();
			jogadores.add(jogador);
			time.setJogadores(jogadores);
		}

	}

	@Desafio("definirCapitao")
	public void definirCapitao(Long idJogador) {
		Jogador capitao = buscaJogadorPorId(times, idJogador);

		if (capitao == null)
			throw new JogadorNaoEncontradoException();

		Time time = buscaTimePorId(times, capitao.getIdTime());
		time.setCapitao(capitao);
	}

	@Desafio("buscarCapitaoDoTime")
	public Long buscarCapitaoDoTime(Long idTime) {
		Time time = buscaTimePorId(times, idTime);
		if (time == null)
			throw new TimeNaoEncontradoException();

		if(time.getCapitao() == null)
			throw new CapitaoNaoInformadoException();

		return time.getCapitao().getId();
	}

	@Desafio("buscarNomeJogador")
	public String buscarNomeJogador(Long idJogador) {
		Jogador jogador = buscaJogadorPorId(times, idJogador);

		if (jogador == null)
			throw new JogadorNaoEncontradoException();

		return jogador.getNome();
	}

	@Desafio("buscarNomeTime")
	public String buscarNomeTime(Long idTime) {
		Time time = buscaTimePorId(times, idTime);
		if (time == null)
			throw new TimeNaoEncontradoException();

		return time.getNome();
	}

	@Desafio("buscarJogadoresDoTime")
	public List<Long> buscarJogadoresDoTime(Long idTime) {

		Time time = buscaTimePorId(times, idTime);

		if (time == null)
			throw new TimeNaoEncontradoException();

		List<Long> ids = new ArrayList<>();

		for(Jogador jogador : time.getJogadores())
		{
			ids.add(jogador.getId());
		}

		return ids;
	}

	@Desafio("buscarMelhorJogadorDoTime")
	public Long buscarMelhorJogadorDoTime(Long idTime) {

		Time time = buscaTimePorId(times, idTime);

		if (time == null)
			throw new TimeNaoEncontradoException();

		Integer maior = 0;
		Jogador habilidoso = new Jogador();

		for(Jogador jogador: time.getJogadores())
		{
			if (jogador.getNivelHabilidade() > maior) {
				maior = jogador.getNivelHabilidade();
				habilidoso = jogador;
			}
		}
		return habilidoso.getId();
	}

	@Desafio("buscarJogadorMaisVelho")
	public Long buscarJogadorMaisVelho(Long idTime) {

		Time time = buscaTimePorId(times, idTime);

		if (time == null)
			throw new TimeNaoEncontradoException();

		LocalDate maior = LocalDate.now();
		Jogador velho = new Jogador();

		for(Jogador jogador: time.getJogadores())
		{
			if (jogador.getDataNascimento().isBefore(maior)) {
				maior = jogador.getDataNascimento();
				velho = jogador;
			}
		}
		return velho.getId();
	}

	@Desafio("buscarTimes")
	public List<Long> buscarTimes() {
		List<Long> ids = new ArrayList<>();

		for(Time time : times)
		{
			ids.add(time.getId());
		}
		return ids;
	}

	@Desafio("buscarJogadorMaiorSalario")
	public Long buscarJogadorMaiorSalario(Long idTime) {

		Time time = buscaTimePorId(times, idTime);
		if (time == null)
			throw new TimeNaoEncontradoException();

		BigDecimal maior = BigDecimal.ZERO;
		Jogador jogadorRico = new Jogador();
		for(Jogador jogador: time.getJogadores())
		{
			if (jogador.getSalario().compareTo(maior) == 1) {
				maior = jogador.getSalario();
				jogadorRico = jogador;
			}
		}

		return jogadorRico.getId();
	}

	@Desafio("buscarSalarioDoJogador")
	public BigDecimal buscarSalarioDoJogador(Long idJogador) {
		Jogador jogador = buscaJogadorPorId(times, idJogador);

		if (jogador == null)
			throw new JogadorNaoEncontradoException();

		return jogador.getSalario();
	}

	@Desafio("buscarTopJogadores")
	public List<Long> buscarTopJogadores(Integer top) {
		throw new UnsupportedOperationException();
	}

	@Desafio("buscarCorCamisaTimeDeFora")
	public String buscarCorCamisaTimeDeFora(Long timeDaCasa, Long timeDeFora) {
		throw new UnsupportedOperationException();
	}

}
