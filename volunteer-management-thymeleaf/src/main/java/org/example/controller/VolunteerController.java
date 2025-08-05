package org.example.controller;

import java.util.List;
import java.util.Optional;

import org.example.dto.VolunteerDTO;
import org.example.entity.Volunteer;
import org.example.service.VolunteerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/volunteers")
public class VolunteerController {

    @Autowired
    private VolunteerService volunteerService;

    @GetMapping
public String listVolunteers(
        @RequestParam(required = false) String name,
        @RequestParam(required = false) String email,
        @RequestParam(required = false) String status,
        Model model) {
    List<Volunteer> volunteers = volunteerService.getAllVolunteers();

    if (name != null && !name.isEmpty()) {
        volunteers = volunteers.stream()
                .filter(v -> v.getFullName() != null && v.getFullName().toLowerCase().contains(name.toLowerCase()))
                .toList();
    }
    if (email != null && !email.isEmpty()) {
        volunteers = volunteers.stream()
                .filter(v -> v.getEmail() != null && v.getEmail().toLowerCase().contains(email.toLowerCase()))
                .toList();
    }
    if ("active".equals(status)) {
        volunteers = volunteers.stream()
                .filter(v -> v.getSkillLevels() != null && !v.getSkillLevels().isEmpty())
                .toList();
    } else if ("inactive".equals(status)) {
        volunteers = volunteers.stream()
                .filter(v -> v.getSkillLevels() == null || v.getSkillLevels().isEmpty())
                .toList();
    }

    model.addAttribute("volunteers", volunteers);
    model.addAttribute("title", "Danh sách Tình nguyện viên");
    return "volunteer/list";
}

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("volunteer", new VolunteerDTO());
        model.addAttribute("title", "Thêm Tình nguyện viên");
        return "volunteer/form";
    }

    @PostMapping("/add")
    public String addVolunteer(@Valid @ModelAttribute("volunteer") VolunteerDTO volunteerDto,
                              BindingResult result,
                              RedirectAttributes redirectAttributes) {
        
        if (result.hasErrors()) {
            return "volunteer/form";
        }

        Volunteer volunteer = new Volunteer();
        volunteer.setFullName(volunteerDto.getFullName());
        volunteer.setEmail(volunteerDto.getEmail());
        volunteer.setPhone(volunteerDto.getPhone());
        volunteer.setJoinDate(volunteerDto.getJoinDate());

        volunteerService.saveVolunteer(volunteer);
        
        redirectAttributes.addFlashAttribute("success", "Thêm tình nguyện viên thành công!");
        return "redirect:/volunteers";
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable Long id, Model model) {
        Optional<Volunteer> volunteerOpt = volunteerService.getVolunteerById(id);
        
        if (volunteerOpt.isEmpty()) {
            return "redirect:/volunteers";
        }

        Volunteer volunteer = volunteerOpt.get();
        VolunteerDTO volunteerDto = new VolunteerDTO();
        volunteerDto.setId(volunteer.getId());
        volunteerDto.setFullName(volunteer.getFullName());
        volunteerDto.setEmail(volunteer.getEmail());
        volunteerDto.setPhone(volunteer.getPhone());
        volunteerDto.setJoinDate(volunteer.getJoinDate());

        model.addAttribute("volunteer", volunteerDto);
        model.addAttribute("title", "Sửa Tình nguyện viên");
        return "volunteer/form";
    }

    @PostMapping("/{id}/edit")
    public String editVolunteer(@PathVariable Long id,
                               @Valid @ModelAttribute("volunteer") VolunteerDTO volunteerDto,
                               BindingResult result,
                               RedirectAttributes redirectAttributes) {
        
        if (result.hasErrors()) {
            return "volunteer/form";
        }

        Optional<Volunteer> volunteerOpt = volunteerService.getVolunteerById(id);
        if (volunteerOpt.isEmpty()) {
            return "redirect:/volunteers";
        }

        Volunteer volunteer = volunteerOpt.get();
        volunteer.setFullName(volunteerDto.getFullName());
        volunteer.setEmail(volunteerDto.getEmail());
        volunteer.setPhone(volunteerDto.getPhone());
        volunteer.setJoinDate(volunteerDto.getJoinDate());

        volunteerService.saveVolunteer(volunteer);
        
        redirectAttributes.addFlashAttribute("success", "Cập nhật tình nguyện viên thành công!");
        return "redirect:/volunteers";
    }

    @GetMapping("/{id}")
    public String showDetail(@PathVariable Long id, Model model) {
        Optional<Volunteer> volunteerOpt = volunteerService.getVolunteerById(id);
        
        if (volunteerOpt.isEmpty()) {
            return "redirect:/volunteers";
        }

        Volunteer volunteer = volunteerOpt.get();
        model.addAttribute("volunteer", volunteer);
        model.addAttribute("title", "Chi tiết Tình nguyện viên");
        return "volunteer/detail";
    }

    @PostMapping("/{id}/delete")
    public String deleteVolunteer(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            volunteerService.deleteVolunteer(id);
            redirectAttributes.addFlashAttribute("success", "Xóa tình nguyện viên thành công!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Không thể xóa tình nguyện viên: " + e.getMessage());
        }
        return "redirect:/volunteers";
    }

    @GetMapping("/search")
    public String searchVolunteers(@RequestParam String name, Model model) {
        List<Volunteer> volunteers = volunteerService.findByFullNameContaining(name);
        model.addAttribute("volunteers", volunteers);
        model.addAttribute("searchTerm", name);
        model.addAttribute("title", "Kết quả tìm kiếm");
        return "volunteer/list";
    }
}