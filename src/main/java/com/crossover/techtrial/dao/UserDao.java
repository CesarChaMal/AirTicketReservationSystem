package com.crossover.techtrial.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.DependsOn;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.crossover.techtrial.airline.context.AuthenticationStrategy;
import com.crossover.techtrial.airline.context.Google;
import com.crossover.techtrial.airline.context.Rol;
import com.crossover.techtrial.airline.context.SocialNetwork;
import com.crossover.techtrial.airline.context.Twitter;
import com.crossover.techtrial.airline.context.User;
import com.crossover.techtrial.util.EncryptionDecryption;

@DependsOn({"dataSourceSQL"})
public class UserDao {

	private static final Logger log = Logger.getLogger(UserDao.class);

	private static BasicDataSource dataSourceSQL;
	
	private User user;

	@Autowired
	public UserDao(BasicDataSource basicDataSource) {
		this.dataSourceSQL = basicDataSource;
	}

	public UserDao() {
	}

	public UserDao(User user) {
		this.user = user;
	}
	
	// get the current set dataSource
	private BasicDataSource getDataSource() {
		BasicDataSource ds;
		log.info("Active DataSource is set to MYSQL: " + dataSourceSQL.toString());
		ds = this.dataSourceSQL;
		return ds;
	}

	public List<User> findAll() {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(getDataSource());
		return jdbcTemplate.query("select * from user", new UserRowMapper()); 
	}

	public User findByUserName(String username) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(getDataSource());
		List<User> list = jdbcTemplate.query("select * from user where username='" + username + "'", new UserRowMapper());
		return list.size() == 0 ? null : list.get(0);
	}

	public User findByUserNamePassword(String username, String password) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(getDataSource());
		
		String passEnc = "";
		String passDec = "";

		List<String> passList = jdbcTemplate.query("select password from user where username='" + username + "'", new UserPasswordRowMapper());
		if (!(passList.size() == 0)){
			passEnc = passList.get(0);
		} else {
			return null;
		}
			
		EncryptionDecryption.getInstance();
		passDec = EncryptionDecryption.decrypt2(passEnc);
		
		List<User> list = new ArrayList<>(); 
		if (password.equals(passDec)){
			list = jdbcTemplate.query("select * from user where username='" + username + "'", new UserRowMapper());
		}
		return list.size() == 0 ? null : list.get(0);
	}
	
	public class UserRowMapper implements RowMapper<User> {
		public User mapRow(ResultSet rs, int line) throws SQLException {
			UserResultSetExtractor extractor = new UserResultSetExtractor();
			return extractor.extractData(rs);
		}
	}

	public class UserPasswordRowMapper implements RowMapper<String> {
		public String mapRow(ResultSet rs, int line) throws SQLException {
			UserPasswordResultSetExtractor extractor = new UserPasswordResultSetExtractor();
			return extractor.extractData(rs);
		}
	}

	public class UserResultSetExtractor {

		public User extractData(ResultSet rs) throws SQLException, DataAccessException {
			
			SocialNetwork socialNetWork = getSocialNetwork(rs);
			Rol rol = new Rol(rs.getInt("rol_id"));
			
			User user = new User.Builder(rs.getString("username"), rs.getString("password"))
						.setName(rs.getString("name"))
						.setSocialNetwork(socialNetWork)
						.setRol(rol)
						.build();
			
			log.debug("Name: " + user.getName());
			log.debug("Password: " + user.getPassword());
			log.debug("Username: " + user.getUserName());

			return user;
		}

		private SocialNetwork getSocialNetwork(ResultSet rs) throws SQLException {
			AuthenticationStrategy strategy = null;
			int stategyId = rs.getInt("social_network_id");
			if (stategyId != 0){
				if (stategyId == 1)
					strategy = new Google();
				else if (stategyId == 2)
					strategy = new Twitter();
			} 
			
			SocialNetwork socialNetWork = new SocialNetwork(rs.getString("username"));
			socialNetWork.setStrategy(strategy);
            String passEnc= rs.getString("password");
            EncryptionDecryption.getInstance();
			socialNetWork.setPassword(EncryptionDecryption.decrypt2(passEnc));
			socialNetWork.setName(rs.getString("name"));
			socialNetWork.authenticate(socialNetWork.getId(), socialNetWork.getPassword());
			return socialNetWork;
		}
		
	}

	public class UserPasswordResultSetExtractor {
		public String extractData(ResultSet rs) throws SQLException, DataAccessException {
			
			String pass = rs.getString("password");
			log.debug("Password: " + pass);

			return pass;
		}
	}
}
