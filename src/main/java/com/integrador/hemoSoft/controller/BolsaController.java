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

import com.integrador.hemoSoft.model.Bolsa;
import com.integrador.hemoSoft.model.TipoSanguineo;
import com.integrador.hemoSoft.service.BolsaService;
@Controller
@RequestMapping("/bolsas")
public class BolsaController {
	
	private static final String Tela_Cad_Bolsa = "bolsa/cadastro";
	private static final String Tela_List_Bolsa = "bolsa/lista";	
	
	@Autowired
	private BolsaService service; //	Injeta a classe de serviços
	
	//Vai para tela de adição de bolsa
	@RequestMapping("/novo")
	public ModelAndView add(Bolsa bolsa) {
		
		ModelAndView mv = new ModelAndView(Tela_Cad_Bolsa);
		mv.addObject("bolsa", bolsa);
		
		return mv;
	}
	
	//Vai para tela de listagem de bolsas
	@RequestMapping("/lista")
	public ModelAndView findAll() {		
		ModelAndView mv = new ModelAndView(Tela_List_Bolsa);
		mv.addObject("bolsas", service.findAll());
		
		return mv;
	}
	
	//Recebe um objeto preenchido do Thymeleaf e valida 
	//Se tudo estiver ok, salva e volta para tela de listagem
	//Se houver erro, retorna para tela atual exibindo as mensagens de erro
	@RequestMapping(method = RequestMethod.POST)
	public String salvar(@Validated Bolsa bolsa, Errors errors, RedirectAttributes attributes) {
		
		if (errors.hasErrors()) {
			return Tela_Cad_Bolsa;
		}
		
		service.save(bolsa);
		attributes.addFlashAttribute("mensagem", "Bolsa Salva com Sucesso!");
		
		return "redirect:/bolsas/lista";
	}
	
	//Vai para tela de edição (mesma tela de adição, contudo é enviado para a view um objeto que já existe)
	@RequestMapping("{id}")
	public ModelAndView edicao(@PathVariable("id") Long id) {
		
		return add(service.findOne(id));
	}
	
	//Exclui uma bolsa por seu ID e redireciona para a tela de listagem
	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	public String excluir(@PathVariable Long id, RedirectAttributes attributes) {
		
		service.delete(id);
		attributes.addFlashAttribute("mensagem", "Bolsa excluída com sucesso!");
		
		return "redirect:/bolsas/lista";
	}

	@ModelAttribute("todosTipoS")
	public List<TipoSanguineo> todosTipoS() {
		return Arrays.asList(TipoSanguineo.values());

	}

}
