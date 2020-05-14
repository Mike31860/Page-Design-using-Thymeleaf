package com.example.demo.Controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.Model.TsscGame;
import com.example.demo.Model.TsscTopic;
import com.example.demo.Service.GameService;
import com.example.demo.Service.TopicService;
import com.example.demo.Service.TopicServiceImpt;
import com.example.demo.Validate.TopicValidar;

@Controller
public class TopicController {

	private TopicService servicio;
	private GameService servicio2;

	@Autowired
	public TopicController(TopicService Service, GameService es) {
		this.servicio = Service;
		this.servicio2 = es;
	}

	@GetMapping("/TemaCap/")
	public String principalTopic(Model model) {
		model.addAttribute("topics", servicio.findAlll());
		return "TemaCap/principalTopic";
	}

	@GetMapping("/TemaCap/agregar")
	public String agregarTopicPrincipal(Model model) {
		model.addAttribute("tsscTopic", new TsscTopic());
		return "TemaCap/agregarTopic";
	}

	@GetMapping("/TemaCap/editar/{id}")
	public String mostrarGameAEditarPrincipal(@PathVariable("id") long id, Model modeloPrincipal) {
		TsscTopic topic = servicio.findTopicById(id);
		if (topic == null)
			throw new IllegalArgumentException("Id del juego Invalido:" + id);

		modeloPrincipal.addAttribute("tsscTopic", topic);
		modeloPrincipal.addAttribute("name", topic.getName());
		modeloPrincipal.addAttribute("description", topic.getDescription());
		modeloPrincipal.addAttribute("defaultGroups", topic.getDefaultGroups());
		modeloPrincipal.addAttribute("defaultSprints", topic.getDefaultSprints());
		modeloPrincipal.addAttribute("groupPrefix", topic.getGroupPrefix());

		return "TemaCap/EditarTopic";
	}

	@PostMapping("/TemaCap/editar/{id}")
	public String actualizarTemaPost(@PathVariable("id") long id,
			@RequestParam(value = "action", required = true) String opcion,
			@Validated(TopicValidar.class) TsscTopic tema, BindingResult bind, Model modeloPrincipal) {

		if (!opcion.equals("CancelarOperacion")) {
			if (bind.hasErrors()) {

				modeloPrincipal.addAttribute("name", tema.getName());
				modeloPrincipal.addAttribute("description", tema.getDescription());
				modeloPrincipal.addAttribute("defaultGroups", tema.getDefaultGroups());
				modeloPrincipal.addAttribute("defaultSprints", tema.getDefaultSprints());
				modeloPrincipal.addAttribute("groupPrefix", tema.getGroupPrefix());

				return "TemaCap/EditarTopic";
			} else {

				servicio.AnadirTopic(tema);

				return "redirect:/TemaCap/";

			}
			// return "redirect:/TemaCap/";
		}
		return "redirect:/TemaCap/";
	}

//	
	@PostMapping("/TemaCap/agregar")
	public String agregarTemaNuevo(@Validated(TopicValidar.class) TsscTopic agregar, BindingResult bind,
			Model modeloPrincipal, @RequestParam(value = "action", required = true) String opcion) {

		if (!opcion.equals("Cancelar")) {
			if (bind.hasErrors()) {

				modeloPrincipal.addAttribute("name", agregar.getName());
				modeloPrincipal.addAttribute("description", agregar.getDescription());
				modeloPrincipal.addAttribute("defaultGroups", agregar.getDefaultGroups());
				modeloPrincipal.addAttribute("defaultSprints", agregar.getDefaultSprints());
				modeloPrincipal.addAttribute("groupPrefix", agregar.getGroupPrefix());

				return "TemaCap/agregarTopic";
			} else {

				servicio.AnadirTopic(agregar);
				return "redirect:/TemaCap/";

			}

		} else {

			modeloPrincipal.addAttribute("topics", servicio.findAlll());
			return "TemaCap/principalTopic";
		}

	}

	@GetMapping("/TemaCap/eliminar/{id}")
	public String deleteTopic(@PathVariable("id") long id) {
		TsscTopic encontrado = servicio.findTopicById(id);

		for (TsscGame juegos : servicio2.findAlll()) {
			if (juegos.getTsscTopic() != null && juegos.getTsscTopic().equals(encontrado)) {
				juegos.setTsscTopic(null);
			}

		}

		servicio.eliminarTopic(encontrado);
		return "redirect:/";
	}
//
//	

}
