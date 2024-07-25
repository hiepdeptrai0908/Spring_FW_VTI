package com.hiep.blog.specifition;

import com.hiep.blog.entity.Comment;
import com.hiep.blog.entity.Post;
import com.hiep.blog.form.CommentFilterForm;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import java.util.LinkedList;

public class CommentSpecifition {
    public static Specification<Comment> buildSpec(CommentFilterForm form) {
        return form == null ? null : new Specification<Comment>() {
            @Override
            public Predicate toPredicate(
                    Root<Comment> root,
                    CriteriaQuery<?> query,
                    CriteriaBuilder builder
            ) {
                var predicates = new LinkedList<Predicate>();
                var search = form.getSearch();
                if (StringUtils.hasText(search)) {
                    var pattern = "%" + search + "%";
                    var predicateName = builder.like(root.get("name"), pattern);
                    var predicateEmail = builder.like(root.get("email"), pattern);
                    var predicate = builder.or(predicateName, predicateEmail);
                    predicates.add(predicate);
                }

                var postId = form.getPostId();
                if (postId != null) {
                    var predicate = builder.equal(root.get("post").get("id"), postId);
                    predicates.add(predicate);
                }

                return builder.and(predicates.toArray(new Predicate[0]));
            }
        };
    }
}
