package com.example.controllers.api;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.controllers.api.form.bounenkaiForm;
import com.example.model.entities.bounenkai2;
import com.example.model.services.BounenkaiService;
import com.example.model.services.ReportService;

@Controller
public class AcceptanceController {

	@Autowired
	BounenkaiService bounenkaiService;

	@Autowired
	private ReportService reportService;

	// 全件検索し、nameのみ一覧表示
	@Transactional
	@GetMapping(value = "admin/sample/bounenkai")
	String bounenkaiList(@Validated bounenkaiForm form, Model model) {
		//List<bounenkai2> bounenkais = bounenkaiService.findAll();
		Map<String, List<bounenkai2>> bounenkais = bounenkaiService.findAllWithGroupingFirstCharactor();
		model.addAttribute("bounenkais", bounenkais);
		return "admin/sample/bounenkai";
	}

	// flagを更新
	@Transactional
	@PostMapping(value = "/admin/sample/bounenkai")
	public String bounenkaiPost(@Valid bounenkaiForm bounenkaiForm, Model model, BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			model.addAttribute("bounenkaiForm", bounenkaiForm);
			return "admin/sample/bounenkai";
		}
		bounenkaiService.findOne(bounenkaiForm.getId()).ifPresent(bounenkai2 -> {
			bounenkaiForm.setFlag("出席");
			bounenkaiService.update(bounenkai2, bounenkaiForm);
		});

		return "redirect:/admin/sample/bounenkai/?success=true";
	}

	@GetMapping(value = "/management")
	String management(Model model) {
		return "management";
	}

	// 全件検索し、全情報を一覧表示
	@Transactional
	@GetMapping(value = "/eventdetail")
	String detailList(@Validated bounenkaiForm form, Model model) {
		List<bounenkai2> manage = bounenkaiService.findAll();
		model.addAttribute("manage", manage);

		// 出席人数計算
		long present = bounenkaiService.count("出席");
		model.addAttribute("present", present);
		model.addAttribute("absent", manage.size() - present);
		return "eventdetail";
	}

	/**
	 * DownLoad
	 */
	@Transactional
	@RequestMapping(value = "/eventdetail/download", method = RequestMethod.GET)
	public ResponseEntity download() throws UnsupportedEncodingException {
		List<bounenkai2> rows = bounenkaiService.findAll();
		// Set Header
		final HttpHeaders headers = new HttpHeaders() {
			{
				setContentDispositionFormData("filename", URLEncoder.encode("event.xlsx", "UTF-8"));
			}
		};
		// Create excel byte data
		byte[] excelData = reportService.createEventReport(rows);
		;
		return new ResponseEntity<>(excelData, headers, HttpStatus.OK);
	}
}
