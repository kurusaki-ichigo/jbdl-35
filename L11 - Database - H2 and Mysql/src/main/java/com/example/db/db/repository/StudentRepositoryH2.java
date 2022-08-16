package com.example.db.db.repository;

import com.example.db.db.model.entities.Student;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.Objects;
import java.util.Optional;

@Repository("h2Repository")
@Slf4j
public class StudentRepositoryH2 implements IStudentRepository, InitializingBean {
    private Connection connection ;


    private static final String CREATE_TABLE = " CREATE TABLE IF NOT EXISTS `student` ( " +
            " `id` integer NOT NULL AUTO_INCREMENT, " +
            "  `name` varchar(50) DEFAULT NULL, " +
            " `age` integer DEFAULT NULL, " +
            "  PRIMARY KEY (`id`) " +
            ")  ";

    private static final String INSERT_INTO_STUDENT = " INSERT INTO `student`(`name`, `age`)" +
            " values (?,?) ";

    private static final String FIND_STUDENT = "SELECT * from `student` where id=";
    @SneakyThrows
    @Override
    public Student save(Student student) {
        PreparedStatement preparedStatement = connection.prepareStatement(INSERT_INTO_STUDENT);
        preparedStatement.setString(1,student.getName());
        preparedStatement.setInt(2,student.getAge());
        int i = preparedStatement.executeUpdate();
        log.info(" saving student {} ", i);
        return student;
    }

    @SneakyThrows
    @Override
    /**
     * uuid = 1; drop database;
     */
    public Optional<Student> findById(Integer uuid) {
        Statement fetch = connection.createStatement();
        ResultSet resultSet = fetch.executeQuery(FIND_STUDENT + uuid);
        Student output = null;
        while (resultSet.next()){
            output = Student.builder()
                    .id(resultSet.getInt("id"))
                    .name(resultSet.getString("name"))
                    .age(resultSet.getInt("age"))
                    .build();
            if(Objects.nonNull(output)){
                break;
            }
        }
        return Optional.ofNullable(output);
    }


    @Override
    public void afterPropertiesSet() throws Exception {

        try {
            connection = DriverManager.getConnection("jdbc:h2:mem:test", "root" , "");
            Statement statement = connection.createStatement();
            statement.execute(CREATE_TABLE);
            log.info("created the table ");
        } catch (Exception exception){
            log.error("error received" ,exception);
        }

    }
}
