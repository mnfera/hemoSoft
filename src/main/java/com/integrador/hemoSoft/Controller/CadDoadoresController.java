package com.integrador.hemoSoft.Controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.integrador.hemoSoft.model.Doador;
import com.integrador.hemoSoft.model.TipoSanguineo;
import com.integrador.hemoSoft.repository.Doadores;
import com.integrador.hemoSoft.repository.filter.DoadorFilter;

@Controller
@RequestMapping("/doadores")
public class CadDoadoresController {

	private static final String Tela_Cad_Doador = "CadastroDoadores";

	@Autowired
	private Doadores doadores;

	@RequestMapping("/novo")
	public ModelAndView novo() {
		ModelAndView mv = new ModelAndView(Tela_Cad_Doador);
		mv.addObject(new Doador());
		return mv;
	}

	@RequestMapping(method = RequestMethod.POST)
	public String salvar(@Validated Doador doador, Errors errors, RedirectAttributes attributes) {
		if (errors.hasErrors()) {
			return Tela_Cad_Doador;
		}
		doadores.save(doador);
		attributes.addFlashAttribute("mensagem", "Doador Salvo com Sucesso!");
		return "redirect:/doadores/novo";
	}

	@RequestMapping
	public ModelAndView pesquisa(@ModelAttribute("filtro") DoadorFilter filtro) {
		String nome = filtro.getDescricao() == null ? "%" : filtro.getDescricao();
		List<Doador> todosDoadores = doadores.findByNomeContaining(nome);
		ModelAndView mv = new ModelAndView("PesquisaDoadores");
		mv.addObject("doadores", todosDoadores);
		return mv;
	}

	@RequestMapping("{id}")
	public ModelAndView edicao(@PathVariable("id") Doador doador) {
		ModelAndView mv = new ModelAndView(Tela_Cad_Doador);
		mv.addObject(doador);
		return mv;
	}

	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	public String excluir(@PathVariable Long id, RedirectAttributes attributes) {
		doadores.deleteById(id);
		attributes.addFlashAttribute("mensagem", "Doador excluído com sucesso!");
		return "redirect:/doadores";
	}

	@ModelAttribute("todosTipoS")
	public List<TipoSanguineo> todosTipoS() {
		return Arrays.asList(TipoSanguineo.values());

	}

}
