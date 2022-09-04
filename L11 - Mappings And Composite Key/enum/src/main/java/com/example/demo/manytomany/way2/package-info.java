package com.example.demo.manytomany.way2;


/**
 *
 *
 * wishlist --- it was in memory mapping and there was not any extra information that was added
 *
 *
 *
 *
 *      User               (Course Rating)                 Course
 *
 *        u_p1                                              c_pk1
 *        u_p2                                              c_pk2
 *        u_p3                                              c_pk3
 *
 *                          rating
 *                          reviews
 *                          (Composite primary key)
 *                       id , user_primary key , Course primary key , rating , review
 *                      id_1,    u_p1 ,                       c_pk1 , ratings , review
 *                      id_2,    u_p1 ,                       c_pk2 , ratings , review
 *                      id_3,    u_p1 ,                       c_pk3 , ratings , review
 *                      id_4,    u_p2 ,                       c_pk2 , ratings , review
 *
 *
 *
 *     User ------ Course Rating
 *
 *      One unique user ---------> course rating -- (Many)
 *          1   -------------- N
 *         1    <-------------  1 unique course rating
 *
 *
 *
 *      Course -------- Course Rating
 *      1 unqiue course will have ----------> Course Rating
 *      1 ---------> N
 *
 * @Mnay to Many
 *      --- one to many and many to one
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *                            Home work - Primary as Index vs Unique key as Index
 *
 +-----------+---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+
 1 row in set (0.00 sec)

 mysql>
 mysql>
 mysql> show indexes from user_info;
 +-----------+------------+------------+--------------+-------------+-----------+-------------+----------+--------+------+------------+---------+---------------+---------+------------+
 | Table     | Non_unique | Key_name   | Seq_in_index | Column_name | Collation | Cardinality | Sub_part | Packed | Null | Index_type | Comment | Index_comment | Visible | Expression |
 +-----------+------------+------------+--------------+-------------+-----------+-------------+----------+--------+------+------------+---------+---------------+---------+------------+
 | user_info |          0 | PRIMARY    |            1 | id          | A         |           0 |     NULL |   NULL |      | BTREE      |         |               | YES     | NULL       |
 | user_info |          0 | identifier |            1 | identifier  | A         |           0 |     NULL |   NULL | YES  | BTREE      |         |               | YES     | NULL       |
 +-----------+------------+------------+--------------+-------------+-----------+-------------+----------+--------+------+------------+---------+---------------+---------+------------+
 2 rows in set (0.00 sec)
 *
 *
 *
 *
 *      User to Course
 *         1----  N
 *         N  -----1
 *      (many to many mapping)
 *
 *
 */