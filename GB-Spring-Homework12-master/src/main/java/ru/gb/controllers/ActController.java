package ru.gb.controllers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.gb.model.Act;
import ru.gb.service.ActService;
import ru.gb.service.FileGateway;

import java.util.List;

@Controller
@AllArgsConstructor
public class ActController {
    private final ActService actService;
    private final FileGateway fileGateway;

    @GetMapping("/acts")
    public String findAll(Model model) {
        List<Act> acts = actService.getAllActs();
        model.addAttribute("acts", acts);
        return "act-list";
    }

    @GetMapping("/act-create")
    public String createActForm(Act ignoredAct) {
        return "act-create";
    }

    @PostMapping("/act-create")
    public String createAct(Act act) {
        actService.createAct(act);
        fileGateway.writeToFile(act.getReportingPeriod() + ".txt", act.toString());
        return "redirect:/acts";
    }

    @GetMapping("/act-delete/{id}")
    public String deleteAct(@PathVariable("id") Long id) {
        actService.deleteAct(id);
        return "redirect:/acts";
    }

    @GetMapping("/act-update/{id}")
    public String getOneAct(@PathVariable("id") Long id, Model model) {
        Act act = actService.getActById(id);
        model.addAttribute("act", act);
        return "act-update";
    }

    @PostMapping("/act-update")
    public String updateAct(Act act) {
        actService.updateAct(act);
        return "redirect:/acts";
    }

    @GetMapping("/find-acts-by-period")
    public String findActsByPeriod(Model model) {
        List<Act> acts = actService.findActByReportingPeriod("February");
        model.addAttribute("acts", acts);
        return "act-list";
    }
}
