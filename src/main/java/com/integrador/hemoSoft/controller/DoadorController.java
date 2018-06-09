package com.integrador.hemoSoft.controller;

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
import com.integrador.hemoSoft.repository.filter.DoadorFilter;
import com.integrador.hemoSoft.service.DoadorService;

@Controller
@RequestMapping("/doadores")
public class DoadorController {

	private static final String Tela_Cad_Doador = "doador/cadastro";
	private static final String Tela_List_Doador = "doador/lista";
	
	@Autowired
	private DoadorService service; //	Injeta a classe de serviços
	
	
	//Vai para tela de adição de doador
	@RequestMapping("/novo")
	public ModelAndView add(Doador doador) {
		
		ModelAndView mv = new ModelAndView(Tela_Cad_Doador);
		mv.addObject("doador", doador);
		
		return mv;
	}
	
	//Vai para tela de listagem de doadores
	@RequestMapping("/lista")
	public ModelAndView findAll(@ModelAttribute("filtro") DoadorFilter filtro) {
		ModelAndView mv = new ModelAndView(Tela_List_Doador);
		mv.addObject("doadores", service.findAll());
		
		return mv;
	}
	
	//Recebe um objeto preenchido do Thymeleaf e valida 
	//Se tudo estiver ok, salva e volta para tela de listagem
	//Se houver erro, retorna para tela atual exibindo as mensagens de erro
	@RequestMapping(method = RequestMethod.POST)
	public String salvar(@Validated Doador doador, Errors errors, RedirectAttributes attributes) {
		
		if (errors.hasErrors()) {
			return Tela_Cad_Doador;
		}
		
		service.save(doador);
		attributes.addFlashAttribute("mensagem", "Doador Salvo com Sucesso!");
		
		return "redirect:/doadores/lista";
	}
	
	//Vai para tela de edição (mesma tela de adição, contudo é enviado para a view um objeto que já existe)
	@RequestMapping("{id}")
	public ModelAndView edicao(@PathVariable("id") Long id) {
		
		return add(service.findOne(id));
	}
	
	//Exclui um doador por seu ID e redireciona para a tela de listagem
	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	public String excluir(@PathVariable Long id, RedirectAttributes attributes) {
		
		service.delete(id);
		attributes.addFlashAttribute("mensagem", "Doador excluído com sucesso!");
		
		return "redirect:/doadores/lista";
	}
	
	@RequestMapping
	public ModelAndView pesquisa(@ModelAttribute("filtro") DoadorFilter filtro) {
		String nome = filtro.getDescricao() == null ? "%" : filtro.getDescricao();
		ModelAndView mv = new ModelAndView(Tela_List_Doador);
		mv.addObject("doadores", service.findByNome(nome));
		return mv;
	}

	@ModelAttribute("todosTipoS")
	public List<TipoSanguineo> todosTipoS() {
		return Arrays.asList(TipoSanguineo.values());

	}

}
