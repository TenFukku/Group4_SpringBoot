package org.example.controller;

import org.example.service.CampaignService;
import org.example.service.SkillService;
import org.example.service.VolunteerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @Autowired
    private VolunteerService volunteerService;

    @Autowired
    private CampaignService campaignService;

    @Autowired
    private SkillService skillService;

    @GetMapping("/")
    public String home(Model model) {
        long totalVolunteers = volunteerService.getAllVolunteers().size();
        long totalCampaigns = campaignService.getAllCampaigns().size();
        long totalSkills = skillService.getAllSkills().size();
        long activeCampaigns = campaignService.findActiveCampaigns().size();

        model.addAttribute("totalVolunteers", totalVolunteers);
        model.addAttribute("totalCampaigns", totalCampaigns);
        model.addAttribute("totalSkills", totalSkills);
        model.addAttribute("activeCampaigns", activeCampaigns);
        model.addAttribute("title", "Trang chủ - Quản lý Tình nguyện viên");

        model.addAttribute("profileImage", "/images/avatars/001.jpg");
        model.addAttribute("isActive", false);
        
        return "home";
    }
}