INSERT INTO user(name      , username  , email               , password                                                      , role      , created_at       , updated_at       )
VALUES          ('Manager' , 'manager' , 'manager@gmail.com' , '$2a$10$A6hHP3WElSJmzlBIqPzTfu/eQAsE8LRGmFyG.7zEFFymkFFpJLwSi', 'MANAGER' , CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
                ('Employee', 'employee', 'employee@gmail.com', '$2a$10$A6hHP3WElSJmzlBIqPzTfu/eQAsE8LRGmFyG.7zEFFymkFFpJLwSi', 'EMPLOYEE', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO post(id, title     , content                           , description             , status   , created_at       , updated_at       )
VALUES          (1 ,'Java Core', 'Learn Spring Framework with Khoa', 'Learn Spring Framework', 'O'      , CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO comment(id, name             , email              , body              , post_id, created_at       , updated_at       )
VALUES             (1 , 'Nguyễn Văn Khoa', 'khoa.nv@gmail.com', 'Bài viết rất hay', 1      , CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);