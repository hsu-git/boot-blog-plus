package org.example.bootblogplus.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@RequestMapping("/blog")
@RequiredArgsConstructor
public class BlogController {
    @GetMapping
    public String list(Model model) {
        model.addAttribute("list", blogService.getAllArticles());
        return "/blog/list";    // post 매핑일때는 redirect가 필요한데 get 매핑일때는 ..? 음..
    }

    @GetMapping("/new")
    public String newArticle(Model model) {
        model.addAttribute("form", new ArticleForm());
        return "/blog/form";
    }

    @PostMapping("/new")    // 오버로딩되서 /new 한번 더 써도 됨
    public String newArticle(ArticleForm form, RedirectAttributes redirectAttributes) {
        Article article = new Article();
        article.setTitle(form.title());
        article.setContent(form.content());
        try {
            if (blogService.createArticle(article)) {
                return "redirect:/blog";
            }
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute();
            throw new RuntimeException(e);
        }
        return "redirect:/blog/new";
    }
}
