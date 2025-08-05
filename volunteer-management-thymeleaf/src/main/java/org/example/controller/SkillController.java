package org.example.controller;

import java.util.List;
import java.util.Optional;

import org.example.entity.Skill;
import org.example.service.SkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/skills")
public class SkillController {

    @Autowired
    private SkillService skillService;

    @GetMapping
    public String listSkills(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Integer minVolunteers,
            Model model) {
        List<Skill> skills = skillService.getAllSkills();

        if (name != null && !name.isEmpty()) {
            skills = skills.stream()
                    .filter(s -> s.getName() != null && s.getName().toLowerCase().contains(name.toLowerCase()))
                    .toList();
        }
        if (minVolunteers != null) {
            skills = skills.stream()
                    .filter(s -> s.getSkillLevels() != null && s.getSkillLevels().size() >= minVolunteers)
                    .toList();
        }

        model.addAttribute("skills", skills);
        model.addAttribute("title", "Danh sách Kỹ năng");
        return "skill/list";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("skill", new Skill());
        model.addAttribute("title", "Thêm Kỹ năng mới");
        return "skill/form";
    }

    @PostMapping("/add")
    public String addSkill(@ModelAttribute Skill skill) {
        skillService.saveSkill(skill);
        return "redirect:/skills";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Optional<Skill> skillOptional = skillService.getSkillById(id);
        if (skillOptional.isEmpty()) {
            return "redirect:/skills";
        }
        model.addAttribute("skill", skillOptional.get());
        model.addAttribute("title", "Chỉnh sửa Kỹ năng");
        return "skill/form";
    }

    @PostMapping("/edit/{id}")
    public String updateSkill(@PathVariable Long id, @ModelAttribute Skill skill) {
        skill.setId(id);
        skillService.saveSkill(skill);
        return "redirect:/skills";
    }

    @GetMapping("/delete/{id}")
    public String deleteSkill(@PathVariable Long id) {
        skillService.deleteSkill(id);
        return "redirect:/skills";
    }
}
