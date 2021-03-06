package org.spring.tutorial.examples.jpa.dao;

import org.spring.tutorial.examples.jpa.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserDaoImpl implements IUserDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public User getUserById(long id){
		String sql = "SELECT * FROM users WHERE ID = ?";
		try {

			return (User) jdbcTemplate.queryForObject(
					sql, new Object[] { id }, new BeanPropertyRowMapper(User.class));
		}catch (EmptyResultDataAccessException ex){

			System.out.println("Cannot find the user : " + ex.toString());
			return null;
		}

	}
	
	public List<User> findAll(){
		String sql = "SELECT * FROM users";
		List<User> users  = jdbcTemplate.query(sql,new BeanPropertyRowMapper(User.class));
		return users;
	}
	
	public void deleteById(long id) {
		
		String deleteStatement = "DELETE FROM users WHERE id=?";
		jdbcTemplate.update(deleteStatement, id);
	}

	@Override
	public void deleteByIdNewTransaction(long id) {
		String deleteStatement = "DELETE FROM users WHERE id=?";
		jdbcTemplate.update(deleteStatement, id);
	}

	public int createUser(User user) {
		String insertSql="INSERT INTO users values(?,?,?)";
		return jdbcTemplate.update(insertSql,new Object[]{user.getId(),user.getName(),user.getEmail()});
	}

	public int updateUser(User user) {
		
		String updateSql="UPDATE users set name=?,email=? where id=?";
		return jdbcTemplate.update(updateSql,new Object[]{user.getName(),user.getEmail(),user.getId()});
	}
}
