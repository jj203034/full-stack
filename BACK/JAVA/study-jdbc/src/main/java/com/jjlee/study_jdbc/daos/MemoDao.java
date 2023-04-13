package com.jjlee.study_jdbc.daos;

import com.jjlee.study_jdbc.entities.MemoEntity;
import com.jjlee.study_jdbc.utils.DatabaseUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class MemoDao {
    public int insert(MemoEntity memo) throws Exception {
        try (Connection connection = DatabaseUtil.getConnection()) {
            String query = "INSERT INTO `study_jdbc`.`memos` (datetime, text) VALUE (?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setTimestamp(1, new Timestamp(memo.getDate().getTime()));
                preparedStatement.setString(2, memo.getText());
                return preparedStatement.executeUpdate();
            }
        }
    }

    public List<MemoEntity> select(int page) throws Exception {
        List<MemoEntity> memos = new ArrayList<>();
        try (Connection connection = DatabaseUtil.getConnection()) {
            String query = "" +
                    "SELECT `index`    AS `index`,\n" +
                    "       `datetime` AS `datetime`,\n" +
                    "       `text`     AS `text`\n" +
                    "FROM `study_jdbc`.`memos`\n" +
                    "ORDER BY `index` DESC\n" +
                    "LIMIT 10 OFFSET ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, 10 * (page - 1));
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        MemoEntity memo = new MemoEntity()
                                .setIndex(resultSet.getInt("index"))
                                .setDate(resultSet.getDate("datetime"))
                                .setText(resultSet.getString("text"));
                        memos.add(memo);
                    }
                }
            }
        }
        return memos;
    }

    public int selectCount() throws Exception {
        try (Connection connection = DatabaseUtil.getConnection()) {
            String query = "" +
                    "SELECT COUNT(0) AS `count`\n" +
                    "FROM `study_jdbc`.`memos`;";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    resultSet.next();
                    return resultSet.getInt("count");
                }
            }
        }
    }

    public int delete(int index) throws Exception {
        try (Connection connection = DatabaseUtil.getConnection()) {
            String query = "" +
                    "DELETE\n" +
                    "FROM `study_jdbc`.`memos`\n" +
                    "WHERE `index` = ?\n" +
                    "LIMIT 1";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, index);
                return preparedStatement.executeUpdate();
            }
        }
    }
}

