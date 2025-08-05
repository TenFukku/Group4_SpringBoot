package org.example.controller;

import java.util.List;
import java.util.Optional;

import org.example.dto.CampaignDto;
import org.example.entity.Campaign;
import org.example.service.CampaignService;
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
@RequestMapping("/campaigns")
public class CampaignController {

    @Autowired
    private CampaignService campaignService;

    @GetMapping
    public String listCampaigns(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String location,
            @RequestParam(required = false) String status,
            Model model) {
        List<Campaign> campaigns = campaignService.getAllCampaigns();

        if (name != null && !name.isEmpty()) {
            campaigns = campaigns.stream()
                    .filter(c -> c.getName() != null && c.getName().toLowerCase().contains(name.toLowerCase()))
                    .toList();
        }
        if (location != null && !location.isEmpty()) {
            campaigns = campaigns.stream()
                    .filter(c -> c.getLocation() != null && c.getLocation().toLowerCase().contains(location.toLowerCase()))
                    .toList();
        }
        if ("active".equals(status)) {
            java.util.Date now = new java.util.Date();
            campaigns = campaigns.stream()
                    .filter(c -> c.getStartDate().before(now) && c.getEndDate().after(now))
                    .toList();
        } else if ("upcoming".equals(status)) {
            java.util.Date now = new java.util.Date();
            campaigns = campaigns.stream()
                    .filter(c -> c.getStartDate().after(now))
                    .toList();
        } else if ("ended".equals(status)) {
            java.util.Date now = new java.util.Date();
            campaigns = campaigns.stream()
                    .filter(c -> c.getEndDate().before(now))
                    .toList();
        }

        model.addAttribute("campaigns", campaigns);
        model.addAttribute("title", "Danh sách Chiến dịch");
        return "campaign/list";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("campaign", new CampaignDto());
        model.addAttribute("title", "Thêm Chiến dịch");
        return "campaign/form";
    }

    @PostMapping("/add")
    public String addCampaign(@Valid @ModelAttribute("campaign") CampaignDto campaignDto,
                             BindingResult result,
                             RedirectAttributes redirectAttributes) {
        
        if (result.hasErrors()) {
            return "campaign/form";
        }

        Campaign campaign = new Campaign();
        campaign.setName(campaignDto.getName());
        campaign.setLocation(campaignDto.getLocation());
        campaign.setStartDate(campaignDto.getStartDate());
        campaign.setEndDate(campaignDto.getEndDate());

        campaignService.saveCampaign(campaign);
        
        redirectAttributes.addFlashAttribute("success", "Thêm chiến dịch thành công!");
        return "redirect:/campaigns";
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable Long id, Model model) {
        Optional<Campaign> campaignOpt = campaignService.getCampaignById(id);
        
        if (campaignOpt.isEmpty()) {
            return "redirect:/campaigns";
        }

        Campaign campaign = campaignOpt.get();
        CampaignDto campaignDto = new CampaignDto();
        campaignDto.setId(campaign.getId());
        campaignDto.setName(campaign.getName());
        campaignDto.setLocation(campaign.getLocation());
        campaignDto.setStartDate(campaign.getStartDate());
        campaignDto.setEndDate(campaign.getEndDate());

        model.addAttribute("campaign", campaignDto);
        model.addAttribute("title", "Sửa Chiến dịch");
        return "campaign/form";
    }

    @PostMapping("/{id}/edit")
    public String editCampaign(@PathVariable Long id,
                              @Valid @ModelAttribute("campaign") CampaignDto campaignDto,
                              BindingResult result,
                              RedirectAttributes redirectAttributes) {
        
        if (result.hasErrors()) {
            return "campaign/form";
        }

        Optional<Campaign> campaignOpt = campaignService.getCampaignById(id);
        if (campaignOpt.isEmpty()) {
            return "redirect:/campaigns";
        }

        Campaign campaign = campaignOpt.get();
        campaign.setName(campaignDto.getName());
        campaign.setLocation(campaignDto.getLocation());
        campaign.setStartDate(campaignDto.getStartDate());
        campaign.setEndDate(campaignDto.getEndDate());

        campaignService.saveCampaign(campaign);
        
        redirectAttributes.addFlashAttribute("success", "Cập nhật chiến dịch thành công!");
        return "redirect:/campaigns";
    }

    @GetMapping("/{id}")
    public String showDetail(@PathVariable Long id, Model model) {
        Optional<Campaign> campaignOpt = campaignService.getCampaignById(id);
        
        if (campaignOpt.isEmpty()) {
            return "redirect:/campaigns";
        }

        Campaign campaign = campaignOpt.get();
        model.addAttribute("campaign", campaign);
        model.addAttribute("title", "Chi tiết Chiến dịch");
        return "campaign/detail";
    }

    @PostMapping("/{id}/delete")
    public String deleteCampaign(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            campaignService.deleteCampaign(id);
            redirectAttributes.addFlashAttribute("success", "Xóa chiến dịch thành công!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Không thể xóa chiến dịch: " + e.getMessage());
        }
        return "redirect:/campaigns";
    }

    @GetMapping("/search")
    public String searchCampaigns(@RequestParam String name, Model model) {
        List<Campaign> campaigns = campaignService.findByNameContaining(name);
        model.addAttribute("campaigns", campaigns);
        model.addAttribute("searchTerm", name);
        model.addAttribute("title", "Kết quả tìm kiếm");
        return "campaign/list";
    }

    @GetMapping("/active")
    public String showActiveCampaigns(Model model) {
        List<Campaign> campaigns = campaignService.findActiveCampaigns();
        model.addAttribute("campaigns", campaigns);
        model.addAttribute("title", "Chiến dịch đang hoạt động");
        return "campaign/list";
    }
}