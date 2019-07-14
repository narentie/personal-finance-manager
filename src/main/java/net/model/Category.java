package net.model;

import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;

import java.util.Comparator;
import java.util.Set;

@Entity
@Table(name = "CATEGORIES")
@Transactional
public class Category {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "CATEGORY_NAME")
    private String name;

    @Column(name = "CATEGORY_DESCRIPTION")
    @JoinColumn(name="ID")
    private String description;

    @OneToMany(mappedBy = "category")
    private Set<Transaction> transactions;

    public static Comparator<Category> COMPARE_BY_NAME = new Comparator<Category>() {
        public int compare(Category c1, Category c2) {
            return c1.getName().compareTo(c2.getName());
        }
    };

    public static Comparator<Category> COMPARE_BY_ID = new Comparator<Category>() {
        public int compare(Category c1, Category c2) {
            return new Integer(c1.getId()).compareTo(c2.getId());
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
