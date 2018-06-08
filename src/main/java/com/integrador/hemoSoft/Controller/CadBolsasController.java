package com.integrador.hemoSoft.Controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.integrador.hemoSoft.model.Bolsa;
import com.integrador.hemoSoft.model.TipoSanguineo;
import com.integrador.hemoSoft.repository.Bolsas;

@Controller
@RequestMapping("/bolsas")
public class CadBolsasController {
	
	private static final String Tela_Cad_Bolsa = "bolsa/cadastro";
	private static final String Tela_List_Bolsa = "bolsa/lista";
	

	@Autowired
	private Bolsas bolsas;

	@RequestMapping("/novo")
	public ModelAndView novo() {
		ModelAndView mv = new ModelAndView(Tela_Cad_Bolsa);
		mv.addObject(new Bolsa());
		return mv;
	}
	
	//Vai para tela de listagem de bolsas
	@GetMapping("/lista")
	public ModelAndView detail() {		
		List<Bolsa> todasBolsas = bolsas.findAll();
		ModelAndView mv = new ModelAndView(Tela_List_Bolsa);
		mv.addObject("bolsa", todasBolsas);
		
		return mv;
	}

	@RequestMapping(method = RequestMethod.POST)
	public String salvar(@Validated Bolsa bolsa, Errors errors, RedirectAttributes attributes) {
		if (errors.hasErrors()) {
			return Tela_Cad_Bolsa;
		}
		bolsas.save(bolsa);
		attributes.addFlashAttribute("mensagem", "Bolsa Salva com Sucesso!");
		return "redirect:/bolsas/novo";
	}

	@RequestMapping("{id}")
	public ModelAndView edicao(@PathVariable("id") Bolsa bolsa) {
		ModelAndView mv = new ModelAndView(Tela_Cad_Bolsa);
		mv.addObject(bolsa);
		return mv;
	}

	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	public String excluir(@PathVariable Long id, RedirectAttributes attributes) {
		bolsas.deleteById(id);
		attributes.addFlashAttribute("mensagem", "Bolsa excluída com sucesso!");
		return "redirect:/bolsas";
	}

	@ModelAttribute("todosTipoS")
	public List<TipoSanguineo> todosTipoS() {
		return Arrays.asList(TipoSanguineo.values());

	}

}
