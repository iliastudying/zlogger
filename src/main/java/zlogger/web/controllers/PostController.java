package zlogger.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import zlogger.logic.services.CommentaryService;
import zlogger.logic.services.PostService;
import zlogger.web.models.IndexModel;
import zlogger.web.models.PostModel;

@Controller
//@RequestMapping("/posts")
public class PostController {
    @Autowired
    PostService postService;

    @Autowired
    CommentaryService commentaryService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String displayHome() {
        return "redirect:/list";
    }

    @RequestMapping("/list")
    public ModelAndView getList() {
        IndexModel model = new IndexModel();
        model.setWelcome("test welcome");
        model.setPosts(postService.listPosts());
        model.setCanAddPost(false); //todo get user rights
        model.setUrlAddPost("/post/add"); //todo implement
        return new ModelAndView("list", "indexModel", model);
    }

    @RequestMapping("/post/{id}")
    public ModelAndView showPost(@PathVariable("id") long postId) {
        PostModel model = new PostModel();
        model.setPost(postService.getPost(postId));
        //model.setCommentaries(commentaryService.listCommentariesForPost(model.getPost())); load by ajax
        model.setUrlLoadCommentary("/post/"+postId+"/commentaries");
        model.setCanAddCommnetary(false); //todo get user rights
        model.setUrlAddCommentary("/post/"+postId+"/addcomment"); //todo implement
        return new ModelAndView("post", "postModel", model);
    }

}
