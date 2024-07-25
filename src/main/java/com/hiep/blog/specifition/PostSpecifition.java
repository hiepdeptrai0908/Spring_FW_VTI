package com.hiep.blog.specifition;

import com.hiep.blog.entity.Post;
import com.hiep.blog.form.PostFilterForm;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.LinkedList;

public class PostSpecifition {
    public static Specification<Post> buildSpec(PostFilterForm form) {
        return form == null ? null : new Specification<Post>() {
            @Override
            public Predicate toPredicate(
                    Root<Post> root,
                    CriteriaQuery<?> query,
                    CriteriaBuilder builder
            ) {
                var predicates = new LinkedList<Predicate>();

                // Lọc bằng thông tin tìm kiếm
                var search = form.getSearch();
                if (StringUtils.hasText(search)) {
                    var pattern = "%" + search + "%";
                    var predicate = builder.like(root.get("title"), pattern);
                    predicates.add(predicate);
                }

                // Lọc theo ngày nhỏ nhất
                var minCreatedDate = form.getMinCreatedDate();
                if (minCreatedDate != null) {
                    var minCreatedAt = LocalDateTime.of(minCreatedDate, LocalTime.MIN);
                    var predicate = builder.greaterThanOrEqualTo(root.get("createdAt"), minCreatedAt);
                    predicates.add(predicate);
                }

                // Lọc theo ngày lớn nhất
                var maxCreatedDate = form.getMaxCreatedDate();
                if (maxCreatedDate != null)  {
                    var maxCreatedAt = LocalDateTime.of(maxCreatedDate, LocalTime.MAX);
                    var predicate = builder.lessThanOrEqualTo(root.get("createdAt"), maxCreatedAt);
                    predicates.add(predicate);
                }

                // Lọc theo status
                var status = form.getStatus();
                if (status != null) {
                    var predicate = builder.equal(root.get("status"), status);
                    predicates.add(predicate);
                }

                return builder.and(predicates.toArray(new Predicate[0]));
            }
        };
    }
}
