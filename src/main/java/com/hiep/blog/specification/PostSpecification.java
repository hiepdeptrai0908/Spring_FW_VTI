package com.hiep.blog.specification;

import ch.qos.logback.core.util.StringUtil;
import com.hiep.blog.entity.Post;
import com.hiep.blog.form.PostFilterForm;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.LinkedList;

public class PostSpecification {
    public static Specification<Post> buildSpec(PostFilterForm form) {
        return form == null ? null : (root, query, builder) -> {
            var predicates = new LinkedList<Predicate>();

            // Lọc thông tin "search"
            var search = form.getSearch();
            if (StringUtils.hasText(search)) {
                var pattern = "%" + search.trim() + "%";
                var predicate = builder.like(root.get("title"), pattern);
                predicates.add(predicate);
            }

            // Lọc thông tin theo ngày tạo nhỏ nhất
            var minCreateDate = form.getMinCreatedDate();
            if (minCreateDate != null) {
                var minCreatedAt = LocalDateTime.of(minCreateDate, LocalTime.MIN);
                var predicate = builder.greaterThanOrEqualTo(
                        root.get("createdAt"),
                        minCreatedAt
                );
                predicates.add(predicate);
            }

            // Lọc thông tin theo ngày tạo lớn nhất
            var maxCreateDate = form.getMaxCreatedDate();
            if (maxCreateDate != null) {
                var maxCreatedAt = LocalDateTime.of(maxCreateDate, LocalTime.MAX);
                var predicate = builder.lessThanOrEqualTo(root.get("createdAt"), maxCreatedAt);
                predicates.add(predicate);
            }


            // Chuyển hoá thành 1 danh sách và ghép nhiều điều kiện lại với nhau
            return builder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
