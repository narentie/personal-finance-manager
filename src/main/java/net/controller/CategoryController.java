package net.controller;

import net.model.Category;
import net.service.ItemService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Collections;
import java.util.List;

@Controller
@Transactional
@RequestMapping(value = "/categories")
public class CategoryController {

    private ItemService<Category> itemService;

    @Autowired(required = true)
    @Qualifier(value = "categoryService")
    public void setService(ItemService<Category> itemService) {
        this.itemService = itemService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String list(Model model){
        List<Category> categoryList = this.itemService.list();
        Collections.sort(categoryList, Category.COMPARE_BY_ID);

        model.addAttribute("category", new Category());
        model.addAttribute("categoriesList", categoryList);

        return "categories";
    }

    @RequestMapping(value = "add")
    public String addCategory(@ModelAttribute(value = "category") Category category){
        if (category.getName().equals("")) {
            return "redirect:/categories";
        }

        if (category.getName().length() > 26)
            category.setName(category.getName().substring(0, 25));

        if (category.getDescription().length() > 100)
            category.setDescription(category.getDescription().substring(0, 100));

        if(category.getId() == 0) {
            this.itemService.add(category);
        } else {
            this.itemService.update(category);
        }

        return "redirect:/categories";
    }

    @RequestMapping("remove/{id}")
    public String remove(@PathVariable("id") int id){
        this.itemService.remove(id);

        return "redirect:/categories";
    }

    @RequestMapping("edit/{id}")
    public String edit(@PathVariable("id") int id, Model model){
        model.addAttribute("category", this.itemService.getById(id));
        model.addAttribute("categoriesList", this.itemService.list());

        return "categories";
    }

}
