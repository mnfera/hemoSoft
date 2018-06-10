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

import com.integrador.hemoSoft.model.Enfermeiro;
import com.integrador.hemoSoft.model.TipoSanguineo;
import com.integrador.hemoSoft.service.EnfermeiroService;

@Controller
@RequestMapping("/enfermeiros")
public class EnfermeiroController {
	
	private static final String Tela_Cad_Enfermeiro = "enfermeiro/cadastro";
	private static final String Tela_List_Enfermeiro = "enfermeiro/lista";
	
	@Autowired
	private EnfermeiroService service; //	Injeta a classe de serviços
	
	//Vai para tela de adição de enfermeiro
	@RequestMapping("/novo")
	public ModelAndView add(Enfermeiro enfermeiro) {
		
		ModelAndView mv = new ModelAndView(Tela_Cad_Enfermeiro);
		mv.addObject("enfermeiro", enfermeiro);
		
		return mv;
	}
	
	//Vai para tela de listagem de enfermeiros
	@RequestMapping("/lista")
	public ModelAndView findAll() {
		ModelAndView mv = new ModelAndView(Tela_List_Enfermeiro);
		mv.addObject("enfermeiros", service.findAll());
		
		return mv;
	}
	
	//Recebe um objeto preenchido do Thymeleaf e valida 
	//Se tudo estiver ok, salva e volta para tela de listagem
	//Se houver erro, retorna para tela atual exibindo as mensagens de erro
	@RequestMapping(method = RequestMethod.POST)
	public String salvar(@Validated Enfermeiro enfermeiro, Errors errors, RedirectAttributes attributes) {
		
		if (errors.hasErrors()) {
			return Tela_Cad_Enfermeiro;
		}
		
		service.save(enfermeiro);
		attributes.addFlashAttribute("mensagem", "Enfermeiro Salvo com Sucesso!");
		
		return "redirect:/enfermeiros/lista";
	}
	
	//Vai para tela de edição (mesma tela de adição, contudo é enviado para a view um objeto que já existe)
	@RequestMapping("{id}")
	public ModelAndView edicao(@PathVariable("id") Long id) {
		
		return add(service.findOne(id));
	}
	
	//Exclui um enfermeiro por seu ID e redireciona para a tela de listagem
	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	public String excluir(@PathVariable Long id, RedirectAttributes attributes) {
		
		service.delete(id);
		attributes.addFlashAttribute("mensagem", "Enfermeiro excluído com sucesso!");
		
		return "redirect:/enfermeiros/lista";
	}

	@ModelAttribute("todosTipoS")
	public List<TipoSanguineo> todosTipoS() {
		return Arrays.asList(TipoSanguineo.values());

	}

}
