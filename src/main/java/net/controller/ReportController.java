package net.controller;

import net.model.Category;
import net.model.ReportForm;
import net.model.Transaction;
import net.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@Transactional
public class ReportController {

    private ItemService<Category> categoryItemService;
    private ItemService<Transaction> transactionItemService;

    @Autowired(required = true)
    @Qualifier(value = "categoryService")
    public void setCategoryItemService(ItemService<Category> categoryItemService) {
        this.categoryItemService = categoryItemService;
    }

    @Autowired(required = true)
    @Qualifier(value = "transactionService")
    public void setTransactionItemService(ItemService<Transaction> transactionItemService) {
        this.transactionItemService = transactionItemService;
    }

    @RequestMapping(value = "/report", method = RequestMethod.GET)
    public String index(Model model) {
        List<Category> list = this.categoryItemService.list();

        model.addAttribute("categoriesList", list);
        model.addAttribute("reportForm", new ReportForm());

        return "report";
    }

    @RequestMapping(value = "/charts", params = "byDates", method = RequestMethod.POST)
    public String byDates(@ModelAttribute("reportForm") ReportForm reportForm, BindingResult result, ModelMap model) {
        String dateFrom = this.getStringDate(reportForm.getDate1());
        String dateTo = this.getStringDate(reportForm.getDate2());

        String query = "SELECT t.date, SUM(t.total) FROM Transaction t " +
                "WHERE t.date between '" + dateFrom + "' and '" + dateTo +
                "'AND t.type = '" + reportForm.getType() + "' GROUP BY t.date" +
                " order by date(t.date) asc";
        List transactions = this.transactionItemService.listByQuery(query);

        List<Map<Object,Object>> chartList = new ArrayList<Map<Object,Object>>();
        Map<Object,Object> map = null;

        for (Object o : transactions) {
            Object[] row = (Object[]) o;
            Date date = (Date) row[0];
            Double total = (Double) row[1];

            map = new HashMap<Object,Object>();
            map.put("label", date);
            map.put("y", total);

            chartList.add(map);
        }

        model.addAttribute("reportForm", reportForm);
        model.addAttribute("chartList", chartList);
        return "day-by-day-report";
    }

    @RequestMapping(value = "/charts", params = "byCategories", method = RequestMethod.POST)
    public String charts(@ModelAttribute("reportForm") ReportForm reportForm, BindingResult result, ModelMap model) {

        String dateFrom = this.getStringDate(reportForm.getDate1());
        String dateTo = this.getStringDate(reportForm.getDate2());

        String query = "SELECT t.category.name AS nam, SUM(t.total) AS tot FROM Transaction t inner join t.category " +
                "WHERE t.date between '" + dateFrom + "' and '" + dateTo +
                "' AND t.type = '" + reportForm.getType() + "' GROUP BY t.category.name";
        List transactions = this.transactionItemService.listByQuery(query);

        double sum = 0;
        for (Object o : transactions) {
            Object[] row = (Object[]) o;
            Double total = (Double) row[1];
            sum += total;
        }

        List<Map<Object,Object>> chartList = new ArrayList<Map<Object,Object>>();
        Map<Object,Object> map = null;

        for (Object o : transactions) {
            Object[] row = (Object[]) o;
            String name = (String) row[0];
            Double total = (Double) row[1];
            double num = (total / sum) * 100;

            map = new HashMap<Object,Object>();
            map.put("label", name);
            map.put("y", (double) Math.round(num * 100) / 100);

            chartList.add(map);
        }

        model.addAttribute("reportForm", reportForm);
        model.addAttribute("chartList", chartList);
        model.addAttribute("transactions", transactions);

        return "by-categories-report";
    }


    private String getStringDate(Date date) {
        return new SimpleDateFormat("MM.dd.yyyy").format(date);
    }
}
