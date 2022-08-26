package name.legkodymov.aws.itemtracker2.controller;

import name.legkodymov.aws.itemtracker2.model.WorkItem;
import name.legkodymov.aws.itemtracker2.repository.WorkItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class WorkItemController {

    private final WorkItemRepository workItemRepository;

    @Autowired
    public WorkItemController(WorkItemRepository workItemRepository) {
        this.workItemRepository = workItemRepository;
    }

    @GetMapping(value = "/signup")
    public String signUp(WorkItem item, Model model) {
        model.addAttribute("item", item);
        return "add-item";
    }

    @PostMapping("/additem")
    public String addItem(@Valid WorkItem item, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add-item";
        }

        workItemRepository.save(item);
        return "redirect:/index";
    }

    @GetMapping("/index")
    public String index(Model model) {
        model.addAttribute("items", workItemRepository.findAll());
        return "index";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, Model model) {
        WorkItem workItem = workItemRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid work item id: " + id));

        model.addAttribute("item", workItem);
        return "update-item";
    }

    @PostMapping("/update/{id}")
    public String update(@PathVariable("id") Long id, @Valid WorkItem workItem, BindingResult result, Model model) {
        if (result.hasErrors()) {
            workItem.setId(id);
            return "update-item";
        }
        workItemRepository.save(workItem);
        return "redirect:/index";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id, Model model) {
        WorkItem workItem = workItemRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid work item id: " + id));
        workItemRepository.delete(workItem);
        return "redirect:/index";
    }
}
