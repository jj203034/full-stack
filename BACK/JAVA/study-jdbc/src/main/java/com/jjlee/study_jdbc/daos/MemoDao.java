package com.jjlee.study_jdbc.daos;

import com.jjlee.study_jdbc.entities.MemoEntity;
import com.jjlee.study_jdbc.utils.DatabaseUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Timestamp;

public class MemoDao {
    public int insert(MemoEntity memo) throws Exception{
        try (Connection connection = DatabaseUtil.getConnection()) {
            String query = "INSERT INTO `study_jdbc`.`memos` (datetime, text) VALUE (?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setTimestamp(1, new Timestamp(memo.getDate().getTime()));
                preparedStatement.setString(2, memo.getText());
                return preparedStatement.executeUpdate();
            }
        }
    }
}
