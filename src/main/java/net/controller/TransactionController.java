package net.controller;

import net.model.Category;
import net.model.Transaction;
import net.service.ItemService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@Controller
@Transactional
@RequestMapping(value = "/transactions")
public class TransactionController {

    private ItemService<Transaction> itemService;
    private ItemService<Category> categoryItemService;

    @Autowired(required = true)
    @Qualifier(value = "categoryService")
    public void setCategoryItemService(ItemService<Category> categoryItemService) {
        this.categoryItemService = categoryItemService;
    }

    @Autowired(required = true)
    @Qualifier(value = "transactionService")
    public void setItemService(ItemService<Transaction> itemService) {
        this.itemService = itemService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String list(Model model) {
        List<Transaction> list = this.itemService.list();
        Collections.sort(list, Transaction.COMPARE_BY_ID);
        List<Category> categoryList = this.categoryItemService.list();

        model.addAttribute("categoriesList", categoryList);
        model.addAttribute("transaction", new Transaction());
        model.addAttribute("transactionsList", list);

        return "transactions";
    }

    @RequestMapping(value = "add")
    public String add(@ModelAttribute(value = "transaction") Transaction transaction){
        if(transaction.getId() == 0) {
            this.itemService.add(transaction);
        } else {
            this.itemService.update(transaction);
        }

        return "redirect:/transactions";
    }

    @RequestMapping("remove/{id}")
    public String remove(@PathVariable("id") int id){
        this.itemService.remove(id);

        return "redirect:/transactions";
    }

    @RequestMapping("edit/{id}")
    public String edit(@PathVariable("id") int id, Model model){
        List<Transaction> list = this.itemService.list();
        Collections.sort(list, Transaction.COMPARE_BY_ID);
        List<Category> categoryList = this.categoryItemService.list();

        model.addAttribute("categoriesList", categoryList);
        model.addAttribute("transactionsList", list);
        model.addAttribute("transaction", this.itemService.getById(id));

        return "transactions";
    }


}
