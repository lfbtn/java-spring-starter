package br.lb.leal.util;

import org.springframework.data.domain.Page;
import org.springframework.ui.Model;

public final class PageIndex {
	
	public void montarIndicedePaginas(Model model, Page<?> pagina) {
		int current = pagina.getNumber() + 1;
		int begin = Math.max(1, current - 5);
		int end = Math.min(begin + 10, pagina.getTotalPages());
		model.addAttribute("beginIndex", begin);
		model.addAttribute("endIndex", end);
		model.addAttribute("currentIndex", current);
	}

}
