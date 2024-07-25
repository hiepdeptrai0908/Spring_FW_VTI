package com.hiep.blog.repository;

import com.hiep.blog.entity.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long>, JpaSpecificationExecutor<Comment> {

    // 1. Method name

    // TIỀN TỐ:
    // findBy: Tìm kiếm
    // existsBy: Kiểm tra tồn tại hay không
    // countBy: Đếm số lượng
    // deleteBy: Xoá
    // * Không thể thêm dữ liệu, hãy dùng save() được xây dựng sẵn
    // Tài liệu tham khảo: https://docs.spring.io/spring-data/jpa/reference/jpa/query-methods.html
    // VD: Lấy ra tất cả comment theo name
    List<Comment> findByName(String name);
    // VD: Lấy ra tất cả comment có body chứa "SEARCH"
    List<Comment> findByBodyContaining(String search);
    // VD: Lấy ra tất cả comment theo name hoặc email người dùng nhập vào
    List<Comment> findByNameOrEmail(String name, String email);
    // VD: Lấy ra tất cả comment bằng post id và phân trang
    Page<Comment> findByPostId(Long postId, Pageable pageable);


    // 2. @Query
    // HQL: Theo entity
    // VD: Xoá dữ liệu theo email
    @Query("DELETE FROM Comment WHERE email = :email")
    void deleteByEmail(@Param("email") String email);
    // VD: Xoá dữ liệu theo name và email
    @Query("DELETE FROM Comment WHERE name = ?1 AND email = ?2")
    void deleteByNameAndEmail(String name, String email);

    // SQL: Theo bảng database, Native Query
    // Lấy comment có id lớn hơn ?
    @Query(value = "SELECT * FROM comment WHERE id > ?1", nativeQuery = true)
    Page<Comment> findByIdGreaterThan(Long id, Pageable pageable);
}
