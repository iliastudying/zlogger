package zlogger.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import zlogger.logic.models.Commentary;
import zlogger.logic.services.CommentaryService;
import zlogger.logic.services.PostService;

import javax.ws.rs.core.MediaType;
import java.util.List;

@Controller
public class CommentariesController {
    @Autowired
    PostService postService;

    @Autowired
    CommentaryService commentaryService;


    @RequestMapping(value = "/post/{id}/commentaries",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<Commentary> showPost(@PathVariable("id") long postId) {
        return commentaryService.listCommentariesForPost(postService.getPost(postId));
    }

}
