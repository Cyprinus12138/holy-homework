package com.example.homework.service;

import com.example.homework.entity.Item;
import com.example.homework.entity.Item_view;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

import javax.annotation.Resource;

@Service
public class ItemService {

    @Resource
    private JdbcTemplate jdbcTemplate;

    public boolean create(Item item) {
        String sql = "INSERT INTO item VALUES(null,?,?,?,?)";
        try {
            jdbcTemplate.update(sql, item.getItem_name(), item.getUnit(), item.getPrice(), item.getPic_url());
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public boolean update(Item item, int id) {
        String sql = "UPDATE  item SET item_name=?,price=?,pic_url=? WHERE item_id=?";
        try {
            jdbcTemplate.update(sql, item.getItem_name(), item.getPrice(), item.getPic_url(), id);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public List<Item> findItemById(int id) {
        String sql = "SELECT * FROM item WHERE item_id=" + id;
        try {
            return jdbcTemplate.query(sql, (resultSet, i) -> {
                Item item = new Item();
                item.setItem_name(resultSet.getString("item_name"));
                item.setUnit(resultSet.getString("unit"));
                item.setPic_url(resultSet.getString("pic_url"));
                item.setItem_id(resultSet.getInt("item_id"));
                item.setPrice(resultSet.getInt("price"));
                return item;
            });
        } catch (Exception e) {
            return null;
        }
    }

    public List<Item> getAllItems(int pageIndex, int pageSize) {
        String sql = String.format("SELECT * FROM item LIMIT %d OFFSET %d", pageSize, pageSize * (pageIndex - 1));
        try {
            return jdbcTemplate.query(sql, (resultSet, i) -> {
                Item item = new Item();
                item.setItem_name(resultSet.getString("item_name"));
                item.setUnit(resultSet.getString("unit"));
                item.setPic_url(resultSet.getString("pic_url"));
                item.setItem_id(resultSet.getInt("item_id"));
                item.setPrice(resultSet.getInt("price"));
                return item;
            });
        } catch (Exception e) {
            return null;
        }
    }

    public int getItemNum() {
        String sql = "SELECT COUNT(*) AS num FROM item";
        try {
            return jdbcTemplate.queryForObject(sql, Integer.class);
        } catch (Exception e) {
            return 0;
        }
    }

    public List<Item_view> getAllItemRec(int pageIndex, int pageSize) {
        String sql = String.format("SELECT * FROM item_view LIMIT %d OFFSET %d", pageSize, pageSize * (pageIndex - 1));
        try {
            return jdbcTemplate.query(sql, (resultSet, i) -> {
                Item_view itemView = new Item_view();
                itemView.setItem_name(resultSet.getString("item_name"));
                itemView.setUnit(resultSet.getString("unit"));
                itemView.setPrice(resultSet.getInt("price"));
                itemView.setPic_url(resultSet.getString("pic_url"));
                itemView.setNum(resultSet.getInt("num"));
                itemView.setRepo_id(resultSet.getInt("repo_id"));
                itemView.setItem_id(resultSet.getInt("item_id"));
                return itemView;
            });
        } catch (Exception e) {
            return null;
        }
    }


}



